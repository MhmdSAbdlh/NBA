package com.mhmdsabdlh.nbals;

import java.awt.Color;
import java.awt.GridLayout;
import java.net.URL;
import javax.swing.*;

@SuppressWarnings("serial")
class EastMain extends JFrame{
	
	int gameP;
	
	EastMain(){
		//Frame details
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(1300, 720);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setTitle("East Live Standing");
		this.getContentPane().setBackground(Main.DarkC);
		URL url = getClass().getResource("icon.png");
		ImageIcon icon = new ImageIcon(url);
		this.setIconImage(icon.getImage());
		
		//Panel details
		JPanel panel = new JPanel();
		panel.setLocation(10, 10);
		panel.setSize(1250, 570);
		panel.setLayout(new GridLayout(16, 12));
		panel.setBackground(Main.LightC);
		
		//Label
		Team[] team = FrameEast.teams;
		JLabel teamName[][] = new JLabel[15][12];
		JLabel title[] = new JLabel[12];
		titleAdd(team, teamName, title);		
		for(int i=0;i<12;i++)
			panel.add(title[i]);
		teamsAdd(panel, teamName);
		JProgressBar progress = new JProgressBar();
		progress.setBounds(10, 585, 1250, 10);
		progress.setValue(gameP*100/(15*82));
		progress.setForeground(Main.LightC);
		progress.setBackground(Main.DarkC);
		
		//Button and TextArea
		JButton newR = new JButton("<-- Back");
		newR.setBounds(1050, 600, 200, 50);
		newR.setBackground(Main.LightC);
		newR.setForeground(Main.DarkC);
		newR.setFocusable(false);
		newR.setFont(Main.myFont);
		newR.addActionListener( e -> {
			this.dispose();
			new FrameEast();
			});
		JTextArea keywords = new JTextArea();
		keywords.setText("Rank: Ranking\t\tGP: Game Played\t%: Win Ratio\t\tGR: Game Remaining\n\nGBU: Game Behind Upper\tGB: Game Behind First\tFRK: Final Ranking\tAV-RK: Available Ranking");
		keywords.setBounds(20,610,1000,70);
		keywords.setEditable(false);
		keywords.setBackground(Main.DarkC);
		keywords.setForeground(Main.LightC);
		keywords.setFont(Main.smallF);
		Main.credit.setHorizontalAlignment(0);
		Main.credit.setBounds(1000, 660, 300, 20);
		Main.credit.setForeground(Color.white);
		
		//Add to frame
		this.add(Main.credit);
		this.add(keywords);
		this.add(newR);
		this.add(panel);
		this.add(progress);
		
		//Set frame Visible
		this.getRootPane().setDefaultButton(newR);
		this.setVisible(true);
	}

	private void teamsAdd(JPanel panel, JLabel[][] teamName) {
		for(int i=0;i<15;i++) {
			for(int j=0;j<12;j++) {
				teamName[i][j].setHorizontalAlignment(0);
				teamName[i][j].setFont(Main.myFont);
				teamName[i][j].setForeground(Main.TextC);				
				if(i!=14&&j!=11)
					teamName[i][j].setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Main.DarkC));
				else
					if(i==14&&j!=11)
					teamName[i][j].setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Main.DarkC));
					else
						if(i!=14&&j==11)
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
				//Color status
				if(teamName[i][11].getText()=="Qualify")
					teamName[i][11].setForeground(Main.greenC);
				else
					if(teamName[i][11].getText()=="Out")
						teamName[i][11].setForeground(Main.redC);
					else
						if(teamName[i][11].getText()=="Play-In")
							teamName[i][11].setForeground(Main.orangeC);
						else
							teamName[i][11].setForeground(Main.TextC);
				//Color finalRank
				if(teamName[i][10].getText()=="Not Yet")
					teamName[i][10].setForeground(Main.TextC);
				else
					teamName[i][10].setForeground(Main.DarkC);
				panel.add(teamName[i][j]);
			}
			gameP+=Integer.parseInt(teamName[i][2].getText());
		}
	}

	private void titleAdd(Team[] team, JLabel[][] teamName, JLabel[] title) {
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
		for(int i=0;i<15;i++){
				teamName[i][0] = new JLabel(team[i].rank+"");
				teamName[i][1] = new JLabel(team[i].name);
				teamName[i][2] = new JLabel(team[i].gameP+"");
				teamName[i][3] = new JLabel(team[i].win+"");
				teamName[i][4] = new JLabel(team[i].loss+"");
				teamName[i][5] = new JLabel(team[i].per+"");
				teamName[i][6] = new JLabel(team[i].GBU+"");
				teamName[i][7] = new JLabel(team[i].GB+"");
				teamName[i][8] = new JLabel(team[i].gameR+"");
				teamName[i][9] = new JLabel("("+team[i].avUp+","+team[i].avLow+")");
				teamName[i][10] = new JLabel(team[i].fRank);
				teamName[i][11] = new JLabel(team[i].status);
		}
	}
}