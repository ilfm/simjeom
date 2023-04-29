package com.simjeom.simjeom.domain.keyword.repository;

import com.simjeom.simjeom.domain.keyword.domain.Keyword;
import com.simjeom.simjeom.domain.reviews.domain.Review;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class KeywordRepository {

  @PersistenceContext
  private final EntityManager em;

  /*
  * 키워드 저장
  * */
  public String save(Keyword keyword){
    em.persist(keyword);
    return keyword.getKeywordId();
  }

  /*
  * 아이디로 키워드 찾기
  * */
  public Keyword findById(String keywordId){
    return em.find(Keyword.class,keywordId);
  }

  /*
  * 키워드이름으로 키워드 찾기
  * */
  public Optional<Keyword> findByName(String keywordNm){
    Optional<Keyword> keyword = em.createQuery("SELECT k FROM Keyword k WHERE k.keywordNm =: keywordNm",Keyword.class)
        .setParameter("keywordNm",keywordNm)
        .getResultList().stream().findAny();
    return keyword;
  }
}
