import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.JTableHeader;

public class PreviousPolls implements ActionListener {

	JFrame frame;
	JButton btnCreatePoll;
	JButton btnPastPoll;
	JButton btnOngoingPoll;
	JButton btnInfo;
	JTable table;
	JPanel panel_2;
	JTable tableOption;

	//rows of table
	
	String[] column = {"Title", "description", "Date and time"};
	Object[][] data= {{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""}};

//	String[] column = {"Title", "description", "Date and time", "Majority"};
//	Object[][] data= {{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""}};

	String[] columnOption = {"Options", "Votes"};
	Object[][] dataOption= {{"",""},{"",""},{"",""},{"",""}};
	
	PreviousPolls() {
		
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
		
		btnCreatePoll = new JButton("Create poll");
		btnCreatePoll.setBackground(SystemColor.window);
		btnCreatePoll.setFont(new Font("Mongolian Baiti", Font.PLAIN, 14));
		btnCreatePoll.setBounds(10, 95, 104, 23);
		frame.getContentPane().add(btnCreatePoll);
		
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
		
		btnInfo = new JButton("Info");
		btnInfo.setBackground(SystemColor.window);
		btnInfo.setFont(new Font("Mongolian Baiti", Font.PLAIN, 12));
		btnInfo.setBounds(10, 197, 104, 23);
		frame.getContentPane().add(btnInfo);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(SystemColor.window, 3));
		panel.setBackground(Color.WHITE);
		panel.setBounds(124, 56, 912, 330);
		frame.getContentPane().add(panel);
		
		
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
				
		frame.setVisible(true);
		
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
				data[i][2] = rs.getString(5);
				i++;
			}
			
		}
		catch (Exception excpt) {
			excpt.printStackTrace();
		}
		
		// table
		table = new JTable(data, column);
		JTableHeader header = table.getTableHeader();
		table.setBounds(552, 64, -541, -53);
		panel.setLayout(new BorderLayout());
		panel.add(header, BorderLayout.NORTH);
		panel.add(table, BorderLayout.CENTER);
		table.setPreferredScrollableViewportSize(new Dimension(30,30));
		table.setFillsViewportHeight(true);
		
		panel_2 = new JPanel();
		panel_2.setBounds(123, 387,910, 165);
		frame.getContentPane().add(panel_2);
		panel_2.setBorder(new LineBorder(Color.BLACK, 2));
		panel_2.setLayout(null);
		
		btnInfo.addActionListener(this);
		btnCreatePoll.addActionListener(this);
		btnPastPoll.addActionListener(this);
		btnOngoingPoll.addActionListener(this);
		
		tableOption = new JTable(dataOption, columnOption);
		JTableHeader headerOption = tableOption.getTableHeader();
		table.setBounds(552, 64, -541, -53);
		panel_2.setLayout(new BorderLayout());
		panel_2.add(headerOption, BorderLayout.NORTH);
		panel_2.add(tableOption, BorderLayout.CENTER);
		tableOption.setPreferredScrollableViewportSize(new Dimension(30,30));
		tableOption.setFillsViewportHeight(true);
		
		
	}
	
	
	public static void main(String[] args) {
		
		new PreviousPolls();
			
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == btnInfo) {
			// connection
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
				int pollId = Integer.parseInt(stringPollId);
				
				// use the poll id to get the options
				Statement stmtGetOptions = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				String getOptions = "SELECT * FROM poll_option WHERE poll_id="+pollId+";";
				ResultSet rsOptions = stmtGetOptions.executeQuery(getOptions);
				
				int i=0;
				while(rsOptions.next()) {
					tableOption.setValueAt(rsOptions.getString(3), i, 0);
					i++;
				}
				
				// use poll_option_id to get count
				Statement stmtGetCount = conn.createStatement();
				String get_count="";
				ResultSet rs_get_count = null;
				rsOptions.beforeFirst();
				int j=0;
				while(rsOptions.next()) {
					get_count="SELECT COUNT(*) FROM votes WHERE poll_option_id="+rsOptions.getString(1)+";";
					rs_get_count = stmtGetCount.executeQuery(get_count);
					while(rs_get_count.next()) {
						tableOption.setValueAt(rs_get_count.getString(1), j, 1);
						j++;
					}
				}
				
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		}
		
		if(e.getSource() == btnCreatePoll) {
			new CreatePolls();
			frame.dispose();
		}
		
		if (e.getSource() == btnOngoingPoll) {
			JOptionPane.showMessageDialog(frame, "No Ongoing polls");
		}
		
	}
} 


















































