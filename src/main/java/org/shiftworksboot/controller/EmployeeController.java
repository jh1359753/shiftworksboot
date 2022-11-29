package org.shiftworksboot.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.shiftworksboot.dto.EmployeeFormDto;
import org.shiftworksboot.entity.Employee;
import org.shiftworksboot.service.EmployeeService;
import org.springframework.boot.Banner;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/users")
@Controller
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping(value="/login")
    public String loginEmployee(){
        return "customLogin";
    }

    @GetMapping(value="/login/error")
    public String loginError(){
//        model.addAttribute("loginErrorMsg",
//                "사번 또는 비밀번호를 확인해주세요.");
        return "/accessError";
    }

    @GetMapping("/new")
    public String employeeForm(Model model){
        model.addAttribute("employeeFormDto",
                new EmployeeFormDto());
        return "manager/MGR_new";
    }

//    @PostMapping("/new")
//    public String employeeForm(EmployeeFormDto employeeFormDto){
//        Employee employee = Employee.createEmployee(employeeFormDto, passwordEncoder);
//        employeeService.saveEmployee(employee);
//
//        return "redirect:/";
//    }

    @PostMapping(value = "/new")
    public String employeeForm(@Valid EmployeeFormDto employeeFormDto,
                         BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "manager/MGR_new";
        }
        try {
            Employee employee = Employee.createEmployee(employeeFormDto, passwordEncoder);
            employeeService.saveEmployee(employee);
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "manager/MGR_new";

        }
        return "redirect:/";
    }

    @PostMapping("/new/test")
    public void insertEmp(@RequestBody EmployeeFormDto employeeFormDto) {
        employeeService.saveEmployee(Employee.createEmployee(employeeFormDto, passwordEncoder));
    }
}
