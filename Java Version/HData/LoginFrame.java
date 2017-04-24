// David Whynot 1.13.14 www.xdavesbanex.com
import javax.swing.JOptionPane.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
public class LoginFrame extends JFrame implements MouseListener {
	String userLogReturn;
	JPanel newProfile = new JPanel(); // Create a new profile panel		
	JLabel newProfileLabel = new JLabel("New Profile"); // Text inside new profile panel
	JLabel userLogin = new JLabel("Username:"); // username instructions
	JTextField inputUserLogin = new JTextField(10); // username field
	JLabel passLogin = new JLabel("Password:"); // password instructions
	JTextField inputPassLogin = new JTextField(10); // password field
	JPanel loginButton = new JPanel(); // login button panel
	JLabel loginButtonText = new JLabel("Login"); // login button text
	BevelBorder borderUp = new BevelBorder(1, Color.darkGray, Color.lightGray); // what the border looks like by default
	BevelBorder borderDown = new BevelBorder(0, Color.darkGray, Color.lightGray); // what the border looks like when you hover over it
	public LoginFrame() {
		super("HData");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container con = getContentPane();
		con.setLayout(new FlowLayout());
		con.add(userLogin);
		con.add(inputUserLogin);
		con.add(passLogin);
		con.add(inputPassLogin);
		con.add(loginButton);
		loginButton.add(loginButtonText);
		loginButton.setBorder(borderUp);
		con.add(newProfile);
		newProfile.setBorder(borderUp);
		newProfile.add(newProfileLabel);
		newProfile.addMouseListener(this);
		loginButton.addMouseListener(this);
	}
	public void mouseEntered(MouseEvent e) {
		Object source = e.getSource();
		if(source == newProfile)
			newProfile.setBorder(borderDown);
		if(source == loginButton)
			loginButton.setBorder(borderDown);
	}
	public void mouseExited(MouseEvent e) {
		Object source = e.getSource();
		if(source == newProfile)
			newProfile.setBorder(borderUp);
		if(source == loginButton)
			loginButton.setBorder(borderUp);
	}
	public void mouseClicked(MouseEvent e) {
		Object source = e.getSource();
		if(source == newProfile) {
			NewProfileFrame newP = new NewProfileFrame(); // create NewProfileFrame from class NewProfileFrame
			newP.setSize(250,200);
			newP.setVisible(true);
			newP.setResizable(false);
			newP.setLocation(this.getX(), this.getY());
		}
		if(source == loginButton) {
			String userLoginText = inputUserLogin.getText();
			String passLoginText = inputPassLogin.getText();
			// pass username and password to a class to determine if they exist or not in the database
			HSQL check = new HSQL();
			if(check.CheckIfUserPassExists(userLoginText, passLoginText)) {
				MainFrame mFrame = new MainFrame(userLoginText);
				mFrame.setSize(950,850);
				mFrame.setResizable(false);
				mFrame.setLocation(this.getX() - 250, this.getY() - 250);
				mFrame.setVisible(true);
				this.setVisible(false);
			}
			else
				JOptionPane.showMessageDialog(null,"Invalid username or password!");
		}
	}
	public String getUsername() {
		return userLogReturn;
	}
	public void setUsername() {
		userLogReturn = inputUserLogin.getText();
	}
	public void mousePressed(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {
	}
	public static void main(String[] args) {
		HSQL start = new HSQL();
		start.InitUserpass();
		LoginFrame login = new LoginFrame();
		login.setSize(150,200);
		login.setVisible(true);
		login.setLocationRelativeTo(null);
	}
}