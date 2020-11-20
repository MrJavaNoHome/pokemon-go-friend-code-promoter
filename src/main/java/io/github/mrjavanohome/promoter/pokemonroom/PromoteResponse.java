package io.github.mrjavanohome.promoter.pokemonroom;

class PromoteResponse {

   private boolean success;
   private String response;

   public boolean isSuccess() {
      return success;
   }

   public void setSuccess(boolean success) {
      this.success = success;
   }

   public String getResponse() {
      return response;
   }

   public void setResponse(String response) {
      this.response = response;
   }

   @Override
   public String toString() {
      return "PromoteResponse{" + "success=" + success + ", response='" + response + '\'' + '}';
   }
}
