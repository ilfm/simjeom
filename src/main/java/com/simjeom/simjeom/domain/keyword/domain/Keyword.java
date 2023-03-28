package com.simjeom.simjeom.domain.keyword.domain;


import com.simjeom.simjeom.global.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Builder
@SequenceGenerator(name = "KEYWORD_SEQ", sequenceName = "KEYWORD_SEQ", allocationSize = 1)
public class Keyword extends BaseEntity {

  // 속성
  // 키워드 id, 키워드명, 수정날짜, 등록날짜
  @Id
  @GeneratedValue(generator = "KEYWORD_SEQ", strategy = GenerationType.SEQUENCE)
  @Column(nullable = false)
  private String keywordId;

  @Column(nullable = false)
  private String keywordNm;

  // 생성 메소드
  public static Keyword createKeyword(Keyword keyword){
    return Keyword.builder().keywordNm(keyword.getKeywordNm()).build();
  }
}
