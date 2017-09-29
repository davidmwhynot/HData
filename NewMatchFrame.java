// David Whynot 2.16.14 www.xdavesbanex.com
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
public class NewMatchFrame extends JFrame implements MouseListener {
	String user;
	String[] classArray = {"Warrior","Shaman","Rogue","Paladin","Hunter","Druid","Warlock","Mage","Priest"};
	JComboBox<String> yourClassDrop = new JComboBox<>(classArray);
	JComboBox<String> vsClassDrop = new JComboBox<>(classArray);
	ButtonGroup wlGroup = new ButtonGroup();
		JRadioButton winCheck = new JRadioButton("Win", true);
		JRadioButton lossCheck = new JRadioButton("Loss");
	ButtonGroup rankedGroup = new ButtonGroup();
		JRadioButton rankedCheck = new JRadioButton("Ranked", true);
		JRadioButton unrankedCheck = new JRadioButton("Unranked");
	JLabel userName = new JLabel("XXXXXXXXXXXXXXXXXXXX");
	JLabel headerText = new JLabel("New Match");
	JLabel cardsSearch = new JLabel("Cards Search");
	JLabel newMatch = new JLabel("New Match");
	JLabel newDeck = new JLabel("New Deck");
	JLabel stats = new JLabel("Stats");
	JLabel newArenaMatch = new JLabel("New Arena Match");
	JLabel overview = new JLabel("Overview");
	JLabel yourClass = new JLabel("Your Class: ");
	JLabel vsClass = new JLabel("Opponent Class: ");
	JLabel done = new JLabel("Done");
	Font userNameFont = new Font("Arial", Font.PLAIN, 17);
	Font headerFont = new Font("Arial", Font.BOLD, 17);
	Font mFrameFont = new Font("Arial", Font.PLAIN, 15);
	JPanel header = new JPanel();
	JPanel names = new JPanel();
	JPanel nav = new JPanel();
	JPanel bodyPanel = new JPanel();
	JPanel donePanel = new JPanel();
	BevelBorder borderUp = new BevelBorder(1, Color.darkGray, Color.lightGray); // what the border looks like by default
	BevelBorder borderDown = new BevelBorder(0, Color.darkGray, Color.lightGray); // what the border looks like when you hover over it
	public NewMatchFrame(String userToNewMatch) {
		super("HData");
		user = userToNewMatch;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container con = getContentPane(); // create container CON
			con.setLayout(new BorderLayout()); // set CONTAINER border layout
			con.add(header, BorderLayout.NORTH); // add HEADER to CONTAINER set to NORTH
			header.setLayout(new BorderLayout()); // set HEADER border layout
				header.add(names, BorderLayout.NORTH); // add NAMES to HEADER set to NORTH
				header.add(nav, BorderLayout.SOUTH); // add NAV to HEADER set to SOUTH
					names.setLayout(new FlowLayout()); // set NAMES flow layout
					names.add(userName); // add USERNAME to NAMES
						userName.setText(user); // set USERNAME text to the arguments called for in the method MAINFRAME
						userName.setFont(userNameFont);
					names.add(headerText); // add OVERVIEWHEADER to NAMES
						headerText.setFont(headerFont);
					nav.setLayout(new FlowLayout()); // set NAV flow layout
						nav.add(overview);
						nav.add(newMatch);
						nav.add(newArenaMatch);
						nav.add(newDeck);
						nav.add(stats);
						nav.add(cardsSearch);
			con.add(bodyPanel, BorderLayout.CENTER);
				bodyPanel.setLayout(new FlowLayout());
				bodyPanel.setBorder(borderUp);
				bodyPanel.add(yourClass);
				bodyPanel.add(yourClassDrop);
				bodyPanel.add(vsClass);
				bodyPanel.add(vsClassDrop);
				bodyPanel.add(winCheck);
				bodyPanel.add(lossCheck);
				wlGroup.add(winCheck);
				wlGroup.add(lossCheck);
				bodyPanel.add(rankedCheck);
				bodyPanel.add(unrankedCheck);
				rankedGroup.add(rankedCheck);
				rankedGroup.add(unrankedCheck);
				bodyPanel.add(donePanel);
					donePanel.setLayout(new FlowLayout());
					donePanel.setBorder(borderUp);
					donePanel.add(done);
		// initialize borders
		newMatch.setBorder(borderUp);
		stats.setBorder(borderUp);
		newArenaMatch.setBorder(borderUp);
		newDeck.setBorder(borderUp);
		overview.setBorder(borderUp);
		cardsSearch.setBorder(borderUp);
		// event listeners
		donePanel.addMouseListener(this);
		// nav event listeners
		newMatch.addMouseListener(this);
		stats.addMouseListener(this);
		newDeck.addMouseListener(this);
		newArenaMatch.addMouseListener(this);
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
		if(source == donePanel)
			donePanel.setBorder(borderDown);
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
		if(source == donePanel)
			donePanel.setBorder(borderUp);
		if(source == cardsSearch)
			cardsSearch.setBorder(borderUp);
	}
	public void mouseClicked(MouseEvent e) {
		Object source = e.getSource();
		if(source == newMatch) {
			System.out.println("newMatch");
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
			NewArenaFrame aFrame = new NewArenaFrame(user);
			aFrame.setResizable(false);
			aFrame.setSize(950,850);
			aFrame.setLocation(this.getX(), this.getY());
			aFrame.setVisible(true);
			this.setVisible(false);
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
		if(source == donePanel) {
			HSQL sqlStore = new HSQL();
			HSQL numRecent = new HSQL();
			int recentX = numRecent.GetNumberRecentMatches(user);
			int yourClassToSQL = yourClassDrop.getSelectedIndex();
			int vsClassToSQL = vsClassDrop.getSelectedIndex();
			boolean winEval = winCheck.isSelected();
			boolean rankedEval = rankedCheck.isSelected();
			if(winEval) {
				if(rankedEval) {
					String winRankedQuery = "insert into " + user + "matchHistory values (" + yourClassToSQL + ", 0, " + vsClassToSQL + ", 2, " + recentX + ")";
					sqlStore.StoreNewMatch(winRankedQuery);
				}
				else {
					String winUnrankedQuery = "insert into " + user + "matchHistory values (" + yourClassToSQL + ", 0, " + vsClassToSQL + ", 1, " + recentX + ")";
					sqlStore.StoreNewMatch(winUnrankedQuery);
				}
			}
			if(!(winEval)) {
				if(rankedEval){
					String lossRankedQuery = "insert into " + user + "matchHistory values (" + yourClassToSQL + ", 1, " + vsClassToSQL + ", 2, " + recentX + ")";
					sqlStore.StoreNewMatch(lossRankedQuery);
				}
				else {
					String lossUnrankedQuery = "insert into " + user + "matchHistory values (" + yourClassToSQL + ", 1, " + vsClassToSQL + ", 1, " + recentX + ")";
					sqlStore.StoreNewMatch(lossUnrankedQuery);
				}
			}
		}
	}
	public void mousePressed(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {
	}
}