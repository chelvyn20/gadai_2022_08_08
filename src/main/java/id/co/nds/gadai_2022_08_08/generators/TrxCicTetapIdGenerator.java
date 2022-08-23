package id.co.nds.gadai_2022_08_08.generators;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class TrxCicTetapIdGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor ssci, Object o) throws HibernateException {
        Connection connection = ssci.connection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT COUNT(*) AS seq FROM \"TX_TRANSAKSI_CICILAN_TETAP\" WHERE date_part('year', tanggal_tx) = date_part('year', NOW())");

            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                int seq = rs.getInt("seq") + 1;

                DateFormat df = new SimpleDateFormat("yyMM");
                String yearMonth = df.format(Calendar.getInstance().getTime());

                String code = String.format(yearMonth + "01%05d", seq);
                System.out.println("Generated Stock Code: " + code);
                return code;
            }
            else {
                throw new HibernateException("Generator failed to generate transaction id");
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
