package lab.aikibo.services;

import lab.aikibo.App;

import org.jpos.iso.ISOPackager;
import org.jpos.iso.ServerChannel;
import org.jpos.iso.ISOServer;
import org.jpos.iso.ISOException;
import org.jpos.iso.channel.ASCIIChannel;
import org.jpos.iso.packager.GenericPackager;

public class ServerImpl implements Server {
  @Override
  public void start() {
    String hostname = "localhost";
    int portNumber = 15243;

    try {
      ISOPackager packager = new GenericPackager("packager/iso93ascii.xml");
      ServerChannel channel = new ASCIIChannel(hostname, portNumber, packager);
      ISOServer server = new ISOServer(portNumber, channel, null);
      server.addISORequestListener(new ProcessRequest());

      new Thread(server).start();

      App.getLogger().debug("Server siap menerima koneksi pada port [" + portNumber + "]");
    } catch(ISOException isoe) {
      App.getLogger().debug(isoe);
    }
  }
}
