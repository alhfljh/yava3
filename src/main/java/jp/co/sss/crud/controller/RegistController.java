package jp.co.sss.crud.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.validation.Valid;
import jp.co.sss.crud.entity.Department;
import jp.co.sss.crud.entity.Employee;
import jp.co.sss.crud.form.EmployeeForm;
import jp.co.sss.crud.repository.DepartmentRepository;
import jp.co.sss.crud.repository.EmployeeRepository;

@Controller
public class RegistController {
	@Autowired
	EmployeeRepository repository;
	DepartmentRepository deptrepository;

	
	
	@RequestMapping(path="/regist/input", method = RequestMethod.GET)
	public String registInput(@ModelAttribute EmployeeForm employeeForm) {
		return "regist/regist_input";
	}
	
	@RequestMapping(path="/regist/inp", method = RequestMethod.POST)
	public String registInp(@ModelAttribute EmployeeForm employeeForm) {
		return "regist/regist_input";
	}
	
	@RequestMapping(path="/regist/check", method = RequestMethod.POST)
	public String registCheck(@Valid @ModelAttribute EmployeeForm employeeForm, BindingResult result,Model model) {
		EmployeeForm employeeform = new EmployeeForm();
		BeanUtils.copyProperties(employeeForm,employeeform);
		model.addAttribute("employee",employeeform);
		
		System.out.println(employeeform.getEmpName()+"aaaa");		
//		System.out.println(employeeform.getDeptName()+"bbbb");		
//		if(result.hasErrors()) {
//			return "regist/regit_input";
//		}
//		if(employeeForm !=null) {
	
		
		return "regist/regist_check";
	
//	}else {
//		return "regist/regist_input";
//		}
	}
	
	@RequestMapping(path="/regist/complete", method = RequestMethod.POST)
	public String registComplete(@ModelAttribute EmployeeForm employeeForm) {
					
		Employee employee = new Employee();
		Department department = new Department();
		int deptId = employeeForm.getDeptId();
		department.setDeptId(deptId);

		BeanUtils.copyProperties(employeeForm,employee);
		employee.setDepartment(department);
		employee = repository.save(employee);
		
		return "regist/regist_complete";

    }
}
