package com.simjeom.simjeom.domain.reviews;

import com.simjeom.simjeom.domain.reviews.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ReviewController {

  private final ReviewService reviewService;

}
