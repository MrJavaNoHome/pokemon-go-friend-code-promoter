package io.github.mrjavanohome.promoter;

import java.util.List;
import java.util.TimerTask;

class PromoteTimerTask extends TimerTask {

   private final Promoter promoter;
   private final List<String> codes;

   public PromoteTimerTask(Promoter promoter, List<String> codes) {
      this.promoter = promoter;
      this.codes = codes;
   }

   @Override
   public void run() {
      promoter.promote(codes);
   }

}
