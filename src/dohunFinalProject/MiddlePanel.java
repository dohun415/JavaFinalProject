package dohunFinalProject;

import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;
import java.util.*;

public class MiddlePanel extends JPanel {
	public void reset() {
		result.setVisible(false);
		ImageIcon basicImg = new ImageIcon("src/images/thomas.jpg");//set subway's basic image
		Image img = basicImg.getImage();
		Image imgUpdate = img.getScaledInstance(150, 100, Image.SCALE_SMOOTH);
		subway = new JLabel(new ImageIcon(imgUpdate));
		subway.setBounds(100,450,100,50);
	}
	
	BottomPanel bottomPanel;
	
	public JLabel result;
	public JLabel subway;
	public BufferedImage img;
	public JFileChooser fc = new JFileChooser();
	public Image updateImg;
	private Line[] lines;
	String preStationName = "";
	String curStationName="";
	int curStationX;
	int curStationY;

	public MiddlePanel(JPanel bottomPanel) {
		this.bottomPanel=(BottomPanel)bottomPanel;
		
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);

		ImageIcon basicImg = new ImageIcon("src/images/thomas.jpg");//set subway's basic image
		Image img = basicImg.getImage();
		Image imgUpdate = img.getScaledInstance(150, 100, Image.SCALE_SMOOTH);
		subway = new JLabel(new ImageIcon(imgUpdate));
		add(subway);
		subway.setBounds(100, 450, 110, 50);
		
		//create Stations
		JPanel st1 = Station("Gupa");
		JPanel st2 = Station("Castle");
		JPanel st3 = Station("Angook");
		JPanel st4 = Station("Jongro3ga");
		JPanel st5 = Station("Dongdaemoon");
		JPanel st6 = Station("Heogi");
		JPanel st7 = Station("Dangsan");
		JPanel st8 = Station("Moonrae");
		JPanel st9 = Station("Cityhall");
		JPanel st10 = Station("Eulgiro");
		JPanel st11 = Station("Park");
		JPanel st12 = Station("Guro");
		JPanel st13 = Station("Sindorim");
		JPanel st14 = Station("Seoul");
		JPanel st15 = Station("Oksoo");
		JPanel st16 = Station("Sinrim");
		JPanel st17 = Station("Sadang");
		JPanel st18 = Station("Gyodae");
		JPanel st19 = Station("Seungsoo");
		JPanel st20 = Station("Daechi");
		JPanel st21 = Station("Suseo");

		add(st1);
		add(st2);
		add(st3);
		add(st4);
		add(st5);
		add(st6);
		add(st7);
		add(st8);
		add(st9);
		add(st10);
		add(st11);
		add(st12);
		add(st13);
		add(st14);
		add(st15);
		add(st16);
		add(st17);
		add(st18);
		add(st19);
		add(st20);
		add(st21);

		st1.setBounds(100, 200, 100, 100);
		st2.setBounds(250, 200, 100, 100);
		st3.setBounds(400, 200, 100, 100);
		st4.setBounds(550, 200, 100, 100);
		st5.setBounds(700, 200, 100, 100);
		st6.setBounds(700, 50, 100, 100);
		st7.setBounds(100, 350, 100, 100);
		st8.setBounds(250, 350, 100, 100);
		st9.setBounds(400, 350, 100, 100);
		st10.setBounds(550, 350, 100, 100);
		st11.setBounds(700, 350, 100, 100);
		st12.setBounds(100, 500, 100, 100);
		st13.setBounds(250, 500, 100, 100);
		st14.setBounds(400, 500, 100, 100);
		st15.setBounds(550, 500, 100, 100);
		st16.setBounds(250, 650, 100, 100);
		st17.setBounds(400, 650, 100, 100);
		st18.setBounds(550, 650, 100, 100);
		st19.setBounds(700, 650, 100, 100);
		st20.setBounds(550, 800, 100, 100);
		st21.setBounds(700, 800, 100, 100);
		
		lines = new Line[12];
		for (int i = 0; i < 12; i++) {
			lines[i] = new Line();
		}
		for (int i = 0; i < 3; i++) {//set each line's color
			lines[i].setColor(Color.ORANGE);
		}
		for (int i = 3; i < 8; i++) {
			lines[i].setColor(Color.BLUE);
		}
		for (int i = 8; i < 12; i++) {
			lines[i].setColor(Color.GREEN);
		}
		repaint();
		
		JLabel pumpGate1 = new JLabel("Pumpgate");
		JLabel pumpGate2 = new JLabel("Pumpgate");
		JLabel pumpGate3 = new JLabel("Pumpgate");
		add(pumpGate1);
		add(pumpGate2);
		add(pumpGate3);
		pumpGate1.setForeground(Color.RED);
		pumpGate2.setForeground(Color.RED);
		pumpGate3.setForeground(Color.RED);
		pumpGate1.setBounds(345, 320, 80, 80);
		pumpGate2.setBounds(500, 250, 80, 80);
		pumpGate3.setBounds(195, 390, 80, 80);
		
		result = new JLabel();
		add(result);
		result.setSize(500, 500);
		result.setVisible(false);
	}

	public void load() {// upload subway image function
		int result = fc.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			String path = fc.getSelectedFile().getPath();
			try {
				img = ImageIO.read(new File(path));
				updateImg = img.getScaledInstance(150, 100, Image.SCALE_SMOOTH);// google
			} catch (IOException e) {
				e.printStackTrace();
			}

			subway.setIcon(new ImageIcon(updateImg));
		}
	}
	
	public JPanel Station(String stationName) {//station JPanel has three buttons
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 1));
		panel.setOpaque(false);
		CurStationActionListener curStationActionListener = new CurStationActionListener();
		SetStationActionListener setStationActionListener = new SetStationActionListener();

		JButton station = new JButton(stationName);
		JButton depart = new JButton("depart");
		JButton arrive = new JButton("arrive");

		station.addActionListener(curStationActionListener);
		depart.addActionListener(setStationActionListener);
		arrive.addActionListener(setStationActionListener);

		panel.add(station);
		panel.add(depart);
		panel.add(arrive);

		depart.setVisible(false);
		arrive.setVisible(false);

		return panel;
	}
	
	class CurStationActionListener implements ActionListener {//get curStationInfo
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton) e.getSource();
			String stationName = button.getText();
			JPanel panel = (JPanel) button.getParent();
			curStationName = stationName;

			int x = panel.getX() + button.getX();
			int y = panel.getY() + button.getY();
			curStationX = x;
			curStationY = y;

			if (preStationName.equals(curStationName)) {
				Component[] components = panel.getComponents();
				for (Component component : components) {
					if (component instanceof JButton && component != button) {

						JButton btn = (JButton) component;
						btn.setVisible(false);
						preStationName = "";
					}
				}
			} else {
				Component[] components = panel.getComponents();
				for (Component component : components) {
					if (component instanceof JButton && component != button) {

						JButton btn = (JButton) component;
						btn.setVisible(true);
						preStationName = curStationName;
					}
				}
			}
		}
	}

	class SetStationActionListener implements ActionListener {//setStationInfo
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton) e.getSource();
			String cmd = button.getText();
			JPanel panel = (JPanel) button.getParent();
			if (cmd.equals("depart")) {
				bottomPanel.departStation.setText(curStationName);
				bottomPanel.startX = curStationX;
				bottomPanel.startY = curStationY - 50;
			} else if (cmd.equals("arrive")) {
				bottomPanel.arriveStation.setText(curStationName);
				bottomPanel.targetX = curStationX;
				bottomPanel.targetY = curStationY - 50;
			}

			Component[] componentss = getComponents();
			for (Component components : componentss) {
				if (components instanceof JPanel) {
					JPanel a = (JPanel) components;
					Component[] component = a.getComponents();
					for (Component comp : component) {
						if (comp instanceof JButton) {
							JButton btn = (JButton) comp;
							String name = btn.getText();
							if (name.equals("depart") || name.equals("arrive")) {
								btn.setVisible(false);
							}
						}
					}
				}
			}
		}
	}
	
	class Line {
		private Color color;

		public void setColor(Color color) {
			this.color = color;
		}

		public void draw(Graphics g, int x, int y, int x2, int y2) {

			g.setColor(color);
			g.drawLine(x, y, x2, y2);
		}
	}
	public void paintComponent(Graphics g) {//draw lines
		super.paintComponent(g);

		lines[0].draw(g, 150, 215, 650, 215);// orange
		lines[1].draw(g, 600, 215, 600, 815);// orange
		lines[2].draw(g, 600, 815, 800, 815);// orange
		lines[3].draw(g, 600, 215, 800, 215);// blue
		lines[4].draw(g, 200, 515, 500, 515);// blue
		lines[5].draw(g, 450, 515, 450, 365);// blue
		lines[6].draw(g, 450, 365, 600, 215);// blue
		lines[7].draw(g, 750, 215, 750, 65);// blue
		lines[8].draw(g, 200, 365, 800, 365);// green
		lines[9].draw(g, 300, 675, 300, 365);// green
		lines[10].draw(g, 300, 665, 800, 665);// green
		lines[11].draw(g, 150, 365, 300, 515);

		repaint();
	}

}
