package id.co.nds.gadai_2022_08_08.services;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import id.co.nds.gadai_2022_08_08.entities.TrxCicTetapEntity;
import id.co.nds.gadai_2022_08_08.entities.TrxCicilanEntity;
import id.co.nds.gadai_2022_08_08.entities.TrxDendaEntity;
import id.co.nds.gadai_2022_08_08.models.TrxCicTetapModel;
import id.co.nds.gadai_2022_08_08.repos.TrxDendaRepo;

@Service
public class TrxDendaService implements Serializable {
    @Autowired
    TrxDendaRepo trxDendaRepo;

    @Autowired
    TrxCicTetapService trxCicTetapService;

    @Autowired
    TrxCicilanService trxCicilanService;

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    public List<TrxDendaEntity> doCheckDenda() {
        List<TrxDendaEntity> dendaList = new ArrayList<>();
        TrxDendaEntity trxDendaEntity = new TrxDendaEntity();
        trxDendaEntity.setTglDenda(new Date(System.currentTimeMillis()));

        List<TrxCicilanEntity> cicilan = new ArrayList<>();
        cicilan = trxCicilanService.doGetCicilanTerlambat();

        if(cicilan.size() == 0) {
            return null;
        }

        Calendar start = Calendar.getInstance();
        Calendar current = Calendar.getInstance();
        current.setTime(new java.util.Date(System.currentTimeMillis()));

        TrxCicTetapModel trxModel = new TrxCicTetapModel();
        trxModel.setActorId("Scheduler");
        for(int i = 0; i < cicilan.size(); i++) {
            if(trxDendaRepo.countDuplicate(cicilan.get(i).getNoTransaksi(), cicilan.get(i).getCicilanKe(), trxDendaEntity.getTglDenda()) == 0) {
                trxModel.setNoTransaksi(cicilan.get(i).getNoTransaksi());
                TrxCicTetapEntity trx = trxCicTetapService.doGetDetailCicTetap(trxModel);

                start.setTime(cicilan.get(i).getTanggalJatuhTempo());
                start.add(Calendar.DAY_OF_MONTH, 1);
                
                if(trx.getProduct().getProductTipe().equalsIgnoreCase("Konsinyasi Cicilan Fleksibel")) {
                    long timeDiff = Math.abs(setZeroDate(new Date(start.getTime().getTime())).getTime() - setZeroDate(new Date(System.currentTimeMillis())).getTime());
                    long daysDiff = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);

                    if(daysDiff % trx.getProduct().getProductBiayaDendaPeriode() == 0) {
                        trxDendaEntity.setNoTransaksi(trx.getNoTransaksi());
                        trxDendaEntity.setCicilanKe(cicilan.get(i).getCicilanKe());
                        trxDendaEntity.setBiayaDenda(new BigDecimal(trx.getProduct().getProductBiayaDenda().doubleValue() / 100 * (cicilan.get(i).getTxPokok().doubleValue() + cicilan.get(i).getTxBunga().doubleValue())).setScale(2, RoundingMode.HALF_UP));

                        trxDendaRepo.save(trxDendaEntity);
                        dendaList.add(trxDendaEntity);
                    }
                }
                else {
                    long monthsDiff = (current.get(Calendar.YEAR) - start.get(Calendar.YEAR)) * 12 + current.get(Calendar.MONTH) - start.get(Calendar.MONTH);
                    
                    if(monthsDiff % trx.getProduct().getProductBiayaDendaPeriode() == 0 && start.get(Calendar.DATE) == current.get(Calendar.DATE)) {
                        trxDendaEntity.setNoTransaksi(trx.getNoTransaksi());
                        trxDendaEntity.setCicilanKe(cicilan.get(i).getCicilanKe());
                        trxDendaEntity.setBiayaDenda(new BigDecimal(trx.getProduct().getProductBiayaDenda().doubleValue() / 100 * (cicilan.get(i).getTxPokok().doubleValue() + cicilan.get(i).getTxBunga().doubleValue())).setScale(2, RoundingMode.HALF_UP));

                        trxDendaRepo.save(trxDendaEntity);
                        dendaList.add(trxDendaEntity);
                    }
                }
            }
        }

        return dendaList;
    }

    private static Date setZeroDate(Date date) {
        Calendar calendar = Calendar.getInstance();
    
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    
        return new Date(calendar.getTime().getTime());
    }
}
