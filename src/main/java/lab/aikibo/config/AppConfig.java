package lab.aikibo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ComponentScan;

import lab.aikibo.services.Server;
import lab.aikibo.services.ServerImpl;

@Configuration
@Import({HibernateConfiguration.class})
@ComponentScan(basePackages = {"lab.aikibo"})
public class AppConfig {
  @Bean(name="server")
  public Server start() {
    return new ServerImpl();
  }
}
