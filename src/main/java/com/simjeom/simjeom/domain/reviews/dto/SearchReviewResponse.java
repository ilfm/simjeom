package com.simjeom.simjeom.domain.reviews.dto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class SearchReviewResponse {

  private String reviewId;
  private Integer star;
  private String comment;
  //private LocalDateTime LastVisitDt;  // 최근 방문일
  private String restaurantNm;
  private Integer visitCnt;
  //private String remark;

}
