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

	int map[][] = { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
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

	Computer com = new Computer();//Compuer ��ü ����
	
	// ImageIcon��ü�� ����

	Image WhiteO = new ImageIcon("./������/1.png").getImage().getScaledInstance(maxSize, maxSize, Image.SCALE_DEFAULT);
	Image Cg[] = new Image[5];

	Image BlackO = new ImageIcon("./������/7.png").getImage().getScaledInstance(maxSize, maxSize, Image.SCALE_DEFAULT);

	Image changedImg = new ImageIcon("./Background.png").getImage().getScaledInstance(maxSize * maxXY + 1,
			maxSize * maxXY + 1, Image.SCALE_DEFAULT);

	
	
	
	
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

		int myState = map[_y][_x];// GUI������ ���� �ݴ�� ���̱� ����.
		int t = 0;
		int is_putable = 0;
		int count = 0;
		if (myState != 0) {
			return is_putable;
		}
		int i, j;
		// �� ���� ���� �˻�
		for (i = _y - 1; i >= 0; i--) {
			if (((_turn * -1)) == map[i][_x]) { // I�� ���� Seeking �ϴ� ��ġ. ���� Seeking�� ���� ���� �� ����� �ٸ��� ���ڸ� 1�� �ø���.
				t = 1;
				continue;// ���� �� �����̶� ���� ������ for���� ������.
			} else if (_turn == map[i][_x]) {// ���� �� ����� ���������. T�� 1�϶��� ����� �����Ѵ�.
				if (t == 1) {
					is_putable = 1;
					// System.out.println("�������˻�" + _turn);
					if (mode >= 1) {

						for (j = i + 1; j < _y; j++) {
							// map[j][_x] = _turn + 3;
							if (mode == 1) {
								SetMapComponent(j, _x, _turn + 3);// +3�� �ִϸ��̼��� ������ �Ѵ�.
							} else if (mode == 2) {
								count++;
							}
						}

					}
				}
				break;
			} else {// ���� ��������� �ٷ� ��!
				break;
			}
		}
		t = 0;
		// �Ʒ� ���� ���� �˻�
		for (i = _y + 1; i < maxXY; i++) {
			if (((_turn * -1)) == map[i][_x]) {
				t = 1;
				continue;
			} else if (_turn == map[i][_x]) {
				if (t == 1) {
					is_putable = 1;
					// System.out.println("���ξƷ��˻�" + _turn);

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
		// �� ���� ���� �˻�
		for (i = _x - 1; i >= 0; i--) {

			if (((_turn * -1)) == map[_y][i]) {
				t = 1;
				continue;
			} else if (_turn == map[_y][i]) {
				if (t == 1) {
					is_putable = 1;
					// System.out.println("�ް��ΰ˻�" + _turn);

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
		// ���� ���� ���� �˻�
		for (i = _x + 1; i < maxXY; i++) {
			if (((_turn * -1)) == map[_y][i]) {
				t = 1;
				continue;
			} else if (_turn == map[_y][i]) {
				if (t == 1) {
					is_putable = 1;
					// System.out.println("�������ΰ˻�" + _turn);

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
		// ���� �� ���� �밢�� �˻�
		for (i = 1; (_x - i) >= 0 && (_y - i) >= 0; i++) {
			if (((_turn * -1)) == map[_y - i][_x - i]) {
				t = 1;
				continue;
			} else if (_turn == map[_y - i][_x - i]) {
				if (t == 1) {
					is_putable = 1;
					// System.out.println("�����밢�˻�" + _turn);

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
		// ������ �Ʒ� ���� �밢�� �˻�
		for (i = 1; (_x + i) < maxXY && (_y + i) < maxXY; i++) {
			if (((_turn * -1)) == map[_y + i][_x + i]) {
				t = 1;
				continue;
			} else if (_turn == map[_y + i][_x + i]) {
				if (t == 1) {
					is_putable = 1;
					// System.out.println("�����Ʒ��밢�˻�" + _turn);

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
		// ���� �Ʒ� ���� �밢�� �˻�
		for (i = 1; (_x - i) >= 0 && (_y + i) < maxXY; i++) {
			if (((_turn * -1)) == map[_y + i][_x - i]) {
				t = 1;
				continue;
			} else if (_turn == map[_y + i][_x - i]) {
				if (t == 1) {
					is_putable = 1;
					// System.out.println("�޾Ʒ��밢�˻�" + _turn);

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
		// ������ �� ���� �밢�� �˻�
		for (i = 1; (_x + i) < maxXY && (_y - i) >= 0; i++) {
			if (((_turn * -1)) == map[_y - i][_x + i]) {
				t = 1;
				continue;
			} else if (_turn == map[_y - i][_x + i]) {
				if (t == 1) {
					is_putable = 1;
					// System.out.println("�������밢�˻�" + _turn);

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

		
		


		if ((CheckAlgorithm(x, y, turn, 0)) == 1 && map[y][x] == 0) { // �˰��� �����ϰ� ����ֽ��ϱ�?
			CheckAlgorithm(x, y, turn, 1);
			SetMapComponent(y, x, turn);
			
			blackcheck = whitecheck = 0;
			for (i = 0; i < maxXY; i++) {
				for (j = 0; j < maxXY; j++) {
					blackcheck += CheckAlgorithm(i, j, Main.BLACK, 0);
					whitecheck += CheckAlgorithm(i, j, Main.WHITE, 0);

				}
			}


			if ((blackcheck == 0 && whitecheck == 0) || (blackcount + whitecount) >= maxXY * maxXY) {
				int winner = 0;
				if (blackcount > whitecount) {
					winner = Main.BLACK;
				} else if (blackcount < whitecount) {
					winner = Main.WHITE;
				} else if (blackcount == whitecount) {
					winner = 3;
				}

				win(winner);
				Winner = winner;

			}

			// map[y][x] = turn;// �� ä���.

			turn *= -1;
			if (whitecheck == 0 && turn == Main.WHITE) {
				turn *= -1;
				JOptionPane.showMessageDialog(null,"����� ���� ���� ���׿�. ���� �ٲߴϴ�.","�ȳ�", JOptionPane.YES_OPTION);
				ChangeBackground();
			}
			if (blackcheck == 0 && turn == Main.BLACK) {
				turn *= -1;
				JOptionPane.showMessageDialog(null,"����� ���� ���� ���׿�. ���� �ٲߴϴ�.","�ȳ�", JOptionPane.YES_OPTION);
				ChangeBackground();
			}

			try {

				AudioInputStream ais2 = AudioSystem.getAudioInputStream(new File("./���콺����.wav"));
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

	void win(int winner) {

		if (winner == Main.WHITE) {
			WinDialog.setTitle("���� �¸�!");
			WinDialog.add(new JLabel(new ImageIcon(
					new ImageIcon("./�򵹽¸�.png").getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH))));
			WinDialog.setVisible(true);
			WinDialog.setSize(400, 400);
			WinDialog.setLocationRelativeTo(null);

		} else if (winner == Main.BLACK) {
			WinDialog.setTitle("�浹�� �¸�!");
			WinDialog.add(new JLabel(new ImageIcon(
					new ImageIcon("./�浹�¸�.png").getImage().getScaledInstance(256, 512, Image.SCALE_SMOOTH))));
			WinDialog.setVisible(true);
			WinDialog.setSize(400, 400);
			WinDialog.setLocationRelativeTo(null);
		} else if (winner == 3) {
			WinDialog.setTitle("���º�!");
			WinDialog.add(new JLabel(new ImageIcon(
					new ImageIcon("./���º�.png").getImage().getScaledInstance(256, 512, Image.SCALE_SMOOTH))));
			WinDialog.setVisible(true);
			WinDialog.setSize(400, 400);
			WinDialog.setLocationRelativeTo(null);
		} else {
			WinDialog.setTitle("��������");
		}

	}

	Offline(Main main) {

		new Timer(100, new TimerListener()).start();

		this.setPreferredSize(new Dimension(maxSize * 16 + 300, maxSize * 16 + 1));
		this.setBackground(Color.black);
		this.setLayout(null);
		int i;
		for (i = 0; i < 5; i++) {
			Cg[i] = new ImageIcon("./������/" + (i + 2) + ".png").getImage().getScaledInstance(maxSize, maxSize,
					Image.SCALE_DEFAULT);
		}
		addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {

				if(turn == Main.BLACK) {
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
						main.c.add(main.title); // Title�� �ٽ� ���ư���.
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
			// simply iterate through list and move all squares
			repaint(); // then repaint the GUI
		}
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		g.drawImage(changedImg, 1, 1, this);
		g.setFont(new Font("Arial", Font.ITALIC, 20));

		blackcount = whitecount = 0;
		g.setColor(Color.white);
		for (int i = 0; i <= maxXY; i++) {

			g.drawLine(0, maxSize * i, maxSize * maxXY, maxSize * i);
			g.drawLine(maxSize * i, 0, maxSize * i, maxSize * maxXY);
		}

		for (int i = 0; i < maxXY; i++)
			for (int j = 0; j < maxXY; j++) {
				if (map[j][i] == 1) {
					blackcount++;
					g.drawImage(BlackO, i * maxSize, j * maxSize, this);
				}
			}

		for (int i = 0; i < maxXY; i++)
			for (int j = 0; j < maxXY; j++) {
				if (map[j][i] == -1) {
					whitecount++;
					g.drawImage(WhiteO, i * maxSize, j * maxSize, this);

				}
			}
		for (int i = 0; i < maxXY; i++)
			for (int j = 0; j < maxXY; j++) {
				if (map[j][i] == 2) {
					for (int k = 4; k >= 0; k--) {
						g.drawImage(Cg[k], i * maxSize, j * maxSize, this);
					}

					map[j][i] = -1;
				} else if (map[j][i] == 4) {
					for (int k = 0; k < 5; k++) {
						g.drawImage(Cg[k], i * maxSize, j * maxSize, this);

					}

					map[j][i] = 1;
				}
			}

		if (turn == 1) {

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