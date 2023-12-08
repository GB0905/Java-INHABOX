package GYH_202145024;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import LUH_201945022.frame.*;

public class SnackMenu extends JPanel implements ActionListener, MouseListener {

	// DB관련 변수
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	private String Driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://118.46.199.58:3306/movie";

	private JButton PayMent;
	private JButton ReSet;
	private String header[] = { "메뉴", "계수", "가격" };
	private DefaultTableModel stmodel = new DefaultTableModel(header, 0);
	private JTable tb = new JTable(stmodel);
	private JScrollPane ScrollPane = new JScrollPane(tb);
	private JPanel Main;
	private JLabel imglabel1;
	private JLabel imglabel2;
	private JLabel imglabel3;
	private JButton PopcornBtn;
	private JButton drinkBtn;
	private JButton SideBtn;
	private Detail Dt;
	private String title;
	private PaymentMethod PM;
	private int OrderId;
	private PopupMenu pm;
	private MenuItem pm_item1;
	private MenuItem pm_item2;
	private int TbIndex;
	private JPanel popcorn;
	private JPanel beverage;
	private JPanel etc;
	private MainPanel main;

	public DefaultTableModel getStmodel() {
		return stmodel;
	}

	public SnackMenu(MainPanel main) {
		this.main = main;
		main.s = this;

		pm = new PopupMenu();
		pm_item1 = new MenuItem("전체삭제");
		pm_item1.addActionListener(this);
		pm_item2 = new MenuItem("삭제하기");
		pm_item2.addActionListener(this);

		pm.add(pm_item1);
		pm.addSeparator();
		pm.add(pm_item2);

		tb.addMouseListener(this);
		tb.setRowHeight(40);
		tb.add(pm);

		setSize(1440, 810);

		setLayout(new BorderLayout());

		MenuList();
		ControlBox();
	}

	private void ControlBox() {
		Main = new JPanel();
		Main.setLayout(new GridLayout(0, 1, 10, 0));
		Main.setPreferredSize(new Dimension(300, 500)); // 가로,세로 사이즈 제정의

		JPanel Sub = new JPanel();
		Sub.setBorder(BorderFactory.createEmptyBorder(150, 0, 0, 0));
		Sub.setBackground(new Color(99, 69, 196));

		ImageIcon I_Money = new ImageIcon("images/snack/money.png");
		Image I_Money_img = I_Money.getImage();
		Image New_I_Money_img = I_Money_img.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
		ImageIcon newMoneyIcon = new ImageIcon(New_I_Money_img);

		PayMent = new JButton("결제하기", newMoneyIcon);
		PayMent.addActionListener(this);
		PayMent.setPreferredSize(new Dimension(270, 60));

		ImageIcon I_ReSet = new ImageIcon("images/snack/bin.png");
		Image I_ReSet_img = I_ReSet.getImage();
		Image New_I_ReSet_img = I_ReSet_img.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
		ImageIcon newReSetIcon = new ImageIcon(New_I_ReSet_img);

		ReSet = new JButton("휴지통", newReSetIcon);
		ReSet.addActionListener(this);
		ReSet.setPreferredSize(new Dimension(270, 60));

		Sub.add(PayMent);
		Sub.add(ReSet);

		Main.add(ScrollPane);
		Main.add(Sub);
		add(Main, BorderLayout.EAST);
	}

	private void MenuList() {

		JPanel MenuList = new JPanel();
		MenuList.setPreferredSize(new Dimension(700, 500));
		MenuList.setBorder(BorderFactory.createEmptyBorder(100, 60, 100, 60));
		MenuList.setLayout(new GridLayout(0, 3, 0, 0));
		MenuList.setBackground(new Color(75, 52, 149));

		Font font = new Font("맑은고딕", Font.BOLD, 20);

		popcorn = new JPanel();
		popcorn.addMouseListener(this);
		popcorn.setBorder(BorderFactory.createEmptyBorder(130, 0, 0, 0));
		popcorn.setBackground(Color.WHITE);

		ImageIcon ic1 = new ImageIcon("images/snack/일반팝콘.jpeg");
		Image img1 = ic1.getImage();
		Image newimg1 = img1.getScaledInstance(195, 280, java.awt.Image.SCALE_SMOOTH);
		ImageIcon newic1 = new ImageIcon(newimg1);
		imglabel1 = new JLabel(newic1);

		ImageIcon I_popcorn = new ImageIcon("images/snack/popcorn.png");
		Image I_Popcorn_img = I_popcorn.getImage();
		Image New_I_Popcorn_img = I_Popcorn_img.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
		ImageIcon newPopcornIcon = new ImageIcon(New_I_Popcorn_img);

		PopcornBtn = new JButton("팝콘", newPopcornIcon);
		PopcornBtn.setFont(font);
		PopcornBtn.setPreferredSize(new Dimension(200, 60));
		PopcornBtn.addActionListener(this);

		popcorn.add(imglabel1);
		popcorn.add(PopcornBtn);

		beverage = new JPanel();
		beverage.addMouseListener(this);
		beverage.setBorder(BorderFactory.createEmptyBorder(130, 0, 0, 0));
		beverage.setBackground(Color.WHITE);

		ImageIcon ic2 = new ImageIcon("images/snack/칠성사이다.jpeg");
		Image img2 = ic2.getImage();
		Image newimg2 = img2.getScaledInstance(195, 280, java.awt.Image.SCALE_SMOOTH);
		ImageIcon newic2 = new ImageIcon(newimg2);
		imglabel2 = new JLabel(newic2);

		ImageIcon I_beverage = new ImageIcon("images/snack/soda.png");
		Image I_Beverage_img = I_beverage.getImage();
		Image New_I_Beverage_img = I_Beverage_img.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
		ImageIcon newBeverageIcon = new ImageIcon(New_I_Beverage_img);

		drinkBtn = new JButton("음료수", newBeverageIcon);
		drinkBtn.setFont(font);
		drinkBtn.setPreferredSize(new Dimension(200, 60));
		drinkBtn.addActionListener(this);

		beverage.add(imglabel2);
		beverage.add(drinkBtn);

		etc = new JPanel();
		etc.addMouseListener(this);
		etc.setBorder(BorderFactory.createEmptyBorder(130, 0, 0, 0));
		etc.setBackground(Color.WHITE);

		ImageIcon ic3 = new ImageIcon("images/snack/나쵸.jpeg");
		Image img3 = ic3.getImage();
		Image newimg3 = img3.getScaledInstance(195, 280, java.awt.Image.SCALE_SMOOTH);
		ImageIcon newic3 = new ImageIcon(newimg3);
		imglabel3 = new JLabel(newic3);

		ImageIcon I_ect = new ImageIcon("images/snack/deepFryer.png");
		Image I_Ect_img = I_ect.getImage();
		Image New_I_Ect_img = I_Ect_img.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
		ImageIcon newEctIcon = new ImageIcon(New_I_Ect_img);

		SideBtn = new JButton("사이드", newEctIcon);
		SideBtn.setFont(font);
		SideBtn.setPreferredSize(new Dimension(200, 60));
		SideBtn.addActionListener(this);

		etc.add(imglabel3);
		etc.add(SideBtn);

		MenuList.add(popcorn);
		MenuList.add(beverage);
		MenuList.add(etc);

		add(MenuList, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == PopcornBtn) { // 팝콘 버튼
			title = PopcornBtn.getText();
			if (Dt == null) {
				Dt = new Detail(this, title);
			} else {
				Dt.dispose();
				Dt = new Detail(this, title);
			}
		} else if (obj == drinkBtn) { // 음료 버튼
			title = drinkBtn.getText();
			if (Dt == null) {
				Dt = new Detail(this, title);
			} else {
				Dt.dispose();
				Dt = new Detail(this, title);
			}
		} else if (obj == SideBtn) { // 기타 버튼
			title = SideBtn.getText();
			if (Dt == null) {
				Dt = new Detail(this, title);
			} else {
				Dt.dispose();
				Dt = new Detail(this, title);
			}
		} else if (obj == PayMent) { // 결제하기 버튼

			Calendar today = Calendar.getInstance();
			int month = today.get(Calendar.MONDAY);
			int date = today.get(Calendar.DATE);
			Random random = new Random();
			String now = String.valueOf((month + 1)) + String.valueOf(date);
			OrderId = Integer.parseInt(now + String.valueOf(random.nextInt(10000)));

			int row = stmodel.getRowCount();
			int col = stmodel.getColumnCount();

			if (row == 0) {
				JOptionPane.showMessageDialog(this, "리스트에 메뉴가 없습니다.", "알림", JOptionPane.OK_OPTION);
				return;
			} else {

				if (PM == null) {
					PM = new PaymentMethod(this);
				} else {
					PM.dispose();
					PM = new PaymentMethod(this);
				}
				try {
					String sql = null;
					Class.forName(Driver);
					conn = DriverManager.getConnection(url, "javapj", "1234");
					stmt = conn.createStatement();

					for (int i = 0; i < row; i++) {
						String Name = stmodel.getValueAt(i, 0).toString();
						String Num = stmodel.getValueAt(i, 1).toString();
						String TotalPrice = stmodel.getValueAt(i, 2).toString();

						sql = "INSERT INTO snorder(snname, sncount,snprice,smcheck) values('" + Name + "','" + Num
								+ "','" + TotalPrice + "','" + OrderId + "')";
						stmt.executeUpdate(sql);

					}
				} catch (Exception e1) {
					e1.printStackTrace();
				} finally {
					try {
//							rs.close();
						stmt.close();
						conn.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}

		} else if (obj == ReSet) { // 초기화버튼

			if (stmodel.getRowCount() == 0) {
				JOptionPane.showMessageDialog(this, "리스트가 비어있습니다.", "초기화", JOptionPane.OK_OPTION);
			} else {
				stmodel.setNumRows(0);
				JOptionPane.showMessageDialog(this, "초기화 되었습니다.", "알림", JOptionPane.OK_OPTION);
			}
		} else if (obj == pm_item1) {
			stmodel.setRowCount(0);
		} else if (obj == pm_item2) {
			stmodel.removeRow(TbIndex);
		}

	}

	public int getOrderId() {
		return OrderId;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (SwingUtilities.isRightMouseButton(e) && e.getClickCount() == 1) {
			pm.show(tb, e.getX(), e.getY());
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		int rowAtPoint = tb.rowAtPoint(e.getPoint());
		TbIndex = rowAtPoint;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		Object obj = e.getSource();

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
