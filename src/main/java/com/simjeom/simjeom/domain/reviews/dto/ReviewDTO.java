package com.simjeom.simjeom.domain.reviews.dto;

import com.simjeom.simjeom.domain.keyword.dto.KeywordDto;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class ReviewDTO {

  private String reviewId;
  private Integer star;
  private String comment;
  //private LocalDateTime LastVisitDt;  // 최근 방문일
  private String restaurantNm;
  private Integer visitCnt;
  private List<KeywordDto> keywords;
  private String keywordString;
}
