package lab.aikibo.services;

import lab.aikibo.App;

import org.jpos.iso.ISORequestListener;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOSource;
import org.jpos.iso.ISOException;
import org.jpos.iso.BaseChannel;

import java.text.SimpleDateFormat;
import java.io.IOException;
import java.util.Date;

public class ProcessRequest implements ISORequestListener {

  public boolean process(ISOSource isoSrc, ISOMsg isoMsg) {
    App.getLogger().debug("Server menerima koneksi dari [" + ((BaseChannel)isoSrc).getSocket().getInetAddress().getHostAddress() + "]");
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
      reply.set(39, "0");

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
      processTrx(isoSrc, isoMsg));
    }
  }

}
