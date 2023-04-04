package com.simjeom.simjeom.domain.reviews.repository;


import com.simjeom.simjeom.domain.restaurant.domain.Restaurant;
import com.simjeom.simjeom.domain.restaurant.repository.RestaurantRepository;
import com.simjeom.simjeom.domain.reviews.dto.CreateReviewRequest;
import com.simjeom.simjeom.domain.reviews.service.ReviewService;
import java.time.LocalDateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReviewRepositoryTest {

  @Autowired
  private ReviewService reviewService;

  @Autowired
  private ReviewRepository reviewRepository;
  @Autowired
  private RestaurantRepository restaurantRepository;

  @Rollback(false)
  @Test
  @Transactional
  public void 시퀀스_테스트_식당데이터 (){
    Restaurant restaurant = Restaurant.builder().restaurantNm("옹심이").build();
    restaurantRepository.save(restaurant);
    //Restaurant restaurant2 = Restaurant.builder().restaurantNm("궁순대국").build();
    //restaurantRepository.save(restaurant2);
    //Restaurant restaurant3 = Restaurant.builder().restaurantNm("강촌식당").build();
    //restaurantRepository.save(restaurant3);
    //Restaurant restaurant4 = Restaurant.builder().restaurantNm("한가람").build();
    //restaurantRepository.save(restaurant4);
  }

  @Rollback(false)
  @Test
  public void 리뷰등록(){
    //given
    CreateReviewRequest request = new CreateReviewRequest();
    request.setComment("맛있어용2");
    request.setRestaurantId("RT-5");
    request.setStar(4);
    request.setVisitDt(LocalDateTime.now());
    String[] keywords = {"존맛"};
    request.setKeywords(keywords);

    //when
    reviewService.registerReview(request);

    //then
  }

  @Rollback(false)
  @Test
  public void 모든_리뷰보기(){
    reviewRepository.findAllReveiw();
  }


  @Rollback(false)
  @Test
  public void 키워드데이터_넣기 (){

  }
}
