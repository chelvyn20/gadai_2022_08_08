package id.co.nds.gadai_2022_08_08.services;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import id.co.nds.gadai_2022_08_08.entities.CicilanEntity;
// import id.co.nds.gadai_2022_08_08.entities.InfoCicilanEntity;
import id.co.nds.gadai_2022_08_08.exceptions.ClientException;
import id.co.nds.gadai_2022_08_08.exceptions.NotFoundException;
import id.co.nds.gadai_2022_08_08.globals.GlobalConstanst;
import id.co.nds.gadai_2022_08_08.models.CicilanModel;
import id.co.nds.gadai_2022_08_08.repos.CicilanRepo;
// import id.co.nds.gadai_2022_08_08.repos.InfoCicilanRepo;
import id.co.nds.gadai_2022_08_08.repos.specs.CicilanSpec;
// import id.co.nds.gadai_2022_08_08.validators.CicilanValidator;

@Service
public class CicilanService implements Serializable {
    @Autowired
    private CicilanRepo CicilanRepo;

    // @Autowired
    // private InfoCicilanRepo infoCicilanRepo;

    // CicilanValidator CicilanValidator = new CicilanValidator();

    public CicilanEntity add(CicilanModel CicilanModel) throws ClientException {
        // CicilanValidator.nullCheckNoTransaksi(CicilanModel.getNoTransaksi());
        // CicilanValidator.validateNotransaksi(CicilanModel.getNoTransaksi());

        Long count = CicilanRepo.countByTransaksi(CicilanModel.getNoTransaksi());
        if (count > 0) {
            throw new ClientException("Cicilan is already existed");
        }

        CicilanEntity Cicilan = new CicilanEntity();
        Cicilan.setNoTransaksi(CicilanModel.getNoTransaksi());
        Cicilan.setCicilanKe(CicilanModel.getCicilanKe());
        Cicilan.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        Cicilan.setTxPokok(CicilanModel.getTxPokok().doubleValue());
        Cicilan.setTxBunga(CicilanModel.getTxBunga().doubleValue());
        Cicilan.setTxStatus(CicilanModel.getTxStatus());
        Cicilan.setTanggalAktif(CicilanModel.getTanggalAktif());
        Cicilan.setTanggalJatuhTempo(CicilanModel.getTanggalJatuhTempo());
        Cicilan.setTanggalBayar(CicilanModel.getTanggalBayar());

        return CicilanRepo.save(Cicilan);
    }

    // public List<CicilanEntity> findAllByCriteria(CicilanModel CicilanModel) {
    //     List<CicilanEntity> Cicilan = new ArrayList<>();
    //     CicilanSpec spec = new CicilanSpec(CicilanModel);
    //     CicilanRepo.findAll(spec).forEach(Cicilan::add);
    //     return Cicilan;
    // }

    // public CicilanEntity findById(String noTransaksi) throws ClientException, NotFoundException {
    //     // CicilanValidator.nullCheckNoTransaksi(noTransaksi);
    //     // CicilanValidator.validateNotransaksi(noTransaksi);

    //     CicilanEntity Cicilan = CicilanRepo.findById(noTransaksi).orElse(null);
    //     // CicilanValidator.nullCheckObject(Cicilan);
    //     return Cicilan;
    // }

    // public CicilanEntity edit(CicilanModel CicilanModel) throws ClientException, NotFoundException {
    //     // CicilanValidator.nullCheckNoTransaksi(CicilanModel.getNoTransaksi());
    //     // CicilanValidator.validateNotransaksi(CicilanModel.getNoTransaksi());
    //     if (!CicilanRepo.existsById(CicilanModel.getNoTransaksi())) {
    //         throw new NotFoundException("Cannot find Cicilan with no transaksi = " + CicilanModel.getNoTransaksi());
    //     }

    //     CicilanEntity Cicilan = new CicilanEntity();
    //     Cicilan = findById(CicilanModel.getNoTransaksi());

    //     if (CicilanModel.getCicilanKe() != null) {
    //         // CicilanValidator.validateName(CicilanModel.getNamaCicilan());
    //         Cicilan.setCicilanKe(CicilanModel.getCicilanKe());
    //     }

    //     if (CicilanModel.getTxPokok() != null) {
    //         // CicilanValidator.validateKondisi(CicilanModel.getKondisi());
    //         Cicilan.setTxPokok(CicilanModel.getTxPokok());
    //     }

    //     if (CicilanModel.getTxBunga() != null) {
    //         // CicilanValidator.validateKondisi(CicilanModel.getKondisi());
    //         Cicilan.setTxBunga(CicilanModel.getTxBunga());
    //     }

    //     if (CicilanModel.getTxStatus() != null) {
    //         // CicilanValidator.validateKondisi(CicilanModel.getKondisi());
    //         Cicilan.setTxStatus(CicilanModel.getTxStatus());
    //     }
    //     if (CicilanModel.getTanggalAktif() != null) {
    //         // CicilanValidator.validateKondisi(CicilanModel.getKondisi());
    //         Cicilan.setTanggalAktif(CicilanModel.getTanggalAktif());
    //     }
    //     if (CicilanModel.getTanggalJatuhTempo() != null) {
    //         // CicilanValidator.validateKondisi(CicilanModel.getKondisi());
    //         Cicilan.setTanggalJatuhTempo(CicilanModel.getTanggalJatuhTempo());
    //     }
    //     if (CicilanModel.getTanggalBayar() != null) {
    //         // CicilanValidator.validateKondisi(CicilanModel.getKondisi());
    //         Cicilan.setTanggalBayar(CicilanModel.getTanggalBayar());
    //     }


    //     return CicilanRepo.save(Cicilan);
    // }

    // public CicilanEntity delete(CicilanModel CicilanModel) throws ClientException, NotFoundException {
        
    //     if (!CicilanRepo.existsById(CicilanModel.getNoTransaksi())) {
    //         throw new NotFoundException("Cannot find Cicilan with no transaksi : " + CicilanModel.getNoTransaksi());
    //     }

    //     // proses
    //     CicilanEntity Cicilan = new CicilanEntity();
    //     Cicilan = findById(CicilanModel.getNoTransaksi());
        
    //     return CicilanRepo.save(Cicilan);
    // }

    // public List<InfoCicilanEntity> findAllByCicilan(String noTransaksi) throws ClientException, NotFoundException{
    //     CicilanValidator.nullCheckNoTransaksi(noTransaksi);
    //     CicilanValidator.validateNotransaksi(noTransaksi);

    //     List<InfoCicilanEntity> Cicilan = infoCicilanRepo.SearchTrans(noTransaksi);
    //     CicilanValidator.nullCheckObject(Cicilan);
    //     return Cicilan;
    // }
}
