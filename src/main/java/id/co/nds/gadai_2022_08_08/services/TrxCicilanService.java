package id.co.nds.gadai_2022_08_08.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import id.co.nds.gadai_2022_08_08.entities.TrxCicilanEntity;
import id.co.nds.gadai_2022_08_08.repos.TrxCicilanRepo;

@Service
public class TrxCicilanService implements Serializable {
    @Autowired
    TrxCicilanRepo trxCicilanRepo;

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    public List<String> doUpdateStatusCicilan() {
        List<TrxCicilanEntity> cicilan = new ArrayList<>();
        List<String> updated = new ArrayList<>();

        cicilan = trxCicilanRepo.getChangeToActive();
        for(int i = 0; i < cicilan.size(); i++) {
            cicilan.get(i).setTxStatus("AKTIF");

            trxCicilanRepo.save(cicilan.get(i));

            updated.add("{" + cicilan.get(i).getNoTransaksi() + " - " + cicilan.get(i).getCicilanKe() + "}: BELUM AKTIF became AKTIF");
        }

        cicilan = trxCicilanRepo.getChangeToLate();
        for(int i = 0; i < cicilan.size(); i++) {
            cicilan.get(i).setTxStatus("TERLAMBAT");

            trxCicilanRepo.save(cicilan.get(i));

            updated.add("{" + cicilan.get(i).getNoTransaksi() + " - " + cicilan.get(i).getCicilanKe() + "}: AKTIF became TERLAMBAT");
        }

        return updated;
    }

    public List<TrxCicilanEntity> doGetCicilanTerlambat() {
        return trxCicilanRepo.getLateCicilan();
    }
}
