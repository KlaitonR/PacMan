package graficos;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import main.Game;

public class UI {

	public void render(Graphics g) {
		
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.BOLD, 18));
		g.drawString("Maças: "+ Game.player.atlApplee + "/"  + Game.contApple, 10, 20);
		
	}
	
}
