package com.simjeom.simjeom.domain.reviews.dto;


import lombok.Getter;


public interface SearchReviewResult {

  String getReviewId();

  Integer getStar();

  String getComment();

  String getMenuNm();

  String getEatDt();  // 최근 방문일

  String getRestaurantNm();

  Integer getVisitCnt();

  String getKeywords();
}
