package jp.co.sss.crud.form;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;


public class EmpIdForm {
	/** 社員ID */
	@NotNull
	@Max(value = 99999)
	private Integer empId;

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
	
}
