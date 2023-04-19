package com.simjeom.simjeom.domain.reviews.dto;

import com.simjeom.simjeom.domain.keyword.domain.Keyword;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class CreateReviewRequest {


  //private String reviewId;
  private Integer star;
  private String comment;
  private String restaurantNm;
  private String visitDt;
  private String[] keywords; // 이런 배열이나 받아야하는데...하..

}
