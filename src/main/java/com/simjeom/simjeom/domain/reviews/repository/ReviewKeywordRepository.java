package com.simjeom.simjeom.domain.reviews.repository;


import com.simjeom.simjeom.domain.reviews.domain.ReviewKeyword;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ReviewKeywordRepository {

  @PersistenceContext
  private final EntityManager em;

  /**
   * 리뷰키워드 저장
   * */
  public void save(ReviewKeyword reviewKeyword){
    em.persist(reviewKeyword);
  }

}
