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
import javax.swing.table.JTableHeader;


 class StudentPastPolls implements ActionListener {

	JFrame frame;
	JButton btnPastPoll;
	JButton btnOngoingPoll;
	JTable table;
	JButton btnInfo;
	
	String[] column = {"Title", "description", "Date and time", "You voted"};
	Object[][] data= {{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""}};
	
	int user_id=0;
	void setUserId(int id) {
		user_id = id;
	}
	
	StudentPastPolls(int user_id) {
		
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
		btnPastPoll.setBounds(10, 97, 104, 23);
		frame.getContentPane().add(btnPastPoll);
		
		btnOngoingPoll = new JButton("Ongoing poll");
		btnOngoingPoll.setBackground(SystemColor.window);
		btnOngoingPoll.setFont(new Font("Mongolian Baiti", Font.PLAIN, 12));
		btnOngoingPoll.setBounds(10, 131, 104, 23);
		frame.getContentPane().add(btnOngoingPoll);
		
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(SystemColor.window, 3));
		panel.setBackground(Color.WHITE);
		panel.setBounds(124, 56, 912, 507);
		frame.getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("BALLOT");
		lblNewLabel.setBounds(10, 11, 664, 46);
		frame.getContentPane().add(lblNewLabel);
		lblNewLabel.setForeground(SystemColor.window);
		lblNewLabel.setBackground(SystemColor.textHighlight);
		lblNewLabel.setFont(new Font("Broadway", Font.BOLD, 22));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setBorder(new LineBorder(Color.WHITE, 3));
		panel_1.setBounds(0, 56, 123, 497);
		frame.getContentPane().add(panel_1);
		
		
		btnPastPoll.addActionListener(this);
		btnOngoingPoll.addActionListener(this);
		
		table = new JTable(data, column);
		JTableHeader header = table.getTableHeader();
		table.setBounds(552, 64, -541, -53);
		panel.setLayout(new BorderLayout());
		panel.add(header, BorderLayout.NORTH);
		panel.add(table, BorderLayout.CENTER);
		table.setPreferredScrollableViewportSize(new Dimension(30,30));
		table.setFillsViewportHeight(true);
		
		try {
			String connString = "jdbc:sqlserver://LAPTOP-CEM8CB00;instanceName=AAMIRSQL;Database=BALOT;integratedSecurity=true;";
			Connection conn = DriverManager.getConnection(connString);
			Statement stmt_pollId_optionId = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				    ResultSet.CONCUR_READ_ONLY);
			Statement stmt_poll_info = conn.createStatement();
			Statement stmt_poll_option = conn.createStatement();
			// get poll_id and poll_option_id selected from votes table
			String get_pollId_optionId = "SELECT poll_id, poll_option_id FROM votes WHERE user_id ="+user_id+";";
			
			ResultSet rs_pollId_optionId = stmt_pollId_optionId.executeQuery(get_pollId_optionId);
			
			// use poll_id to get title, description, date and time from poll table
			String get_poll_info = "";
			ResultSet rs_poll_info = null;
			
			int i = 0;
			while(rs_pollId_optionId.next()) {
				get_poll_info = "SELECT * FROM poll WHERE poll_id="+rs_pollId_optionId.getString(1)+";";
				rs_poll_info = stmt_poll_info.executeQuery(get_poll_info);
				
				while(rs_poll_info.next()) {
					table.setValueAt(rs_poll_info.getString(3), i, 0);
					table.setValueAt(rs_poll_info.getString(4), i, 1);
					table.setValueAt(rs_poll_info.getString(5), i, 2);
					i++;
				}
			}
			rs_pollId_optionId.beforeFirst();
			
			// use poll_id and poll_option_id, to get the selected option
			String get_option="";
			ResultSet rs_option = null;
			int j=0;
			while(rs_pollId_optionId.next()) {
				get_option = "SELECT * FROM poll_option WHERE poll_id="+rs_pollId_optionId.getString(1)+" AND poll_option_id ="+rs_pollId_optionId.getString(2)+";";
				rs_option = stmt_poll_option.executeQuery(get_option);
				
				while (rs_option.next()) {
					table.setValueAt(rs_option.getString(3), j, 3);
					j++;
				}
			}
			
		}
		catch (Exception excpt) {
			excpt.printStackTrace();
		}
		
		frame.setVisible(true);
	}
	
	

	
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == btnOngoingPoll) {
			 new StudentOngoingPoll(user_id);
			 frame.dispose();
		}
	}
	

	public static void main(String[] args) {
		
		new StudentPastPolls(2);
		
	}
}
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 