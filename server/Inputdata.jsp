<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%@page import ="java.sql.*,java.util.*" %>
<%@page import ="yourtaste.*" %>
<% 

StringBuffer sql = new StringBuffer();

String uid = request.getParameter("uid");
String fid = request.getParameter("fid");
String score = request.getParameter("score");
/*String uid = Integer.toString(6);
String fid = Integer.toString(9);
String score = Float.toString(3);*/
String userid = uid;
String foodid = null;

Class.forName("org.gjt.mm.mysql.Driver");
Connection conn = null;
Connection conn1 = null;
Connection conn2 = null;

PreparedStatement pstmt = null;
PreparedStatement pstmt1 = null;
PreparedStatement pstmt2 = null;

try {
	conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/yourtaste" , "root", "yourtaste2017");
	pstmt = conn.prepareStatement("insert into yourtaste.userscore(uid,fid,score) values(?,?,?)");
	pstmt.setString(1, uid);
	pstmt.setString(2, fid);
	pstmt.setString(3, score);
	
	pstmt.executeUpdate();

} catch(SQLException e) {
	e.printStackTrace();
} finally {
	if(pstmt != null) {
		try	{
			pstmt.close();
		} catch(Exception e){}
	}
	if(conn != null) {
		try {
			conn.close();
		} catch(Exception e) {}
	}
} 
try {
	conn1 = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/yourtaste" , "root", "yourtaste2017");
	pstmt1 = conn1.prepareStatement("UPDATE yourtaste.userscore SET score = ? WHERE uid=? and fid = ?");
	pstmt1.setString(1, score);
	pstmt1.setString(2, uid);
	pstmt1.setString(3, fid);
	
	pstmt1.executeUpdate();

} catch(SQLException e) {
	e.printStackTrace();
} finally {
	if(pstmt1 != null) {
		try	{
			pstmt1.close();
		} catch(Exception e){}
	}
	if(conn1 != null) {
		try {
			conn1.close();
		} catch(Exception e) {}
	}
} 
try {
	
	conn2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/yourtaste" , "root", "yourtaste2017");
	
	if(Integer.parseInt(fid) == 1) {
		pstmt2 = conn2.prepareStatement("UPDATE yourtaste.foodsim SET foodid1 = ? WHERE userid=?");
		//pstmt2.setString(1, foodid);
		pstmt2.setString(1, score);
		pstmt2.setString(2, userid);
		pstmt2.executeUpdate();
	}
	else if(Integer.parseInt(fid) == 2) {
		pstmt2 = conn2.prepareStatement("UPDATE yourtaste.foodsim SET foodid2 = ? WHERE userid=?");
		//pstmt2.setString(1, fid);
		pstmt2.setString(1, score);
		pstmt2.setString(2, userid);
		pstmt2.executeUpdate();
		}
	else if(Integer.parseInt(fid) == 3) {
		pstmt2 = conn2.prepareStatement("UPDATE yourtaste.foodsim SET foodid3 = ? WHERE userid=?");
		//pstmt2.setString(1, fid);
		pstmt2.setString(1, score);
		pstmt2.setString(2, userid);
		pstmt2.executeUpdate();
		}
	else if(Integer.parseInt(fid) == 4) {
		pstmt2 = conn2.prepareStatement("UPDATE yourtaste.foodsim SET foodid4 = ? WHERE userid=?");
		//pstmt2.setString(1, fid);
		pstmt2.setString(1, score);
		pstmt2.setString(2, userid);
		pstmt2.executeUpdate();
		}
	else if(Integer.parseInt(fid) == 5) {
		pstmt2 = conn2.prepareStatement("UPDATE yourtaste.foodsim SET foodid5 = ? WHERE userid=?");
		//pstmt2.setString(1, fid);
		pstmt2.setString(1, score);
		pstmt2.setString(2, userid);
		pstmt2.executeUpdate();
		}
	else if(Integer.parseInt(fid) == 6) {
		pstmt2 = conn2.prepareStatement("UPDATE yourtaste.foodsim SET foodid6 = ? WHERE userid=?");
		//pstmt2.setString(1, fid);
		pstmt2.setString(1, score);
		pstmt2.setString(2, userid);
		pstmt2.executeUpdate();
		}
	else if(Integer.parseInt(fid) == 7) {
		pstmt2 = conn2.prepareStatement("UPDATE yourtaste.foodsim SET foodid7 = ? WHERE userid=?");
		//pstmt2.setString(1, fid);
		pstmt2.setString(1, score);
		pstmt2.setString(2, userid);
		pstmt2.executeUpdate();
		}
	else if(Integer.parseInt(fid) == 8) {
		pstmt2 = conn2.prepareStatement("UPDATE yourtaste.foodsim SET foodid8 = ? WHERE userid=?");
		//pstmt2.setString(1, fid);
		pstmt2.setString(1, score);
		pstmt2.setString(2, userid);
		pstmt2.executeUpdate();
		}
	else if(Integer.parseInt(fid) == 9) {
		pstmt2 = conn2.prepareStatement("UPDATE yourtaste.foodsim SET foodid9 = ? WHERE userid=?");
		//pstmt2.setString(1, fid);
		pstmt2.setString(1, score);
		pstmt2.setString(2, userid);
		pstmt2.executeUpdate();
		}
	else if(Integer.parseInt(fid) == 10) {
		pstmt2 = conn2.prepareStatement("UPDATE yourtaste.foodsim SET foodid10 = ? WHERE userid=?");
		//pstmt2.setString(1, fid);
		pstmt2.setString(1, score);
		pstmt2.setString(2, userid);
		pstmt2.executeUpdate();
		}
	else if(Integer.parseInt(fid) == 11) {
		pstmt2 = conn2.prepareStatement("UPDATE yourtaste.foodsim SET foodid11 = ? WHERE userid=?");
		//pstmt2.setString(1, fid);
		pstmt2.setString(1, score);
		pstmt2.setString(2, userid);
		pstmt2.executeUpdate();
		}
	else if(Integer.parseInt(fid) == 12) {
		pstmt2 = conn2.prepareStatement("UPDATE yourtaste.foodsim SET foodid12 = ? WHERE userid=?");
		//pstmt2.setString(1, fid);
		pstmt2.setString(1, score);
		pstmt2.setString(2, userid);
		pstmt2.executeUpdate();
		}
	else if(Integer.parseInt(fid) == 13) {
		pstmt2 = conn2.prepareStatement("UPDATE yourtaste.foodsim SET foodid13 = ? WHERE userid=?");
		//pstmt2.setString(1, fid);
		pstmt2.setString(1, score);
		pstmt2.setString(2, userid);
		pstmt2.executeUpdate();
		}
	else if(Integer.parseInt(fid) == 14) {
		pstmt2 = conn2.prepareStatement("UPDATE yourtaste.foodsim SET foodid14 = ? WHERE userid=?");
		//pstmt2.setString(1, fid);
		pstmt2.setString(1, score);
		pstmt2.setString(2, userid);
		pstmt2.executeUpdate();
		}
	else if(Integer.parseInt(fid) == 15) {
		pstmt2 = conn2.prepareStatement("UPDATE yourtaste.foodsim SET foodid15 = ? WHERE userid=?");
		//pstmt2.setString(1, fid);
		pstmt2.setString(1, score);
		pstmt2.setString(2, userid);
		pstmt2.executeUpdate();
		}
	else if(Integer.parseInt(fid) == 16) {
		pstmt2 = conn2.prepareStatement("UPDATE yourtaste.foodsim SET foodid16 = ? WHERE userid=?");
		//pstmt2.setString(1, fid);
		pstmt2.setString(1, score);
		pstmt2.setString(2, userid);
		pstmt2.executeUpdate();
		}
	else if(Integer.parseInt(fid) == 17) {
		pstmt2 = conn2.prepareStatement("UPDATE yourtaste.foodsim SET foodid17 = ? WHERE userid=?");
		//pstmt2.setString(1, fid);
		pstmt2.setString(1, score);
		pstmt2.setString(2, userid);
		pstmt2.executeUpdate();
		}
	else if(Integer.parseInt(fid) == 18) {
		pstmt2 = conn2.prepareStatement("UPDATE yourtaste.foodsim SET foodid18 = ? WHERE userid=?");
		//pstmt2.setString(1, fid);
		pstmt2.setString(1, score);
		pstmt2.setString(2, userid);
		pstmt2.executeUpdate();
		}
	else if(Integer.parseInt(fid) == 19) {
		pstmt2 = conn2.prepareStatement("UPDATE yourtaste.foodsim SET foodid19 = ? WHERE userid=?");
		//pstmt2.setString(1, fid);
		pstmt2.setString(1, score);
		pstmt2.setString(2, userid);
		pstmt2.executeUpdate();
		}
	else if(Integer.parseInt(fid) == 20) {
		pstmt2 = conn2.prepareStatement("UPDATE yourtaste.foodsim SET foodid20 = ? WHERE userid=?");
		//pstmt2.setString(1, fid);
		pstmt2.setString(1, score);
		pstmt2.setString(2, userid);
		pstmt2.executeUpdate();
		}
	
} catch(SQLException e) {
	e.printStackTrace();
} finally {
	if(pstmt2 != null) {
		try	{
			pstmt2.close();
		} catch(Exception e){}
	}
	if(conn2 != null) {
		try {
			conn2.close();
		} catch(Exception e) {}
	}
} 
%>
</body>
</html>