package jp.co.sss.crud.controller;

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
import jp.co.sss.crud.repository.DepartmentRepository;
import jp.co.sss.crud.repository.EmployeeRepository;

/** ログイン画面周りの挙動を管理するコントローラ*/
@Controller
public class DeleteController {

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

	@RequestMapping(path = "/delete/input")
	public String delete(HttpServletRequest request, Model model) {
		model.addAttribute("emp", employeeRepository.findAllByOrderByEmpIdAsc());
		return "delete/delete_input";

	}

	@RequestMapping(path = "/delete/comp", method = RequestMethod.POST)
	public String deleteComp(@ModelAttribute EmployeeForm employeeForm, HttpSession session, Model model) {
		//		物理削除
		//		employeeRepository.deleteById(employeeForm.getEmpId());

		//		論理削除
		Integer empId = employeeForm.getEmpId();
		if (empId == session.getAttribute("userId")) {
			model.addAttribute("messageNot", "もうやめましょうよ!!!命がも˝った˝いた˝い!!!!");
			return "/no_control";

		}
		Employee employee = employeeRepository.getReferenceById(empId);
		employee.setDeleteFlag(1);

		employee = employeeRepository.save(employee);
		return "delete/delete_complete";
	}

	@RequestMapping(path = "/list/delFlg")
	public String listDelete(Model model) {
		model.addAttribute("emp", employeeRepository.findDeleteByOrderByEmpIdAsc());
		model.addAttribute("dept", departmentRepository.findAllByOrderByDeptIdAsc());
		//JPAリポジトリに元々入っている機能であるcount()を使って表に表示している数をカウント　それをempCount属性に入れている
		model.addAttribute("empCount", employeeRepository.countDelete1());
		return "list/list_delete";
	}

}
