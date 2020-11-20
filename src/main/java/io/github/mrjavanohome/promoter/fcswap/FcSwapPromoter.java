package io.github.mrjavanohome.promoter.fcswap;

import com.google.inject.internal.util.Lists;
import io.github.mrjavanohome.common.HttpClientProvider;
import io.github.mrjavanohome.promoter.Promoter;
import io.github.mrjavanohome.promoter.PromotingFailureException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FcSwapPromoter implements Promoter {

   private final Logger log = Logger.getLogger(getClass().getSimpleName());
   private final String uri = "https://www.fcswap.com/game/pokemon-go/";
   private HttpClientProvider httpClientProvider = new HttpClientProvider();

   @Override
   public void promote(List<String> codes) throws PromotingFailureException {
      try {
         for (String code : codes) {
            promote(code);
         }
      } catch (Exception e) {
         log.log(Level.SEVERE, e.getMessage(), e);
      }
   }

   private void promote(String code) throws IOException {
      try (final CloseableHttpClient httpClient = httpClientProvider.createClient()) {
         final ArrayList<BasicNameValuePair> form = Lists.newArrayList(new BasicNameValuePair("code", code), new BasicNameValuePair("submit", null));
         final HttpPost httpPost = new HttpPost(uri);
         httpPost.setEntity(new UrlEncodedFormEntity(form));
         final CloseableHttpResponse response = httpClient.execute(httpPost);
         log.info(String.format("Posted code %s with response status %d", code, response.getStatusLine().getStatusCode()));
      }
   }

}
