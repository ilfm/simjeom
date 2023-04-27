package com.simjeom.simjeom.domain.menu.domain;

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
@SequenceGenerator(name = "MENU_SEQ", sequenceName = "MENU_SEQ", allocationSize = 1)
public class Menu extends BaseEntity {

  protected Menu(){

  }
  @Builder
  public Menu(String menuNm) {
    this.menuNm = menuNm;
  }

  // 메뉴id, 메뉴명, 등록일, 수정일, 먹은횟수,
  @Id
  @GenericGenerator(name = "SeqGenerator", strategy = "com.simjeom.simjeom.domain.global.SeqGenerator"
                    , parameters = {@Parameter(name="SEQ_NAME",value="MENU_SEQ"),
                                   @Parameter(name="PREFIX",value="MN")})
  @GeneratedValue(generator ="SeqGenerator")
  private String menuId;

  @Column(nullable = false)
  private String menuNm;         // 메뉴명

  @Column(nullable = true)
  @Builder.Default
  @ColumnDefault("0")
  private Integer eatCnt = 0;   // 먹은횟수

  @Column(nullable = true)
  private String remark;       // 기타사항

  // 생성 메소드
  public static Menu createMenu(String menuNm) {
    return Menu.builder()
        .menuNm(menuNm)
        .build();
  }
}
