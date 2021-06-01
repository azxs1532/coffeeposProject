package orange;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class OrderDAO {
	static Connection conn = null;
	static PreparedStatement pstmt = null;
	static ResultSet rs = null;

	public static ArrayList<orderVO> select() {
		ArrayList<orderVO> list = null;
		try {
			conn = jpgDB.getMySQLConnection();
			String sql = "select * from jpglist order by no DESC";
			pstmt = conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			list = new ArrayList<orderVO>();
			while(rs.next()) {//rs.next() = ResultSet 객체에 다음 데이터가 있나?
				orderVO vo = new orderVO();
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

	public static void insert(orderVO ordervo) {
		try {
			conn = jpgDB.getMySQLConnection();
			String sql = "INSERT INTO jpglist(menulist, money) values (?,?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ordervo.getMenulist());
			pstmt.setInt(2, ordervo.getMoney());
			
			pstmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "주문 완료!!");
			jpgDB.close(conn);
			jpgDB.close(pstmt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	public static void coffee(String coffee) {
		try {
		conn = jpgDB.getMySQLConnection();
		String sql = "SELECT * FROM coffee WHERE menu = ?";
		String sql1 = "UPDATE coffee SET ea = ? WHERE menu = ?";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, coffee);
		rs = pstmt.executeQuery();
		while(rs.next()) {
			orderVO vo = new orderVO();
			vo.setMenu(rs.getString("menu"));
			vo.setWon(rs.getInt("won"));
			vo.setEa(rs.getInt("ea")-1);
		}
		orderVO vo = new orderVO();
		pstmt = conn.prepareStatement(sql1);
		pstmt.setInt(1, vo.getEa());
		pstmt.setString(2, coffee);
		pstmt.executeUpdate();
		
		
		jpgDB.close(conn);
		jpgDB.close(pstmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}