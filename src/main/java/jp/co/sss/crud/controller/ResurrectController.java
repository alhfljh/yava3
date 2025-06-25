package jp.co.sss.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import jp.co.sss.crud.entity.Employee;
import jp.co.sss.crud.form.EmployeeForm;
import jp.co.sss.crud.repository.DepartmentRepository;
import jp.co.sss.crud.repository.EmployeeRepository;

/** ログイン画面周りの挙動を管理するコントローラ*/
@Controller
public class ResurrectController {

	/** 
	 * EmployeeRepository（従業員リポジトリ）呼び出し
	 * →実質new演算子でオブジェクト生成したのと同じ意味を持つ
	 */
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	DepartmentRepository departmentRepository;

	/** 
	 * セッションスコープ（クラスライブラリ）呼び出し
	 * →実質new演算子でオブジェクト生成したのと同じ意味を持つ
	 */
	@Autowired
	HttpSession session;

	@RequestMapping(path = "/resurrect")
	public String resurrect(@ModelAttribute EmployeeForm employeeForm, Model model) {
		model.addAttribute("emp", employeeRepository.findDeleteByOrderByEmpIdAsc());
		return "resurrect/resurrect_input";
	}

	@RequestMapping(path = "/resurrect/comp")
	public String resurrectComp(@ModelAttribute EmployeeForm employeeForm, HttpSession session, Model model) {
		Integer manage = (Integer) session.getAttribute("manage");
		if(manage==1) {
			model.addAttribute("emp", employeeRepository.findDeleteByOrderByEmpIdAsc());
			model.addAttribute("messageNot", "気合が足りん、もっと気持ち見せろ!!!!!");
			return "resurrect/resurrect_input";
//			return "/no_control";
		}
		Integer empId = employeeForm.getEmpId();
		Employee employee = employeeRepository.getReferenceById(empId);
		employee.setDeleteFlag(0);
		employee = employeeRepository.save(employee);
		model.addAttribute("empName", employee.getEmpName());
		return "resurrect/resurrect_complete";
	}

}
