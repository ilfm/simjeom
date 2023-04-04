package com.simjeom.simjeom.domain.reviews.dto;

import com.simjeom.simjeom.domain.keyword.domain.Keyword;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateReviewRequest {

  private String reviewId;
  private Integer star;
  private String comment;
  private String restaurantId;
  private LocalDateTime visitDt;
  private String[] keywords; // 이런 배열이나 받아야하는데...하..

}
