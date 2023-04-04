package com.simjeom.simjeom.domain.keyword.domain;


import com.simjeom.simjeom.domain.global.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Getter @Setter
@SequenceGenerator(name = "KEYWORD_SEQ", sequenceName = "KEYWORD_SEQ", allocationSize = 1)
public class Keyword extends BaseEntity {

  protected Keyword(){

  }

  @Builder
  public Keyword(String keywordNm) {
    this.keywordNm = keywordNm;
  }

  // 속성
  // 키워드 id, 키워드명, 수정날짜, 등록날짜
  @Id
  @GenericGenerator(name = "SeqGenerator", strategy = "com.simjeom.simjeom.domain.global.SeqGenerator"
      , parameters = {@Parameter(name="SEQ_NAME",value="KEYWORD_SEQ"),
      @Parameter(name="PREFIX",value="KW")})
  @GeneratedValue(generator ="SeqGenerator")
  @Column(nullable = false)
  private String keywordId;

  @Column(nullable = false)
  private String keywordNm;

  // 생성 메소드
  public static Keyword createKeyword(String keywordNm){
    return Keyword.builder().keywordNm(keywordNm).build();
  }
}
