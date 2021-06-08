//import java.sql.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.border.*;


 class CreatePolls implements ActionListener {

	JFrame frame;
	private JTextField txtTitle;
	private JTextField txtDescription;
	private JTextField txtOption_1;
	private JTextField txtOption_2;
	private JTextField txtOption_3;
	private JTextField txtOption_4;
	JButton button;
	JButton btnOngoingPoll;
	JButton btnPastPoll;
	
	CreatePolls() {
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.setSize(1050, 600);
		frame.setLocation(100, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// main container
		Container mainContainer = frame.getContentPane();
		mainContainer.setLayout(null);
		mainContainer.setBackground(Color.GRAY);
		frame.getRootPane().setBorder(BorderFactory.createMatteBorder(4,4,4,4, Color.black));
		
		JButton btnNewButton = new JButton("Create poll");
		btnNewButton.setBackground(SystemColor.window);
		btnNewButton.setFont(new Font("Mongolian Baiti", Font.PLAIN, 14));
		btnNewButton.setBounds(10, 95, 104, 23);
		frame.getContentPane().add(btnNewButton);
		
		btnPastPoll = new JButton("Past poll");
		btnPastPoll.setBackground(SystemColor.window);
		btnPastPoll.setFont(new Font("Mongolian Baiti", Font.PLAIN, 14));
		btnPastPoll.setBounds(10, 129, 104, 23);
		frame.getContentPane().add(btnPastPoll);
		
		btnOngoingPoll = new JButton("Ongoing poll");
		btnOngoingPoll.setBackground(SystemColor.window);
		btnOngoingPoll.setFont(new Font("Mongolian Baiti", Font.PLAIN, 12));
		btnOngoingPoll.setBounds(10, 163, 104, 23);
		frame.getContentPane().add(btnOngoingPoll);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(SystemColor.window, 3));
		panel.setBackground(Color.WHITE);
		panel.setBounds(124, 56, 912, 507);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Title");
		lblNewLabel_1.setBounds(32, 35, 107, 38);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Description");
		lblNewLabel_2.setBounds(32, 65, 107, 38);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Option 1");
		lblNewLabel_3.setBounds(32, 115, 107, 38);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("Option 2");
		lblNewLabel_3_1.setBounds(32, 145, 107, 38);
		panel.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_3_2 = new JLabel("Option 3");
		lblNewLabel_3_2.setBounds(32, 175, 107, 38);
		panel.add(lblNewLabel_3_2);
		
		JLabel lblNewLabel_3_3 = new JLabel("Option 4");
		lblNewLabel_3_3.setBounds(32, 205, 107, 38);
		panel.add(lblNewLabel_3_3);
		
		txtTitle = new JTextField();
		txtTitle.setBounds(135, 45, 160, 19);
		panel.add(txtTitle);
		txtTitle.setColumns(10);
		
		txtDescription = new JTextField();
		txtDescription.setColumns(10);
		txtDescription.setBounds(135, 75, 160, 19);
		panel.add(txtDescription);
		
		txtOption_1 = new JTextField();
		txtOption_1.setColumns(10);
		txtOption_1.setBounds(135, 125, 160, 19);
		panel.add(txtOption_1);
		
		txtOption_2 = new JTextField();
		txtOption_2.setColumns(10);
		txtOption_2.setBounds(135, 155, 160, 19);
		panel.add(txtOption_2);
		
		txtOption_3 = new JTextField();
		txtOption_3.setColumns(10);
		txtOption_3.setBounds(135, 185, 160, 19);
		panel.add(txtOption_3);
		
		txtOption_4 = new JTextField();
		txtOption_4.setColumns(10);
		txtOption_4.setBounds(135, 215, 160, 19);
		panel.add(txtOption_4);
		
		JLabel lblNewLabel = new JLabel("BALLOT");
		lblNewLabel.setBounds(10, 11, 664, 46);
		frame.getContentPane().add(lblNewLabel);
		lblNewLabel.setForeground(SystemColor.window);
		lblNewLabel.setBackground(SystemColor.textHighlight);
		lblNewLabel.setFont(new Font("Broadway", Font.BOLD, 22));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setBorder(new LineBorder(SystemColor.window, 3));
		panel_1.setBounds(0, 56, 123, 497);
		frame.getContentPane().add(panel_1);
		
		button = new JButton("Create");
		button.setBounds(108, 271, 85, 21);
		panel.add(button);
		button.setBackground(Color.DARK_GRAY);
		button.setForeground(Color.WHITE);
		button.addActionListener(this);
		
		btnPastPoll.addActionListener(this);
		btnOngoingPoll.addActionListener(this);
		frame.setVisible(true);
	}
	

	
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == button) {
			String title = txtTitle.getText();
			title = "'"+title+"'";
			
			String description = txtDescription.getText();
			description = "'"+description+"'";
			
			String option_1 = txtOption_1.getText();
			option_1 = "'"+option_1+"'";
			
			String option_2 = txtOption_2.getText();
			option_2 = "'"+option_2+"'";
			
			String option_3 = txtOption_3.getText();
			option_3 = "'"+option_3+"'";
			
			String option_4 = txtOption_4.getText();
			option_4 = "'"+option_4+"'";
			
			try {
				// connection with database
				String connString = "jdbc:sqlserver://LAPTOP-CEM8CB00;instanceName=AAMIRSQL;Database=BALOT;integratedSecurity=true;";
				Connection conn = DriverManager.getConnection(connString);
				Statement stmtPollInsert = conn.createStatement();
				Statement stmtGetPollId = conn.createStatement();
				Statement stmtOptionInsert = conn.createStatement();
				
	//			sql query for poll
				String insertInPoll = "INSERT INTO poll (administrator_id, title, poll_description) VALUES (7,"+title+","+description+");";
	//			execute query
				stmtPollInsert.executeUpdate(insertInPoll);
				
				// get Id of inserted poll
				String getPollId = "SELECT poll_id FROM poll WHERE title="+title+" AND poll_description="+description+";";
				ResultSet rsPollId =  stmtGetPollId.executeQuery(getPollId);
				String stringPollId = "";
				while (rsPollId.next()) {
					stringPollId = rsPollId.getString(1);
				}
				int pollId = Integer.parseInt(stringPollId);
	
	//			sql query for option
				String insertInOption = "INSERT INTO poll_option (poll_id, options) VALUES ("+pollId+","+option_1+"),("+pollId+","+option_2+"),("+pollId+","+option_3+"),("+pollId+","+option_4+");";
				stmtOptionInsert.executeUpdate(insertInOption);
				
			}
			catch (Exception excpt) {
				excpt.printStackTrace();
			}
			JOptionPane.showMessageDialog(frame, "Poll Created successfullly");
		}
		
		if (e.getSource() == btnOngoingPoll) {
			JOptionPane.showMessageDialog(frame, "No Ongoing polls");
		}
		
		if (e.getSource() == btnPastPoll) {
			new PreviousPolls();
			frame.dispose();
		}
		
	}
	

	public static void main(String[] args) {
		
		new CreatePolls();
		
	}
}
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 