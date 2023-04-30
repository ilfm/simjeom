package com.simjeom.simjeom.domain.reviews.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.simjeom.simjeom.domain.reviews.service.ReviewService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringRunner.class)
@SpringBootTest
class ReviewJpaRepositoryTest {

  @Autowired
  ReviewJpaRepository reviewJpaRepository;

  @Autowired
  ReviewService reviewService;

  @Rollback(value = false)
  @Transactional
  @Test
  public void 리뷰저장(){

  }


  @Test
  public void 리뷰_전체_조회(){
    reviewJpaRepository.findAllReview().forEach(reviewDto -> {
      System.out.println("reviewDto.getMenuNm() = " + reviewDto.getMenuNm());
      System.out.println("reviewDto.getRestaurantNm() = " + reviewDto.getRestaurantNm());
      System.out.println("reviewDto.getStar() = " + reviewDto.getStar());
      System.out.println("reviewDto.getLastEatDt() = " + reviewDto.getLastEatDt());
    });

  }

}