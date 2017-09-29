// David Whynot 8.2.14 www.xdavesbanex.com
// frame for searching the card database
import javax.swing.JOptionPane.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
public class CardsFrame extends JFrame implements MouseListener {
	String user;
	String checker = "";
	String finalQuery = "";
	String manaStr = "";
	String searchQuery;
	String searchNumQuery;
	int resultCount = 0;
	int firstHold = 0;
	int typeInt;
	int arrayCounter = 0;
	int newSearchCounter = 0;
	int manaToSQL;
	int cardCounter = 0;
	int p = 0;
	int firstRunCounter = 0;
	boolean firstRun = true;
	String[][] resultStrings = new String[400][9];
	JLabel[][] resultLbls = new JLabel[400][9];
	JLabel userName = new JLabel("XXXXXXXXXXXXXXXXXXXX");
	JLabel headerText = new JLabel("Cards Search");
	JLabel cardsSearch = new JLabel("Cards Search");
	JLabel newArenaMatch = new JLabel("New Arena Match");
	JLabel newMatch = new JLabel("New Match");
	JLabel stats = new JLabel("Stats");
	JLabel overview = new JLabel("Overview");
	JLabel newDeck = new JLabel("New Deck");
	JLabel cardNameLbl = new JLabel("Card Name: ");
	JLabel textLbl = new JLabel("Card Text: ");
	JLabel manaLbl = new JLabel("Mana Cost: ");
	JLabel cardTypeLbl = new JLabel("Card Type: ");
	JLabel attackLbl = new JLabel("Attack: ");
	JLabel healthLbl = new JLabel("Health: ");
	JLabel rarityLbl = new JLabel("Rarity: ");
	JLabel raceLbl = new JLabel("Race: ");
	JLabel classLbl = new JLabel("Class: ");
	JLabel searchLbl = new JLabel("Search");
	JLabel clearFrameLbl = new JLabel("Clear");
	JLabel resultsLbl = new JLabel("Results");
	JPanel resultsPanel = new JPanel();
	// Labels for result categories
	JLabel nameLblR = new JLabel("Name");
	JLabel textLblR = new JLabel("Text");
	JLabel costLblR = new JLabel("Cost");
	JLabel typeLblR = new JLabel("Type");
	JLabel attackLblR = new JLabel("Attack");
	JLabel healthLblR = new JLabel("Health");
	JLabel rarityLblR = new JLabel("Rarity");
	JLabel classLblR = new JLabel("Class");
	JLabel raceLblR = new JLabel("Race");
	JScrollPane resultsScrollPane = new JScrollPane(resultsPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	String[] rarityArray = {"Any","Basic","Common","Rare","Epic","Legendary"};
	String[] raceArray = {"Any","Beast","Totem","Demon","Pirate","Murloc","Dragon"};
	String[] typeArray = {"Any","Minion","Spell","Weapon","Secret"};
	String[] classArray = {"Any","Warrior","Shaman","Rogue","Paladin","Hunter","Druid","Warlock","Mage","Priest"};
	JComboBox<String> rarityDrop = new JComboBox<>(rarityArray);
	JComboBox<String> raceDrop = new JComboBox<>(raceArray);
	JComboBox<String> typeDrop = new JComboBox<>(typeArray);
	JComboBox<String> classDrop = new JComboBox<>(classArray);
	JTextField nameField = new JTextField(15);
	JTextField textField = new JTextField(15);
	JTextField manaField = new JTextField(2);
	JTextField attackField = new JTextField(2);
	JTextField healthField = new JTextField(2);
	Font userNameFont = new Font("Arial", Font.PLAIN, 17);
	Font headerFont = new Font("Arial", Font.BOLD, 17);
	Font mFrameFont = new Font("Arial", Font.PLAIN, 15);
	Font tinyFont = new Font("Arial", Font.PLAIN, 10);
	JPanel header = new JPanel();
	JPanel names = new JPanel();
	JPanel nav = new JPanel();
	JPanel bodyPanel = new JPanel();
	JPanel paramsPanel = new JPanel();
	JPanel paramsTopPanel = new JPanel();
	JPanel paramsMidPanel = new JPanel();
	JPanel paramsBotPanel = new JPanel();
	BevelBorder borderUp = new BevelBorder(1, Color.darkGray, Color.lightGray); // what the border looks like by default
	BevelBorder borderDown = new BevelBorder(0, Color.darkGray, Color.lightGray); // what the border looks like when you hover over it
	public CardsFrame(String userToCardsFrame) {
		super("HData");
		user = userToCardsFrame;
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
				bodyPanel.add(paramsPanel);
					paramsPanel.setLayout(new BorderLayout());
					paramsPanel.setPreferredSize(new Dimension(925, 100));
					paramsPanel.add(paramsTopPanel, BorderLayout.NORTH);
						paramsTopPanel.setLayout(new FlowLayout());
						paramsTopPanel.add(cardNameLbl);
							paramsTopPanel.add(nameField);
						paramsTopPanel.add(textLbl);
							paramsTopPanel.add(textField);
						paramsTopPanel.add(manaLbl);
							paramsTopPanel.add(manaField);
						paramsTopPanel.add(attackLbl);
							paramsTopPanel.add(attackField);
						paramsTopPanel.add(healthLbl);
							paramsTopPanel.add(healthField);
					paramsPanel.add(paramsMidPanel, BorderLayout.CENTER);
						paramsMidPanel.setLayout(new FlowLayout());
						paramsMidPanel.add(cardTypeLbl);
							paramsMidPanel.add(typeDrop);
						paramsMidPanel.add(raceLbl);
							paramsMidPanel.add(raceDrop);
						paramsMidPanel.add(rarityLbl);
							paramsMidPanel.add(rarityDrop);
						paramsMidPanel.add(classLbl);
							paramsMidPanel.add(classDrop);
					paramsPanel.add(paramsBotPanel, BorderLayout.SOUTH);
						paramsBotPanel.setLayout(new FlowLayout());
						paramsBotPanel.add(searchLbl);
						paramsBotPanel.add(clearFrameLbl);
				bodyPanel.add(resultsLbl);
					resultsLbl.setPreferredSize(new Dimension(925, 40));
					resultsLbl.setFont(headerFont);
				bodyPanel.add(nameLblR);
					nameLblR.setPreferredSize(new Dimension(180,50));
					nameLblR.setBorder(borderUp);
				bodyPanel.add(textLblR);
					textLblR.setPreferredSize(new Dimension(230,50));
					textLblR.setBorder(borderUp);
				bodyPanel.add(costLblR);
					costLblR.setPreferredSize(new Dimension(55,50));
					costLblR.setBorder(borderUp);
				bodyPanel.add(typeLblR);
					typeLblR.setPreferredSize(new Dimension(80,50));
					typeLblR.setBorder(borderUp);
				bodyPanel.add(attackLblR);
					attackLblR.setPreferredSize(new Dimension(55,50));
					attackLblR.setBorder(borderUp);
				bodyPanel.add(healthLblR);
					healthLblR.setPreferredSize(new Dimension(55,50));
					healthLblR.setBorder(borderUp);
				bodyPanel.add(rarityLblR);
					rarityLblR.setPreferredSize(new Dimension(80,50));
					rarityLblR.setBorder(borderUp);
				bodyPanel.add(classLblR);
					classLblR.setPreferredSize(new Dimension(75,50));
					classLblR.setBorder(borderUp);
				bodyPanel.add(raceLblR);
					raceLblR.setPreferredSize(new Dimension(80,50));
					raceLblR.setBorder(borderUp);
				bodyPanel.add(resultsScrollPane);
						resultsScrollPane.setPreferredSize(new Dimension(925, 500));
						resultsPanel.setPreferredSize(new Dimension(900, 60));
						resultsPanel.setLayout(new FlowLayout());
		// initialize borders
		newMatch.setBorder(borderUp);
		newArenaMatch.setBorder(borderUp);
		stats.setBorder(borderUp);
		newDeck.setBorder(borderUp);
		overview.setBorder(borderUp);
		searchLbl.setBorder(borderUp);
		clearFrameLbl.setBorder(borderUp);
		cardsSearch.setBorder(borderUp);
		// add event listeners
		newMatch.addMouseListener(this);
		newArenaMatch.addMouseListener(this);
		stats.addMouseListener(this);
		newDeck.addMouseListener(this);
		overview.addMouseListener(this);
		searchLbl.addMouseListener(this);
		clearFrameLbl.addMouseListener(this);
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
		if(source == searchLbl)
			searchLbl.setBorder(borderDown);
		if(source == clearFrameLbl)
			clearFrameLbl.setBorder(borderDown);
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
		if(source == searchLbl)
			searchLbl.setBorder(borderUp);
		if(source == clearFrameLbl)
			clearFrameLbl.setBorder(borderUp);
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
		}
		if(source == clearFrameLbl) {
			System.out.println("clear");
		}
		if(source == searchLbl) {
			System.out.println("Search");
			// remove the previous results before displaying the new ones
			for(int p = 0; p < cardCounter; ++p)
				for(int q = 0; q < 9; ++q)
					resultsPanel.remove(resultLbls[p][q]);
			// reset containers
			cardCounter = 0;
			searchQuery = "select * from cards where ";
			// first, we must get the total of number of cards that meet the search requirements
			// in order to do this, we have to construct a SQL query and then pass it to HSQL.ReturnNumCards(String query)
			manaStr = manaField.getText();
			String addQueryMana = "cost = ";
			boolean notFirst = false;
			if(!manaStr.equals("")) {
				addQueryMana = addQueryMana + manaStr;
				searchQuery = searchQuery + addQueryMana;
				notFirst = true;
			}
			String cardNameStr = nameField.getText();
			String addQueryName = "name like '%";
			if(!(cardNameStr.equals(""))) {
				if(notFirst) {
					addQueryName = addQueryName + cardNameStr + "%'";
					searchQuery = searchQuery + " and " + addQueryName;
				}
				else {
					addQueryName = addQueryName + cardNameStr + "%'";
					searchQuery = searchQuery + addQueryName;
					notFirst = true;
				}
			}
			String cardTextStr = textField.getText();
			String addQueryText = "text like '%";
			if(!(cardTextStr.equals(""))) {
				if(notFirst) {
					addQueryText = addQueryText + cardTextStr + "%'";
					searchQuery = searchQuery + " and " + addQueryText;
				}
				else {
					addQueryText = addQueryText + cardTextStr + "%'";
					searchQuery = searchQuery + addQueryText;
					notFirst = true;
				}
			}
			int rarityInt = rarityDrop.getSelectedIndex();
			--rarityInt;
			String addQueryRarity = "rarity = ";
			if(!(rarityInt == -1)) {
				if(notFirst) {
					addQueryRarity = addQueryRarity + rarityInt;
					searchQuery = searchQuery + " and " + addQueryRarity;
				}
				else {
					addQueryRarity = addQueryRarity + rarityInt;
					searchQuery = searchQuery + addQueryRarity;
					notFirst = true;
				}
			}
			int classInt = classDrop.getSelectedIndex();
			--classInt;
			String addQueryClass = "class = ";
			if(!(classInt == -1)) {
				if(notFirst) {
					addQueryClass = addQueryClass + classInt;
					searchQuery = searchQuery + " and " + addQueryClass;
				}
				else {
					addQueryClass = addQueryClass + classInt;
					searchQuery = searchQuery + addQueryClass;
					notFirst = true;
				}
			}
			int raceInt = raceDrop.getSelectedIndex();
			--raceInt;
			String addQueryRace = "race = ";
			if(!(raceInt == -1)) {
				if(notFirst) {
					addQueryRace = addQueryRace + raceInt;
					searchQuery = searchQuery + " and " + addQueryRace;
				}
				else {
					addQueryRace = addQueryRace + raceInt;
					searchQuery = searchQuery + addQueryRace;
					notFirst = true;
				}
			}
			int typeInt = typeDrop.getSelectedIndex();
			--typeInt;
			String addQueryType = "type = ";
			if(!(typeInt == -1)) {
				if(notFirst) {
					addQueryType = addQueryType + typeInt;
					searchQuery = searchQuery + " and " + addQueryType;
				}
				else {
					addQueryType = addQueryType + typeInt;
					searchQuery = searchQuery + addQueryType;
					notFirst = true;
				}
			}
			String attackStr = attackField.getText();
			String addQueryAttack = "attack = ";
			if(!attackStr.equals("")) {
				if(notFirst) {
					addQueryAttack = addQueryAttack + attackStr;
					searchQuery = searchQuery + " and " + addQueryAttack;
				}
				else {
					addQueryAttack = addQueryAttack + attackStr;
					searchQuery = searchQuery + addQueryAttack;
					notFirst = true;
				}
			}
			String healthStr = healthField.getText();
			String addQueryHealth = "health = ";
			if(!healthStr.equals("")) {
				if(notFirst) {
					addQueryHealth = addQueryHealth + healthStr;
					searchQuery = searchQuery + " and " + addQueryHealth;
				}
				else {
					addQueryHealth = addQueryHealth + healthStr;
					searchQuery = searchQuery + addQueryHealth;
					notFirst = true;
				}
			}
			HSQL numQueryObj = new HSQL();
			System.out.println(searchQuery);
			cardCounter = numQueryObj.ReturnNumCards(searchQuery);
			// then instantiate array based on cardcounter
			for(int u = 0; u < cardCounter; ++u)
				for(int o = 0; o < 9; ++o)
					resultStrings[u][o] = "";
			// then pass that number along with the search parameters to the SQL command to return the array of cards to be displayed
			// REMEMBER we are passing the SQL query to the HSQL class now
			// HSQL is no longer constructing the query, we must construct it here
			if(!(cardCounter == 0)) {
				HSQL getCards = new HSQL();
				String[][] tempArray = getCards.GetCards(searchQuery, cardCounter); // putting results in a temporary array of unspecified size
				for(int k = 0; k < cardCounter; ++k)
					for(int v = 0; v < 9; ++v)
						resultStrings[k][v] = tempArray[k][v]; // we are taking the results from the temp array and putting them into the array that we can pull from to diplay
			}
			for(int i = 0; i < cardCounter; i++) {
				resultLbls[i][0] = new JLabel("<html>" + resultStrings[i][0] + "</html>");
				resultLbls[i][1] = new JLabel("<html>" + resultStrings[i][1] + "</html>");
				resultLbls[i][2] = new JLabel("<html>" + resultStrings[i][2] + "</html>");
				resultLbls[i][3] = new JLabel("<html>" + resultStrings[i][3] + "</html>");
				resultLbls[i][4] = new JLabel("<html>" + resultStrings[i][4] + "</html>");
				resultLbls[i][5] = new JLabel("<html>" + resultStrings[i][5] + "</html>");
				resultLbls[i][6] = new JLabel("<html>" + resultStrings[i][6] + "</html>");
				resultLbls[i][7] = new JLabel("<html>" + resultStrings[i][7] + "</html>");
				resultLbls[i][8] = new JLabel("<html>" + resultStrings[i][8] + "</html>");
			}
			for(int a = 0; a < cardCounter; ++a) {
				checker = resultLbls[a][0].getText(); // wtf are u doing here????? need to understand how the array that is returned by the query is structured. structured based on my database right?
				if(!checker.equals("<html></html>")) {
					resultsPanel.add(resultLbls[a][0]);
					resultLbls[a][0].setPreferredSize(new Dimension(175,50));
					resultLbls[a][0].setBorder(borderUp);
				}	
				checker = resultLbls[a][1].getText();
				if(!checker.equals("<html></html>")) {
					resultsPanel.add(resultLbls[a][1]);
					resultLbls[a][1].setPreferredSize(new Dimension(225,50));
					resultLbls[a][1].setBorder(borderUp);
				}
				checker = resultLbls[a][2].getText();
				if(!checker.equals("<html></html>")) {
					resultsPanel.add(resultLbls[a][2]);
					resultLbls[a][2].setPreferredSize(new Dimension(50,50));
					resultLbls[a][2].setBorder(borderUp);
				}
				checker = resultLbls[a][3].getText();
				if(!checker.equals("<html></html>")) {
					resultsPanel.add(resultLbls[a][3]);
					resultLbls[a][3].setPreferredSize(new Dimension(75,50));
					resultLbls[a][3].setBorder(borderUp);
				}
				checker = resultLbls[a][4].getText();
				if(!checker.equals("<html></html>")) {
					resultsPanel.add(resultLbls[a][4]);
					resultLbls[a][4].setPreferredSize(new Dimension(50,50));
					resultLbls[a][4].setBorder(borderUp);
				}
				checker = resultLbls[a][5].getText();
				if(!checker.equals("<html></html>")) {
					resultsPanel.add(resultLbls[a][5]);
					resultLbls[a][5].setPreferredSize(new Dimension(50,50));
					resultLbls[a][5].setBorder(borderUp);
				}
				checker = resultLbls[a][6].getText();
				if(!checker.equals("<html></html>")) {
					resultsPanel.add(resultLbls[a][6]);
					resultLbls[a][6].setPreferredSize(new Dimension(75,50));
					resultLbls[a][6].setBorder(borderUp);
				}
				checker = resultLbls[a][7].getText();
				if(!checker.equals("<html></html>")) {
					resultsPanel.add(resultLbls[a][7]);
					resultLbls[a][7].setPreferredSize(new Dimension(70,50));
					resultLbls[a][7].setBorder(borderUp);
				}
				checker = resultLbls[a][8].getText();
				if(!checker.equals("<html></html>")) {
					resultsPanel.add(resultLbls[a][8]);
					resultLbls[a][8].setPreferredSize(new Dimension(75,50));
					resultLbls[a][8].setBorder(borderUp);
				}
			}
			resultsPanel.setPreferredSize(new Dimension(900, 60 * cardCounter));
		}
	}
	public void mousePressed(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {
	}
	public void setLastQueryResultCount(int lastQueryResultCount) {
		resultCount = lastQueryResultCount;
	}
	public int getLastQueryResultCount() {
		return resultCount;
	}
}