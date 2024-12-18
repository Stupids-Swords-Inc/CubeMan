package com.tutorial.main;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;
// import javafx.application.*;
// import javafx.stage.Stage;
// import javafx.scene.Scene;
// import javafx.scene.Parent;
// import javafx.fxml.FXMLLoader;
// import javafx.fxml.FXML;


public class Window extends Canvas{


	private static final long serialVersionUID = -240840600533728354L;

	public Window(int width, int height, String title, Game game){
		JFrame frame = new JFrame(title);
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		game.start();
		
	}

	// @Override
	// public void start(Stage primaryStage) throws Exception {
	// 	int WIDTH = 940, HEIGHT = WIDTH / 12 * 9;
	// 	Scene scene = new Scene(loadFXML("CubeMan"), WIDTH, HEIGHT);
	// 	primaryStage.setScene(scene);
	// 	primaryStage.show();
	// }
}
