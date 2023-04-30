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
        .comment("진짜 짱맛~")
        .menuNm("팟타이")
        .star(5)
        .restaurantNm("리틀방콕")
        .visitDt("2023-04-10")
        .keywords(new String[]{"비쌈","가까움","웨이팅없음"})
        .build();

    String reviewId = reviewService.registerReview(review);
    System.out.println("reviewId = " + reviewId);

  }

}