package com.mhmdsabdlh.nbals;

import java.awt.Color;
import java.awt.GridLayout;
import java.io.*;
import java.net.URL;
import javax.swing.*;

@SuppressWarnings("serial")
class FrameEast extends JFrame{

	static Team[] teams = FrameConf.East();
	JSpinner teamWin[] = new JSpinner[15];
	JSpinner teamLoss[] = new JSpinner[15];
	
	FrameEast() {
		//Frame
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(720, 720);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setTitle("East Conference");
		this.getContentPane().setBackground(Main.DarkC);
		URL url = getClass().getResource("icon.png");
		ImageIcon icon = new ImageIcon(url);
		this.setIconImage(icon.getImage());
		
		//Panel
		JPanel panel = new JPanel();
		panel.setLocation(50, 20);
		panel.setSize(600, 550);
		panel.setLayout(new GridLayout(16, 3));
		panel.setBackground(Main.LightC);
		
		//Label and TextField
		JLabel teamName[] = new JLabel[15];
		JLabel title[] = new JLabel[3];
		teamsDef(teamName, title, teamWin, teamLoss);
		openEast(teamWin, teamLoss);
		
		//Start of Add to panel
		panel.add(title[0]);
		panel.add(title[1]);
		panel.add(title[2]);
		for(int i=0;i<15;i++) {
			panel.add(teamName[i]);
			panel.add(teamWin[i]);
			panel.add(teamLoss[i]);
		}
		
		//Start of setRank Button
		JButton setRank = new JButton("Rank on");
		setRank.setBounds(50, 580, 200, 50);
		setRank.setBackground(Main.LightC);
		setRank.setForeground(Main.DarkC);
		setRank.setFont(Main.myFont);
		setRank.addActionListener( e -> rankBtn(teamWin, teamLoss));
		//End of setRank Button
		
		//Start of Back Button
		JButton back = new JButton("<-- Back");
		back.setBounds(450, 580, 200, 50);
		back.setBackground(Main.LightC);
		back.setForeground(Main.DarkC);
		back.setFont(Main.myFont);
		back.addActionListener( e -> {
			this.dispose();
			new FrameConf();
			});	
		//End of Back Button
			
		//Credit
		Main.credit.setHorizontalAlignment(0);
		Main.credit.setBounds(0, 635, 720, 20);
		Main.credit.setForeground(Color.white);
		
		//Add MenuBar
		Main.mb = new JMenuBar();
		Main.m1 = new JMenu("File");
		Main.m2 = new JMenuItem("About");
		Main.mb.add(Main.m1);
		Main.mb.add(Main.m2);
		Main.m11 = new JMenuItem("Open");
		Main.m12 = new JMenuItem("Save");
		Main.m13 = new JMenuItem("Exit");
		Main.m1.add(Main.m11);
		Main.m1.add(Main.m12);
		Main.m1.add(Main.m13);
		Main.m11.addActionListener( e -> openEast(teamWin, teamLoss));
		Main.m12.addActionListener( e -> saveEast(teamWin, teamLoss));
		Main.m13.addActionListener( e -> System.exit(0));
		Main.m2.addActionListener( e -> JOptionPane.showMessageDialog(this,Main.credit.getText()));
		Main.mb.setFocusable(false);
		Main.mb.setFont(Main.myFont);
		
		//Add to Frame
        this.setJMenuBar(Main.mb);
		this.add(Main.credit);
		this.add(setRank);
		this.add(back);
		this.add(panel);
		
		//Show the Frame
		this.getRootPane().setDefaultButton(setRank);
		this.setVisible(true);
	}

	private void saveEast(JSpinner[] teamWin, JSpinner[] teamLoss) {
		try {
			FileWriter dataSaved = new FileWriter("East.csv");
			int i=0;
			for(@SuppressWarnings("unused") JSpinner str:teamWin){
				dataSaved.write(teamWin[i].getValue()+",");
				dataSaved.write(teamLoss[i].getValue()+System.lineSeparator());
				i++;
			}
			dataSaved.close();
		} catch (Exception e) {}
	}
	
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

	private void rankBtn(JSpinner[] teamWin, JSpinner[] teamLoss) {
		for(int i=0;i<15;i++) {
					 teams[i].win = (int)teamWin[i].getValue();
					 teams[i].loss = (int)teamLoss[i].getValue();
				 }
		saveEast(teamWin,teamLoss);
		NBACalc.gamePR(teams);
		NBACalc.rank(teams);
		NBACalc.gameBehind(teams);
		NBACalc.teamStatus(teams);
		NBACalc.rankAva(teams);
		NBACalc.FinalRank(teams);
		this.dispose();
		new EastMain();
	}
	
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

	private void teamsDef(JLabel[] teamName, JLabel[] title, JSpinner[] teamWin, JSpinner[] teamLoss) {
		for(int i=0;i<15;i++) {
			teamWin[i] = new JSpinner();
			JFormattedTextField temp = getTextField(teamWin[i]);
			temp.setHorizontalAlignment(0);
			temp.setBackground(Main.DarkC);
			temp.setForeground(Color.white);
			temp.setCaretColor(Main.LightC);
			teamWin[i].setFont(Main.myFont);
			teamWin[i].setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Main.LightC));
			teamLoss[i] = new JSpinner();
			JFormattedTextField temp2 = getTextField(teamLoss[i]);
			temp2.setHorizontalAlignment(0);
			temp2.setBackground(Main.DarkC);
			temp2.setForeground(Color.white);
			temp2.setCaretColor(Main.LightC);
			teamLoss[i].setFont(Main.myFont);
			teamLoss[i].setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Main.LightC));
		}
		title[0] = new JLabel("Team Name");
		title[0].setHorizontalAlignment(0);
		title[0].setFont(Main.myFont);
		title[0].setForeground(Main.DarkC);
		title[0].setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Main.DarkC));
		title[1] = new JLabel("Win");
		title[1].setHorizontalAlignment(0);
		title[1].setFont(Main.myFont);
		title[1].setForeground(Main.DarkC);
		title[1].setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Main.DarkC));
		title[2] = new JLabel("Loss");
		title[2].setHorizontalAlignment(0);
		title[2].setFont(Main.myFont);
		title[2].setForeground(Main.DarkC);
		title[2].setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Main.DarkC));
		for(int i=0;i<15;i++) {
			teamName[i] = new JLabel(teams[i].name);
			teamName[i].setHorizontalAlignment(0);
			teamName[i].setFont(Main.myFont);
			teamName[i].setForeground(Main.TextC);
			if(i!=14)
				teamName[i].setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Main.DarkC));
			else
				teamName[i].setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Main.DarkC));
		}
	}

}