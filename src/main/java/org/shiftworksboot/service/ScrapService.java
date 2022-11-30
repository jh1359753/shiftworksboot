package org.shiftworksboot.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.shiftworksboot.dto.ScrapDto;
import org.shiftworksboot.entity.Employee;
import org.shiftworksboot.entity.Post;
import org.shiftworksboot.entity.Scrap;
import org.shiftworksboot.repository.EmployeeRepository;
import org.shiftworksboot.repository.PostRepository;
import org.shiftworksboot.repository.ScrapRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Date;

@Service
@Transactional
@RequiredArgsConstructor
@Log
public class ScrapService {

    private final ScrapRepository scrapRepository;

    private final PostRepository postRepository;

    private final EmployeeRepository employeeRepository;

    //스크랩하기
    public void ScrapPost(ScrapDto scrapDto, String emp_id){
       Post post = postRepository.findById(scrapDto.getPost_id())
                .orElseThrow(EntityNotFoundException::new);
       Scrap scrap = new Scrap();
       log.info(new Date().toString());
       scrap.setScrap_date(new Date().toString());
       scrap.setPost(post);

        Employee employee = employeeRepository.findByEmpId(emp_id);
        scrap.setEmployee(employee);

       scrapRepository.save(scrap);
    }

    //스크랩 문서 조회하기
    public void getScrapList(String emp_id){

    }

}
