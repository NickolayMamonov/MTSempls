package com.MTStasks.employees.controllers;

import com.MTStasks.employees.models.MTSempl;
import com.MTStasks.employees.repo.MTSemplRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmployeeController {
    @Autowired
    private MTSemplRepository MTSemplRepository;
    @GetMapping("/employee")
    public String employee(Model model){
        Iterable<MTSempl> empls = MTSemplRepository.findAll();
        model.addAttribute("empls",empls);
        return "employee-main";
    }
    @GetMapping("/employee/add")
    public String employeeAdd(Model model){

        return "employee-add";
    }
    @PostMapping("/employee/add")
    public String employeePostAdd(@RequestParam String firstname,@RequestParam String lastname,@RequestParam String number,@RequestParam String department, Model model){
        MTSempl post = new MTSempl(firstname,lastname,number,department);
        MTSemplRepository.save(post);

        return "redirect:/employee";
    }

}
