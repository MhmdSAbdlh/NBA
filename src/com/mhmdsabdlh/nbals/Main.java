package com.mhmdsabdlh.nbals;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

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

	public static void main(String[] args) {
		
		new Conf();
	}
}