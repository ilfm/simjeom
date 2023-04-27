package com.simjeom.simjeom.domain.reviews.dto;

import com.simjeom.simjeom.domain.keyword.dto.KeywordDto;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class ReviewDto {

  private String reviewId;
  private Integer star;
  private String comment;
  private String restaurantNm;
  //private String LastVisitDt;  // 최근 방문일
  private String menuNm;
  private Integer eatCnt;
  private List<KeywordDto> keywords;
  private String keywordString;

  // toEntity
  /*public Review toEntity(){
    return Review.builder().build();
  }*/

}
