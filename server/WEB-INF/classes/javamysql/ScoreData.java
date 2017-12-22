package javamysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ScoreData {
	// 싱글톤 패턴으로 사용 하기위 한 코드들
	private static ScoreData instance = new ScoreData();

	public static ScoreData getInstance() {
		return instance;
	}

	public ScoreData() {

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
	public String insertdb(String userid, String fid, String score ) {
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);

      int fdiI = Integer.parseInt(fid);
      float scoreF = Float.parseFloat(score);



			sql = "select uid from usertable where userid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();


      //  int uid = rs.getInt("uid");
      String uid = rs.getString("uid");


				sql2 = "insert into userscore values(?,?,?)";
				pstmt2 = conn.prepareStatement(sql2);
        /*
				pstmt2.setS(1, uid);
				pstmt2.setInt(2, fdiI);
				pstmt2.setFloat(3, scoreF);
        */

        pstmt2.setString(1, uid );
				pstmt2.setString(2, fid);
				pstmt2.setString(3, score);


				pstmt2.executeUpdate();

				returns = "ok";

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
