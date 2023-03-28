package com.simjeom.simjeom.domain.reviews.domain;


import com.simjeom.simjeom.domain.keyword.domain.Keyword;
import com.simjeom.simjeom.global.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "REVIEW_KEYWORD_SEQ", sequenceName = "REVIEW_KEYWORD_SEQ", allocationSize = 1)
public class ReviewKeyword extends BaseEntity {

  @Id @GeneratedValue(generator = "REVIEW_KEYWORD_SEQ", strategy = GenerationType.SEQUENCE)
  private String reviewKeywordId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="reviewId")
  private Review review;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "keywordId")
  private Keyword keyword;


}
