package dohunFinalProject;

import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;
import java.util.*;

public class TopPanel extends JPanel{
	MiddlePanel middlePanel;
	BottomPanel bottomPanel;
	
	public TopPanel(JPanel middlePanel,JPanel bottomPanel) {
		this.middlePanel=(MiddlePanel)middlePanel;
		this.bottomPanel=(BottomPanel)bottomPanel;
		
		setBackground(Color.CYAN);
		FlowLayout layout = new FlowLayout(FlowLayout.CENTER, 180, 10);
		setLayout(layout);

		JButton reset = new JButton("Restart");
		reset.addActionListener(new resetGameActionListener());
		add(reset);

		JLabel title = new JLabel("Subway Game");
		add(title);
		title.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));

		JButton file = new JButton("Change Subway?");
        file.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ((MiddlePanel) middlePanel).load();
            }
        });
		add(file);
	}
	
	public class resetGameActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			middlePanel.reset();
			bottomPanel.reset();
		}
	}
}
