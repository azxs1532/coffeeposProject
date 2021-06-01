package orange;
 
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
 
public class dailyclosing extends JFrame implements ActionListener {
	
	int sum = 0, sum1 = 0;
	 // 값 입력
	String []menu = {"품목","총 판매개수","금 액"};
    DefaultTableModel dtm = new DefaultTableModel(null,menu);
    
    JPanel p1 = new JPanel(new GridLayout(2,1));
    JPanel p2 = new JPanel(new GridLayout(2,1));
    JPanel p3 = new JPanel(new BorderLayout());
    JPanel p4 = new JPanel(new BorderLayout());

    // Talbe 생성
    JTable jTable = new JTable(dtm);

    // 스크롤 페널 생성
    JScrollPane pane = new JScrollPane(jTable);
    
    JButton btn = new JButton("마감 버튼");
    // 총 판매금액 라벨 생성 
    JLabel total = new JLabel();
    // 총 판매갯수 라벨 생성
    JLabel total1 = new JLabel();
    
    public dailyclosing() {
    	
    	setTitle("일일정산");
        setBounds(700,200,500,450);
        add(p1);
        
        total1.setFont(new Font("Dialog",Font.BOLD,30));
        total.setFont(new Font("Dialog",Font.BOLD,30));
        btn.setFont(new Font("Dialog",Font.BOLD,40));
        // JFrame에 페널 추가
        p1.add(pane);
        p1.add(p2);
        p3.add(total1,BorderLayout.NORTH);
        p3.add(total,BorderLayout.CENTER);
        p4.add(btn);
        p2.add(p3);
        p2.add(p4);
 
        // JFrame 화면 보이기
        setVisible(true);
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
        view();
        
        btn.addActionListener(this);
    }
 
    public static void main(String[] args) {
        dailyclosing test = new dailyclosing();
    }
	private void view() {
		
		ArrayList<orderVO> list = daybaDAO.select();
		dtm.setNumRows(0); //model rows 초기화 시키기
		if(list.size()==0) {
		}else {
			String[] rows = new String[3];
			for(orderVO vo : list) {
			rows[0] = vo.getCoffee();
			rows[1] = String.valueOf(vo.getDaliy());
			rows[2] = String.valueOf(vo.getDaliymoney()); 
			sum += vo.getDaliymoney();
			sum1 += vo.getDaliy();
			dtm.addRow(rows);
			}
		}
		total.setText("총 판매 금액 : " + sum);
		total1.setText("총 판매 갯수 : "+ sum1);
//		view();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		daybaDAO dao = new daybaDAO();
		int a = 0; int b = 0;
		switch(e.getActionCommand()) {
		case "마감 버튼" :
			dao.daily(a, b);
			JOptionPane.showMessageDialog(null,"금일 총 판매 수량 : "+sum1+" 판매 금액 : " +sum+" 입니다.");
			view();
			total.setText("총 판매 금액 : " + 0);
			total1.setText("총 판매 갯수 : "+ 0);
			break;
		}
	}

}