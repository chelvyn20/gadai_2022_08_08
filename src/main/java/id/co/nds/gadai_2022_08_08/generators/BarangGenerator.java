package id.co.nds.gadai_2022_08_08.generators;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class BarangGenerator implements IdentifierGenerator {
    @Override
    public Serializable generate(SharedSessionContractImplementor ssci, Object o) throws HibernateException {
        // TODO Auto-generated method stub
        // Connection connection = ssci.connection();
        // try {
        //     PreparedStatement ps = connection
        //             .prepareStatement("SELECT COUNT(*) AS seq FROM \"TX_TRANSAKSI_BARANG_\" ");
        //     ResultSet rs = ps.executeQuery();

        //     if (rs.next()) {
                
        //         int seq = rs.getInt("seq") + 1;
        //         String code = String.format("TX%05d", seq);
        //         System.out.println("Generated Stock code : " + code);
        //         return code;
        //     } else {
        //         throw new HibernateException("Generator is failed to generate id");
        //     }
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }
        return null;
    }

}