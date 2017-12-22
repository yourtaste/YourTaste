package javamysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserData {
	// 싱글톤 패턴으로 사용 하기위 한 코드들
	private static UserData instance = new UserData();

	public static UserData getInstance() {
		return instance;
	}

	public UserData() {

	}

	private String jdbcUrl = "jdbc:mysql://localhost:3306/yourtaste";
	private String dbId = "root";
	private String dbPw = "yourtaste2017";
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private PreparedStatement pstmt1 = null;
	private PreparedStatement pstmt2 = null;
	private ResultSet rs = null;
	private String sql = "";
	private String sql1 = "";
	private String sql2 = "";
	String returns = "";


	// 데이터베이스와 통신하기 위한 코드가 들어있는 메서드
	public String insertdb(String id) {
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);

				//sql2 = "insert into usertable values(?,?)";
        sql2 = "insert into usertable (userid)values(?)";
        //sql2 = "insert into usertable values (SELECT MAX(uid+1) AS m_cn FROM usertable, ?)";
        //sql2 = "INSERT INTO usertable( uid, userid ) SELECT MAX( uid ) + 1, ? FROM usertable";
        //INSERT INTO 테이블명 (COLUMN_LIST)VALUES (COLUMN_LIST에 넣을 VALUE_LIST);

				pstmt2 = conn.prepareStatement(sql2);
				//pstmt2.setString(1, max);
				pstmt2.setString(1, id);

				pstmt2.executeUpdate();

				returns = "ok";
			}
		catch (Exception e) {
			e.printStackTrace();
		}
     finally {
      if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
			if (conn != null)try {conn.close();} catch (SQLException ex) {}
			if (pstmt2 != null)try {pstmt2.close();} catch (SQLException ex) {}
			if (rs != null)try {rs.close();} catch (SQLException ex) {}
		}
		return returns;
	}



}
