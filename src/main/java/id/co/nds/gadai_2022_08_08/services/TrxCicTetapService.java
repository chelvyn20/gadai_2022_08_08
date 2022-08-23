package id.co.nds.gadai_2022_08_08.services;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import id.co.nds.gadai_2022_08_08.entities.CustomerEntity;
import id.co.nds.gadai_2022_08_08.entities.ProductEntity;
import id.co.nds.gadai_2022_08_08.entities.TrxBarangEntity;
import id.co.nds.gadai_2022_08_08.entities.TrxCicTetapEntity;
import id.co.nds.gadai_2022_08_08.entities.TrxCicilanEntity;
import id.co.nds.gadai_2022_08_08.entities.UserEntity;
import id.co.nds.gadai_2022_08_08.exceptions.ClientException;
import id.co.nds.gadai_2022_08_08.globals.GlobalConstant;
import id.co.nds.gadai_2022_08_08.models.CustomerModel;
import id.co.nds.gadai_2022_08_08.models.ProductModel;
import id.co.nds.gadai_2022_08_08.models.TrxCicTetapModel;
import id.co.nds.gadai_2022_08_08.models.UserModel;
import id.co.nds.gadai_2022_08_08.objects.CustomerTransaction;
import id.co.nds.gadai_2022_08_08.objects.ProductTransaction;
import id.co.nds.gadai_2022_08_08.objects.SearchTransaksiObject;
import id.co.nds.gadai_2022_08_08.repos.TrxBarangRepo;
import id.co.nds.gadai_2022_08_08.repos.TrxCicTetapRepo;
import id.co.nds.gadai_2022_08_08.repos.TrxCicilanRepo;
import id.co.nds.gadai_2022_08_08.repos.specs.TrxCicTetapSpec;

@Service
public class TrxCicTetapService implements Serializable {
    @Autowired
    TrxCicTetapRepo trxCicTetapRepo;

    @Autowired
    TrxBarangRepo trxBarangRepo;

    @Autowired
    TrxCicilanRepo trxCicilanRepo;

    @Autowired
    CustomerService customerService;

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    public SearchTransaksiObject[] doSearchTransCicTetap(TrxCicTetapModel trxCicTetapModel) throws ParseException, ClientException {
        if(new SimpleDateFormat("yyyy-MM-dd").parse(trxCicTetapModel.getTrxDateBegin()).compareTo(new SimpleDateFormat("yyyy-MM-dd").parse(trxCicTetapModel.getTrxDateEnd())) > 0) {
            throw new ClientException("Date Start can't be higher than Date End");
        }
        
        ArrayList<TrxCicTetapEntity> trx = new ArrayList<>();
        TrxCicTetapSpec spec = new TrxCicTetapSpec(trxCicTetapModel);
        trxCicTetapRepo.findAll(spec).forEach(trx::add);

        ArrayList<SearchTransaksiObject> trxObject = new ArrayList<>();
        
        String status;
        Boolean add = true;
        for(int i = 0; i < trx.size(); i++) {
            if(trxCicilanRepo.countDibayar(trx.get(i).getNoTransaksi()) == trx.get(i).getProduct().getProductJangkaWaktu() / trx.get(i).getProduct().getProductBiayaJasaPenyPeriode()) {
                status = "LUNAS";
            }
            else {
                if(setZeroDate(new Date(trx.get(i).getTanggalJatuhTempo().getTime())).compareTo(setZeroDate(new Date(System.currentTimeMillis()))) > 0) {
                    if(trxCicilanRepo.countJatuhTempo(trx.get(i).getNoTransaksi()) > 0) {
                        status = "JATUH TEMPO CICILAN";
                    }
                    else status = "AKTIF";
                }
                else if(setZeroDate(new Date(trx.get(i).getTanggalJatuhTempo().getTime())).compareTo(setZeroDate(new Date(System.currentTimeMillis()))) == 0) {
                    status = "JATUH TEMPO TRANSAKSI";
                }
                else {
                    status = "TERLAMBAT BAYAR";
                }
            }

            if(trxCicTetapModel.getStatusTrans() != null && !trxCicTetapModel.getStatusTrans().trim().equalsIgnoreCase("")) {
                if(!trxCicTetapModel.getStatusTrans().equalsIgnoreCase(status)) {
                    add = false;
                }
            }

            if(add) {
                trxObject.add(new SearchTransaksiObject(trx.get(i), status));
            }

            add = true;
        }
        
        SearchTransaksiObject[] trxArray = {};
        trxArray = trxObject.toArray(trxArray);
        return trxArray;
    }

    public TrxCicTetapEntity doGetDetailCicTetap(TrxCicTetapModel trxCicTetapModel) throws NoSuchElementException {
        TrxCicTetapEntity trx = trxCicTetapRepo.findById(trxCicTetapModel.getNoTransaksi()).orElse(null);
        if(trx == null) {
            throw new NoSuchElementException("Product not found");
        }

        return trx;
    }

    public CustomerTransaction[] doSearchPelanggan(TrxCicTetapModel trxCicTetapModel) {
        ArrayList<CustomerTransaction> customers = new ArrayList<>();

        CustomerModel customerModel = new CustomerModel();
        customerModel.setCustId(trxCicTetapModel.getCustId());
        customerModel.setCustName(trxCicTetapModel.getCustName());
        customerModel.setCustKtp(trxCicTetapModel.getCustKtp());
        customerModel.setCustHp(trxCicTetapModel.getCustHp());
        customerModel.setCustStatus(GlobalConstant.REC_STATUS_ACTIVE);
        customerModel.setActorId(trxCicTetapModel.getActorId());

        CustomerEntity[] customersData = customerService.doSearchPelanggan(customerModel);

        for(int i = 0; i < customersData.length; i++) {
            customers.add(new CustomerTransaction(customersData[i].getCustId(), customersData[i].getCustKtp(), customersData[i].getCustHp(), customersData[i].getCustName()));
        }
        
        CustomerTransaction[] customersArray = {};
        customersArray = customers.toArray(customersArray);
        return customersArray;
    }

    public ProductTransaction[] doGetListProduk(TrxCicTetapModel trxCicTetapModel) throws ClientException {
        ArrayList<ProductTransaction> products = new ArrayList<>();

        ProductModel productModel = new ProductModel();
        productModel.setProductStatus(GlobalConstant.REC_STATUS_ACTIVE);
        productModel.setActorId(trxCicTetapModel.getActorId());

        ProductEntity[] productsData = productService.doSearchProduk(productModel);

        for(int i = 0; i < productsData.length; i++) {
            products.add(new ProductTransaction(productsData[i].getProductId(), productsData[i].getProductName(), productsData[i].getProductDesc()));
        }

        ProductTransaction[] productsArray = {};
        productsArray = products.toArray(productsArray);
        return productsArray;
    }

    public TrxCicTetapEntity doHitungTrxCicTetap(TrxCicTetapModel trxCicTetapModel) {
        ProductModel productModel = new ProductModel();
        productModel.setProductId(trxCicTetapModel.getProductId());
        productModel.setActorId(trxCicTetapModel.getActorId());

        ProductEntity product = productService.doGetDetailProduk(productModel);

        TrxCicTetapEntity trx = new TrxCicTetapEntity();
        trx.setTotalNilaiTak(trxCicTetapModel.getTotalNilaiTaksiran());
        trx.setNilaiPencairanPel(trxCicTetapModel.getNilaiPencairanPelanggan());
        trx.setDiskonAdmBuka(trxCicTetapModel.getDiskonAdmBuka());
        trx.setTxLtv(product.getProductLtv());
        trx.setMaxNilaiPinj(new BigDecimal(trxCicTetapModel.getTotalNilaiTaksiran().doubleValue() * product.getProductLtv().doubleValue()).setScale(2, RoundingMode.HALF_UP));
        
        if(product.getProductBiayaAdminBukaTipe().equalsIgnoreCase("NOMINAL"))
            trx.setBiayaAdmBuka(product.getProductBiayaAdminBuka());
        else trx.setBiayaAdmBuka(new BigDecimal(trxCicTetapModel.getNilaiPencairanPelanggan().doubleValue() * product.getProductBiayaAdminBuka().doubleValue() / 100).setScale(2, RoundingMode.HALF_UP));

        trx.setBiayaAdmBukaAk(new BigDecimal(trx.getBiayaAdmBuka().doubleValue() - (trx.getBiayaAdmBuka().doubleValue() * trx.getDiskonAdmBuka().doubleValue() / 100)).setScale(2, RoundingMode.HALF_UP));
        trx.setTotalNilaiPinj(new BigDecimal(trx.getNilaiPencairanPel().doubleValue() + trx.getBiayaAdmBukaAk().doubleValue()).setScale(2, RoundingMode.HALF_UP));
        trx.setTanggalTx(new Timestamp(System.currentTimeMillis()));

        Calendar cal = Calendar.getInstance();
        cal.setTime(trx.getTanggalTx());
        cal.add(Calendar.MONTH, product.getProductJangkaWaktu());

        trx.setTanggalJatuhTempo(new Timestamp(cal.getTime().getTime()));
        trx.setTxBiayaJasaPeny(product.getProductBiayaJasaPeny());
        trx.setTxBiayaJasaPenyPer(new BigDecimal((product.getProductBiayaJasaPeny().doubleValue() / 100 * trx.getTotalNilaiPinj().doubleValue()) / (product.getProductJangkaWaktu() / product.getProductBiayaJasaPenyPeriode())).setScale(2, RoundingMode.HALF_UP));
        trx.setTotalBiayaJasaPeny(new BigDecimal((product.getProductJangkaWaktu() / product.getProductBiayaJasaPenyPeriode()) * product.getProductBiayaJasaPeny().doubleValue()).setScale(2, RoundingMode.HALF_UP));

        if(product.getProductBiayaAdminTutupTipe().equalsIgnoreCase("NOMINAL"))
            trx.setTxBiayaAdmTutup(product.getProductBiayaAdminTutup());
        else trx.setTxBiayaAdmTutup(new BigDecimal(trxCicTetapModel.getNilaiPencairanPelanggan().doubleValue() * product.getProductBiayaAdminTutup().doubleValue() / 100).setScale(2, RoundingMode.HALF_UP));

        trx.setTotalPengem(new BigDecimal(trx.getTotalNilaiPinj().doubleValue() + trx.getTotalBiayaJasaPeny().doubleValue() + trx.getTxBiayaAdmTutup().doubleValue()).setScale(2, RoundingMode.HALF_UP));
        trx.setProduct(product);

        return trx;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    public TrxCicTetapEntity doSaveTrxCicTetap(TrxCicTetapModel trxCicTetapModel) throws ClientException {
        if(trxCicTetapModel.getDaftarBarangGadai() == null || trxCicTetapModel.getDaftarBarangGadai().length == 0) {
            throw new ClientException("Barang Gadai is required");
        }

        CustomerModel customerModel = new CustomerModel();
        customerModel.setCustId(trxCicTetapModel.getCustId());
        customerModel.setActorId(trxCicTetapModel.getActorId());

        CustomerEntity customer = customerService.doGetDetailPelanggan(customerModel);

        if(trxCicTetapModel.getNilaiPencairanPelanggan().doubleValue() > customer.getCustLimitTxn().doubleValue()) {
            throw new ClientException("Nilai Pencairan Pelanggan can't be higher than Customer's maximum transaction limit");
        }

        UserModel userModel = new UserModel();
        userModel.setUserId(trxCicTetapModel.getActorId());
        userModel.setActorId(trxCicTetapModel.getActorId());

        UserEntity user = userService.findById(userModel);

        if(trxCicTetapModel.getNilaiPencairanPelanggan().doubleValue() > user.getMaxLimit()) {
            throw new ClientException("Nilai Pencairan Pelanggan can't be higher than User's maximum transaction limit");
        }

        TrxCicTetapEntity trx = new TrxCicTetapEntity();
        trx = doHitungTrxCicTetap(trxCicTetapModel);
        trx.setCustomer(customer);
        
        trx.setCreatedDate(new Timestamp(System.currentTimeMillis()));

        TrxCicTetapEntity result = trxCicTetapRepo.save(trx);

        for(int i = 0; i < trxCicTetapModel.getDaftarBarangGadai().length; i++) {
            TrxBarangEntity trxBarang = new TrxBarangEntity();
            trxBarang.setNoTransaksi(result.getNoTransaksi());
            trxBarang.setNoUrut(trxCicTetapModel.getDaftarBarangGadai()[i].getNoUrut());
            trxBarang.setNamaBarang(trxCicTetapModel.getDaftarBarangGadai()[i].getNamaBarang());
            trxBarang.setKondisi(trxCicTetapModel.getDaftarBarangGadai()[i].getKondisi());
            trxBarang.setJlh(trxCicTetapModel.getDaftarBarangGadai()[i].getJlh());
            trxBarang.setHargaPerSatuan(trxCicTetapModel.getDaftarBarangGadai()[i].getHargaPerSatuan());

            trxBarangRepo.save(trxBarang);
        }
        
        String status = "AKTIF";
        Date aktif = new Date(result.getTanggalTx().getTime());
        Date jatuhTempo;

        Calendar cal = Calendar.getInstance();
        cal.setTime(aktif);
        for(int i = 0; i < result.getProduct().getProductJangkaWaktu() / result.getProduct().getProductBiayaJasaPenyPeriode(); i++) {
            TrxCicilanEntity trxCicilan = new TrxCicilanEntity();
            trxCicilan.setNoTransaksi(result.getNoTransaksi());
            trxCicilan.setCicilanKe(i + 1);
            trxCicilan.setTxPokok(new BigDecimal(result.getTotalNilaiPinj().doubleValue() / (result.getProduct().getProductJangkaWaktu() / result.getProduct().getProductBiayaJasaPenyPeriode())).setScale(2, RoundingMode.HALF_UP));
            trxCicilan.setTxBunga(new BigDecimal(trxCicilan.getTxPokok().doubleValue() * result.getProduct().getProductBiayaJasaPeny().doubleValue() / 100).setScale(2, RoundingMode.HALF_UP));
            trxCicilan.setTxStatus(status);

            trxCicilan.setTanggalAktif(aktif);

            cal.add(Calendar.MONTH, result.getProduct().getProductBiayaJasaPenyPeriode());
            cal.add(Calendar.DAY_OF_MONTH, -1);
            jatuhTempo = new Date(cal.getTime().getTime());

            trxCicilan.setTanggalJatuhTempo(jatuhTempo);

            cal.add(Calendar.DAY_OF_MONTH, 1);
            aktif = new Date(cal.getTime().getTime());

            trxCicilan.setCreatedDate(new Timestamp(System.currentTimeMillis()));

            trxCicilanRepo.save(trxCicilan);

            status = "BELUM AKTIF";
        }

        return result;
    }

    private static Date setZeroDate(Date date) {
        Calendar calendar = Calendar.getInstance();
    
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    
        Date res = new Date(calendar.getTime().getTime());
    
        return res;
    }
}
