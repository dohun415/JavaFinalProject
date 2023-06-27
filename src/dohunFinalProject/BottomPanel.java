package dohunFinalProject;

import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;
import java.util.*;
import java.util.Queue;

public class BottomPanel extends JPanel{
	void reset() {
		currentMoveNum=0;
		departStation.setText("");
		arriveStation.setText("");
		currentMoveNumField.setText(Integer.toString(currentMoveNum));
		targetMoveNumField.setText("0");
	}
	
	JPanel middlePanel;
	JTextField departStation;
	JTextField arriveStation;
	JTextField currentMoveNumField;
	JTextField targetMoveNumField;
	int currentMoveNum = 0;
	int targetMoveNum = 0;
	int startX, startY;
	int targetX, targetY;

	public BottomPanel() {
		
		setBackground(Color.GREEN);
		FlowLayout layout = new FlowLayout(FlowLayout.CENTER, 30, 10);
		setLayout(layout);
		
		JLabel depart = new JLabel("Depart Station");
		JLabel arrive = new JLabel("Arrive Station");
		JLabel cur = new JLabel("You");
		JLabel tar = new JLabel("Target");

		departStation = new JTextField(10);
		arriveStation = new JTextField(10);
		currentMoveNumField = new JTextField(5);
		targetMoveNumField = new JTextField(5);
		JButton start = new JButton("Start");
		start.addActionListener(new StartGameActionListener());
		
		departStation.setText("");
		arriveStation.setText("");

		departStation.setEnabled(false);
		arriveStation.setEnabled(false);
		currentMoveNumField.setEnabled(false);
		targetMoveNumField.setEnabled(false);

		add(depart);
		add(departStation);
		add(arrive);
		add(arriveStation);
		add(start);
		add(cur);
		add(currentMoveNumField);
		add(tar);
		add(targetMoveNumField);

		currentMoveNumField.setText(Integer.toString(currentMoveNum));
	}
	
	public void setMiddlePanel(JPanel middlePanel) {
	    this.middlePanel = (MiddlePanel)middlePanel;
	}


	class StartGameActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if ((departStation.getText().equals("") || arriveStation.getText().equals(""))
					|| departStation.getText().equals(arriveStation.getText())) {

			} else {

				String departSt = departStation.getText();
				String arriveSt = arriveStation.getText();

				Graph graph = new Graph(departSt, arriveSt);
				graph.BFS();
				targetMoveNum = graph.getDistance();

				targetMoveNumField.setText(Integer.toString(targetMoveNum));

				Component[] componentss = middlePanel.getComponents();
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

				((MiddlePanel)middlePanel).subway.setLocation(startX, startY);

				Container d = middlePanel.getRootPane().getContentPane();
				d.addKeyListener(new MoveSubKeyListener());
				d.setFocusable(true);
				d.requestFocus();
			}
		}
	}
	
	class MoveSubKeyListener extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();

			switch (keyCode) {
			case KeyEvent.VK_UP:
				if (moveAbleUp[(((MiddlePanel)middlePanel).subway.getY() + 50) / 50][((MiddlePanel)middlePanel).subway.getX() / 50] == 1) {
					((MiddlePanel)middlePanel).subway.setLocation(((MiddlePanel)middlePanel).subway.getX(), ((MiddlePanel)middlePanel).subway.getY() - 150);
					currentMoveNum++;
					arriveCheck();
				}
				break;
			case KeyEvent.VK_DOWN:
				if (moveAbleDown[(((MiddlePanel)middlePanel).subway.getY() + 50) / 50][((MiddlePanel)middlePanel).subway.getX() / 50] == 1) {
					((MiddlePanel)middlePanel).subway.setLocation(((MiddlePanel)middlePanel).subway.getX(), ((MiddlePanel)middlePanel).subway.getY() + 150);
					currentMoveNum++;
					arriveCheck();
				}
				break;
			case KeyEvent.VK_LEFT:
				if (moveAbleLeft[(((MiddlePanel)middlePanel).subway.getY() + 50) / 50][((MiddlePanel)middlePanel).subway.getX() / 50] == 1) {
					((MiddlePanel)middlePanel).subway.setLocation(((MiddlePanel)middlePanel).subway.getX() - 150, ((MiddlePanel)middlePanel).subway.getY());
					currentMoveNum++;
					arriveCheck();
				}
				break;
			case KeyEvent.VK_RIGHT:
				if (moveAbleRight[(((MiddlePanel)middlePanel).subway.getY() + 50) / 50][((MiddlePanel)middlePanel).subway.getX() / 50] == 1) {
					((MiddlePanel)middlePanel).subway.setLocation(((MiddlePanel)middlePanel).subway.getX() + 150, ((MiddlePanel)middlePanel).subway.getY());
					currentMoveNum++;
					arriveCheck();
				}
			}
			currentMoveNumField.setText(Integer.toString(currentMoveNum));
		}

		void arriveCheck() {
			if (((MiddlePanel)middlePanel).subway.getX() == targetX &&((MiddlePanel)middlePanel).subway.getY() == targetY) {
				if (currentMoveNum == targetMoveNum) {
					((MiddlePanel)middlePanel).result.setForeground(Color.GREEN);
					((MiddlePanel)middlePanel).result.setText("SUCCESS");
					((MiddlePanel)middlePanel).setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
					((MiddlePanel)middlePanel).result.setVisible(true);
				} else {
					((MiddlePanel)middlePanel).result.setForeground(Color.RED);
					((MiddlePanel)middlePanel).result.setText("FAIL");
					((MiddlePanel)middlePanel).setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
					((MiddlePanel)middlePanel).result.setVisible(true);
				}
			}
		}
	}

	int[][] moveAbleUp = { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0 } };
	int[][] moveAbleDown = { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
	int[][] moveAbleLeft = { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 } };
	int[][] moveAbleRight = { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0 } };

	public class Graph {
		HashMap<String, Integer> map = new HashMap<>();
		boolean[][] edge = new boolean[22][22];
		boolean[] visited = new boolean[22];
		int departIdx, arriveIdx;
		int targetNum = 0;
		int[] distance = new int[22];

		public Graph(String departS, String arriveS) {
			map.put("Gupa", 1);
			map.put("Castle", 2);
			map.put("Angook", 3);
			map.put("Jongro3ga", 4);
			map.put("Dongdaemoon", 5);
			map.put("Heogi", 6);
			map.put("Dangsan", 7);
			map.put("Moonrae", 8);
			map.put("Cityhall", 9);
			map.put("Eulgiro", 10);
			map.put("Park", 11);
			map.put("Guro", 12);
			map.put("Sindorim", 13);
			map.put("Seoul", 14);
			map.put("Oksoo", 15);
			map.put("Sinrim", 16);
			map.put("Sadang", 17);
			map.put("Gyodae", 18);
			map.put("Seungsoo", 19);
			map.put("Daechi", 20);
			map.put("Suseo", 21);

			edge[1][2] = edge[2][1] = true;
			edge[2][3] = edge[3][2] = true;
			edge[3][4] = edge[4][3] = true;
			edge[4][5] = edge[5][4] = true;
			edge[4][10] = edge[10][4] = true;
			edge[5][6] = edge[6][5] = true;
			edge[7][8] = edge[8][7] = true;
			edge[8][13] = edge[13][8] = true;
			edge[9][14] = edge[14][9] = true;
			edge[9][10] = edge[10][9] = true;
			edge[10][11] = edge[11][10] = true;
			edge[10][15] = edge[15][10] = true;
			edge[12][13] = edge[13][12] = true;
			edge[13][14] = edge[14][13] = true;
			edge[13][16] = edge[16][13] = true;
			edge[15][18] = edge[18][15] = true;
			edge[16][17] = edge[17][16] = true;
			edge[17][18] = edge[18][17] = true;
			edge[18][19] = edge[19][18] = true;
			edge[18][20] = edge[20][18] = true;
			edge[20][21] = edge[21][20] = true;

			departIdx = map.get(departS);
			arriveIdx = map.get(arriveS);
		}

		void BFS() {
			Queue<Integer> queue = new LinkedList<>();
			queue.add(departIdx);
			visited[departIdx] = true;
			distance[departIdx] = 0;
			while (!queue.isEmpty()) {
				Integer front = queue.poll();
				if (front == arriveIdx) {
					return;
				}
				for (int i = 0; i < 22; i++) {
					if (edge[front][i] && !visited[i]) {
						queue.add(i);
						visited[i] = true;
						distance[i] = distance[front] + 1;
					}
				}
			}
		}

		int getDistance() {
			return distance[arriveIdx];
		}
	}
}
