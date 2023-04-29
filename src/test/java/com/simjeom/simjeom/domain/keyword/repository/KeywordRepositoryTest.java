package com.simjeom.simjeom.domain.keyword.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.simjeom.simjeom.domain.keyword.domain.Keyword;
import com.simjeom.simjeom.domain.keyword.service.KeywordService;
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

  @Autowired
  KeywordService keywordService;

  @Rollback(value = false)
  @Transactional
  @Test
  public void 키워드저장(){

    String[] keywords = {"존맛","캐맛있음","가성비","멀다"};
    for (String keywordNm : keywords) {
      String KeywordId = keywordRepository.save(Keyword.builder().keywordNm(keywordNm).build());
      System.out.println("KeywordId = " + KeywordId);
    }
  }

  @Transactional
  @Test
  public void 키워드_중복체크_키워드가_이미_존재하는경우(){
    String keywordId = keywordService.checkKeywordsDuplicated("가성비");
    System.out.println("keywordId = " + keywordId);
  }

}