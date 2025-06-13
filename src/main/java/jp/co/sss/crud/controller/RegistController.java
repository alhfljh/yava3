package jp.co.sss.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.sss.crud.form.EmployeeForm;
import jp.co.sss.crud.repository.EmployeeRepository;

@Controller
public class RegistController {
	@Autowired
	EmployeeRepository repository;

	
	
	@RequestMapping(path="/regist/input", method = RequestMethod.GET)
	public String registInput(@ModelAttribute EmployeeForm employeeForm) {
		return "regist/regist_input";
	}
	
	@RequestMapping(path="/regist/check", method = RequestMethod.POST)
	public String registCheck(@ModelAttribute EmployeeForm employeeForm, Model model) {
		model.addAttribute("empPass", employeeForm.getEmpPass());
		
		
		return "regist/regist_check";
	}
	
	@RequestMapping(path="/regist/complete", method = RequestMethod.POST)
	public String registComplete(@ModelAttribute EmployeeForm employeeForm) {
		return "regist/regist_complete";

    }
}
