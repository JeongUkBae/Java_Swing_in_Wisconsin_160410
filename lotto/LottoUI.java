package lotto;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class LottoUI extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	Lotto lotto;
	JButton btn;
	JPanel panelNorth, panelSouth;
	ImageIcon icon;
	List<JButton> btns;

	public LottoUI() {
		init();
	}

	public void init() {
		setTitle("로또 발생기");
		lotto = new Lotto();
		btns = new ArrayList<JButton>();
		panelNorth = new JPanel();
		panelSouth = new JPanel();
		btn = new JButton("로또 번호 추첨");
		/* 조립 */
		btn.addActionListener(this);
		panelNorth.add(btn);
		add(panelNorth, BorderLayout.NORTH);
		add(panelSouth, BorderLayout.SOUTH);
		setBounds(300, 400, 1200, 300);
		// 300, 400 은 x , y 좌표값
		// 1200, 300 은 픽셀로 크기
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (btns.size() == 0) {
			makeBtns();
		}
		lotto.setLotto();
		showLotto();

	}

	private void showLotto() {
		int[] arr = lotto.getLotto();
		for (int i = 0; i < arr.length; i++) {
			btns.get(i).setIcon(getIcon(arr[i]));
		}
	}

	private Icon getIcon(int i) {
		String imgPath = "src/image/" + Integer.toString(i) + ".gif";
		return new ImageIcon(imgPath);
	}

	private void makeBtns() {
		JButton tmp = null;
		for (int i = 0; i < 6; i++) {
			tmp = new JButton();
			btns.add(tmp);

			panelSouth.add(tmp);
		}

	}

}
