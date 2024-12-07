package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class PSigninFrame {
    private JFrame frame;
    private JTextField textFieldName;
    private JTextField textFieldID;
    private JPasswordField passwordField;

    public void run() {
        initializeGUI();
        frame.setVisible(true);
    }

    private void initializeGUI() {
        frame = new JFrame();
        frame.setBounds(100, 100, 400, 300);
        frame.getContentPane().setLayout(null);

        JLabel lblTitle = new JLabel("명지대학교 수강신청 회원가입");
        lblTitle.setFont(new Font("Serif", Font.BOLD, 16));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setBounds(50, 30, 300, 30);
        frame.getContentPane().add(lblTitle);

        JLabel lblName = new JLabel("이름:");
        lblName.setBounds(50, 80, 80, 20);
        frame.getContentPane().add(lblName);

        textFieldName = new JTextField();
        textFieldName.setBounds(140, 80, 150, 20);
        frame.getContentPane().add(textFieldName);
        textFieldName.setColumns(10);

        JLabel lblID = new JLabel("아이디:");
        lblID.setBounds(50, 120, 80, 20);
        frame.getContentPane().add(lblID);

        textFieldID = new JTextField();
        textFieldID.setBounds(140, 120, 150, 20);
        frame.getContentPane().add(textFieldID);
        textFieldID.setColumns(10);

        JLabel lblPassword = new JLabel("비밀번호:");
        lblPassword.setBounds(50, 160, 80, 20);
        frame.getContentPane().add(lblPassword);

        passwordField = new JPasswordField();
        passwordField.setBounds(140, 160, 150, 20);
        frame.getContentPane().add(passwordField);

        JButton btnSignup = new JButton("가입");
        btnSignup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = textFieldName.getText();
                String id = textFieldID.getText();
                String pw = new String(passwordField.getPassword());

                // 서버에 회원가입 정보 전송
                sendSignupRequest(name, id, pw);
            }
        });
        btnSignup.setBounds(100, 200, 200, 30);
        frame.getContentPane().add(btnSignup);
    }

    private void sendSignupRequest(String name, String id, String password) {
        try (Socket socket = new Socket("localhost", 1234);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            out.println("SIGNUP"); // 회원가입 요청임을 표시
            out.println(name);
            out.println(id);
            out.println(password);

            String response = in.readLine();
            JOptionPane.showMessageDialog(frame, response);

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "서버 연결 실패");
        }
    }
}