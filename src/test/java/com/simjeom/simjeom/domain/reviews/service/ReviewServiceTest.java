package com.simjeom.simjeom.domain.reviews.service;

import static org.junit.jupiter.api.Assertions.*;

import com.simjeom.simjeom.domain.reviews.dto.CreateReviewRequest;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@RunWith(SpringRunner.class)
class ReviewServiceTest {

  @Autowired
  private ReviewService reviewService;

  @Test
  @Transactional
  @Rollback(value = false)
  public void 리뷰등록(){
    CreateReviewRequest review =CreateReviewRequest.builder()
        .comment("맛있어용")
        .menuNm("육회덮밥")
        .star(5)
        .restaurantNm("광시탕")
        .visitDt("2023-04-29")
        .keywords(new String[]{"가성비","가까움"})
        .build();

    String reviewId = reviewService.registerReview(review);
    System.out.println("reviewId = " + reviewId);

  }

}