package presentation;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;

import javax.swing.*;

import valueObject.VUserInfo;

public class PLoginFrame extends JFrame {
    private JFrame frame;
    private JTextField idField;
    private JPasswordField passwordField;

    public PLoginFrame() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("로그인");
        frame.setBounds(100, 100, 300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("아이디:");
        lblNewLabel.setBounds(50, 40, 80, 15);
        frame.getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("비밀번호:");
        lblNewLabel_1.setBounds(50, 80, 80, 15);
        frame.getContentPane().add(lblNewLabel_1);

        idField = new JTextField();
        idField.setBounds(130, 37, 116, 21);
        frame.getContentPane().add(idField);
        idField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setBounds(130, 77, 116, 21);
        frame.getContentPane().add(passwordField);

        JButton btnLogin = new JButton("로그인");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String password = new String(passwordField.getPassword());

                try (Socket socket = new Socket("localhost", 1234);
                     PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                    out.println("LOGIN"); // 로그인 요청임을 표시
                    out.println(id);
                    out.println(password);

                    String userName = in.readLine();
                    if (!userName.isEmpty()) {
                        VUserInfo vUserInfo = new VUserInfo();
                        vUserInfo.setName(userName);

                        JOptionPane.showMessageDialog(null, "로그인 성공! 환영합니다, " + userName + "님");

                        PMenuFrame pMenuFrame = new PMenuFrame();
                        pMenuFrame.run(vUserInfo);
                    } else {
                        JOptionPane.showMessageDialog(null, "로그인 실패. 아이디와 비밀번호를 확인하세요.");
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "서버 연결에 실패했습니다.");
                }
            }
        });
        btnLogin.setBounds(50, 120, 100, 30);
        frame.getContentPane().add(btnLogin);

        JButton btnSignup = new JButton("회원가입");
        btnSignup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PSigninFrame signin = new PSigninFrame();
                signin.run();
            }
        });
        btnSignup.setBounds(150, 120, 100, 30);
        frame.getContentPane().add(btnSignup);
    }

    public void run() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null); // 창을 화면 중앙에 배치
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
