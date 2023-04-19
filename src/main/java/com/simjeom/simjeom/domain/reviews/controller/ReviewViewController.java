package com.simjeom.simjeom.domain.reviews.controller;


import com.simjeom.simjeom.domain.reviews.dto.SearchReviewResponse;
import com.simjeom.simjeom.domain.reviews.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/review")
@Controller
@RequiredArgsConstructor
public class ReviewViewController {

  private final ReviewService reviewService;
  /**
   *   리뷰 리스트화면 이동
   * */
  @GetMapping(value="/reviewList")
  public String reviewList(Model model){
    // 모든 리뷰 조회
    SearchReviewResponse response = reviewService.searchAllReview();
    model.addAttribute("response", response);
    return "/reviews/reviewList";
  }

  /**
   *   리뷰 등록화면 이동
   * */
  @GetMapping(value="/registerReview")
  public String registerReview(){
    return "/reviews/registerReview";
  }


}
