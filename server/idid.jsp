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
String userid = request.getParameter("userid");

Class.forName("org.gjt.mm.mysql.Driver");
Connection conn = null;
ResultSet rs = null;

PreparedStatement pstmt = null;

try {
	conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/yourtaste" , "root", "yourtaste2017");
	String sql = "select * from yourtaste.usertable";
	pstmt = conn.prepareStatement(sql);
	rs = pstmt.executeQuery();
	
	while(rs.next()) {
		String uid = rs.getString("uid");
		String Useremail = rs.getString("userid");
		if(Useremail.equals(userid)) {
			out.print(uid);
		}
	}

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
%>
</body>
</html>