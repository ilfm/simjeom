package com.simjeom.simjeom.domain.keyword.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
class KeywordRepositoryTest {

  @Autowired
  KeywordRepository keywordRepository;

  @Rollback(value = false)
  @Transactional
  @Test
  public void 키워드저장(){

  }

}