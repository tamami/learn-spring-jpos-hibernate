package lab.aikibo.services;

import org.jpos.iso.ISOSource;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.IOException;

import lab.aikibo.App;

public class ErrorRespon {

  public void inquiryNotFoundError(ISOSource isoSrc, ISOMsg isoMsg) {
    ISOMsg reply = (ISOMsg) isoMsg.clone();

    try {
      if(isoMsg.getMTI().equalsIgnoreCase("0200") && isoMsg.getString(3).equalsIgnoreCase("360000")) {
        reply.setMTI("0210");
        reply.set(7, getBit7());
        reply.set(39, "10");

        isoSrc.send(reply);
      } else {
        processingCodeError(isoSrc, isoMsg);
      }
    } catch(IOException ioe) {
      App.getLogger().debug("Kesalahan IO di ErrorRespon.inquiryNotFoundError: " + ioe);
    } catch(ISOException isoe) {
      App.getLogger().debug("Kesalahan ISO di ErrorRespon.inquiryNotFoundError: " + isoe);
    }
  }

  public void tagihanTelahTerbayarError(ISOSource isoSrc, ISOMsg isoMsg) {
    ISOMsg reply = (ISOMsg) isoMsg.clone();

    try {
      if(isoMsg.getMTI().equalsIgnoreCase("0200")) {
        reply.setMTI("0210");
        reply.set(7, getBit7());
        reply.set(39, "13");

        isoSrc.send(reply);
      }
    } catch(IOException ioe) {
      App.getLogger().debug("Kesalahan IO di ErrorRespon.tagihanTelahTerbayarError: " + ioe);
    } catch(ISOException isoe) {
      App.getLogger().debug("Kesalahan ISO di ErrorRespon.tagihanTelahTerbayarError: " + isoe);
    }
  }

  public void setoranNihilError(ISOSource isoSrc, ISOMsg isoMsg) {
    ISOMsg reply = (ISOMsg) isoMsg.clone();

    try {
      if(isoMsg.getMTI().equalsIgnoreCase("0200")) {
        reply.setMTI("0210");
        reply.set(7, getBit7());
        reply.set(39, "03");

        isoSrc.send(reply);
      }
    } catch(IOException ioe) {
      App.getLogger().debug("Kesalahan IO di ErrorRespon.setoranNihilError: " + ioe);
    } catch(ISOException isoe) {
      App.getLogger().debug("Kesalahan ISO di ErrorRespon.setoranNihilError: " + isoe);
    }
  }

  public void databaseError(ISOSource isoSrc, ISOMsg isoMsg) {
    ISOMsg reply = (ISOMsg) isoMsg.clone();

    try {
      if(isoMsg.getMTI().equalsIgnoreCase("0200")) {
        reply.setMTI("0210");
        reply.set(7, getBit7());
        reply.set(39, "04");

        isoSrc.send(reply);
      } else if(isoMsg.getMTI().equalsIgnoreCase("0400")) {
        reply.setMTI("0410");
        reply.set(7, getBit7());
        reply.set(39, "04");

        isoSrc.send(reply);
      } else if(isoMsg.getMTI().equalsIgnoreCase("0401")) {
        reply.setMTI("0411");
        reply.set(7, getBit7());
        reply.set(39, "04");

        isoSrc.send(reply);
      }
    } catch(IOException ioe) {
      App.getLogger().debug("Kesalahan IO di ErrorRespon.databaseError: " + ioe);
    } catch(ISOException isoe) {
      App.getLogger().debug("Kesalahan ISO di ErrorRespon.databaseError: " + isoe);
    }
  }

  public void createKodePengesahanError(ISOSource isoSrc, ISOMsg isoMsg) {
    ISOMsg reply = (ISOMsg) isoMsg.clone();

    try {
      if(isoMsg.getMTI().equalsIgnoreCase("0200")) {
        reply.setMTI("0210");
        reply.set(7, getBit7());
        reply.set(39, "05");

        isoSrc.send(reply);
      }
    } catch(IOException ioe) {
      App.getLogger().debug("Kesalahan IO di ErrorRespon.createKodePengesahanError: " + ioe);
    } catch(ISOException isoe) {
      App.getLogger().debug("Kesalahan ISO di ErrorRespon.createKodePengesahanError: " + isoe);
    }
  }

  public void karakterJumlahPembayaranError(ISOSource isoSrc, ISOMsg isoMsg) {
    ISOMsg reply = (ISOMsg) isoMsg.clone();

    try {
      if(isoMsg.getMTI().equalsIgnoreCase("0200")) {
        reply.setMTI("0210");
        reply.set(7, getBit7());
        reply.set(39, "31");

        isoSrc.send(reply);
      }
    } catch(IOException ioe) {
      App.getLogger().debug("Kesalahan IO di ErrorRespon.karakterJumlahPembayaranError: " + ioe);
    } catch(ISOException isoe) {
      App.getLogger().debug("Kesalahan ISO di ErrorRespon.karakterJumlahPembayaranError: " + isoe);
    }
  }

  public void tglJamBayarLbTglJamKirimError(ISOSource isoSrc, ISOMsg isoMsg) {
    ISOMsg reply = (ISOMsg) isoMsg.clone();

    try {
      if(isoMsg.getMTI().equalsIgnoreCase("0200")) {
        reply.setMTI("0210");
        reply.set(7, getBit7());
        reply.set(39, "32");

        isoSrc.send(reply);
      }
    } catch(IOException ioe) {
      App.getLogger().debug("Kesalahan IO di ErrorRespon.tglJamBayarLbTglJamKirimError: " + ioe);
    } catch(ISOException isoe) {
      App.getLogger().debug("Kesalahan ISO di ErrorRespon.tglJamBayarLbTglJamKirimError: " + isoe);
    }
  }

  public void tglKirimRevLbH1TglOriError(ISOSource isoSrc, ISOMsg isoMsg) {
    ISOMsg reply = (ISOMsg) isoMsg.clone();

    try {
      if(isoMsg.getMTI().equalsIgnoreCase("0400")) {
        reply.setMTI("0410");
        reply.set(7, getBit7());
        reply.set(39, "33");

        isoSrc.send(reply);
      } else if(isoMsg.getMTI().equalsIgnoreCase("0401")) {
        reply.setMTI("0411");
        reply.set(7, getBit7());
        reply.set(39, "33");

        isoSrc.send(reply);
      }
    } catch(IOException ioe) {
      App.getLogger().debug("Kesalahan IO di ErrorRespon.tglKirimRevLbH1TglOriError: " + ioe);
    } catch(ISOException isoe) {
      App.getLogger().debug("Kesalahan ISO di ErrorRespon.tglKirimRevLbH1TglOriError: " + isoe);
    }
  }

  public void processingCodeError(ISOSource isoSrc, ISOMsg isoMsg) {
    ISOMsg reply = (ISOMsg) isoMsg.clone();

    try {
      if(isoMsg.getMTI().equalsIgnoreCase("0200")) {
        reply.setMTI("0210");
        reply.set(7, getBit7());
        reply.set(39, "34");

        isoSrc.send(reply);
      } else if(isoMsg.getMTI().equalsIgnoreCase("0400")) {
        reply.setMTI("0410");
        reply.set(7, getBit7());
        reply.set(39, "34");

        isoSrc.send(reply);
      } else if(isoMsg.getMTI().equalsIgnoreCase("0401")) {
        reply.setMTI("0411");
        reply.set(7, getBit7());
        reply.set(30, "34");

        isoSrc.send(reply);
      }
    } catch(IOException ioe) {
      App.getLogger().debug("Kesalahan IO di ErrorRespon.processingCodeError: " + ioe);
    } catch(ISOException isoe) {
      App.getLogger().debug("Kesalahan ISO di ErrorRespon.processingCodeError: " + isoe);
    }
  }

  public void karakterTahunPajakError(ISOSource isoSrc, ISOMsg isoMsg) {
    ISOMsg reply = (ISOMsg) isoMsg.clone();

    try {
      if(isoMsg.getMTI().equalsIgnoreCase("0200")) {
        reply.setMTI("0210");
        reply.set(7, getBit7());
        reply.set(30, "35");

        isoSrc.send(reply);
      } else if(isoMsg.getMTI().equalsIgnoreCase("0400")) {
        reply.setMTI("0410");
        reply.set(7, getBit7());
        reply.set(30, "35");

        isoSrc.send(reply);
      } else if(isoMsg.getMTI().equalsIgnoreCase("0401")) {
        reply.setMTI("0411");
        reply.set(7, getBit7());
        reply.set(30, "35");

        isoSrc.send(reply);
      }
    } catch(IOException ioe) {
      App.getLogger().debug("Kesalahan IO di ErrorRespon.karakterTahunPajakError: " + ioe);
    } catch(ISOException isoe) {
      App.getLogger().debug("Kesalahan ISO di ErrorRespon.karakterTahunPajakError: " + isoe);
    }
  }

  public String getBit7() {
    return new SimpleDateFormat("MMddHHmmss").format(new Date());
  }

}
