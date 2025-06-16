package jp.co.sss.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.sss.crud.entity.Employee;

/** 社員データのリポジトリ
 * 要するに、社員データをCRUD処理する機能を持ったインターフェース
 */
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	//findBy○○で、○○を条件とした部分検索を行う
	//Employeeエンティティの中から、EnpIdとEmpPassを条件にして検索
	Employee findByEmpIdAndEmpPass(int empId, String empPass);
	//EmpNameが含まれている
	List<Employee> findByEmpNameContaining(String name);

}
