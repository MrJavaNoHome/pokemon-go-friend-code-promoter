package io.github.mrjavanohome;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Parameters {

   private static final String FRIEND_CODES_KEY = "codes";
   private final Map<String, String> argumentsByKeys;

   public Parameters(String[] args) {
      argumentsByKeys = new HashMap<>();
      for (int i = 0; i < args.length; i += 2) {
         argumentsByKeys.put(args[i].substring(2), args[i + 1]);
      }
      validateRequiredParams();
   }

   private void validateRequiredParams() {
      getFriendCodes().forEach(this::validateFriendCode);
   }

   public List<String> getFriendCodes() {
      return Arrays.stream(argumentsByKeys.get(FRIEND_CODES_KEY).split(",")).collect(Collectors.toList());
   }

   private void validateFriendCode(String code) {
      if (code.length() != 12 || !isValidNumber(code)) {
         throw new RuntimeException("Invalid code: " + code);
      }
   }

   private boolean isValidNumber(String code) {
      try {
         Long.valueOf(code);
         return true;
      } catch (NumberFormatException e) {
         return false;
      }
   }


}
