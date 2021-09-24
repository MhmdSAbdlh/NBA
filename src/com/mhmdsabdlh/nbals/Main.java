package com.mhmdsabdlh.nbals;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Main {

	static Color LightC = new Color(193, 207, 192);
	static Color DarkC = new Color(17, 50, 77);
	static Color TextC = new Color(107, 122, 161);
	static Color greenC = new Color(28,174,0);
	static Color redC = new Color(164,0,0);
	static Color orangeC = new Color(240,120,0);
	static JLabel credit = new JLabel("Created & Designed by MhmdSAbdlh");
	static Font smallF = new Font("Tahoma",Font.BOLD,17);
	static Font myFont = new Font("Tahoma",Font.BOLD,20);
	static Font titleF = new Font("Tahoma",Font.BOLD,65);
	
	static JMenuBar mb = new JMenuBar();
	static JMenu m1 = new JMenu("File");
	static JMenuItem m2 = new JMenuItem("About");
	static JMenuItem m11 = new JMenuItem("Open");
	static JMenuItem m12 = new JMenuItem("Save");
	static JMenuItem m13 = new JMenuItem("Exit");

	public static void main(String[] args) {
		
		new FrameConf();
	}
}