package com.example.httpclientcert.controller;

import com.example.httpclientcert.dto.HttpReq;
import com.example.httpclientcert.service.ClientService;
import java.security.cert.X509Certificate;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MainController {

  private final ClientService service;

  @GetMapping("/get")
  public String get(HttpServletRequest request) {
    log.info("get {}", request);
    X509Certificate[] certs = (X509Certificate[]) request.getAttribute("javax.servlet.request.X509Certificate");
    return certs[0].getSubjectDN().getName();
  }

  @PostMapping("/executeRequest")
  public String executeRequest(@RequestBody HttpReq req) {
    return service.doRequest(req);
  }
}
