package jp.co.sss.crud.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * 部署名テーブルを作成、管理するエンティティ
 */
@Entity
@Table(name = "department")
public class Department {
	/** 主キー部署ID */
	@Id
	private Integer deptId;

	/** 部署名（文字型）の列を作成*/
	@Column
	private String deptName;

	/**
	 * 部署IDの取得
	 * @return 部署D
	 */
	public Integer getDeptId() {
		return deptId;
	}

	/**
	 * 部署IDのセット
	 * @param deptId
	 */
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	/**
	 * 部署名の取得
	 * @return 部署名
	 */
	public String getDeptName() {
		return deptName;
	}

	/**
	 * 部署名のセット
	 * @param deptName
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

}
