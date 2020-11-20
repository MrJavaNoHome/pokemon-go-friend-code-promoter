package io.github.mrjavanohome.promoter.pokemonroom;

class CSRFTokenResponse {

   private boolean success;
   private String csrfToken;

   public boolean isSuccess() {
      return success;
   }

   public void setSuccess(boolean success) {
      this.success = success;
   }

   public String getCsrfToken() {
      return csrfToken;
   }

   public void setCsrfToken(String csrfToken) {
      this.csrfToken = csrfToken;
   }

   @Override
   public String toString() {
      return "TokenResponse{" + "success=" + success + ", csrfToken='" + csrfToken + '\'' + '}';
   }
}
