package id.co.nds.gadai_2022_08_08.services;

import java.io.Serializable;

// import java.io.Serializable;
// import java.math.BigDecimal;
// import java.math.RoundingMode;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

// import id.co.nds.gadai_2022_08_08.domains.noTransaksi;
import id.co.nds.gadai_2022_08_08.entities.CicTetapEntity;
import id.co.nds.gadai_2022_08_08.entities.CicilanEntity;
import id.co.nds.gadai_2022_08_08.entities.DendaEntity;
import id.co.nds.gadai_2022_08_08.exceptions.ClientException;
import id.co.nds.gadai_2022_08_08.exceptions.NotFoundException;
import id.co.nds.gadai_2022_08_08.models.CicTetapModel;
import id.co.nds.gadai_2022_08_08.repos.AktivityRepo;
import id.co.nds.gadai_2022_08_08.repos.CicilanRepo;
import id.co.nds.gadai_2022_08_08.repos.DendaRepo;

@Service
@Transactional
public class ActivityService implements Serializable {
    @Autowired
    private DendaRepo dendaRepo;

    @Autowired
    private CicilanRepo CicilanRepo;

    @Autowired
    private AktivityRepo aktivityRepo;

    @Autowired
    CicTetapService cicTetapService;

    @Autowired
    CicilanService cicilanService;

    private static Date setDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date getDate = new Date(calendar.getTime().getTime());
        return getDate;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { Exception.class })
    public List<DendaEntity> doCheckDenda() throws ClientException, NotFoundException {
        List<DendaEntity> denda = new ArrayList<>();
        DendaEntity denda2 = new DendaEntity();
        denda2.setTglDenda(new Date(System.currentTimeMillis()));

        List<CicilanEntity> cicilan = new ArrayList<>();
        cicilan = aktivityRepo.BelumBayar();

        Calendar now = Calendar.getInstance();
        now.setTime(new java.util.Date(System.currentTimeMillis()));
        Calendar awal = Calendar.getInstance();

        long c = setDate(new Date(awal.getTime().getTime())).getTime();
        long d = setDate(new Date(System.currentTimeMillis())).getTime();

        CicTetapModel cic = new CicTetapModel();
        cic.setActorId("Cicilan");

        for (int i = 0; i < cicilan.size(); i++) {
            Long count = dendaRepo.count(cicilan.get(i).getNoTransaksi(), cicilan.get(i).getCicilanKe(),
                    new Date(System.currentTimeMillis()));
            long time = Math.abs(c - d);

            if (count == 0) {
                cic.setNoTransaksi(cicilan.get(i).getNoTransaksi());
                CicTetapEntity tx = cicTetapService.doGetCicTetap(cic.getNoTransaksi());

                awal.setTime(cicilan.get(i).getTanggalJatuhTempo());
                now.add(Calendar.DAY_OF_MONTH, 1);

                if (TimeUnit.DAYS.convert(time, TimeUnit.MILLISECONDS)
                        % tx.getProduct().getBiayaDendaKeterlambatanPer() == 0) {
                    denda2.setNoTransaksi(tx.getNoTransaksi());
                    denda2.setCicilanKe(cicilan.get(i).getCicilanKe());
                    denda2.setBiayaDenda(
                            (cicilan.get(i).getTxPokok().doubleValue() + cicilan.get(i).getTxBunga().doubleValue())
                                    * tx.getProduct().getBiayaDendaKeterlambatanrate() / 100);
                    denda2.setTglDenda(new Date(System.currentTimeMillis()));
                    dendaRepo.save(denda2);
                    denda.add(denda2);
                } else {
                    throw new NotFoundException("process is in valid");
                }
            }
        }

        return denda;
    }

    public List<CicilanEntity> schedulerCicilan() throws ClientException {
        List<CicilanEntity> cicilan = new ArrayList<>();
        // CicilanRepo.findAll().forEach(cicilan::add);

        if (cicilan == aktivityRepo.Aktif()) {
            for (Integer i = 0; i < cicilan.size(); i++) {
                cicilan.get(i).setTxStatus("AKTIF");

                aktivityRepo.save(cicilan.get(i));
                cicilan.forEach(cicilan::add);
            }
        } else if (cicilan == aktivityRepo.Terlambat()) {
            for (Integer i = 0; i < cicilan.size(); i++) {
                cicilan.get(i).setTxStatus("TERLAMBAT");

                aktivityRepo.save(cicilan.get(i));
                cicilan.forEach(cicilan::add);
            }
        }

        return cicilan;
    }
}
