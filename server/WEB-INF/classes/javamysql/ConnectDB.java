package javamysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectDB {
	// 싱글톤 패턴으로 사용 하기위 한 코드들
	private static ConnectDB instance = new ConnectDB();

	public static ConnectDB getInstance() {
		return instance;
	}

	public ConnectDB() {

	}

	private String jdbcUrl = "jdbc:mysql://localhost:3306/test";
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
	public String joindb(String id, String pwd) {
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
			sql = "select id from example where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				if (rs.getString("id").equals(id)) { // 이미 아이디가 있는 경우
					sql1 = "UPDATE example SET pw=? where id = ?";
					pstmt1 = conn.prepareStatement(sql1);
					pstmt1.setString(1, pwd);
					pstmt1.setString(2, id);

					pstmt1.executeUpdate();



					returns = "ok";

				}

			} else { // 입력한 아이디가 없는 경우
				sql2 = "insert into example values(?,?) ON DUPLICATE KEY UPDATE id=?, pwd=?";
				pstmt2 = conn.prepareStatement(sql2);
				pstmt2.setString(1, id);
				pstmt2.setString(2, pwd);
				pstmt2.setString(3, id);
				pstmt2.setString(4, pwd);

				pstmt2.executeUpdate();

				returns = "ok";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
			if (conn != null)try {conn.close();} catch (SQLException ex) {}
			if (pstmt2 != null)try {pstmt2.close();} catch (SQLException ex) {}
			if (rs != null)try {rs.close();} catch (SQLException ex) {}
		}
		return returns;
	}



}
