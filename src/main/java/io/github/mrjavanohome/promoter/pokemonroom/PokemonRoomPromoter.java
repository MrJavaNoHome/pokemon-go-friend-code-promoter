package io.github.mrjavanohome.promoter.pokemonroom;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.internal.util.Lists;
import io.github.mrjavanohome.common.HttpClientProvider;
import io.github.mrjavanohome.promoter.Promoter;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PokemonRoomPromoter implements Promoter {

   private final Logger log = Logger.getLogger(getClass().getSimpleName());
   private final String url = "https://pokemonroom.com";
   private final String apiUrl = "https://api.pokemonroom.com";
   private final ObjectMapper objectMapper = new ObjectMapper();
   private final HttpClientProvider httpClientProvider = new HttpClientProvider();

   @Override
   public void promote(List<String> codes) {
      for (String code : codes) {
         try {
            promote(code);
         } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage(), e);
         }
      }
   }

   private void promote(String code) throws IOException, URISyntaxException {
      log.info(String.format("Promoting %s on pokemon room", code));
      try (CloseableHttpClient client = createHttpClient()) {
         HttpPost httpPost = new HttpPost("https://api.pokemonroom.com/codes/add");
         httpPost.setHeader("X-CSRF-TOKEN", getCsrfToken(client).getCsrfToken());

         PromoteRequest promoteRequest = new PromoteRequest(code);
         String promoteRequestJson = objectMapper.writeValueAsString(promoteRequest);
         httpPost.setEntity(new StringEntity(promoteRequestJson));
         CloseableHttpResponse promoteResponse = client.execute(httpPost);
         log.info(String.format("Promote %s with status %d and response %s", code, promoteResponse.getStatusLine().getStatusCode(),
               parseJsonToObj(promoteResponse, PromoteResponse.class)));
      }
   }

   private CSRFTokenResponse getCsrfToken(CloseableHttpClient client) throws IOException, URISyntaxException {
      HttpResponse response = client.execute(new HttpGet(new URI(apiUrl).resolve("/codes/token")));
      CSRFTokenResponse tokenResponse = parseJsonToObj(response, CSRFTokenResponse.class);
      log.info(String.format("Obtained CSRF token %s with status %d", tokenResponse.toString(), response.getStatusLine().getStatusCode()));
      return tokenResponse;
   }

   private <T> T parseJsonToObj(HttpResponse promoteResponse, Class<T> clazz) throws IOException {
      return objectMapper.readValue(promoteResponse.getEntity().getContent(), clazz);
   }

   private CloseableHttpClient createHttpClient() {
      ArrayList<BasicHeader> defaultHeaders = Lists.newArrayList(
            new BasicHeader("Accept", "application/json, text/plain, */*"),
            new BasicHeader("Content-type", "application/json;charset=UTF-8"),
            new BasicHeader("Host", "api.pokemonroom.com"),
            new BasicHeader("Origin", url),
            new BasicHeader("Referer", url));

      return httpClientProvider.createClient(defaultHeaders);
   }

}
