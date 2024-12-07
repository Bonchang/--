package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PBoard {

    private JFrame frame;
    private DefaultListModel<String> listModel;

    public PBoard() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("게시판");
        frame.setBounds(100, 100, 450, 300);

        listModel = new DefaultListModel<>();
        JList<String> list = new JList<>(listModel);
        frame.getContentPane().add(new JScrollPane(list), BorderLayout.CENTER);

        JPanel panel = new JPanel();
        JTextField textField = new JTextField(20);
        JButton addButton = new JButton("글 추가");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textField.getText();
                if (!text.isEmpty()) {
                    listModel.addElement(text);
                    textField.setText("");
                }
            }
        });

        panel.add(textField);
        panel.add(addButton);
        frame.getContentPane().add(panel, BorderLayout.SOUTH);
    }

    public void run() {
        frame.setVisible(true);
        frame.setLocationRelativeTo(null); // 창을 화면 중앙에 배치

    }
}
