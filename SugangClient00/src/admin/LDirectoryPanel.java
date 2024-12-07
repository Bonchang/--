package admin;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.BorderLayout;
import java.io.File;

public class LDirectoryPanel extends JPanel {
    private static final long serialVersionUID = 1L;
	private JTree directoryTree;

    public LDirectoryPanel() {
        this.setLayout(new BorderLayout());

        // 현재 디렉토리를 기준으로 트리 노드 생성
        File currentDirectory = new File("."); // 현재 디렉토리 (".")
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(currentDirectory.getName());
        createNodes(root, currentDirectory); // 하위 디렉토리와 파일들을 트리에 추가

        // 트리 모델 생성 및 설정
        directoryTree = new JTree(root);
        this.add(directoryTree, BorderLayout.CENTER);
    }

    private void createNodes(DefaultMutableTreeNode node, File file) {
        if (file.isDirectory()) {
            // 디렉토리인 경우, 하위 파일과 디렉토리를 탐색
            File[] files = file.listFiles();
            if (files != null) {
                for (File child : files) {
                    DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(child.getName());
                    node.add(childNode);
                    createNodes(childNode, child); // 재귀적으로 하위 노드 추가
                }
            }
        }
    }
}
