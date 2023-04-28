package com.simjeom.simjeom.domain.reviews.service;


import com.simjeom.simjeom.domain.keyword.domain.Keyword;
import com.simjeom.simjeom.domain.keyword.repository.KeywordRepository;
import com.simjeom.simjeom.domain.menu.domain.Menu;
import com.simjeom.simjeom.domain.menu.repository.MenuRepository;
import com.simjeom.simjeom.domain.reviews.domain.Review;
import com.simjeom.simjeom.domain.reviews.domain.ReviewKeyword;
import com.simjeom.simjeom.domain.reviews.dto.CreateReviewRequest;
import com.simjeom.simjeom.domain.reviews.dto.DetailReviewResult;
import com.simjeom.simjeom.domain.reviews.dto.ReviewDto;
import com.simjeom.simjeom.domain.reviews.dto.SearchReviewResponse;
import com.simjeom.simjeom.domain.reviews.dto.SearchReviewResult;
import com.simjeom.simjeom.domain.reviews.repository.ReviewJpaRepository;
import com.simjeom.simjeom.domain.reviews.repository.ReviewRepository;
import java.util.ArrayList;
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
  private final MenuRepository menuRepository;
  private final KeywordRepository keywordRepository;
  private final ReviewJpaRepository reviewJpaRepository;

  /**
   * 리뷰 등록
   */
  @Transactional
  public String registerReview(CreateReviewRequest request) {

    // 메뉴 중복체크
    Menu menu = Menu.builder().menuNm(request.getMenuNm()).build();
    String menuId = menuRepository.save(menu);
    Menu findMenu = menuRepository.findById(menuId);

    List<ReviewKeyword> reviewKeywords = new ArrayList<>();

    // todo keyword 중복체크
    for (String keyword : request.getKeywords()) {
      String keywordId = keywordRepository.save(Keyword.createKeyword(keyword));
      ReviewKeyword reviewKeyword = ReviewKeyword.createReviewKeyword(
          keywordRepository.findById(keywordId));
      reviewKeywords.add(reviewKeyword);
    }

    // 리뷰 엔티티 생성
    Review review = Review.createReview(request, findMenu, reviewKeywords);
    String reviewId = reviewRepository.save(review);

    return reviewId;
  }

  /**
   * 리뷰 상세조회
   */
  public ReviewDto reviewDetail() {
    DetailReviewResult result = reviewJpaRepository.getReviewDetail();
    return ReviewDto.builder()
        .reviewId(result.getReviewId())
        //.visitCnt(result.getVisitCnt())
        .comment(result.getComment())
        .star(result.getStar())
        .menuNm(result.getRestaurantNm())
        .keywordString("")
        .build();
  }

  /**
   * 리뷰 조회
   */
  public SearchReviewResponse searchAllReview() {
    // 리뷰데이터 가져오기
    List<SearchReviewResult> results = reviewJpaRepository.findReviewByKeywords();
    List<ReviewDto> reviews = new ArrayList<>();

    for (SearchReviewResult result : results) {
      ReviewDto reviewDTO = ReviewDto.builder()
          .reviewId(result.getReviewId())
          .star(result.getStar())
          .comment(result.getComment())
          //.menuNm(result.getMenuNm())
          //.keywords(keywords)
          .keywordString(result.getKeywords())
          //.visitCnt(result.getVisitCnt())
          .build();

      reviews.add(reviewDTO);
    }

    SearchReviewResponse response = SearchReviewResponse.builder()
        //.reviews(reviews)
        .build();
    return response;
  }

  /**
   * 키워드로 리뷰조회
   */
  public SearchReviewResponse searchReviewByKeyword(List<String> keywords) {

    // 리뷰데이터 가져오기
    List<SearchReviewResult> results = reviewJpaRepository.searchReview(keywords);
    List<ReviewDto> reviews = new ArrayList<>();

    // 응답 데이터에 맞게 변환
    for (SearchReviewResult result : results) {

      ReviewDto reviewDTO = ReviewDto.builder()
          .reviewId(result.getReviewId())
          .star(result.getStar())
          .comment(result.getComment())
          //.menuNm(result.getMenuNm())
          .keywordString(result.getKeywords())
          //.visitCnt(result.getVisitCnt())
          .build();

      reviews.add(reviewDTO);
    }

    SearchReviewResponse response = SearchReviewResponse.builder()
        //.reviews(reviews)
        .build();
    return response;
  }

}
