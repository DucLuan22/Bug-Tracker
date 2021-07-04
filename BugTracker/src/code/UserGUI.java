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
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class UserGUI  {

	private JFrame frame = new JFrame();
	private JTextField unameText;
	private JTextField uidText;
	private JTextField uBugText;
	private JTextField bugDescText;
	private JTextField bugStatusTxt;
	private JTextField uBugIDText;
	private JTable table;
	private String url = "jdbc:mysql://localhost:3306/bugtracker";
	private String username = "root";
	private String password = "3737Tuonglai";
	private LoginUI ui = new LoginUI(false);
	private JTextField projectIDTxt;
	
	public UserGUI() {
		
		frame.setBounds(100, 100, 550, 400);
		frame.setTitle("UserGUI");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel uName = new JLabel("Name");
		uName.setBounds(10, 48, 67, 33);
		frame.getContentPane().add(uName);
		
		unameText = new JTextField();
		unameText.setEditable(false);
		unameText.setBounds(124, 54, 190, 20);
		frame.getContentPane().add(unameText);
		unameText.setColumns(10);
		
		JLabel uID = new JLabel("ID");
		uID.setBounds(10, 92, 46, 14);
		frame.getContentPane().add(uID);
		
		uidText = new JTextField();
		uidText.setEditable(false);
		uidText.setBounds(124, 87, 148, 20);
		frame.getContentPane().add(uidText);
		uidText.setColumns(10);
		
		JLabel bugType = new JLabel("Bug Type");
		bugType.setBounds(10, 121, 67, 14);
		frame.getContentPane().add(bugType);
		
		uBugText = new JTextField();
		uBugText.setBounds(124, 118, 148, 20);
		frame.getContentPane().add(uBugText);
		uBugText.setColumns(10);
		
		JLabel uPanel = new JLabel("User Panel");
		uPanel .setFont(new Font("Tahoma", Font.BOLD, 16));
		uPanel .setBounds(221, 11, 148, 32);
		frame.getContentPane().add(uPanel);
		
		JLabel bugDesc = new JLabel("Bug Description");
		bugDesc.setBounds(10, 181, 104, 25);
		frame.getContentPane().add(bugDesc);
		
		bugDescText = new JTextField();
		bugDescText.setBounds(123, 183, 179, 20);
		frame.getContentPane().add(bugDescText);
		bugDescText.setColumns(10);
		
		JLabel lblBugStatus = new JLabel("Bug Status");
		lblBugStatus.setBounds(319, 92, 67, 14);
		frame.getContentPane().add(lblBugStatus);
		
		bugStatusTxt = new JTextField();
		bugStatusTxt.setText("new(not fix)");
		bugStatusTxt.setEditable(false);
		bugStatusTxt.setBounds(396, 89, 86, 20);
		frame.getContentPane().add(bugStatusTxt);
		bugStatusTxt.setColumns(10);
		
		uBugIDText = new JTextField();
		uBugIDText.setColumns(10);
		uBugIDText.setBounds(124, 149, 148, 20);
		frame.getContentPane().add(uBugIDText);
		
		JLabel lblBugId = new JLabel("Bug ID");
		lblBugId.setBounds(10, 146, 104, 25);
		frame.getContentPane().add(lblBugId);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 248, 514, 102);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton reportBtn = new JButton("Report");
		reportBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (uBugIDText.getText().equals("") || uBugText.getText().equals("")
						|| bugDescText.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter information");
				}
				else {
					try {
						DBConnection con = new DBConnection(url, username, password);
						Statement stm = con.getConnection().createStatement();
						String sql = "INSERT INTO bug(bug_id, user_id,project_id, bug_name, bug_status, bug_date, bug_desc) VALUES "
								+ "('" + uBugIDText.getText() + "','"+ uidText.getText() + "','"+ projectIDTxt.getText()+"','"
								+ uBugText.getText()+ "','"+ bugStatusTxt.getText()+"', curdate(),'"+ bugDescText.getText() +"')";
						stm.execute(sql);
						JOptionPane.showMessageDialog(null, "Added Successfully");
						stm.close();
						bugDescText.setText("");
						uBugText.setText("");
						uBugIDText.setText("");
					} catch (Exception n) {
						n.printStackTrace();
					}
				}
				
		}
			
		});
		reportBtn.setBounds(98, 214, 89, 23);
		frame.getContentPane().add(reportBtn);
		
		JButton loadBtn = new JButton("Load Bug Reported");
		loadBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					String query = "Select bug_id, project_id,bug_name, bug_status, bug_date, "
							+ "bug_desc from user,bug where user.user_id ='" +uidText.getText()+"'and bug.user_id = user.user_id";
					DBConnection con2 = new DBConnection(url, username, password);
					PreparedStatement pst = con2.getConnection().prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					rs.close();
					con2.getConnection().close();
				} catch (Exception n) {
					n.printStackTrace();
				}
			}
		});
		loadBtn.setBounds(242, 214, 148, 23);
		frame.getContentPane().add(loadBtn);
		
		JLabel lblProjectId = new JLabel("Project ID");
		lblProjectId.setBounds(319, 121, 67, 14);
		frame.getContentPane().add(lblProjectId);
		
		projectIDTxt = new JTextField();
		projectIDTxt.setColumns(10);
		projectIDTxt.setBounds(396, 118, 106, 20);
		frame.getContentPane().add(projectIDTxt);
		frame.setVisible(true);
		
		EnterIDAndName();
	}
	private void EnterIDAndName() {
		unameText.setText(ui.getName());
		uidText.setText(ui.getID());
		
	}
}
