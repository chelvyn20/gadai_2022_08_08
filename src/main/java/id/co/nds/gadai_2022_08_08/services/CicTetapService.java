package id.co.nds.gadai_2022_08_08.services;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import id.co.nds.gadai_2022_08_08.entities.BarangEntity;
import id.co.nds.gadai_2022_08_08.entities.CicTetapEntity;
import id.co.nds.gadai_2022_08_08.entities.CicilanEntity;
import id.co.nds.gadai_2022_08_08.entities.CustomerEntity;
import id.co.nds.gadai_2022_08_08.entities.ProductEntity;
import id.co.nds.gadai_2022_08_08.exceptions.ClientException;
import id.co.nds.gadai_2022_08_08.exceptions.NotFoundException;
import id.co.nds.gadai_2022_08_08.globals.GlobalConstanst;
import id.co.nds.gadai_2022_08_08.models.CicTetapModel;
import id.co.nds.gadai_2022_08_08.models.CustomerModel;
import id.co.nds.gadai_2022_08_08.models.ProductModel;
import id.co.nds.gadai_2022_08_08.repos.BarangRepo;
import id.co.nds.gadai_2022_08_08.repos.CicTetapRepo;
import id.co.nds.gadai_2022_08_08.repos.CicilanRepo;
import id.co.nds.gadai_2022_08_08.repos.CustomerRepo;
import id.co.nds.gadai_2022_08_08.repos.InfoCicTetapRepo;
import id.co.nds.gadai_2022_08_08.repos.ProductRepo;
// import id.co.nds.gadai_2022_08_08.repos.specs.CicTetapModel;
import java.util.Calendar;
import java.sql.Date;

import id.co.nds.gadai_2022_08_08.repos.specs.CicTetapSpec;
import id.co.nds.gadai_2022_08_08.repos.specs.CustomerSpec;
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

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductRepo productRepo;

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

    public CustomerEntity[] doSearchPelanggan(CicTetapModel cictTetapModel) {
        ArrayList<CustomerEntity> customers = new ArrayList<>();
        CustomerModel customerModel = new CustomerModel();
        customerModel.setCustId(cictTetapModel.getCustId());
        customerModel.setCustName(cictTetapModel.getCustName());
        customerModel.setCustKtp(cictTetapModel.getCustKtp());
        customerModel.setCustHp(cictTetapModel.getCustHp());
        customerModel.setCustStatus(GlobalConstanst.REC_STATUS_ACTIVE);
        customerModel.setActorId(cictTetapModel.getActorId());
        CustomerEntity[] customersArray = {};
        customersArray = customers.toArray(customersArray);
        return customersArray;
    }

    public ProductEntity[] doGetListProduct(CicTetapModel cicTetapModel) throws ClientException, NotFoundException {
        ArrayList<ProductEntity> products = new ArrayList<>();
        ProductModel productModel = new ProductModel();
        productModel.setProductStatus(GlobalConstanst.REC_STATUS_ACTIVE);
        productModel.setActorId(cicTetapModel.getActorId());
        ProductEntity[] productsArray = {};
        productsArray = products.toArray(productsArray);
        return productsArray;
    }

    public Object[] doSearchTrans(CicTetapModel cicTetapModel) {
        List<CicTetapEntity> cicilan = new ArrayList<>();
        CicTetapSpec spec = new CicTetapSpec(cicTetapModel);
        cicTetapRepo.findAll(spec).forEach(cicilan::add);

        ArrayList<Object> o = new ArrayList<>();
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
                o.add(new Object());
            }

            add = true;
        }
        Object[] a = {};
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

    // @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {
    // Exception.class })
    public CicTetapEntity doHitungTrans(CicTetapModel cicTetapModel) throws ClientException {
        ProductModel productModel = new ProductModel();
        productModel.setProductId(cicTetapModel.getProductId());
        productModel.setActorId(cicTetapModel.getActorId());

        ProductEntity product = (ProductEntity) productService.findAllByCriteria(productModel);

        CicTetapEntity transaksi = new CicTetapEntity();
        transaksi.setTotalNilaiTak(cicTetapModel.getTotalNilaiTaksiran().doubleValue());
        transaksi.setNilaiPencairanPel(cicTetapModel.getNilaiPencairanPelanggan().doubleValue());
        transaksi.setDiskonAdmBuka(cicTetapModel.getDiskonAdmBuka().doubleValue());
        transaksi.setTxLtv(product.getProductLtv());
        transaksi.setMaxNilaiPinj(
                (cicTetapModel.getTotalNilaiTaksiran().doubleValue() * product.getProductLtv().doubleValue()));

        if (product.getBiayaAdmBukaType().equalsIgnoreCase("NOMINAL"))
            transaksi.setBiayaAdmBuka(product.getBiayaAdmBukaVal());
        else
            transaksi.setBiayaAdmBuka((cicTetapModel.getNilaiPencairanPelanggan().doubleValue()
                    * product.getBiayaAdmBukaVal().doubleValue() / 100));

        transaksi.setBiayaAdmBukaAk((transaksi.getBiayaAdmBuka().doubleValue()
                - (transaksi.getBiayaAdmBuka().doubleValue() * transaksi.getDiskonAdmBuka().doubleValue() / 100)));
        transaksi.setTotalNilaiPinj(
                (transaksi.getNilaiPencairanPel().doubleValue() + transaksi.getBiayaAdmBukaAk().doubleValue()));
        transaksi.setTanggalTx(new Timestamp(System.currentTimeMillis()));

        Calendar cal = Calendar.getInstance();
        cal.setTime(transaksi.getTanggalTx());
        cal.add(Calendar.MONTH, product.getProductTenor());

        transaksi.setTanggalJatuhTempo(new Timestamp(cal.getTime().getTime()));
        transaksi.setTxBiayaJasaPeny(product.getBiayaJsPenyRate());
        transaksi.setTxBiayaJasaPenyPer(
                ((product.getProductTenor().doubleValue() / 100 * transaksi.getTotalNilaiPinj().doubleValue())
                        / (product.getProductTenor() / product.getBiayaJsPenyPer())));
        transaksi.setTotalBiayaJasaPeny(((product.getProductTenor() / product.getBiayaJsPenyPer())
                * product.getBiayaJsPenyRate().doubleValue()));

        if (product.getBiayaAdmTutupType().equalsIgnoreCase("NOMINAL"))
            transaksi.setTxBiayaAdmTutup(product.getBiayaAdmTutupVal());
        else
            transaksi.setTxBiayaAdmTutup((cicTetapModel.getNilaiPencairanPelanggan().doubleValue()
                    * product.getBiayaAdmBukaVal().doubleValue() / 100));

        transaksi.setTotalPengem((transaksi.getTotalNilaiPinj().doubleValue()
                + transaksi.getTotalBiayaJasaPeny().doubleValue() + transaksi.getTxBiayaAdmTutup().doubleValue()));
        transaksi.setProduct(product);

        return transaksi;
    }

    public CicTetapEntity doSaveTrans(CicTetapModel cicTetapModel) throws ClientException {
        cicTetapValidator.notnullTransaksi(cicTetapModel.getNoTransaksi());
        Long count = cicTetapRepo.countByTransaksi(cicTetapModel.getNoTransaksi());
        if (count > 0) {
            throw new ClientException("transaksi is already existed");
        }

        CustomerModel customerModel = new CustomerModel();
        CustomerEntity customer = customerService.doGetDetailPelanggan(customerModel);
        CicTetapEntity cicilan = new CicTetapEntity();
        CicTetapEntity x = cicTetapRepo.save(cicilan);
        String status = "AKTIF";

        customerModel.setCustId(cicTetapModel.getCustId());
        customerModel.setActorId(cicTetapModel.getActorId());
        cicilan = doHitungTrans(cicTetapModel);
        cicilan.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        cicilan.setCustomer(customer);

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(x.getTanggalTx().getTime()));
        for (int i = 0; i < x.getProduct().getProductTenor() / x.getProduct().getBiayaJsPenyPer(); i++) {
            CicilanEntity cic = new CicilanEntity();
            cic.setNoTransaksi(x.getNoTransaksi());
            cic.setCicilanKe(i + 1);
            cic.setTxPokok(new BigDecimal(x.getTotalNilaiPinj().doubleValue()
                    / (x.getProduct().getProductTenor() / x.getProduct().getBiayaJsPenyPer()))
                    .setScale(2, RoundingMode.HALF_UP));
            cic.setTxBunga(new BigDecimal(
                    cic.getTxPokok().doubleValue() * x.getProduct().getBiayaJsPenyPer().doubleValue() / 100)
                    .setScale(2, RoundingMode.HALF_UP));
            cic.setTxStatus(status);
            cic.setTanggalAktif(new Date(x.getTanggalTx().getTime()));

            cal.add(Calendar.MONTH, x.getProduct().getBiayaJsPenyPer());
            cal.add(Calendar.DAY_OF_MONTH, -1);
            cic.setTanggalJatuhTempo(new Date(cal.getTime().getTime()));

            cal.add(Calendar.DAY_OF_MONTH, 1);

            cic.setCreatedDate(new Timestamp(System.currentTimeMillis()));

            cicilanRepo.save(cic);

            status = "BELUM AKTIF";
        }

        return x;
    }

}
