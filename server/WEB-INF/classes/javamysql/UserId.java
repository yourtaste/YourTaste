package javamysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserId {
	// 싱글톤 패턴으로 사용 하기위 한 코드들
	private static UserId instance = new UserId();

	public static UserId getInstance() {
		return instance;
	}

	public UserId() {

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
  String uid ="";


	// 데이터베이스와 통신하기 위한 코드가 들어있는 메서드
	public String insertdb(String id) {
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);

				sql2 = "select uid from usertable where userid=?";

				pstmt2 = conn.prepareStatement(sql2);
				pstmt2.setString(1, id);

        rs = pstmt2.executeQuery();

                       if(rs.next()){

                              uid = rs.getString("uid");
                       }


				returns = uid;
        return returns;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
			if (conn != null)try {conn.close();} catch (SQLException ex) {}
			if (pstmt2 != null)try {pstmt2.close();} catch (SQLException ex) {}
			if (rs != null)try {rs.close();} catch (SQLException ex) {}
      return returns;
		}
	}
}
