package jp.co.sss.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.sss.crud.entity.Department;

/**
 * 部署テーブルリポジトリ
 */
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
