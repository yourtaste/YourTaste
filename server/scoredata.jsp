<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import = "javamysql.ScoreData"%>
<%
	// 자바파일이 필요하므로 위 코드처럼 임포트합니다.
%>
<%
	request.setCharacterEncoding("UTF-8");
	String userid = request.getParameter("userid");
	String fid = request.getParameter("fid");
	String score = request.getParameter("score");

	out.print(userid);
	out.print(fid);
	out.print(score);

	//싱글톤 방식으로 자바 클래스를 불러옵니다.
    ScoreData sco = new ScoreData();
    sco.getInstance();
    String returns = sco.insertdb(userid,fid,score);
    out.print(returns);
%>
