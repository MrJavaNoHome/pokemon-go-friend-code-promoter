package io.github.mrjavanohome;

import io.github.mrjavanohome.promoter.PromoteManager;

import java.util.logging.Logger;

public class App {

   private static final Logger log = Logger.getLogger(App.class.getSimpleName());

   public static void main(String[] args) {
      Parameters parameters = new Parameters(args);
      log.info("Starting with friend codes: " + parameters.getFriendCodes().toString());
      new PromoteManager().schedulePromoters(parameters.getFriendCodes());
   }

}
