package lab.aikibo.services;

public class ProcessRequest implements ISORequestListener {

  public boolean process(ISOSource isoSrc, ISOMsg isoMsg) {
    App.getLogger().debug("Server menerima koneksi dari [" + ((BaseChannel)isoSrc).getSocket().getInetAddress().getHostAddress() + "]");
    if(isoMsg.getMTI().equalsIgnore("0800")) {
      processLogon(isoSrc, isoMsg);
    }

    return false;
  }

  private void processLogon(ISOSource isoSrc, ISOMsg isoMsg) {
    App.getLogger().debug("Accepting Network Management Request");
    ISOMsg reply = (ISOMsg) isoMsg.clone();
    reply.setMTI("0810");
    reply.set(7, new SimpleDateFormat("MMddHHmmss").format(new Date()));
    reply.set(39, 0);
  }

}
