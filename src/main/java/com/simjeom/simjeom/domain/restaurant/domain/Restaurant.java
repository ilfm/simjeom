package com.simjeom.simjeom.domain.restaurant.domain;

import com.simjeom.simjeom.domain.global.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Getter
@Setter
@SequenceGenerator(name = "RESTAURANT_SEQ", sequenceName = "RESTAURANT_SEQ", allocationSize = 1)
public class Restaurant extends BaseEntity {

  protected Restaurant(){

  }
  @Builder
  public Restaurant(String restaurantNm) {
    this.restaurantNm = restaurantNm;
  }

  // 식당id, 식당명, 운영일, 등록일, 수정일, 방문횟수,
  @Id
  @GenericGenerator(name = "SeqGenerator", strategy = "com.simjeom.simjeom.domain.global.SeqGenerator"
                    , parameters = {@Parameter(name="SEQ_NAME",value="RESTAURANT_SEQ"),
                                   @Parameter(name="PREFIX",value="RT")})
  @GeneratedValue(generator ="SeqGenerator")
  private String restaurantId;

  @Column(nullable = false)
  private String restaurantNm;    // 식당명

  @Column(nullable = true)
  @Builder.Default
  @ColumnDefault("1")
  private Integer visitCnt =1;      //방문횟수

  @Column(nullable = true)
  private String remark;       // 기타사항

  // 생성 메소드
  public static Restaurant createRestaurant(String restaurantNm) {

    return Restaurant.builder()
        .restaurantNm(restaurantNm)
        .build();
  }
}
