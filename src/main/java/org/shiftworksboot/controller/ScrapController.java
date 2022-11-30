package org.shiftworksboot.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.shiftworksboot.dto.ScrapDto;
import org.shiftworksboot.entity.Employee;
import org.shiftworksboot.entity.Scrap;
import org.shiftworksboot.repository.EmployeeRepository;
import org.shiftworksboot.service.ScrapService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Log
public class ScrapController {

    private final EmployeeRepository employeeRepository;

    private final ScrapService scrapService;

    //스크랩하기
    @PostMapping(value="/board/scrap")
    public ResponseEntity scrapPost(@RequestBody ScrapDto scrapDto, Authentication auth){
        //log.info("scrap..........");

        //로그인한 사람의 emp_id 구하기
        UserDetails ud = (UserDetails)auth.getPrincipal();
        log.info(ud.getUsername());
        String emp_id = ud.getUsername();


        scrapService.ScrapPost(scrapDto, emp_id);
        return new ResponseEntity<String>("success", HttpStatus.OK);

    }

    //스크랩 불러오기
    //스크랩한 게시물 리스트 보기
    /*@GetMapping(value = "/scrap/{pageNum}")
    public ModelAndView getScrapList(@PathVariable("pageNum")int pageNum, Authentication auth){

        //log.info("scraplist.........");

        //로그인한 사람만 접근 가능
        UserDetails ud = (UserDetails)auth.getPrincipal();
        //log.info(ud.getUsername());
        String emp_id = ud.getUsername();

        DocumentCriteria cri = new DocumentCriteria();
        cri.setPageNum(pageNum);
        cri.setEmp_id(emp_id);


        ModelAndView mav = new ModelAndView();
        mav.setViewName("/document/DOC_scraplist");
        mav.addObject("pageMaker", service.getScrapListWithPaging(cri));

        return mav;
    }*/

}
