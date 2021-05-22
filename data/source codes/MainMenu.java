package game;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Hlavni spoustejici menu hry
 * Umoznuje zapnuti hry, zmenu nastaveni nebo spusteni tabulky se scorem
 * @author David Muhr
 *
 */
public class MainMenu extends Application {
	
	private static Scanner sc;
	private Stage primaryStage;
	private static MediaPlayer mediaPlayer;
	public static Options options;
	public boolean menuMusicPlaying = false;
	public int W = 500;
	public int H = 500;
	
	private final String BUTTON_IDLE = new String(
			"-fx-border-color: transparent;" + 
			"-fx-border-width: 0;" + 
			"-fx-background-radius: 0;" + 
			"-fx-background-color: transparent;" + 
			"-fx-font-family: Comic Sans;" + 
			"-fx-font-size: 25;" + 
			"-fx-font-weight: bold;" +
			"-fx-text-fill: #424242;");
	private final String BUTTON_HOVER = new String(
			"-fx-border-color: transparent, black;" + 
			"-fx-border-width: 0;" +
			"-fx-border-style: solid, segments(1, 2);" +
			"-fx-background-radius: 0;" + 
			"-fx-background-color: transparent;" + 
			"-fx-font-family: Comic Sans;" + 
			"-fx-font-size: 25;" + 
			"-fx-font-weight: bold;" +
			"-fx-text-fill: #00a1b3;");
	private final String BUTTON_PRESSED = new String(
			"-fx-border-color: transparent, black;" + 
			"-fx-border-width: 0;" +
			"-fx-border-style: solid, segments(1, 2);" +
			"-fx-background-radius: 0;" + 
			"-fx-background-color: transparent;" + 
			"-fx-font-family: Comic Sans;" + 
			"-fx-font-size: 25;" + 
			"-fx-font-weight: bold;" +
			"-fx-text-fill: #00e5ff;");
	private final BackgroundImage B_IMG = new BackgroundImage(new Image("file:bg.jpg",
			false),
	        BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
	        BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
	
	protected final BackgroundImage B_OP_IMG = new BackgroundImage(new Image("file:bgOptions.png",
			false),
	        BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
	        BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
	
	private static boolean optionsIsLoaded = false;

	@Override
	public void start(Stage primaryStage) throws Exception {		
		this.primaryStage=primaryStage;
		
		if (!optionsIsLoaded) {
			MainMenu.options = loadOptions();
			optionsIsLoaded=true;
		}
		
		music();			
		
		if (options.getPlayMusic()==true) {
			mediaPlayer.play();
		}
		else mediaPlayer.stop();
		
		//-------------------------
		//        Title
		//-------------------------
		
		BorderPane mainPane = new BorderPane();
		
		DropShadow ds = new DropShadow();
		ds.setOffsetY(3);
		ds.setColor(Color.BLACK);
		 
		Text title = new Text();
		title.setEffect(ds);
		title.setCache(true);
		title.setText("FAVTRIS");
		title.setFont(Font.font("Comic Sans", FontWeight.BOLD, 80));
		title.setStyle(
				"-fx-fill: linear-gradient(from 0% 0% to 100% 200%, repeat, deepskyblue 0%, orange 50%);" +
				"-fx-stroke: black;" +
				"-fx-stroke-width: 2;" +
				"-fx-font-weight: bold;");
		
		//-------------------------
		//       Buttons
		//-------------------------
		
		Button gameB = new Button("START");
		Button optionB = new Button("OPTIONS");
		Button scoreB = new Button("SCORE");
		Button exitB = new Button("EXIT");
		
		gameB.setStyle(BUTTON_IDLE);
		gameB.setOnMouseEntered(e -> gameB.setStyle(BUTTON_HOVER));
		gameB.setOnMouseExited(e -> gameB.setStyle(BUTTON_IDLE));
		gameB.setOnMousePressed(e -> gameB.setStyle(BUTTON_PRESSED));
		
		optionB.setStyle(BUTTON_IDLE);
		optionB.setOnMouseEntered(e -> optionB.setStyle(BUTTON_HOVER));
		optionB.setOnMouseExited(e -> optionB.setStyle(BUTTON_IDLE));
		optionB.setOnMousePressed(e -> optionB.setStyle(BUTTON_PRESSED));
		
		scoreB.setStyle(BUTTON_IDLE);
		scoreB.setOnMouseEntered(e -> scoreB.setStyle(BUTTON_HOVER));
		scoreB.setOnMouseExited(e -> scoreB.setStyle(BUTTON_IDLE));
		scoreB.setOnMousePressed(e -> scoreB.setStyle(BUTTON_PRESSED));
		
		exitB.setStyle(BUTTON_IDLE);
		exitB.setOnMouseEntered(e -> exitB.setStyle(BUTTON_HOVER));
		exitB.setOnMouseExited(e -> exitB.setStyle(BUTTON_IDLE));
		exitB.setOnMousePressed(e -> exitB.setStyle(BUTTON_PRESSED));
		
		optionB.setOnAction(aEvent -> startOption(primaryStage));
		gameB.setOnAction(bEvent -> {
		GameScreen gameplay = new GameScreen();	
		try {
			mediaPlayer.stop();
			menuMusicPlaying=false;
			gameplay.start(primaryStage);

		} catch (Exception e) {
			System.out.println("Next window could not be loaded");
			e.printStackTrace();
		}
		});
		exitB.setOnAction(cEvent -> {primaryStage.close();try {
			saveOptions();
		} catch (IOException e1) {
			System.out.println("Options could not be saved");
			e1.printStackTrace();
		}});
		
		scoreB.setOnAction(bEvent -> {
		ScoreBoard scoreboard = new ScoreBoard();	
		try {
			scoreboard.start(primaryStage);

		} catch (Exception e) {
			System.out.println("Next window could not be loaded");
			e.printStackTrace();
		}
		});
		
		//-------------------------
		//     Menu settings
		//-------------------------
		
		
		VBox menuButtons = new VBox(gameB,scoreB,optionB,exitB);
				
		menuButtons.setSpacing(10);
		menuButtons.setAlignment(Pos.CENTER);
		menuButtons.setPadding(new Insets(1));
		menuButtons.setStyle("-fx-padding: 20;" + "-fx-border-style: solid inside;"
		        + "-fx-border-width: 5;" + "-fx-border-insets: 0;"
		        + "-fx-border-radius: 0;" + "-fx-border-color: black;"
		        + "-fx-background-color: lightgray");
		
		
		VBox menu = new VBox(title,menuButtons);
		
		menu.setAlignment(Pos.CENTER);
		menu.setSpacing(20);
		menu.setPadding(new Insets(30));
		menu.setMaxWidth(400);
		
		mainPane.setCenter(menu);
		mainPane.setBackground(new Background(B_IMG));
		
		Scene mainMenu = new Scene(mainPane,500,500);
		
		this.primaryStage.setScene(mainMenu);
		this.primaryStage.setTitle("Tetris Game - Main Menu");
		this.primaryStage.setHeight(mainMenu.getHeight());
		this.primaryStage.setWidth(mainMenu.getWidth());
//		this.primaryStage.setMinHeight(mainMenu.getHeight());
//		this.primaryStage.setMinWidth(mainMenu.getWidth()-100);
		this.primaryStage.show();
		this.primaryStage.sizeToScene();
		this.primaryStage.setResizable(false);
		this.primaryStage.getIcons().add(new Image("file:icon.png"));
	}
	
	//--------------
	//	 Options
	//--------------
	
	private void startOption(Stage p) {
		
		Text menuL = new Text("Options");
		menuL.setStyle(
				"-fx-font-size: 50;" +
				"-fx-fill: orange;" +
				"-fx-stroke: black;" +
				"-fx-stroke-width: 2;" +
				"-fx-font-weight: bold;");
		
		Label musicL = new Label("Music:");
		CheckBox musicCB = new CheckBox();
		
		HBox music = new HBox(musicL,musicCB);
		music.setAlignment(Pos.CENTER);
		music.setSpacing(10);
		music.setStyle(BUTTON_IDLE);
		
		Label volumeL = new Label("Volume:");
		volumeL.setStyle(BUTTON_IDLE);
		Slider volume = new Slider();
		volume.setMax(1);
		volume.setMin(0);
		volume.setMaxWidth(300);
		
		Label darkL = new Label("Dark Mode:");
		CheckBox darkmCB = new CheckBox();
		
		HBox darkMode = new HBox(darkL,darkmCB);
		darkMode.setStyle(BUTTON_IDLE);
		darkMode.setAlignment(Pos.CENTER);
		darkMode.setSpacing(10);
		
		Label paletteL = new Label("Color Palette:");
		
		ChoiceBox<String> paletteCB = new ChoiceBox<String>();
        paletteCB.getItems().add("Normal");
        paletteCB.getItems().add("Special");
        paletteCB.getItems().add("GameBoy");
        paletteCB.getItems().add("Pastel");
        paletteCB.getItems().add("Pink");
        paletteCB.getItems().add("Gray");
        paletteCB.getItems().add("Flame");
        paletteCB.getItems().add("Aqua");
        paletteCB.getItems().add("Dark");
        paletteCB.getItems().add("Neon");
        paletteCB.getItems().add("Sunset");
        paletteCB.valueProperty().bindBidirectional(options.colorPallete);
        paletteCB.setMaxHeight(5);
        
		Label hModeL = new Label("Hard Mode:");
		CheckBox hModeCB = new CheckBox();
		
		HBox hardM = new HBox(hModeL,hModeCB);
		hardM.setAlignment(Pos.CENTER);
		hardM.setSpacing(10);
		hardM.setStyle(BUTTON_IDLE);
		
		HBox colorP = new HBox(paletteL,paletteCB);
		colorP.setAlignment(Pos.CENTER);
		colorP.setSpacing(10);
		paletteCB.setStyle("-fx-font-family: Comic Sans;" + 
				"-fx-font-size: 15;" + 
				"-fx-font-weight: bold;" +
				"-fx-text-fill: #2e2e2e;");
		paletteL.setStyle( 
				"-fx-font-family: Comic Sans;" + 
				"-fx-font-size: 25;" + 
				"-fx-font-weight: bold;" +
				"-fx-text-fill: #2e2e2e;");
		
		Button backB = new Button("Back");

		VBox optionPane = new VBox(menuL,music,volumeL,volume,darkMode,colorP,hardM,backB);
		optionPane.setSpacing(20);
		optionPane.setAlignment(Pos.CENTER);
		optionPane.setBackground(new Background(B_OP_IMG));
		
		//-------------------------
		//   Set functions
		//-------------------------
		
		backB.setStyle(BUTTON_IDLE);
		backB.setOnMouseEntered(e -> backB.setStyle(BUTTON_HOVER));
		backB.setOnMouseExited(e -> backB.setStyle(BUTTON_IDLE));
		backB.setOnMousePressed(e -> backB.setStyle(BUTTON_PRESSED));
		
		backB.setOnAction(aEvent -> {
			try {
				start(p);
			} catch (Exception e) {
				System.out.println("Next window could not be loaded");
				e.printStackTrace();
			}
		});
		
		musicCB.selectedProperty().bindBidirectional(options.getPlayMusicProperty());
	
		volume.valueProperty().bindBidirectional(options.getmVolumeProperty());
		
		darkmCB.selectedProperty().bindBidirectional(options.getIsDarkThemeProperty());
		
		hModeCB.selectedProperty().bindBidirectional(options.getIsHardModeProperty());
		
		// Final setings
		
		Scene optionMenu = new Scene(optionPane, 400,480);
		
		p.setScene(optionMenu);
		p.setTitle("Tetris Game - Options");
		p.setHeight(optionMenu.getHeight());
		p.setWidth(optionMenu.getWidth());
		p.setMinHeight(optionMenu.getHeight());
		p.setMinWidth(optionMenu.getWidth());
		p.setResizable(true);
		p.show();
		p.sizeToScene();
		
	}

	public void music() {
		if (!menuMusicPlaying) {
			final String s = "music/Waldys Tetris menu.wav";
			Media h = new Media(Paths.get(s).toUri().toString());
			mediaPlayer = new MediaPlayer(h);
			mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
			mediaPlayer.volumeProperty().bindBidirectional(options.getmVolumeProperty());
			menuMusicPlaying=true;;
		}
		
	}
	
	public void saveOptions() throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter("options.txt"));
		bw.write(
				"Music= " + options.getPlayMusic() +
				"\nVolume= " + options.getmVolume() +
				"\nDarkMode= " + options.isDarkTheme() +
				"\nColorPalette= " + options.getColorPallete() +
				"\nHardMode= " + options.isHardMode()
				);
        bw.close();
	}
	
	public Options loadOptions() throws IOException {
		
		File f = new File("options.txt");
		if(!f.isFile()){
			BufferedWriter bw = new BufferedWriter(new FileWriter("options.txt"));
			bw.write(
					"Music= true"+
					"\nVolume= 0.5" +
					"\nDarkMode= true" +
					"\nColorPalette= Normal"+
					"\nHardMode= false" 
					);
	        bw.close();
	        f = new File("options.txt");  
		}
		sc = new Scanner(f);
		
		ArrayList<String> sts = new ArrayList<String>();
		
		while(sc.hasNext()) {
			String st = sc.nextLine();
			String parts[] = st.split(" ", 2);

			sts.add(parts[1]);
		}

		boolean music = Boolean.parseBoolean(sts.get(0));
		double volume= Double.parseDouble(sts.get(1));
		boolean darkMode= Boolean.parseBoolean(sts.get(2));
		String color= sts.get(3);
		boolean hardMode= Boolean.parseBoolean(sts.get(4));
		
		Options op = new Options(music,volume,darkMode,color, hardMode);
		
		return op;
	}
	
	public static void main(String[] args) {
		launch();
	}

	
}
