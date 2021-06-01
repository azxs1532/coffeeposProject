package orange;

import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class managerDAO {

	static Connection conn = null;
	static PreparedStatement pstmt = null;
	static ResultSet rs = null;

	public static ArrayList<managerVO> select() {
		ArrayList<managerVO> list = null;
		try {
			conn = jpgDB.getMySQLConnection();
			String sql = "select * from jpglist order by no DESC";
			pstmt = conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			list = new ArrayList<managerVO>();
			while(rs.next()) {//rs.next() = ResultSet 객체에 다음 데이터가 있나?
				managerVO vo = new managerVO();
				vo.setIdx(rs.getInt("no"));
				vo.setMenulist(rs.getString("menulist"));
				vo.setMoney(rs.getInt("money"));
				list.add(vo);
				
			}
		} catch (SQLException e) {
		}
		jpgDB.close(conn);
		jpgDB.close(pstmt);
		jpgDB.close(rs);
		return list;
	}
	public static ArrayList<managerVO> select2() {
		ArrayList<managerVO> list = null;
		try {
			conn = jpgDB.getMySQLConnection();
			String sql = "select * from coffee";
			pstmt = conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			list = new ArrayList<managerVO>();
			while(rs.next()) {//rs.next() = ResultSet 객체에 다음 데이터가 있나?
				managerVO vo = new managerVO();
				vo.setMenu(rs.getString("menu"));
				vo.setWon(rs.getInt("won"));
				vo.setEa(rs.getInt("ea"));
				list.add(vo);
				}
		} catch (SQLException e) {}
		jpgDB.close(conn);
		jpgDB.close(pstmt);
		jpgDB.close(rs);
		return list;
	}
	public void reset() {
		
		try {
		conn = jpgDB.getMySQLConnection();
		String sql = "delete from jpglist";
		pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (SQLException e) {e.printStackTrace();}
		
		jpgDB.close(conn);
		jpgDB.close(pstmt);
		jpgDB.close(rs);
		
	}
	public void update(int a, int b, String c) {
		managerVO vo = new managerVO();
		try {
			conn = jpgDB.getMySQLConnection();
			String sql = "update coffee set won =  ? , ea = ? where menu = ?";
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
	public void delete(int a, String v) {
		managerVO vo = new managerVO();
		try {
			conn = jpgDB.getMySQLConnection();
			String sql = "delete from jpglist where no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, a);
			pstmt.executeUpdate();
			} catch (SQLException e) {e.printStackTrace();}
			
			jpgDB.close(conn);
			jpgDB.close(pstmt);
			jpgDB.close(rs);
		
	}
}