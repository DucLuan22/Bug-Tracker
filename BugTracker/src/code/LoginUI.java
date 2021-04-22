package code;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.CardLayout;
import javax.swing.SwingConstants;

import com.mysql.cj.protocol.Resultset;

import java.awt.Font;
import javax.swing.JTextField;

public class LoginUI {

	private JFrame frame;
	private JLayeredPane layeredPane = new JLayeredPane();
	private JTextField adUserField;
	private JPasswordField adPassField;
	private JTextField staffUserField;
	private JPasswordField staffPassField;
	private JTextField userUserField;
	private JPasswordField userPassField;
	private UserGUI uGUI;
	private StaffGUI staff;
	private AdminGUI admin;
	public  static String Name;
	private static String ID;
	private String url = "jdbc:mysql://localhost:3306/bugtracker";
	private String username = "root";
	private String password = "3737Tuonglai";
	private DBConnection con;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public LoginUI(boolean Switch) {
		
		if(Switch == true)
		{
			initialize();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		inItComponent();

	}

	public void switchPannel(JPanel panel) {
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
	}

	private void inItComponent() {
		frame = new JFrame();
		frame.setBounds(100, 100, 550, 400);
		frame.setTitle("BugTracker");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		layeredPane.setBounds(10, 67, 514, 283);
		frame.getContentPane().add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));

		JPanel adminPanel = new JPanel();
		layeredPane.add(adminPanel, "name_8146116753200");
		adminPanel.setLayout(null);

		JLabel adText = new JLabel("ADMIN LOGIN");
		adText.setFont(new Font("Tahoma", Font.BOLD, 25));
		adText.setHorizontalAlignment(SwingConstants.CENTER);
		adText.setBounds(136, 11, 248, 48);
		adminPanel.add(adText);

		JLabel adPass = new JLabel("Password");
		adPass.setFont(new Font("Tahoma", Font.BOLD, 11));
		adPass.setBounds(59, 117, 65, 26);
		adminPanel.add(adPass);

		JLabel adUsername = new JLabel("Username");
		adUsername.setFont(new Font("Tahoma", Font.BOLD, 11));
		adUsername.setBounds(59, 67, 65, 26);
		adminPanel.add(adUsername);

		adUserField = new JTextField();
		adUserField.setBounds(157, 70, 258, 20);
		adminPanel.add(adUserField);
		adUserField.setColumns(10);

		adPassField = new JPasswordField();
		adPassField.setBounds(157, 120, 258, 20);
		adminPanel.add(adPassField);
		adPassField.setColumns(10);

		JButton adLogButt = new JButton("Login");
		adLogButt.setBounds(216, 171, 89, 23);
		adminPanel.add(adLogButt);
		adLogButt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String adUser = adUserField.getText();
				String adPass = adPassField.getText();
				
				try {
					con = new DBConnection(url, username, password);
					Statement stm = con.getConnection().createStatement();
					String sql = "Select * from Admin where admin_id = '" + adUser+"' and admin_pass ='" + adPass +"'";
					stm.execute(sql);
					ResultSet rs = stm.executeQuery(sql);
					
					if(rs.next()){
						Name = rs.getString("admin_name");
						ID = rs.getString("admin_id");
						admin = new AdminGUI();
					}
					else
					{
						JOptionPane.showMessageDialog(adminPanel, "Wrong Username or Password");
						adUserField.setText("");
						adPassField.setText("");
					}
					stm.close();
					rs.close();
				} catch (Exception n) {
					n.printStackTrace();
				}
			}
		});

		JPanel staffPanel = new JPanel();
		staffPanel.setLayout(null);
		layeredPane.add(staffPanel, "name_8976913389800");

		JLabel staffText = new JLabel("STAFF LOGIN");
		staffText.setHorizontalAlignment(SwingConstants.CENTER);
		staffText.setFont(new Font("Tahoma", Font.BOLD, 25));
		staffText.setBounds(136, 11, 248, 48);
		staffPanel.add(staffText);

		JLabel staffPass = new JLabel("Password");
		staffPass.setFont(new Font("Tahoma", Font.BOLD, 11));
		staffPass.setBounds(59, 117, 65, 26);
		staffPanel.add(staffPass);

		JLabel staffUsername = new JLabel("Username");
		staffUsername.setFont(new Font("Tahoma", Font.BOLD, 11));
		staffUsername.setBounds(59, 67, 65, 26);
		staffPanel.add(staffUsername);

		staffUserField = new JTextField();
		staffUserField.setColumns(10);
		staffUserField.setBounds(157, 70, 258, 20);
		staffPanel.add(staffUserField);

		staffPassField = new JPasswordField();
		staffPassField.setColumns(10);
		staffPassField.setBounds(157, 120, 258, 20);
		staffPanel.add(staffPassField);

		JButton staffLogButt = new JButton("Login");
		staffLogButt.setBounds(216, 171, 89, 23);
		staffPanel.add(staffLogButt);
		staffLogButt.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (staffUserField.getText().equals("Nam") && staffPassField.getText().equals("123")) {
					staff = new StaffGUI();
				}
			}
		});

		JPanel userPanel = new JPanel();
		userPanel.setLayout(null);
		layeredPane.add(userPanel, "name_9239269797300");

		JLabel userText = new JLabel("USER LOGIN");
		userText.setHorizontalAlignment(SwingConstants.CENTER);
		userText.setFont(new Font("Tahoma", Font.BOLD, 25));
		userText.setBounds(136, 11, 248, 48);
		userPanel.add(userText);

		JLabel userPass = new JLabel("Password");
		userPass.setFont(new Font("Tahoma", Font.BOLD, 11));
		userPass.setBounds(59, 117, 65, 26);
		userPanel.add(userPass);

		JLabel userUsername = new JLabel("Username");
		userUsername.setFont(new Font("Tahoma", Font.BOLD, 11));
		userUsername.setBounds(59, 67, 65, 26);
		userPanel.add(userUsername);

		userUserField = new JTextField();
		userUserField.setColumns(10);
		userUserField.setBounds(157, 70, 258, 20);
		userPanel.add(userUserField);

		userPassField = new JPasswordField();
		userPassField.setColumns(10);
		userPassField.setBounds(157, 120, 258, 20);
		userPanel.add(userPassField);

		JButton userLogbutt = new JButton("Login");
		userLogbutt.setBounds(216, 171, 89, 23);
		userPanel.add(userLogbutt);
		userLogbutt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (userUserField.getText().equals("User") && userPassField.getText().equals("123")) {
					uGUI = new UserGUI();

				}
			}
		});

		JButton staffButton = new JButton("Staff");
		staffButton.setBounds(209, 11, 106, 42);
		staffButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPannel(staffPanel);
			}
		});
		frame.getContentPane().add(staffButton);

		JButton adminButton = new JButton("Admin");
		adminButton.setBounds(10, 11, 106, 42);
		adminButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPannel(adminPanel);
			}
		});
		frame.getContentPane().add(adminButton);

		JButton userButton = new JButton("User");
		userButton.setBounds(403, 11, 106, 42);
		userButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPannel(userPanel);
			}
		});
		frame.getContentPane().add(userButton);
	}
	
	public String getName()
	{
		return this.Name;
	}
	
	public String getID()
	{
		return this.ID;
	}
}
