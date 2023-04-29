package com.simjeom.simjeom.domain.keyword.service;

import com.simjeom.simjeom.domain.keyword.domain.Keyword;
import com.simjeom.simjeom.domain.keyword.repository.KeywordRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class KeywordService {

  private final KeywordRepository keywordRepository;

  /*
   * 키워드 중복체크
   * */
  public String checkKeywordsDuplicated(String keywordNm) {

    String keywordId;
    Optional<Keyword> keyword = keywordRepository.findByName(keywordNm);

    if (keyword.isPresent()) {
      keywordId = keyword.get().getKeywordId();
    } else {
      keywordId = keywordRepository.save(Keyword.builder().keywordNm(keywordNm).build());
    }
    return keywordId;
  }

  /*
  *  아이디로 키워드 찾기
  * */
  public Keyword findById(String keywordId){
    return keywordRepository.findById(keywordId);
  }

}
