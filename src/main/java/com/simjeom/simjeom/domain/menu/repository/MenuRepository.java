package com.simjeom.simjeom.domain.menu.repository;

import com.simjeom.simjeom.domain.menu.domain.Menu;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class MenuRepository {

  @PersistenceContext
  private final EntityManager em;

  public MenuRepository(EntityManager em){
    this.em = em;
  }

  /*
  * 메뉴 저장
  * */
  public String save(Menu menu){
    em.persist(menu);
    return menu.getMenuId();
  }

  /*
  * 아이도로 메뉴 찾기
  * */
  public Menu findById(String menuId){
    return em.find(Menu.class, menuId);
  }

  /*
  * 이름으로 메뉴찾기
  * - https://kudolove.tistory.com/1407
  *   optional로 null 체크
  * */
  public Optional<Menu> findByName(String menuNm){
    Optional<Menu> menu = em.createQuery("SELECT m from Menu m where m.menuNm =:menuNm",Menu.class)
        .setParameter("menuNm",menuNm)
        .getResultList().stream().findAny();
    return menu;
  }


}
