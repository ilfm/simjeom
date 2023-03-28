package com.simjeom.simjeom.domain.reviews.domain;


import com.simjeom.simjeom.domain.keyword.dto.KeywordDto;
import com.simjeom.simjeom.domain.restaurant.domain.Lunch;
import com.simjeom.simjeom.domain.restaurant.dto.LunchDto;
import com.simjeom.simjeom.domain.reviews.dto.ReviewDto;
import com.simjeom.simjeom.global.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Builder
@SequenceGenerator(name = "REVIEW_SEQ", sequenceName = "REVIEW_SEQ", allocationSize = 1)
public class Review extends BaseEntity {

  protected Review(){

  }
  // 속성
  // 리뷰아이디, 식당, 키워드 , 등록일, 수정일, 별점, 한줄평
  @Id
  @GeneratedValue(generator = "REVIEW_SEQ", strategy = GenerationType.SEQUENCE)
  private String reviewId;

  @Column(nullable = false)
  private Integer star;

  @Column(nullable = false)
  private String menu;

  @Column
  private String comment;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="restaurantId")
  private Lunch lunch;

  @OneToMany(mappedBy = "reviewKeyword")
  private List<ReviewKeyword> reviewKeywordList = new ArrayList<>();

  // 생성 메소드
  public static Review createReview(ReviewDto review, List<KeywordDto> keywordList, LunchDto restaurant){

    Review reveiw = new Review();
    //review.set

    return reveiw;

  }



}
