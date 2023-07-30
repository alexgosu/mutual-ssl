package com.example.httpclientcert;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@Slf4j
public class HttpClientCertApplication {

  public static void main(String[] args) {
    SpringApplication.run(HttpClientCertApplication.class, args);
  }

  @EventListener
  public void onStart(ApplicationStartedEvent e) {
    log.info("started");
  }


}
