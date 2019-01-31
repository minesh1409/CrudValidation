package controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.EmpDao;
import model.EmpModel;

/**
 * Servlet implementation class employee
 */
@WebServlet("/employee")
public class Employee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Employee() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("actionButton");
		System.out.println(action);
				
		String id =  request.getParameter("id") ;
				
		if(action.equals("Add")){
			System.out.println("action "+action);
			EmpModel empModel = new EmpModel();
			EmpDao empdao = new EmpDao();

			String name = request.getParameter("ename");							
			String date = request.getParameter("date")+"/"+request.getParameter("month")+"/"+request.getParameter("year");
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date1 = null;
			try {
				 date1 = (Date)dateFormat.parse(date);
				 System.out.println("date1: "+date1+"\tType: "+date1.getClass().getName());
			} catch (ParseException e) {
				e.printStackTrace();
			}		
			
			String address = request.getParameter("eaddress");
			String gender = request.getParameter("egender");
			String[] skillId = request.getParameterValues("eskill");
			String salary =  request.getParameter("esalary");
			//using array
			/*byte[] skills = new byte[skillId.length];
			for(int i=0; i< skillId.length; i++ ){
				byte j = Byte.parseByte(skillId[i]);
				skills[i]=j;
			}*/
			//using list
			ArrayList<Byte> skillList=new ArrayList<Byte>();
			for(int i=0; i< skillId.length; i++ ){
				skillList.add(Byte.parseByte(skillId[i]));
			}
			
			String bday=  request.getParameter("year")+"/"+request.getParameter("month")+"/"+request.getParameter("date");
			
			empModel.setEmpName(name);
			empModel.setEmpBirthDate(date1);
			empModel.setEmpAddress(address);
			empModel.setEmpGender(Byte.parseByte(gender));
			empModel.setEmpSalary(Double.parseDouble(salary));
			//empModel.setSkills(skills); //using array
			empModel.setSkillList(skillList); //using list
			//empModel.setEmpBirthDate(date1);
			empModel.setBday(bday);
			empdao.insert(empModel);
			response.sendRedirect("index.jsp");
			
		} else if(action.equals("Edit")){
			System.out.println("action "+action);
//			System.out.println(id);			
			EmpDao empDao=new EmpDao();
			EmpModel empModel= empDao.edit(id);
			
			String dd=empModel.getBday().substring(8, 10);
			String MM=empModel.getBday().substring(5, 7);
			String yyyy=empModel.getBday().substring(0,4);
			
			request.setAttribute("emp", empModel);
			request.setAttribute("dd", dd);
			request.setAttribute("MM", MM);
			request.setAttribute("yyyy", yyyy);
			
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("update.jsp");
			dispatcher.forward(request, response);
			
			
		} else if(action.equals("Update")){
			System.out.println("action "+action);
			EmpModel empModel = new EmpModel();
			EmpDao empdao = new EmpDao();

			String name = request.getParameter("ename");							
			//Insert Date Code of Date datatype and set in it in pojo. If need than copy from above add operation
			String address = request.getParameter("eaddress");
			String gender = request.getParameter("egender");
			String[] skillId = request.getParameterValues("eskill");
			String salary =  request.getParameter("esalary");
			//using array of skill. If u need Then copy from above add operation
			//using list
			ArrayList<Byte> skillList=new ArrayList<Byte>();
			for(int i=0; i< skillId.length; i++ ){
				skillList.add(Byte.parseByte(skillId[i]));
			}	
			String bday=  request.getParameter("year")+"/"+request.getParameter("month")+"/"+request.getParameter("date");
			empModel.setEmpId(Long.parseLong(id));
			empModel.setEmpName(name);
			//empModel.setEmpBirthDate(date1);
			empModel.setEmpAddress(address);
			empModel.setEmpGender(Byte.parseByte(gender));
			empModel.setEmpSalary(Double.parseDouble(salary));
			empModel.setSkillList(skillList); //using list
			empModel.setBday(bday);
			empdao.update(empModel);
			response.sendRedirect("index.jsp");
			
		}else if(action.equals("Delete")){
			System.out.println("action "+action);
			System.out.println(id);
			EmpDao empDao=new EmpDao();
			empDao.delete(id);  
			response.sendRedirect("index.jsp");
			}
		
		
		
		
		/*System.out.println(id);
		System.out.println(name);
		System.out.println("date: "+date1+"\tType: "+(date1).getClass().getName());
		System.out.println(address);
		System.out.println(gender);
		for(String s: skill ){
			System.out.println(s);
		}
		System.out.println(salary);*/
		
	}

}
