package com.simjeom.simjeom.domain.reviews.dto;

public interface DetailReviewResult {

  String getReviewId();

  Integer getStar();

  String getComment();

  String getVisitDt();  // 최근 방문일

  String getRestaurantNm();

  Integer getVisitCnt();

  String getKeywords();
}
