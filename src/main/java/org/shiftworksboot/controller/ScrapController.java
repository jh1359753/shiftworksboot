package org.shiftworksboot.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.shiftworksboot.entity.Scrap;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Log
public class ScrapController {

    //스크랩하기
    /*@PostMapping(value="/scrap")
    public ResponseEntity scrapPost(@RequestBody Scrap scrap, Authentication auth){
        //log.info("scrap..........");

        //로그인한 사람의 emp_id 구하기
        UserDetails ud = (UserDetails)auth.getPrincipal();
        //log.info(ud.getUsername());
        String emp_id = ud.getUsername();
        //vo.setEmp_id(emp_id);

        String str = vo.getPost_regdate();
        String[] regdate = str.split(" ");
        //log.info(regdate[0]);
        vo.setPost_regdate(regdate[0]);

        return service.scrapPost(vo)==1
                ? new ResponseEntity<String>("success", HttpStatus.OK)
                : new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);

    }*/
}
