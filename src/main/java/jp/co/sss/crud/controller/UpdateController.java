package jp.co.sss.crud.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jp.co.sss.crud.entity.Department;
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
			BeanUtils.copyProperties(employee, employeeForm);
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
		//update_input.htmlで、登録する部署の選択肢を表示するために必要
		model.addAttribute("dept", deptRepository.findAllByOrderByDeptIdAsc());
		Integer empId = employeeForm.getEmpId();
		System.out.println(empId + "ID");
		Employee employee = employeeRepository.findByEmpId(empId);

		if (employee != null) {
			BeanUtils.copyProperties(employee, employeeForm);
			return "update/update_input";

		} else {
			model.addAttribute("errMessage", "社員IDが存在しません。");
			return "list/list";
		}

	}

	@RequestMapping(path = "/update/back", method = RequestMethod.POST)
	public String registInp(@ModelAttribute EmployeeForm employeeForm, Model model) {
		//入力画面に戻った時に部署の選択肢が消滅しないように、
		//ここでもう一回部署データをリクエストスコープに保存する脳筋メソッド
		model.addAttribute("dept", deptRepository.findAllByOrderByDeptIdAsc());
		return "update/update_input";
	}

	@RequestMapping(path = "/update/checked", method = RequestMethod.POST)
	public String updateCheak(@Valid @ModelAttribute EmployeeForm employeeForm, BindingResult result,HttpSession session, Model model) {
		if(result.hasErrors()) {
//			Integer empId = (Integer) session.getAttribute("userId");
			//↓empIdにformのIDを入れる。↑セッションを入れると自分の情報になるためNG
			Integer empId = (Integer) employeeForm.getEmpId();
			Employee employee = employeeRepository.findByEmpId(empId);
			//これでエラーがあった時も部署ドロップダウンメニューがバグらず安心
			model.addAttribute("dept", deptRepository.findAllByOrderByDeptIdAsc());
			BeanUtils.copyProperties(employee, employeeForm);
			return "update/update_user";
		}
		EmployeeForm employeeform = new EmployeeForm();
		BeanUtils.copyProperties(employeeForm, employeeform);
		model.addAttribute("update_employee", employeeform);
		//update_check.htmlで部署データを使うため、リクエストスコープに保存
		model.addAttribute("dept", deptRepository.findAllByOrderByDeptIdAsc());
		return "update/update_check";
	}

	@RequestMapping(path = "/update/complete", method = RequestMethod.POST)
	public String updateConpelete(@ModelAttribute EmployeeForm employeeForm, Model model) {
		Integer empId = employeeForm.getEmpId();
		//以下、dept関連の3行を追加し、登録情報変更時に部署名のみ変更されないバグを修正
		//（ちゃんと部署登録も反映されていたRegistControllerを基に追加　無駄な行があるかもしれない）
		Department department = new Department();
		int deptId = employeeForm.getDeptId();
		department.setDeptId(deptId);
		
		Employee employee = employeeRepository.getReferenceById(empId);
		BeanUtils.copyProperties(employeeForm, employee, "empId");
		//RegistControllerを基にこの行を追加し、部署名のみ変更されないバグを修正
		employee.setDepartment(department);
		employee = employeeRepository.save(employee);
		return "/update/update_complete";
	}

}
