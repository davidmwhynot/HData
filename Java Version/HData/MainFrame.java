// David Whynot 1.13.14 www.xdavesbanex.com
import javax.swing.JOptionPane.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
public class MainFrame extends JFrame implements MouseListener {
	String classDetReturnString;
	int rcntMatch;
	String user;
	// JLabel's for the RECENT pane
		// no matches
			JLabel noMatches = new JLabel("No matches played yet!");
		// match one
			JPanel recentMatchOne = new JPanel();
			JLabel matchOneVs = new JLabel("");
			JLabel matchOneResult = new JLabel("");
		// match two
			JPanel recentMatchTwo = new JPanel();
			JLabel matchTwoVs = new JLabel("");
			JLabel matchTwoResult = new JLabel("");
		// match three
			JPanel recentMatchThree = new JPanel();
			JLabel matchThreeVs = new JLabel("");
			JLabel matchThreeResult = new JLabel("");
		// match four
			JPanel recentMatchFour = new JPanel();
			JLabel matchFourVs = new JLabel("");
			JLabel matchFourResult = new JLabel("");
		// match five
			JPanel recentMatchFive = new JPanel();
			JLabel matchFiveVs = new JLabel("");
			JLabel matchFiveResult = new JLabel("");
	JLabel userName = new JLabel("XXXXXXXXXXXXXXXXXXXX");
	JLabel headerText = new JLabel("Overview");
	JLabel cardsSearch = new JLabel("Cards Search");
	JLabel recent = new JLabel("Recent");
	JLabel ranking = new JLabel("Ranking");
	JLabel bestDecks = new JLabel("Best Decks");
	JLabel newMatch = new JLabel("New Match");
	JLabel stats = new JLabel("Stats");
	JLabel newArenaMatch = new JLabel("New Arena Match");
	JLabel overview = new JLabel("Overview");
	JLabel newDeck = new JLabel("New Deck");
	Font userNameFont = new Font("Arial", Font.PLAIN, 17);
	Font headerFont = new Font("Arial", Font.BOLD, 17);
	Font mFrameFont = new Font("Arial", Font.PLAIN, 15);
	JPanel header = new JPanel();
	JPanel names = new JPanel();
	JPanel nav = new JPanel();
	JPanel bodyPanel = new JPanel();
	JPanel recentPanel = new JPanel();
	JPanel rankingPanel = new JPanel();
	JPanel bestDecksPanel = new JPanel();
	BevelBorder borderUp = new BevelBorder(1, Color.darkGray, Color.lightGray); // what the border looks like by default
	BevelBorder borderDown = new BevelBorder(0, Color.darkGray, Color.lightGray); // what the border looks like when you hover over it
	public MainFrame(String userToMain) {
		super("HData");
		user = userToMain;
		HSQL starting = new HSQL();
		starting.InitMatchHistory(user);
		HSQL creatingCardDB = new HSQL();
		creatingCardDB.createCardDB();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container con = getContentPane();
			con.setLayout(new BorderLayout());
			con.add(header, BorderLayout.NORTH);
			con.add(bodyPanel, BorderLayout.CENTER);
				//bodyPanel.setLayout(new GridLayout(1, 3, 5, 5));
				//bodyPanel.add(rankingPanel);
				bodyPanel.add(recentPanel);
					recentPanel.setLayout(new GridLayout(6,1));
					recentPanel.setBorder(borderUp);
					recentPanel.add(recent); // add RECENT to RECENTPANEL
						recent.setFont(mFrameFont);
				//bodyPanel.add(bestDecksPanel);
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
				HSQL rcnt = new HSQL();
				rcntMatch = rcnt.GetNumberRecentMatches(user);
				if(rcntMatch == 0) {
					recentPanel.add(noMatches);
				}
				if(rcntMatch == 1) {
					HSQL mOneObj = new HSQL();
					String mOneYourClass = classDet(mOneObj.MatchReturnYourClass(rcntMatch, user));
					String mOneVsClass = classDet(mOneObj.MatchReturnVsClass(rcntMatch, user));
					String mOneResult = "";
					int mOneCalcWL = mOneObj.MatchReturnWL(rcntMatch, user);
					int mOneGetGameType = mOneObj.MatchReturnGameType(rcntMatch, user);
					if(mOneCalcWL == 0) {
						if(mOneGetGameType == 0) {
							mOneResult = "Arena Win";
						}
						else if(mOneGetGameType == 1) {
							mOneResult = "Unranked Win";
						}
						else if(mOneGetGameType == 2) {
							mOneResult = "Ranked Win";
						}
						else {
							mOneResult = "Win";
						}
					}
					if(mOneCalcWL == 1) {
						if(mOneGetGameType == 0) {
							mOneResult = "Arena Loss";
						}
						else if(mOneGetGameType == 1) {
							mOneResult = "Unranked Loss";
						}
						else if(mOneGetGameType == 2) {
							mOneResult = "Ranked Loss";
						}
						else {
							mOneResult = "Loss";
						}
					}
					String mOneVs = "You: " + mOneYourClass + " Vs: " + mOneVsClass;
					recentPanel.add(recentMatchOne);
						recentMatchOne.setLayout(new FlowLayout());
						recentMatchOne.setBorder(borderUp);
						recentMatchOne.add(matchOneVs);
							matchOneVs.setText(mOneVs);
						recentMatchOne.add(matchOneResult);
							matchOneResult.setText(mOneResult);
				}
				if(rcntMatch == 2) {
					HSQL mTwoObj = new HSQL();
					String mTwoYourClass = classDet(mTwoObj.MatchReturnYourClass(rcntMatch, user));
					String mTwoVsClass = classDet(mTwoObj.MatchReturnVsClass(rcntMatch, user));
					String mTwoResult = "";
					int mTwoCalcWL = mTwoObj.MatchReturnWL(rcntMatch, user);
					int mTwoGetGameType = mTwoObj.MatchReturnGameType(rcntMatch, user);
					if(mTwoCalcWL == 0) {
						if(mTwoGetGameType == 0) {
							mTwoResult = "Arena Win";
						}
						else if(mTwoGetGameType == 1) {
							mTwoResult = "Unranked Win";
						}
						else if(mTwoGetGameType == 2) {
							mTwoResult = "Ranked Win";
						}
						else {
							mTwoResult = "Win";
						}
					}
					if(mTwoCalcWL == 1) {
						if(mTwoGetGameType == 0) {
							mTwoResult = "Arena Loss";
						}
						else if(mTwoGetGameType == 1) {
							mTwoResult = "Unranked Loss";
						}
						else if(mTwoGetGameType == 2) {
							mTwoResult = "Ranked Loss";
						}
						else {
							mTwoResult = "Loss";
						}
					}
					String mTwoVs = "You: " + mTwoYourClass + " Vs: " + mTwoVsClass;
					recentPanel.add(recentMatchTwo);
						recentMatchTwo.setLayout(new FlowLayout());
						recentMatchTwo.setBorder(borderUp);
						recentMatchTwo.add(matchTwoVs);
							matchTwoVs.setText(mTwoVs);
						recentMatchTwo.add(matchTwoResult);
							matchTwoResult.setText(mTwoResult);
					HSQL mOneObj = new HSQL();
					String mOneYourClass = classDet(mOneObj.MatchReturnYourClass(rcntMatch - 1, user));
					String mOneVsClass = classDet(mOneObj.MatchReturnVsClass(rcntMatch - 1, user));
					String mOneResult = "";
					int mOneCalcWL = mOneObj.MatchReturnWL(rcntMatch - 1, user);
					int mOneGetGameType = mOneObj.MatchReturnGameType(rcntMatch - 1, user);
					if(mOneCalcWL == 0) {
						if(mOneGetGameType == 0) {
							mOneResult = "Arena Win";
						}
						else if(mOneGetGameType == 1) {
							mOneResult = "Unranked Win";
						}
						else if(mOneGetGameType == 2) {
							mOneResult = "Ranked Win";
						}
						else {
							mOneResult = "Win";
						}
					}
					if(mOneCalcWL == 1) {
						if(mOneGetGameType == 0) {
							mOneResult = "Arena Loss";
						}
						else if(mOneGetGameType == 1) {
							mOneResult = "Unranked Loss";
						}
						else if(mOneGetGameType == 2) {
							mOneResult = "Ranked Loss";
						}
						else {
							mOneResult = "Loss";
						}
					}
					String mOneVs = "You: " + mOneYourClass + " Vs: " + mOneVsClass;
					recentPanel.add(recentMatchOne);
						recentMatchOne.setLayout(new FlowLayout());
						recentMatchOne.setBorder(borderUp);
						recentMatchOne.add(matchOneVs);
							matchOneVs.setText(mOneVs);
						recentMatchOne.add(matchOneResult);
							matchOneResult.setText(mOneResult);
				}
				if(rcntMatch == 3) {
					HSQL mThreeObj = new HSQL();
					String mThreeYourClass = classDet(mThreeObj.MatchReturnYourClass(rcntMatch, user));
					String mThreeVsClass = classDet(mThreeObj.MatchReturnVsClass(rcntMatch, user));
					String mThreeResult = "";
					int mThreeCalcWL = mThreeObj.MatchReturnWL(rcntMatch, user);
					int mThreeGetGameType = mThreeObj.MatchReturnGameType(rcntMatch, user);
					if(mThreeCalcWL == 0) {
						if(mThreeGetGameType == 0) {
							mThreeResult = "Arena Win";
						}
						else if(mThreeGetGameType == 1) {
							mThreeResult = "Unranked Win";
						}
						else if(mThreeGetGameType == 2) {
							mThreeResult = "Ranked Win";
						}
						else {
							mThreeResult = "Win";
						}
					}
					if(mThreeCalcWL == 1) {
						if(mThreeGetGameType == 0) {
							mThreeResult = "Arena Loss";
						}
						else if(mThreeGetGameType == 1) {
							mThreeResult = "Unranked Loss";
						}
						else if(mThreeGetGameType == 2) {
							mThreeResult = "Ranked Loss";
						}
						else {
							mThreeResult = "Loss";
						}
					}
					String mThreeVs = "You: " + mThreeYourClass + " Vs: " + mThreeVsClass;
					recentPanel.add(recentMatchThree);
						recentMatchThree.setLayout(new FlowLayout());
						recentMatchThree.setBorder(borderUp);
						recentMatchThree.add(matchThreeVs);
							matchThreeVs.setText(mThreeVs);
						recentMatchThree.add(matchThreeResult);
							matchThreeResult.setText(mThreeResult);
					
					HSQL mTwoObj = new HSQL();
					String mTwoYourClass = classDet(mTwoObj.MatchReturnYourClass(rcntMatch - 1, user));
					String mTwoVsClass = classDet(mTwoObj.MatchReturnVsClass(rcntMatch - 1, user));
					String mTwoResult = "";
					int mTwoCalcWL = mTwoObj.MatchReturnWL(rcntMatch - 1, user);
					int mTwoGetGameType = mTwoObj.MatchReturnGameType(rcntMatch - 1, user);
					if(mTwoCalcWL == 0) {
						if(mTwoGetGameType == 0) {
							mTwoResult = "Arena Win";
						}
						else if(mTwoGetGameType == 1) {
							mTwoResult = "Unranked Win";
						}
						else if(mTwoGetGameType == 2) {
							mTwoResult = "Ranked Win";
						}
						else {
							mTwoResult = "Win";
						}
					}
					if(mTwoCalcWL == 1) {
						if(mTwoGetGameType == 0) {
							mTwoResult = "Arena Loss";
						}
						else if(mTwoGetGameType == 1) {
							mTwoResult = "Unranked Loss";
						}
						else if(mTwoGetGameType == 2) {
							mTwoResult = "Ranked Loss";
						}
						else {
							mTwoResult = "Loss";
						}
					}
					String mTwoVs = "You: " + mTwoYourClass + " Vs: " + mTwoVsClass;
					recentPanel.add(recentMatchTwo);
						recentMatchTwo.setLayout(new FlowLayout());
						recentMatchTwo.setBorder(borderUp);
						recentMatchTwo.add(matchTwoVs);
							matchTwoVs.setText(mTwoVs);
						recentMatchTwo.add(matchTwoResult);
							matchTwoResult.setText(mTwoResult);
					
					HSQL mOneObj = new HSQL();
					String mOneYourClass = classDet(mOneObj.MatchReturnYourClass(rcntMatch - 2, user));
					String mOneVsClass = classDet(mOneObj.MatchReturnVsClass(rcntMatch - 2, user));
					String mOneResult = "";
					int mOneCalcWL = mOneObj.MatchReturnWL(rcntMatch - 2, user);
					int mOneGetGameType = mOneObj.MatchReturnGameType(rcntMatch - 2, user);
					if(mOneCalcWL == 0) {
						if(mOneGetGameType == 0) {
							mOneResult = "Arena Win";
						}
						else if(mOneGetGameType == 1) {
							mOneResult = "Unranked Win";
						}
						else if(mOneGetGameType == 2) {
							mOneResult = "Ranked Win";
						}
						else {
							mOneResult = "Win";
						}
					}
					if(mOneCalcWL == 1) {
						if(mOneGetGameType == 0) {
							mOneResult = "Arena Loss";
						}
						else if(mOneGetGameType == 1) {
							mOneResult = "Unranked Loss";
						}
						else if(mOneGetGameType == 2) {
							mOneResult = "Ranked Loss";
						}
						else {
							mOneResult = "Loss";
						}
					}
					String mOneVs = "You: " + mOneYourClass + " Vs: " + mOneVsClass;
					recentPanel.add(recentMatchOne);
						recentMatchOne.setLayout(new FlowLayout());
						recentMatchOne.setBorder(borderUp);
						recentMatchOne.add(matchOneVs);
							matchOneVs.setText(mOneVs);
						recentMatchOne.add(matchOneResult);
							matchOneResult.setText(mOneResult);
				}
				if(rcntMatch == 4) {
					HSQL mFourObj = new HSQL();
					String mFourYourClass = classDet(mFourObj.MatchReturnYourClass(rcntMatch, user));
					String mFourVsClass = classDet(mFourObj.MatchReturnVsClass(rcntMatch, user));
					String mFourResult = "";
					int mFourCalcWL = mFourObj.MatchReturnWL(rcntMatch, user);
					int mFourGetGameType = mFourObj.MatchReturnGameType(rcntMatch, user);
					if(mFourCalcWL == 0) {
						if(mFourGetGameType == 0) {
							mFourResult = "Arena Win";
						}
						else if(mFourGetGameType == 1) {
							mFourResult = "Unranked Win";
						}
						else if(mFourGetGameType == 2) {
							mFourResult = "Ranked Win";
						}
						else {
							mFourResult = "Win";
						}
					}
					if(mFourCalcWL == 1) {
						if(mFourGetGameType == 0) {
							mFourResult = "Arena Loss";
						}
						else if(mFourGetGameType == 1) {
							mFourResult = "Unranked Loss";
						}
						else if(mFourGetGameType == 2) {
							mFourResult = "Ranked Loss";
						}
						else {
							mFourResult = "Loss";
						}
					}
					String mFourVs = "You: " + mFourYourClass + " Vs: " + mFourVsClass;
					recentPanel.add(recentMatchFour);
						recentMatchFour.setLayout(new FlowLayout());
						recentMatchFour.setBorder(borderUp);
						recentMatchFour.add(matchFourVs);
							matchFourVs.setText(mFourVs);
						recentMatchFour.add(matchFourResult);
							matchFourResult.setText(mFourResult);

					HSQL mThreeObj = new HSQL();
					String mThreeYourClass = classDet(mThreeObj.MatchReturnYourClass(rcntMatch - 1, user));
					String mThreeVsClass = classDet(mThreeObj.MatchReturnVsClass(rcntMatch - 1, user));
					String mThreeResult = "";
					int mThreeCalcWL = mThreeObj.MatchReturnWL(rcntMatch - 1, user);
					int mThreeGetGameType = mThreeObj.MatchReturnGameType(rcntMatch - 1, user);
					if(mThreeCalcWL == 0) {
						if(mThreeGetGameType == 0) {
							mThreeResult = "Arena Win";
						}
						else if(mThreeGetGameType == 1) {
							mThreeResult = "Unranked Win";
						}
						else if(mThreeGetGameType == 2) {
							mThreeResult = "Ranked Win";
						}
						else {
							mThreeResult = "Win";
						}
					}
					if(mThreeCalcWL == 1) {
						if(mThreeGetGameType == 0) {
							mThreeResult = "Arena Loss";
						}
						else if(mThreeGetGameType == 1) {
							mThreeResult = "Unranked Loss";
						}
						else if(mThreeGetGameType == 2) {
							mThreeResult = "Ranked Loss";
						}
						else {
							mThreeResult = "Loss";
						}
					}
					String mThreeVs = "You: " + mThreeYourClass + " Vs: " + mThreeVsClass;
					recentPanel.add(recentMatchThree);
						recentMatchThree.setLayout(new FlowLayout());
						recentMatchThree.setBorder(borderUp);
						recentMatchThree.add(matchThreeVs);
							matchThreeVs.setText(mThreeVs);
						recentMatchThree.add(matchThreeResult);
							matchThreeResult.setText(mThreeResult);
					
					HSQL mTwoObj = new HSQL();
					String mTwoYourClass = classDet(mTwoObj.MatchReturnYourClass(rcntMatch - 2, user));
					String mTwoVsClass = classDet(mTwoObj.MatchReturnVsClass(rcntMatch - 2, user));
					String mTwoResult = "";
					int mTwoCalcWL = mTwoObj.MatchReturnWL(rcntMatch - 2, user);
					int mTwoGetGameType = mTwoObj.MatchReturnGameType(rcntMatch - 2, user);
					if(mTwoCalcWL == 0) {
						if(mTwoGetGameType == 0) {
							mTwoResult = "Arena Win";
						}
						else if(mTwoGetGameType == 1) {
							mTwoResult = "Unranked Win";
						}
						else if(mTwoGetGameType == 2) {
							mTwoResult = "Ranked Win";
						}
						else {
							mTwoResult = "Win";
						}
					}
					if(mTwoCalcWL == 1) {
						if(mTwoGetGameType == 0) {
							mTwoResult = "Arena Loss";
						}
						else if(mTwoGetGameType == 1) {
							mTwoResult = "Unranked Loss";
						}
						else if(mTwoGetGameType == 2) {
							mTwoResult = "Ranked Loss";
						}
						else {
							mTwoResult = "Loss";
						}
					}
					String mTwoVs = "You: " + mTwoYourClass + " Vs: " + mTwoVsClass;
					recentPanel.add(recentMatchTwo);
						recentMatchTwo.setLayout(new FlowLayout());
						recentMatchTwo.setBorder(borderUp);
						recentMatchTwo.add(matchTwoVs);
							matchTwoVs.setText(mTwoVs);
						recentMatchTwo.add(matchTwoResult);
							matchTwoResult.setText(mTwoResult);
					
					HSQL mOneObj = new HSQL();
					String mOneYourClass = classDet(mOneObj.MatchReturnYourClass(rcntMatch - 3, user));
					String mOneVsClass = classDet(mOneObj.MatchReturnVsClass(rcntMatch - 3, user));
					String mOneResult = "";
					int mOneCalcWL = mOneObj.MatchReturnWL(rcntMatch - 3, user);
					int mOneGetGameType = mOneObj.MatchReturnGameType(rcntMatch - 3, user);
					if(mOneCalcWL == 0) {
						if(mOneGetGameType == 0) {
							mOneResult = "Arena Win";
						}
						else if(mOneGetGameType == 1) {
							mOneResult = "Unranked Win";
						}
						else if(mOneGetGameType == 2) {
							mOneResult = "Ranked Win";
						}
						else {
							mOneResult = "Win";
						}
					}
					if(mOneCalcWL == 1) {
						if(mOneGetGameType == 0) {
							mOneResult = "Arena Loss";
						}
						else if(mOneGetGameType == 1) {
							mOneResult = "Unranked Loss";
						}
						else if(mOneGetGameType == 2) {
							mOneResult = "Ranked Loss";
						}
						else {
							mOneResult = "Loss";
						}
					}
					String mOneVs = "You: " + mOneYourClass + " Vs: " + mOneVsClass;
					recentPanel.add(recentMatchOne);
						recentMatchOne.setLayout(new FlowLayout());
						recentMatchOne.setBorder(borderUp);
						recentMatchOne.add(matchOneVs);
							matchOneVs.setText(mOneVs);
						recentMatchOne.add(matchOneResult);
							matchOneResult.setText(mOneResult);
				}
				if(rcntMatch >= 5) {
					HSQL mFiveObj = new HSQL();
					String mFiveYourClass = classDet(mFiveObj.MatchReturnYourClass(rcntMatch, user));
					String mFiveVsClass = classDet(mFiveObj.MatchReturnVsClass(rcntMatch, user));
					String mFiveResult = "";
					int mFiveCalcWL = mFiveObj.MatchReturnWL(rcntMatch, user);
					int mFiveGetGameType = mFiveObj.MatchReturnGameType(rcntMatch, user);
					if(mFiveCalcWL == 0) {
						if(mFiveGetGameType == 0) {
							mFiveResult = "Arena Win";
						}
						else if(mFiveGetGameType == 1) {
							mFiveResult = "Unranked Win";
						}
						else if(mFiveGetGameType == 2) {
							mFiveResult = "Ranked Win";
						}
						else {
							mFiveResult = "Win";
						}
					}
					if(mFiveCalcWL == 1) {
						if(mFiveGetGameType == 0) {
							mFiveResult = "Arena Loss";
						}
						else if(mFiveGetGameType == 1) {
							mFiveResult = "Unranked Loss";
						}
						else if(mFiveGetGameType == 2) {
							mFiveResult = "Ranked Loss";
						}
						else {
							mFiveResult = "Loss";
						}
					}
					String mFiveVs = "You: " + mFiveYourClass + " Vs: " + mFiveVsClass;
					recentPanel.add(recentMatchFive);
						recentMatchFive.setLayout(new FlowLayout());
						recentMatchFive.setBorder(borderUp);
						recentMatchFive.add(matchFiveVs);
							matchFiveVs.setText(mFiveVs);
						recentMatchFive.add(matchFiveResult);
							matchFiveResult.setText(mFiveResult);

					HSQL mFourObj = new HSQL();
					String mFourYourClass = classDet(mFourObj.MatchReturnYourClass(rcntMatch - 1, user));
					String mFourVsClass = classDet(mFourObj.MatchReturnVsClass(rcntMatch - 1, user));
					String mFourResult = "";
					int mFourCalcWL = mFourObj.MatchReturnWL(rcntMatch - 1, user);
					int mFourGetGameType = mFourObj.MatchReturnGameType(rcntMatch - 1, user);
					if(mFourCalcWL == 0) {
						if(mFourGetGameType == 0) {
							mFourResult = "Arena Win";
						}
						else if(mFourGetGameType == 1) {
							mFourResult = "Unranked Win";
						}
						else if(mFourGetGameType == 2) {
							mFourResult = "Ranked Win";
						}
						else {
							mFourResult = "Win";
						}
					}
					if(mFourCalcWL == 1) {
						if(mFourGetGameType == 0) {
							mFourResult = "Arena Loss";
						}
						else if(mFourGetGameType == 1) {
							mFourResult = "Unranked Loss";
						}
						else if(mFourGetGameType == 2) {
							mFourResult = "Ranked Loss";
						}
						else {
							mFourResult = "Loss";
						}
					}
					String mFourVs = "You: " + mFourYourClass + " Vs: " + mFourVsClass;
					recentPanel.add(recentMatchFour);
						recentMatchFour.setLayout(new FlowLayout());
						recentMatchFour.setBorder(borderUp);
						recentMatchFour.add(matchFourVs);
							matchFourVs.setText(mFourVs);
						recentMatchFour.add(matchFourResult);
							matchFourResult.setText(mFourResult);

					HSQL mThreeObj = new HSQL();
					String mThreeYourClass = classDet(mThreeObj.MatchReturnYourClass(rcntMatch - 2, user));
					String mThreeVsClass = classDet(mThreeObj.MatchReturnVsClass(rcntMatch - 2, user));
					String mThreeResult = "";
					int mThreeCalcWL = mThreeObj.MatchReturnWL(rcntMatch - 2, user);
					int mThreeGetGameType = mThreeObj.MatchReturnGameType(rcntMatch - 2, user);
					if(mThreeCalcWL == 0) {
						if(mThreeGetGameType == 0) {
							mThreeResult = "Arena Win";
						}
						else if(mThreeGetGameType == 1) {
							mThreeResult = "Unranked Win";
						}
						else if(mThreeGetGameType == 2) {
							mThreeResult = "Ranked Win";
						}
						else {
							mThreeResult = "Win";
						}
					}
					if(mThreeCalcWL == 1) {
						if(mThreeGetGameType == 0) {
							mThreeResult = "Arena Loss";
						}
						else if(mThreeGetGameType == 1) {
							mThreeResult = "Unranked Loss";
						}
						else if(mThreeGetGameType == 2) {
							mThreeResult = "Ranked Loss";
						}
						else {
							mThreeResult = "Loss";
						}
					}
					String mThreeVs = "You: " + mThreeYourClass + " Vs: " + mThreeVsClass;
					recentPanel.add(recentMatchThree);
						recentMatchThree.setLayout(new FlowLayout());
						recentMatchThree.setBorder(borderUp);
						recentMatchThree.add(matchThreeVs);
							matchThreeVs.setText(mThreeVs);
						recentMatchThree.add(matchThreeResult);
							matchThreeResult.setText(mThreeResult);
					
					HSQL mTwoObj = new HSQL();
					String mTwoYourClass = classDet(mTwoObj.MatchReturnYourClass(rcntMatch - 3, user));
					String mTwoVsClass = classDet(mTwoObj.MatchReturnVsClass(rcntMatch - 3, user));
					String mTwoResult = "";
					int mTwoCalcWL = mTwoObj.MatchReturnWL(rcntMatch - 3, user);
					int mTwoGetGameType = mTwoObj.MatchReturnGameType(rcntMatch - 3, user);
					if(mTwoCalcWL == 0) {
						if(mTwoGetGameType == 0) {
							mTwoResult = "Arena Win";
						}
						else if(mTwoGetGameType == 1) {
							mTwoResult = "Unranked Win";
						}
						else if(mTwoGetGameType == 2) {
							mTwoResult = "Ranked Win";
						}
						else {
							mTwoResult = "Win";
						}
					}
					if(mTwoCalcWL == 1) {
						if(mTwoGetGameType == 0) {
							mTwoResult = "Arena Loss";
						}
						else if(mTwoGetGameType == 1) {
							mTwoResult = "Unranked Loss";
						}
						else if(mTwoGetGameType == 2) {
							mTwoResult = "Ranked Loss";
						}
						else {
							mTwoResult = "Loss";
						}
					}
					String mTwoVs = "You: " + mTwoYourClass + " Vs: " + mTwoVsClass;
					recentPanel.add(recentMatchTwo);
						recentMatchTwo.setLayout(new FlowLayout());
						recentMatchTwo.setBorder(borderUp);
						recentMatchTwo.add(matchTwoVs);
							matchTwoVs.setText(mTwoVs);
						recentMatchTwo.add(matchTwoResult);
							matchTwoResult.setText(mTwoResult);
					
					HSQL mOneObj = new HSQL();
					String mOneYourClass = classDet(mOneObj.MatchReturnYourClass(rcntMatch - 4, user));
					String mOneVsClass = classDet(mOneObj.MatchReturnVsClass(rcntMatch - 4, user));
					String mOneResult = "";
					int mOneCalcWL = mOneObj.MatchReturnWL(rcntMatch - 4, user);
					int mOneGetGameType = mOneObj.MatchReturnGameType(rcntMatch - 4, user);
					if(mOneCalcWL == 0) {
						if(mOneGetGameType == 0) {
							mOneResult = "Arena Win";
						}
						else if(mOneGetGameType == 1) {
							mOneResult = "Unranked Win";
						}
						else if(mOneGetGameType == 2) {
							mOneResult = "Ranked Win";
						}
						else {
							mOneResult = "Win";
						}
					}
					if(mOneCalcWL == 1) {
						if(mOneGetGameType == 0) {
							mOneResult = "Arena Loss";
						}
						else if(mOneGetGameType == 1) {
							mOneResult = "Unranked Loss";
						}
						else if(mOneGetGameType == 2) {
							mOneResult = "Ranked Loss";
						}
						else {
							mOneResult = "Loss";
						}
					}
					String mOneVs = "You: " + mOneYourClass + " Vs: " + mOneVsClass;
					recentPanel.add(recentMatchOne);
						recentMatchOne.setLayout(new FlowLayout());
						recentMatchOne.setBorder(borderUp);
						recentMatchOne.add(matchOneVs);
							matchOneVs.setText(mOneVs);
						recentMatchOne.add(matchOneResult);
							matchOneResult.setText(mOneResult);
				}
			//rankingPanel.setBorder(borderUp);
			//rankingPanel.add(ranking);
				//ranking.setFont(mFrameFont);
			//bestDecksPanel.setBorder(borderUp);
			//bestDecksPanel.add(bestDecks);
				//bestDecks.setFont(mFrameFont);
		// initialize borders
		newMatch.setBorder(borderUp);
		stats.setBorder(borderUp);
		newArenaMatch.setBorder(borderUp);
		overview.setBorder(borderUp);
		newDeck.setBorder(borderUp);
		cardsSearch.setBorder(borderUp);
		// nav event listeners
		newMatch.addMouseListener(this);
		stats.addMouseListener(this);
		newArenaMatch.addMouseListener(this);
		overview.addMouseListener(this);
		newDeck.addMouseListener(this);
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
	public String classDet(int classInt) {
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
}