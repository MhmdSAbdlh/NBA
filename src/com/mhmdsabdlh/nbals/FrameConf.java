package com.mhmdsabdlh.nbals;

import java.awt.Color;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
class FrameConf extends JFrame{	
	//West Teams
	static Team[] West(){
			Team[] team = new Team[15];
			team[0] = new Team("Lakers");
			team[1] = new Team("Clippers");
			team[2] = new Team("Jazz");
			team[3] = new Team("Warrios");
			team[4] = new Team("Spurs");
			team[5] = new Team("Pelicans");
			team[6] = new Team("Suns");
			team[7] = new Team("Grizzlies");
			team[8] = new Team("Nuggets");
			team[9] = new Team("OKC");
			team[10] = new Team("Rockets");
			team[11] = new Team("Mavs");
			team[12] = new Team("Kings");
			team[13] = new Team("Wolves");
			team[14] = new Team("Portland");
			
			return team;
		}
	
	//East Teams
	static Team[] East() {
			Team[] east = new Team[15];
			east[0] = new Team("76ers");
			east[1] = new Team("Nets");
			east[2] = new Team("Celtics");
			east[3] = new Team("Heat");
			east[4] = new Team("Raptors");
			east[5] = new Team("Bucks");
			east[6] = new Team("Hawks");
			east[7] = new Team("Knicks");
			east[8] = new Team("Wizards");
			east[9] = new Team("Pacers");
			east[10] = new Team("Hornets");
			east[11] = new Team("Bulls");
			east[12] = new Team("Cavs");
			east[13] = new Team("Magic");
			east[14] = new Team("Pistons");
			return east;
		}

	FrameConf(){
		//Frame
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(600, 600);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setTitle("NBA Live Standing");
		this.getContentPane().setBackground(Main.DarkC);
		URL url = getClass().getResource("icon.png");
		ImageIcon icon = new ImageIcon(url);
		this.setIconImage(icon.getImage());
//		
		//Button
		JButton West = new JButton("West");
		JButton East = new JButton("East");
		West.setBounds(370, 350, 100, 50);
		West.setBackground(Main.LightC);
		West.setForeground(Main.DarkC);
		West.setFont(Main.myFont);
		West.setFocusable(false);
		West.addActionListener( e -> {
			this.dispose();
			new FrameWest();
		});			
		East.setBounds(120, 350, 100, 50);
		East.setBackground(Main.LightC);
		East.setForeground(Main.DarkC);
		East.setFont(Main.myFont);
		East.setFocusable(false);
		East.addActionListener( e -> {
			this.dispose();
			new FrameEast();
		});
		
		//Label
		JLabel title = new JLabel(icon);
		JLabel subTitle = new JLabel("Choose the Conference");
		title.setBounds(225, 100, 150, 150);
		title.setHorizontalAlignment(0);
		title.setForeground(Main.LightC);
		title.setFont(Main.titleF);
		title.setFocusable(false);
		subTitle.setBounds(0, 250, 600, 50);
		subTitle.setHorizontalAlignment(0);
		subTitle.setForeground(Color.white);
		subTitle.setFont(Main.myFont);
		subTitle.setFocusable(false);
		
		//Credit
		Main.credit.setHorizontalAlignment(0);
		Main.credit.setBounds(0, 540, 600, 20);
		Main.credit.setForeground(Color.white);
		
		//Add to frame
		this.add(title);
		this.add(subTitle);
		this.add(West);
		this.add(East);
		this.add(Main.credit);
		
		//Show Visible
		this.setVisible(true);
	}
}