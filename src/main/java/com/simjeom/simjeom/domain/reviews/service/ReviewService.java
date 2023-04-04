package com.simjeom.simjeom.domain.reviews.service;


import com.simjeom.simjeom.domain.keyword.domain.Keyword;
import com.simjeom.simjeom.domain.keyword.repository.KeywordRepository;
import com.simjeom.simjeom.domain.restaurant.domain.Restaurant;
import com.simjeom.simjeom.domain.restaurant.repository.RestaurantRepository;
import com.simjeom.simjeom.domain.reviews.domain.Review;
import com.simjeom.simjeom.domain.reviews.domain.ReviewKeyword;
import com.simjeom.simjeom.domain.reviews.dto.CreateReviewRequest;
import com.simjeom.simjeom.domain.reviews.dto.SearchReviewResponse;
import com.simjeom.simjeom.domain.reviews.repository.ReviewRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor  // final 붙은것 자동으로
public class ReviewService {

  private final ReviewRepository reviewRepository;
  private final RestaurantRepository restaurantRepository;
  private final KeywordRepository keywordRepository;

  // 리뷰 등록
  @Transactional
 public void registerReview(CreateReviewRequest request){

   // todo 점심 중복여부 체크
   Restaurant restaurant = restaurantRepository.findById(request.getRestaurantId());
   // todo keyword 중복체크

   // reviewkeyword 엔티티 생성
   Keyword keyword = Keyword.createKeyword("존맛");
   //keywordRepository.save(keyword);  <- keyword persist
   ReviewKeyword reviewKeyword = ReviewKeyword.createReviewKeyword(keyword);

   // 리뷰 엔티티 생성
   Review review = Review.createReview(request, restaurant, reviewKeyword);
   reviewRepository.save(review);

  }

  // 모든 리뷰 조회 - 보통 여러객체 가져올때 어떻게할까나?
  public void searchAllReview(){
    List<SearchReviewResponse> reviews =  reviewRepository.findAllReveiw();
    for ( SearchReviewResponse review: reviews) {

      //log.debug("reviewId={]",review.getReviewId());

    }


  }

  // 이미 등록 되어있는 점심인지 체크
  public String checkLunchDuplicate(){

   return "";
  }

  // 키워드 이미 등록 되어 있는지 체크
  public String checkKeywordDuplicate(){
   return "";
  }
}
