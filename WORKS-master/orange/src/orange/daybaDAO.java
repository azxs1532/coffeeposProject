package orange;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class daybaDAO {
	
	static Connection conn = null;
	static PreparedStatement pstmt = null;
	static ResultSet rs = null;
	
	
	public static ArrayList<orderVO> select() {
		ArrayList<orderVO> list = null;
		try {
			conn = jpgDB.getMySQLConnection();
			String sql = "select * from coffee";
			pstmt = conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			list = new ArrayList<orderVO>();
			while(rs.next()) {//rs.next() = ResultSet 객체에 다음 데이터가 있나?
				orderVO vo = new orderVO();
				vo.setCoffee(rs.getString("menu"));
				vo.setDaliy(rs.getInt("daily"));
				vo.setDaliymoney(rs.getInt("money"));
				list.add(vo);
			}
		} catch (SQLException e) {}
		jpgDB.close(conn);
		jpgDB.close(pstmt);
		jpgDB.close(rs);
		return list;
	}
	public static void choice(String c) {
		try {
			conn =jpgDB.getMySQLConnection();
			String sql = "select * from coffee where menu = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, c);
			rs=pstmt.executeQuery();
			while(rs.next()) {//rs.next() = ResultSet 객체에 다음 데이터가 있나?
				orderVO vo = new orderVO();
				vo.setCoffee(rs.getString("menu"));
				vo.setEa2(rs.getInt("daily"));
				vo.setSum(rs.getInt("money"));
			}
		} catch (SQLException e) {}
		jpgDB.close(conn);
		jpgDB.close(pstmt);
		jpgDB.close(rs);
	}
	
	
	public void update(int a, int b, String c) {
		orderVO vo = new orderVO();
		try {
			conn = jpgDB.getMySQLConnection();
			String sql = "update coffee set daily =  ? , money = ? where menu = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, a);
			pstmt.setInt(2, b);
			pstmt.setString(3, c);
			pstmt.executeUpdate();
			} catch (SQLException e) {e.printStackTrace();}
			
			jpgDB.close(conn);
			jpgDB.close(pstmt);
			jpgDB.close(rs);
		
	}
	public void daily(int a, int b) {
		orderVO vo = new orderVO();
		try {
			conn = jpgDB.getMySQLConnection();
			String sql = "update coffee set daily =  ? , money = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, a);
			pstmt.setInt(2, b);
			pstmt.executeUpdate();
			} catch (SQLException e) {e.printStackTrace();}
			
			jpgDB.close(conn);
			jpgDB.close(pstmt);
			jpgDB.close(rs);
		
	}

	
	
	
}