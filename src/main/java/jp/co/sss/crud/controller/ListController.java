package jp.co.sss.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sss.crud.repository.DepartmentRepository;
import jp.co.sss.crud.repository.EmployeeRepository;

/**
 * 社員一覧表示画面の挙動を管理する
 */
/**
 * 
 */
/**
 * 
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
		model.addAttribute("isVisible",true);
		model.addAttribute("emp",repository.findAllByOrderByEmpIdAsc());
		model.addAttribute("dept",deptRepository.findAllByOrderByDeptIdAsc());
		model.addAttribute("empCount", repository.countDelete0());
		List<String> pages = List.of(
				"",
		        "http://localhost:7779/spring_crud/list/asc",
		        "http://localhost:7779/spring_crud/list/desc"
		    );
		model.addAttribute("pages",pages);
		List<String> ascDesc = List.of("リスト","昇順","降順");
		model.addAttribute("ascDesc",ascDesc);
		return "list/list";
	}
	
	@RequestMapping("/menu")
	public String menu(Model model) {
		model.addAttribute("emp",repository.findAllByOrderByEmpIdAsc());
		model.addAttribute("dept",deptRepository.findAllByOrderByDeptIdAsc());
		//社員リポジトリより、削除フラグが0の社員をカウントし、
		//その結果をempCount属性でリクエストスコープに保存
		//これをmenu.htmlで使用する
		model.addAttribute("empCount", repository.countDelete0());
		List<String> pages = pages();
		List<String> ascDesc = ascDesc();
		model.addAttribute("pages",pages);
		model.addAttribute("ascDesc",ascDesc);
		model.addAttribute("isVisible",true);
		return "menu";
	}
	
	/**
	 *テスト用、絶対に開いてはいけない
	 * @param ？？？？？？？？？？？？？？？？？？？
	 * @return？？？？？？？？？？？？？？
	 */
	@RequestMapping("/menu07")
	public String menu2(Model model) {
		model.addAttribute("emp",repository.findAllByOrderByEmpIdAsc());
		model.addAttribute("dept",deptRepository.findAllByOrderByDeptIdAsc());
		List<String> pages = pages();
		List<String> ascDesc = ascDesc();
		model.addAttribute("pages",pages);
		model.addAttribute("ascDesc",ascDesc);
		model.addAttribute("isVisible",true);
		return "menu2";
	}
	
	
	/**
	 * リクエストスコープにエンプロイリポジトリで社員一覧を保存
	 * 全件検索社員IDを昇順にして社員を表示
	 * @param model
	 * @return 社員検索画面へ遷移
	 */
	@RequestMapping("/search")
	public String search(Model model) {
		model.addAttribute("emp",repository.findAllByOrderByEmpIdAsc());
		return "search";
	}
	
	@RequestMapping("/menu/asc")
	public String menuAsc(Model model) {
		model.addAttribute("emp",repository.findAllByOrderByEmpIdAsc());
		List<String> pages = pages();
		List<String> ascDesc = ascDesc();
		model.addAttribute("empCount", repository.countDelete0());
		model.addAttribute("pages",pages);
		model.addAttribute("ascDesc",ascDesc);
		model.addAttribute("isVisible",true);
		return "menu";
	}
	@RequestMapping("/menu/desc")
	public String menuDesc(Model model) {
		model.addAttribute("emp",repository.findAllByOrderByEmpIdDesc());
		model.addAttribute("empCount", repository.countDelete0());
		List<String> pages = pages();
		List<String> ascDesc = ascDesc();
		model.addAttribute("pages",pages);
		model.addAttribute("ascDesc",ascDesc);
		model.addAttribute("isVisible",true);
		return "menu";
	}
	
	
	
	
	
	
	
	public List<String> ascDesc() {
		List<String> ascDesc = List.of("リスト","昇順","降順");
		return ascDesc;
	}

	public List<String> pages() {
		List<String> pages = List.of(
				"",
		        "http://localhost:7779/spring_crud/menu/asc",
		        "http://localhost:7779/spring_crud/menu/desc"
		    );
		return pages;
	}
	
}
