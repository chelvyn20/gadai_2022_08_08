package id.co.nds.gadai_2022_08_08.services;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
// import java.math.BigDecimal;
// import java.math.RoundingMode;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import id.co.nds.gadai_2022_08_08.domains.CicTetapObject;
import id.co.nds.gadai_2022_08_08.domains.CustNewObject;
import id.co.nds.gadai_2022_08_08.domains.ProdNewObject;
import id.co.nds.gadai_2022_08_08.entities.BarangEntity;
import id.co.nds.gadai_2022_08_08.entities.CicTetapEntity;
import id.co.nds.gadai_2022_08_08.entities.CicilanEntity;
import id.co.nds.gadai_2022_08_08.entities.CustomerEntity;
import id.co.nds.gadai_2022_08_08.entities.ProductEntity;
import id.co.nds.gadai_2022_08_08.entities.UserEntity;
import id.co.nds.gadai_2022_08_08.exceptions.ClientException;
import id.co.nds.gadai_2022_08_08.exceptions.NotFoundException;
import id.co.nds.gadai_2022_08_08.globals.GlobalConstanst;
import id.co.nds.gadai_2022_08_08.models.CicTetapModel;
import id.co.nds.gadai_2022_08_08.models.CustomerModel;
import id.co.nds.gadai_2022_08_08.models.ProductModel;
import id.co.nds.gadai_2022_08_08.models.UserModel;
import id.co.nds.gadai_2022_08_08.repos.BarangRepo;
import id.co.nds.gadai_2022_08_08.repos.CicTetapRepo;
import id.co.nds.gadai_2022_08_08.repos.CicilanRepo;
// import id.co.nds.gadai_2022_08_08.repos.CustomerRepo;
// import id.co.nds.gadai_2022_08_08.repos.ProductRepo;
import java.util.Calendar;
import java.sql.Date;

import id.co.nds.gadai_2022_08_08.repos.specs.CicTetapSpec;
// import id.co.nds.gadai_2022_08_08.repos.specs.CustomerSpec;
import id.co.nds.gadai_2022_08_08.validators.CicTetapValidator;
import id.co.nds.gadai_2022_08_08.validators.ProductValidator;

@Service
public class CicTetapService implements Serializable {
    @Autowired
    private CicTetapRepo cicTetapRepo;

    @Autowired
    private BarangRepo barangRepo;

    @Autowired
    private CicilanRepo cicilanRepo;

    // @Autowired
    // private CustomerRepo customerRepo;

    @Autowired
    private CustomerService customerService;

    // @Autowired
    // private ProductRepo productRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    CicTetapValidator cicTetapValidator = new CicTetapValidator();
    ProductValidator productValidator = new ProductValidator();

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

    public CustNewObject[] doSearchPelanggan(CicTetapModel cictTetapModel) {
        List<CustNewObject> cust = new ArrayList<>();
        CustomerModel customerModel = new CustomerModel();
        customerModel.setCustId(cictTetapModel.getCustId());
        customerModel.setCustName(cictTetapModel.getCustName());
        customerModel.setCustKtp(cictTetapModel.getCustKtp());
        customerModel.setCustHp(cictTetapModel.getCustHp());
        customerModel.setCustStatus(GlobalConstanst.REC_STATUS_ACTIVE);
        customerModel.setActorId(cictTetapModel.getActorId());

        CustomerEntity[] customers = customerService.doSearchPelanggan(customerModel);
        for(int i = 0; i < customers.length; i++) {
            cust.add(new CustNewObject(customers[i].getCustId(), customers[i].getCustKtp(), customers[i].getCustHp(), customers[i].getCustName()));
        }
        CustNewObject[] customersArray = {};
        customersArray = cust.toArray(customersArray);
        return customersArray;
    }

    public ProdNewObject[] doGetListProduct(CicTetapModel cicTetapModel) throws ClientException, NotFoundException {
        cicTetapValidator.validateActorId(cicTetapModel.getActorId());

        ProductModel productModel = new ProductModel();
        productModel.setProductStatus(GlobalConstanst.REC_STATUS_ACTIVE);
        productModel.setActorId(cicTetapModel.getActorId());

        List<ProductEntity> products = productService.findAllByCriteria(productModel);
        for(int i = 0; i < products.size(); i++) {
            products.add(new ProdNewObject(products.get(i).getProductId(), products.get(i).getProductName(), products.get(i).getProductDesc()));
        }

        ProdNewObject[] productsArray = {};
        productsArray = products.toArray(productsArray);
        return productsArray;
    }

    public CicTetapObject[] doSearchTrans(CicTetapModel cicTetapModel) throws ClientException {
        cicTetapValidator.validateproductId(cicTetapModel.getProductId());
        cicTetapValidator.validateProductName(cicTetapModel.getProductName());
        cicTetapValidator.validateDateBegin(cicTetapModel.getTrxDateBegin());
        cicTetapValidator.validateDateEnd(cicTetapModel.getTrxDateEnd());
        cicTetapValidator.validateStatus(cicTetapModel.getStatusTrans());
        cicTetapValidator.validateKtp(cicTetapModel.getCustKtp());
        cicTetapValidator.validateCustId(cicTetapModel.getCustId());
        cicTetapValidator.validateCustName(cicTetapModel.getCustName());
        cicTetapValidator.validateNotransaksi(cicTetapModel.getNoTransaksi());
        cicTetapValidator.validateActorId(cicTetapModel.getActorId());

        List<CicTetapEntity> cicilan = new ArrayList<>();
        CicTetapSpec spec = new CicTetapSpec(cicTetapModel);
        cicTetapRepo.findAll(spec).forEach(cicilan::add);

        List<CicTetapObject> o = new ArrayList<>();
        String status;
        Boolean add = true;
        for (int i = 0; i < cicilan.size(); i++) {
            if (cicilanRepo.countbayar(cicilan.get(i).getNoTransaksi()) == cicilan.get(i).getProduct().getProductTenor()
                    / cicilan.get(i).getProduct().getBiayaJsPenyPer()) {
                status = "LUNAS";
            } else {
                if (setDate(new Date(cicilan.get(i).getTanggalJatuhTempo().getTime()))
                        .compareTo(setDate(new Date(System.currentTimeMillis()))) > 0) {
                    if (cicilanRepo.countTempo(cicilan.get(i).getNoTransaksi()) > 0) {
                        status = "JATUH TEMPO CICILAN";
                    } else
                        status = "AKTIF";
                } else if (setDate(new Date(cicilan.get(i).getTanggalJatuhTempo().getTime()))
                        .compareTo(setDate(new Date(System.currentTimeMillis()))) == 0) {
                    status = "JATUH TEMPO TRANSAKSI";
                } else {
                    status = "TERLAMBAT BAYAR";
                }
            }

            if (cicTetapModel.getStatusTrans() != null && !cicTetapModel.getStatusTrans().trim().equalsIgnoreCase("")) {
                if (!cicTetapModel.getStatusTrans().equalsIgnoreCase(status)) {
                    add = false;
                }
            }

            if (add) {
                o.add(new CicTetapObject(cicilan.get(i), status));
            }

            add = true;
        }
        CicTetapObject[] a = {};
        a = o.toArray(a);
        return a;
    }

    public CicTetapEntity doGetCicTetap(String noTransaksi) throws ClientException, NotFoundException {
        cicTetapValidator.nullCheckNoTransaksi(noTransaksi);
        cicTetapValidator.validateNotransaksi(noTransaksi);

        CicTetapEntity cicilan = cicTetapRepo.findById(noTransaksi).orElse(null);
        cicTetapValidator.nullCheckObject(cicilan);

        return cicilan;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { Exception.class })
    public CicTetapEntity doHitungTrans(CicTetapModel cicTetapModel) throws ClientException {
        cicTetapValidator.validateActorId(cicTetapModel.getActorId());
        cicTetapValidator.validateproductId(cicTetapModel.getProductId());

        ProductModel productModel = new ProductModel();
        productModel.setProductId(cicTetapModel.getProductId());
        productModel.setActorId(cicTetapModel.getActorId());

        ProductEntity product = (ProductEntity) productService.findAllByCriteria(productModel);
        CicTetapEntity transaksi = new CicTetapEntity();
        Calendar cal = Calendar.getInstance();
        cal.setTime(transaksi.getTanggalTx());
        cal.add(Calendar.MONTH, product.getProductTenor());

        transaksi.setTotalNilaiTak(cicTetapModel.getTotalNilaiTaksiran().doubleValue());
        transaksi.setNilaiPencairanPel(cicTetapModel.getNilaiPencairanPelanggan().doubleValue());
        transaksi.setDiskonAdmBuka(cicTetapModel.getDiskonAdmBuka().doubleValue());
        transaksi.setTxLtv(product.getProductLtv());
        transaksi.setMaxNilaiPinj(
                (cicTetapModel.getTotalNilaiTaksiran().doubleValue() * product.getProductLtv().doubleValue()));

        if (product.getBiayaAdmBukaType().equalsIgnoreCase("NOMINAL")) {
            transaksi.setBiayaAdmBuka(product.getBiayaAdmBukaVal());
        } 
        else{
            transaksi.setBiayaAdmBuka((cicTetapModel.getNilaiPencairanPelanggan().doubleValue()
            * product.getBiayaAdmBukaVal().doubleValue() / 100));
        }
            
        transaksi.setBiayaAdmBukaAk((transaksi.getBiayaAdmBuka().doubleValue()
                - (transaksi.getBiayaAdmBuka().doubleValue() * transaksi.getDiskonAdmBuka().doubleValue() / 100)));
        transaksi.setTotalNilaiPinj(
                (transaksi.getNilaiPencairanPel().doubleValue() + transaksi.getBiayaAdmBukaAk().doubleValue()));
        transaksi.setTanggalTx(new Timestamp(System.currentTimeMillis()));     
        transaksi.setTanggalJatuhTempo(new Timestamp(cal.getTime().getTime()));
        transaksi.setTxBiayaJasaPeny(product.getBiayaJsPenyRate());
        transaksi.setTxBiayaJasaPenyPer(
                ((product.getProductTenor().doubleValue() / 100 * transaksi.getTotalNilaiPinj().doubleValue())
                        / (product.getProductTenor() / product.getBiayaJsPenyPer())));
        transaksi.setTotalBiayaJasaPeny(((product.getProductTenor() / product.getBiayaJsPenyPer())
                * product.getBiayaJsPenyRate().doubleValue()));

        if (product.getBiayaAdmTutupType().equalsIgnoreCase("NOMINAL")){
            transaksi.setTxBiayaAdmTutup(product.getBiayaAdmTutupVal());
        }
        else{
            transaksi.setTxBiayaAdmTutup((cicTetapModel.getNilaiPencairanPelanggan().doubleValue()
                    * product.getBiayaAdmTutupVal().doubleValue() / 100));
        }

        transaksi.setTotalPengem((transaksi.getTotalNilaiPinj().doubleValue()
                + transaksi.getTotalBiayaJasaPeny().doubleValue() + transaksi.getTxBiayaAdmTutup().doubleValue()));
        transaksi.setProduct(product);

        return transaksi;
    }

    public CicTetapEntity doSaveTrans(CicTetapModel cicTetapModel) throws ClientException, NotFoundException {
        // cicTetapValidator.notnullTransaksi(cicTetapModel.getNoTransaksi());
        // cicTetapValidator.validateNotransaksi(cicTetapModel.getNoTransaksi());
        cicTetapValidator.validateproductId(cicTetapModel.getProductId());
        cicTetapValidator.validateCustId(cicTetapModel.getCustId());
        cicTetapValidator.validateActorId(cicTetapModel.getActorId());

        Long count = cicTetapRepo.countByTransaksi(cicTetapModel.getNoTransaksi());
        if (count > 0) {
            throw new ClientException("transaksi is already existed");
        }

        CustomerModel customerModel = new CustomerModel();
        CustomerEntity customer = customerService.doGetDetailPelanggan(customerModel);
        CicTetapEntity cicilan = new CicTetapEntity();
        UserModel userModel = new UserModel();
        UserEntity user = userService.findById(userModel.getUserId());
        String status = "AKTIF";
        
        if(cicTetapModel.getNilaiPencairanPelanggan().doubleValue() > user.getMaxLimit().doubleValue()
                && cicTetapModel.getNilaiPencairanPelanggan().doubleValue() > customer.getCustLimitTxn().doubleValue()) {
            throw new ClientException("Nilai Pencairan Pelanggan is not valid");
        }

        customerModel.setCustId(cicTetapModel.getCustId());
        customerModel.setActorId(cicTetapModel.getActorId());
        userModel.setActorId(cicTetapModel.getActorId());
        cicilan = doHitungTrans(cicTetapModel);
        cicilan.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        cicilan.setCustomer(customer);

        CicTetapEntity x = cicTetapRepo.save(cicilan);
        Calendar date = Calendar.getInstance();
        date.setTime(new Date(x.getTanggalTx().getTime()));

        for(int i = 0; i < cicTetapModel.getDaftarBarang().length; i++) {
            BarangEntity Barang = new BarangEntity();
            Barang.setNoTransaksi(x.getNoTransaksi());
            Barang.setNoUrut(cicTetapModel.getDaftarBarang()[i].getNoUrut());
            Barang.setNamaBarang(cicTetapModel.getDaftarBarang()[i].getNamaBarang());
            Barang.setKondisi(cicTetapModel.getDaftarBarang()[i].getKondisi());
            Barang.setJlh(cicTetapModel.getDaftarBarang()[i].getJumlah());
            Barang.setHargaPerSatuan(cicTetapModel.getDaftarBarang()[i].getHargaPerSatuan().doubleValue());
            barangRepo.save(Barang);
        }

        for (int i = 0; i < x.getProduct().getProductTenor() / x.getProduct().getBiayaJsPenyPer(); i++) {
            CicilanEntity cic = new CicilanEntity();
            cic.setNoTransaksi(x.getNoTransaksi());
            cic.setCicilanKe(i + 1);
            cic.setTxPokok((x.getTotalNilaiPinj().doubleValue()
                    / (x.getProduct().getProductTenor() / x.getProduct().getBiayaJsPenyPer())));
            cic.setTxBunga((cic.getTxPokok().doubleValue() * x.getProduct().getBiayaJsPenyPer().doubleValue() / 100));
            cic.setTxStatus(status);
            cic.setTanggalAktif(new Date(x.getTanggalTx().getTime()));

            date.add(Calendar.MONTH, x.getProduct().getBiayaJsPenyPer());
            date.add(Calendar.DAY_OF_MONTH, -1);
            cic.setTanggalJatuhTempo(new Date(date.getTime().getTime()));

            date.add(Calendar.DAY_OF_MONTH, 1);

            cic.setCreatedDate(new Timestamp(System.currentTimeMillis()));

            cicilanRepo.save(cic);

            status = "BELUM AKTIF";
        }

        return x;
    }

}
