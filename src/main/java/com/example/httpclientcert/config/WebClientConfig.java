package com.example.httpclientcert.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

@Configuration
public class WebClientConfig {

  @Value("${auth.keystore}")
  private String keystore;


  @Value("${auth.password}")
  private String password;


  @Bean
  public RestTemplate restTemplate()
      throws NoSuchAlgorithmException, KeyStoreException, IOException, CertificateException, UnrecoverableKeyException, KeyManagementException {
    KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
    keyStore.load(new FileInputStream(keystore),
        password.toCharArray());

    SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(
        new SSLContextBuilder()
            .loadTrustMaterial(new TrustAllStrategy())
            .loadKeyMaterial(keyStore, password.toCharArray())
            .build(),
        NoopHostnameVerifier.INSTANCE);

    CloseableHttpClient client = HttpClients.custom()
        .setSSLSocketFactory(socketFactory)
        .build();

    ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(client);
    RestTemplate rest = new RestTemplate(requestFactory);
    rest.setErrorHandler(new ResponseErrorHandler() {
      @Override
      public boolean hasError(ClientHttpResponse response) throws IOException {
        return false;
      }

      @Override
      public void handleError(ClientHttpResponse response) throws IOException {

      }
    });
    return rest;
  }


}
