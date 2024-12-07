package admin;
import javax.swing.JFrame;

public class LStart {
	
	private LMainFrame mainFrame;
	
	public LStart() {
		this.mainFrame = new LMainFrame();
	}
	
	public void initialize() {
		this.mainFrame.setVisible(true);
	}

}