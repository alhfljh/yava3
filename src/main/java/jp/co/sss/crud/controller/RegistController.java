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
	/**エンプロイリポジトリを生成もしくは宣言 */
	@Autowired
	EmployeeRepository repository;
	DepartmentRepository deptrepository;
	
	/**
	 * @param employeeForm
	 * @return 社員登録入力画面へ遷移
	 */
	@RequestMapping(path = "/regist/input", method = RequestMethod.GET)
	public String registInput(@ModelAttribute EmployeeForm employeeForm) {
		employeeForm.setAuthority(1);
		employeeForm.setGender(1);
		return "regist/regist_input";
	}
	
	 
	/**
	 * @param employeeForm
	 * @return 社員登録入力画面へ遷移

	 */
	@RequestMapping(path = "/regist/back", method = RequestMethod.POST)
	public String registInp(@ModelAttribute EmployeeForm employeeForm) {
		return "regist/regist_input";
	}
	
	/**
	 * 入力チェック
	 * 入力画面に入力された情報を表示する。
	 * 再度入力内容をリクエストスコープへ保存する。
	 * @param employeeForm
	 * @param result
	 * @param model
	 * @return 社員登録入力画面へ遷移
	 */
	@RequestMapping(path = "/regist/check", method = RequestMethod.POST)
	public String registCheck(@Valid @ModelAttribute EmployeeForm employeeForm, BindingResult result, Model model) {

		
		if (result.hasErrors()) {
			return "regist/regist_input";	
		}
		
            
		    EmployeeForm employeeform = new EmployeeForm();
			BeanUtils.copyProperties(employeeForm, employeeform);
			model.addAttribute("employee", employeeform);

			return "regist/regist_check";
        }
	

	/**
	 * 入力された情報をエンプロイエンティティに保存
	 * @param employeeForm
	 * @return 社員登録完了画面
	 */
	@RequestMapping(path = "/regist/complete", method = RequestMethod.POST)
	public String registComplete(@ModelAttribute EmployeeForm employeeForm) {

		Employee employee = new Employee();
		Department department = new Department();
		int deptId = employeeForm.getDeptId();
		department.setDeptId(deptId);

		BeanUtils.copyProperties(employeeForm, employee);
		employee.setDepartment(department);
		employee = repository.save(employee);

		return "regist/regist_complete";

	}
}
