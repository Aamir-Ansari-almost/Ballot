//import java.sql.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.JTableHeader;


 class StudentOngoingPoll implements ActionListener {

	JFrame frame;
	JButton btnPastPoll;
	JButton btnOngoingPoll;
	JButton btnVote;
	JTable table;
	
	String[] column = {"Title", "description"};
	Object[][] data= {{"",""},{"",""},{"",""},{"",""},{"",""},{"",""},{"",""},{"",""},{"",""},{"",""},{"",""},{"",""},{"",""},{"",""},{"",""},{"",""},{"",""},{"",""}};
	
	int user_id=0;
	void setUserId(int id) {
		user_id = id;
	}
	
	StudentOngoingPoll(int user_id) {
		
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
		
		btnVote = new JButton("Vote");
		btnVote.setBackground(SystemColor.window);
		btnVote.setFont(new Font("Mongolian Baiti", Font.PLAIN, 12));
		btnVote.setBounds(10, 164, 104, 23);
		frame.getContentPane().add(btnVote);
		
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
		btnVote.addActionListener(this);

		try {
			int i=0;
			// connection with database
			String connString = "jdbc:sqlserver://LAPTOP-CEM8CB00;instanceName=AAMIRSQL;Database=BALOT;integratedSecurity=true;";
			Connection conn = DriverManager.getConnection(connString);
			Statement stmt = conn.createStatement();
			// sql query
			String query = "SELECT * FROM poll";
			
			// result set
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				data[i][0] = rs.getString(3);
				data[i][1] = rs.getString(4);
				i++;
			}
			
		}
		catch (Exception excpt) {
			excpt.printStackTrace();
		}
		
		table = new JTable(data, column);
		JTableHeader header = table.getTableHeader();
		table.setBounds(552, 64, -541, -53);
		panel.setLayout(new BorderLayout());
		panel.add(header, BorderLayout.NORTH);
		panel.add(table, BorderLayout.CENTER);
		table.setPreferredScrollableViewportSize(new Dimension(30,30));
		table.setFillsViewportHeight(true);
		
		frame.setVisible(true);
	}
	

	
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == btnVote) {
			int pollId=0;
			String connString = "jdbc:sqlserver://LAPTOP-CEM8CB00;instanceName=AAMIRSQL;Database=BALOT;integratedSecurity=true;";
			try {
				Connection conn = DriverManager.getConnection(connString);
				
				// get info about the row selected 
				int row = table.getSelectedRow();
				String title = table.getModel().getValueAt(row,0).toString();
				String description = table.getModel().getValueAt(row,1).toString();
				
				// get poll id
				Statement stmtGetPollId = conn.createStatement();
				String getPollId = "SELECT poll_id FROM poll WHERE title="+"'"+title+"'"+" AND poll_description="+"'"+description+"'"+";";
				ResultSet rsPollId =  stmtGetPollId.executeQuery(getPollId);
				String stringPollId = "";
				while (rsPollId.next()) {
					stringPollId = rsPollId.getString(1);
				}
				pollId = Integer.parseInt(stringPollId);
				
			} 
			catch (SQLException e1) {
				e1.printStackTrace();
			}
			new Vote(pollId, user_id);
		}
		
		if (e.getSource() == btnPastPoll) {
			new StudentPastPolls(user_id);
			frame.dispose();
		}
		
		if (e.getSource() == btnOngoingPoll) {
			new StudentOngoingPoll(user_id);
			frame.dispose();
		}
		
	}
	

	public static void main(String[] args) {
		
		new StudentOngoingPoll(2);
		
	}
}
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 