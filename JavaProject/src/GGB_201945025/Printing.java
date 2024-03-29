package GGB_201945025;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import LUH_201945022.frame.*;

public class Printing extends JFrame implements ActionListener, WindowListener {

	private JLabel text;
	private Container c;
	private MainPanel main;
	private MainFrame fs;
	private JButton closeBtn;
	private File base = new File(".Ticket/ticketInfo.txt");
	private String[] splitArray; 	// i 0번 티켓 번호
									// i 1번 좌석
									// i 2번 성인 청소년 구성
									// i 3번 날짜
									// i 4번 시간
									// i 5번 상영관
									// i 6번 제목
									// i 7번 관람가
									// i 8번 장르
	private ArrayList<String> i;
	
	public Printing(MainPanel main, ArrayList<String> i) {
		this.i = i;
		this.main = main;
		this.fs = main.getFs();

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(300, 80);
		setResizable(false);
		setUndecorated(true);
		setLocationRelativeTo(fs);
		setLayout(new FlowLayout());
		c = getContentPane();

		text = new JLabel();
		Font font1 = new Font("맑은고딕", Font.ITALIC, 20);
		text.setFont(font1);
		text.setHorizontalAlignment(JLabel.CENTER);
		c.add(text);

		closeBtn = new JButton("확인");
		closeBtn.addActionListener(this);

		Timer timer = new Timer();
		TimerTask task = new TimerTask() {

			private int count = 0;

			@Override
			public void run() {
				closeBtn.setPreferredSize(new Dimension(100, 30));
				text.setPreferredSize(new Dimension(300, 30));
				count++;
				if (count == 1) {
					text.setText("출력 중");
				} else if (count == 2) {
					text.setText("출력 중.");
				} else if (count == 3) {
					text.setText("출력 중..");
				} else if (count == 4) {
					text.setText("출력 중...");
				} else if (count > 4) {
					File ticket = new File(".Ticket/" + i.get(0) + ".txt");
					FileReader fr;
					FileWriter fw;
					try {
						fr = new FileReader(base);
						BufferedReader br = new BufferedReader(fr);

						String line = "";
						String line1 = "";

						while ((line1 = br.readLine()) != null) {
							line = line + line1 + "\n";
						}

						splitArray = line.split("<a");

						splitArray[0] = splitArray[0] + i.get(6);
						splitArray[1] = splitArray[1] + i.get(7) + "세 이용가 / " + i.get(8);
						splitArray[2] = splitArray[2] + i.get(3) + " / " + i.get(4);
						splitArray[3] = splitArray[3] + i.get(5) + "관 / 좌석 : " + i.get(1);
						splitArray[4] = splitArray[4] + i.get(2);
						splitArray[5] = splitArray[5] + i.get(0);

						fw = new FileWriter(ticket);
						BufferedWriter bw = new BufferedWriter(fw);
						bw.write(splitArray[0]); // 버퍼에 있는 값 출력
						bw.write(splitArray[1]);
						bw.write(splitArray[2]);
						bw.write(splitArray[3]);
						bw.write(splitArray[4]);
						bw.write(splitArray[5]);
						bw.write(splitArray[6]);
						bw.flush(); // 남아있는 데이터를 모두 출력시킴
						bw.close(); // 스트림을 닫음

					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					text.setText("티켓이 출력되었습니다.");
					add(closeBtn);
					timer.cancel(); // 타이머 종료
					revalidate();
				}
			}
		};
		timer.schedule(task, 0, 1000);
		addWindowListener(this);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.dispose();
		fs = main.getFs();
		fs.c.remove(fs.mainPanel);

		MainPanel a = new MainPanel(fs);
		fs.mainPanel = a;
		fs.c.add(fs.mainPanel);

		fs.setVisible(true);

		revalidate();
	}

	@Override
	public void windowOpened(WindowEvent e) {
		fs.setEnabled(false);
	}

	@Override
	public void windowClosed(WindowEvent e) {
		fs.setEnabled(true);
	}

	@Override
	public void windowClosing(WindowEvent e) {
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}
}