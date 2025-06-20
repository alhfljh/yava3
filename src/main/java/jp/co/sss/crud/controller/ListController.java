package jp.co.sss.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sss.crud.repository.DepartmentRepository;
import jp.co.sss.crud.repository.EmployeeRepository;

/**
 * 社員一覧表示画面の挙動を管理する
 * （社員名検索をしていない、デフォルトの状態での社員一覧表示）
 */
@Controller
public class ListController {
	
	/**	エンプロイリポジトリを生成もしくは宣言 */
	@Autowired
	EmployeeRepository repository;
	@Autowired
	DepartmentRepository deptRepository;
	
	/**
	 * リクエストスコープにエンプロイリポジトリで社員一覧を保存
	 * →findAll()なので、全ての社員を表示する処理
	 * 
	 * @param model
	 * @return　社員一覧画面へ遷移
	 */
	@RequestMapping("/list")
	public String list(Model model) {
		model.addAttribute("emp",repository.findAllByOrderByEmpIdAsc());
		model.addAttribute("dept",deptRepository.findAllByOrderByDeptIdAsc());
		
		return "list/list";
	}
}
