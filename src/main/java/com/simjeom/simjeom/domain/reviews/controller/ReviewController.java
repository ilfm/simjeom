package com.simjeom.simjeom.domain.reviews.controller;

import com.simjeom.simjeom.domain.reviews.dto.CreateReviewRequest;
import com.simjeom.simjeom.domain.reviews.dto.SearchReviewResponse;
import com.simjeom.simjeom.domain.reviews.service.ReviewService;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequestMapping(value = "/api/review")
@RequiredArgsConstructor
public class ReviewController {

  private final ReviewService reviewService;

  /**
   * 리뷰 등록
   */
  @PostMapping(value = "/createReview")
  public ResponseEntity<String> registerReview(@RequestBody CreateReviewRequest request) {

    String reviewId = reviewService.registerReview(request);
    return new ResponseEntity<>(reviewId, HttpStatus.CREATED);
  }

  /**
   * 리뷰 검색
   */
  @GetMapping(value = "/searchReviewList")
  public String reviewList(Model model, @RequestParam(required = false) String[] keywords) {

    if(keywords == null){
      //SearchReviewResponse response =reviewService.searchAllReview();
      //model.addAttribute("response", response);
    }else if(keywords != null){
      SearchReviewResponse response = reviewService.searchReviewByKeyword(
          Arrays.stream(keywords).toList());
      model.addAttribute("response", response);
    }

    return "/reviews/reviewList :: #reviewList";
  }
  /*
    - 전체 화면 렌더링 말고 부분부분 업데이트 하고 싶을 때
       타임리프 fragment 문법을 사용하여 처리한다.
       "/reviews/reviewList :: #reviewList" 구문은
       /reviews/reviewList 템프릿 파일에 id가  reviewList인
       html요소를 찾아서 해당 부분만 반환한다는 의미

       즉 /reviews/reviewList 페이지의 #reviewList 요소를 업데이트
       하는데 Model에 추가한 response 데이터를 이용해서 html를 동적으로 생성

      [참고]
      - 비동기,동기
      https://velog.io/@daybreak/%EB%8F%99%EA%B8%B0-%EB%B9%84%EB%8F%99%EA%B8%B0-%EC%B2%98%EB%A6%AC
      - 타임리프 ajax로 비동기식 화면수정
      https://chaelin1211.github.io/study/2021/04/14/thymeleaf-ajax.html
    */

}
