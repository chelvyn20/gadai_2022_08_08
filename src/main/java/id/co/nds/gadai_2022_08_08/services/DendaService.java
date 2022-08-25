package id.co.nds.gadai_2022_08_08.services;

import java.io.Serializable;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import id.co.nds.gadai_2022_08_08.entities.DendaEntity;
import id.co.nds.gadai_2022_08_08.exceptions.ClientException;
import id.co.nds.gadai_2022_08_08.exceptions.NotFoundException;
// import id.co.nds.gadai_2022_08_08.globals.GlobalConstanst;
import id.co.nds.gadai_2022_08_08.models.DendaModel;
import id.co.nds.gadai_2022_08_08.repos.DendaRepo;

@Service
public class DendaService implements Serializable{

    @Autowired
    private DendaRepo dendaRepo;


    public DendaEntity add(DendaModel dendaModel) throws ClientException {

        Long count = dendaRepo.hitung(dendaModel.getNoTransaksi());
        if (count > 0) {
            throw new ClientException("Denda is already existed");
        }

        DendaEntity denda = new DendaEntity();
        denda.setNoTransaksi(dendaModel.getNoTransaksi());
        denda.setIdDenda(dendaModel.getIdDenda());
        denda.setCicilanKe(dendaModel.getCicilanKe());
        denda.setNoPembayaran(dendaModel.getNoPembayaran());
        denda.setBiayaDenda(dendaModel.getBiayaDenda());
        denda.setTglDenda(dendaModel.getTglDenda());
        denda.setTglPembayaranDenda(dendaModel.getTglPembayaranDenda());

        return dendaRepo.save(denda);
    }

    public List<DendaEntity> findAllByCriteria(DendaModel DendaModel) {
        List<DendaEntity> Denda = new ArrayList<>();
        // DendaSpec spec = new DendaSpec(DendaModel);
        dendaRepo.findAll().forEach(Denda::add);
        return Denda;
    }

    public DendaEntity findById(String noTransaksi) throws ClientException, NotFoundException {
        DendaEntity Denda = dendaRepo.findById(noTransaksi).orElse(null);
        // DendaValidator.nullCheckObject(Denda);
        return Denda;
    }

    public DendaEntity edit(DendaModel DendaModel) throws ClientException, NotFoundException {
        if (!dendaRepo.existsById(DendaModel.getNoTransaksi())) {
            throw new NotFoundException("Cannot find Denda with no transaksi = " + DendaModel.getNoTransaksi());
        }

        DendaEntity Denda = new DendaEntity();
        Denda = findById(DendaModel.getNoTransaksi());

        if (DendaModel.getNoTransaksi() != null) {
            // DendaValidator.validateName(DendaModel.getNamaDenda());
            Denda.setNoTransaksi(DendaModel.getNoTransaksi());
        }

        if (DendaModel.getNoPembayaran() != null) {
            // DendaValidator.validateKondisi(DendaModel.getKondisi());
            Denda.setNoPembayaran(DendaModel.getNoPembayaran());
        }

        if (DendaModel.getCicilanKe() != null) {
            Denda.setCicilanKe(DendaModel.getCicilanKe());
        }

        if (DendaModel.getBiayaDenda() != null) {
            Denda.setBiayaDenda(DendaModel.getBiayaDenda());
        }

        if (DendaModel.getTglDenda() != null) {
            Denda.setTglDenda(DendaModel.getTglDenda());
        }
        if (DendaModel.getTglPembayaranDenda() != null) {
            Denda.setTglPembayaranDenda(DendaModel.getTglPembayaranDenda());
        }

        return dendaRepo.save(Denda);
    }

    public DendaEntity delete(DendaModel DendaModel) throws ClientException, NotFoundException {
           if (!dendaRepo.existsById(DendaModel.getNoTransaksi())) {
            throw new NotFoundException("Cannot find Denda with no transaksi : " + DendaModel.getNoTransaksi());
        }

        // proses
        DendaEntity Denda = new DendaEntity();
        Denda = findById(DendaModel.getNoTransaksi());

        return dendaRepo.save(Denda);
    }


}
