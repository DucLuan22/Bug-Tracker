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

import javax.swing.DefaultButtonModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;
import javax.swing.JTabbedPane;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class AdminGUI {

	private JFrame frame = new JFrame();
	private DBConnection con;
	private String url = "jdbc:mysql://localhost:3306/bugtracker";
	private String username = "root";
	private String password = "3737Tuonglai";
	private JTextField name_Field;
	private JTextField id_Field;
	private JTextField proName_Field;
	private JTextField proId_Field;
	private JTextPane proDesc_Field;
	private JTextField userNameField;
	private JTextField userIDField;
	private JPasswordField userRePassField;
	private JPasswordField userPassField;
	private JTextField staffNameField;
	private JTextField staffIDField;
	private JPasswordField staffPassField;
	private JPasswordField staffRePassField;
	private LoginUI ui = new LoginUI(false);
	private JTable table_product;
	private String[] nameParts;
	private JTextField queryField;
	private JTable resultTab;
	private JTextField projectAssignField;

	public AdminGUI() {

		frame.setBounds(100, 100, 550, 500);
		frame.setTitle("UserGUI");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 11, 534, 450);
		frame.getContentPane().add(tabbedPane);

		JPanel adPro = new JPanel();
		tabbedPane.addTab("Project", null, adPro, null);
		adPro.setLayout(null);
		frame.setVisible(true);
		//
		JLabel adName = new JLabel("Name");
		adName.setBounds(10, 62, 67, 33);
		adPro.add(adName);

		name_Field = new JTextField();
		name_Field.setEditable(false);
		name_Field.setColumns(10);
		name_Field.setBounds(131, 68, 190, 20);
		adPro.add(name_Field);

		JLabel adID = new JLabel("ID");
		adID.setBounds(10, 102, 46, 14);
		adPro.add(adID);

		id_Field = new JTextField();
		id_Field.setEditable(false);
		id_Field.setColumns(10);
		id_Field.setBounds(131, 99, 148, 20);
		adPro.add(id_Field);

		proName_Field = new JTextField();
		proName_Field.setColumns(10);
		proName_Field.setBounds(131, 126, 243, 20);
		adPro.add(proName_Field);

		proDesc_Field = new JTextPane();
		proDesc_Field.setBounds(131, 190, 299, 39);
		adPro.add(proDesc_Field);

		JLabel adminPanel = new JLabel("Admin Panel - Project");
		adminPanel.setFont(new Font("Tahoma", Font.BOLD, 16));
		adminPanel.setBounds(181, 11, 201, 32);
		adPro.add(adminPanel);

		JButton proInsert = new JButton("Insert");
		proInsert.setBounds(141, 241, 89, 23);
		adPro.add(proInsert);
		proInsert.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (proId_Field.getText().equals("") && proName_Field.getText().equals("")
						&& proDesc_Field.getText().equals("")) {
					JOptionPane.showMessageDialog(adminPanel, "Please enter information");

				} else {
					try {
						con = new DBConnection(url, username, password);
						Statement stm = con.getConnection().createStatement();
						String sql = "INSERT INTO project VALUES ('" + proId_Field.getText() + "','"
								+ proName_Field.getText() + "','" + proDesc_Field.getText() +"','"+id_Field.getText() +"')";
						stm.execute(sql);
						JOptionPane.showMessageDialog(adminPanel, "Added Successfully");
						stm.close();
					} catch (Exception n) {
						n.printStackTrace();
					}
				}
			}
		});

		JLabel lblNewLabel = new JLabel("Project Name");
		lblNewLabel.setBounds(10, 129, 89, 14);
		adPro.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Project ID");
		lblNewLabel_1.setBounds(9, 160, 67, 14);
		adPro.add(lblNewLabel_1);

		proId_Field = new JTextField();
		proId_Field.setColumns(10);
		proId_Field.setBounds(131, 159, 158, 20);
		adPro.add(proId_Field);

		JLabel lblNewLabel_2 = new JLabel("Project Descripsion");
		lblNewLabel_2.setBounds(8, 190, 113, 14);
		adPro.add(lblNewLabel_2);

		JScrollPane productTable = new JScrollPane();
		productTable.setBounds(75, 277, 394, 134);
		adPro.add(productTable);

		table_product = new JTable();
		productTable.setViewportView(table_product);

		JButton proLoad = new JButton("Load");
		proLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					String query = "Select * from project";
					DBConnection con2 = new DBConnection(url, username, password);
					PreparedStatement pst = con2.getConnection().prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table_product.setModel(DbUtils.resultSetToTableModel(rs));
					rs.close();
					con2.getConnection().close();
				} catch (Exception n) {
					n.printStackTrace();
				}
			}
		});
		proLoad.setBounds(285, 240, 89, 23);
		adPro.add(proLoad);
		// Tab
		JPanel adUser = new JPanel();
		tabbedPane.addTab("Insert", null, adUser, null);
		adUser.setLayout(null);

		JLabel adUserLabel = new JLabel("Admin - Insert");
		adUserLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		adUserLabel.setBounds(186, 11, 179, 24);
		adUser.add(adUserLabel);

		JLabel lblNewLabel_3 = new JLabel("User Name");
		lblNewLabel_3.setBounds(10, 42, 76, 24);
		adUser.add(lblNewLabel_3);

		JLabel lblNewLabel_3_1 = new JLabel("User ID");
		lblNewLabel_3_1.setBounds(10, 77, 60, 24);
		adUser.add(lblNewLabel_3_1);

		JLabel lblNewLabel_3_2 = new JLabel("User Password");
		lblNewLabel_3_2.setBounds(10, 112, 106, 24);
		adUser.add(lblNewLabel_3_2);

		userNameField = new JTextField();
		userNameField.setBounds(127, 44, 218, 20);
		adUser.add(userNameField);
		userNameField.setColumns(10);

		userIDField = new JTextField();
		userIDField.setBounds(127, 79, 191, 20);
		adUser.add(userIDField);
		userIDField.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Repeat Password");
		lblNewLabel_4.setBounds(10, 150, 106, 14);
		adUser.add(lblNewLabel_4);

		userRePassField = new JPasswordField();
		userRePassField.setBounds(127, 147, 156, 20);
		adUser.add(userRePassField);

		userPassField = new JPasswordField();
		userPassField.setBounds(127, 114, 156, 20);
		adUser.add(userPassField);

		JButton userInsert = new JButton("Insert");
		userInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (userNameField.getText().equals("") || userPassField.getText().equals("")
						|| userIDField.getText().equals("") || userRePassField.getText().equals("")) {
					JOptionPane.showMessageDialog(adminPanel, "Please enter information");
				}
				else if(userPassField.getText().equals(userRePassField.getText())) {
					try {
						nameParts = nameSplit(userNameField.getText());
						con = new DBConnection(url, username, password);
						Statement stm = con.getConnection().createStatement();
						String sql = "INSERT INTO user(user_ID,user_pass,user_firstname,user_middlename,user_lastname,admin_id) VALUES "
								+ "('" + userIDField.getText() + "','"+ userPassField.getText() + "','"
								+ nameParts[0] + "','"+ nameParts[1] +"','"+ nameParts[2]+"','"+id_Field.getText() +"')";
						stm.execute(sql);
						JOptionPane.showMessageDialog(adminPanel, "Added Successfully");
						stm.close();
						userNameField.setText("");
						userPassField.setText("");
						userIDField.setText("");
						userRePassField.setText("");
					} catch (Exception n) {
						n.printStackTrace();
					}
				}
				else {
					JOptionPane.showMessageDialog(adminPanel, "Repeat password is not right");
				}
			}
		});
		userInsert.setBounds(202, 188, 89, 23);
		adUser.add(userInsert);

		JLabel lblNewLabel_3_3 = new JLabel("Staff Name");
		lblNewLabel_3_3.setBounds(10, 220, 76, 24);
		adUser.add(lblNewLabel_3_3);

		JLabel lblNewLabel_3_1_1 = new JLabel("Staff ID");
		lblNewLabel_3_1_1.setBounds(10, 255, 60, 24);
		adUser.add(lblNewLabel_3_1_1);

		JLabel lblNewLabel_3_2_1 = new JLabel("Staff Password");
		lblNewLabel_3_2_1.setBounds(10, 290, 106, 24);
		adUser.add(lblNewLabel_3_2_1);

		JLabel lblNewLabel_4_1 = new JLabel("Repeat Password");
		lblNewLabel_4_1.setBounds(10, 328, 106, 14);
		adUser.add(lblNewLabel_4_1);

		staffNameField = new JTextField();
		staffNameField.setColumns(10);
		staffNameField.setBounds(127, 222, 238, 20);
		adUser.add(staffNameField);

		staffIDField = new JTextField();
		staffIDField.setColumns(10);
		staffIDField.setBounds(127, 257, 191, 20);
		adUser.add(staffIDField);

		staffPassField = new JPasswordField();
		staffPassField.setBounds(127, 292, 156, 20);
		adUser.add(staffPassField);

		staffRePassField = new JPasswordField();
		staffRePassField.setBounds(127, 325, 156, 20);
		adUser.add(staffRePassField);

		JButton staffInsert = new JButton("Insert");
		staffInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					if (staffNameField.getText().equals("") || staffPassField.getText().equals("")
							|| staffIDField.getText().equals("") || staffRePassField.getText().equals("")) {
						JOptionPane.showMessageDialog(adminPanel, "Please enter information");
					}
					else if(staffPassField.getText().equals(staffRePassField.getText())) {
						try {
							nameParts = nameSplit(staffNameField.getText());
							con = new DBConnection(url, username, password);
							Statement stm = con.getConnection().createStatement();
							String sql = "INSERT INTO staff(staff_ID,staff_pass,staff_firstname,staff_middlename,staff_lastname,project_id,admin_id) VALUES "
									+ "('" + staffIDField.getText() + "','"+ staffPassField.getText() + "','"
									+ nameParts[0] + "','"+ nameParts[1] +"','"+ nameParts[2]+"','"+ projectAssignField.getText()+"','"+ id_Field.getText()+"')";
							stm.execute(sql);
							JOptionPane.showMessageDialog(adminPanel, "Added Successfully");
							stm.close();
							staffNameField.setText("");
							staffPassField.setText("");
							staffIDField.setText("");
							staffRePassField.setText("");
						} catch (Exception n) {
							n.printStackTrace();
						}
					}
					else {
						JOptionPane.showMessageDialog(adminPanel, "Repeat password is not right");
					}
				}
		});
		staffInsert.setBounds(204, 376, 87, 23);
		adUser.add(staffInsert);
		
		JLabel lblNewLabel_3_2_1_1 = new JLabel("Project ID");
		lblNewLabel_3_2_1_1.setBounds(305, 290, 89, 24);
		adUser.add(lblNewLabel_3_2_1_1);
		
		projectAssignField = new JTextField();
		projectAssignField.setBounds(387, 292, 115, 20);
		adUser.add(projectAssignField);
		projectAssignField.setColumns(10);

		JPanel adSearch = new JPanel();
		tabbedPane.addTab("Search", null, adSearch, null);
		adSearch.setLayout(null);
		
		JLabel lblAdminSearch = new JLabel("Admin - Search");
		lblAdminSearch.setBounds(202, 5, 124, 20);
		lblAdminSearch.setFont(new Font("Tahoma", Font.BOLD, 16));
		adSearch.add(lblAdminSearch);
		
		queryField = new JTextField();
		queryField.setBounds(103, 36, 316, 39);
		adSearch.add(queryField);
		queryField.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Query");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_8.setBounds(36, 42, 57, 27);
		adSearch.add(lblNewLabel_8);
		
		JButton btnSearch = new JButton("Run");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {

					String query = queryField.getText();
					DBConnection con2 = new DBConnection(url, username, password);
					PreparedStatement pst = con2.getConnection().prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					resultTab.setModel(DbUtils.resultSetToTableModel(rs));
					rs.close();
					con2.getConnection().close();
				} catch (Exception n) {
					n.printStackTrace();
				}
				
			}
		});
		btnSearch.setBounds(430, 44, 89, 23);
		adSearch.add(btnSearch);
		
		JScrollPane resultScroll = new JScrollPane();
		resultScroll.setBounds(36, 107, 473, 286);
		adSearch.add(resultScroll);
		
		resultTab = new JTable();
		resultScroll.setViewportView(resultTab);

		EnterIDAndName();

	}

	private void EnterIDAndName() {
		name_Field.setText(ui.getName());
		id_Field.setText(ui.getID());
	}

	private String[] nameSplit(String n) {
		String name = n;
		String[] parts = name.split(" ");
		return parts;
	}
}
