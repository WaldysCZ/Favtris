package game;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Options {

	public BooleanProperty playMusic;
	public DoubleProperty mVolume;
	public BooleanProperty isDarkTheme;
	public StringProperty colorPallete;
	public BooleanProperty isHardMode;
	
	public Options(boolean playMusic,double volume, boolean darkTheme,String colorPallete, boolean hardMode) {
		this.playMusic = new SimpleBooleanProperty(playMusic);
		this.mVolume = new SimpleDoubleProperty(volume);
		this.isDarkTheme = new SimpleBooleanProperty(darkTheme);
		this.colorPallete = new SimpleStringProperty(colorPallete);
		this.isHardMode = new SimpleBooleanProperty(hardMode);
	}
	
	public boolean getPlayMusic() {
		return playMusic.get();
	}
	public BooleanProperty getPlayMusicProperty() {
		return playMusic;
	}
	
	public void setPlayMusic(boolean playMusic) {
		this.playMusic.setValue(playMusic);
	}
	
	public double getmVolume() {
		return mVolume.get();
	}
	
	public DoubleProperty getmVolumeProperty() {
		return mVolume;
	}
	
	public void setmVolume(DoubleProperty mVolume) {
		this.mVolume = mVolume;
	}
	
	public BooleanProperty getIsDarkThemeProperty() {
		return isDarkTheme;
	}
	
	public BooleanProperty getIsHardModeProperty() {
		return isHardMode;
	}
	
	public boolean isHardMode() {
		return isHardMode.get();
	}
	
	public boolean isDarkTheme() {
		return isDarkTheme.get();
	}
	public void setIsDarkTheme(boolean isDarkTheme) {
		this.isDarkTheme.setValue(isDarkTheme);
	}
	
	public StringProperty getColorPaletteProperty() {
		return colorPallete;
	}
	
	public String getColorPallete() {
		return colorPallete.get();
	}
	
	public void setColorPallete(String colorPallete) {
		this.colorPallete.setValue(colorPallete);;
	}
	
}
