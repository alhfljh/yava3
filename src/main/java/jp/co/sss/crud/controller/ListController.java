package jp.co.sss.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sss.crud.repository.EmployeeRepository;

@Controller
public class ListController {
	@Autowired
	EmployeeRepository repository;

	
	
	@RequestMapping("/list")
	public String list(Model model) {
		model.addAttribute("emp",repository.findAll());
		return "list/list";
	}
}
