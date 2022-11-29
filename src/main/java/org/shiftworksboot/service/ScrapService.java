package org.shiftworksboot.service;

import lombok.RequiredArgsConstructor;
import org.shiftworksboot.entity.Scrap;
import org.shiftworksboot.repository.ScrapRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ScrapService {

    private final ScrapRepository scrapRepository;

    //스크랩하기
    public void ScrapPost(Scrap scrap){
        scrapRepository.save(scrap);
    }
}
