package com.simjeom.simjeom.domain.reviews.repository;


import com.simjeom.simjeom.domain.reviews.domain.Review;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ReviewRepository {

  @PersistenceContext
  private final EntityManager em;

  /**
   * 리뷰 저장
   * */
  public String save(Review review){
    em.persist(review);
    return review.getReviewId();
  }

  /**
   * 모든 리뷰 조회
   */
  /*public List<ReviewDTO> findAllReview(){
    String jpql = "SELECT new com.simjeom.simjeom.domain.reviews.dto.ReviewDTO(r.reviewId,r.star,r.comment,rt.restaurantNm,rt.visitCnt) "
                  +" FROM Review r"
                  + " LEFT JOIN r.restaurant rt ";
    Query query = em.createQuery(jpql);
    return query.getResultList();
  }*/

}
