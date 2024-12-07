package admin;
import javax.swing.JMenuBar;

public class LMenuBar extends JMenuBar {
	private static final long serialVersionUID = 1L;
	private LFileMenu fileMenu;
	private LEditMenu editMenu;
	
	public LMenuBar () {
		this.fileMenu = new LFileMenu();
		this.add(this.fileMenu);
		
		this.editMenu = new LEditMenu();
		this.add(this.editMenu);
	}
}
