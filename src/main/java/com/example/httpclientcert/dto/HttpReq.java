package com.example.httpclientcert.dto;

import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpMethod;

@Getter
@Setter
public class HttpReq {

  private String url;
  private HttpMethod method;
  private Map<String, List<String>> headers;
  private String body;
}
