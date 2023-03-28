package com.simjeom.simjeom.domain.reviews.dto;

import com.simjeom.simjeom.domain.keyword.dto.KeywordDto;
import com.simjeom.simjeom.domain.restaurant.dto.LunchDto;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReviewDto {

  private String reviewId;
  private Integer star;
  private String comment;
  private LunchDto restaurant;
  private KeywordDto keyword;

}
