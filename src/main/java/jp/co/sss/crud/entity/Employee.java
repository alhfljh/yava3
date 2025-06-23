package jp.co.sss.crud.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

/**
 * 社員テーブルを作成、管理するエンティティ
 */
@Entity
@Table(name = "employee")
public class Employee {
	/**
	 * 社員ID
	 * 主キー社員ID
	 * シーケンスによってIDが自動採番される
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_emp_gen")
	@SequenceGenerator(name = "seq_emp_gen", sequenceName = "seq_emp", allocationSize = 1)
	private Integer empId;

	/**社員のパスワード（文字列型）*/
	@Column
	private String empPass;

	/**社員名（文字列型）*/
	@Column
	private String empName;

	/**社員の性別（数値型）*/
	@Column
	private Integer gender;

	/**社員の住所（文字列型）*/
	@Column
	private String address;

	/**社員の生年月日（yyyy/MM/dd）*/
	@Column
	private Date birthday;

	/**社員の権限（数値型）*/
	@Column
	private Integer authority;

	/** */
	@ManyToOne
	@JoinColumn(name = "dept_id", referencedColumnName = "deptId")
	private Department department;

	/**削除フラグ*/
	@Column
	private Integer deleteFlag;

	/**
	 * 社員IDの取得
	 * @return　社員ID
	 */
	public Integer getEmpId() {
		return empId;
	}

	/**
	 *社員IDのセット 
	 * @param empId
	 */
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	/**
	 * 社員パスワードの取得
	 * @return 社員パスワード
	 */
	public String getEmpPass() {
		return empPass;
	}

	/**
	 * 社員パスワードのセット
	 * @param empPass
	 */
	public void setEmpPass(String empPass) {
		this.empPass = empPass;
	}

	/**
	 *社員名の取得
	 * @return 社員名
	 */
	public String getEmpName() {
		return empName;
	}

	/**
	 * 社員名のセット
	 * @param empName
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}

	/**
	 * 社員の性別の取得
	 * @return 社員の性別
	 */
	public Integer getGender() {
		return gender;
	}

	/**
	 * 社員の性別のセット
	 * @param gender
	 */
	public void setGender(Integer gender) {
		this.gender = gender;
	}

	/**
	 * 社員の住所の取得
	 * @return 社員の住所
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 社員の住所のセット
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 社員の生年月日の取得
	 * @return 社員の生年月日
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * 社員の生年月日のセット
	 * @param birthday
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	/**
	 * 社員の権限の取得
	 * @return 社員の権限
	 */
	public Integer getAuthority() {
		return authority;
	}

	/**
	 * 社員の権限のセット
	 * @param authority
	 */
	public void setAuthority(Integer authority) {
		this.authority = authority;
	}

	/**
	 * 社員の部署名の取得
	 * @return 社員の部署名
	 */
	public Department getDepartment() {
		return department;
	}

	/**
	* 社員の部署名のセット
	* @param department
	*/
	public void setDepartment(Department department) {
		this.department = department;
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

}
