package game;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ScoreData {

	private StringProperty name;
	private IntegerProperty score;
	private StringProperty date;
	
	public ScoreData( String name, int score, String date) {
		this.name = new SimpleStringProperty(name);
		this.score = new SimpleIntegerProperty(score);
		this.date = new SimpleStringProperty(date);
	}

	public StringProperty getNameProperty() {
		return name;
	}
	
	public String getName() {
		return name.get();
	}

	public void setName(StringProperty name) {
		this.name = name;
	}

	public IntegerProperty getScoreProperty() {
		return score;
	}
	
	public int getScore() {
		return score.get();
	}

	public void setScore(IntegerProperty score) {
		this.score = score;
	}

	public StringProperty getDateProperty() {
		return date;
	}
	public String getDate() {
		return date.get();
	}

	public void setDate(StringProperty date) {
		this.date = date;
	}
	
	
}
