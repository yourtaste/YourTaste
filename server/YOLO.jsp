<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import ="java.sql.*,java.util.*" %>
<%@page import ="yourtaste.*" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%
ItemCluster.DBConnect();
String id = request.getParameter("id");

int UserNum;
UserNum = Integer.parseInt(id);
//UserNum = 6;

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
