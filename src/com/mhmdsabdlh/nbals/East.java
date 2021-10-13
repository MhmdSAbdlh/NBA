package com.mhmdsabdlh.nbals;

import java.awt.GridLayout;
import java.io.*;
import java.net.URL;
import javax.swing.*;

@SuppressWarnings("serial")
class East extends JFrame{
	
	int gameP;
	Team[] teams = new Team[15];
	JSpinner teamWin[] = new JSpinner[15];
	JSpinner teamLoss[] = new JSpinner[15];
	JLabel teamData[][] = new JLabel[15][10];
	JLabel title[] = new JLabel[12];
	URL url = getClass().getResource("icon.png");
	ImageIcon icon = new ImageIcon(url);
	
	East(){
		//Frame details
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(1280, 720);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setTitle("East Live Standing");
		this.getContentPane().setBackground(Main.DarkC);
		this.setIconImage(icon.getImage());
		
		//Teams
		teams[0] = new Team("76ers");
		teams[1] = new Team("Nets");
		teams[2] = new Team("Celtics");
		teams[3] = new Team("Heat");
		teams[4] = new Team("Raptors");
		teams[5] = new Team("Bucks");
		teams[6] = new Team("Hawks");
		teams[7] = new Team("Knicks");
		teams[8] = new Team("Wizards");
		teams[9] = new Team("Pacers");
		teams[10] = new Team("Hornets");
		teams[11] = new Team("Bulls");
		teams[12] = new Team("Cavs");
		teams[13] = new Team("Magic");
		teams[14] = new Team("Pistons");
			
		//Panel details
		JPanel panel1 = new JPanel();
		panel1.setLocation(10, 10);
		panel1.setSize(320, 570);
		panel1.setLayout(new GridLayout(16, 3));
		panel1.setBackground(Main.LightC);
		JPanel panel2 = new JPanel();
		panel2.setLocation(330, 10);
		panel2.setSize(180, 570);
		panel2.setLayout(new GridLayout(16, 2));
		panel2.setBackground(Main.LightC);
		JPanel panel3 = new JPanel();
		panel3.setLocation(510, 10);
		panel3.setSize(740, 570);
		panel3.setLayout(new GridLayout(16, 7));
		panel3.setBackground(Main.LightC);
		
		//Fill the data
		titleAdd(title);
		teamsDef(teamData);	
		openEast(teamWin, teamLoss);
		rankBtn(teamWin, teamLoss);
		
		//Panel
		//Add titles
		panel1.add(title[0]);
		panel1.add(title[1]);
		panel1.add(title[2]);
		panel2.add(title[3]);
		panel2.add(title[4]);
		for(int i =5;i<12;i++)
			panel3.add(title[i]);
		//Set the values and properties
		for(int i=0;i<15;i++) {
			teamData[i][0].setText(teams[i].rank+"");
			teamData[i][1].setText(teams[i].name);
			teamData[i][2].setText(teams[i].gameP+"");
			teamWin[i].setValue(teams[i].win);
			teamLoss[i].setValue(teams[i].loss);
			teamData[i][3].setText(teams[i].per+"");
			teamData[i][4].setText(teams[i].GBU+"");
			teamData[i][5].setText(teams[i].GB+"");
			teamData[i][6].setText(teams[i].gameR+"");
			teamData[i][7].setText("("+teams[i].avUp+","+teams[i].avLow+")");
			teamData[i][8].setText(teams[i].fRank);
			teamData[i][9].setText(teams[i].status);
			//Color status
			if(teamData[i][9].getText()=="PLAYOFFS")
				teamData[i][9].setForeground(Main.greenC);
			else
				if(teamData[i][9].getText()=="OUT")
					teamData[i][9].setForeground(Main.redC);
				else
					if(teamData[i][9].getText()=="PLAY-IN")
						teamData[i][9].setForeground(Main.orangeC);
					else
						teamData[i][9].setForeground(Main.TextC);
			//Color finalRank
			if(teamData[i][8].getText()=="NOT YET")
				teamData[i][8].setForeground(Main.TextC);
			else
				teamData[i][8].setForeground(Main.DarkC);
			//Calculate game played
			gameP+=Integer.parseInt(teamData[i][2].getText());
			//Add teams to panel
			for(int j=0;j<3;j++)
				panel1.add(teamData[i][j]);
			panel2.add(teamWin[i]);
			panel2.add(teamLoss[i]);
			for(int k=3;k<10;k++)
				panel3.add(teamData[i][k]);
		}
		
		//Seasons progressBar
		JProgressBar progress = new JProgressBar();
		progress.setBounds(10, 585, 1240, 10);
		progress.setValue(gameP*100/(15*82));
		progress.setForeground(Main.LightC);
		progress.setBackground(Main.DarkC);
		//Update ranking button
		JButton newR = new JButton("UPDATE");
		newR.setBounds(1050, 615, 200, 50);
		newR.setBackground(Main.LightC);
		newR.setForeground(Main.DarkC);
		newR.setFocusable(false);
		newR.setFont(Main.myFont);
		newR.addActionListener( e -> {
			saveEast(teamWin, teamLoss);
			this.dispose();
			new East();
			});
		//back to main button
		JButton back = new JButton("BACK");
		back.setBounds(800, 615, 200, 50);
		back.setBackground(Main.LightC);
		back.setForeground(Main.DarkC);
		back.setFocusable(false);
		back.setFont(Main.myFont);
		back.addActionListener( e -> {
			this.dispose();
			new Conf();
			});
		//Keywords text
		JTextArea keywords = new JTextArea();
		keywords.setText("GP: Game Played\tGBU: Game Behind Upper\tFRK: Final Ranking\n\nGR: Game Remaining\tGB: Game Behind First\tAV-RK: Available Ranking");
		keywords.setBounds(20,610,750,70);
		keywords.setEditable(false);
		keywords.setBackground(Main.DarkC);
		keywords.setForeground(Main.LightC);
		keywords.setFont(Main.smallF);
		
		//Add to frame
		this.add(keywords);
		this.add(newR);
		this.add(back);
		this.add(panel1);
		this.add(panel2);
		this.add(panel3);
		this.add(progress);
		
		//Set frame Visible
		this.getRootPane().setDefaultButton(newR);
		this.setVisible(true);
	}

	//Locate the team(for the save)
	private int teamLocation(int index) {
		
		String name=teamData[index][1].getText();
		switch (name) {
		case "76ers":
			return 0;
		case "Nets":
			return 1;
		case "Celtics":
			return 2;
		case "Heat":
			return 3;
		case "Raptors":
			return 4;
		case "Bucks":
			return 5;
		case "Hawks":
			return 6;
		case "Knicks":
			return 7;
		case "Wizards":
			return 8;
		case "Pacers":
			return 9;
		case "Hornets":
			return 10;
		case "Bulls":
			return 11;
		case "Cavs":
			return 12;
		case "Magic":
			return 13;
		case "Pistons":
			return 14;
		default:
			return -1;
		}
	}

	//Save the data for each team
	private void saveEast(JSpinner[] teamWin, JSpinner[] teamLoss) {
		try {
			FileWriter dataSaved = new FileWriter("East.csv");
			int i=0, j;
			for(@SuppressWarnings("unused") JSpinner str:teamWin){
				j=0;
				while(teamLocation(j) != i)
					j++;
				dataSaved.write(teamWin[j].getValue()+",");
				dataSaved.write(teamLoss[j].getValue()+System.lineSeparator());
				i++;
			}
			dataSaved.close();
		} catch (Exception e) {}
	}
	
	//Open the saved data
	private void openEast(JSpinner[] teamWin, JSpinner[] teamLoss) {

		BufferedReader dataOpened=null;
		String line ="";
		int i=0;
		try{
			dataOpened = new BufferedReader(new FileReader(new File("East.csv")));
			while((line = dataOpened.readLine())!=null){
				String[] row = line.split(",");
				teamWin[i].setValue(Integer.parseInt(row[0]));
				teamLoss[i].setValue(Integer.parseInt(row[1]));
				i++;
			}
			dataOpened.close();
		} catch (Exception e) {}
	}

	//Calculate all the data
	private void rankBtn(JSpinner[] teamWin, JSpinner[] teamLoss) {
		for(int i=0;i<15;i++) {
					 teams[i].win = (int)teamWin[i].getValue();
					 teams[i].loss = (int)teamLoss[i].getValue();
				 }
		NBACalc.gamePR(teams);
		NBACalc.rank(teams);
		NBACalc.gameBehind(teams);
		NBACalc.rankAva(teams);
		NBACalc.teamStatus(teams);
		NBACalc.FinalRank(teams);
	}
	
	//Spinner theme
	private JFormattedTextField getTextField(JSpinner spinner) {
	    JComponent editor = spinner.getEditor();
	    if (editor instanceof JSpinner.DefaultEditor) {
	        return ((JSpinner.DefaultEditor)editor).getTextField();
	    } else {
	        System.err.println("Unexpected editor type: "
	                           + spinner.getEditor().getClass()
	                           + " isn't a descendant of DefaultEditor");
	        return null;
	    }
	}
	
	//Initialize & Theme the data for teams
	private void teamsDef(JLabel[][] teamName) {
		for(int i=0;i<15;i++) {
			teamName[i][0] = new JLabel();
			teamName[i][1] = new JLabel();
			teamName[i][2] = new JLabel();
			teamWin[i] = new JSpinner();
			teamLoss[i] = new JSpinner();
			teamName[i][3] = new JLabel();
			teamName[i][4] = new JLabel();
			teamName[i][5] = new JLabel();
			teamName[i][6] = new JLabel();
			teamName[i][7] = new JLabel();
			teamName[i][8] = new JLabel();
			teamName[i][9] = new JLabel();
			for(int j=0;j<10;j++) {
				teamName[i][j].setHorizontalAlignment(0);
				teamName[i][j].setFont(Main.myFont);
				teamName[i][j].setForeground(Main.TextC);				
				if(i!=14&&j!=9)
					teamName[i][j].setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Main.DarkC));
				else
					if(i==14&&j!=9)
					teamName[i][j].setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Main.DarkC));
					else
						if(i!=14&&j==9)
						teamName[i][j].setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Main.DarkC));
						else
							teamName[i][j].setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Main.DarkC));
				//Color ranking
				if(i<6)
					teamName[i][0].setForeground(Main.greenC);
				else
					if(i>9)
						teamName[i][0].setForeground(Main.redC);
					else
						teamName[i][0].setForeground(Main.orangeC);
			}
			JFormattedTextField temp = getTextField(teamWin[i]);
			temp.setHorizontalAlignment(0);
			temp.setBackground(Main.LightC);
			temp.setForeground(Main.TextC);
			temp.setCaretColor(Main.DarkC);
			teamWin[i].setFont(Main.myFont);
			teamWin[i].setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Main.DarkC));
			JFormattedTextField temp2 = getTextField(teamLoss[i]);
			temp2.setHorizontalAlignment(0);
			temp2.setBackground(Main.LightC);
			temp2.setForeground(Main.TextC);
			temp2.setCaretColor(Main.DarkC);
			teamLoss[i].setFont(Main.myFont);
			teamLoss[i].setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Main.DarkC));
			if(i==14) {
				teamWin[i].setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Main.DarkC));
				teamLoss[i].setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Main.DarkC));
				}
			}
		}

	//Initialize & Theme the titles
	private void titleAdd(JLabel[] title) {
		title[0] = new JLabel("Rank");
		title[1] = new JLabel("Name");
		title[2] = new JLabel("GP");
		title[3] = new JLabel("Win");
		title[4] = new JLabel("Loss");
		title[5] = new JLabel("%");
		title[6] = new JLabel("GBU");
		title[7] = new JLabel("GB");
		title[8] = new JLabel("GR");
		title[9] = new JLabel("AV-RK");
		title[10] = new JLabel("FRK");
		title[11] = new JLabel("Status");
		for(int i=0;i<12;i++) {
			title[i].setHorizontalAlignment(0);
			title[i].setFont(Main.myFont);
			title[i].setForeground(Main.DarkC);
			if(i!=11)
				title[i].setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Main.DarkC));
			else
				title[i].setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Main.DarkC));
		}
	}
}