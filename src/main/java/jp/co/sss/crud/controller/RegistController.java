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
	@Autowired
	DepartmentRepository deptRepository;

	/**
	 * @param employeeForm
	 * @return 社員登録入力画面へ遷移
	 */
	@RequestMapping(path = "/regist/input", method = RequestMethod.GET)
	public String registInput(@ModelAttribute EmployeeForm employeeForm, Model model) {
		employeeForm.setAuthority(1);
		employeeForm.setGender(1);
		//regist_inputで、登録する部署の選択肢を表示するために必要
		model.addAttribute("dept", deptRepository.findAllByOrderByDeptIdAsc());
		return "regist/regist_input";
	}

	/**
	 * @param employeeForm
	 * @return 社員登録入力画面へ遷移
	
	 */
	@RequestMapping(path = "/regist/back", method = RequestMethod.POST)
	public String registInp(@ModelAttribute EmployeeForm employeeForm, Model model) {
		model.addAttribute("dept",deptRepository.findAllByOrderByDeptIdAsc());
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
			//入力画面に戻った時に部署の選択肢が消滅しないように、
			//ここでもう一回部署データをリクエストスコープに保存する脳筋メソッド
			model.addAttribute("dept", deptRepository.findAllByOrderByDeptIdAsc());
			return "regist/regist_input";
		}
		
		//regist_check.htmlで部署データを使うため、リクエストスコープに保存
		model.addAttribute("dept", deptRepository.findAllByOrderByDeptIdAsc());
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
		//regist_checkの登録ボタンの処理だけでは削除フラグがnullになってエラーを吐くため、
		//こっちで削除フラグ0も追加でセットするようにしたという力技
		employee.setDeleteFlag(0);
		employee = repository.save(employee);

		return "regist/regist_complete";

	}
}
