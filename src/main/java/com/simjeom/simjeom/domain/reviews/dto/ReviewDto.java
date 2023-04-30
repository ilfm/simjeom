package com.simjeom.simjeom.domain.reviews.dto;

import com.simjeom.simjeom.domain.keyword.dto.KeywordDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
public class ReviewDto {

  private String reviewId;
  private Integer star;
  private String comment;
  private String restaurantNm;
  private String lastEatDt;  // 최근 방문일
  private String menuNm;
  private Integer eatCnt;
  private List<KeywordDto> keywords;
  private String keywordString;

  @Builder
  public ReviewDto(String reviewId, Integer star, String comment, String restaurantNm,
      String lastEatDt, String menuNm, Integer eatCnt,
      List<KeywordDto> keywords, String keywordString) {
    this.reviewId = reviewId;
    this.star = star;
    this.comment = comment;
    this.restaurantNm = restaurantNm;
    this.lastEatDt = lastEatDt;
    this.menuNm = menuNm;
    this.eatCnt = eatCnt;
    this.keywords = keywords;
    this.keywordString = keywordString;
  }

  @Builder
  public ReviewDto(Integer star, String restaurantNm, String lastEatDt, String menuNm) {
    this.star = star;
    this.restaurantNm = restaurantNm;
    this.lastEatDt = lastEatDt;
    this.menuNm = menuNm;
  }

  // toEntity
  /*public Review toEntity(){
    return Review.builder().build();
  }*/

}
