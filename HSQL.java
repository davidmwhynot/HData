// David Whynot 7.15.14 www.xdavesbanex.com
// "TONS OF SQL" ~ David 'Phreak' Turly (modified)
// It is good practice to remember that if we are going to use the same query in multiple places to have a method here for it
// but if we are only going to be using a query in one area of the program we should have a default query method and use that
// lines to port:
// line 181 - this section sets up the recent matches and creates the table that we store the matches in based on the username
// basically every user has their own unique table and within that table are their matches and decks and any other info we want to store about them
// EXCEPT for their username and password, those are in a different table that we store and is querried and compared upon login.
// line 240 - this section store takes a string of the username and password and stores it into the database table userpass, needs to be ported
// after that CheckIfUserPassExists needs to be ported because first we check to see if the username exists or not (a command we should have already created when instantiating users)
// so this one is the real one that checks to see if a specific username and password are matching. so first we look for username then password when a login is attempted, then we
// check again to see that if for that username there is an associated password that matches the one that was entered in the login attempt
import java.sql.*;
import javax.swing.*;
public class HSQL {
	String classDetReturnString;
	String typeDetReturnString;
	String rarityDetReturnString;
	String raceDetReturnString;
	String ahDetReturnString;
	private static String connectionURL = "jdbc:mysql://localhost:3306/hdata?useSSL=false"; // need to worry about securely communicating with the server
	private static String connectionUser = "root";
	private static String connectionPw = "";
	int xCreateCardDB;
	int checkIfUserPassExistsx;
	boolean checkIfUserPassExistsz;
	int checkIfUserExistsx, checkIfUserExistsy;
	boolean checkIfUserExistsz;
	double returnWins;
	double returnLosses;
	int cardCountString = 0;
	int cardCountInt = 0;
	int xGetNumberRecentMatches = 0;
	int rtrnYourClass;
	int rtrnVsClass;
	int rtrnWL;
	int rtrnGameType;
	int xReturnNumCards = 0;
	public int ReturnNumCards(String numCardsQuery) {
		try
		(
			Connection connReturnNumCards = DriverManager.getConnection(connectionURL, connectionUser, connectionPw);
			Statement stmtReturnNumCards = connReturnNumCards.createStatement();
		) {
	//		ResultSet rsetReturnNumCardsByMana = stmtReturnNumCardsByMana.executeQuery("select * from cards where cost = " + manaCost);
			ResultSet rsetReturnNumCards = stmtReturnNumCards.executeQuery(numCardsQuery);
			for(xReturnNumCards = 0; rsetReturnNumCards.next(); ++xReturnNumCards);
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		return xReturnNumCards;
	}
	public int MatchReturnYourClass(int mID, String user) {
		try
		(
			Connection connMatchReturnYourClass = DriverManager.getConnection(connectionURL, connectionUser, connectionPw);
			Statement stmtMatchReturnYourClass = connMatchReturnYourClass.createStatement();
		) {
			int mIDSub = mID - 1;
			ResultSet rsetMatchReturnYourClass = stmtMatchReturnYourClass.executeQuery("select class from " + user + "matchHistory where matchID = " + mIDSub);
			if(rsetMatchReturnYourClass.next())
				rtrnYourClass = rsetMatchReturnYourClass.getInt("class");
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		return rtrnYourClass;
	}
	public int MatchReturnVsClass(int mID, String user) {
		try
		(
			Connection connMatchReturnVsClass = DriverManager.getConnection(connectionURL, connectionUser, connectionPw);
			Statement stmtMatchReturnVsClass = connMatchReturnVsClass.createStatement();
		) {
			int mIDSub = mID - 1;
			ResultSet rsetMatchReturnVsClass = stmtMatchReturnVsClass.executeQuery("select against from " + user + "matchHistory where matchID = " + mIDSub);
			if(rsetMatchReturnVsClass.next())
				rtrnVsClass = rsetMatchReturnVsClass.getInt("against");
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		return rtrnVsClass;
	}
	public int MatchReturnWL(int mID, String user) {
		try
		(
			Connection connMatchReturnWL = DriverManager.getConnection(connectionURL, connectionUser, connectionPw);
			Statement stmtMatchReturnWL = connMatchReturnWL.createStatement();
		) {
			int mIDSub = mID - 1;
			ResultSet rsetMatchReturnWL = stmtMatchReturnWL.executeQuery("select wl from " + user + "matchHistory where matchID = " + mIDSub);
			if(rsetMatchReturnWL.next())
				rtrnWL = rsetMatchReturnWL.getInt("wl");
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		return rtrnWL;
	}
	public int MatchReturnGameType(int mID, String user) {
		try
		(
			Connection connMatchReturnGameType = DriverManager.getConnection(connectionURL, connectionUser, connectionPw);
			Statement stmtMatchReturnGameType = connMatchReturnGameType.createStatement();
		) {
			int mIDSub = mID - 1;
			ResultSet rsetMatchReturnGameType = stmtMatchReturnGameType.executeQuery("select gameType from " + user + "matchHistory where matchID = " + mIDSub);
			if(rsetMatchReturnGameType.next())
				rtrnGameType = rsetMatchReturnGameType.getInt("gameType");
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		return rtrnGameType;
	}
	// This SQL command, executed in the OVERVIEW frame, will retrieve the number of matches in recent
	// matches, so the appropriate amount can be displayed in the recent panel of the OVERVIEW frame
	public int GetNumberRecentMatches(String userGetNumberRecentMatches) {
		try
		(
			Connection connGetNumberRecentMatches = DriverManager.getConnection(connectionURL, connectionUser, connectionPw);
			Statement stmtGetNumberRecentMatches = connGetNumberRecentMatches.createStatement();
		) {
			ResultSet rsetGetNumberRecentMatches = stmtGetNumberRecentMatches.executeQuery("select * from " + userGetNumberRecentMatches + "matchHistory");
			while(rsetGetNumberRecentMatches.next())
				++xGetNumberRecentMatches;
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		return xGetNumberRecentMatches;
	}
	public void InitDatabase() { // THIS CAN PROBABLY BE REMOVED
		try
		(
			Connection connInitDatabase = DriverManager.getConnection(connectionURL, connectionUser, connectionPw);
			Statement stmtInitDatabase = connInitDatabase.createStatement();
		) {
			stmtInitDatabase.executeUpdate("create table if not exists userpass (user varchar(20), pass varchar(20))");
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	public void InitUserpass() {
		try
		(
			Connection connInitDatabase = DriverManager.getConnection(connectionURL, connectionUser, connectionPw);
			Statement stmtInitDatabase = connInitDatabase.createStatement();
		) {
			stmtInitDatabase.executeUpdate("create table if not exists userpass (user varchar(20), pass varchar(20))");
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	public void InitMatchHistory(String userInitMatchHistory) {
		try
		(
			Connection connInitMatchHistory = DriverManager.getConnection(connectionURL, connectionUser, connectionPw);
			Statement stmtInitMatchHistory = connInitMatchHistory.createStatement();
		) {
			// keep in mind that these commands ARE NOT EXECUTED HERE
			// this SQL statement simply INITIALIZES THE MATCH HISTORY DATABASE
			// a new database is created for each user!
			// first field in table "matchHistory" will be the class id for a match
			// class id's are:
				// 0 - warrior
				// 1 - shaman
				// 2 - rogue
				// 3 - paladin
				// 4 - hunter
				// 5 - druid
				// 6 - warlock
				// 7 - mage
				// 8 - priest
			// second field will be for a win or a loss with specified class
				// 0 - win
				// 1 - loss
			// third field will be for what class that match was played against
				// same integer assignments as "class" for what class was played
			// fourth field is the game mode/type
				// 0 - arena
				// 1 - normal (includes practice mode)
				// 2 - ranked
			// fifth field will be the unique "matchID"
			// need to call SQL commands when creating a new match too..
				// first get the value of the last match
				// if a previous match existed, the process is simple: add one to the previous "matchID" and assign it to the next match
				// if a previous match did not exist (this is the first match being recorded) then the SQL selector will return zero, and the value 0 will be assigned to that match for its "matchID"
			// planning on adding sixth field in the future for what deck was being used in the match
			stmtInitMatchHistory.executeUpdate("create table if not exists " + userInitMatchHistory + "matchHistory (class int, wl int, against int, gameType int, matchID int)");
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	public double getWins(String userGetWins, int classNumGetWins) {
		try
		(
			Connection connGetWins = DriverManager.getConnection(connectionURL, connectionUser, connectionPw);
			Statement stmtGetWins = connGetWins.createStatement();
		) {
			ResultSet rsetGetWins = stmtGetWins.executeQuery("select wl from " + userGetWins + "matchHistory where wl = 0 and class = " + classNumGetWins);
			while(rsetGetWins.next())
				++returnWins;
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		return returnWins;
	}
	public double getLosses(String userGetLosses, int classNumGetLosses) {
		try
		(
			Connection connGetLosses = DriverManager.getConnection(connectionURL, connectionUser, connectionPw);
			Statement stmtGetLosses = connGetLosses.createStatement();
		) {
			ResultSet rsetGetLosses = stmtGetLosses.executeQuery("select wl from " + userGetLosses + "matchHistory where wl = 1 and class = " + classNumGetLosses);
			while(rsetGetLosses.next())
				++returnLosses;
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		return returnLosses;
	}
	public static void StoreNewMatch(String storeNewMatcha) { // ur a fucking monkey for making the variables named like this
		try
		(
			Connection connStoreNewMatch = DriverManager.getConnection(connectionURL, connectionUser, connectionPw);
			Statement stmtStoreNewMatch = connStoreNewMatch.createStatement();
		) {
			stmtStoreNewMatch.executeUpdate(storeNewMatcha);
			JOptionPane.showMessageDialog(null,"Match Added!");
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	public static void StoreUsernamePassword(String storeUsernamePassworda, String storeUsernamePasswordb) {
		try
		(
			Connection connStoreUserPass = DriverManager.getConnection(connectionURL, connectionUser, connectionPw);
			Statement stmtStoreUserPass = connStoreUserPass.createStatement();
		) {
			stmtStoreUserPass.executeUpdate("insert into userpass values('" + storeUsernamePassworda + "', '" + storeUsernamePasswordb + "')");
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	public boolean CheckIfUserExists(String checkIfUserExistsa) {
		try
		(
			Connection connUserExists = DriverManager.getConnection(connectionURL, connectionUser, connectionPw);
			Statement stmtUserExists = connUserExists.createStatement();
		) {
			ResultSet usrExists = stmtUserExists.executeQuery("select user from userpass where user = '" + checkIfUserExistsa + "'");
			for(checkIfUserExistsx = 0; usrExists.next(); ++checkIfUserExistsx);
		}
		catch(SQLException ex) {
			ex.printStackTrace();
			checkIfUserExistsx = -1;
		}
		checkIfUserExistsy = checkIfUserExistsx;
		if(checkIfUserExistsy == 0)
			checkIfUserExistsz = false;
		else
			checkIfUserExistsz = true;
		return checkIfUserExistsz;
	}
	public boolean CheckIfUserPassExists(String checkIfUserPassExistsa, String checkIfUserPassExistsb) {
		try
		(
			Connection connUserPassExists = DriverManager.getConnection(connectionURL, connectionUser, connectionPw);
			Statement stmtUserPassExists = connUserPassExists.createStatement();
		) {
			ResultSet usrPassExists = stmtUserPassExists.executeQuery("select user, pass from userpass where user = '" + checkIfUserPassExistsa + "' and pass = '" + checkIfUserPassExistsb + "'");
			for(checkIfUserPassExistsx = 0; usrPassExists.next(); ++checkIfUserPassExistsx);
		}
		catch(SQLException ex) {
			ex.printStackTrace();
			checkIfUserPassExistsx = 0;
		}
		if(checkIfUserPassExistsx == 0)
			checkIfUserPassExistsz = false;
		else
			checkIfUserPassExistsz = true;
		return checkIfUserPassExistsz;
	}
	public String[][] GetCards(String cardParams, int numCards) {
		String[][] tempGetCardsArray = new String[numCards][9];
		try
		(
			Connection connGetCards = DriverManager.getConnection(connectionURL, connectionUser, connectionPw);
			Statement stmtGetCards = connGetCards.createStatement();
		) {
			ResultSet rsetGetCards = stmtGetCards.executeQuery(cardParams);
			for(int xGetCards = 0; rsetGetCards.next(); ++xGetCards) {
				tempGetCardsArray[xGetCards][0] = rsetGetCards.getString("name");
				tempGetCardsArray[xGetCards][1] = rsetGetCards.getString("text");
				tempGetCardsArray[xGetCards][2] = "" + rsetGetCards.getInt("cost");
				tempGetCardsArray[xGetCards][3] = typeDet(rsetGetCards.getInt("type"));
				tempGetCardsArray[xGetCards][4] = ahDet(rsetGetCards.getInt("attack"));
				tempGetCardsArray[xGetCards][5] = ahDet(rsetGetCards.getInt("health"));
				tempGetCardsArray[xGetCards][6] = rarityDet(rsetGetCards.getInt("rarity"));
				tempGetCardsArray[xGetCards][7] = classDet(rsetGetCards.getInt("class"));
				tempGetCardsArray[xGetCards][8] = raceDet(rsetGetCards.getInt("race"));
			}
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		return tempGetCardsArray;
	}
	public void createCardDB() {
		try
		(
			Connection connCreateCardDB = DriverManager.getConnection(connectionURL, connectionUser, connectionPw);
			Statement stmtCreateCardDB = connCreateCardDB.createStatement();
		) {
			// This creates the table, then the query tests to see if the card table has already been created on this machine or not (the table only needs to be created once)
				// 1 - first argument is for the name of the card
				// 2 - second argument is for the text on the card
				// 3 - third is for the mana cost of the card
				// 4 - fourth is for the type of card:
					// 0 - minion
					// 1 - spell
					// 2 - weapon
					// 3 - secret
				// 5 - fifth is for the attack of the card, -1 if n/a
				// 6 - sixth is for the health of the card, -1 if n/a
				// 7 - seventh is for the rarity of the card:
					// 0 - basic
					// 1 - common
					// 2 - rare
					// 3 - epic
					// 4 - legendary
				// 8 - eighth is for the class if the card is class specific:
					// -1 if not applicable (neutral)
					// 0 - warrior
					// 1 - shaman
					// 2 - rogue
					// 3 - paladin
					// 4 - hunter
					// 5 - druid
					// 6 - warlock
					// 7 - mage
					// 8 - priest
				// 9 - ninth is for the race of the card
					// -1 if not applicable
					// 0 - beast
					// 1 - totem
					// 2 - demon
					// 3 - pirate
					// 4 - murloc
					// 5 - dragon
			stmtCreateCardDB.executeUpdate("create table if not exists cards (name varchar(999), text varchar(9999), cost int, type int, attack int, health int, rarity int, class int, race int, id int)");
			ResultSet dbExists = stmtCreateCardDB.executeQuery("select * from cards");
			for(xCreateCardDB = 0; dbExists.next(); ++xCreateCardDB);
			if(xCreateCardDB == 0) {
				System.out.println("Creating Database, this may take a second...");
				// Druid
				stmtCreateCardDB.executeUpdate("insert into cards values('Innervate', 'Gain 2 Mana Crystals this turn only.', 0, 1, -1, -1, 0, 5, -1, 0)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Moonfire', 'Deal 1 damage.', 0, 1, -1, -1, 0, 5, -1, 1)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Claw', 'Give your hero +2 Attack this turn and 2 Armor.', 1, 1, -1, -1, 0, 5, -1, 2)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Naturalize', 'Destroy a minion. Your opponent draws 2 cards.', 1, 1, -1, -1, 1, 5, -1, 3)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Savagery', 'Deal damage equal to your heroes attack to a minion.', 1, 1, -1, -1, 2, 5, -1, 4)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Mark of the Wild', 'Give a minion Taunt and +2/+2.', 2, 1, -1, -1, 0, 5, -1, 5)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Power of the Wild', 'Choose One - Give your minions +1/+1 or Summon a 3/2 Panther (beast).', 2, 1, -1, -1, 1, 5, -1, 6)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Wild Growth', 'Gain an empty Mana Crystal.', 2, 1, -1, -1, 1, 5, -1, 7)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Wrath', 'Choose One - Deal 3 damage to a minion or 1 damage and draw a card', 2, 1, -1, -1, 1, 5, -1, 8)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Healing Touch', 'Restore 8 Health.', 3, 1, -1, -1, 0, 5, -1, 9)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Mark of Nature', 'Choose One - Give a minion +4 Attack or +4 Health and Taunt.', 3, 1, -1, -1, 1, 5, -1, 10)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Savage Roar', 'Give your characters +2 Attack this turn', 3, 1, -1, -1, 0, 5, -1, 11)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Bite', 'Give your hero +4 Attack this turn and +4 Armor.', 4, 1, -1, -1, 2, 5, -1, 12)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Soul of the Forest', 'Give your minions: Deathrattle: Summon a 2/2 Treant.', 4, 1, -1, -1, 1, 5, -1, 13)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Swipe', 'Deal 4 damage to an enemy and 1 damage to all other enemies.', 4, 1, -1, -1, 0, 5, -1, 14)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Keeper of the Grove', 'Choose One - Deal 2 damage or Silence a minion.', 4, 0, 2, 4, 2, 5, -1, 15)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Nourish', 'Choose one - Gain 2 Mana Crystals or Draw 3 cards', 5, 1, -1, -1, 2, 5, -1, 16)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Starfall', 'Choose one - Deal 5 damage to a minion or 2 damage to all enemy minions.', 5, 1, -1, -1, 2, 5, -1, 17)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Druid of the Claw', 'Choose One - Charge or +2 Health and Taunt.', 5, 0, 4, 4, 1, 5, -1, 18)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Force of Nature', 'Summon three 2/2 Treants with Charge that die at the end of the turn.', 6, 1, -1, -1, 3, 5, -1, 19)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Starfire', 'Deal 5 damage. Draw a card.', 6, 1, -1, -1, 0, 5, -1, 20)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Ancient of Lore', 'Choose One - Draw 2 cards or Restore 5 Health.', 7, 0, 5, 5, 3, 5, -1, 21)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Ancient of War', 'Choose One - +5 Attack or +5 Health and Taunt.', 7, 0, 5, 5, 3, 5, -1, 22)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Ironbark Protector', 'Taunt', 8, 0, 8, 8, 0, 5, -1, 23)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Cenarius', 'Choose One - Give your other minions +2/+2 or Summon two 2/2 Treants with Taunt.', 9, 0, 5, 8, 4, 5, -1, 24)");
				// Mage
				stmtCreateCardDB.executeUpdate("insert into cards values('Hunters Mark', 'Change a minions Health to 1', 0, 1, -1, -1, 0, 4, -1, 25)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Arcane Shot', 'Deal 2 damage.', 1, 1, -1, -1, 0, 4, -1, 26)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Bestial Wrath', 'Give a beast +2 Attack and Immune this turn.', 1, 1, -1, -1, 3, 4, -1, 27)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Flare', 'All minions lose Stealth. Destroy all enemy Secrets. Draw a card.', 1, 1, -1, -1, 2, 4, -1, 28)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Tracking', 'Look at the top 3 cards of your deck. Draw one and discard the others.', 1, 1, -1, -1, 0, 4, -1, 29)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Timber Wolf', 'Your other beasts have +1 Attack.', 1, 0, 1, 1, 0, 4, 0, 30)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Explosive Trap', 'Secret: When your hero is attacked, deal 2 damage to all enemies.', 2, 3, -1, -1, 1, 4, -1, 31)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Freezing Trap', 'Secret: When an enemy minion attacks, return it to its owners hand and it costs 2 more.', 2, 3, -1, -1, 1, 4, -1, 32)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Misdirection', 'Secret: When a character attacks your hero, instead he attacks another random character.', 2, 3, -1, -1, 2, 4, -1, 33)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Snake Trap', 'Secret: When one of your minions is attacked, summon three 1/1 Snakes.', 2, 3, -1, -1, 3, 4, -1, 34)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Snipe', 'Secret: When your opponent plays a minion, deal 4 damage to it.', 2, 3, -1, -1, 1, 4, -1, 35)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Unleash the Hounds', 'For each enemy minion, summon a 1/1 Hound with Charge.', 2, 1, -1, -1, 1, 4, -1, 36)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Scavenging Hyena', 'Whenever a friendly Beast dies, gain +2/+1.', 2, 0, 2, 2, 1, 4, 0, 37)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Starving Buzzard', 'Whenever you summon a Beast, draw a card.', 2, 0, 2, 1, 0, 4, 0, 38)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Eaglehorn Bow', 'Whenever a Secret is revealed, gain +1 Durability', 3, 2, 3, 2, 2, 4, -1, 39)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Animal Companion', 'Summon a random Beast Companion.', 3, 1, -1, -1, 0, 4, -1, 40)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Deadly Shot', 'Destroy a random enemy minion.', 3, 1, -1, -1, 1, 4, -1, 41)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Kill Command', 'Deal 3 damage. If you have a Beast, deal 5 damage instead.', 3, 1, -1, -1, 0, 4, -1, 42)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Multi-Shot', 'Deal 3 damage to two random enemy minions.', 4, 1, -1, -1, 0, 4, -1, 43)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Houndmaster', 'Battlecry: Give a friendly Beast +2/+2 and Taunt', 4, 9, 4, 3, 0, 4, -1, 44)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Explosive Shot', 'Deal 5 damage to a minion and 2 damage to adjacent ones.', 5, 1, -1, -1, 2, 4, -1, 45)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Tundra Rhino', 'Your Beasts have Charge', 5, 0, 2, 5, 0, 4, 0, 46)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Savannah Highmane', 'Deathrattle: Summon two 2/2 Hyenas', 6, 0, 6, 5, 2, 4, 0, 47)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Gladiators Longbow', 'Your hero is Immune while attacking', 7, 2, 5, 2, 3, 4, -1, 48)");
				stmtCreateCardDB.executeUpdate("insert into cards values('King Krush', 'Charge', 9, 0, 8, 8, 4, 4, 0, 49)");
				// Hunter
				stmtCreateCardDB.executeUpdate("insert into cards values('Arcane Missiles', 'Deal 3 damage randomly split among enemy characters.', 1, 1, -1, -1, 0, 7, -1, 50)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Ice Lance', 'Freeze a character. If it was already Frozen, deal 4 damage instead.', 1, 1, -1, -1, 1, 7, -1, 51)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Mirror Image', 'Summon two 0/2 minions with Taunt.', 1, 1, -1, -1, 0, 7, -1, 52)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Mana Wyrm', 'Whenever you cast a spell, gain +1 Attack.', 1, 0, 1, 3, 1, 7, -1, 53)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Arcane Explosion', 'Deal 1 damage to all enemy minions', 1, 1, -1, -1, 0, 7, -1, 54)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Frostbolt', 'Deal 3 damage to a character and Freeze it.', 2, 1, -1, -1, 0, 7, -1, 55)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Sorcerers Apprentice', 'Your spells cost 1 less.', 2, 0, 3, 2, 1, 7, -1, 56)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Arcane Intellect', 'Draw 2 cards.', 3, 1, -1, -1, 0, 7, -1, 57)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Counterspell', 'Secret: When your opponent casts a spell, Counter it.', 3, 3, -1, -1, 2, 7, -1, 58)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Frost Nova', 'Freeze all enemy minions.', 3, 1, -1, -1, 0, 7, -1, 59)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Ice Barrier', 'Secret: As soon as your hero is attacked, gain 8 armor', 3, 3, -1, -1, 1, 7, -1, 60)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Ice Block', 'Secret: When your hero takes fatal damage, prevent it and become Immune this turn.', 3, 3, -1, -1, 3, 7, -1, 61)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Mirror Entity', 'Secret: When your opponent plays a minion, summon a copy of it.', 3, 3, -1, -1, 1, 7, -1, 62)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Spellbender', 'Secret: When an enemy casts a spell on a minion, summon a 1/3 as the new target.', 3, 3, -1, -1, 3, 7, -1, 63)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Vaporize', 'Secret: When a minion attacks your hero, destroy it.', 3, 3, -1, -1, 2, 7, -1, 64)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Kirin Tor Mage', 'Battlecry: The next Secret you play this turn costs (0).', 3, 0, 4, 3, 2, 7, -1, 65)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Cone of Cold', 'Freeze a minion and the minions next to it, and deal 1 damage to them.', 4, 1, -1, -1, 1, 7, -1, 66)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Fireball', 'Deal 6 damage.', 4, 1, -1, -1, 0, 7, -1, 67)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Polymorph', 'Transform a minion into a 1/1 Sheep.', 4, 1, -1, -1, 0, 7, -1, 68)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Ethereal Arcanist', 'If you control a Secret at the end of your turn, gain +2/+2.', 4, 0, 3, 3, 2, 7, -1, 69)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Water Elemental', 'Freeze any character damaged by this minion.', 4, 0, 3, 6, 0, 7, -1, 70)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Blizzard', 'Deal 2 damage to all enemy minions and Freeze them.', 6, 1, -1, -1, 2, 7, -1, 71)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Flamestrike', 'Deal 4 damage to all enemy minions.', 7, 1, -1, -1, 0, 7, -1, 72)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Archmage Antonidas', 'Whenever you cast a spell, put a Fireball into your hand.', 7, 0, 5, 7, 4, 7, -1, 73)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Pyroblast', 'Deal 10 damage.', 10, -1, -1, 3, 3, 7, -1, 74)");
				// Paladin
				stmtCreateCardDB.executeUpdate("insert into cards values('Lights Justice', 'null', 1, 2, 1, 4, 0, 3, -1, 75)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Blessing of Might', 'Give a minion +3 Attack.', 1, 1, -1, -1, 0, 3, -1, 76)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Blessing of Wisdom', 'Choose a minion. Whenever it attacks, draw a card.', 1, 1, -1, -1, 1, 3, -1, 77)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Eye for an Eye', 'Secret: When your hero takes damage, deal that much damage to the enemy hero.', 1, 3, -1, -1, 1, 3, -1, 78)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Hand of Protection', 'Give a minion Divine Shield.', 1, 1, -1, -1, 0, 3, -1, 79)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Humility', 'Change a minions Attack to 1.', 1, 1, -1, -1, 0, 3, -1, 80)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Noble Sacrifice', 'Secret: When an enemy attacks, summon a 2/1 Defender as the new target.', 1, 3, -1, -1, 1, 3, -1, 81)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Redemption', 'Secret: When one of your minions dies, return it to life with 1 Health.', 1, 3, -1, -1, 1, 3, -1, 82)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Repentance', 'Secret: When your opponent plays a minion, reduce its Health to 1.', 1, 3, -1, -1, 1, 3, -1, 83)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Equality', 'Change the Health of ALL minions to 1.', 2, 1, -1, -1, 2, 3, -1, 84)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Holy Light', 'Restore 6 Health.', 2, 1, -1, -1, 0, 3, -1, 85)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Argent Protector', 'Battlecry: Give a friendly minion Divine Shield.', 2, 0, 2, 2, 1, 3, -1, 86)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Sword of Justice', 'Whenever you summon a minion, give it +1/+1 and this loses 1 Durability.', 3, 2, 1, 5, 3, 3, -1, 87)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Divine Favor', 'Draw cards until you have as many in hand as your opponent.', 3, 1, -1, -1, 2, 3, -1, 88)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Aldor Peacekeeper', 'Battlecry: Change an enemy minions Attack to 1.', 3, 0, 3, 3, 2, 3, -1, 89)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Truesilver Champion', 'Whenever your hero attacks, restore 2 Health to it.', 4, 2, 2, 4, 0, 3, -1, 90)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Blessing of Kings', 'Give a minion +4/+4', 4, 1, -1, -1, 1, 3, -1, 91)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Consecration', 'Deal 2 damage to all enemies.', 4, 1, -1, -1, 0, 3, -1, 92)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Hammer of Wrath', 'Deal 3 damage. Draw a card.', 4, 1, -1, -1, 0, 3, -1, 93)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Blessed Champion', 'Double a minions Attack.', 5, 1, -1, -1, 2, 3, -1, 94)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Holy Wrath', 'Draw a card and deal damage equal to its cost.', 5, 1, -1, -1, 2, 3, -1, 95)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Avenging Wrath', 'Deal 8 damage randomly split among enemy characters.', 6, 1, -1, -1, 3, 3, -1, 96)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Guardian of Kings', 'Battlecry: Restore 6 Health to your hero.', 7, 0, 5, 6, 0, 3, -1, 97)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Lay on Hands', 'Restore 8 Health. Draw 3 cards.', 8, 1, -1, -1, 3, 3, -1, 98)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Tirion Fordring', 'Divine Shield. Taunt. Deathrattle: Equip a 5/3 Ashbringer.', 8, 0, 6, 6, 4, 3, -1, 99)");
				// Priest
				stmtCreateCardDB.executeUpdate("insert into cards values('Circle of Healing', 'Restore 4 Health to ALL minions.', 0, 1, -1, -1, 1, 8, -1, 100)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Silence', 'Silence a minion.', 0, 1, -1, -1, 1, 8, -1, 101)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Holy Smite', 'Deal 2 damage.', 1, 1, -1, -1, 0, 8, -1, 102)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Inner Fire', 'Change a minions Attack to be equal to its Health.', 1, 1, -1, -1, 1, 8, -1, 103)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Mind Vision', 'Put a random card in your opponents hand into your hand.', 1, 1, -1, -1, 0, 8, -1, 104)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Power Word: Shield', 'Give a minion +2 Health. Draw a card.', 1, 1, -1, -1, 0, 8, -1, 105)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Northshire Cleric', 'Whenever a minion is healed, draw a card.', 1, 0, 1, 3, 0, 8, -1, 106)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Divine Spirit', 'Double a minions Health.', 2, 1, -1, -1, 0, 8, -1, 107)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Mind Blast', 'Deal 5 damage to the enemy hero.', 2, 1, -1, -1, 0, 8, -1, 108)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Shadow Word: Pain', 'Destroy a minion with 3 or less Attack.', 2, 1, -1, -1, 0, 8, -1, 109)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Lightwell', 'At the start of your turn, restore 3 Health to a damaged friendly character.', 2, 0, 0, 5, 2, 8, -1, 110)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Shadow Word: Death', 'Destroy a minion with an Attack of 5 or more.', 3, 1, -1, -1, 0, 8, -1, 111)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Shadowform', 'Your Hero Power becomes: Deal 2 damage. If already in Shadowform: 3 damage.', 3, 1, -1, -1, 3, 8, -1, 112)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Thoughtsteal', 'Copy 2 cards from your opponents deck and put them into your hand.', 3, 1, -1, -1, 1, 8, -1, 113)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Mass Dispel', 'Silence all enemy minions. Draw a card.', 4, 1, -1, -1, 2, 8, -1, 114)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Mindgames', 'Put a copy of a random minion from your opponents deck into the battlefield.', 4, 1, -1, -1, 3, 8, -1, 115)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Shadow Madness', 'Gain control of an enemy minion with 3 or less Attack until end of turn.',4 , 1, -1, -1, 2, 8, -1, 116)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Auchenai Soulpriest', 'Your cards and powers that restore Health now deal damage instead.', 4, 0, 3, 5, 2, 8, -1, 117)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Lightspawn', 'This minions Attack is always equal to its Health.', 4, 0, 0, 5, 1, 8, -1, 118)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Holy Nova', 'Deal 2 damage to all enemies. Restore 2 Health to all friendly characters.', 5, 1, -1, -1, 0, 8, -1, 119)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Holy Fire', 'Deal 5 damage. Restore 5 Health to your hero.', 5, 1, -1, -1, 2, 8, -1, 120)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Cabal Shadow Priest', 'Battlecry: Take control of an enemy minion that has 2 or less Attack.', 6, 0, 4, 5, 3, 8, -1, 121)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Temple Enforcer', 'Battlecry: Give a friendly minion +3 Health.', 6, 0, 6, 6, 1, 8, -1, 122)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Prophet Velen', 'Double the damage and healing of your spells and Hero Power.', 7, 0, 7, 7, 4, 8, -1, 123)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Mind Control', 'Take control of an enemy minion.', 10, 1, -1, -1, 0, 8, -1, 124)");
				// Rogue
				stmtCreateCardDB.executeUpdate("insert into cards values('Backstab', 'Deal 2 damage to an undamaged minion.', 0, 1, -1, -1, 0, 2, -1, 125)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Preparation', 'The next spell you cast this turn costs (3) less.', 0, 1, -1, -1, 3, 2, -1, 126)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Shadowstep', 'Return a friendly minion to your hand. It costs (2) less.', 0, 1, -1, -1, 1, 2, -1, 127)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Cold Blood', 'Give a minion +2 Attack. Combo: +4 Attack instead.', 1, 1, -1, -1, 1, 2, -1, 128)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Conceal', 'Give your minions Stealth until your next turn.', 1, 1, -1, -1, 1, 2, -1, 129)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Deadly Poison', 'Give your weapon +2 Attack.', 1, 1, -1, -1, 0, 2, -1, 130)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Sinister Strike', 'Deal 3 damage to the enemy hero.', 1, 1, -1, -1, 0, 2, -1, 131)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Betrayal', 'Force an enemy minion to deal its damage to the minions next to it.', 2, 1, -1, -1, 1, 2, -1, 132)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Blade Flurry', 'Destroy your weapon and deal its damage to all enemies.', 2, 1, -1, -1, 2, 2, -1, 133)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Eviscerate', 'Deal 2 damage. Combo: Deal 4 damage instead.', 2, 1, -1, -1, 1, 2, -1, 134)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Sap', 'Return an enemy minion to your opponents hand.', 2, 1, -1, -1, 0, 2, -1, 135)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Shiv', 'Deal 1 damage. Draw a card.', 2, 1, -1, -1, 0, 2, -1, 136)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Defias Ringleader', 'Combo: Summon a 2/1 Defias Bandit.', 2, 0, 2, 2, 1, 2, -1, 137)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Patient Assassin', 'Stealth. Destroy any minion damaged by this minion.', 2, 0, 1, 1, 3, 2, -1, 138)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Perditions Blade', 'Battlecry: Deal 1 damage. Combo: Deal 2 instead.', 3, 2, 2, 2, 2, 2, -1, 139)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Fan of Knives', 'Deal 1 damage to all enemy minions. Draw a card.', 3, 1, -1, -1, 0, 2, -1, 140)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Headcrack', 'Deal 2 damage to the enemy hero. Combo: Return this to your hand next turn.', 3, 1, -1, -1, 2, 2, -1, 141)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Edwin VanCleef', 'Combo: Gain +2/+2 for each card played earlier this turn.', 3, 0, 2, 2, 4, 2, -1, 142)");
				stmtCreateCardDB.executeUpdate("insert into cards values('SI:7 Agent', 'Combo: Deal 2 damage.', 3, 0, 3, 3, 2, 2, -1, 143)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Master of Disguise', 'Battlecry: Give a friendly minion Stealth.', 4, 0, 4, 4, 2, 2, -1, 144)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Assassins Blade', 'null', 5, 2, 3, 4, 0, 2, -1, 145)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Assassinate', 'Destroy an enemy minion.', 5, 1, -1, -1, 0, 2, -1, 146)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Vanish', 'Return all minions to their owners hand.', 6, 1, -1, -1, 0, 2, -1, 147)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Kidnapper', 'Combo: Return a minion to its owners hand.', 6, 0, 5, 5, 3, 2, -1, 148)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Sprint', 'Draw 4 cards.', 7, 1, -1, -1, 0, 2, -1, 149)");
				// Shaman
				stmtCreateCardDB.executeUpdate("insert into cards values('Ancestral Healing', 'Restore a minion to full Health and give it Taunt.', 0, 1, -1, -1, 0, 1, -1, 150)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Totemic Might', 'Give your Totems +2 Health.', 0, 1, -1, -1, 0, 1, -1, 151)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Earth Shock', 'Silence a minion, then deal 1 damage to it.', 1, 1, -1, -1, 1, 1, -1, 152)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Forked Lightning', 'Deal 2 damage to 2 random enemy minions. Overload: (2)', 1, 1, -1, -1, 1, 1, -1, 153)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Frost Shock', 'Deal 1 damage to an enemy character and Freeze it.', 1, 1, -1, -1, 0, 1, -1, 154)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Lightning Bolt', 'Deal 3 damage. Overload: (1)', 1, 1, -1, -1, 1, 1, -1, 155)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Rockbiter Weapon', 'Give a friendly character +3 Attack this turn', 1, 1, -1, -1, 0, 1, -1, 156)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Dust Devil', 'Windfury. Overload: (2)', 1, 0, 3, 1, 1, 1, -1, 157)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Stormforged Axe', 'Overload: (1)', 2, 2, 2, 2, 1, 1, -1, 158)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Ancestral Spirit', 'Choose a minion. When that minion is destroyed, return it to the battlefield.', 2, 1, -1, -1, 2, 1, -1, 159)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Windfury', 'Give a minion Windfury.', 2, 1, -1, -1, 0, 1, -1, 160)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Flametongue Totem', 'Adjacent minions have +2 Attack.', 2, 0, 0, 3, 0, 1, 1, 161)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Far Sight', 'Draw a card. That card costs (3) less.', 3, 1, -1, -1, 3, 1, -1, 162)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Feral Spirit', 'Summon two 2/3 Spirit Wolves with Taunt. Overload: (2)', 3, 1, -1, -1, 2, 1, -1, 163)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Hex', 'Transform a minion into a 0/1 Frog with Taunt.', 3, 1, -1, -1, 0, 1, -1, 164)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Lava Burst', 'Deal 5 damage. Overload: (2)', 3, 1, -1, -1, 2, 1, -1, 165)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Lightning Storm', 'Deal 2-3 damage to all enemy minions. Overload: (2)', 3, 1, -1, -1, 2, 1, -1, 166)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Mana Tide Totem', 'At the end of your turn, draw a card.', 3, 0, 0, 3, 2, 1, 1, 167)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Unbound Elemental', 'Whenever you play a card with Overload, Gain +1/+1.', 4, 0, 2, 4, 1, 1, -1, 168)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Windspeaker', 'Battlecry: Gives a friendly minion Windfury.', 5, 0, 3, 3, 0, 1, -1, 169)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Doomhammer', 'Windfury. Overload (2)', 5, 2, 2, 8, 3, 1, -1, 170)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Bloodlust', 'Give your minions +3 Attack this turn.', 5, 1, -1, -1, 0, 1, -1, 171)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Earth Elemental', 'Taunt. Overload: (3)', 5, 0, 7, 8, 3, 1, -1, 172)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Fire Elemental', 'Battlecry: Deal 3 damage.', 6, 0, 6, 5, 0, 1, -1, 173)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Al Akir the Windlord', 'Windfury, Charge, Divine Shield, Taunt', 8, 0, 3, 5, 4, 1, -1, 174)");
				// Warlock
				stmtCreateCardDB.executeUpdate("insert into cards values('Sacrificial Pact', 'Destroy a Demon. Restore 5 Health to your hero.', 0, 1, -1, -1, 0, 6, -1, 175)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Soulfire', 'Deal 4 damage. Discard a random card.', 0, 1, -1, -1, 0, 6, -1, 176)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Corruption', 'Choose a minion. At the start of your turn, destroy it.', 1, 1, -1, -1, 0, 6, -1, 177)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Mortal Coil', 'Deal 1 damage to a minion. If that kills it, draw a card.', 1, 1, -1, -1, 0, 6, -1, 178)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Power Overwhelming', 'Give a friendly minion +4/+4 until end of turn. Then, it dies. Horribly.', 1, 1, -1, -1, 1, 6, -1, 179)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Blood Imp', 'Stealth. At the end of your turn, give another random friendly minion +1 Health.', 1, 0, 0, 1, 1, 6, 2, 180)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Flame Imp', 'Battlecry: Deal 3 damage to your hero.', 1, 0, 3, 2, 1, 6, 2, 181)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Voidwalker', 'Taunt', 1, 0, 1, 3, 0, 6, 2, 182)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Demonfire', 'Deal 2 damage to a minion. If it is a friendly Demon, give it +2/+2 instead.', 2, 1, -1, -1, 1, 6, -1, 183)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Succubus', 'Battlecry: Discard a random card.', 2, 0, 4, 3, 0, 6, 2, 184)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Drain Life', 'Deal 2 damage. Restore 2 Health to your hero.', 3, 1, -1, -1, 0, 6, -1, 185)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Sense Demons', 'Put 2 random Demons from your deck into your hand.', 3, 1, -1, -1, 1, 6, -1, 186)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Shadow Bolt', 'Deal 4 damage to a minion.', 3, 1, -1, -1, 0, 6, -1, 187)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Felguard', 'Taunt, Battlecry: Destroy one of your Mana Crystals.', 3, 0, 3, 5, 2, 6, 2, 188)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Void Terror', 'Battlecry: Destroy the minions on either side of this minion and gain their Attack and Health.', 3, 0, 3, 3, 2, 6, 2, 189)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Hellfire', 'Deal 3 damage to ALL characters.', 4, 1, -1, -1, 0, 6, -1, 190)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Shadowflame', 'Destroy a friendly minion and deal its Attack damage to all enemy minions.', 4, 1, -1, -1, 2, 6, -1, 191)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Pit Lord', 'Battlecry: Deal 5 damage to your hero.', 4, 0, 5, 6, 3, 6, 2, 192)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Summoning Portal', 'Your minions cost (2) less, but not less than (1).', 4, 0, 0, 4, 1, 6, -1, 193)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Bane of Doom', 'Deal 2 damage to a character. If that kills it, summon a random Demon.', 5, 1, -1, -1, 3, 6, -1, 194)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Doomguard', 'Charge. Battlecry: Discard two random cards.', 5, 0, 5, 7, 2, 6, 2, 195)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Siphon Soul', 'Destroy a minion. Restore 3 Health to your hero.', 6, 1, -1, -1, 2, 6, -1, 196)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Dread Infernal', 'Battlecry: Deal 1 damage to ALL other characters.', 6, 0, 6, 6, 0, 6, 2, 197)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Twisting Nether', 'Destroy all minions.', 8, 1, -1, -1, 3, 6, -1, 198)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Lord Jaraxxus', 'Battlecry: Destroy your hero and replace him with Lord Jaraxxus.', 9, 0, 3, 15, 4, 6, 2, 199)");
				// Warrior
				stmtCreateCardDB.executeUpdate("insert into cards values('Inner Rage', 'Deal 1 damage to a minion and give it +2 Attack.', 0, 1, -1, -1, 1, 0, -1, 200)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Execute', 'Destroy a damaged enemy minion.', 1, 1, -1, -1, 0, 0, -1, 201)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Shield Slam', 'Deal 1 damage to a minion for each Armor you have.', 1, 1, -1, -1, 3, 0, -1, 202)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Upgrade!', 'If you have a weapon, give it +1/+1. Otherwise, equip a 1/3 weapon.', 1, 1, -1, -1, 2, 0, -1, 203)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Whirlwind', 'Deal 1 damage to ALL minions.', 1, 1, -1, -1, 0, 0, -1, 204)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Fiery War Axe', 'null', 2, 2, 3, 2, 0, 0, -1, 205)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Battle Rage', 'Draw a card for each damaged friendly character.', 2, 1, -1, -1, 1, 0, -1, 206)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Cleave', 'Deal 2 damage to two random enemy minions.', 2, 1, -1, -1, 0, 0, -1, 207)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Commanding Shout', 'Your minions cant be reduced below 1 Health this turn. Draw a card.', 2, 1, -1, -1, 2, 0, -1, 208)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Heroic Strike', 'Give your hero +4 Attack this turn.', 2, 1, -1, -1, 0, 0, -1, 209)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Rampage', 'Give a damaged minion +3/+3.', 2, 1, -1, -1, 1, 0, -1, 210)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Slam', 'Deal 2 damage to a minion. If it survives, draw a card.', 2, 1, -1, -1, 1, 0, -1, 211)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Armorsmith', 'Whenever a friendly minion takes damage, gain 1 Armor.', 2, 0, 1, 4, 2, 0, -1, 212)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Cruel Taskmaster', 'Battlecry: Deal 1 damage to a minion and give it +2 Attack.', 2, 0, 2, 2, 1, 0, -1, 213)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Charge', 'Give a friendly minion +2 Attack and Charge.', 3, 1, -1, -1, 0, 0, -1, 214)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Shield Block', 'Gain 5 Armor. Draw a card.', 3, 1, -1, -1, 0, 0, -1, 215)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Frothing Berserker', 'Whenever a minion takes damage, gain +1 Attack.', 3, 0, 2, 4, 2, 0, -1, 216)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Warsong Commander', 'Whenever you summon a minion with 3 or less Attack, give it Charge.', 3, 0, 2, 3, 0, 0, -1, 217)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Mortal Strike', 'Deal 4 damage. If you have 12 or less Health, deal 6 instead.', 4, 1, -1, -1, 2, 0, -1, 218)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Arathi Weaponsmith', 'Battlecry: Equip a 2/2 weapon.', 4, 0, 3, 3, 1, 0, -1, 219)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Kor kron Elite', 'Charge', 4, 0, 4, 3, 0, 0, -1, 220)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Arcanite Reaper', 'null', 5, 2, 5, 2, 0, 0, -1, 221)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Brawl', 'Destroy all minions except one. (chosen randomly)', 5, 1, -1, -1, 3, 0, -1, 222)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Gorehowl', 'Attacking a minion costs 1 Attack instead of 1 Durability.', 7, 2, 7, 1, 3, 0, -1, 223)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Grommash Hellscream', 'Charge, Enrage: +6 Attack', 8, 0, 4, 9, 4, 0, -1, 224)");
				// Neutral
				stmtCreateCardDB.executeUpdate("insert into cards values('Wisp', 'null', 0, 0, 1, 1, 1, -1, -1, 225)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Abusive Sergeant', 'Battlecry: Give a minion +2 Attack this turn.', 1, 0, 2, 1, 1, -1, -1, 226)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Angry Chicken', 'Enrage: +5 Attack.', 1, 0, 1, 1, 2, -1, 0, 227)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Argent Squire', 'Divine Shield', 1, 0, 1, 1, 1, -1, -1, 228)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Bloodsail Corsair', 'Battlecry: Remove 1 Durability from your opponents weapon.', 1, 0, 1, 2, 2, -1, 3, 229)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Elven Archer', 'Battlecry: Deal 1 damage.', 1, 0, 1, 1, 0, -1, -1, 230)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Goldshire Footman', 'Taunt', 1, 0, 1, 2, 0, -1, -1, 231)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Grimscale Oracle', 'ALL other Murlocs have +1 Attack.', 1, 0, 1, 1, 0, -1, 4, 232)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Hungry Crab', 'Battlecry: Destroy a Murloc and gain +2/+2.', 1, 0, 1, 2, 3, -1, 0, 233)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Leper Gnome', 'Deathrattle: Deal 2 damage to the enemy hero.', 1, 0, 2, 1, 1, -1, -1, 234)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Lightwarden', 'Whenever a character is healed, gain +2 Attack.', 1, 0, 1, 2, 2, -1, -1, 235)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Murloc Raider', 'null', 1, 0, 2, 1, 0, -1, 4, 236)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Murloc Tidecaller', 'Whenever a murloc is summoned, gain +1 Attack.', 1, 0, 1, 2, 2, -1, 4, 237)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Secretkeeper', 'Whenever a Secret is played, gain +1/+1.', 1, 0, 1, 2, 2, -1, -1, 238)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Shieldbearer', 'Taunt', 1, 0, 0, 4, 1, -1, -1, 239)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Southsea Deckhand', 'Has Charge while you have a weapon equipped.', 1, 0, 2, 1, 1, -1, 3, 240)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Stonetusk Boar', 'Charge', 1, 0, 1, 1, 0, -1, 0, 241)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Voodoo Doctor', 'Battlecry: Restore 2 Health.', 1, 0, 2, 1, 0, -1, -1, 242)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Worgen Infiltrator', 'Stealth', 1, 0, 2, 1, 1, -1, -1, 243)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Young Dragonhawk', 'Windfury', 1, 0, 1, 1, 1, -1, 0, 244)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Young Priestess', 'At the end of your turn, give another random friendly minion +1 Health.', 1, 0, 2, 1, 2, -1, -1, 245)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Acidic Swamp Ooze', 'Battlecry: Destroy your opponents weapon.', 2, 0, 3, 2, 0, -1, -1, 246)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Amani Berserker', 'Enrage: +3 Attack', 2, 0, 2, 3, 1, -1, -1, 247)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Ancient Watcher', 'Cant Attack.', 2, 0, 4, 5, 2, -1, -1, 248)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Bloodfen Raptor', 'null', 2, 0, 3, 2, 0, -1, 0, 249)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Bloodmage Thalnos', 'Spell Damage +1. Deathrattle: Draw a card.', 2, 0, 1, 1, 4, -1, -1, 250)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Bloodsail Raider', 'Battlecry: Gain Attack equal to the Attack of your weapon.', 2, 0, 2, 3, 1, -1, 3, 251)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Bluegill Warrior', 'Charge', 2, 0, 2, 1, 0, -1, 4, 252)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Captains Parrot', 'Battlecry: Put a random Pirate from your deck into your hand.', 2, 0, 1, 1, 3, -1, 0, 253)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Crazed Alchemist', 'Battlecry: Swap the Attack and Health of a minion.', 2, 0, 2, 2, 2, -1, -1, 254)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Dire Wolf Alpha', 'Adjacent minions have +1 Attack.', 2, 0, 2, 2, 1, -1, 0, 255)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Doomsayer', 'At the start of your turn, destroy ALL minions.', 2, 0, 0, 7, 3, -1, -1, 256)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Faerie Dragon', 'Cant be targeted by Spells or Hero Powers.', 2, 0, 3, 2, 1, -1, 5, 257)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Frostwolf Grunt', 'Taunt', 2, 0, 2, 2, 0, -1, -1, 258)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Ironbeak Owl', 'Battlecry: Silence a minion.', 2, 0, 2, 1, 1, -1, 0, 259)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Knife Juggler', 'After you summon a minion, deal 1 damage to a random enemy.', 2, 0, 3, 2, 2, -1, -1, 260)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Kobold Geomancer', 'Spell Damage +1', 2, 0, 2, 2, 0, -1, -1, 261)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Loot Hoarder', 'Deathrattle: Draw a card.', 2, 0, 2, 1, 1, -1, -1, 262)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Lorewalker Cho', 'Whenever a player casts a spell, put a copy into the other players hand.', 2, 0, 0, 4, 4, -1, -1, 263)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Mad Bomber', 'Battlecry: Deal 3 damage randomly split between all other characters.', 2, 0, 3, 2, 1, -1, -1, 264)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Mana Addict', 'Whenever you cast a spell, gain +2 Attack this turn.', 2, 0, 1, 3, 2, -1, -1, 265)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Mana Wraith', 'ALL minions cost (1) more.', 2, 0, 2, 2, 2, -1, -1, 266)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Master Swordsmith', 'At the end of your turn, give another random friendly minion +1 Attack.', 2, 0, 1, 3, 2, -1, -1, 267)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Millhouse Manastorm', 'Battlecry: Enemy spells cost (0) next turn.', 2, 0, 4, 4, 4, -1, -1, 268)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Murloc Tidehunter', 'Battlecry: Summon a 1/1 Murloc Scout.', 2, 0, 2, 1, 0, -1, 4, 269)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Nat Pagle', 'At the start of your turn, you have a 50% chance to draw an extra card.', 2, 0, 0, 4, 4, -1, -1, 270)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Novice Engineer', 'Battlecry: Draw a card.', 2, 0, 1, 1, 0, -1, -1, 271)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Pint-Sized Summoner', 'The first minion you play each turn costs (1) less.', 2, 0, 2, 2, 2, -1, -1, 272)");
				stmtCreateCardDB.executeUpdate("insert into cards values('River Crocolisk', 'null', 2, 0, 2, 3, 0, -1, 0, 273)");;
				stmtCreateCardDB.executeUpdate("insert into cards values('Sunfury Protector', 'Battlecry: Give adjacent minions Taunt.', 2, 0, 2, 3, 2, -1, -1, 274)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Wild Pyromancer', 'After you cast a spell, deal 1 damage to ALL minions.', 2, 0, 3, 2, 2, -1, -1, 275)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Youthful Brewmaster', 'Battlecry: Return a friendly minion from the battlefield to your hand.', 2, 0, 3, 2, 1, -1, -1, 276)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Acolyte of Pain', 'Whenever this minion takes damage, draw a card.', 3, 0, 1, 3, 1, -1, -1, 277)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Alarm-o-Bot', 'At the start of your turn, swap this minion with a random one in your hand.', 3, 0, 0, 3, 2, -1, -1, 278)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Arcane Golem', 'Charge. Battlecry: Give your opponent a Mana Crystal.', 3, 0, 4, 2, 2, -1, -1, 279)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Big Game Hunter', 'Battlecry: Destroy a minion with an Attack of 7 or more.', 3, 0, 4, 2, 3, -1, -1, 280)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Blood Knight', 'Battlecry: All minions lose Divine Shield. Gain +3/+3 for each shield lost.', 3, 0, 3, 3, 3, -1, -1, 281)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Coldlight Oracle', 'Battlecry: Each player draws 2 cards.', 3, 0, 2, 2, 2, -1, 4, 282)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Coldlight Seer', 'Battlecry: Give ALL other Murlocs +2 Health.', 3, 0, 2, 3, 2, -1, 4, 283)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Dalaran Mage', 'Spell Damage +1', 3, 0, 1, 4, 0, -1, -1, 284)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Demolisher', 'At the start of your turn, deal 2 damage to a random enemy.', 3, 0, 1, 4, 2, -1, -1, 285)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Earthen Ring Farseer', 'Battlecry: Restore 3 Health.', 3, 0, 3, 3, 1, -1, -1, 286)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Emperor Cobra', 'Destroy any minion damaged by this minion.', 3, 0, 2, 3, 2, -1, 0, 287)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Flesheating Ghoul', 'Whenever a minion dies, gain +1 Attack.', 3, 0, 2, 3, 1, -1, -1, 288)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Harvest Golem', 'Deathrattle: Summon a 2/1 Damaged Golem.', 3, 0, 2, 3, 1, -1, -1, 289)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Imp Master', 'At the end of your turn, deal 1 damage to this minion and summon 1/1 imp.', 3, 0, 0, 1, 2, -1, -1, 290)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Injured Blademaster', 'Battlecry: Deal 4 damage to HIMSELF.', 3, 0, 4, 3, 2, -1, -1, 291)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Ironforge Rifleman', 'Battlecry: Deal 1 damage.', 3, 0, 2, 2, 0, -1, -1, 292)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Ironfur Grizzly', 'Taunt', 3, 0, 3, 3, 0, -1, 0, 293)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Jungle Panther', 'Stealth', 3, 0, 4, 2, 1, -1, 0, 294)");
				stmtCreateCardDB.executeUpdate("insert into cards values('King Mukla', 'Battlecry: Give your opponent 2 Bananas.', 3, 0, 5, 5, 4, -1, 0, 295)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Magma Rager', 'null', 3, 0, 5, 1, 0, -1, -1, 296)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Mind Control Tech', 'Battlecry: If your opponent has 4 or more minions, take control of one at random.', 3, 0, 3, 3, 2, -1, -1, 297)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Murloc Warleader', 'ALL other Murlocs have +2/+1', 3, 0, 3, 3, 3, -1, 4, 298)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Questing Adventurer', 'Whenever you play a card, gain +1/+1.', 3, 0, 2, 2, 2, -1, -1, 299)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Raging Worgen', 'Enrage: Windfury and +1 Attack', 3, 0, 3, 3, 1, -1, -1, 300)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Raid Leader', 'Your other minions have +1 Attack.', 3, 0, 2, 2, 0, -1, -1, 301)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Razorfen Hunter', 'Battlecry: Summon a 1/1 Boar.', 3, 0, 2, 3, 0, -1, -1, 302)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Scarlet Crusader', 'Divine Shield', 3, 0, 3, 1, 1, -1, -1, 303)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Shattered Sun Cleric', 'Battlecry: Give a friendly minion +1/+1.', 3, 0, 3, 2, 0, -1, -1, 304)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Silverback Patriarch', 'Taunt', 3, 0, 1, 4, 0, -1, 0, 305)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Southsea Captain', 'Your other Pirates have +1/+1.', 3, 0, 3, 3, 3, -1, 3, 306)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Tauren Warrior', 'Taunt. Enrage: +3 Attack', 3, 0, 2, 3, 1, -1, -1, 307)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Thrallmar Farseer', 'Windfury', 3, 0, 2, 3, 1, -1, -1, 308)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Tinkmaster Overspark', 'Battlecry: Transform another random minion into a 5/5 Devilsaur or 1/1 Squirrel.', 3, 0, 3, 3, 4, -1, -1, 309)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Wolfrider', 'Charge', 3, 0, 3, 1, 0, -1, -1, 310)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Ancient Brewmaster', 'Battlecry: Return a friendly minion from the battlefield to your hand.', 4, 0, 5, 4, 1, -1, -1, 311)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Ancient Mage', 'Battlecry: Give adjacent minions Spell Damage +1.', 4, 0, 2, 5, 2, -1, -1, 312)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Chillwind Yeti', 'null', 4, 0, 4, 5, 0, -1, -1, 313)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Cult Master', 'Whenever one of your other minions dies, draw a card.', 4, 0, 4, 2, 1, -1, -1, 314)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Dark Iron Dwarf', 'Battle: Give a minion +2 Attack this turn.', 4, 0, 4, 4, 1, -1, -1, 315)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Defender of Argus', 'Battlecry: Give adjacent minions +1/+1 and Taunt.', 4, 0, 2, 3, 2, -1, -1, 316)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Dragonling Mechanic', 'Battlecry: Summon a 2/1 Mechanical Dragonling.', 4, 0, 2, 4, 0, -1, -1, 317)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Dread Corsair', 'Taunt. Costs (1) less per Attack of your weapon.', 4, 0, 3, 3, 1, -1, 3, 318)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Gnomish Inventor', 'Battlecry: Draw a card.', 4, 0, 2, 4, 0, -1, -1, 319)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Leeroy Jenkins', 'Charge. Battlecry: Summon two 1/1 Whelps for your opponent.', 4, 0, 6, 2, 4, -1, -1, 320)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Mogushan Warden', 'Taunt', 4, 0, 1, 7, 1, -1, -1, 321)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Oasis Snapjaw', 'null', 4, 0, 2, 7, 0, -1, 0, 322)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Ogre Magi', 'Spell Damage +1', 4, 0, 4, 4, 0, -1, -1, 323)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Old Murk-Eye', 'Charge. Has +1 Attack for each other Murloc on the battlefield.', 4, 0, 2, 4, 4, -1, 4, 324)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Senjin Shieldmasta', 'Taunt', 4, 0, 3, 5, 0, -1, -1, 325)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Silvermoon Guardian', 'Divine Shield', 4, 0, 3, 3, 1, -1, -1, 326)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Spellbreaker', 'Battlecry: Silence a minion.', 4, 0, 4, 3, 1, -1, -1, 327)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Stormwind Knight', 'Charge', 4, 0, 2, 5, 0, -1, -1, 328)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Twilight Drake', 'Battlecry: Gain +1 Health for each card in your hand.', 4, 0, 4, 1, 2, -1, 5, 329)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Violet Teacher', 'Whenever you cast a spell, summon a 1/1 Violet Apprentice.', 4, 0, 3, 5, 2, -1, -1, 330)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Abomination', 'Taunt. Deathrattle: Deal 2 damage to ALL characters.', 5, 0, 4, 4, 2, -1, -1, 331)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Azure Drake', 'Spell Damage +1. Battlecry: Draw a card.', 5, 0, 4, 4, 2, -1, 5, 332)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Booty Bay Bodyguard', 'Taunt', 5, 0, 5, 4, 0, -1, -1, 333)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Captain Greenskin', 'Battlecry: Give your weapon +1/+1.', 5, 0, 5, 4, 4, -1, 5, 334)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Darkscale Healer', 'Battlecry: Restore 2 Health to all friendly characters.', 5, 0, 4, 5, 0, -1, -1, 335)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Elite Tauren Chieftain', 'Battlecry: Give both players the power to ROCK! (with a Power Chord card)', 5, 0, 5, 5, 4, -1, -1, 336)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Faceless Manipulator', 'Battlecry: Choose a minion and become a copy of it.', 5, 0, 3, 3, 3, -1, -1, 337)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Fen Creeper', 'Taunt', 5, 0, 3, 6, 1, -1, -1, 338)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Frostwolf Warlord', 'Battlecry: Gain +1/+1 for each other friendly minion on the battlefield.', 5, 0, 4, 4, 0, -1, -1, 339)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Gadgetzan Auctioneer', 'Whenever you cast a spell, draw a card.', 5, 0, 4, 4, 2, -1, -1, 340)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Gurubashi Berserker', 'Whenever this minion takes damage, gain +3 Attack.', 5, 0, 2, 7, 0, -1, -1, 341)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Harrison Jones', 'Battlecry: Destroy your opponents weapon and draw cards equal to its Durability.', 5, 0, 5, 4, 4, -1, -1, 342)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Nightblade', 'Battlecry: Deal 3 damage to the enemy hero.', 5, 0, 4, 4, 0, -1, -1, 343)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Silver Hand Knight', 'Battlecry: Summon a 2/2 Squire.', 5, 0, 4, 4, 1, -1, -1, 344)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Spiteful Smith', 'Enrage: Your weapon has +2 Attack.', 5, 0, 4, 6, 1, -1, -1, 345)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Stampeding Kodo', 'Battlecry: Destroy a random enemy minion with 2 or less Attack.', 5, 0, 3, 5, 2, -1, 0, 346)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Stormpike Commando', 'Battlecry: Deal 2 damage.', 5, 0, 4, 2, 0, -1, -1, 347)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Stranglethorn Tiger', 'Stealth', 5, 0, 5, 5, 1, -1, 0, 348)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Venture Co. Mercenary', 'Your minions cost (3) more.', 5, 0, 7, 6, 1, -1, -1, 349)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Archmage', 'Spell Damage +1', 6, 0, 4, 7, 0, -1, -1, 350)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Argent Commander', 'Charge. Divine Shield', 6, 0, 4, 2, 2, -1, -1, 351)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Boulderfist Ogre', 'null', 6, 0, 6, 7, 0, -1, -1, 352)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Cairne Bloodhoof', 'Deathrattle: Summon a 4/5 Baine Bloodhoof.', 6, 0, 4, 5, 4, -1, -1, 353)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Frost Elemental', 'Battlecry: Freeze a character.', 6, 0, 5, 5, 1, -1, -1, 354)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Gelbin Mekkatorque', 'Battlery: Summon an AWESOME invention.', 6, 0, 6, 6, 4, -1, -1, 355)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Hogger', 'At then end of your turn, summon a 2/2 Gnoll with Taunt.', 6, 0, 4, 4, 4, -1, -1, 356)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Illidan Stormrage', 'Whenever you play a card, summon a 2/1 Flame of Azizinoth.', 6, 0, 7, 5, 4, -1, 2, 357)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Lord of the Arena', 'Taunt', 6, 0, 6, 5, 0, -1, -1, 358)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Priestess of Elune', 'Battlecry: Restore 4 Health to your hero.', 6, 0, 5, 4, 1, -1, -1, 359)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Reckless Rocketeer', 'Charge', 6, 0, 5, 2, 0, -1, -1, 360)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Sunwalker', 'Taunt. Divine Shield', 6, 0, 4, 5, 2, -1, -1, 361)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Sylvanas Windrunner', 'Deathrattle: Take control of a random enemy minion.', 6, 0, 5, 5, 4, -1, -1, 362)");
				stmtCreateCardDB.executeUpdate("insert into cards values('The Beast', 'Deathrattle: Summon a 3/3 Finkle Einhorn for your opponent.', 6, 0, 9, 7, 4, -1, 0, 363)");
				stmtCreateCardDB.executeUpdate("insert into cards values('The Black Knight', 'Battlecry: Destroy an enemy minion with Taunt.', 6, 0, 4, 5, 4, -1, -1, 364)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Windfury Harpy', 'Windfury', 6, 0, 4, 5, 1, -1, -1, 365)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Baron Geddon', 'At the end of your turn, deal 2 damage to ALL other characters.', 7, 0, 7, 5, 4, -1, -1, 366)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Core Hound', 'null', 7, 0, 9, 5, 0, -1, 0, 367)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Ravenholdt Assassin', 'Stealth', 7, 0, 7, 5, 2, -1, -1, 368)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Stormwind Champion', 'Your other minions have +1/+1.', 7, 0, 6, 6, 0, -1, -1, 369)");
				stmtCreateCardDB.executeUpdate("insert into cards values('War Golem', 'null', 7, 0, 7, 7, 0, -1, -1, 370)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Gruul', 'At the end of each turn, gain +1/+1.', 8, 0, 7, 7, 4, -1, -1, 371)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Ragnaros the Firelord', 'Cant Attack. At the end of your turn, deal 8 damage to a random enemy.', 8, 0, 8, 8, 4, -1, -1, 372)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Alexstrasza', 'Battlecry: Set a heros remaining Health to 15.', 9, 0, 8, 8, 4, -1, 5, 373)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Malygos', 'Spell Damage +5', 9, 0, 4, 12, 4, -1, 5, 374)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Nozdormu', 'Players only have 15 seconds to take their turns.', 9, 0, 8, 8, 4, -1, 5, 375)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Onyxia', 'Battlecry: Summon 1/1 Whelps until your side of the battlefield is full.', 9, 0, 8, 8, 4, -1, 5, 376)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Ysera', 'At the end of your turn, draw a Dream Card.', 9, 0, 4, 12, 4, -1, 5, 377)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Deathwing', 'Battlecry: Destroy all other minions and discard your hand.', 10, 0, 12, 12, 4, -1, 5, 378)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Sea Giant', 'Costs (1) less for each other minion on the battlefield.', 10, 0, 8, 8, 3, -1, -1, 379)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Mountain Giant', 'Costs (1) less for each other card in your hand.', 12, 0, 8, 8, 3, -1, -1, 380)");
				stmtCreateCardDB.executeUpdate("insert into cards values('Molten Giant', 'Costs (1) less for each damage your hero has taken.', 20, 0, 8, 8, 3, -1, -1, 381)");
				System.out.println("Card Database Successfully Created!");
			}
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	private String classDet(int classInt) {
		if(classInt == -1)
			classDetReturnString = "Neutral";
		if(classInt == 0)
			classDetReturnString = "Warrior";
		if(classInt == 1)
			classDetReturnString = "Shaman";
		if(classInt == 2)
			classDetReturnString = "Rogue";
		if(classInt == 3)
			classDetReturnString = "Paladin";
		if(classInt == 4)
			classDetReturnString = "Hunter";
		if(classInt == 5)
			classDetReturnString = "Druid";
		if(classInt == 6)
			classDetReturnString = "Warlock";
		if(classInt == 7)
			classDetReturnString = "Mage";
		if(classInt == 8)
			classDetReturnString = "Priest";
		return classDetReturnString;
	}
	private String ahDet(int ahInt) { // what is ah??
		if(ahInt == -1)
			ahDetReturnString = "n/a";
		else
			ahDetReturnString = "" + ahInt;
		return ahDetReturnString;
	}
	private String typeDet(int typeInt) { // redefine type
		if(typeInt == 0)
			typeDetReturnString = "Minion";
		if(typeInt == 1)
			typeDetReturnString = "Spell";
		if(typeInt == 2)
			typeDetReturnString = "Weapon";
		if(typeInt == 3)
			typeDetReturnString = "Secret";
		return typeDetReturnString;
	}
	private String rarityDet(int rarityInt) {
		if(rarityInt == 0)
			rarityDetReturnString = "Basic";
		if(rarityInt == 1)
			rarityDetReturnString = "Common";
		if(rarityInt == 2)
			rarityDetReturnString = "Rare";
		if(rarityInt == 3)
			rarityDetReturnString = "Epic";
		if(rarityInt == 4)
			rarityDetReturnString = "Legendary";
		return rarityDetReturnString;
	}
	private String raceDet(int raceInt) {
		if(raceInt == -1)
			raceDetReturnString = "None";
		if(raceInt == 0)
			raceDetReturnString = "Beast";
		if(raceInt == 1)
			raceDetReturnString = "Totem";
		if(raceInt == 2)
			raceDetReturnString = "Demon";
		if(raceInt == 3)
			raceDetReturnString = "Pirate";
		if(raceInt == 4)
			raceDetReturnString = "Murloc";
		if(raceInt == 5)
			raceDetReturnString = "Dragon";
		return raceDetReturnString;
	}
}