package jp.co.sss.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sss.crud.repository.EmployeeRepository;

/**
 *一覧表示画面の挙動を管理する
 */
@Controller
public class ListController {
	
	/**	エンプロイリポジトリを生成もしくは宣言 */
	@Autowired
	EmployeeRepository repository;

	
	/**
	 * リクエストスコープにエンプロイリポジトリで社員一覧を保存
	 * @param model
	 * @return　社員一覧画面へ遷移
	 */
	@RequestMapping("/list")
	public String list(Model model) {
		model.addAttribute("emp",repository.findAll());
		return "list/list";
	}
}
