package com.mhmdsabdlh.nbals;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JSpinner;

public class Main {

	static Color lightC = new Color(193, 207, 192);
	static Color darkC = new Color(17, 50, 77);
	static Color TextC = new Color(107, 122, 161);
	static Color greenC = new Color(28,174,0);
	static Color redC = new Color(164,0,0);
	static Color orangeC = new Color(240,120,0);
	static Color greenD = new Color(10,60,0);
	static Color redD = new Color(100,0,0);
	static Color orangeD = new Color(150,80,0);
	static JLabel credit = new JLabel("Created & Designed by MhmdSAbdlh");
	static Font smallF = new Font("Tahoma",Font.BOLD,17);
	static Font myFont = new Font("Tahoma",Font.BOLD,20);
	static Font titleF = new Font("Tahoma",Font.BOLD,65);

	public static void main(String[] args) {
		
		new Conf();
	}
	
	static void hideSpinnerArrow(JSpinner spinner) {
		for (Component component : spinner.getComponents()) {
	        if (component.getName() != null && component.getName().endsWith("Button")) {
	            spinner.remove(component);
	        }
	    }
    }
}