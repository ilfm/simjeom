package com.simjeom.simjeom.domain.menu.repository;

import static org.junit.jupiter.api.Assertions.*;


import com.simjeom.simjeom.domain.menu.domain.Menu;
import java.util.List;
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

  @Transactional
  @Test
  @Rollback(value = false)
  public void 메뉴등록(){

    String[] menuList = {"닭볶음탕","김치찌개","초밥","육회덮밥","팟타이","쌀국수","라면"};

    for (String menuNm : menuList) {
      menuRepository.save(Menu.builder().menuNm(menuNm).build());
    }
  }

}