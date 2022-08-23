package id.co.nds.gadai_2022_08_08.services;

import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import id.co.nds.gadai_2022_08_08.entities.BarangEntity;
import id.co.nds.gadai_2022_08_08.entities.InfoBarangEntity;
import id.co.nds.gadai_2022_08_08.exceptions.ClientException;
import id.co.nds.gadai_2022_08_08.exceptions.NotFoundException;
import id.co.nds.gadai_2022_08_08.globals.GlobalConstanst;
import id.co.nds.gadai_2022_08_08.models.BarangModel;
import id.co.nds.gadai_2022_08_08.repos.BarangRepo;
import id.co.nds.gadai_2022_08_08.repos.InfoBarangRepo;
import id.co.nds.gadai_2022_08_08.repos.specs.BarangSpec;
import id.co.nds.gadai_2022_08_08.validators.BarangValidator;

@Service
public class BarangService implements Serializable {
    @Autowired
    private BarangRepo barangRepo;

    @Autowired
    private InfoBarangRepo infoBarangRepo;

    BarangValidator barangValidator = new BarangValidator();

    public BarangEntity add(BarangModel BarangModel) throws ClientException {
        barangValidator.nullCheckNoTransaksi(BarangModel.getNoTransaksi());
        barangValidator.validateNotransaksi(BarangModel.getNoTransaksi());

        Long count = barangRepo.countByTransaksi(BarangModel.getNoTransaksi());
        if (count > 0) {
            throw new ClientException("Barang is already existed");
        }

        BarangEntity Barang = new BarangEntity();
        Barang.setNamaBarang(BarangModel.getNamaBarang());
        Barang.setKondisi(BarangModel.getKondisi());
        Barang.setNoUrut(BarangModel.getNoUrut());
        Barang.setJlh(BarangModel.getJumlah());
        Barang.setHargaPerSatuan(BarangModel.getHargaPerSatuan().doubleValue());

        return barangRepo.save(Barang);
    }

    public List<BarangEntity> findAllByCriteria(BarangModel BarangModel) {
        List<BarangEntity> Barang = new ArrayList<>();
        BarangSpec spec = new BarangSpec(BarangModel);
        barangRepo.findAll(spec).forEach(Barang::add);
        return Barang;
    }

    public BarangEntity findById(String noTransaksi) throws ClientException, NotFoundException {
        barangValidator.nullCheckNoTransaksi(noTransaksi);
        barangValidator.validateNotransaksi(noTransaksi);

        BarangEntity Barang = barangRepo.findById(noTransaksi).orElse(null);
        barangValidator.nullCheckObject(Barang);
        return Barang;
    }

    public BarangEntity edit(BarangModel BarangModel) throws ClientException, NotFoundException {
        barangValidator.nullCheckNoTransaksi(BarangModel.getNoTransaksi());
        barangValidator.validateNotransaksi(BarangModel.getNoTransaksi());
        if (!barangRepo.existsById(BarangModel.getNoTransaksi())) {
            throw new NotFoundException("Cannot find Barang with no transaksi = " + BarangModel.getNoTransaksi());
        }

        BarangEntity Barang = new BarangEntity();
        Barang = findById(BarangModel.getNoTransaksi());

        if (BarangModel.getNamaBarang() != null) {
            barangValidator.validateName(BarangModel.getNamaBarang());
            Barang.setNamaBarang(BarangModel.getNamaBarang());
        }

        if (BarangModel.getKondisi() != null) {
            barangValidator.validateKondisi(BarangModel.getKondisi());
            Barang.setKondisi(BarangModel.getKondisi());
        }

        if (BarangModel.getJumlah() != null) {
            Barang.setJlh(BarangModel.getJumlah());
        }



        return barangRepo.save(Barang);
    }

    public BarangEntity delete(BarangModel BarangModel) throws ClientException, NotFoundException {
        barangValidator.nullCheckNoTransaksi(BarangModel.getNoTransaksi());
        barangValidator.validateNotransaksi(BarangModel.getNoTransaksi());
        if (!barangRepo.existsById(BarangModel.getNoTransaksi())) {
            throw new NotFoundException("Cannot find Barang with no transaksi : " + BarangModel.getNoTransaksi());
        }

        // proses
        BarangEntity Barang = new BarangEntity();
        Barang = findById(BarangModel.getNoTransaksi());
        
        return barangRepo.save(Barang);
    }

    public List<InfoBarangEntity> findAllBybarang(String noTransaksi) throws ClientException, NotFoundException{
        barangValidator.nullCheckNoTransaksi(noTransaksi);
        barangValidator.validateNotransaksi(noTransaksi);

        List<InfoBarangEntity> barang = infoBarangRepo.SearchTrans(noTransaksi);
        barangValidator.nullCheckObject(barang);
        return barang;
    }
}
