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


 class Vote implements ActionListener {

	JFrame frame;
	JButton btnSubmit;
	JRadioButton rdOption1;
	JRadioButton rdOption2;
	JRadioButton rdOption3;
	JRadioButton rdOption4;
	JButton btnOngoingPoll;
	JButton btnPastPoll;
	
	public int pollId=0;
	
	void setPollId(int pollId) {
		this.pollId = pollId;
	}
	
	int user_id=0;
	void setUserId(int id) {
		user_id = id;
	}
	
	Vote(int pollId, int user_id) {
		
		setPollId(pollId);
		setUserId(user_id);
		
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
		
		btnPastPoll = new JButton("Past poll");
		btnPastPoll.setBackground(SystemColor.window);
		btnPastPoll.setFont(new Font("Mongolian Baiti", Font.PLAIN, 14));
		btnPastPoll.setBounds(10, 95, 104, 23);
		frame.getContentPane().add(btnPastPoll);
		
		btnOngoingPoll = new JButton("Ongoing poll");
		btnOngoingPoll.setBackground(SystemColor.window);
		btnOngoingPoll.setFont(new Font("Mongolian Baiti", Font.PLAIN, 13));
		btnOngoingPoll.setBounds(10, 129, 104, 23);
		frame.getContentPane().add(btnOngoingPoll);
		
//		JButton btnOngoingPoll = new JButton("Ongoing poll");
//		btnOngoingPoll.setBackground(SystemColor.window);
//		btnOngoingPoll.setFont(new Font("Mongolian Baiti", Font.PLAIN, 12));
//		btnOngoingPoll.setBounds(10, 163, 104, 23);
//		frame.getContentPane().add(btnOngoingPoll);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(SystemColor.window, 3));
		panel.setBackground(Color.WHITE);
		panel.setBounds(124, 56, 912, 507);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Title");
		lblNewLabel_1.setBounds(32, 45, 107, 38);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Description");
		lblNewLabel_2.setBounds(32, 78, 107, 38);
		panel.add(lblNewLabel_2);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(108, 271, 85, 21);
		btnSubmit.setBackground(Color.DARK_GRAY);
		btnSubmit.setForeground(Color.WHITE);
		panel.add(btnSubmit);
		
		JLabel lblTitle = new JLabel("New label");
		lblTitle.setBounds(146, 45, 300, 38);
		panel.add(lblTitle);
		
		JLabel lblDescription = new JLabel("New label");
		lblDescription.setBounds(146, 78, 400, 38);
		panel.add(lblDescription);
		
		JLabel lblOption1 = new JLabel("Option 1");
		lblOption1.setBounds(146, 126, 160, 38);
		panel.add(lblOption1);
		
		JLabel lblOption2 = new JLabel("Option 2");
		lblOption2.setBounds(146, 157, 160, 38);
		panel.add(lblOption2);
		
		JLabel lblOption3 = new JLabel("Option 3");
		lblOption3.setBounds(146, 193, 160, 38);
		panel.add(lblOption3);
		
		JLabel lblOption4 = new JLabel("Option 4");
		lblOption4.setBounds(146, 223, 160, 38);
		panel.add(lblOption4);
		
		try {
			int i=0;
			String[] optionArray = new String[10];
			// connection with database
			String connString = "jdbc:sqlserver://LAPTOP-CEM8CB00;instanceName=AAMIRSQL;Database=BALOT;integratedSecurity=true;";
			Connection conn = DriverManager.getConnection(connString);
			Statement stmt = conn.createStatement();
			Statement stmt2 = conn.createStatement();
			// sql query
			String query = "SELECT * FROM poll_option WHERE poll_id="+pollId+";";
			
			// result set
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				optionArray[i++]=rs.getString(3);
			}
			lblOption1.setText(optionArray[0]);
			lblOption2.setText(optionArray[1]);
			lblOption3.setText(optionArray[2]);
			lblOption4.setText(optionArray[3]);
			
			String query2 = "SELECT * FROM poll WHERE poll_id="+pollId+";";
			ResultSet rs2 = stmt2.executeQuery(query2);
			
			while(rs2.next()) {
				lblTitle.setText(rs2.getString(3));
				lblDescription.setText(rs2.getString(4));
			}
			
		}
		catch (Exception excpt) {
			excpt.printStackTrace();
		}
		
		rdOption1 = new JRadioButton("Option 1");
		rdOption1.setBounds(26, 135, 103, 21);
		panel.add(rdOption1);
		
		rdOption2 = new JRadioButton("Option 2");
		rdOption2.setBounds(26, 166, 103, 21);
		panel.add(rdOption2);
		
		rdOption3 = new JRadioButton("Option 3");
		rdOption3.setBounds(26, 202, 103, 21);
		panel.add(rdOption3);
		
		rdOption4 = new JRadioButton("Option 4");
		rdOption4.setBounds(26, 232, 103, 21);
		panel.add(rdOption4);
		
		ButtonGroup G = new ButtonGroup();
		G.add(rdOption1);
		G.add(rdOption2);
		G.add(rdOption3);
		G.add(rdOption4);
		
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
		
		btnSubmit.addActionListener(this);
		btnOngoingPoll.addActionListener(this);
		btnPastPoll.addActionListener(this);
		
		frame.setVisible(true);
	}
	

	
	public void actionPerformed(ActionEvent e) {
	
		if (e.getSource() == btnSubmit) {
			int selected=-999;
			if (rdOption1.isSelected()) {
				selected=0;
			}
			if (rdOption2.isSelected()) {
				selected=1;
			}
			if (rdOption3.isSelected()) {
				selected=2;
			}
			if (rdOption4.isSelected()) {
				selected=3;
			}
//			System.out.println(selected);
//			System.out.println(pollId);
		
			try {
				int i=0;
				String[] optionId = new String[10];
				// connection with database
				String connString = "jdbc:sqlserver://LAPTOP-CEM8CB00;instanceName=AAMIRSQL;Database=BALOT;integratedSecurity=true;";
				Connection conn = DriverManager.getConnection(connString);
				Statement stmt = conn.createStatement();
				Statement stmtVote = conn.createStatement();
				// sql query
				String query = "SELECT * FROM poll_option WHERE poll_id="+pollId+";";
				
				// result set
				ResultSet rs = stmt.executeQuery(query);
				while (rs.next()) {
					optionId[i++]=rs.getString(1);
				}
				
				// poll_id, poll_option_id
				String insertVote = "INSERT INTO votes(poll_id, user_id, poll_option_id) VALUES ("+pollId+","+ user_id+","+optionId[selected]+");";
				stmtVote.executeUpdate(insertVote);
				JOptionPane.showMessageDialog(frame, "Successfully Voted");
			}
			catch (Exception excpt) {
				excpt.printStackTrace();
			}
			
		}
		
		if (e.getSource() == btnOngoingPoll) {
			new StudentOngoingPoll(user_id);
			frame.dispose();
		}
		
		if (e.getSource() == btnPastPoll) {
			new StudentPastPolls(user_id);
			frame.dispose();
		}
		
	}
	

	public static void main(String[] args) {
		
		new Vote(2, 3);
	}
}
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 