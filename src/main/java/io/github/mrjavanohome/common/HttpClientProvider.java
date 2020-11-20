package io.github.mrjavanohome.common;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;

import java.util.List;

public class HttpClientProvider {

   public CloseableHttpClient createClient(List<BasicHeader> defaultHeaders) {
      return createClientBuilder().setDefaultHeaders(defaultHeaders).build();
   }

   public CloseableHttpClient createClient() {
      return createClientBuilder().build();
   }

   private HttpClientBuilder createClientBuilder() {
      return HttpClientBuilder.create()
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36");
   }

}
