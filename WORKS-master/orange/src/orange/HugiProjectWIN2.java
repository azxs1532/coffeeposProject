package orange;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;




public class HugiProjectWIN2 extends JFrame implements ActionListener{
	
	String[] columName = {"닉네임","한 줄 후기","별점"};
	DefaultTableModel model = new DefaultTableModel(columName, 0) {
		public boolean isCellEditable(int i, int c) {
			return false;
		}
	};
	 
	
	JTable table = new JTable(model);
	
	JPanel dataPanel = new JPanel(new BorderLayout());
	
	JPanel labelPanel = new JPanel(new GridLayout(3,1));

	JLabel passwordLabel = new JLabel("비밀번호(4자리) :");
	JLabel nameLabel = new JLabel("닉네임 :");
	JLabel hanLabel = new JLabel("한 줄평 :");
	JLabel memoLabel = new JLabel("");
	
	JPanel fieldPanel = new JPanel(new GridLayout(3,1));
	JTextField nameField = new JTextField();
	JPasswordField passwordField = new JPasswordField(4);
	JTextField memoField = new JTextField();
	
	
	JPanel buttonPanel = new JPanel(new GridLayout(1,4));
	JButton systemButton = new JButton("관리자모드");
	JButton insertButton = new JButton("입력");
	JButton deleteButton = new JButton("삭제");
	JButton updateButton = new JButton("수정");
	
	JPanel addPanel = new JPanel();
	JTextField hanfield = new JTextField(20);
	JTextField namefield = new JTextField(5);
	JButton addButton;
	JLabel buttonLabel;
	JLabel topJLabel;
	JPanel bot = new JPanel();

	JComboBox<String> comboBox;

	
	public HugiProjectWIN2() {
		setTitle("MemoProjectWIN");
		setBounds(500, 100, 1000, 500);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		table.getColumnModel().getColumn(0).setPreferredWidth(20);
		table.getColumnModel().getColumn(1).setPreferredWidth(700);
		table.getColumnModel().getColumn(2).setPreferredWidth(50);
		topJLabel = new JLabel("카페 리뷰");
		topJLabel.setHorizontalAlignment(JLabel.CENTER);
		topJLabel.setFont(new Font("D2Coding", Font.BOLD, 35));
		String[] fruit = {"~~별점~~", "★☆☆☆☆", "★★☆☆☆", "★★★☆☆", "★★★★☆", "★★★★★"};
		comboBox = new JComboBox<String>(fruit);

		JScrollPane mora = new JScrollPane(table);
		add(topJLabel,BorderLayout.NORTH);
		mora.setEnabled(false);
		add(mora, BorderLayout.CENTER);

		bot.add(comboBox);
		bot.add(insertButton);
		bot.add(updateButton);
		bot.add(deleteButton);
		bot.add(systemButton);
		addPanel.add(nameLabel);
		addPanel.add(namefield);
		addPanel.add(passwordLabel);
		addPanel.add(passwordField);
		addPanel.add(hanLabel);
		addPanel.add(hanfield);
		addPanel.add(bot);
		add(addPanel,BorderLayout.SOUTH);
		
		insertButton.addActionListener(this);
		deleteButton.addActionListener(this);
		updateButton.addActionListener(this);
		systemButton.addActionListener(this);
		
		clear();
		view();
		setVisible(true);
		nameField.requestFocus();
	}

	public static void main(String[] args) {
		
		HugiProjectWIN2 window = new HugiProjectWIN2();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String[] fruit = {"★☆☆☆☆", "★★☆☆☆", "★★★☆☆", "★★★★☆", "★★★★★"};

		String name ="",han = "", password = "";
		HugiVO vo = new HugiVO();
		
		switch(e.getActionCommand()) {
			case "입력":
				name = namefield.getText().trim();
				password = passwordField.getText().trim();
				han = hanfield.getText().trim();
				vo.setName(name);
				vo.setpassword(password);
				vo.sethan(han);
				String review = null;
				for(int i = 0; i < 5; i++) {
					if(comboBox.getSelectedItem().equals(fruit[i])) {
						review = fruit[i];
						vo.setreview(review);
					}
				}
				HugiProjectDAO.insert(vo);
				clear();
				view();
				break;
			case "삭제":
				password = passwordField.getText().trim();
				HugiProjectDAO.delete(table.getSelectedRow(), password);
				clear();
				view();
				break;
			case "수정":
				han = hanfield.getText().trim();
				review = comboBox.getSelectedItem()+"";
				password = passwordField.getText().trim();
				HugiProjectDAO.update(table.getSelectedRow(), han,review ,password );
				clear();
				view();
				break;
			case "관리자모드":
				password = passwordField.getText().trim();
				if(password.equals("0000")) {
					HugiProjectDAO.system(table.getSelectedRow(), password);
				}
				
				clear();
				view();
				break;
		}
		
		
		
	}

	private void clear() {
		namefield.setText("");
		passwordField.setText("");
		hanfield.setText("");
		namefield.requestFocus();
	}

	private void view() {
		ArrayList<HugiVO> list = HugiProjectDAO.select();
		for(int i =  model.getRowCount() - 1; i >= 0; i--) {
			model.removeRow(i);
		}
				
		if(list.size() == 0) {
		} else {

			String[] rowData = new String[3];
			for(HugiVO data : list) {
				rowData[0] = data.getName();
				rowData[1] = data.gethan();
				rowData[2] = data.getreview();
				model.addRow(rowData);
			}
		}
	}
}
