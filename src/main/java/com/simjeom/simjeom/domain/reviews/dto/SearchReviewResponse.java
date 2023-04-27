package com.simjeom.simjeom.domain.reviews.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class SearchReviewResponse {

  private String menuNm;
  private String keywordString;
  private String eatCnt;

}
