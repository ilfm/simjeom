package com.simjeom.simjeom.domain.reviews.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class CreateReviewRequest {

  private String menuNm;
  private String restaurantNm;
  private Integer star;
  private String comment;
  private String visitDt;
  private String[] keywords;

}
