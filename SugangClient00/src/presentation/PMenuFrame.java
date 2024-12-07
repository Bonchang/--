package presentation;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import valueObject.VUserInfo;

public class PMenuFrame {

	public void run(VUserInfo vUserInfo) {
		JFrame frame = new JFrame("수강 신청");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// "LMS" 문구를 표시하는 레이블 추가
		JLabel lmsLabel = new JLabel("<html>명지대학교 LMS<br><br></html>", JLabel.CENTER);
		lmsLabel.setFont(new Font("Serif", Font.BOLD, 24));

		// 사용자 정보 레이블 추가
		JLabel infoLabel = new JLabel("안녕하세요! " + vUserInfo.getName() + " 님", JLabel.CENTER);

		// 두 레이블을 포함할 새 패널 생성
		JPanel labelPanel = new JPanel();
		labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
		labelPanel.add(lmsLabel);
		labelPanel.add(infoLabel);

		frame.getContentPane().add(labelPanel, BorderLayout.NORTH);

		// 버튼 생성 및 이벤트 리스너 추가
		JButton sugangButton = new JButton(" 수강 신청 ");
		sugangButton.addActionListener(e -> {
			PLectureSelection lectureSelection = new PLectureSelection(vUserInfo);
			lectureSelection.run();
		});

		JButton confirmButton = new JButton(" 신청 확인 ");
		confirmButton.addActionListener(e -> {
			PLectureBasket plecturebasket = new PLectureBasket();
			plecturebasket.run(vUserInfo);
		});

		JButton deletelecturebutton = new JButton(" 수강신청 취소 ");
		deletelecturebutton.addActionListener(e -> {
			PDeletelecture pDeletelecture = new PDeletelecture();
			try {
				pDeletelecture.run(vUserInfo);
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		});

		JButton miridangiButton = new JButton(" 미리 담기 ");
		miridangiButton.addActionListener(e -> {
			PMiridamgiSelection miridamgiSelection = new PMiridamgiSelection(vUserInfo);
			miridamgiSelection.run();
		});

		JButton miriconfirmButton = new JButton("미리담기 확인");
		miriconfirmButton.addActionListener(e -> {
			PMiridamgiBasket pmiribasket = new PMiridamgiBasket();
			pmiribasket.run(vUserInfo);
		});

		JButton deletemiridamgibutton = new JButton(" 미리담기 취소 ");
		deletemiridamgibutton.addActionListener(e -> {
			PDeletemiridamgi pDeletemiridamgi = new PDeletemiridamgi();
			try {
				pDeletemiridamgi.run(vUserInfo);
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		});
		JButton chatRoomButton = new JButton("문의 하기");
		chatRoomButton.addActionListener(e -> {
			PBoard board = new PBoard();
			board.run();
		});
		JButton darkModeButton = new JButton("다크 모드");
		PDarkMod darkMod = new PDarkMod(frame);

		darkModeButton.addActionListener(e -> {
		    darkMod.toggleMode();
		});


		// 버튼을 포함하는 패널을 GridLayout으로 설정
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(2, 3, 10, 10));
		buttonPanel.add(sugangButton);
		buttonPanel.add(confirmButton);
		buttonPanel.add(deletelecturebutton);
		buttonPanel.add(miridangiButton);
		buttonPanel.add(miriconfirmButton);
		buttonPanel.add(deletemiridamgibutton);
		buttonPanel.add(chatRoomButton);
		buttonPanel.add(darkModeButton);

		frame.getContentPane().add(buttonPanel, BorderLayout.CENTER);

		JButton smallButton = new JButton();
		smallButton.setPreferredSize(new Dimension(20, 20)); // 작은 정사각형 크기로 설정
		smallButton.addActionListener(e -> {
			Adminmod adminmod = new Adminmod();
			adminmod.run();
		});

		// 버튼을 오른쪽에 정렬하기 위한 패널
		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		bottomPanel.add(smallButton);

		// 프레임의 하단에 패널 추가
		frame.getContentPane().add(bottomPanel, BorderLayout.SOUTH);

		frame.setSize(600, 400); // 너비와 높이 설정
		frame.setLocationRelativeTo(null); // 창을 화면 중앙에 배치
		frame.setVisible(true);
	}

}
