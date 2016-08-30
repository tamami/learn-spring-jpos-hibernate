package lab.aikibo.services;

import lab.aikibo.model.Sppt;
import lab.aikibo.dao.StoreProceduresDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Service("spptServices")
@Transactional
public class SpptServicesImpl implements SpptServices {

  @Autowired
  private StoreProceduresDao spDao;

  @Override
  public Sppt getSpptByNopThn(String nop, String thn) throws SQLException {
    return spDao.getDataSppt(nop, thn);
  }
}
