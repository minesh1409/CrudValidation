<%@page import="java.text.ParseException"%>
<%@page import="java.util.Date"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="dbconnection.DbConn"%>
<%@page import="java.sql.ResultSet"%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			<td colspan="3">Click here to Add Record&nbsp; <a
				href="add.jsp"> Click here to Add Record </a> <!-- ${pageContext.request.contextPath}/ -->
			</td>
		</tr>
		<tr>
			<th>NAME</th>
			<th>BirthDate</th>
			<th>Address</th>
			<th>Gender</th>
			<th>Skill</th>
			<th>Salary</th>
			<th>ACTION</th>
		</tr>
		<%
			DbConn dbconn = DbConn.getInstance();
			dbconn.openConnection();
			String empList = "Select * from employee";
			ResultSet rs = dbconn.executeQuery(empList);
			while (rs.next()) {
		%>
		<tr>
			<td><%=rs.getString(2)%></td>
<%-- 			 <td><%=rs.getString(6)%></td>  --%>
			 <td><% DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					Date dt = (Date) dateFormat.parse(rs.getString(6));
					out.println(new SimpleDateFormat("dd/MM/yyyy").format(dt));
				} catch (ParseException e) {
					e.printStackTrace();
				}%></td> 
			<td><%=rs.getString(3)%></td>
			<td>
				<%
					if (rs.getString(4).equals("1")) { out.println("Male");
						} else { out.println("FeMale");
						}
				%>
			</td>
			<td>
				<%
					DbConn dbconn1 = DbConn.getInstance();
						dbconn1.openConnection();
						String skillList = "SELECT skillid FROM employeeskill where employeeskillid=" + rs.getString(1);
						ResultSet rs1 = dbconn.executeQuery(skillList);
						while (rs1.next()) {
							if (rs1.getInt(1) == 1) { out.println("Java ");
							} else if (rs1.getInt(1) == 2) { out.println("C-sha ");
							} else if (rs1.getInt(1) == 3) { out.println("Python ");
							} else if (rs1.getInt(1) == 4) { out.println("Android ");
							} else if (rs1.getInt(1) == 5) { out.println("Ios ");
							} 
						}
						dbconn1.closeConnection();
				%>

			</td>
			<td><%=rs.getInt(5)%></td>
			<td><form action="employee" method="post">
					<input type="hidden" value="<%=rs.getInt(1)%>" name="id" /> <input
						type="submit" value="Edit" name="actionButton"> <input
						type="submit" value="Delete" name="actionButton">
				</form></td>
		</tr>
		<%
			}
			dbconn.closeConnection();
		%>

	</table>


</body>
</html>