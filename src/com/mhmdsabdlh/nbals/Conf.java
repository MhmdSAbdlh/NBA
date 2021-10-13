package com.mhmdsabdlh.nbals;

import java.awt.Color;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
class Conf extends JFrame{	

	Conf(){
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
			new West();
		});			
		East.setBounds(120, 350, 100, 50);
		East.setBackground(Main.LightC);
		East.setForeground(Main.DarkC);
		East.setFont(Main.myFont);
		East.setFocusable(false);
		East.addActionListener( e -> {
			this.dispose();
			new East();
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
		Main.credit.setBounds(0, 530, 600, 20);
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