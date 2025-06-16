package jp.co.sss.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.sss.crud.entity.Employee;

/** 社員データのリポジトリ
 * 要するに、社員データをCRUD処理する機能を持ったインターフェース*/
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	Employee findByEmpIdAndEmpPass(int empId, String empPass);
	List<Employee> findByEmpNameContaining(String name);

}
