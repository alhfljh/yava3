package jp.co.sss.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.sss.crud.entity.Department;

/**
 * 部署テーブルリポジトリ
 */
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

	List<Department> findAllByOrderByDeptIdAsc();

	Department findByDeptId(Integer deptId);
}
