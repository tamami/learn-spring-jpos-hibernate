package lab.aikibo.dao;

import lab.aikibo.model.Sppt;

import java.sql.SQLException;

public interface StoreProceduresDao {
  public Sppt getDataSppt(String nop, String thn) throws SQLException;
}
