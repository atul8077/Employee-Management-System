package com.em.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.em.entity.Employee;
import com.em.service.EmpService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class EmpController {
	
	@Autowired
	private EmpService service;
	
	@GetMapping("/")
	public String home(Model m) {
		
		List<Employee> emp=service.getAllEmp();
		m.addAttribute("emp", emp);
		return "home";
	}
	
	
	@GetMapping("/addemp")
	public String add_emp(Model m) {
		
		m.addAttribute("emp", new Employee());
		return "add_emp";
	}
	

	@PostMapping("/register")
	public String empregister(@Valid  @ModelAttribute("emp") Employee e, BindingResult result , HttpSession session, Model m) {
		
		if(result.hasErrors()) {
			System.out.println("ERROR"+result.toString());
			m.addAttribute("emp",e);
			return "add_emp";
		}
		
		service.addEmp(e);
		session.setAttribute("msg", "Employee Added Successfully !!");
		System.out.println(e);
		return "redirect:/";
	}
	
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable int id, Model m) {
		
		Employee e=service.getEmpById(id);
		m.addAttribute("emp" ,e);
		
		return "edit";
	}
	
	
	@PostMapping("/update")
	public String updateEmp(@ModelAttribute Employee e, HttpSession session) {
		
		service.addEmp(e);
		session.setAttribute("msg","update successfully");
		return "redirect:/";
		
	}
	
	
	@GetMapping("/delete/{id}")
	public String deleteEmp(@PathVariable int id , HttpSession session) {
		
		service.deleteEmp(id);
		session.setAttribute("msg","Delete data successfully");
		
		 return "redirect:/";
	}
}
