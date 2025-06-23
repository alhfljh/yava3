package jp.co.sss.crud.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpSession;
import jp.co.sss.crud.entity.Department;
import jp.co.sss.crud.form.DeptForm;
import jp.co.sss.crud.repository.DepartmentRepository;
import jp.co.sss.crud.repository.EmployeeRepository;

@Controller
public class DeptController {
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	DepartmentRepository departmentRepository;
	HttpSession session;

	int regist = 0;
	int update = 0;
	@RequestMapping(path = "/dept/dept/dept")
	public String dept(@ModelAttribute DeptForm deptForm, Model model) {
		model.addAttribute("deptall", departmentRepository.findAllByOrderByDeptIdAsc());
		if (regist > 0) {
			model.addAttribute("messege", "IDが重複しています。");
			regist = 0;
		}
		if (update > 0) {	
			model.addAttribute("mess", "IDが存在しません。");
			update = 0;
		}
		return "dept/dept_dept";
	}

	@RequestMapping(path = "/dept/dept/input", method = RequestMethod.POST)
	public String deptInput(/*@Valid */ @ModelAttribute DeptForm deptForm, /*BindingResult result,*/Model model) {
		//		if(result.hasErrors()) {
		//			return "dept/dept";
		//		}
		Integer deptId = deptForm.getDeptId();
		Department department = departmentRepository.findByDeptId(deptId);
		if (department == null) {
			DeptForm deptform = new DeptForm();
			BeanUtils.copyProperties(deptForm, deptform);
			model.addAttribute("dept", deptform);
			return "dept/dept_check";
		}
		regist++;
		//相対パスは、"redirect:dept"で繋がります。
		//よくわからないパスなので絶対パスで記述しています。
		return "redirect:http://localhost:7779/spring_crud/dept/dept/dept";

	}

	@RequestMapping(path = "/dept/back", method = RequestMethod.POST)
	public String deptBack(@ModelAttribute DeptForm deptForm, Model model) {
		model.addAttribute("deptall", departmentRepository.findAllByOrderByDeptIdAsc());
		return "dept/dept_dept";
	}

	@RequestMapping(path = "/dept/complete", method = RequestMethod.POST)
	public String deptComplete(@ModelAttribute DeptForm deptForm) {
		Department department = new Department();
		//		Integer deptId = deptForm.getDeptId();
		//		String deptName = deptForm.getDeptName();
		//		department.setDeptId(deptId);
		//		department.setDeptName(deptName);
		BeanUtils.copyProperties(deptForm, department);
		department = departmentRepository.save(department);
		return "dept/dept_complete";
	}

	@RequestMapping(path = "/dept/dept/update", method = RequestMethod.POST)
	public String deptUpdate(@ModelAttribute DeptForm deptForm, Model model) {
		Integer deptId = deptForm.getDeptId();
		Department department = departmentRepository.findByDeptId(deptId);
		if (department != null) {

			DeptForm deptform = new DeptForm();
			BeanUtils.copyProperties(deptForm, deptform);
			model.addAttribute("dept", deptform);
			return "dept/dept_update";
		}
		update++;
		return "redirect:http://localhost:7779/spring_crud/dept/dept/dept";
	}

	@RequestMapping(path = "/dept/back/update", method = RequestMethod.POST)
	public String deptUpdateBack(@ModelAttribute DeptForm deptForm, Model model) {
		model.addAttribute("deptall", departmentRepository.findAllByOrderByDeptIdAsc());
		return "dept/dept_dept";
	}

	@RequestMapping(path = "/dept/complete/update", method = RequestMethod.POST)
	public String deptUpdateComplete(@ModelAttribute DeptForm deptForm) {
		Department department = new Department();
		BeanUtils.copyProperties(deptForm, department);
		department = departmentRepository.save(department);

		return "dept/dept_update_complete";
	}

}
