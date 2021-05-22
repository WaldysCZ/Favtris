package game;

import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameScreen extends Application {
	
	public static boolean isPaused=false;
	private boolean gameMusicPlaying = false;
	private static MediaPlayer mediaPlayer;
	private Stage primaryStage;
	private MainMenu menu;
	private Options options;
	private final String BUTTON_IDLE = new String(
			"-fx-border-color: transparent;" + 
			"-fx-border-width: 0;" + 
			"-fx-background-radius: 0;" + 
			"-fx-background-color: transparent;" + 
			"-fx-font-family: Comic Sans;" + 
			"-fx-font-size: 30;" + 
			"-fx-font-weight: bold;");
	private final String BUTTON_HOVER = new String(
			"-fx-border-color: transparent, black;" + 
			"-fx-border-width: 0;" +
			"-fx-border-style: solid, segments(1, 2);" +
			"-fx-background-radius: 0;" + 
			"-fx-background-color: transparent;" + 
			"-fx-font-family: Comic Sans;" + 
			"-fx-font-size: 30;" + 
			"-fx-font-weight: bold;" +
			"-fx-text-fill: #e01709;");
	private final String BUTTON_PRESSED = new String(
			"-fx-border-color: transparent, black;" + 
			"-fx-border-width: 0;" +
			"-fx-border-style: solid, segments(1, 2);" +
			"-fx-background-radius: 0;" + 
			"-fx-background-color: transparent;" + 
			"-fx-font-family: Comic Sans;" + 
			"-fx-font-size: 30;" + 
			"-fx-font-weight: bold;" +
			"-fx-text-fill: #ff1100;");
	private int SIZE = 750;
	
	private final BackgroundImage B_IMG_D = new BackgroundImage(new Image("file:bgGameD.jpg",
			false),
	        BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
	        BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
	
	private final BackgroundImage B_IMG_L = new BackgroundImage(new Image("file:bgGameL.jpg",
			SIZE,SIZE, false, true),
	        BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
	        BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
	private String themeString = "-fx-text-fill: #e8e8e8;";
	
	private Scene gameScene;
	
	private Tetris game;
	

	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.menu = new MainMenu();	
		options=MainMenu.options;
		game = new Tetris();
		
		music();
		
		if (options.getPlayMusic()==true) {
			mediaPlayer.play();
		}
		else mediaPlayer.stop();
		
		BorderPane gamePane = new BorderPane();
		
		if (options.isDarkTheme()) {	
			gamePane.setBackground(new Background(B_IMG_D));
			themeString = "-fx-text-fill: #c9c9c9;";
		}
		else {
			gamePane.setBackground(new Background(B_IMG_L));
			themeString = "-fx-text-fill: #424242;";
		}
		gamePane.setLeft(makeLeft());
		gamePane.setCenter(makeRight());
		
		
		gameScene = new Scene(gamePane,750,750);
		
		
		this.primaryStage.setScene(gameScene);
		this.primaryStage.setTitle("Tetris Game - Gameplay");
		this.primaryStage.setHeight(gameScene.getHeight());
		this.primaryStage.setWidth(gameScene.getWidth());
		this.primaryStage.setResizable(false);
//		this.primaryStage.setMinHeight(gameScene.getHeight());
//		this.primaryStage.setMinWidth(gameScene.getWidth());
//		this.primaryStage.setMaxHeight(gameScene.getHeight()+150);
//		this.primaryStage.setMaxWidth(gameScene.getWidth()+150);
		this.primaryStage.show();
		
		
		//--------------------
		//	Key Presses
		//--------------------		
		
		gameScene.setOnKeyPressed(e -> {
			if (!isPaused) {
			    if (e.getCode() == KeyCode.A || e.getCode() == KeyCode.LEFT) {
			    		Controller.MoveLeft(Tetris.a);			    		
			    }
			    if (e.getCode() == KeyCode.D  || e.getCode() == KeyCode.RIGHT) {
			        Controller.MoveRight(Tetris.a);
			    }
			    if (e.getCode() == KeyCode.S   || e.getCode() == KeyCode.DOWN) {
			    	if (Tetris.top<1) {
			    		game.MoveDown(Tetris.a);
			    		if (options.isHardMode()) {
			    			game.score +=15;
			    		}
			    		else game.score += 5;
			    	}
			    }
			    if (e.getCode() == KeyCode.W   || e.getCode() == KeyCode.UP) {
			        game.MoveTurn(Tetris.a);
			    } 
		    }
		    if (e.getCode() == KeyCode.P) {
	    		if (isPaused) {
	    			isPaused=false;
	    		} else isPaused=true;
		    }
		    if (e.getCode() == KeyCode.X) {
				try {
					isPaused=true;
					leave(2);
				} catch (Exception e1) {
					System.out.println("Next window could not be loaded");
					e1.printStackTrace();
				}
		    } 
		    if (e.getCode() == KeyCode.M) {
				try {
					isPaused=true;
					leave(1);
					
				} catch (Exception e2) {
					System.out.println("Next window could not be loaded");
					e2.printStackTrace();
				}
			} 
		});
	
	}
	
	
	private Node makeControls() {
		
		Color themeColor;
		if (options.isDarkTheme()) {
			themeColor = Color.web("#c9c9c9");
		}
		else themeColor = Color.web("#424242");
		
		Text controlT = new Text("Menu controls");
		controlT.setStroke(themeColor);
		Text controls = new Text(" P - Pause \n M - Menu \n X - Exit");
		controls.setStroke(themeColor);
		
		Text gameplayT = new Text("Game controls");
		gameplayT.setStroke(themeColor);
		Text gameConT = new Text(" A - Move left \n D - Move right \n S - Move down" +
				"\n W - Rotate");
		gameConT.setStroke(themeColor);
				
		VBox gameC = new VBox(gameplayT,gameConT);
		VBox menuC = new VBox(controlT,controls);
		
		HBox helpPane = new HBox(gameC,menuC);
		helpPane.setSpacing(50);
		helpPane.setAlignment(Pos.CENTER);
		
		return helpPane;
	}


	private Node makeRight() {
		
		//-------------------
		//    Score
		//-------------------
		
		Text scoreL = new Text("SCORE:");
		scoreL.setFont(Font.font("Open Sans", FontWeight.BOLD, 30));
		scoreL.setStyle(themeString);
		if (options.isDarkTheme()) {
			scoreL.setFill(Color.DARKGRAY);			
		}
		Text scoreValue = new Text();
		scoreValue.setFont(Font.font("Open Sans", FontWeight.BOLD, 30));
		scoreValue.setStyle(themeString);
		if (options.isDarkTheme()) {
			scoreValue.setFill(Color.DARKGRAY);			
		}
		scoreValue.textProperty().bind(game.scoreProperty.asString());
		
		HBox score = new HBox(scoreL,scoreValue);
		score.setSpacing(10);
		score.setPadding(new Insets(20));
		score.setAlignment(Pos.CENTER);
		
		
		//-------------------
		// Next brick Screen
		//-------------------

		GridPane pane = new GridPane();
		resetPane(pane);
		drawNextBrick(game.nextObj.getName(),pane);
		
		VBox nextBrick = new VBox(pane);
		nextBrick.setStyle("-fx-padding: 2;" + "-fx-border-style: solid inside;"
		        + "-fx-border-width: 5;" + "-fx-border-insets: 0;"
		        + "-fx-border-radius: 0;" + "-fx-border-color: black;"
		        + "-fx-background-color: #707070");
//		nextBrick.setFillWidth(false);
		nextBrick.setMaxWidth(200);
		
		game.formNameProperty.addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(game.game) {
					resetPane(pane);
					drawNextBrick(newValue,pane);
				}
			}
		});	
		
		//-------------------
		//    Buttons
		//-------------------
		
		Button pauseB = new Button("PAUSE");
		Button menuB = new Button("MENU");
		Button exitB = new Button("EXIT");
		
		VBox buttons = new VBox(pauseB,menuB,exitB);
		buttons.setAlignment(Pos.CENTER);
		buttons.setSpacing(10);
		buttons.setPadding(new Insets(20));
		
		pauseB.setStyle(BUTTON_IDLE + themeString);
		pauseB.setOnMouseEntered(e -> pauseB.setStyle(BUTTON_HOVER));
		pauseB.setOnMouseExited(e -> pauseB.setStyle(BUTTON_IDLE + themeString));
		pauseB.setOnMousePressed(e -> pauseB.setStyle(BUTTON_PRESSED));
		
		menuB.setStyle(BUTTON_IDLE + themeString);
		menuB.setOnMouseEntered(e -> menuB.setStyle(BUTTON_HOVER));
		menuB.setOnMouseExited(e -> menuB.setStyle(BUTTON_IDLE + themeString));
		menuB.setOnMousePressed(e -> menuB.setStyle(BUTTON_PRESSED));
		
		exitB.setStyle(BUTTON_IDLE + themeString);
		exitB.setOnMouseEntered(e -> exitB.setStyle(BUTTON_HOVER));
		exitB.setOnMouseExited(e -> exitB.setStyle(BUTTON_IDLE + themeString));
		exitB.setOnMousePressed(e -> exitB.setStyle(BUTTON_PRESSED));
		
		//-------------------
		// Buttons functions
		//-------------------
		
		menuB.setOnAction(bEvent -> {
		try {
			isPaused=true;
			leave(1);
			
		} catch (Exception e) {
			System.out.println("Next window could not be loaded");
			e.printStackTrace();
		}
		});
		
		exitB.setOnAction(cEvent -> {
			try {
				isPaused=true;
				leave(2);
			} catch (Exception e1) {
				System.out.println("Next window could not be loaded");
				e1.printStackTrace();
			}
		});
		
		pauseB.setOnAction(aEvent -> {
			if (isPaused) {
				isPaused=false;
			}
			else {
				isPaused=true;
			}
			
		});
		
		
		//-------------------
		//  Final settings
		//-------------------
		
		game.gameProperty.addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				try {
					saveAlert(1);
				} catch (Exception e) {
					System.out.println("Window could not be loaded");
					e.printStackTrace();
				}
			}
		});	
		
		AnimationTimer timer = new AnimationTimer() {
			
			@Override
			public void handle(long now) {

				if (isPaused) {
					pauseB.setText("RESUME");
				}
				else pauseB.setText("PAUSE");
				
			}
		};
		timer.start();
		
		
		VBox controlBox = new VBox(score,nextBrick,buttons,makeControls());
//		controlBox.setMaxWidth(200);
		controlBox.setAlignment(Pos.CENTER);

		return controlBox;
	}


	private void resetPane(GridPane pane) {
		pane.add(new Rectangle(50,50), 0, 0);
		pane.add(new Rectangle(50,50), 0, 1);
		pane.add(new Rectangle(50,50), 0, 2);
		pane.add(new Rectangle(50,50), 0, 3);
		pane.add(new Rectangle(50,50), 1, 0);
		pane.add(new Rectangle(50,50), 1, 1);
		pane.add(new Rectangle(50,50), 1, 2);
		pane.add(new Rectangle(50,50), 1, 3);
		pane.add(new Rectangle(50,50), 2, 0);
		pane.add(new Rectangle(50,50), 2, 1);
		pane.add(new Rectangle(50,50), 2, 2);
		pane.add(new Rectangle(50,50), 2, 3);
		pane.add(new Rectangle(50,50), 3, 0);
		pane.add(new Rectangle(50,50), 3, 1);
		pane.add(new Rectangle(50,50), 3, 2);
		pane.add(new Rectangle(50,50), 3, 3);
	}
	
	private void drawNextBrick(String name, GridPane pane) {
		if (name.equals("o")) {
			pane.add(new Rectangle(50,50,Color.GREENYELLOW), 1, 1);
			pane.add(new Rectangle(50,50,Color.GREENYELLOW), 1, 2);
			pane.add(new Rectangle(50,50,Color.GREENYELLOW), 2, 1);
			pane.add(new Rectangle(50,50,Color.GREENYELLOW), 2, 2);
		}
		else if (name.equals("j")) {
			pane.add(new Rectangle(50,50,Color.GREENYELLOW), 1, 3);
			pane.add(new Rectangle(50,50,Color.GREENYELLOW), 2, 1);
			pane.add(new Rectangle(50,50,Color.GREENYELLOW), 2, 2);
			pane.add(new Rectangle(50,50,Color.GREENYELLOW), 2, 3);
		}
		else if (name.equals("l")) {
			pane.add(new Rectangle(50,50,Color.GREENYELLOW), 1, 1);
			pane.add(new Rectangle(50,50,Color.GREENYELLOW), 1, 2);
			pane.add(new Rectangle(50,50,Color.GREENYELLOW), 1, 3);
			pane.add(new Rectangle(50,50,Color.GREENYELLOW), 2, 3);
		}
		else if (name.equals("s")) {
			pane.add(new Rectangle(50,50,Color.GREENYELLOW), 1, 1);
			pane.add(new Rectangle(50,50,Color.GREENYELLOW), 1, 2);
			pane.add(new Rectangle(50,50,Color.GREENYELLOW), 2, 2);
			pane.add(new Rectangle(50,50,Color.GREENYELLOW), 2, 3);
		}
		else if (name.equals("z")) {
			pane.add(new Rectangle(50,50,Color.GREENYELLOW), 1, 2);
			pane.add(new Rectangle(50,50,Color.GREENYELLOW), 1, 3);
			pane.add(new Rectangle(50,50,Color.GREENYELLOW), 2, 1);
			pane.add(new Rectangle(50,50,Color.GREENYELLOW), 2, 2);
		}
		else if (name.equals("t")) {
			pane.add(new Rectangle(50,50,Color.GREENYELLOW), 1, 1);
			pane.add(new Rectangle(50,50,Color.GREENYELLOW), 1, 2);
			pane.add(new Rectangle(50,50,Color.GREENYELLOW), 1, 3);
			pane.add(new Rectangle(50,50,Color.GREENYELLOW), 2, 2);
		}
		else if (name.equals("i")) {
			pane.add(new Rectangle(50,50,Color.GREENYELLOW), 2, 0);
			pane.add(new Rectangle(50,50,Color.GREENYELLOW), 2, 1);
			pane.add(new Rectangle(50,50,Color.GREENYELLOW), 2, 2);
			pane.add(new Rectangle(50,50,Color.GREENYELLOW), 2, 3);
		}
	}


	private Node makeLeft() throws Exception {
		
		game.start(primaryStage);
		
		Rectangle bgR = new Rectangle(Tetris.XMAX+15,Tetris.YMAX+15);
		bgR.setFill(Color.BLACK);
		
		StackPane pane = new StackPane();
		pane.getChildren().addAll(bgR,game.group);
		
		VBox gamebox = new VBox(pane);
		gamebox.setAlignment(Pos.CENTER);
		gamebox.setPadding(new Insets(50));

		return gamebox;
	}

	
	public void leave(int type) throws Exception {
		Alert alert = new Alert(AlertType.NONE, "", ButtonType.YES, ButtonType.CANCEL);
		alert.setTitle("Exiting");
		alert.setHeaderText("Are you sure you want to quit?");
		alert.setContentText("You will then have an option to save your current score");
		DialogPane dialogPane = alert.getDialogPane();
		dialogPane.setStyle("-fx-font-fill: white;"+
				"-fx-font-weight: bold;" +
				"-fx-background-color: darkgray;"
				);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.CANCEL) {
			isPaused=false;
			return;
		}
		else if (result.get() == ButtonType.YES) {
			saveAlert(type);
		}
		else return;
	}
	
	public void saveAlert(int type) throws Exception {
		Alert alert = new Alert(AlertType.NONE, "", ButtonType.YES, ButtonType.NO);
		alert.setTitle("Saving");
		alert.setHeaderText("Do you want to save your current score?");
		alert.setContentText("Your current score will be saved");
		DialogPane dialogPane = alert.getDialogPane();
		dialogPane.setStyle("-fx-font-fill: white;"+
				"-fx-font-weight: bold;" +
				"-fx-background-color: darkgray;"
				);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.NO) {
			if (type == 1) {
				mediaPlayer.stop();
				gameMusicPlaying=false;
				menu.start(primaryStage);
				isPaused=false;
				game.fall.cancel();
			}
			else if (type == 2) {
				primaryStage.close();
				menu.saveOptions();
				game.fall.cancel();
			}
			else return;
		}
		else if (result.get() == ButtonType.YES) {
			saveToScoreboard(type);
		}
		else return;
	}

	public void saveToScoreboard(int type) throws Exception {
	
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Saving");
		dialog.setHeaderText("Enter your nickname");
		dialog.setContentText("Your nickname:");
		DialogPane dialogPane = dialog.getDialogPane();
		dialogPane.setStyle("-fx-font-fill: white;"+
				"-fx-font-weight: bold;" +
				"-fx-background-color: darkgray;"
				);
		
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){
			ScoreBoard score = new ScoreBoard();
			
			String name = result.get().toString();
		    Date date = new Date();  
		    int finalScore = game.scoreProperty.get();
		    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
		    String strDate= formatter.format(date);  			

		    score.saveData(name, finalScore, strDate);
		    
			if (type == 1) {
				mediaPlayer.stop();
				gameMusicPlaying=false;
//				Stage p = new Stage();
//				primaryStage.close();
				menu.start(primaryStage);
				isPaused=false;
				game.fall.cancel();
			}
			else if (type == 2) {
				primaryStage.close();
				menu.saveOptions();
			}
			else return;
		}
		else return;
	}
		
	public void music() {
		if (!gameMusicPlaying) {
			final String s = "music/Waldys Tetris theme.wav";
			Media h = new Media(Paths.get(s).toUri().toString());
			mediaPlayer = new MediaPlayer(h);
			mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
			mediaPlayer.volumeProperty().bindBidirectional(options.getmVolumeProperty());
			gameMusicPlaying=true;
		}
		
	}

}
