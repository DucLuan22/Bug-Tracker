package code;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserGUI  {

	private JFrame frame = new JFrame();
	private JTextField unameText;
	private JTextField uidText;
	private JTextField bugtypeText;
	private JTextField bugDescText;

	
	public UserGUI() {
		
		frame.setBounds(100, 100, 550, 400);
		frame.setTitle("UserGUI");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel uName = new JLabel("Name");
		uName.setBounds(10, 48, 67, 33);
		frame.getContentPane().add(uName);
		
		unameText = new JTextField();
		unameText.setBounds(124, 54, 190, 20);
		frame.getContentPane().add(unameText);
		unameText.setColumns(10);
		
		JLabel uID = new JLabel("ID");
		uID.setBounds(10, 92, 46, 14);
		frame.getContentPane().add(uID);
		
		uidText = new JTextField();
		uidText.setBounds(124, 87, 148, 20);
		frame.getContentPane().add(uidText);
		uidText.setColumns(10);
		
		JLabel bugType = new JLabel("Bug Type");
		bugType.setBounds(10, 121, 67, 14);
		frame.getContentPane().add(bugType);
		
		bugtypeText = new JTextField();
		bugtypeText.setBounds(124, 118, 148, 20);
		frame.getContentPane().add(bugtypeText);
		bugtypeText.setColumns(10);
		
		JLabel uPanel = new JLabel("User Panel");
		uPanel .setFont(new Font("Tahoma", Font.BOLD, 16));
		uPanel .setBounds(221, 11, 148, 32);
		frame.getContentPane().add(uPanel);
		
		JLabel bugDesc = new JLabel("Bug Description");
		bugDesc.setBounds(10, 157, 104, 25);
		frame.getContentPane().add(bugDesc);
		
		bugDescText = new JTextField();
		bugDescText.setBounds(124, 159, 234, 20);
		frame.getContentPane().add(bugDescText);
		bugDescText.setColumns(10);
		
		JButton uInsert = new JButton("Insert");
		uInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		uInsert.setBounds(124, 208, 89, 23);
		frame.getContentPane().add(uInsert);
		
		JButton uSearch = new JButton("Search");
		uSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		uSearch.setBounds(269, 208, 89, 23);
		frame.getContentPane().add(uSearch);
		frame.setVisible(true);
		
	}
}
