package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbconnection.DbConn;
import model.EmpModel;

public class EmpDao {

	public void insert(EmpModel empModel) {
		DbConn dbConn = DbConn.getInstance();
		try {
			// insert in to employee
			dbConn.openConnection();
			String sql = "insert into employee(name,address,gender,salary,bdate) values ('" + empModel.getEmpName()
					+ "','" + empModel.getEmpAddress() + "'," + empModel.getEmpGender() + "," + empModel.getEmpSalary()
					+ ",'" + empModel.getBday() + "')";
			System.out.println("SQL: " + sql);
			dbConn.executeUpdate(sql);

			// get last id
			ResultSet rs = dbConn.executeQuery("SELECT max(employeeId) FROM employee;");
			while (rs.next()) {
				empModel.setEmpId(rs.getByte(1));
			}
			// using array insert in to skill
			/*
			 * for (byte s : empModel.getSkills()) { empModel.setSkillId(s); sql
			 * = "insert into employeeskill (employeeskillid, skillid) values("
			 * + empModel.getEmpId() + "," + empModel.getSkillId() + ")";
			 * System.out.println("SQL: " + sql); dbConn.executeUpdate(sql); }
			 */

			// using list insert in to skill
			for (byte s : empModel.getSkillList()) {
				empModel.setSkillId(s);
				sql = "insert into employeeskill (employeeskillid, skillid) values(" + empModel.getEmpId() + ","
						+ empModel.getSkillId() + ")";
				System.out.println("SQL: " + sql);
				dbConn.executeUpdate(sql);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConn.closeConnection();
		}

	}

	public EmpModel edit(String id) {
		EmpModel empModel = new EmpModel();

		DbConn dbConn = DbConn.getInstance();
		try {
			dbConn.openConnection();
			String sql = "select * from employee where employeeId=" + id;
			ResultSet resultSet = dbConn.executeQuery(sql);
			while (resultSet.next()) {
				empModel.setEmpId(resultSet.getInt(1));
				empModel.setEmpName(resultSet.getString(2));
				empModel.setEmpAddress(resultSet.getString(3));
				empModel.setEmpGender(resultSet.getByte(4));
				empModel.setEmpSalary(resultSet.getDouble(5));
				empModel.setBday(resultSet.getString(6));
			}
			sql = "SELECT skillid FROM employeeskill where employeeskillid=" + id;
			resultSet = dbConn.executeQuery(sql);
			ArrayList<Byte> skillList = new ArrayList<Byte>();
			while (resultSet.next()) {
				skillList.add(resultSet.getByte(1));
			}
			empModel.setSkillList(skillList);

			/*
			 * System.out.println("list"); System.out.println(skillList);
			 * System.out.println(empModel.getSkillList().contains( (byte) 3));
			 */

			// list convert in array for set arry
			/*
			 * byte[] skills= new byte[skillList.size()]; for(int i=0;
			 * i<skillList.size();i++){ skills[i]=(Byte)skillList.get(i); }
			 * empModel.setSkills(skills); //set valie array
			 */

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConn.closeConnection();
		}

		return empModel;
	}

	public void update(EmpModel empModel) {
		DbConn dbConn = DbConn.getInstance();
		try {
			dbConn.openConnection();
			String sql = "update employee set name='"+empModel.getEmpName()+"', address='"+empModel.getEmpAddress()
					+"', gender=" + empModel.getEmpGender() + ", salary=" + empModel.getEmpSalary() + ", bdate='"
					+ empModel.getBday() + "' where employeeId=" + empModel.getEmpId();
			 System.out.println("SQL: " + sql);
			dbConn.executeUpdate(sql);
			sql = "delete from employeeskill where employeeskillid=" + empModel.getEmpId();
			dbConn.executeUpdate(sql);
			for (byte s : empModel.getSkillList()) {
				sql = "insert into employeeskill (employeeskillid, skillid) values(" + empModel.getEmpId() + "," + s
						+ ")";
				 //System.out.println("SQL: " + sql);
				dbConn.executeUpdate(sql);
			}
		} finally {
			dbConn.closeConnection();
		}
	}

	public void delete(String id) {
		DbConn dbConn = DbConn.getInstance();
		dbConn.openConnection();
		String sql = "delete from employeeskill where employeeskillid="+id;
		dbConn.executeUpdate(sql);
		 sql = "delete from employee where employeeId="+id;
		dbConn.executeUpdate(sql);
		
		dbConn.closeConnection();
	}

}
