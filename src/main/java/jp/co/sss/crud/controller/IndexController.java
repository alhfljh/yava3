package jp.co.sss.crud.controller;

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
import jp.co.sss.crud.bean.EmployeeBean;
import jp.co.sss.crud.entity.Employee;
import jp.co.sss.crud.form.EmployeeForm;
import jp.co.sss.crud.form.LoginForm;
import jp.co.sss.crud.repository.EmployeeRepository;

/** ログイン画面周りの挙動を管理するコントローラ*/
@Controller
public class IndexController {

	/** 
	 * EmployeeRepository（従業員リポジトリ）呼び出し
	 * →実質new演算子でオブジェクト生成したのと同じ意味を持つ
	 */
	@Autowired
	EmployeeRepository employeeRepository;

	/** 
	 * セッションスコープ（クラスライブラリ）呼び出し
	 * →実質new演算子でオブジェクト生成したのと同じ意味を持つ
	 */
	@Autowired
	HttpSession session;

	/**
	 * ログイン画面に遷移する際の挙動について
	 * 念のためセッションスコープの内容を破棄し、index.html（ログイン画面）を表示
	 * 
	 * @param loginForm
	 * @return ログイン画面のHTML（index）
	 */
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String index(@ModelAttribute LoginForm loginForm) {
		session.invalidate();
		return "index";
	}

	/**
	 * ログインボタンが押下されたらこのURLへ遷移
	 * 
	 * @param loginForm　入力された値をLoginForm.javaへ入れる
	 * @param result　BindingResult…入力チェックの判定結果を保存
	 * @param session　入力した値をセッションスコープに保存
	 * @param model　メソッドが呼ばれた際に、Modelオブジェクトがこの引数に渡された状態でメソッドが実行される
	 * @return ログイン画面のHTML（index.html）
	 */
	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public String login(
			@Valid @ModelAttribute LoginForm loginForm,
			BindingResult result, HttpSession session, Model model) {
		if (result.hasErrors()) {
			return "index";
		}

		/*
		 * 入力された社員IDがgetEmpId()に保存されているので、その値を持ってくる
		 *
		 *入力された社員パスワードについても同じ
		 */
		int empId = loginForm.getEmpId();
		String empPass = loginForm.getEmpPass();
		//EmployeeRepositoryで作成した「findByEmpIdAndEmpPass」をここで使用
		Employee employee = employeeRepository.findByEmpIdAndEmpPass(empId, empPass);

		/** 
		 * 入力した社員ID、パスワードに合致する社員がいた場合
		 * エラーメッセージを表示し、もう一回ログイン画面を表示
		 */
		if (employee != null) {
			//employeeBeanのデータの箱を作成（EmployeeBean.javaの設計図を元に）
			EmployeeBean employeeBean = new EmployeeBean();
			//employeeBeanの箱に、EmpId、EmpName、Authorityの値を入れる
			employeeBean.setEmpId(employee.getEmpId());
			employeeBean.setEmpName(employee.getEmpName());
			employeeBean.setAuthority(employee.getAuthority());
			//employeeBeanに入れた値3つを、user属性でリクエストスコープに代入
			session.setAttribute("user", employeeBean);
			session.setAttribute("manage", employeeBean.getAuthority());

			// 一覧へリダイレクト
			return "redirect:/list";

			/** 
			 * 入力した社員ID、パスワードに合致する社員がいなかった場合
			 * エラーメッセージを表示し、もう一回ログイン画面を表示
			 */
		} else {
			model.addAttribute("errMessage", "社員ID、またはパスワードが間違っています。");
			return "index";
		}

	}

	//	追加した↓
	@RequestMapping(path = "/delete/input")
	public String delete(HttpServletRequest request, Model model) {
		
			model.addAttribute("emp", employeeRepository.findAllByOrderByEmpIdAsc());
			return "delete/delete_input";
		
	}

	@RequestMapping(path = "/delete/comp", method = RequestMethod.POST)
	public String deleteComp(@ModelAttribute EmployeeForm employeeForm, Model model) {
		employeeRepository.deleteById(employeeForm.getEmpId());
		return "delete/delete_complete";
	}
	//	追加した↑

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
