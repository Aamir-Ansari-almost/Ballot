import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.*;


public class AdminHomePage implements ActionListener {

	JFrame frame;
	
	JButton btnCreatePoll;
	JButton btnPastPoll;
	JButton btnOngoingPoll;
	
	AdminHomePage() {
		
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
		btnCreatePoll.addActionListener(this);
		frame.getContentPane().add(btnCreatePoll);
		
		btnPastPoll = new JButton("Past poll");
		btnPastPoll.setBackground(SystemColor.window);
		btnPastPoll.setFont(new Font("Mongolian Baiti", Font.PLAIN, 14));
		btnPastPoll.setBounds(10, 129, 104, 23);
		btnPastPoll.addActionListener(this);
		frame.getContentPane().add(btnPastPoll);
		
		btnOngoingPoll = new JButton("Ongoing poll");
		btnOngoingPoll.setBackground(SystemColor.window);
		btnOngoingPoll.setFont(new Font("Mongolian Baiti", Font.PLAIN, 12));
		btnOngoingPoll.setBounds(10, 163, 104, 23);
		btnOngoingPoll.addActionListener(this);
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
		panel_1.setBorder(new LineBorder(SystemColor.window, 3));
		panel_1.setBounds(0, 56, 123, 497);
		frame.getContentPane().add(panel_1);
		
		
		frame.setVisible(true);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == btnPastPoll) {
			new PreviousPolls();
			frame.dispose();
		}
		
		if (e.getSource() == btnCreatePoll) {
			new CreatePolls();
			frame.dispose();
		}
		
		if (e.getSource() == btnOngoingPoll) {
			JOptionPane.showMessageDialog(frame, "No Ongoing polls");
		}
		
	}
	

	public static void main(String[] args) {
		
		new AdminHomePage();
		
	}

}



















































