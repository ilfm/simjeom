package com.simjeom.simjeom.domain.menu.service;

import com.simjeom.simjeom.domain.menu.domain.Menu;
import com.simjeom.simjeom.domain.menu.repository.MenuRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MenuService {

  private final MenuRepository menuRepository;

  /*
   * 메뉴 중복 검사
   * */
  @Transactional
  public String checkDuplicated(String menuNm) {

    String menuId = "";
    Optional<Menu> menu = menuRepository.findByName(menuNm);

    if (menu.isPresent()) {
      menuId = menu.get().getMenuId();
    } else {
      menuId = menuRepository.save(Menu.builder().menuNm(menuNm).build());
    }

    return menuId;
  }
}
