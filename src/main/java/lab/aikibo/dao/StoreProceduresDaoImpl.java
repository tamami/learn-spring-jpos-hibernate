package lab.aikibo.dao;

import lab.aikibo.model.Sppt;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import com.jolbox.bonecp.BoneCPDataSource;

import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository("storeProceduresDao")
public class StoreProceduresDaoImpl implements StoreProceduresDao  {

  @Autowired
  private BoneCPDataSource boneCPDs;

  private CallableStatement callable;
  private Sppt sppt;

  public Sppt getDataSppt(String nop, String thn) throws SQLException {
    callable = null;
    sppt = null;

    callable = boneCPDs.getConnection().prepareCall("call sppt_terhutang(?,?,?)");
    callable.registerOutParameter(1, OracleTypes.CURSOR);
    callable.setString(2, nop);
    callable.setString(3, thn);
    callable.executeUpdate();
    ResultSet rs = (ResultSet) callable.getObject(1);
    sppt = new Sppt();


    while(rs.next()) {
      sppt.setNop(nop);
      sppt.setThn(thn);
      sppt.setNama(rs.getString("NAMA"));
      sppt.setAlamatOp(rs.getString("ALAMAT_OP"));
      sppt.setPokok(rs.getBigDecimal("POKOK").toBigInteger());
      sppt.setDenda(rs.getBigDecimal("DENDA").toBigInteger());
    }
    return sppt;
  }

}
