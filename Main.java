package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Main extends Application {
//javafx buttons,textfields,hboxes,vboxes..
	@Override
	public void start(Stage primaryStage) {

		primaryStage.setTitle("My First Algorithm Project");
		Button Browse = new Button("Browse");
		Button Next = new Button("Next");
		TextArea output = new TextArea();
		output.setPrefWidth(300);
		output.setPrefHeight(200);
		HBox browsebox = new HBox(5);
		browsebox.getChildren().addAll(Browse);
		browsebox.setAlignment(Pos.TOP_CENTER);
		HBox areabox = new HBox(5);
		areabox.getChildren().addAll(output);
		areabox.setAlignment(Pos.CENTER);
		HBox nextbox = new HBox(5);
		nextbox.getChildren().addAll(Next);
		nextbox.setAlignment(Pos.BOTTOM_RIGHT);
		VBox vbox = new VBox(5);
		vbox.getChildren().addAll(browsebox, areabox, nextbox);

		TextField Numbers = new TextField();
		Numbers.setPromptText("Enter The number of power sources");
		Numbers.setFocusTraversable(false);
		Numbers.setPrefWidth(200);

		TextArea output2 = new TextArea();
		output2.setPrefWidth(300);
		output2.setPrefHeight(300);

		Button Check = new Button("Check");

		VBox numbers = new VBox();
		numbers.setAlignment(Pos.TOP_CENTER);
		numbers.getChildren().addAll(Numbers, Check);

		HBox areabox2 = new HBox();
		areabox2.setAlignment(Pos.TOP_CENTER);
		areabox2.getChildren().addAll(output2);
		areabox2.setVisible(false);

		VBox vb = new VBox(5);
		vb.getChildren().addAll(numbers, areabox2);
//buttons set on action
		Browse.setOnAction(e -> {

			FileChooser fileChooser = new FileChooser();

			File file = fileChooser.showOpenDialog(primaryStage);

			try {

				int numOfPWS;

				Scanner input = new Scanner(file);
				// takes the first line which represent the powersSources Number
				numOfPWS = input.nextInt();
//Array for leds
				int[] LEDs = new int[numOfPWS];

				output.setText("Number of Power Sources is  : ");
				String pwsNumbers = numOfPWS + " ";
				output.appendText(pwsNumbers);

				output.appendText("\n LED's Numbers are : ");
//to read the led's numbers from the file
				if (input.hasNextInt()) {
					for (int j = 0; j < LEDs.length; j++) {
						LEDs[j] = input.nextInt();
						String ledsNumbers = LEDs[j] + " ";
						output.appendText(ledsNumbers);

					}
					input.close();
					output.appendText("\n The Number Of The Leds That will Light : ");
					//to find the time
					long currentTime = System.currentTimeMillis();
					int t = LCS.LCSS(numOfPWS, LEDs);
					long elapsedTime = System.currentTimeMillis() - currentTime;

					String out = t + " \n" + "Time : " + elapsedTime;
					output.appendText(out);
				}

			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}

		});
		//check button which check the numOf powerSources and randomly leds to find lcs
		Check.setOnAction(e -> {
			areabox2.setVisible(true);

			String num = Numbers.getText();

			int[] arr = LCS.getRandomNumber(Integer.parseInt(num));
			output2.appendText("Randomly LED'S numbers are : \n");
			for (int i = 0; i < arr.length; i++) {
				output2.appendText(arr[i] + " ");
			}

			output2.appendText("\n Number of The leds that will light: \n");

			int numofpws = LCS.LCSS(Integer.parseInt(num), arr);
			String nums = numofpws + " ";
			output2.appendText(nums);

		});
		try {

			Scene scene = new Scene(vbox, 400, 400);
			Scene scene2 = new Scene(vb, 400, 400);
			Next.setOnAction(e -> {
				primaryStage.setScene(scene2);
			});

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		launch(args);
	}

}
