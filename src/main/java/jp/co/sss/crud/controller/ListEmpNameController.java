package jp.co.sss.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.sss.crud.form.EmployeeForm;
import jp.co.sss.crud.repository.EmployeeRepository;

@Controller
public class ListEmpNameController {

	@Autowired
	EmployeeRepository repository;

	@RequestMapping(path = "/list/empName", method = RequestMethod.GET)
	public String empName(EmployeeForm employeeForm, Model model) {
		String empName = employeeForm.getEmpName();

		if (empName.isBlank()) {
			return "list/list";
		} else {
			model.addAttribute("emp", repository.findByEmpNameContaining(empName));
			return "list/list";
		}
		//System.out.println(empName+"エラーやで");
		//List<Employee> emp = repository.findByEmpNameContaining(empName);

	}

}
