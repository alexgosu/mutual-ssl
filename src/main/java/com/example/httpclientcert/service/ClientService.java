package com.example.httpclientcert.service;

import com.example.httpclientcert.dto.HttpReq;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ClientService {

  private final RestTemplate restTemplate;

  public String doRequest(HttpReq request) {
    HttpHeaders headers = new HttpHeaders();
    headers.putAll(request.getHeaders());
    HttpEntity<String> requestBody = new HttpEntity<>(request.getBody(), headers);

    ResponseEntity<String> response = restTemplate.exchange(request.getUrl(),
        request.getMethod(),
        requestBody,
        String.class);
    return String.format("headers: %s;%nbody: %s;", response.getHeaders(), response.getBody());
  }
}
