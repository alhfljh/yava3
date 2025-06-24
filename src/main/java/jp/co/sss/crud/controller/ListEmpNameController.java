package jp.co.sss.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.sss.crud.form.EmployeeForm;
import jp.co.sss.crud.repository.EmployeeRepository;

/** 社員名検索をした時の挙動を管理するコントローラ*/
@Controller
public class ListEmpNameController {

	/** 
	 * EmployeeRepository（従業員リポジトリ）呼び出し
	 * →実質new演算子でオブジェクト生成したのと同じ意味を持つ
	 */
	@Autowired
	EmployeeRepository repository;

	/**
	 * @param employeeForm　社員の登録に使うフォーム
	 * 											  このフォームを社員名検索で再利用している
	 * @param model　メソッドが呼ばれた際に、Modelオブジェクトがこの引数に渡された状態でメソッドが実行される
	 * @return 社員一覧表示画面
	 */
	@RequestMapping(path = "/list/empName", method = RequestMethod.GET)
	public String empName(EmployeeForm employeeForm, Model model) {
		String empName = employeeForm.getEmpName();
		model.addAttribute("isVisible",false);
		/**
		 * 検索窓が空欄、または空白文字だった場合の処理
		 * list.htmlに遷移する
		 * なお、この時、該当する社員が存在しない判定になる
		 * list.htmlには該当する社員が存在しなかった場合の処理が記述されており、
		 * 表は表示されず「該当する社員が存在しません。」と表示される
		 */
		if (empName.isBlank()) {
			return "list/list";
		} else {
			/**
			 * 検索窓にしっかりと文字が入力されていた時の処理
			 * Employeeリポジトリから、
			 * 検索した文字列が含まれているレコードを検索するメソッド（いわゆる曖昧検索）
			 * その結果をemp属性のリクエストスコープに入れる
			 * 遷移先のlist.htmlで、emp属性の結果に対応する社員に絞って表示する
			 */
			model.addAttribute("emp", repository.findByEmpNameContaining(empName));
			model.addAttribute("searchCount", repository.countByEmpNameContaining(empName));
			model.addAttribute("ToTheList", "一覧表示に戻る");
			return "list/list";
		}
	}

}
