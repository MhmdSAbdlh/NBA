package com.mhmdsabdlh.nbals;

import java.awt.GridLayout;
import java.io.*;
import java.net.URL;
import javax.swing.*;

@SuppressWarnings("serial")
class West extends JFrame {

	int gameP;
	Team[] teams = new Team[15];
	JSpinner teamWin[] = new JSpinner[15];
	JSpinner teamLoss[] = new JSpinner[15];
	JSpinner adv[] = new JSpinner[15];
	JLabel teamData[][] = new JLabel[15][10];
	JLabel title[] = new JLabel[13];
	URL url = getClass().getResource("icon.png");
	ImageIcon icon = new ImageIcon(url);
	URL backU = getClass().getResource("back.png");
	ImageIcon backI = new ImageIcon(backU);
	URL updateU = getClass().getResource("update.png");
	ImageIcon updateI = new ImageIcon(updateU);

	West() {
		// Frame details
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(1380, 720);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setTitle("West Live Standing");
		this.getContentPane().setBackground(Main.darkC);
		this.setIconImage(icon.getImage());

		// Teams
		teams[0] = new Team("Lakers");
		teams[1] = new Team("Clippers");
		teams[2] = new Team("Jazz");
		teams[3] = new Team("Warrios");
		teams[4] = new Team("Spurs");
		teams[5] = new Team("Pelicans");
		teams[6] = new Team("Suns");
		teams[7] = new Team("Grizzlies");
		teams[8] = new Team("Nuggets");
		teams[9] = new Team("OKC");
		teams[10] = new Team("Rockets");
		teams[11] = new Team("Mavs");
		teams[12] = new Team("Kings");
		teams[13] = new Team("Wolves");
		teams[14] = new Team("Portland");

		// Panel details
		JPanel panel1 = new JPanel();
		panel1.setLocation(10, 10);
		panel1.setSize(320, 570);
		panel1.setLayout(new GridLayout(16, 3));
		panel1.setBackground(Main.lightC);
		JPanel panel2 = new JPanel();
		panel2.setLocation(330, 10);
		panel2.setSize(180, 570);
		panel2.setLayout(new GridLayout(16, 3));
		panel2.setBackground(Main.lightC);
		JPanel panel3 = new JPanel();
		panel3.setLocation(510, 10);
		panel3.setSize(840, 570);
		panel3.setLayout(new GridLayout(16, 7));
		panel3.setBackground(Main.lightC);

		// Fill the data
		titleAdd(title);
		teamsDef(teamData);
		openWest(teamWin, teamLoss, adv);
		rankBtn(teamWin, teamLoss, adv);

		// Panel
		// Add titles
		panel1.add(title[0]);
		panel1.add(title[1]);
		panel1.add(title[2]);
		panel2.add(title[3]);
		panel2.add(title[4]);
		panel2.add(title[5]);
		for (int i = 6; i < 13; i++)
			panel3.add(title[i]);
		// Set the values and properties
		for (int i = 0; i < 15; i++) {
			teamData[i][0].setText(teams[i].rank + "");
			teamData[i][1].setText(teams[i].name);
			teamData[i][2].setText(teams[i].gameP + "");
			teamWin[i].setValue(teams[i].win);
			Main.hideSpinnerArrow(teamWin[i]);
			teamLoss[i].setValue(teams[i].loss);
			Main.hideSpinnerArrow(teamLoss[i]);
			adv[i].setValue(teams[i].adv);
			Main.hideSpinnerArrow(adv[i]);
			teamData[i][3].setText(teams[i].per + "");
			teamData[i][4].setText(teams[i].GBU + "");
			teamData[i][5].setText(teams[i].GB + "");
			teamData[i][6].setText(teams[i].gameR + "");
			teamData[i][7].setText("(" + teams[i].avUp + "," + teams[i].avLow + ")");
			teamData[i][8].setText(teams[i].gN + "");
			teamData[i][9].setText(teams[i].fRank);
			// Color GN
			if (Integer.parseInt(teamData[i][0].getText()) < 7)
				if (teamData[i][8].getText().equals("PLAYOFFS"))
					teamData[i][8].setForeground(Main.greenC);
				else
					teamData[i][8].setForeground(Main.greenD);
			else if (Integer.parseInt(teamData[i][0].getText()) < 11)
				if (teamData[i][8].getText().equals("PLAY-IN"))
					teamData[i][8].setForeground(Main.orangeC);
				else if (teamData[i][8].getText().equals("PO or PI"))
					teamData[i][8].setForeground(Main.darkC);
				else
					teamData[i][8].setForeground(Main.orangeD);
			else if (teamData[i][8].getText().equals("OUT"))
				teamData[i][8].setForeground(Main.redC);
			else
				teamData[i][8].setForeground(Main.redD);
			// Color finalRank
			if (teamData[i][9].getText() == "NOT YET")
				teamData[i][9].setForeground(Main.TextC);
			else
				teamData[i][9].setForeground(Main.darkC);
			// Calculate game played
			gameP += Integer.parseInt(teamData[i][2].getText());
			// Add teams to panel
			for (int j = 0; j < 3; j++)
				panel1.add(teamData[i][j]);
			panel2.add(teamWin[i]);
			panel2.add(teamLoss[i]);
			panel2.add(adv[i]);
			for (int k = 3; k < 10; k++)
				panel3.add(teamData[i][k]);
		}

		// Seasons progressBar
		JProgressBar progress = new JProgressBar();
		progress.setBounds(10, 585, 1340, 10);
		progress.setValue(gameP * 100 / (15 * 82));
		progress.setForeground(Main.lightC);
		progress.setBackground(Main.darkC);
		// Update ranking button
		JButton newR = new JButton(updateI);
		newR.setBounds(1250, 615, 55, 55);
		newR.setOpaque(false);
		newR.setContentAreaFilled(false);
		newR.setBorderPainted(false);
		newR.addActionListener(e -> {
			saveWest(teamWin, teamLoss, adv);
			this.dispose();
			new West();
		});
		// back to main button
		JButton back = new JButton(backI);
		back.setBounds(1150, 615, 55, 55);
		back.setOpaque(false);
		back.setContentAreaFilled(false);
		back.setBorderPainted(false);
		back.addActionListener(e -> {
			this.dispose();
			new Conf();
		});
		// Keywords text
		JTextArea keywords = new JTextArea();
		keywords.setText("GP: Game Played\tGBU: Game Behind Upper\tFRK: Final Ranking\tGN: Game Needed to Stay \n"
				+ "\nGR: Game Remaining\tGB: Game Behind First\tAV-RK: Available Ranking\t     in PO/PI or get eliminated");
		keywords.setBounds(20, 610, 1020, 70);
		keywords.setEditable(false);
		keywords.setBackground(Main.darkC);
		keywords.setForeground(Main.lightC);
		keywords.setFont(Main.smallF);

		// Add to frame
		this.add(keywords);
		this.add(newR);
		this.add(back);
		this.add(panel1);
		this.add(panel2);
		this.add(panel3);
		this.add(progress);

		// Set frame Visible
		this.getRootPane().setDefaultButton(newR);
		this.setVisible(true);
	}

	// Locate the team(for the save)
	private int teamLocation(int index) {

		String name = teamData[index][1].getText();
		switch (name) {
		case "Lakers":
			return 0;
		case "Clippers":
			return 1;
		case "Jazz":
			return 2;
		case "Warrios":
			return 3;
		case "Spurs":
			return 4;
		case "Pelicans":
			return 5;
		case "Suns":
			return 6;
		case "Grizzlies":
			return 7;
		case "Nuggets":
			return 8;
		case "OKC":
			return 9;
		case "Rockets":
			return 10;
		case "Mavs":
			return 11;
		case "Kings":
			return 12;
		case "Wolves":
			return 13;
		case "Portland":
			return 14;
		default:
			return -1;
		}
	}

	// Save the data for each team
	private void saveWest(JSpinner[] teamWin, JSpinner[] teamLoss, JSpinner[] adv) {
		try {
			FileWriter dataSaved = new FileWriter("West.csv");
			int i = 0, j;
			for (@SuppressWarnings("unused")
			JSpinner str : teamWin) {
				j = 0;
				while (teamLocation(j) != i)
					j++;
				dataSaved.write(teamWin[j].getValue() + ",");
				dataSaved.write(teamLoss[j].getValue() + ",");
				dataSaved.write(adv[j].getValue() + System.lineSeparator());
				i++;
			}
			dataSaved.close();
		} catch (Exception e) {
		}
	}

	// Open the saved data
	private void openWest(JSpinner[] teamWin, JSpinner[] teamLoss, JSpinner[] adv) {

		BufferedReader dataOpened = null;
		String line = "";
		int i = 0;
		try {
			dataOpened = new BufferedReader(new FileReader(new File("West.csv")));
			while ((line = dataOpened.readLine()) != null) {
				String[] row = line.split(",");
				teamWin[i].setValue(Integer.parseInt(row[0]));
				teamLoss[i].setValue(Integer.parseInt(row[1]));
				adv[i].setValue(Integer.parseInt(row[2]));
				i++;
			}
			dataOpened.close();
		} catch (Exception e) {
		}
	}

	// Calculate all the data
	private void rankBtn(JSpinner[] teamWin, JSpinner[] teamLoss, JSpinner[] adv) {
		for (int i = 0; i < 15; i++) {
			teams[i].win = (int) teamWin[i].getValue();
			teams[i].loss = (int) teamLoss[i].getValue();
			teams[i].adv = (int) adv[i].getValue();
		}
		NBACalc.gamePR(teams);
		NBACalc.rank(teams);
		NBACalc.gameBehind(teams);
		NBACalc.rankAva(teams);
		NBACalc.matchNeeded(teams);
		NBACalc.FinalRank(teams);
	}

	// Spinner theme
	private JFormattedTextField getTextField(JSpinner spinner) {
		JComponent editor = spinner.getEditor();
		if (editor instanceof JSpinner.DefaultEditor) {
			return ((JSpinner.DefaultEditor) editor).getTextField();
		} else {
			System.err.println("Unexpected editor type: " + spinner.getEditor().getClass()
					+ " isn't a descendant of DefaultEditor");
			return null;
		}
	}

	// Initialize & Theme the data for teams
	private void teamsDef(JLabel[][] teamName) {
		for (int i = 0; i < 15; i++) {
			teamName[i][0] = new JLabel();
			teamName[i][1] = new JLabel();
			teamName[i][2] = new JLabel();
			teamWin[i] = new JSpinner();
			teamLoss[i] = new JSpinner();
			adv[i] = new JSpinner();
			teamName[i][3] = new JLabel();
			teamName[i][4] = new JLabel();
			teamName[i][5] = new JLabel();
			teamName[i][6] = new JLabel();
			teamName[i][7] = new JLabel();
			teamName[i][8] = new JLabel();
			teamName[i][9] = new JLabel();
			for (int j = 0; j < 10; j++) {
				teamName[i][j].setHorizontalAlignment(0);
				teamName[i][j].setFont(Main.myFont);
				teamName[i][j].setForeground(Main.TextC);
				if (i != 14 && j != 9)
					teamName[i][j].setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Main.darkC));
				else if (i == 14 && j != 9)
					teamName[i][j].setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Main.darkC));
				else if (i != 14 && j == 9)
					teamName[i][j].setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Main.darkC));
				else
					teamName[i][j].setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Main.darkC));
				// Color ranking
				if (i < 6)
					teamName[i][0].setForeground(Main.greenC);
				else if (i > 9)
					teamName[i][0].setForeground(Main.redC);
				else
					teamName[i][0].setForeground(Main.orangeC);

			}
			JFormattedTextField temp = getTextField(teamWin[i]);
			temp.setHorizontalAlignment(0);
			temp.setBackground(Main.lightC);
			temp.setForeground(Main.TextC);
			temp.setCaretColor(Main.darkC);
			teamWin[i].setFont(Main.myFont);
			teamWin[i].setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Main.darkC));
			JFormattedTextField temp2 = getTextField(teamLoss[i]);
			temp2.setHorizontalAlignment(0);
			temp2.setBackground(Main.lightC);
			temp2.setForeground(Main.TextC);
			temp2.setCaretColor(Main.darkC);

			teamLoss[i].setFont(Main.myFont);
			teamLoss[i].setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Main.darkC));
			JFormattedTextField temp3 = getTextField(adv[i]);
			temp3.setHorizontalAlignment(0);
			temp3.setBackground(Main.lightC);
			temp3.setForeground(Main.TextC);
			temp3.setCaretColor(Main.darkC);
			adv[i].setFont(Main.myFont);
			adv[i].setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Main.darkC));
			if (i == 14) {
				teamWin[i].setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Main.darkC));
				teamLoss[i].setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Main.darkC));
				adv[i].setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Main.darkC));
			}
		}
	}

	private void titleAdd(JLabel[] title) {
		title[0] = new JLabel("Rank");
		title[1] = new JLabel("Name");
		title[2] = new JLabel("GP");
		title[3] = new JLabel("Win");
		title[4] = new JLabel("Loss");
		title[5] = new JLabel("Adv");
		title[6] = new JLabel("%");
		title[7] = new JLabel("GBU");
		title[8] = new JLabel("GB");
		title[9] = new JLabel("GR");
		title[10] = new JLabel("AV-RK");
		title[11] = new JLabel("GN");
		title[12] = new JLabel("FRK");
		for (int i = 0; i < 13; i++) {
			title[i].setHorizontalAlignment(0);
			title[i].setFont(Main.myFont);
			title[i].setForeground(Main.darkC);
			if (i != 12)
				title[i].setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Main.darkC));
			else
				title[i].setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Main.darkC));
		}
	}
}