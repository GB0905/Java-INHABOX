package LUH_201945022.panel;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import LUH_201945022.set.*;
import LUH_201945022.frame.*;

public class FirstFrame_right extends PanelSet implements ActionListener {

///////////////////////////////////////////////////////////////
// 변수 선언
	private int imgSize;
	private final int lblNum = 1;
	private final int btnNum = 2;
	private ImageIcon[] poster;
	
	private int curLoc;

///////////////////////////////////////////////////////////////
// 생성자
	public FirstFrame_right(MainPanel main) {
		setDefault(main);
		imgSize = screenWidth / 3;
		
		setImage(imgSize);

		setLayout(gbl);
		setGbc();

		setBackground(purpledark);

		lbl = creLbl(lblNum);
		btn = creBtn(btnNum);

		gbc.fill = GridBagConstraints.NONE;
		setBtn(btn[0], "이전", 70, 70);
		gbc.insets = new Insets(90, 45, 90, 10);
		gbl.setConstraints(btn[0], gbc);

		setBtn(btn[1], "다음", 70, 70);
		gbc.insets = new Insets(90, 10, 90, 45);
		gbc.gridx = 2;
		gbl.setConstraints(btn[1], gbc);

		lbl[0].setIcon(poster[0]);
		lbl[0].setHorizontalAlignment(SwingConstants.CENTER);
		lbl[0].setOpaque(true);
		lbl[0].setBackground(Color.white);
		gbc.insets = new Insets(90, 0, 90, 0);
		gbc.gridx = 1;
		gbc.weightx = 1;
		gbl.setConstraints(lbl[0], gbc);

		for (int i1 = 0; i1 < btnNum; i1++) {
			add(btn[i1]);
			btn[i1].addActionListener(this);
		}

		add(lbl[0]);
	}

///////////////////////////////////////////////////////////////
// 메서드
	protected void setImage(double baseSize) {
		poster = new ImageIcon[movCnt];
		int i = 0;
		for (String loc : movPath) {
			poster[i] = new ImageIcon(loc);
			poster[i] = imageSetSize(poster[i], baseSize, baseSize * 1.42);
			i++;
		}
	}
	
///////////////////////////////////////////////////////////////
// 리스너
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == btn[0]) {
			if (curLoc == 0) {
				curLoc = poster.length - 1;
				lbl[0].setIcon(poster[curLoc]);
			} else {
				lbl[0].setIcon(poster[--curLoc]);
			}
		} else if (obj == btn[1]) {
			if (curLoc == poster.length - 1) {
				curLoc = 0;
				lbl[0].setIcon(poster[0]);
			} else {
				lbl[0].setIcon(poster[++curLoc]);
			}
		}
	}
	
///////////////////////////////////////////////////////////////
}
