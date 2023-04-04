package com.simjeom.simjeom.domain.reviews.repository;


import com.simjeom.simjeom.domain.reviews.domain.Review;
import com.simjeom.simjeom.domain.reviews.dto.SearchReviewResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.List;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ReviewRepository {

  @PersistenceContext
  private final EntityManager em;

  // 리뷰 저장
  public void save(Review review){
    em.persist(review);
  }

  // 모든 리뷰 조회하기
  public List<SearchReviewResponse> findAllReveiw(){

    /*
    String jpql = "SELECT r,rk,k FROM Review r "
                  + "LEFT JOIN r.reviewKeyword rk "
                  + "LEFT JOIN rk.keyword k";

     */
    String jpql = "SELECT new com.simjeom.simjeom.domain.reviews.dto.SearchReviewResponse(r.reviewId,r.star,r.comment,r.restaurantNm,r.visitCnt) "
                  +" FROM Review r";
    log.debug("jpql={}",jpql);
    Query query = em.createQuery(jpql);
    return query.getResultList();
  }

  // 동적으로 리뷰


}
