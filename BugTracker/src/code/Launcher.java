package code;
import java.awt.EventQueue;

public class Launcher {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginUI ui = new LoginUI(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
