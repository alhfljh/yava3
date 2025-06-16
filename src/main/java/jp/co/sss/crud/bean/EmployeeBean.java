package jp.co.sss.crud.bean;

import java.util.Date;

/** 社員の情報を入れる変数の箱を作成、管理するクラス*/
public class EmployeeBean {

	/**社員のID（数値型）を作成*/
	private Integer empId;

	/**社員のパスワード（文字列型）を作成*/
	private String empPass;

	/**社員の名前（文字列型）を作成*/
	private String empName;

	/**社員の性別（数値型）を作成*/
	private Integer gender;

	/**社員の住所（文字列型）を作成*/
	private String address;

	/** 社員の誕生日（日付型）を作成*/
	private Date birthday;

	/** 社員の権限（数値型）を作成*/
	private Integer authority;

	/** 社員の部署ID（数値型）を作成*/
	private Integer deptId;

	/**
	 * 社員IDの取得
	 * 
	 * @return
	 */
	public Integer getEmpId() {
		return empId;
	}

	/**
	 * 社員IDのセット
	 * 
	 * @param empId
	 *            社員ID
	 */
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	/**
	 * 社員のパスワードの取得
	 * 
	 * @return
	 */
	public String getEmpPass() {
		return empPass;
	}

	/**
	 * 社員パスワードのセット
	 * 
	 * @param empPass
	 *       社員パスワード
	 */
	public void setEmpPass(String empPass) {
		this.empPass = empPass;
	}

	/**
	 * 社員名の取得
	 * 
	 * @return
	 */
	public String getEmpName() {
		return empName;
	}

	/**
	 * 社員名のセット
	 * 
	 * @param empName
	 *           社員名
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}

	/**
	 * 社員の性別の取得
	 * 
	 * @return
	 */
	public Integer getGender() {
		return gender;
	}

	/**
	 * 社員の性別のセット
	 * 
	 * @param gender
	 *            性別
	 */
	public void setGender(Integer gender) {
		this.gender = gender;
	}

	/**
	 * 社員の住所の取得
	 * 
	 * @return
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 社員の住所のセット
	 * 
	 * @param address
	 *            住所
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 社員の誕生日の取得
	 * 
	 * @return
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * 社員の誕生日のセット
	 * 
	 * @param birthday
	 *            誕生日
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	/**
	 * 社員の権限の取得
	 * 
	 * @return
	 */
	public Integer getAuthority() {
		return authority;
	}

	/**
	 * 社員の権限のセット
	 * 
	 * @param authority
	 *            社員権限
	 */
	public void setAuthority(Integer authority) {
		this.authority = authority;
	}

	/**
	 * 社員の所属している部署IDの取得
	 * 
	 * @return
	 */
	public Integer getDeptId() {
		return deptId;
	}

	/**
	 * 社員の所属している部署IDのセット
	 * 
	 * @param deptId
	 *            部署ID
	 */
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
}
