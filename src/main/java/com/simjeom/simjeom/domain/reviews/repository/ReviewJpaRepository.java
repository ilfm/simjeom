package com.simjeom.simjeom.domain.reviews.repository;

import com.simjeom.simjeom.domain.reviews.domain.Review;
import com.simjeom.simjeom.domain.reviews.dto.DetailReviewResult;
import com.simjeom.simjeom.domain.reviews.dto.ReviewDto;
import com.simjeom.simjeom.domain.reviews.dto.SearchReviewResult;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Spring jpa 사용하는 repository
 */
public interface ReviewJpaRepository extends JpaRepository<Review, String> {

  /*
   *   [native 쿼리에서 DTO 매핑]
   *   jpql은 new DTO해서 매핑
   *   native query 사용 DTO 매핑할때 value에 쿼리그대로 쓰기 때문에
   *   new 형태로 객체 매핑 x
   *   1.object[]로 반환 받아서 다시 dto매핑
   *   2.Entity에서 @SqlResultSetMapping 사용 등등
   *
   *   [내가 사용한 방법]
   *    getter만 있는 VO interface 생성 하면 바로 받을수 있음
   *
   * */

  /*
   * 리뷰 전체 조회
   * */
  @Query(value =
      "SELECT r.review_id as reviewId"
          + " ,r.star as star"
          + " ,r.comment as comment"
          + " ,rt.restaurant_Nm as restaurantNm"
          + " ,rt.visit_cnt as visitCnt"
          //+ " ,r.visit_dt as visitDt"
          + " ,GROUP_CONCAT(k.keyword_Nm SEPARATOR ', ') as keywords"
          + " FROM Review r"
          + " LEFT JOIN Restaurant rt"
          + " ON r.restaurant_Id = rt.restaurant_Id "
          + " LEFT JOIN review_Keyword rk"
          + " ON r.review_Id = rk.review_Id "
          + " LEFT JOIN keyword k"
          + " ON rk.keyword_Id = k.keyword_Id"
          //+ " WHERE (:keywords is null or k.keyword_nm IN :keywords)"
          + " GROUP BY r.review_id", nativeQuery = true)
  List<SearchReviewResult> findReviewByKeywords();

  /**
   *  상세조회
   */
  @Query("SELECT r.reviewId as reviewId"
      + ", r.star as star"
      + ", r.comment as comment"
      + ", r.menu.menuNm as menuNm"
      //+ ", rk.keyword.keywordNm  as keywordNm"
      //+ ", r.visitDt as visitDt"
      + " FROM Review r"
      + " LEFT JOIN r.menu m"
      //+ " LEFT JOIN r.reviewKeyword rk"
      //+ " LEFT JOIN rk.keyword k"
      + " WHERE r.reviewId = 'RV-59'")
  DetailReviewResult getReviewDetail();

  /*
  * 페치조인은 dto로 반환 안된다.
  * fetch join을 사용하는 이유는 엔티티 상태에서 엔티티 그래프를 참조하기 위해서 사용하는 것입니다.
  *  따라서 당연히 엔티티가 아닌 DTO 상태로 조회하는 것은 불가능합니다.
  *
  * */

  /**
   * 리뷰 검색
   */
  @Query(value =
      "SELECT r.review_id as reviewId"
          + " ,r.star as star"
          + " ,r.comment as comment"
          + " ,rt.restaurant_Nm as restaurantNm"
          + " ,rt.visit_cnt as visitCnt"
          //+ " ,r.visit_dt as visitDt"
          + " , (select GROUP_CONCAT(k.keyword_Nm SEPARATOR ', ')"
            + " FROM review_Keyword rk"
            + " LEFT JOIN keyword k"
            + " ON rk.keyword_Id = k.keyword_Id"
            + " where rk.review_id = r.review_id"
            + " group by rk.review_id ) as keywords"
          + " FROM Review r"
          + " LEFT JOIN Restaurant rt"
          + " ON r.restaurant_Id = rt.restaurant_Id "
          + " LEFT JOIN review_Keyword rk"
          + " ON r.review_Id = rk.review_Id "
          + " LEFT JOIN keyword k"
          + " ON rk.keyword_Id = k.keyword_Id"
          + " WHERE (:keywords is null or k.keyword_nm IN :keywords)"     // keyword가 null 일때는 전체조회
          , nativeQuery = true)
  List<SearchReviewResult> searchReview(@Param("keywords") List<String> keywords);


  /**
   * 리뷰 검색
   */
  @Query(value =
      "SELECT r.review_id as reviewId"
          + " ,r.star as star"
          + " ,r.comment as comment"
          + " ,rt.restaurant_Nm as restaurantNm"
          + " ,rt.visit_cnt as visitCnt"
          //+ " ,r.visit_dt as visitDt"
          + " , (select GROUP_CONCAT(k.keyword_Nm SEPARATOR ', ')"
          + " FROM review_Keyword rk"
          + " LEFT JOIN keyword k"
          + " ON rk.keyword_Id = k.keyword_Id"
          + " where rk.review_id = r.review_id"
          + " group by rk.review_id ) as keywords"
          + " FROM Review r"
          + " LEFT JOIN Restaurant rt"
          + " ON r.restaurant_Id = rt.restaurant_Id "
          + " LEFT JOIN review_Keyword rk"
          + " ON r.review_Id = rk.review_Id "
          + " LEFT JOIN keyword k"
          + " ON rk.keyword_Id = k.keyword_Id"
          + " WHERE (:keywords is null or k.keyword_nm IN :keywords)"     // keyword가 null 일때는 전체조회
      , nativeQuery = true)
  List<SearchReviewResult> findByKeywords(@Param("keywords") List<String> keywords);

  /**
   *  전체 리뷰조회
   */
  @Query("SELECT new com.simjeom.simjeom.domain.reviews.dto.ReviewDto( r.reviewId, r.star as star, r.comment as comment, r.menu.menuNm as menuNm)"
      + " FROM Review r"
      + " LEFT JOIN r.menu m"
  )
  List<ReviewDto> findAllReview();


}

