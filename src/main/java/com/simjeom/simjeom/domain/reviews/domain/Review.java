package com.simjeom.simjeom.domain.reviews.domain;


import com.simjeom.simjeom.domain.menu.domain.Menu;
import com.simjeom.simjeom.domain.reviews.dto.CreateReviewRequest;
import com.simjeom.simjeom.domain.global.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Getter
@Setter
@SequenceGenerator(name = "REVIEW_SEQ", sequenceName = "REVIEW_SEQ", allocationSize = 1)
public class Review extends BaseEntity {

  // protected로 생성하는 이유
  // - 다른곳에서 생성할수 없게 만들고 생성메소드를 통해서 생성 할 수 있도록 만드려고.
  protected Review() {

  }

  // 속성
  // 리뷰아이디,별점,한줄평,키워드,등록일,수정일
  @Id
  @GenericGenerator(name = "SeqGenerator", strategy = "com.simjeom.simjeom.domain.global.SeqGenerator"
      , parameters = {@Parameter(name = "SEQ_NAME", value = "REVIEW_SEQ"),
      @Parameter(name = "PREFIX", value = "RV")})
  @GeneratedValue(generator = "SeqGenerator")
  private String reviewId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "menuId")
  private Menu menu;

  @OneToMany(mappedBy = "review", cascade = CascadeType.PERSIST)
  private List<ReviewKeyword> reviewKeyword = new ArrayList<>();

  @Column(nullable = false)
  private String restaurantNm;

  @Column(nullable = false)
  private Integer star;

  @Column
  private String comment;

  @Column
  private LocalDateTime visitDt;

  public void setMenu(Menu menu) {
    this.menu = menu;
  }

  // 연관관계 편의 메소드 ======================================================================
  public void addReviewKeyword(ReviewKeyword reviewKeyword) {
    this.reviewKeyword.add(reviewKeyword);
    reviewKeyword.setReview(this);
  }
  // ========================================================================================

  public static Review createReview(CreateReviewRequest request, Menu menu, List<ReviewKeyword> reviewKeywords) {
    Review review = new Review();
    review.setStar(request.getStar());
    review.setComment(request.getComment());
    review.setMenu(menu);
    review.setRestaurantNm(request.getRestaurantNm());
    review.setVisitDt(review.getVisitDt());

    for (ReviewKeyword reviewKeyword : reviewKeywords) {
      review.addReviewKeyword(reviewKeyword);
    }
    return review;
  }


}
