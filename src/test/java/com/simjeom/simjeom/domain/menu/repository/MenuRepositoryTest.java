package com.simjeom.simjeom.domain.menu.repository;

import static org.junit.jupiter.api.Assertions.*;


import com.simjeom.simjeom.domain.menu.domain.Menu;
import com.simjeom.simjeom.domain.menu.service.MenuService;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringRunner.class)
@SpringBootTest
class MenuRepositoryTest {

  @Autowired
  MenuRepository menuRepository;

  @Autowired
  MenuService menuService;

  @Transactional
  @Test
  @Rollback(value = false)
  public void 메뉴등록() {

    String[] menuList = {"닭볶음탕", "김치찌개", "초밥", "육회덮밥", "팟타이", "쌀국수", "라면"};

    for (String menuNm : menuList) {
      menuRepository.save(Menu.builder().menuNm(menuNm).build());
    }
  }

  @Transactional
  @Test
  @Rollback(value = false)
  public void 메뉴중복검사() {
    Optional<Menu> menu = menuRepository.findByName("닭볶음탕");
    menu.ifPresent(v -> {
      System.out.println("v.toString() = " + v.getMenuNm());
    });
  }

  @Transactional
  @Test
  @Rollback(value = false)
  public void 메뉴중복검사_중복되지않은경우() {
    System.out.println(menuService.checkDuplicated("오일파스타"));
  }
  
  @Transactional
  @Test
  @Rollback(value = false)
  public void 메뉴중복검사_중복된경우() {
    System.out.println(menuService.checkDuplicated("오일파스타"));
  }
}
    
