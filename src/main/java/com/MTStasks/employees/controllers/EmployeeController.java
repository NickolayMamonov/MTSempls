package com.MTStasks.employees.controllers;

import com.MTStasks.employees.models.MTSempl;
import com.MTStasks.employees.repo.MTSemplRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class EmployeeController {

    @Autowired
    private MTSemplRepository mtsemplRepository;
    @GetMapping("/employee")
    public String employee(Model model){
        Iterable<MTSempl> empls = mtsemplRepository.findAll();
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
        mtsemplRepository.save(post);

        return "redirect:/employee";
    }
    @GetMapping("/employee/{id}")
    public String employeeDetails(@PathVariable(value = "id") long id, Model model){
        if(mtsemplRepository.existsById(id)){
            return "redirect:/employee";
        }
        Optional<MTSempl> post = mtsemplRepository.findById(id);
        ArrayList<MTSempl> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post",res);
        return "employee-details";
    }
    @GetMapping("/employee/edit")
    public String employeeEdit(Model model){

        return "employee-edit";
    }
   /* @GetMapping("/employee/{id}/edit")
    public String employeeEdit(@PathVariable(value = "id") long id, Model model){
        Optional<MTSempl> empl = mtsemplRepository.findById(id);
        ArrayList<MTSempl> res = new ArrayList<>();
        empl.ifPresent(res::add);
        model.addAttribute("empl",res);
        return "employee-edit";
    }*/
    /*@PostMapping("/employee/{id}/edit")
    public String employeePostEdit(@PathVariable(value = "id") long id,@RequestParam String firstname,@RequestParam String lastname,@RequestParam String number,@RequestParam String department, Model model){
        MTSempl post = mtsemplRepository.findById(id).orElseThrow();
        post.setFirstname(firstname);
        post.setLastname(lastname);
        post.setNumber(number);
        post.setDepartment(department);
        mtsemplRepository.save(post);
        return "redirect:/employee";
    }*/
   /* @PostMapping("/employee/{id}/delete")
    public String employeePostDelete(@PathVariable(value = "id") long id, Model model){
        MTSempl post = mtsemplRepository.findById(id).orElseThrow();
        mtsemplRepository.delete(post);
        mtsemplRepository.save(post);
        return "redirect:/employee";
    }*/

}
