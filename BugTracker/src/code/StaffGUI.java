package code;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class StaffGUI  {

	private JFrame frame = new JFrame();
	private JTextField staffNameField;
	private JTextField staffIDField;
	private JTextField staffChooseBug;
	private JTable bugTab;
	private DBConnection con;
	private String url = "jdbc:mysql://localhost:3306/bugtracker";
	private String username = "root";
	private String password = "3737Tuonglai";
	private LoginUI ui = new LoginUI(false);
	private JTextField bugUpdateField;
	
	public StaffGUI() {
		
		frame.setBounds(100, 100, 550, 400);
		frame.setTitle("UserGUI");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 514, 339);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Bug", null, panel, null);
		panel.setLayout(null);
		
		JLabel Stafflab1 = new JLabel("Staff Panel - Bug");
		Stafflab1.setBounds(185, 5, 138, 20);
		Stafflab1.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel.add(Stafflab1);
		
		staffNameField = new JTextField();
		staffNameField.setEditable(false);
		staffNameField.setBounds(83, 40, 211, 20);
		panel.add(staffNameField);
		staffNameField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(10, 43, 46, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setBounds(10, 77, 46, 14);
		panel.add(lblNewLabel_1);
		
		staffIDField = new JTextField();
		staffIDField.setEditable(false);
		staffIDField.setBounds(83, 74, 196, 20);
		panel.add(staffIDField);
		staffIDField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Choose Bug");
		lblNewLabel_2.setBounds(10, 117, 69, 14);
		panel.add(lblNewLabel_2);
		
		staffChooseBug = new JTextField();
		staffChooseBug.setBounds(83, 114, 196, 20);
		panel.add(staffChooseBug);
		staffChooseBug.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 179, 489, 121);
		panel.add(scrollPane);
		
		bugTab = new JTable();
		scrollPane.setViewportView(bugTab);
		
		JButton chooseBugBtn = new JButton("Choose");
		chooseBugBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (staffChooseBug.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter information");

				} else {
					try {
						String sql = "UPDATE STAFF SET bug_id = " +"'" + staffChooseBug.getText()+"'" +"WHERE staff_id ="
								+ "'" + staffIDField.getText()+"'" ;
						con = new DBConnection(url, username, password);
						PreparedStatement pst = con.getConnection().prepareStatement(sql);
						pst.executeUpdate();
						pst.close();
						JOptionPane.showMessageDialog(null, "Choose Bug Successfully");
					} catch (Exception n) {
						n.printStackTrace();
					}
				}
			}
		});
		chooseBugBtn.setBounds(56, 145, 89, 23);
		panel.add(chooseBugBtn);
		
		JButton loadBugbtn = new JButton("Load Bug");
		loadBugbtn.setBounds(190, 145, 89, 23);
		panel.add(loadBugbtn);
		
		JButton loadStaffbtn = new JButton("Load Bug Accepted");
		loadStaffbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					String query = "select staff_ID,concat(staff_firstname,' ',staff_middlename,'  ',staff_lastname)as 'name', bug_id "
							+ "from staff where staff_id = '" + staffIDField.getText()+"'";
					DBConnection con2 = new DBConnection(url, username, password);
					PreparedStatement pst = con2.getConnection().prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					bugTab.setModel(DbUtils.resultSetToTableModel(rs));
					rs.close();
					con2.getConnection().close();
				} catch (Exception n) {
					n.printStackTrace();
				}
			}
		});
		loadStaffbtn.setBounds(331, 145, 153, 23);
		panel.add(loadStaffbtn);
		loadBugbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					String query = "select b.bug_ID,b.bug_status as 'status of bug' \r\n"
							+ "from bug b ,staff s,project p\r\n"
							+ "where not exists(select b.bug_ID from staff s where s.bug_ID= b.bug_ID ) and isnull(s.bug_ID) and p.project_ID = (select project_id from staff where staff_id = '" +staffIDField.getText()+"') and b.project_ID = p.project_ID \r\n"
							+ "group by bug_ID\r\n"
							+ "order by bug_ID;";
					DBConnection con2 = new DBConnection(url, username, password);
					PreparedStatement pst = con2.getConnection().prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					bugTab.setModel(DbUtils.resultSetToTableModel(rs));
					rs.close();
					con2.getConnection().close();
				} catch (Exception n) {
					n.printStackTrace();
				}
			}
		});
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Update", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblStaffPanel = new JLabel("Staff Panel - Update Report Status");
		lblStaffPanel.setBounds(116, 11, 323, 20);
		lblStaffPanel.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel_1.add(lblStaffPanel);
		
		bugUpdateField = new JTextField();
		bugUpdateField.setBounds(124, 57, 223, 20);
		panel_1.add(bugUpdateField);
		bugUpdateField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Update Status ");
		lblNewLabel_3.setBounds(10, 60, 85, 14);
		panel_1.add(lblNewLabel_3);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(20, 154, 479, 146);
		panel_1.add(scrollPane_1);
		
		JTable bugAcceptTab = new JTable();
		scrollPane_1.setViewportView(bugAcceptTab);
		
		JButton bugUpdatebtn = new JButton("Update");
		bugUpdatebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (bugUpdateField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter information");

				} else {
					try {
						String sql = "UPDATE Bug,Staff SET bug_status = " +"'" + bugUpdateField.getText()+"'" +"WHERE staff_id ="
								+ "'" + staffIDField.getText()+"' and bug.bug_id = staff.bug_id" ;
						con = new DBConnection(url, username, password);
						PreparedStatement pst = con.getConnection().prepareStatement(sql);
						pst.executeUpdate();
						pst.close();
						JOptionPane.showMessageDialog(null, "Update Successfully");
					} catch (Exception n) {
						n.printStackTrace();
					}
				}
			}
		});
		bugUpdatebtn.setBounds(124, 111, 89, 23);
		panel_1.add(bugUpdatebtn);
		
		JButton btnNewButton = new JButton("Load Accpted Bug");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					String query = "select staff.bug_id,bug_name,bug_status from bug,staff where staff_id = '" + staffIDField.getText() +"' and staff.bug_id = bug.bug_id;";
					DBConnection con2 = new DBConnection(url, username, password);
					PreparedStatement pst = con2.getConnection().prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					bugAcceptTab.setModel(DbUtils.resultSetToTableModel(rs));
					rs.close();
					con2.getConnection().close();
				} catch (Exception n) {
					n.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(258, 111, 149, 23);
		panel_1.add(btnNewButton);
		frame.setVisible(true);
		EnterIDAndName();
	}
	private void EnterIDAndName() {
		staffNameField.setText(ui.getName());
		staffIDField.setText(ui.getID());
	}
}
