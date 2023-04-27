package com.simjeom.simjeom.domain.menu.repository;

import com.simjeom.simjeom.domain.menu.domain.Menu;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class MenuRepository {

  @PersistenceContext
  private final EntityManager em;

  public MenuRepository(EntityManager em){
    this.em = em;
  }

  // 메뉴저장
  public String save(Menu menu){
    em.persist(menu);
    return menu.getMenuId();
  }

  // 아이디로 메뉴찾기
  public Menu findById(String menuId){
    return em.find(Menu.class, menuId);
  }

  // 이름으로 메뉴찾기
  // COUNT?
  public Menu findByName(String menuNm){
    String sql = "SELECT ";
    //em.createQuery();
    return em.find(Menu.class, menuNm);
  }


}
