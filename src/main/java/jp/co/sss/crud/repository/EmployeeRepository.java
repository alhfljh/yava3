package jp.co.sss.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jp.co.sss.crud.entity.Employee;

/** 
 * 社員データのリポジトリ
 * 要するに、社員データをCRUD処理する機能を持ったインターフェース
 */
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	/**
	 *Employeeエンティティの中から、EmpIdとEmpPassを条件にして検索
	 *→ログイン画面にて入力された社員ID（EmpId）と社員（EmpPass）が存在しているか確認するためのもの
	 *
	 * @param empId
	 * @param empPass
	 * @return ログイン画面のHTML（index.html）
	 * 		 または社員一覧表示画面（list.html）
	 */
	@Query("SELECT e FROM Employee e WHERE e.empId = :empId AND e.empPass = :empPass AND e.deleteFlag = 0 ORDER BY e.empId ASC")
	Employee findByEmpIdAndEmpPass(@Param("empId") int empId, @Param("empPass") String empPass);
	
//	@Query("SELECT e FROM Employee e WHERE e.empId = :empId AND e.empPass = :empPass AND e.deleteFlag = 0")
//	Employee findByEmpIdAndEmpPass(int empId, String empPass);

	/** 
	 * 検索
	 * 社員エンティティ（List<Employee>）から、
	 *検索した文字列（EmpName）が含まれている（Containing）レコードを検索
	 *→社員名検索にて、入力した文字列が社員一覧の表にあるかどうかを確認するためのもの
	 *
	 * @param name
	 * @return 社員一覧表示画面（list.html）
	 */
	@Query("SELECT e FROM Employee e WHERE e.empName LIKE %:name% AND e.deleteFlag = 0 ORDER BY e.empId ASC")
	List<Employee> findByEmpNameContaining(@Param("name") String name);

	@Query("SELECT e FROM Employee e WHERE e.empName LIKE %:name% AND e.deleteFlag = 0 ORDER BY e.empId Desc")
	List<Employee> findByEmpNameContainingDesc(@Param("name") String name);
	@Query("SELECT e FROM Employee e WHERE e.deleteFlag = 0 ORDER BY e.empId ASC")
	List<Employee> findAllByOrderByEmpIdAsc();
	@Query("SELECT e FROM Employee e WHERE e.deleteFlag = 0 ORDER BY e.empId DESC")
	List<Employee> findAllByOrderByEmpIdDesc();

	@Query("SELECT e FROM Employee e WHERE e.empId = :empId AND e.deleteFlag = 0 ORDER BY e.empId ASC")
	Employee findByEmpId(@Param("empId") Integer empId);

	
	@Query("SELECT e FROM Employee e WHERE e.deleteFlag = 1 ORDER BY e.empId ASC")
	List<Employee> findDeleteByOrderByEmpIdAsc();
	
	@Query("SELECT COUNT(e) FROM Employee e WHERE e.deleteFlag = 0")
	long countDelete0();
	
	@Query("SELECT COUNT(e) FROM Employee e WHERE e.deleteFlag = 1")
	long countDelete1();
	
	@Query("SELECT COUNT(e) FROM Employee e WHERE e.empName LIKE %:searchWord% AND e.deleteFlag = 0")
	long countByEmpNameContaining(@Param("searchWord") String searchWord);

}
