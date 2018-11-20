import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Offline extends JPanel {

	int map[][] = new int[][] { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, -1, 1, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 1, -1, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };

	int maxSize = 45;
	int whitecheck = 0;
	int blackcheck = 0;
	int whitecount = 2;
	int blackcount = 2;
	int turn = Main.BLACK;
	int maxXY = Main.MaxXY;

	int Winner = 0;

	Computer com = new Computer();// Compuer 객체 생성

	// ImageIcon객체를 생성

	Image WhiteO = new ImageIcon("./리버시/1.png").getImage().getScaledInstance(maxSize, maxSize, Image.SCALE_DEFAULT);
	Image Cg[] = new Image[5];

	Image BlackO = new ImageIcon("./리버시/7.png").getImage().getScaledInstance(maxSize, maxSize, Image.SCALE_DEFAULT);

	Image changedImg = new ImageIcon("./Background.png").getImage().getScaledInstance(maxSize * maxXY + 1,
			maxSize * maxXY + 1, Image.SCALE_DEFAULT);

	public void Initializing() {

		map = new int[][] { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, -1, 1, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 1, -1, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };

		maxSize = 45;
		whitecheck = 0;
		blackcheck = 0;
		whitecount = 2;
		blackcount = 2;
		turn = Main.BLACK;
		Winner = 0;
	}

	public void ChangeBackground() {
		if (turn == 1)
			this.setBackground(Color.black);
		else
			this.setBackground(Color.white);
	}

	public void SetMapComponent(int _x, int _y, int _turn) {

		map[_x][_y] = _turn;

	}

	public int CheckAlgorithm(int _x, int _y, int _turn, int mode) {

		int myState = map[_y][_x];// GUI에서는 맵이 반대로 보이기 때문.
		int t = 0;
		int is_putable = 0;
		int count = 0;
		if (myState != 0) {
			return is_putable;
		}
		int i, j;
		// 윗 방향 세로 검사
		for (i = _y - 1; i >= 0; i--) {
			if (((_turn * -1)) == map[i][_x]) { // I는 현재 Seeking 하는 위치. 만약 Seeking이 현재 턴의 돌 색깔과 다르면 인자를 1로 올린다.
				t = 1;
				continue;// 턴의 돌 색깔이랑 같을 때까지 for문을 돌린다.
			} else if (_turn == map[i][_x]) {// 현재 돌 색깔과 만났을경우. T가 1일때만 기능을 수행한다.
				if (t == 1) {
					is_putable = 1;
					// System.out.println("세로위검사" + _turn);
					if (mode >= 1) {

						for (j = i + 1; j < _y; j++) {
							// map[j][_x] = _turn + 3;
							if (mode == 1) {
								SetMapComponent(j, _x, _turn + 3);// +3은 애니메이션의 수행을 한다.
							} else if (mode == 2) {
								count++;
							}
						}

					}
				}
				break;
			} else {// 만약 비어있으면 바로 끝!
				break;
			}
		}
		t = 0;
		// 아랫 방향 세로 검사
		for (i = _y + 1; i < maxXY; i++) {
			if (((_turn * -1)) == map[i][_x]) {
				t = 1;
				continue;
			} else if (_turn == map[i][_x]) {
				if (t == 1) {
					is_putable = 1;
					// System.out.println("세로아래검사" + _turn);

					if (mode >= 1) {

						for (j = i - 1; j > _y; j--) {
							// map[j][_x] = _turn + 3;
							if (mode == 1) {
								SetMapComponent(j, _x, _turn + 3);
							} else if (mode == 2) {
								count++;
							}
						} //

					}
				}
				break;
			} else {
				break;
			}
		}
		t = 0;
		// 왼 방향 가로 검사
		for (i = _x - 1; i >= 0; i--) {

			if (((_turn * -1)) == map[_y][i]) {
				t = 1;
				continue;
			} else if (_turn == map[_y][i]) {
				if (t == 1) {
					is_putable = 1;
					// System.out.println("왼가로검사" + _turn);

					if (mode >= 1) {

						for (j = i + 1; j < _x; j++) {
							// map[_y][j] = _turn + 3;
							if (mode == 1) {
								SetMapComponent(_y, j, _turn + 3);
							} else if (mode == 2) {
								count++;
							}
						} //

					}
				}

				break;
			} else {
				break;
			}

		}
		t = 0;
		// 오른 방향 가로 검사
		for (i = _x + 1; i < maxXY; i++) {
			if (((_turn * -1)) == map[_y][i]) {
				t = 1;
				continue;
			} else if (_turn == map[_y][i]) {
				if (t == 1) {
					is_putable = 1;
					// System.out.println("오른가로검사" + _turn);

					if (mode >= 1) {

						for (j = i - 1; j > _x; j--) {
							// map[_y][j] = _turn + 3;

							if (mode == 1) {
								SetMapComponent(_y, j, _turn + 3);
							} else if (mode == 2) {
								count++;
							}

						} //

					}
				}
				break;
			} else {
				break;
			}

		}

		t = 0;
		// 왼쪽 위 방향 대각선 검사
		for (i = 1; (_x - i) >= 0 && (_y - i) >= 0; i++) {
			if (((_turn * -1)) == map[_y - i][_x - i]) {
				t = 1;
				continue;
			} else if (_turn == map[_y - i][_x - i]) {
				if (t == 1) {
					is_putable = 1;
					// System.out.println("왼위대각검사" + _turn);

					if (mode >= 1) {

						for (j = i - 1; j > 0; j--) {
							// map[_y - j][_x - j] = _turn + 3;

							if (mode == 1) {
								SetMapComponent(_y - j, _x - j, _turn + 3);
							} else if (mode == 2) {
								count++;
							}
						} //

					}
				}
				break;
			} else {
				break;
			}
		}
		t = 0;
		// 오른쪽 아래 방향 대각선 검사
		for (i = 1; (_x + i) < maxXY && (_y + i) < maxXY; i++) {
			if (((_turn * -1)) == map[_y + i][_x + i]) {
				t = 1;
				continue;
			} else if (_turn == map[_y + i][_x + i]) {
				if (t == 1) {
					is_putable = 1;
					// System.out.println("오른아래대각검사" + _turn);

					if (mode >= 1) {

						for (j = i - 1; j > 0; j--) {
							// map[_y + j][_x + j] = _turn + 3;

							if (mode == 1) {
								SetMapComponent(_y + j, _x + j, _turn + 3);
							} else if (mode == 2) {
								count++;
							}
						} //

					}
				}
				break;
			} else {
				break;
			}
		}
		t = 0;
		// 왼쪽 아래 방향 대각선 검사
		for (i = 1; (_x - i) >= 0 && (_y + i) < maxXY; i++) {
			if (((_turn * -1)) == map[_y + i][_x - i]) {
				t = 1;
				continue;
			} else if (_turn == map[_y + i][_x - i]) {
				if (t == 1) {
					is_putable = 1;
					// System.out.println("왼아래대각검사" + _turn);

					if (mode >= 1) {

						for (j = i - 1; j > 0; j--) {
							// map[_y + j][_x - j] = _turn + 3;

							if (mode == 1) {
								SetMapComponent(_y + j, _x - j, _turn + 3);
							} else if (mode == 2) {
								count++;
							}
						} //

					}
				}
				break;
			} else {
				break;
			}
		}
		t = 0;
		// 오른쪽 위 방향 대각선 검사
		for (i = 1; (_x + i) < maxXY && (_y - i) >= 0; i++) {
			if (((_turn * -1)) == map[_y - i][_x + i]) {
				t = 1;
				continue;
			} else if (_turn == map[_y - i][_x + i]) {
				if (t == 1) {
					is_putable = 1;
					// System.out.println("오른위대각검사" + _turn);

					if (mode >= 1) {

						for (j = i - 1; j > 0; j--) {
							// map[_y - j][_x + j] = _turn + 3;
							if (mode == 1) {
								SetMapComponent(_y - j, _x + j, _turn + 3);
							} else if (mode == 2) {
								count++;
							}
						} //

					}
				}
				break;
			} else {
				break;
			}
		}
		t = 0;
		// System.out.println("Y: " + _y + " X: " + _x);
		if (mode == 2) {
			is_putable = count;
		}
		return is_putable;

	}

	public void ComputeClick(int x, int y) {

		int i, j;

		if ((CheckAlgorithm(x, y, turn, 0)) == 1 && map[y][x] == 0) { // 알고리즘에 충족하고 비어있습니까?
			CheckAlgorithm(x, y, turn, 1);
			SetMapComponent(y, x, turn);

			blackcount = whitecount = blackcheck = whitecheck = 0;
			for (i = 0; i < maxXY; i++) {
				for (j = 0; j < maxXY; j++) {
					blackcheck += CheckAlgorithm(i, j, Main.BLACK, 0);// 흑돌이 놓을수 있는 수
					whitecheck += CheckAlgorithm(i, j, Main.WHITE, 0);// 백돌이 넣을수 있는 수

					if (map[i][j] == 1 || map[i][j] == 4) {
						blackcount++;
					} else if (map[i][j] == -1 || map[i][j] == 2) {
						whitecount++;
					}
				}
			}
			System.out.println(blackcheck+" "+whitecheck+" "+blackcount+" "+whitecount);

			if ((blackcheck == 0 && whitecheck == 0) || (blackcount + whitecount) >= maxXY * maxXY) {
				int winner = 0;
				if (blackcount > whitecount) {
					winner = Main.BLACK;
				} else if (blackcount < whitecount) {
					winner = Main.WHITE;
				} else if (blackcount == whitecount) {
					winner = Main.DRAW;
				}

				win(winner);


			} else {

				turn *= -1;
				if (whitecheck == 0 && turn == Main.WHITE) {
					turn *= -1;
					JOptionPane.showMessageDialog(null, "백색이 놓을 곳이 없네요. 턴을 바꿉니다.", "Turn", JOptionPane.YES_OPTION);
					ChangeBackground();
				}
				if (blackcheck == 0 && turn == Main.BLACK) {
					turn *= -1;
					JOptionPane.showMessageDialog(null, "흑색이 놓을 곳이 없네요. 턴을 바꿉니다.", "Turn", JOptionPane.YES_OPTION);
					ChangeBackground();
				}
			}

			try {

				AudioInputStream ais2 = AudioSystem.getAudioInputStream(new File("./마우스딸깍.wav"));
				Clip clip2 = AudioSystem.getClip();
				clip2.open(ais2);
				clip2.start();
			} catch (Exception ex) {
			}
		} else {
			try {
				AudioInputStream ais = AudioSystem.getAudioInputStream(new File("./sound_button_wrong.wav"));
				Clip clip = AudioSystem.getClip();
				clip.open(ais);
				clip.start();
			} catch (Exception ex) {
			}
		}
		ChangeBackground();

	}

	JDialog WinDialog = new JDialog();

	public void win(int winner) {

		Winner = winner;
		if (winner == Main.WHITE) {
			WinDialog.setTitle("흰돌의 승리!");
			WinDialog.add(new JLabel(new ImageIcon(
					new ImageIcon("./흰돌승리.png").getImage().getScaledInstance(1024, 512, Image.SCALE_SMOOTH))));
			WinDialog.setVisible(true);
			WinDialog.setSize(1024, 512);
			WinDialog.setLocationRelativeTo(null);

		} else if (winner == Main.BLACK) {
			WinDialog.setTitle("흑돌의 승리!");
			WinDialog.add(new JLabel(new ImageIcon(
					new ImageIcon("./흑돌승리.png").getImage().getScaledInstance(1024, 512, Image.SCALE_SMOOTH))));
			WinDialog.setVisible(true);
			WinDialog.setSize(1024, 512);
			WinDialog.setLocationRelativeTo(null);
		} else if (winner == 3) {
			WinDialog.setTitle("무승부!");
			WinDialog.add(new JLabel(new ImageIcon(
					new ImageIcon("./무승부.png").getImage().getScaledInstance(1024, 512, Image.SCALE_SMOOTH))));
			WinDialog.setVisible(true);
			WinDialog.setSize(1024, 512);
			WinDialog.setLocationRelativeTo(null);
		} else {
			WinDialog.setTitle("오류났어");
		}
		Initializing();
	}

	Offline(Main main) {

		new Timer(100, new TimerListener()).start();

		this.setPreferredSize(new Dimension(maxSize * 16 + 300, maxSize * 16 + 1));
		this.setBackground(Color.black);
		this.setLayout(null);
		int i;
		for (i = 0; i < 5; i++) {
			Cg[i] = new ImageIcon("./리버시/" + (i + 2) + ".png").getImage().getScaledInstance(maxSize, maxSize,
					Image.SCALE_DEFAULT);
		}
		addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {

				if (turn == Main.BLACK) {
					if (arg0.getX() <= maxSize * maxXY && arg0.getY() <= maxSize * maxXY) {// Clamp
						int clkx = arg0.getX() / maxSize;
						int clky = arg0.getY() / maxSize;
						ComputeClick(clkx, clky);


						for (int i = 0; i < maxXY; i++) {
							for (int j = 0; j < maxXY; j++) {
								com.SetWeight(i, j, CheckAlgorithm(i, j, turn, 2));
							}
						}
						com.ThinkBestPosition();
					}
					if (turn == Main.WHITE) {

						Timer time = new Timer(1000, new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent arg0) {
								ComputeClick(com.X, com.Y);
							}
						});
						time.setRepeats(false);
						time.start();

					}
					
					if (Winner != 0) {
						main.c.removeAll();
						main.c.add(main.title); // Title로 다시 돌아간다.
						main.c.repaint();
						main.c.revalidate();
						main.setSize(650, 500);
					}

				}

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

		});

	}

	private class TimerListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			repaint(); // GUI를 계속 그린다.
		}
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		g.drawImage(changedImg, 1, 1, this);
		g.setFont(new Font("Arial", Font.ITALIC, 20));

		g.setColor(Color.white);
		for (int i = 0; i <= maxXY; i++) {

			g.drawLine(0, maxSize * i, maxSize * maxXY, maxSize * i);
			g.drawLine(maxSize * i, 0, maxSize * i, maxSize * maxXY);
		}

		for (int i = 0; i < maxXY; i++)
			for (int j = 0; j < maxXY; j++) {
				if (map[j][i] == 1) {

					g.drawImage(BlackO, i * maxSize, j * maxSize, this);
				} else if (map[j][i] == -1) {

					g.drawImage(WhiteO, i * maxSize, j * maxSize, this);

				}
				if (map[j][i] == 2) {
					for (int k = 4; k >= 0; k--) {
						g.drawImage(Cg[k], i * maxSize, j * maxSize, this);
					}

					map[j][i] = -1;
				} else if (map[j][i] == 4) {
					for (int k = 0; k <= 4; k++) {
						g.drawImage(Cg[k], i * maxSize, j * maxSize, this);

					}

					map[j][i] = 1;
				}
			}

		if (turn == Main.BLACK) {

			g.setColor(Color.white);
			g.drawString("Black", 800, 100);

		} else {
			g.setColor(Color.black);
			g.drawString("White(Computer)", 800, 100);
		}
		g.drawString("BlackCount : " + blackcount, 800, 200);
		g.drawString("WhiteCount : " + whitecount, 800, 300);

	}

}
