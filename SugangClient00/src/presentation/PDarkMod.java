package presentation;

import javax.swing.*;
import java.awt.*;

public class PDarkMod {

    private JFrame frame;
    private boolean isDarkMode; // 다크 모드 상태 추적

    // 다크 모드와 일반 모드의 색상 정의
    private final Color darkBackgroundColor = new Color(45, 45, 45); // 어두운 배경색
    private final Color darkForegroundColor = Color.WHITE; // 어두운 모드의 텍스트 색상

    private final Color lightBackgroundColor = Color.WHITE; // 밝은 배경색
    private final Color lightForegroundColor = Color.BLACK; // 밝은 모드의 텍스트 색상

    public PDarkMod(JFrame frame) {
        this.frame = frame;
        this.isDarkMode = false; // 초기 모드는 일반 모드로 설정
    }

    public void toggleMode() {
        isDarkMode = !isDarkMode; // 모드 전환
        if (isDarkMode) {
            // 다크 모드 적용
            updateComponentColors(frame.getContentPane(), darkBackgroundColor, darkForegroundColor);
        } else {
            // 일반 모드 적용
            updateComponentColors(frame.getContentPane(), lightBackgroundColor, lightForegroundColor);
        }
        frame.repaint(); // 프레임 갱신
    }

    private void updateComponentColors(Container container, Color bg, Color fg) {
        for (Component comp : container.getComponents()) {
            if (comp instanceof Container) {
                updateComponentColors((Container) comp, bg, fg);
            }
            if (comp instanceof JComponent) {
                comp.setBackground(bg);
                comp.setForeground(fg);
            }
        }
    }
}
