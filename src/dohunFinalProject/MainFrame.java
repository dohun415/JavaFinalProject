package dohunFinalProject;

import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;
import java.util.*;

public class MainFrame extends JFrame{
	public JPanel middlePanel;
	public JPanel topPanel;
	public JPanel bottomPanel;
	
	public MainFrame() {
		super("basicFrame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new BorderLayout());

		bottomPanel = new BottomPanel();
		middlePanel = new MiddlePanel(bottomPanel);
		((BottomPanel)bottomPanel).setMiddlePanel(middlePanel);
		topPanel = new TopPanel(middlePanel,bottomPanel);
		
		c.add(topPanel, BorderLayout.NORTH);
		c.add(middlePanel, BorderLayout.CENTER);
		c.add(bottomPanel, BorderLayout.SOUTH);

		setSize(1000, 1000);
		setVisible(true);
		setResizable(false);
	}
	
	public static void main(String[] args) {
		new MainFrame();
	}
}
