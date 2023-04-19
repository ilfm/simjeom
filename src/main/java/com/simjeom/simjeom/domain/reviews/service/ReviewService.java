package com.simjeom.simjeom.domain.reviews.service;


import com.simjeom.simjeom.domain.keyword.domain.Keyword;
import com.simjeom.simjeom.domain.keyword.dto.KeywordDto;
import com.simjeom.simjeom.domain.keyword.repository.KeywordRepository;
import com.simjeom.simjeom.domain.restaurant.domain.Restaurant;
import com.simjeom.simjeom.domain.restaurant.repository.RestaurantRepository;
import com.simjeom.simjeom.domain.reviews.domain.Review;
import com.simjeom.simjeom.domain.reviews.domain.ReviewKeyword;
import com.simjeom.simjeom.domain.reviews.dto.CreateReviewRequest;
import com.simjeom.simjeom.domain.reviews.dto.ReviewDTO;
import com.simjeom.simjeom.domain.reviews.dto.SearchReviewResponse;
import com.simjeom.simjeom.domain.reviews.dto.SearchReviewResult;
import com.simjeom.simjeom.domain.reviews.repository.ReviewJpaRepository;
import com.simjeom.simjeom.domain.reviews.repository.ReviewRepository;
import java.util.ArrayList;
import java.util.Arrays;
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
  private final ReviewJpaRepository reviewJpaRepository;
  //private final

  /**
   * 리뷰 등록
   */
  @Transactional
  public String registerReview(CreateReviewRequest request) {

    // todo 점심 중복여부 체크
    //Restaurant restaurant = restaurantRepository.findById(request.getRestaurantId());
    // todo keyword 중복체크
    String restaurantId = restaurantRepository.save(request.getRestaurantNm());
    Restaurant restaurant = restaurantRepository.findById(restaurantId);

    List<ReviewKeyword> reviewKeywords = new ArrayList<>();
    // reviewkeyword 엔티티 생성

    for (String keyword : request.getKeywords()) {
      String keywordId = keywordRepository.save(Keyword.createKeyword(keyword));
      ReviewKeyword reviewKeyword = ReviewKeyword.createReviewKeyword(keywordRepository.findById(keywordId));
      reviewKeywords.add(reviewKeyword);
    }



    // 리뷰 엔티티 생성
    Review review = Review.createReview(request, restaurant, reviewKeywords);
    String reviewId = reviewRepository.save(review);

    return reviewId;
  }

  /**
   * 모든 리뷰 조회 1. 리뷰조회 (해당리뷰 키워드 조회 )
   */
  public SearchReviewResponse searchAllReview() {

    // 리뷰데이터 가져오기
    List<SearchReviewResult> results = reviewJpaRepository.findReview();
    List<ReviewDTO> reviews = new ArrayList<>();

    // 응답 데이터에 맞게 변환
    for (SearchReviewResult result : results) {

     /* List<KeywordDto> keywords = new ArrayList<>();
      for (String keyword : result.getKeywords().split(",")) {
        KeywordDto keywordDto = KeywordDto.builder()
            .keywordNm(keyword).build();
        keywords.add(keywordDto);
      }*/

      ReviewDTO reviewDTO = ReviewDTO.builder()
          .reviewId(result.getReviewId())
          .star(result.getStar())
          .comment(result.getComment())
          .restaurantNm(result.getRestaurantNm())
          //.keywords(keywords)
          .keywordString(result.getKeywords())
          .visitCnt(result.getVisitCnt())
          .build();

      reviews.add(reviewDTO);
    }

    SearchReviewResponse response = SearchReviewResponse.builder()
        .reviews(reviews)
        .build();
    return response;
  }
  /**
   * 키워드로 리뷰조회
   */
  public SearchReviewResponse searchReviewByKeyword(List<String> keywords) {

    // 리뷰데이터 가져오기
    List<SearchReviewResult> results = reviewJpaRepository.searchReview(keywords);
    List<ReviewDTO> reviews = new ArrayList<>();

    // 응답 데이터에 맞게 변환
    for (SearchReviewResult result : results) {

      ReviewDTO reviewDTO = ReviewDTO.builder()
          .reviewId(result.getReviewId())
          .star(result.getStar())
          .comment(result.getComment())
          .restaurantNm(result.getRestaurantNm())
          .keywordString(result.getKeywords())
          .visitCnt(result.getVisitCnt())
          .build();

      reviews.add(reviewDTO);
    }

    SearchReviewResponse response = SearchReviewResponse.builder()
        .reviews(reviews)
        .build();
    return response;
  }

  // 이미 등록 되어있는 점심인지 체크
  public String checkLunchDuplicate() {
    return "";
  }

  // 키워드 이미 등록 되어 있는지 체크
  public String checkKeywordDuplicate() {
    return "";
  }
}
