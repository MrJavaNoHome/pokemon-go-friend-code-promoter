package io.github.mrjavanohome.promoter.pokemonroom;

final class PromoteRequest {
   private final String code;
   private final String region = "Poland";
   private final String action = "fexp";
   private final String team = "mystic";
   private final String level = "30-40";

   PromoteRequest(String code) {this.code = code;}

   public String getCode() {
      return code;
   }

   public String getRegion() {
      return region;
   }

   public String getAction() {
      return action;
   }

   public String getTeam() {
      return team;
   }

   public String getLevel() {
      return level;
   }
}
