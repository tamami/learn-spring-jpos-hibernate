package lab.aikibo.services;

import lab.aikibo.model.Sppt;

import java.sql.SQLException;

public interface SpptServices {
  public Sppt getSpptByNopThn(String nop, String thn) throws SQLException;
}
