package com.simjeom.simjeom.domain.restaurant.domain;

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
@SequenceGenerator(name = "LUNCH_SEQ", sequenceName = "LUNCH_SEQ", allocationSize = 1)
public class Lunch extends BaseEntity {


  // 식당id, 식당명, 메뉴, 운영일, 등록일, 수정일,
  @Id
  @GeneratedValue(generator = "LUNCH_SEQ", strategy = GenerationType.SEQUENCE)
  private String LunchId;

  @Column(nullable = false)
  private String restaurantNm;

  @Column(nullable = false)
  private String menu;

  // 생성 메소드
  public static Lunch createLunch (Lunch restaurant){
    return Lunch.builder()
        .restaurantNm(restaurant.getRestaurantNm())
        .menu(restaurant.getMenu()).build();
  }
}
