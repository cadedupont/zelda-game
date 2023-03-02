// Name: Cade DuPont
// Date: 02.15.23
// Description: Main class for Zelda game

import javax.swing.JFrame;
import java.awt.Toolkit;

public class Game extends JFrame {
	Controller controller;
	Model model;
	View view;

	public Game() {
		model = new Model();
		controller = new Controller(model);
		view = new View(controller, model);

		view.addMouseListener(controller);
		this.addKeyListener(controller);

		this.setTitle("A3 - Map Editor");
		// size of window to perfectly fit tiles differs based on operating system (top padding with title / exit button, aspect ratio?)
		this.setSize(700, 525); // Mac
		// this.setSize(716, 539); // Windows
		// this.setSize(700, 500); // default
		this.setFocusable(true);
		this.getContentPane().add(view);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
	}

	public void run() {
		while (true) {
			controller.update(); // calling update for smooth scrolling with arrow keys
			view.repaint(); // indirectly calling View.paintComponent
			Toolkit.getDefaultToolkit().sync(); // update screen
			
			// sleep for 40 milliseconds (25 FPS)
			try {
				Thread.sleep(16);
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
	}

	public static void main(String[] args) {
		new Game().run();
	}
}