package com.simjeom.simjeom.domain.restaurant.repository;

import com.simjeom.simjeom.domain.restaurant.domain.Restaurant;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class RestaurantRepository {

  @PersistenceContext
  private final EntityManager em;

  public RestaurantRepository(EntityManager em){
    this.em =em;
  }

  // 점심 저장
  public String save(String restaurantNm){
    Restaurant restaurant = Restaurant.builder().restaurantNm(restaurantNm).build();
    em.persist(restaurant);
    return restaurant.getRestaurantId();
  }

  // 점심 찾기
  public Restaurant findById(String restaurantId){
    return em.find(Restaurant.class,restaurantId);
  }


}
