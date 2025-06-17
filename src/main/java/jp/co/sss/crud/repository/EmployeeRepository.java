package jp.co.sss.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.sss.crud.entity.Employee;

/** 
 * 社員データのリポジトリ
 * 要するに、社員データをCRUD処理する機能を持ったインターフェース
 */
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	/**
	 * findBy○○で、○○を条件とした部分検索を行う
	 *
	 *Employeeエンティティの中から、EmpIdとEmpPassを条件にして検索
	 *→ログイン画面にて入力された社員ID（EmpId）と社員（EmpPass）が存在しているか確認するためのもの
	 *
	 * @param empId
	 * @param empPass
	 * @return ログイン画面のHTML（index.html）
	 * 		 または社員一覧表示画面（list.html）
	 */
	Employee findByEmpIdAndEmpPass(int empId, String empPass);

	/** 社員エンティティ（List<Employee>）から、
	 *検索した文字列（EmpName）が含まれている（Containing）レコードを検索
	 *→社員名検索にて、入力した文字列が社員一覧の表にあるかどうかを確認するためのもの
	 *
	 * @param name
	 * @return 社員一覧表示画面（list.html）
	 */
	List<Employee> findByEmpNameContaining(String name);

}
