import java.sql.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class Authentication implements ActionListener {
	
	JFrame frame;
	JPanel panel;
	JTextField userText;
	JPasswordField passwordText;
//	JLabel message;
	
	public Authentication() {
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.setSize(700, 450);
		frame.setLocation(100, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// main container
		Container mainContainer = frame.getContentPane();
		mainContainer.setLayout(null);
		mainContainer.setBackground(Color.GRAY);
		frame.getRootPane().setBorder(BorderFactory.createMatteBorder(4,4,4,4, Color.black));
		
		JLabel lblNewLabel = new JLabel("BALLOT");
		lblNewLabel.setBounds(10, 11, 664, 46);
		frame.getContentPane().add(lblNewLabel);
		lblNewLabel.setForeground(SystemColor.window);
		lblNewLabel.setBackground(SystemColor.textHighlight);
		lblNewLabel.setFont(new Font("Broadway", Font.BOLD, 22));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setBorder(new LineBorder(SystemColor.window, 3));
		panel_1.setBounds(0, 56, 123, 347);
		frame.getContentPane().add(panel_1);
		
		JLabel lblNewLabel_1 = new JLabel("Email   ");
		lblNewLabel_1.setFont(new Font("Mongolian Baiti", Font.BOLD, 19));
		lblNewLabel_1.setBounds(178, 97, 93, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		userText = new JTextField();
		userText.setBounds(281, 95, 181, 20);
		frame.getContentPane().add(userText);
		userText.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setFont(new Font("Mongolian Baiti", Font.BOLD, 19));
		lblNewLabel_1_1.setBounds(178, 132, 93, 14);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		passwordText = new JPasswordField();
		passwordText.setColumns(10);
		passwordText.setBounds(281, 126, 181, 20);
		frame.getContentPane().add(passwordText);
		
		JButton btnNewButton = new JButton("LOGIN");
		btnNewButton.setBounds(300, 167, 89, 23);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(this);
		
		btnNewButton.setBackground(Color.DARK_GRAY);
		btnNewButton.setForeground(Color.WHITE);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(122, 56, 562, 357);
		frame.getContentPane().add(panel);
		
		frame.setVisible(true);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// entered email
		String user = userText.getText();
		String email = "'"+user+"'";
		
		// entered password
		String password = new String(passwordText.getPassword());
		try {
			// connection with database
			String connString = "jdbc:sqlserver://LAPTOP-CEM8CB00;instanceName=AAMIRSQL;Database=BALOT;integratedSecurity=true;";
			Connection conn = DriverManager.getConnection(connString);
			Statement stmt = conn.createStatement();
			
			// sql query
			String query = "SELECT * FROM users WHERE email="+email;
			
			// result set
			ResultSet rs = stmt.executeQuery(query);
			
			// if result set is empty user is not present
			if (rs.next() == false) {
				JOptionPane.showMessageDialog(frame, "Invalid email or password");
				return;
			}
			// validate user
			if (rs.getString(6).equals(password)) {
				JOptionPane.showMessageDialog(frame, "Login successful");
				new AdminHomePage();
				frame.dispose();
			}
			else {
				JOptionPane.showMessageDialog(frame, "Invalid email or password");
			}
		}
		catch (Exception excpt) {
			excpt.printStackTrace();
		}
		
	}
	
	public static void main(String args[]) {

		new Authentication();
		
	}

}





































