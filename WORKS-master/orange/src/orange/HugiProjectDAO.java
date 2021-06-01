package orange;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;


public class HugiProjectDAO {

	static int idx = 0;
	
	
	public static void insert(HugiVO vo) {
		
		boolean flag = true;
		if(vo.getName().length() == 0) {
			JOptionPane.showMessageDialog(null, "닉네임이 입력되지 않았습니다.");
			flag = false;
		} else if(vo.getpassword().length() == 0) {
			JOptionPane.showMessageDialog(null, "비밀번호가 입력되지 않았습니다.");
			flag = false;
		} else if(vo.gethan().length() == 0) {
			JOptionPane.showMessageDialog(null, "후기가 입력되지 않았습니다.");
			flag = false;
		} else if(vo.getreview().length() == 0) {
			JOptionPane.showMessageDialog(null, "평점이 입력되지 않았습니다.");
			flag = false;
	}
		if(flag) {
			try {
				Connection conn = jpgDB.getMySQLConnection();
				String sql = "INSERT INTO review(nickname, han, review, password) VALUES (?, ?, ?, ?)";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, vo.getName());
				pstmt.setString(2, vo.gethan());
				pstmt.setString(3, vo.getreview());
				pstmt.setString(4, vo.getpassword());
				pstmt.executeUpdate();
				jpgDB.close(pstmt);
				jpgDB.close(conn);
				JOptionPane.showMessageDialog(null, "후기가 입력되었습니다.");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	
	
	
	
	public static ArrayList<HugiVO> select() {
		
		ArrayList<HugiVO> list = null;
		try {
			Connection conn = jpgDB.getMySQLConnection();
			String sql = "SELECT * FROM review ORDER BY idx DESC";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			list = new ArrayList<HugiVO>();
			while(rs.next()) {
				HugiVO vo = new HugiVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setName(rs.getString("nickname"));
				vo.sethan(rs.getString("han"));
				vo.setreview(rs.getString("review"));
				vo.setpassword(rs.getString("password"));
				list.add(vo);
			}
			jpgDB.close(rs);
			jpgDB.close(pstmt);
			jpgDB.close(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
		
	}

	public static HugiVO selectByIdx(int position) {
		
		HugiVO vo = null;
		try {
			Connection conn = jpgDB.getMySQLConnection();
			String sql = "SELECT * FROM review ORDER BY idx DESC LIMIT ?, 1";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, position);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			vo = new HugiVO();
			vo.setIdx(rs.getInt("idx"));
			vo.sethan(rs.getString("password"));
			jpgDB.close(rs);
			jpgDB.close(pstmt);
			jpgDB.close(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
		
	}
	
	
	
	

	public static void delete(int selectedRow, String password) {
		
		if(password.length() == 0) {
			JOptionPane.showMessageDialog(null, "비밀번호가 입력되지 않았습니다.");
		} else {
			
			HugiVO vo = selectByIdx(selectedRow);
			try {
				Connection conn = jpgDB.getMySQLConnection();
				String sql = "DELETE FROM review WHERE idx = ? and password = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, vo.getIdx());
				pstmt.setString(2, password);
				if(pstmt.executeUpdate() == 1) {
					JOptionPane.showMessageDialog(null, "삭제완료!!!");
				} else {
					JOptionPane.showMessageDialog(null, "비밀번호가 올바르지 않습니다.");
				}
				jpgDB.close(pstmt);
				jpgDB.close(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}





	public static void update(int selectedRow, String han, String review, String password) {
			if(password.length() == 0) {
				JOptionPane.showMessageDialog(null, "비밀번호가 입력되지 않았습니다.");
			} else if(han.length() == 0) {
				JOptionPane.showMessageDialog(null, "한줄 평가가 입력되지 않았습니다.");
			} else if(review.equals("~~별점~~")) {
				JOptionPane.showMessageDialog(null, "별점이 선택되지 않았습니다.");
			} else {

				HugiVO vo = selectByIdx(selectedRow);

				try {
					Connection conn = jpgDB.getMySQLConnection();
					String sql = "UPDATE review SET han = ?, review = ? WHERE idx = ? AND password = ?";
					PreparedStatement pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, han);
					pstmt.setString(2, review);
					pstmt.setInt(3, vo.getIdx());
					pstmt.setString(4, password);
					if(pstmt.executeUpdate() == 1) {
						JOptionPane.showMessageDialog(null, vo.getIdx() + "번 글 수정완료!!!");
					} else {
						JOptionPane.showMessageDialog(null, "비밀번호가 올바르지 않습니다.");
					}
					jpgDB.close(pstmt);
					jpgDB.close(conn);
				} catch (SQLException e) {
					e.printStackTrace();
			}
		}
	}


	public static void system(int selectedRow, String password) {
		HugiVO vo = selectByIdx(selectedRow);
			try {
				Connection conn = jpgDB.getMySQLConnection();
				String sql = "DELETE FROM review WHERE idx = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, vo.getIdx());
				if(pstmt.executeUpdate() == 1) {
					JOptionPane.showMessageDialog(null, vo.getIdx() + "번 글 삭제완료!!!");
				} else {
				}
				jpgDB.close(pstmt);
				jpgDB.close(conn);
			} catch (SQLException e) {
		}
	}
}