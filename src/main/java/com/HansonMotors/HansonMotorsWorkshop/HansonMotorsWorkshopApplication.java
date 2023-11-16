package com.HansonMotors.HansonMotorsWorkshop;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

//import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableFeignClients
public class HansonMotorsWorkshopApplication {

  //  @Value("${twilio.account.sid}")
  private static String ACCOUNT_SID = "AC10fe554dfdc6d4127837fa12fd612952";
  //@Value("${twilio.account.token}")
  private static String AUTH_ID = "75386423d41a5f0e32fded1e7065bd3c";

  public static void main(String[] args) {
    SpringApplication.run(HansonMotorsWorkshopApplication.class, args);
  }

}
