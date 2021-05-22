package game;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.SortType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ScoreBoard extends Application {
	
	private Scanner sc;
	private MainMenu menu;
	private Stage primaryStage;
	private TableView<ScoreData> tableView;
	
	private final String BUTTON_IDLE = new String(
			"-fx-border-color: transparent;" + 
			"-fx-border-width: 0;" + 
			"-fx-background-radius: 0;" + 
			"-fx-background-color: transparent;" + 
			"-fx-font-family: Comic Sans;" + 
			"-fx-font-size: 25;" + 
			"-fx-font-weight: bold;" +
			"-fx-text-fill: #b8b8b8;");
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

	public void start(Stage primaryStage) throws Exception {
		
		this.primaryStage=primaryStage;
		
		this.menu = new MainMenu();
		
		this.primaryStage.setTitle("Tetris Game - ScoreBoard");		
		
		Scene mainScene = new Scene(createMainPane(), 500, 500);
		
		this.primaryStage.setScene(mainScene);
		this.primaryStage.setWidth(mainScene.getWidth());
		this.primaryStage.setMinWidth(mainScene.getWidth()-50);
		this.primaryStage.setHeight(mainScene.getHeight());
		this.primaryStage.setMinHeight(mainScene.getHeight()-50);
		this.primaryStage.setResizable(true);
		
		this.primaryStage.show();
	}
  

	private Parent createMainPane() {
		BorderPane main = new BorderPane();
		main.setCenter(makeTable());
		main.setBottom(makeBottom());
		
		main.setBackground(new Background(menu.B_OP_IMG));
	
		return main;
	}

	@SuppressWarnings("unchecked")
	private Node makeTable() {

		tableView = new TableView<ScoreData>();
		
		tableView.setEditable(false);;

		TableColumn<ScoreData, String> nameColumn = new TableColumn<ScoreData, String>("Name");
		TableColumn<ScoreData, Integer> scoreColumn = new TableColumn<ScoreData, Integer>("Score");
		TableColumn<ScoreData, String> dateColumn = new TableColumn<ScoreData, String>("Date");

		nameColumn.setCellValueFactory(new PropertyValueFactory<ScoreData, String>("name"));
		nameColumn.setMinWidth(75);
		nameColumn.setCellFactory(column -> new TextFieldTableCell<ScoreData, String>());
		
		scoreColumn.setCellValueFactory(new PropertyValueFactory<ScoreData, Integer>("score"));
		scoreColumn.setMinWidth(75);
		scoreColumn.setSortType(SortType.DESCENDING);
		
		dateColumn.setCellValueFactory(new PropertyValueFactory<ScoreData, String>("date"));
		dateColumn.setMinWidth(75);
		
	    tableView.getColumns().addAll(nameColumn,scoreColumn,dateColumn);
	    
	    try {
			tableView.setItems(loadData());
		} catch (FileNotFoundException e) {
			System.out.println("Data could not be loaded");
			e.printStackTrace();
		}

	    tableView.getSortOrder().add(scoreColumn);
	    tableView.setMaxWidth(600);

	    tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		tableView.setFixedCellSize(40);
		tableView.prefHeightProperty().bind(Bindings.size(tableView.getItems())
				.multiply(tableView.getFixedCellSize()).add(35));
		
		BorderPane.setMargin(tableView, new Insets(5));
	    
	    tableView.setPlaceholder(new Label("No data to display"));
	    
	    tableView.setStyle("-fx-background-color: black;" +
				"-fx-stroke: black;" +
				"-fx-stroke-width: 2;" +
				"-fx-font-weight: bold;");
	    
	    Text title = new Text("SCOREBOARD");
	    title.setStyle(
				"-fx-font-size: 50;" +
				"-fx-fill: orange;" +
				"-fx-stroke: black;" +
				"-fx-stroke-width: 2;" +
				"-fx-font-weight: bold;");
	    
	    VBox vbox = new VBox(title,tableView);
	    vbox.setAlignment(Pos.CENTER);
	    vbox.setSpacing(20);
	    
		return vbox;
	}

	private Node makeBottom() {
		
		
		Button backB = new Button("BACK");
		
		backB.setStyle(BUTTON_IDLE);
		backB.setOnMouseEntered(e -> backB.setStyle(BUTTON_HOVER));
		backB.setOnMouseExited(e -> backB.setStyle(BUTTON_IDLE));
		backB.setOnMousePressed(e -> backB.setStyle(BUTTON_PRESSED));
		
		backB.setOnAction(aEvent -> {
			try {
				menu.menuMusicPlaying=true;
				menu.start(primaryStage);
			} catch (Exception e) {
				System.out.println("Next window could not be loaded");
				e.printStackTrace();
			}
		});
		
		HBox buttons = new HBox(backB);
		buttons.setAlignment(Pos.CENTER);
		
		return buttons;
	}
	
	public ObservableList<ScoreData> loadData() throws FileNotFoundException {
		ObservableList<ScoreData> data = FXCollections.observableArrayList();	
		
		File f = new File("score.txt");
		if(!f.exists()){
		  try {
				BufferedWriter bw = new BufferedWriter(new FileWriter("score.txt"));
				bw.write(
						"Tetris God#50000#01/01/0000\r\n" + 
						"JJ#34935#15/05/2020\r\n" + 
						"Waldys#34145#14/05/2020\r\n" + 
						"MyNickIsNik#30255#30/05/2020\r\n" + 
						"sltN#22710#04/06/2020\r\n" + 
						"Rypec#16520#08/06/2020"
						);
		        bw.close();
		        f = new File("score.txt");  
		  } 
		  catch (IOException e) {
			System.out.println("Not posible to create new file");
			e.printStackTrace();
		  }
		}
		
		sc = new Scanner(f);
		
		while(sc.hasNext()) {
			String st = sc.nextLine();
			String parts[] = st.split("#", 3);
//			System.out.println(parts[0]);
	//		System.out.println(parts[1]);
		//	System.out.println(parts[2]);
			data.add(new ScoreData(parts[0], Integer.parseInt(parts[1]), parts[2]));
		}
		
		return data;
	}
	
	public void saveData(String name, int score, String date) throws IOException {

		String newData = "";
		int counter = 0;
		boolean isAdded = false;
		
		sc = new Scanner(new File("score.txt"));
		while(sc.hasNext()) {
			String st = sc.nextLine();
			String parts[] = st.split("#", 3);
			if (score>=Integer.parseInt(parts[1])&&isAdded==false) {
				newData+= name +"#"+ score +"#"+ date +"\n";
				counter++;
				isAdded=true;
			}
			if (counter>=10) {
				break;
			}
			else {
				newData+= parts[0] +"#"+ parts[1] +"#"+ parts[2] +"\n";				
				counter++;				
			}
		}
		if (!isAdded && counter<10) {
			newData+= name +"#"+ score +"#"+ date +"\n";
			counter++;
		}
		
		BufferedWriter bw = new BufferedWriter(new FileWriter("score.txt"));
		bw.write(newData);
        bw.close();
	}
	
}