<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="employee" method="post" onsubmit=" return isValid();">
	<!-- <input type="hidden" name="id" id="id"> -->
		<table>
			<tr>
				<td>name</td>
				<td><input type="text" name="ename" id="ename"></td>
				<td><small id="enamemsg" style="display: none;">please
						enter name</small></td>
			</tr>
			<tr>
				<td>email</td>
				<td><input type="email" name="eemail" id="eemail"></td>
				<td><small id="emailmsg" style="display: none;">please
						enter valid email</small></td>
			</tr>
			<tr>
				<td>b-day</td>
				<td><select name="date" id="date">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
				</select> <select name="month" id="month">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
				</select> <select name="year" id="year">
						<option value="2000">2000</option>
						<option value="2001">2001</option>
						<option value="2002">2002</option>
						<option value="2003">2003</option>
						<option value="2004">2004</option>
				</select></td>
				<td><small id="bdaymsg" style="display: none;">please
						age more than 18 year</small></td>
			</tr>
			<tr>
				<td>address</td>
				<td> <textarea rows="3" cols="20" name="eaddress" id="eaddress"> </textarea> </td>
				<td><small id="addressmsg" style="display: none;">max 150 character</small></td>
			</tr>
			<tr>
				<td>gender</td>
				<td><input type="radio" name="egender" value="1" checked>
					Male <input type="radio" name="egender" value="0">
					Female </td>
			</tr>
			<tr>
				<td>skill</td>
				<td><input type="checkbox" name="eskill" value="1">java 
				<input type="checkbox" name="eskill" value="2"> c-sha 
				<input type="checkbox" name="eskill" value="3"> python 
				<input type="checkbox" name="eskill" value="4"> android 
				<input type="checkbox" name="eskill" value="5">ios </td>
				<td><small id="skillmsg" style="display: none;"> select any one</small></td>
			</tr>
			<tr>
				<td>salary</td>
				<td><input type="text" name="esalary" id="esalary"></td>
				<td><small id="salarymsg" style="display: none;"> only number max len up to 9 character</small></td>
			</tr>
			<tr>
				<td><input type="submit" value="Add" name="actionButton"></td>
				<td></td>
			</tr>
		</table>
	</form>
	<script type="text/javascript">
		function isValid() {
			
			submitOK = "true";
			
			var d = new Date();
			
			var bday = document.getElementById("date").value;
			var bmonth = document.getElementById("month").value;
			var byear = document.getElementById("year").value;
			
			var age = d.getFullYear() - byear;
			
            if (d.getMonth()+1 < bmonth ) {
                console.log( " getMonth" + d.getMonth()+1 +" < bmonth "+ bmonth);
                age--;
                }
            if( d.getMonth()+1 == bmonth && d.getDate() < bday){
               console.log( " getMonth = bmonth and getDate < bday " );
                age--;
               }
			var ename = document.getElementById("ename").value;
			var email = document.getElementById("eemail").value.indexOf("@");
			var address = document.getElementById("eaddress").value;
			
			var skill=document.getElementsByName("eskill");
            var okay=false;
            for(var i=0 ;i < skill.length; i++)
                    {
                    if(skill[i].checked)
                        {
                            okay=true;
                            break;
                        }
                    }
			
			var salary = document.getElementById("esalary").value;

			if (ename.length < 1) {
				console.log("false");
				document.getElementById("enamemsg").style.display = "block";
				submitOK = "false";
			} else {
				document.getElementById("enamemsg").style.display = "none";
			}

			if (email == -1) {
				document.getElementById("emailmsg").style.display = "block";
				submitOK = "false";
			} else {
				document.getElementById("emailmsg").style.display = "none";
			}
			
			 if (age < 18) {
					document.getElementById("bdaymsg").style.display = "block";
					submitOK = "false";
				} else {
					document.getElementById("bdaymsg").style.display = "none";
				}
			
			if (address.length > 15){
				document.getElementById("addressmsg").style.display = "block";
				submitOK = "false";
			}else{
				document.getElementById("addressmsg").style.display = "none";
			}
			if (!okay){
				document.getElementById("skillmsg").style.display = "block";
				submitOK = "false";
			}else{
				document.getElementById("skillmsg").style.display = "none";
			}
			if (salary.length > 9 || isNaN(salary)){
				document.getElementById("salarymsg").style.display = "block";
				submitOK = "false";
			}else{
				document.getElementById("salarymsg").style.display = "none";
			}

			if (submitOK == "false") {
				return false;
			}else{
				return true;
			}

		}
	</script>
</body>
</html>