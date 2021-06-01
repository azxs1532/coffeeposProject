package orange;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class manager extends JFrame implements ActionListener,Runnable{

	JPanel p1 = new JPanel(new GridLayout(2,1));
	JPanel p2 = new JPanel(new GridLayout());
	JPanel p3 = new JPanel(new GridLayout(1, 2));
	JPanel p4 = new JPanel(new GridLayout());
	JPanel p5 = new JPanel();
	JPanel p6 = new JPanel(new GridLayout(2, 1));
	
	JButton dailly = new JButton("정산");
	JButton plus = new JButton("수정");
	JButton delete = new JButton("제조완료");
	JButton reset = new JButton("초기화");
	
	String[] order = {"주문번호","주문내역","금액"};
	String[] coffee = {"커피메뉴","금액","재고수량"};
	
	DefaultTableModel model = new DefaultTableModel(null,order);
	JTable orderTable = new JTable(model);
	DefaultTableModel model1 = new DefaultTableModel(null,coffee);
	JTable coffeeTable = new JTable(model1);
	JScrollPane orderpane = new JScrollPane(orderTable);
	JScrollPane coffeepane = new JScrollPane(coffeeTable);
	
	public manager() {
		setTitle("관리자용");
		setBounds(50,20,500,500);
		
		p2.add(orderpane);
		p4.add(coffeepane);
		
		p6.add(p5);
		p6.add(dailly);
		p5.add(plus);
		p5.add(delete);
		p5.add(reset);
		
		p3.add(p4);
		p3.add(p6);
		p1.add(p2);
		p1.add(p3);
		add(p1);
		setVisible(true);
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		plus.addActionListener(this);
		delete.addActionListener(this);
		reset.addActionListener(this);
		dailly.addActionListener(this);
		
		coffeeview();
		
	}	
	public static void main(String[] args) {
		manager man = new manager();
		Thread th = new Thread(man);
		th.start();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		int a,b,k;
		String c,v;
		managerDAO DAO = new managerDAO();
		orderVO vv = new orderVO();
		switch(e.getActionCommand()) {
		case "수정":
			k = coffeeTable.getSelectedRow();
			if(k != -1) {
				a = Integer.parseInt((String)model1.getValueAt(k, 1));
				b = Integer.parseInt((String)model1.getValueAt(k, 2));
				c = (String)model1.getValueAt(k, 0);
				DAO.update(a,b,c);
				JOptionPane.showMessageDialog(null, "수정완료");				
			} else {
				JOptionPane.showMessageDialog(null, "수정할 열을 선택하지 않았습니다.");				
			}
			break;
		case "제조완료":
			k = orderTable.getSelectedRow();
			if(k != -1) {
				a = Integer.parseInt((String)model.getValueAt(k, 0));
				v = (String)model.getValueAt(k, 1);
				DAO.delete(a,v);
				JOptionPane.showMessageDialog(null,a+"번 손님 주문하신 <" + v + "> 나왔습니다.");
			} else {
				JOptionPane.showMessageDialog(null, "선택된 행이 없습니다.");
			}
			break;
		case "초기화":
			DAO.reset();
			break;
		case "정산":
			dailyclosing test = new dailyclosing();
			break;
		}
	}
	private void orderview() {
		ArrayList<managerVO> list = managerDAO.select();
		model.setNumRows(0); //model rows 초기화 시키기
		if(list.size()==0) {
		}else {
			for(managerVO vo1 : list) {
				String[] rows = new String[3];
				rows[0] = String.valueOf(vo1.getIdx());
				rows[1] = vo1.getMenulist();
				rows[2] = String.valueOf(vo1.money);
				model.addRow(rows);
			}
		}
	}
	
	private void coffeeview() {
		ArrayList<managerVO> list = managerDAO.select2();
		model.setNumRows(0); //model rows 초기화 시키기
		if(list.size()==0) {
		}else {
			for(managerVO vo1 : list) {
				String[] rows = new String[3];
				rows[0] = vo1.getMenu();
				rows[1] = String.valueOf(vo1.getWon());
				rows[2] = String.valueOf(vo1.getEa());
				model1.addRow(rows);
			}
		}
	}
	
	public void run() {
		while(true) {
			try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
			orderview();
			
		}
		
	}
	
}