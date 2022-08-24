package id.co.nds.gadai_2022_08_08.generators;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class TrxDendaIdGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor ssci, Object o) throws HibernateException {
        Connection connection = ssci.connection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT COUNT(*) AS seq FROM \"TX_DENDA_KETERLAMBATAN\"");

            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                int seq = rs.getInt("seq") + 1;

                String code = String.format("D%03d", seq);
                System.out.println("Generated Stock Code: " + code);
                return code;
            }
            else {
                throw new HibernateException("Generator failed to generate denda id");
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    
}
