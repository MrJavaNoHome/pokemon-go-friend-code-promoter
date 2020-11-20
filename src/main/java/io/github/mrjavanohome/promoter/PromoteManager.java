package io.github.mrjavanohome.promoter;

import io.github.mrjavanohome.promoter.fcswap.FcSwapPromoter;
import io.github.mrjavanohome.promoter.friendcode.app.FriendCodeAppPromoter;
import io.github.mrjavanohome.promoter.pokemonroom.PokemonRoomPromoter;

import java.util.List;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class PromoteManager {

   private final PokemonRoomPromoter pokemonRoomPromoter;
   private final FcSwapPromoter fcSwapPromoter;
   private final FriendCodeAppPromoter friendCodeAppPromoter;
   private final Timer timer;

   public PromoteManager() {
      pokemonRoomPromoter = new PokemonRoomPromoter();
      fcSwapPromoter = new FcSwapPromoter();
      friendCodeAppPromoter = new FriendCodeAppPromoter();
      timer = new Timer();
   }

   public void schedulePromoters(List<String> codes) {
      schedule(pokemonRoomPromoter, codes, TimeUnit.HOURS.toMillis(2) + 6000);
      schedule(fcSwapPromoter, codes, TimeUnit.HOURS.toMillis(12) + 30000);
      schedule(friendCodeAppPromoter, codes, TimeUnit.HOURS.toMillis(1));
   }

   private void schedule(Promoter promoter, List<String> codes, long periodInMs) {
      timer.scheduleAtFixedRate(new PromoteTimerTask(promoter, codes), 0, periodInMs);
   }

}
