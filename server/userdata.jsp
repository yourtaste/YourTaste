<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import = "javamysql.UserData"%>
<%
	// 자바파일이 필요하므로 위 코드처럼 임포트합니다.
%>
<%
	request.setCharacterEncoding("UTF-8");
	String id = request.getParameter("id");
    out.print(id);

	//싱글톤 방식으로 자바 클래스를 불러옵니다.
    UserData user = new UserData();
    user.getInstance();
    String returns = user.insertdb(id);
    out.print(returns);
%>
