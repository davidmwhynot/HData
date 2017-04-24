// David Whynot 6.1.14 www.xdavesbanex.com
// frame when you want to record an arena match
import javax.swing.JOptionPane.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
public class NewArenaFrame extends JFrame implements MouseListener {
	String user;
	JLabel userName = new JLabel("XXXXXXXXXXXXXXXXXXXX");
	JLabel headerText = new JLabel("New Arena Match");
	JLabel cardsSearch = new JLabel("Cards Search");
	JLabel newArenaMatch = new JLabel("New Arena Match");
	JLabel newMatch = new JLabel("New Match");
	JLabel stats = new JLabel("Stats");
	JLabel overview = new JLabel("Overview");
	JLabel newDeck = new JLabel("New Deck");
	JLabel textLbl = new JLabel("Card Text/Name: ");
	JLabel manaLbl = new JLabel("Mana Cost: ");
	JLabel cardTypeLbl = new JLabel("Card Type: ");
	Font userNameFont = new Font("Arial", Font.PLAIN, 17);
	Font headerFont = new Font("Arial", Font.BOLD, 17);
	Font mFrameFont = new Font("Arial", Font.PLAIN, 15);
	JPanel header = new JPanel();
	JPanel names = new JPanel();
	JPanel nav = new JPanel();
	JPanel bodyPanel = new JPanel();
	BevelBorder borderUp = new BevelBorder(1, Color.darkGray, Color.lightGray); // what the border looks like by default
	BevelBorder borderDown = new BevelBorder(0, Color.darkGray, Color.lightGray); // what the border looks like when you hover over it
	public NewArenaFrame(String userToNewArena) {
		super("HData");
		user = userToNewArena;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container con = getContentPane();
			con.setLayout(new BorderLayout());
			con.add(header, BorderLayout.NORTH);
			header.setLayout(new BorderLayout());
				header.add(names, BorderLayout.NORTH);
				header.add(nav, BorderLayout.SOUTH);
					names.setLayout(new FlowLayout());
					names.add(userName);
						userName.setText(user);
						userName.setFont(userNameFont);
					names.add(headerText);
						headerText.setFont(headerFont);
					nav.setLayout(new FlowLayout());
						nav.add(overview);
						nav.add(newMatch);
						nav.add(newArenaMatch);
						nav.add(newDeck);
						nav.add(stats);
						nav.add(cardsSearch);
			con.add(bodyPanel, BorderLayout.CENTER);
				bodyPanel.setLayout(new FlowLayout());
		// initialize borders
		newMatch.setBorder(borderUp);
		newArenaMatch.setBorder(borderUp);
		stats.setBorder(borderUp);
		newDeck.setBorder(borderUp);
		overview.setBorder(borderUp);
		cardsSearch.setBorder(borderUp);
		// add event listeners
		newMatch.addMouseListener(this);
		newArenaMatch.addMouseListener(this);
		stats.addMouseListener(this);
		newDeck.addMouseListener(this);
		overview.addMouseListener(this);
		cardsSearch.addMouseListener(this);
	}
	public void mouseEntered(MouseEvent e) {
		// change bevel borders to the down state when mouse enters a panel
		Object source = e.getSource();
		if(source == newMatch)
			newMatch.setBorder(borderDown);
		if(source == stats)
			stats.setBorder(borderDown);
		if(source == overview)
			overview.setBorder(borderDown);
		if(source == newArenaMatch)
			newArenaMatch.setBorder(borderDown);
		if(source == newDeck)
			newDeck.setBorder(borderDown);
		if(source == cardsSearch)
			cardsSearch.setBorder(borderDown);
	}
	public void mouseExited(MouseEvent e) {
		// return the bevel borders to the up state after mouse exits a panel
		Object source = e.getSource();
		if(source == newMatch)
			newMatch.setBorder(borderUp);
		if(source == stats)
			stats.setBorder(borderUp);
		if(source == overview)
			overview.setBorder(borderUp);
		if(source == newArenaMatch)
			newArenaMatch.setBorder(borderUp);
		if(source == newDeck)
			newDeck.setBorder(borderUp);
		if(source == cardsSearch)
			cardsSearch.setBorder(borderUp);
	}
	public void mouseClicked(MouseEvent e) {
		Object source = e.getSource();
		if(source == newMatch) {
			// new settings for the newMatch frame
			System.out.println("newMatch");
			NewMatchFrame newMatchFrame = new NewMatchFrame(user);
			newMatchFrame.setVisible(true);
			newMatchFrame.setSize(950,850);
			newMatchFrame.setLocation(this.getX(), this.getY());
			newMatchFrame.setResizable(false);
			this.setVisible(false);
		}
		if(source == stats) {
			// new settings for the stats frame
			System.out.println("stats");
			StatsFrame statsFrame = new StatsFrame(user);
			statsFrame.setResizable(false);
			statsFrame.setVisible(true);
			statsFrame.setSize(950,850);
			statsFrame.setLocation(this.getX(), this.getY());
			this.setVisible(false);
		}
		if(source == overview) {
			// new settings for the overview frame (MainFrame is initialized to these settings by default)
			System.out.println("overview");
			System.out.println(user);
			MainFrame mFrame = new MainFrame(user);
			mFrame.setResizable(false);
			mFrame.setSize(950,850);
			mFrame.setLocation(this.getX(), this.getY());
			mFrame.setVisible(true);
			this.setVisible(false);
		}
		if(source == newArenaMatch) {
			// new settings for the new arena frame
			System.out.println("arenaFrame");
		}
		if(source == newDeck) {
			System.out.println("newDeck");
			NewDeckFrame dFrame = new NewDeckFrame(user);
			dFrame.setResizable(false);
			dFrame.setSize(950,850);
			dFrame.setLocation(this.getX(), this.getY());
			dFrame.setVisible(true);
			this.setVisible(false);
		}
		if(source == cardsSearch) {
			System.out.println("cardsSearch");
			CardsFrame cFrame = new CardsFrame(user);
			cFrame.setResizable(false);
			cFrame.setSize(950,850);
			cFrame.setLocation(this.getX(), this.getY());
			cFrame.setVisible(true);
			this.setVisible(false);
		}
	}
	public void mousePressed(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {
	}
}