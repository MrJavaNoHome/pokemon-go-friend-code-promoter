package io.github.mrjavanohome.promoter;

import java.util.List;

public interface Promoter {

   void promote(List<String> codes) throws PromotingFailureException;

}
