package id.co.nds.gadai_2022_08_08.repos;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import id.co.nds.gadai_2022_08_08.entities.InfoBarangEntity;

@Repository
@Transactional
public interface InfoBarangRepo extends JpaRepository<InfoBarangEntity, String> {
    @Query(value = "SELECT  from \"TX_TRANSAKSI_BARANG\" as p" +
            " SET total = jumlah*harga_per_satuan " +
            " WHERE p.no_transaksi = ?1", nativeQuery = true)
    List<InfoBarangEntity> SearchTrans(String no_transaksi);
}
