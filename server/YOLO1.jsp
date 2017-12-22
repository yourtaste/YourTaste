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
<%@page import ="yourtaste1.*" %>
<%
ItemCluster.DBConnect();

int UserNum;

StringBuffer sql = new StringBuffer();

String uid = request.getParameter("uid");
String fid = request.getParameter("fid");
String score = request.getParameter("score");
String userid = uid;

UserNum = Integer.parseInt(uid);
Class.forName("com.mysql.jdbc.Driver");
Connection conn = null;
Connection conn2 = null;

PreparedStatement pstmt = null;
PreparedStatement pstmt2 = null;

try {
	conn = DriverManager.getConnection("jdbc:mysql://jdbc:mysql://localhost:3306/yourtaste" , "root", "yourtaste2017");
	pstmt = conn.prepareStatement("insert into yourtaste.userscore(uid,fid,score) values(?,?,?)");
	pstmt.setString(1, uid);
	pstmt.setString(2, fid);
	pstmt.setString(3, score);

	
	pstmt.executeUpdate();
	pstmt.close();
	conn.close();
	

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
	
	conn2 = DriverManager.getConnection("jdbc:mysql://jdbc:mysql://localhost:3306/yourtaste" , "root", "yourtaste2017");
	pstmt2 = conn2.prepareStatement("UPDATE yourtaste.foodsim SET ?=? WHERE userid=?");

	pstmt2.setString(1, fid);
	pstmt2.setString(2, score);
	pstmt2.setString(3, userid);
	
	pstmt2.executeUpdate();
	
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


String User[][] = ItemCluster.Usersim(UserNum);
for(int i = 0; i<2; i++) {
	for(int j = 1; j<User[0].length; j++) {
		out.print(User[i][j] + ' ');
	}
	out.print("<br>");
}

String Item[][] = ItemCluster.Itemreco(UserNum);
for(int i = 0; i<2; i++) {
	for(int j = 0; j<Item[0].length; j++) {
		out.print(Item[i][j] + ' ');
	}
	out.print("<br>");
}
%>
</body>
</html>