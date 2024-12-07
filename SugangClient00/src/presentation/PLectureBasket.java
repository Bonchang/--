package presentation;

import javax.swing.*;
import valueObject.VUserInfo;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PLectureBasket {

    public void run(VUserInfo vUserInfo) {
        JFrame frame = new JFrame("Lecture Basket");

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        try {
            // 파일 내용을 읽어와서 JTextArea에 표시
            String filePath = "data/sincheong" + vUserInfo.getName() + ".txt";
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                textArea.append(line + "\n");
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        int width = 400;  // 너비
        int height = 300;  // 높이
        frame.setSize(width, height);
        
        // 창을 화면 중앙에 배치
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }

}
