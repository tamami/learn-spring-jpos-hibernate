package lab.aikibo;

import org.apache.log4j.Logger;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import lab.aikibo.config.AppConfig;
import lab.aikibo.services.Server;

/**
 * Hello world!
 *
 */
public class App {

  static final Logger logger = Logger.getLogger(App.class);

  public static void main( String[] args ) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Server server = (Server) context.getBean("server");

        server.start();
  }


  // --- setter getter

  public static Logger getLogger() { return logger; }
}
