package com.simjeom.simjeom.domain.reviews.domain;


import com.simjeom.simjeom.domain.keyword.domain.Keyword;
import com.simjeom.simjeom.domain.global.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Getter @Setter
@SequenceGenerator(name = "REVIEW_KEYWORD_SEQ", sequenceName = "REVIEW_KEYWORD_SEQ", allocationSize = 1)
public class ReviewKeyword extends BaseEntity {

  protected ReviewKeyword(){

  }

  @Builder
  public ReviewKeyword(Keyword keyword) {
    this.keyword = keyword;
  }

  @Id
  @GenericGenerator(name = "SeqGenerator", strategy = "com.simjeom.simjeom.domain.global.SeqGenerator"
      , parameters = {@Parameter(name="SEQ_NAME",value="REVIEW_KEYWORD_SEQ"),
      @Parameter(name="PREFIX",value="RK")})
  @GeneratedValue(generator ="SeqGenerator")
  private String reviewKeywordId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="reviewId")
  private Review review;

  @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
  @JoinColumn(name = "keywordId")
  private Keyword keyword;

  // 생성 메소드
  public static ReviewKeyword createReviewKeyword(Keyword keyword){
    return ReviewKeyword.builder().keyword(keyword).build();
  }


}
