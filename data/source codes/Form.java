package game;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Form {
	Rectangle a;
	Rectangle b;
	Rectangle c;
	Rectangle d;
	static Color color;
	private String name;
	public int form = 1;
	private StringProperty nameProperty;
	private Options options;

	public Form(Rectangle a, Rectangle b, Rectangle c, Rectangle d) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
	}

	public Form(Rectangle a, Rectangle b, Rectangle c, Rectangle d, String name) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.name = name;
		this.nameProperty= new SimpleStringProperty(name);
		options=MainMenu.options;

		switch (name) {
		case "j":
			// GameBoy
			if (options.getColorPallete().equals("GameBoy")) {
				color = Color.web("#055405");
			}
			// Pastel
			else if (options.getColorPallete().equals("Pastel")) {
				color = Color.web("#FF9AA2");
			}
			// Pink
			else if (options.getColorPallete().equals("Pink")) {
				color = Color.web("#BC00BF");
			}
			// Gray
			else if (options.getColorPallete().equals("Gray")) {
				color = Color.web("#111111");
			}
			// Flame
			else if (options.getColorPallete().equals("Flame")) {
				color = Color.web("#ff0000");
			}
			// Aqua
			else if (options.getColorPallete().equals("Aqua")) {
				color = Color.web("#043087");
			}
			// Dark
			else if (options.getColorPallete().equals("Dark")) {
				color = Color.web("#1B2623");
			}
			// Special
			else if (options.getColorPallete().equals("Special")) {
				color = Color.web("#FD5901");
			}
			// Sunset
			else if (options.getColorPallete().equals("Sunset")) {
				color = Color.web("#150E5C");
			}
			// Neon
			else if (options.getColorPallete().equals("Neon")) {
				color = Color.web("#EF281E");
			}
			// Normal
			else color = Color.SLATEGRAY;
			break;
		case "l":
			// GameBoy
			if (options.getColorPallete().equals("GameBoy")) {
				color = Color.web("#306230");
			}
			// Pastel
			else if (options.getColorPallete().equals("Pastel")) {
				color = Color.web("#FFDAC1");
			}
			// Pink
			else if (options.getColorPallete().equals("Pink")) {
				color = Color.web("#DD08B8");
			}
			// Gray
			else if (options.getColorPallete().equals("Gray")) {
				color = Color.web("#333333");
			}
			// Flame
			else if (options.getColorPallete().equals("Flame")) {
				color = Color.web("#ff5a00");
			}
			// Aqua
			else if (options.getColorPallete().equals("Aqua")) {
				color = Color.web("#227EB8");
			}
			// Dark
			else if (options.getColorPallete().equals("Dark")) {
				color = Color.web("#1A332B");
			}
			// Special
			else if (options.getColorPallete().equals("Special")) {
				color = Color.web("#F78104");
			}
			// Sunset
			else if (options.getColorPallete().equals("Sunset")) {
				color = Color.web("#341358");
			}
			// Neon
			else if (options.getColorPallete().equals("Neon")) {
				color = Color.web("#F6A229");
			}
			// Normal
			else color = Color.DARKGOLDENROD;
			break;
		case "o":
			// GameBoy
			if (options.getColorPallete().equals("GameBoy")) {
				color = Color.web("#8bac0f");
			}
			// Pastel
			else if (options.getColorPallete().equals("Pastel")) {
				color = Color.web("#E2F0CB");
			}
			// Pink
			else if (options.getColorPallete().equals("Pink")) {
				color = Color.web("#F726A7");
			}
			// Gray
			else if (options.getColorPallete().equals("Gray")) {
				color = Color.web("#555555");
			}
			// Flame
			else if (options.getColorPallete().equals("Flame")) {
				color = Color.web("#ff9a00");
			}
			// Aqua
			else if (options.getColorPallete().equals("Aqua")) {
				color = Color.web("#09E2E7");
			}
			// Dark
			else if (options.getColorPallete().equals("Dark")) {
				color = Color.web("#2B4230");
			}
			// Special
			else if (options.getColorPallete().equals("Special")) {
				color = Color.web("#FAAB36");
			}
			// Sunset
			else if (options.getColorPallete().equals("Sunset")) {
				color = Color.web("#5E1D53");
			}
			// Neon
			else if (options.getColorPallete().equals("Neon")) {
				color = Color.web("#FCF532");
			}
			// Normal
			else color = Color.INDIANRED;
			break;
		case "s":
			// GameBoy
			if (options.getColorPallete().equals("GameBoy")) {
				color = Color.web("#9bbc0f");
			}
			// Pastel
			else if (options.getColorPallete().equals("Pastel")) {
				color = Color.web("#C7CEEA");
			}
			// Pink
			else if (options.getColorPallete().equals("Pink")) {
				color = Color.web("#FF4EA4");
			}
			// Gray
			else if (options.getColorPallete().equals("Gray")) {
				color = Color.web("#777777");
			}
			// Flame
			else if (options.getColorPallete().equals("Flame")) {
				color = Color.web("#ffce00");
			}
			// Aqua
			else if (options.getColorPallete().equals("Aqua")) {
				color = Color.web("#1E92EB");
			}
			// Dark
			else if (options.getColorPallete().equals("Dark")) {
				color = Color.web("#71317E");
			}
			// Special
			else if (options.getColorPallete().equals("Special")) {
				color = Color.web("#30c9f0");
			}
			// Sunset
			else if (options.getColorPallete().equals("Sunset")) {
				color = Color.web("#9C2A4B");
			}
			// Neon
			else if (options.getColorPallete().equals("Neon")) {
				color = Color.web("#00BC58");
			}
			// Normal
			else color = Color.FORESTGREEN;
			break;
		case "t":
			// GameBoy
			if (options.getColorPallete().equals("GameBoy")) {
				color = Color.web("#055405");
			}
			// Pastel
			else if (options.getColorPallete().equals("Pastel")) {
				color = Color.web("#B5EAD7");
			}
			// Pink
			else if (options.getColorPallete().equals("Pink")) {
				color = Color.web("#FF76B7");
			}
			// Gray
			else if (options.getColorPallete().equals("Gray")) {
				color = Color.web("#999999");
			}
			// Flame
			else if (options.getColorPallete().equals("Flame")) {
				color = Color.web("#ffe808");
			}
			// Aqua
			else if (options.getColorPallete().equals("Aqua")) {
				color = Color.web("#3957EB");
			}
			// Dark
			else if (options.getColorPallete().equals("Dark")) {
				color = Color.web("#4C2A5C");
			}
			// Special
			else if (options.getColorPallete().equals("Special")) {
				color = Color.web("#249EA0");
			}
			// Sunset
			else if (options.getColorPallete().equals("Sunset")) {
				color = Color.web("#D63940");
			}
			// Neon
			else if (options.getColorPallete().equals("Neon")) {
				color = Color.web("#0881CD");
			}
			// Normal
			else color = Color.CADETBLUE;
			break;
		case "z":
			// GameBoy
			if (options.getColorPallete().equals("GameBoy")) {
				color = Color.web("#3a783a");
			}
			// Pastel
			else if (options.getColorPallete().equals("Pastel")) {
				color = Color.web("#FFB7B2");
			}
			// Pink
			else if (options.getColorPallete().equals("Pink")) {
				color = Color.web("#FF93C4");
			}
			// Gray
			else if (options.getColorPallete().equals("Gray")) {
				color = Color.web("#b0b0b0");
			}
			// Flame
			else if (options.getColorPallete().equals("Flame")) {
				color = Color.web("#cc0000");
			}
			// Aqua
			else if (options.getColorPallete().equals("Aqua")) {
				color = Color.web("#00a8d6");
			}
			// Dark
			else if (options.getColorPallete().equals("Dark")) {
				color = Color.web("#301935");
			}
			// Special
			else if (options.getColorPallete().equals("Special")) {
				color = Color.web("#19f7ff");
			}
			// Sunset
			else if (options.getColorPallete().equals("Sunset")) {
				color = Color.web("#F4633C");
			}
			// Neon
			else if (options.getColorPallete().equals("Neon")) {
				color = Color.web("#cd08ca");
			}
			// Normal
			else color = Color.HOTPINK;
			break;
		case "i":
			// GameBoy
			if (options.getColorPallete().equals("GameBoy")) {
				color = Color.web("#8bac0f");
			}
			// Pastel
			else if (options.getColorPallete().equals("Pastel")) {
				color = Color.web("#E0FEFE");
			}
			// Pink
			else if (options.getColorPallete().equals("Pink")) {
				color = Color.web("#ffa6ce");
			}
			// Gray
			else if (options.getColorPallete().equals("Gray")) {
				color = Color.web("#c4c4c4");
			}
			// Flame
			else if (options.getColorPallete().equals("Flame")) {
				color = Color.web("#d6a400");
			}
			// Aqua
			else if (options.getColorPallete().equals("Aqua")) {
				color = Color.web("#00c1d6");
			}
			// Dark
			else if (options.getColorPallete().equals("Dark")) {
				color = Color.web("#4e4154");
			}
			// Special
			else if (options.getColorPallete().equals("Special")) {
				color = Color.web("#2f9ffa");
			}
			// Sunset
			else if (options.getColorPallete().equals("Sunset")) {
				color = Color.web("#661c46");
			}
			// Neon
			else if (options.getColorPallete().equals("Neon")) {
				color = Color.web("#08c0d1");
			}
			// Normal
			else color = Color.SANDYBROWN;
			break;

		}
		this.a.setFill(color);
		this.b.setFill(color);
		this.c.setFill(color);
		this.d.setFill(color);
	}


	public String getName() {
		return name;
	}
	
	public StringProperty getNameProperty() {
		return nameProperty;
	}


	public void changeForm() {
		if (form != 4) {
			form++;
		} else {
			form = 1;
		}
	}
}