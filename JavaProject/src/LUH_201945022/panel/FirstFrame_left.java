package LUH_201945022.panel;

import java.awt.*;
import java.awt.event.*;

import GGB_201945025.Inquiry;
import GYH_202145024.SnackMenu;
import LUH_201945022.set.*;
import LUH_201945022.frame.*;
import LUH_201945022.panel.*;

public class FirstFrame_left extends PanelSet implements ActionListener {

///////////////////////////////////////////////////////////////
// 변수 선언
	private final int lblNum = 1;
	private final int btnNum = 3;
	
///////////////////////////////////////////////////////////////
// 생성자
	public FirstFrame_left(MainPanel main) {
		this.main = main;
		
		setBorder(wl);
		
		setBackground(purplemid);

		setLayout(gbl);
		setGbc();

		lbl = creLbl(lblNum);
		btn = creBtn(btnNum);
		
		gbc.weighty = 1;
		setBtn(btn[0], "영화 예매", 450, 180);
		gbc.insets = new Insets(20, 20, 10, 45);
		gbl.setConstraints(btn[0], gbc);

		setBtn(btn[1], "예매 조회", 450, 180);
		gbc.insets = new Insets(10, 20, 10, 45);
		gbc.gridy = 1;
		gbl.setConstraints(btn[1], gbc);

		setBtn(btn[2], "매점 구매", 450, 180);
		gbc.insets = new Insets(10, 20, 20, 45);
		gbc.gridy = 2;
		gbl.setConstraints(btn[2], gbc);

		for(int i = 0 ; i < btnNum ; i++) {
			add(btn[i]);
			btn[i].addActionListener(this);
		}
	}

///////////////////////////////////////////////////////////////
// 메서드

///////////////////////////////////////////////////////////////
// 리스너
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		setVisible(false);
		main.fr.setVisible(false);
		if(obj == btn[0]) {
			SelectMovie sm = new SelectMovie(main);
			sm.setPreferredSize(fullsize);
			main.add(sm);
		} else if(obj == btn[1]) {
			Inquiry iq = new Inquiry(main);
			iq.setPreferredSize(new Dimension(fullsize));
			main.add(iq);
			iq.getTf().requestFocus();
		} else if(obj == btn[2]) {
			SnackMenu s = new SnackMenu(main);
			s.setPreferredSize(fullsize);
			main.add(s);
			main.revalidate();
	    }
		revalidate();
	}

///////////////////////////////////////////////////////////////
}
