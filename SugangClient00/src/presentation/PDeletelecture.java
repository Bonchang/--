package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import valueObject.VUserInfo;

public class PDeletelecture {
    
    private List<JRadioButton> radioButtons;
    private ButtonGroup radioGroup;

    public void run(VUserInfo vUserInfo) throws IOException {
        JFrame frame = new JFrame("강의 취소");
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JLabel label = new JLabel("삭제할 강의를 선택하세요:");
        frame.add(label, BorderLayout.NORTH);

        // 라디오 버튼 리스트와 버튼 그룹 초기화
        radioButtons = new ArrayList<>();
        radioGroup = new ButtonGroup();

        // 라디오 버튼 패널
        JPanel radioPanel = new JPanel();
        radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.Y_AXIS));
        loadLecturesToRadioButtons(radioPanel, vUserInfo);
        frame.add(new JScrollPane(radioPanel), BorderLayout.CENTER);

        // 삭제 버튼
        JButton deleteButton = new JButton("삭제");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (radioGroup.getSelection() != null) {
                        deleteLecture(vUserInfo);
                        loadLecturesToRadioButtons(radioPanel, vUserInfo); // 강좌 목록 다시 불러오기
                    } else {
                        JOptionPane.showMessageDialog(frame, "강좌를 선택해주세요.");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        JPanel panel = new JPanel();
        panel.add(deleteButton);
        frame.add(panel, BorderLayout.SOUTH);
        
        frame.setLocationRelativeTo(null); // 창을 화면 중앙에 배치

        frame.setVisible(true);
    }

    private void loadLecturesToRadioButtons(JPanel radioPanel, VUserInfo vUserInfo) throws IOException {
        radioPanel.removeAll();
        radioButtons.clear();
        radioGroup.clearSelection();

        String filePath = "data/sincheong" + vUserInfo.getName() + ".txt";
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        int lineNumber = 1;
        while ((line = br.readLine()) != null) {
            JRadioButton radioButton = new JRadioButton(line);
            radioButton.setActionCommand(String.valueOf(lineNumber));
            radioButtons.add(radioButton);
            radioGroup.add(radioButton);
            radioPanel.add(radioButton);
            lineNumber++;
        }
        br.close();

        radioPanel.revalidate();
        radioPanel.repaint();
    }

    private void deleteLecture(VUserInfo vUserInfo) throws IOException {
        String filePath = "data/sincheong" + vUserInfo.getName() + ".txt";
        File file = new File(filePath);

        File tempFile = new File(filePath + ".tmp");
        BufferedReader br = new BufferedReader(new FileReader(file));
        BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));

        String selectedLine = radioGroup.getSelection().getActionCommand();
        int lineToRemove = Integer.parseInt(selectedLine);
        String line;
        int lineNumber = 1;
        while ((line = br.readLine()) != null) {
            if (lineNumber != lineToRemove) {
                bw.write(line + System.getProperty("line.separator"));
            }
            lineNumber++;
        }

        br.close();
        bw.close();

        file.delete();
        tempFile.renameTo(file);
    }
}
