package jp.co.sss.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jp.co.sss.crud.bean.EmployeeBean;
import jp.co.sss.crud.entity.Employee;
import jp.co.sss.crud.form.LoginForm;
import jp.co.sss.crud.repository.EmployeeRepository;

/** ログイン画面周りの挙動を管理するクラス*/
@Controller
public class IndexController {

	/** 
	 * 従業員リポジトリをDIコンテナに登録
	 * これで、new演算子を記述しなくても従業員リポジトリのオブジェクトを利用できるように
	 */
	@Autowired
	EmployeeRepository employeeRepository;

	/** 
	 * セッションスコープをDIコンテナに登録
	 * これで、new演算子を記述しなくてもセッションスコープを利用できるように
	 */
	@Autowired
	HttpSession session;

	/**
	 * @param loginForm
	 * @return ログイン画面のHTML（index）
	 */
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String index(@ModelAttribute LoginForm loginForm) {
		session.invalidate();
		return "index";
	}

	/**
	 * @param loginForm
	 * @param result
	 * @param session
	 * @param model
	 * @return ログイン画面のHTML（index.html）
	 */
	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public String login(
			@Valid @ModelAttribute LoginForm loginForm,
			BindingResult result, HttpSession session, Model model) {
		if (result.hasErrors()) {
			return "index";
		}

		int empId = loginForm.getEmpId();
		String empPass = loginForm.getEmpPass();
		Employee employee = employeeRepository.findByEmpIdAndEmpPass(empId, empPass);

		if (employee != null) {
			EmployeeBean employeeBean = new EmployeeBean();
			employeeBean.setEmpId(employee.getEmpId());
			employeeBean.setEmpName(employee.getEmpName());
			employeeBean.setAuthority(employee.getAuthority());
			session.setAttribute("user", employeeBean);
			// 一覧へリダイレクト
			return "redirect:/list";

		} else {
			model.addAttribute("errMessage", "社員ID、またはパスワードが間違っています。");
			return "index";
		}

	}

	/**
	 * 各画面でログアウトボタンが押下されたらこのURLへ遷移
	 * セッションスコープに保存された情報を破棄し、"/"のURL（ログイン画面）へリダイレクト
	 * 
	 * @return ログイン画面（リダイレクト）
	 */
	@RequestMapping(path = "/logout", method = RequestMethod.GET)
	public String logout() {
		// セッションの破棄
		session.invalidate();
		return "redirect:/";
	}

}
