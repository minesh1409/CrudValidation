package model;

import java.util.ArrayList;
import java.util.Date;

public class EmpModel {
	
	private long empId;
	private String empName;
	private Date empBirthDate;
	private String empAddress;
	private byte empGender;
	private byte skillId;
	private Double empSalary;
	
	public long getEmpId() {
		return empId;
	}
	public void setEmpId(long empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public Date getEmpBirthDate() {
		return empBirthDate;
	}
	public void setEmpBirthDate(Date empBirthDate) {
		this.empBirthDate = empBirthDate;
	}
	public String getEmpAddress() {
		return empAddress;
	}
	public void setEmpAddress(String empAddress) {
		this.empAddress = empAddress;
	}
	public byte getEmpGender() {
		return empGender;
	}
	public void setEmpGender(byte empGender) {
		this.empGender = empGender;
	}
	public byte getSkillId() {
		return skillId;
	}
	public void setSkillId(byte skillId) {
		this.skillId = skillId;
	}
	public Double getEmpSalary() {
		return empSalary;
	}
	public void setEmpSalary(Double empSalary) {
		this.empSalary = empSalary;
	}
	
	private String bday;
	public String getBday() {
		return bday;
	}
	public void setBday(String bday) {
		this.bday = bday;
	}

	private byte[] skills;
	public byte[] getSkills() {
		return skills;
	}
	public void setSkills(byte[] skills) {
		this.skills = skills;
	}
	
	private ArrayList<Byte> skillList;
	public ArrayList<Byte> getSkillList() {
		return skillList;
	}
	public void setSkillList(ArrayList<Byte> skillList) {
		this.skillList = skillList;
	}
	
}
