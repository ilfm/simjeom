package com.simjeom.simjeom.domain.reviews.dto;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class ReviewDetailResponse {

  private  ReviewDTO review;

}
