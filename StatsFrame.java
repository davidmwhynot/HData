// David Whynot 1.17.14 www.xdavesbanex.com
import javax.swing.JOptionPane.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
public class StatsFrame extends JFrame implements MouseListener {
	String user;
	JLabel wlPercentPriest = new JLabel(" Win Rate:");
	JLabel wlPercentWarrior = new JLabel(" Win Rate:");
	JLabel wlPercentMage = new JLabel(" Win Rate:");
	JLabel wlPercentShaman = new JLabel(" Win Rate:");
	JLabel wlPercentRogue = new JLabel(" Win Rate:");
	JLabel wlPercentPaladin = new JLabel(" Win Rate:");
	JLabel wlPercentWarlock = new JLabel(" Win Rate:");
	JLabel wlPercentHunter = new JLabel(" Win Rate:");
	JLabel wlPercentDruid = new JLabel(" Win Rate:");
	JLabel classesHeader = new JLabel(" Classes:");
	JLabel priest = new JLabel(" Priest");
	JLabel warrior = new JLabel(" Warrior");
	JLabel mage = new JLabel(" Mage");
	JLabel shaman = new JLabel(" Shaman");
	JLabel rogue = new JLabel(" Rogue");
	JLabel paladin = new JLabel(" Paladin");
	JLabel warlock = new JLabel(" Warlock");
	JLabel hunter = new JLabel(" Hunter");
	JLabel druid = new JLabel(" Druid");
	JPanel classesPanel = new JPanel();
	JPanel priestPanel = new JPanel();
	JPanel warriorPanel = new JPanel();
	JPanel magePanel = new JPanel();
	JPanel shamanPanel = new JPanel();
	JPanel roguePanel = new JPanel();
	JPanel paladinPanel = new JPanel();
	JPanel warlockPanel = new JPanel();
	JPanel hunterPanel = new JPanel();
	JPanel druidPanel = new JPanel();
	JLabel userName = new JLabel("XXXXXXXXXXXXXXXXXXXX");
	JLabel headerText = new JLabel("Stats");
	JLabel cardsSearch = new JLabel("Cards Search");
	JLabel newArenaMatch = new JLabel("New Arena Match");
	JLabel newMatch = new JLabel("New Match");
	JLabel stats = new JLabel("Stats");
	JLabel newDeck = new JLabel("New Deck");
	JLabel overview = new JLabel("Overview");
	Font userNameFont = new Font("Arial", Font.PLAIN, 17);
	Font headerFont = new Font("Arial", Font.BOLD, 17);
	Font mFrameFont = new Font("Arial", Font.PLAIN, 15);
	JPanel header = new JPanel();
	JPanel names = new JPanel();
	JPanel nav = new JPanel();
	GridLayout layout = new GridLayout(2, 2, -25, -25);
	BevelBorder borderUp = new BevelBorder(1, Color.darkGray, Color.lightGray); // what the border looks like by default
	BevelBorder borderDown = new BevelBorder(0, Color.darkGray, Color.lightGray); // what the border looks like when you hover over it
	public StatsFrame(String userToStats) {
		super("HData");
		user = userToStats;
		HSQL statWarrior = new HSQL();
		double warriorWins = statWarrior.getWins(user, 0);
		double warriorLosses = statWarrior.getLosses(user, 0);
		HSQL statShaman = new HSQL();
		double shamanWins = statShaman.getWins(user, 1);
		double shamanLosses = statShaman.getLosses(user, 1);
		HSQL statRogue = new HSQL();
		double rogueWins = statRogue.getWins(user, 2);
		double rogueLosses = statRogue.getLosses(user, 2);
		HSQL statPaladin = new HSQL();
		double paladinWins = statPaladin.getWins(user, 3);
		double paladinLosses = statPaladin.getLosses(user, 3);
		HSQL statHunter = new HSQL();
		double hunterWins = statHunter.getWins(user, 4);
		double hunterLosses = statHunter.getLosses(user, 4);
		HSQL statDruid = new HSQL();
		double druidWins = statDruid.getWins(user, 5);
		double druidLosses = statDruid.getLosses(user, 5);
		HSQL statWarlock = new HSQL();
		double warlockWins = statWarlock.getWins(user, 6);
		double warlockLosses = statWarlock.getLosses(user, 6);
		HSQL statMage = new HSQL();
		double mageWins = statMage.getWins(user, 7);
		double mageLosses = statMage.getLosses(user, 7);
		HSQL statPriest = new HSQL();
		double priestWins = statPriest.getWins(user, 8);
		double priestLosses = statPriest.getLosses(user, 8);
		
		JLabel winsPriest = new JLabel(" W: " + priestWins);
		JLabel lossesPriest = new JLabel(" L: " + priestLosses);
		if(!((priestLosses + priestWins) == 0)) {
			wlPercentPriest.setText(" Win Rate: " + Math.round(priestWins / (priestLosses + priestWins) * 100) + "%");
		}
		JLabel winsWarrior = new JLabel(" W: " + warriorWins);
		JLabel lossesWarrior = new JLabel(" L: " + warriorLosses);
		if(!((warriorLosses + warriorWins) == 0)) {
			wlPercentWarrior.setText(" Win Rate: " + Math.round(warriorWins / (warriorLosses + warriorWins) * 100) + "%");
		}
		JLabel winsMage = new JLabel(" W: " + mageWins);
		JLabel lossesMage = new JLabel(" L:" + mageLosses);
		if(!((mageLosses + mageWins) == 0)) {
			wlPercentMage.setText(" Win Rate: " + Math.round(mageWins / (mageLosses + mageWins) * 100) + "%");
		}
		JLabel winsShaman = new JLabel(" W: " + shamanWins);
		JLabel lossesShaman = new JLabel(" L: " + shamanLosses);
		if(!((shamanLosses + shamanWins) == 0)) {
			wlPercentShaman.setText(" Win Rate: " + Math.round(shamanWins / (shamanLosses + shamanWins) * 100) + "%");
		}
		JLabel winsRogue = new JLabel(" W: " + rogueWins);
		JLabel lossesRogue = new JLabel(" L: " + rogueLosses);
		if(!((rogueLosses + rogueWins) == 0)) {
			wlPercentRogue.setText(" Win Rate: " + Math.round(rogueWins / (rogueLosses + rogueWins) * 100) + "%");
		}
		JLabel winsPaladin = new JLabel(" W: " + paladinWins);
		JLabel lossesPaladin = new JLabel(" L: " + paladinLosses);
		if(!((paladinLosses + paladinWins) == 0)) {
			wlPercentPaladin.setText(" Win Rate: " + Math.round(paladinWins / (paladinLosses + paladinWins) * 100) + "%");
		}
		JLabel winsWarlock = new JLabel(" W: " + warlockWins);
		JLabel lossesWarlock = new JLabel(" L: " + warlockLosses);
		if(!((warlockLosses + warlockWins) == 0)) {
			wlPercentWarlock.setText(" Win Rate: " + Math.round(warlockWins / (warlockLosses + warlockWins) * 100) + "%");
		}
		JLabel winsHunter = new JLabel(" W: " + hunterWins);
		JLabel lossesHunter = new JLabel(" L: " + hunterLosses);
		if(!((hunterLosses + hunterWins) == 0)) {
			wlPercentHunter.setText(" Win Rate: " + Math.round(hunterWins / (hunterLosses + hunterWins) * 100) + "%");
		}
		JLabel winsDruid = new JLabel(" W: " + druidWins);
		JLabel lossesDruid = new JLabel(" L: " + druidLosses);
		if(!((druidLosses + druidWins) == 0)) {
			wlPercentDruid.setText(" Win Rate: " + Math.round(druidWins / (druidLosses + druidWins) * 100) + "%");
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container con = getContentPane(); // create container CON
		con.setLayout(new BorderLayout());
		con.add(header, BorderLayout.NORTH); // add HEADER to CONTAINER
		con.add(classesPanel, BorderLayout.CENTER);
		header.setLayout(new BorderLayout());
		header.add(names, BorderLayout.NORTH); // add NAMES to HEADER set to NORTH
		header.add(nav, BorderLayout.SOUTH); // add NAV to HEADER set to SOUTH
		names.setLayout(new FlowLayout());
		names.add(userName); // add USERNAME to NAMES
		userName.setText(user); // set USERNAME text to the arguments called for in the method STATSFRAME
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
		classesPanel.setLayout(new FlowLayout());
			classesPanel.add(warriorPanel);
			classesPanel.add(shamanPanel);
			classesPanel.add(roguePanel);
			classesPanel.add(paladinPanel);
			classesPanel.add(hunterPanel);
			classesPanel.add(druidPanel);
			classesPanel.add(warlockPanel);
			classesPanel.add(magePanel);
			classesPanel.add(priestPanel);
		priestPanel.setLayout(layout);
			priestPanel.setPreferredSize(new Dimension(150,75));
			priestPanel.add(priest);
			priestPanel.add(wlPercentPriest);
			priestPanel.add(winsPriest);
			priestPanel.add(lossesPriest);
			priestPanel.setBorder(borderUp);
		warriorPanel.setLayout(layout);
			warriorPanel.setPreferredSize(new Dimension(150,75));
			warriorPanel.add(warrior);
			warriorPanel.add(wlPercentWarrior);
			warriorPanel.add(winsWarrior);
			warriorPanel.add(lossesWarrior);
			warriorPanel.setBorder(borderUp);
		magePanel.setLayout(layout);
			magePanel.setPreferredSize(new Dimension(150,75));
			magePanel.add(mage);
			magePanel.add(wlPercentMage);
			magePanel.add(winsMage);
			magePanel.add(lossesMage);
			magePanel.setBorder(borderUp);
		shamanPanel.setLayout(layout);
			shamanPanel.setPreferredSize(new Dimension(150,75));
			shamanPanel.add(shaman);
			shamanPanel.add(wlPercentShaman);
			shamanPanel.add(winsShaman);
			shamanPanel.add(lossesShaman);
			shamanPanel.setBorder(borderUp);
		roguePanel.setLayout(layout);
			roguePanel.setPreferredSize(new Dimension(150,75));
			roguePanel.add(rogue);
			roguePanel.add(wlPercentRogue);
			roguePanel.add(winsRogue);
			roguePanel.add(lossesRogue);
			roguePanel.setBorder(borderUp);
		paladinPanel.setLayout(layout);
			paladinPanel.setPreferredSize(new Dimension(150,75));
			paladinPanel.add(paladin);
			paladinPanel.add(wlPercentPaladin);
			paladinPanel.add(winsPaladin);
			paladinPanel.add(lossesPaladin);
			paladinPanel.setBorder(borderUp);
		warlockPanel.setLayout(layout);
			warlockPanel.setPreferredSize(new Dimension(150,75));
			warlockPanel.add(warlock);
			warlockPanel.add(wlPercentWarlock);
			warlockPanel.add(winsWarlock);
			warlockPanel.add(lossesWarlock);
			warlockPanel.setBorder(borderUp);
		hunterPanel.setLayout(layout);
			hunterPanel.setPreferredSize(new Dimension(150,75));
			hunterPanel.add(hunter);
			hunterPanel.add(wlPercentHunter);
			hunterPanel.add(winsHunter);
			hunterPanel.add(lossesHunter);
			hunterPanel.setBorder(borderUp);
		druidPanel.setLayout(layout);
			druidPanel.setPreferredSize(new Dimension(150,75));
			druidPanel.add(druid);
			druidPanel.add(wlPercentDruid);
			druidPanel.add(winsDruid);
			druidPanel.add(lossesDruid);
			druidPanel.setBorder(borderUp);
		// initialize borders
		newMatch.setBorder(borderUp);
		stats.setBorder(borderUp);
		newArenaMatch.setBorder(borderUp);
		newDeck.setBorder(borderUp);
		overview.setBorder(borderUp);
		cardsSearch.setBorder(borderUp);
		// nav event listeners
		newMatch.addMouseListener(this);
		stats.addMouseListener(this);
		newArenaMatch.addMouseListener(this);
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
			newMatchFrame.setResizable(false);
			newMatchFrame.setVisible(true);
			newMatchFrame.setSize(950,850);
			newMatchFrame.setLocation(this.getX(), this.getY());
			this.setVisible(false);
		}
		if(source == stats) {
			// new settings for the stats frame
			System.out.println("stats");
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
	}
	public void mousePressed(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {
	}
}