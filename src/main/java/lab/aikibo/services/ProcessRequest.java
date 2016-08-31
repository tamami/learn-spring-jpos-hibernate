package lab.aikibo.services;

import lab.aikibo.App;
import lab.aikibo.model.Sppt;

import org.jpos.iso.ISORequestListener;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOSource;
import org.jpos.iso.ISOException;
import org.jpos.iso.BaseChannel;

import java.text.SimpleDateFormat;
import java.io.IOException;
import java.util.Date;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;

public class ProcessRequest implements ISORequestListener {

  @Autowired
  SpptServices spptServices;

  ErrorRespon er;

  public boolean process(ISOSource isoSrc, ISOMsg isoMsg) {
    App.getLogger().debug("Server menerima koneksi dari [" + ((BaseChannel)isoSrc).getSocket().getInetAddress().getHostAddress() + "]");
    er = new ErrorRespon();
    try {
      if(isoMsg.getMTI().equalsIgnoreCase("0800")) {
        processLogon(isoSrc, isoMsg);
      } else if(isoMsg.getMTI().equalsIgnoreCase("0200")) {
        processFinTx(isoSrc, isoMsg);
      }
    } catch(ISOException isoe) {
      App.getLogger().debug("Kesalahan ISO di ProcessRequest.process: " + isoe);
    }

    return false;
  }

  private void processLogon(ISOSource isoSrc, ISOMsg isoMsg) {
    App.getLogger().debug("Receiving Network Management Request");
    ISOMsg reply = (ISOMsg) isoMsg.clone();
    try {
      reply.setMTI("0810");
      reply.set(7, new SimpleDateFormat("MMddHHmmss").format(new Date()));
      reply.set(39, "00");

      isoSrc.send(reply);
    } catch(IOException ioe) {
      App.getLogger().debug("Kesalahan IO di ProcessRequest.processLogon: " + ioe);
    } catch(ISOException isoe) {
      App.getLogger().debug("Kesalahan ISO di ProcessRequest.processLogon: " + isoe);
    }
  }

  private void processFinTx(ISOSource isoSrc, ISOMsg isoMsg) {
    App.getLogger().debug("Receiving Financial Transaction");
    // cek apakah inquiry atau transaksi
    if(isoMsg.getString(3).equalsIgnoreCase("360000")) { // inquiry
      processInquiry(isoSrc, isoMsg);
    } else if(isoMsg.getString(3).equalsIgnoreCase("560000")) {
      processTrx(isoSrc, isoMsg);
    } else {
      er.processingCodeError(isoSrc, isoMsg);
    }
  }

  private void processInquiry(ISOSource isoSrc, ISOMsg isoMsg) {
    App.getLogger().debug("Process Inquiry");
    ISOMsg reply = (ISOMsg) isoMsg.clone();

    String additionalData = isoMsg.getString(48);
    String nop = additionalData.substring(3,21);
    String thn = additionalData.substring(21,25);

    // cek thn harus berupa angka
    try {
      Integer.parseInt(thn);
    } catch(NumberFormatException nfe) {
      er.karakterTahunPajakError(isoSrc, isoMsg);
      return;
    }

    Sppt sppt;
    try {
      sppt = spptServices.getSpptByNopThn(nop, thn);
      if(sppt.getNop() == null) {
        App.getLogger().debug("Data NOP : " + nop + " tidak ditemukan.");
        er.inquiryNotFoundError(isoSrc, isoMsg);
        return;
      }
    } catch(SQLException sqle) {
      App.getLogger().debug("Kesalahan SQL di ProcessRequest.processInquiry: " + sqle);
      er.databaseError(isoSrc, isoMsg);
      return;
    }

    // susun bit 48

    try {
      reply.setMTI("0210");
      reply.set(7, new SimpleDateFormat("MMddHHmmss").format(new Date()));
      reply.set(39, "00");
      reply.set(48, );
    } catch(IOException ioe) {
      App.getLogger().debug("Kesalahan IO di ProcessRequest.processInquiry: " + ioe);
    } catch(ISOException isoe) {
      App.getLogger().debug("Kesalahan ISO di ProcessRequest.processInquiry: " + isoe);
    }
  }

  private void processTrx(ISOSource isoSrc, ISOMsg isoMsg) {}

}
