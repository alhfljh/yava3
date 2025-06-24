package jp.co.sss.crud.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jp.co.sss.crud.entity.Employee;
import jp.co.sss.crud.form.EmployeeForm;
import jp.co.sss.crud.form.LoginForm;
import jp.co.sss.crud.repository.DepartmentRepository;
import jp.co.sss.crud.repository.EmployeeRepository;

@Controller
public class UpdateController {
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	DepartmentRepository deptRepository;

	HttpSession session;

	@RequestMapping(path = "/update/manage1")
	public String updateUser(@ModelAttribute EmployeeForm employeeForm, HttpSession session, Model model) {
		Integer empId = (Integer) session.getAttribute("userId");
		Employee employee = employeeRepository.findByEmpId(empId);
		model.addAttribute("dept", deptRepository.findAllByOrderByDeptIdAsc());
		if (employee == null) {
			model.addAttribute("messageNot", "お前が存在しません。");
			return "/no_control";
		} else {
			employeeForm.setEmpId(employee.getEmpId());
			employeeForm.setEmpName(employee.getEmpName());
			employeeForm.setGender(employee.getGender());
			employeeForm.setAddress(employee.getAddress());
			employeeForm.setBirthday(employee.getBirthday());
			employeeForm.setAuthority(employee.getAuthority());
			return "/update/update_user";
		}
	}

	@RequestMapping(path = "/update/manage2")
	public String updateManage(@ModelAttribute LoginForm loginForm, HttpServletRequest request, Model model) {
		//		コントローラ側での管理者権限
		//		HttpSession session = request.getSession();
		//		session.getAttribute("manage");
		//		Integer manage = (Integer) session.getAttribute("manage");
		//		if ( manage == 2) {
		//			
		//			return "/update/update_manage";
		//		} else {
		//			model.addAttribute("errMessage", "権限がありません");
		//			return "list/list";
		//		}
		model.addAttribute("emp", employeeRepository.findAllByOrderByEmpIdAsc());
		return "/update/update_manage";

	}

	@RequestMapping(path = "/update/input", method = RequestMethod.POST)
	public String upDate(@ModelAttribute EmployeeForm employeeForm, Model model) {
		model.addAttribute("dept", deptRepository.findAllByOrderByDeptIdAsc());
		Integer empId = employeeForm.getEmpId();
		System.out.println(empId + "ID");
		Employee employee = employeeRepository.findByEmpId(empId);

		if (employee != null) {

			employeeForm.setEmpId(employee.getEmpId());
			employeeForm.setEmpName(employee.getEmpName());
			employeeForm.setGender(employee.getGender());
			employeeForm.setAddress(employee.getAddress());
			employeeForm.setBirthday(employee.getBirthday());
			employeeForm.setAuthority(employee.getAuthority());
			return "update/update_input";

		} else {
			model.addAttribute("errMessage", "社員IDが存在しません。");
			return "list/list";
		}

	}

	@RequestMapping(path = "/update/back", method = RequestMethod.POST)
	public String registInp(@ModelAttribute EmployeeForm employeeForm) {
		return "update/update_input";
	}

	@RequestMapping(path = "/update/checked", method = RequestMethod.POST)
	public String updateCheak(@ModelAttribute EmployeeForm employeeForm, Model model) {
		EmployeeForm employeeform = new EmployeeForm();
		BeanUtils.copyProperties(employeeForm, employeeform);
		model.addAttribute("update_employee", employeeform);
		model.addAttribute("dept", deptRepository.findAllByOrderByDeptIdAsc());
		return "update/update_check";
	}

	@RequestMapping(path = "/update/complete", method = RequestMethod.POST)
	public String updateConpelete(@ModelAttribute EmployeeForm employeeForm, Model model) {
		Integer empId = employeeForm.getEmpId();
		Employee employee = employeeRepository.getReferenceById(empId);
		BeanUtils.copyProperties(employeeForm, employee, "empId");
		employee = employeeRepository.save(employee);
		return "/update/update_complete";
	}

}
