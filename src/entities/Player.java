package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import main.Game;
import world.Camera;
import world.World;

public class Player extends Entity{
	
	public boolean right,up,left,down;
	public BufferedImage sprite_right, sprite_left, sprite_up, sprite_down;
	
	public int atlApplee;
	
	public int lastDir = 1;

	public Player(int x, int y, int width, int height,double speed,BufferedImage sprite) {
		super(x, y, width, height,speed,sprite);
		
		sprite_left = Game.spritesheet.getSprite(48, 0, 16, 16);
		sprite_right = Game.spritesheet.getSprite(32, 0, 16, 16);
		sprite_up = Game.spritesheet.getSprite(64, 0, 16, 16);
		sprite_down = Game.spritesheet.getSprite(80, 0, 16, 16);
		
	}
	
	public void checkCollidingApple() {
		
		for(int i=0; i<Game.entities.size(); i++) {
			Entity current = Game.entities.get(i);
			if(current instanceof Apple) {
				if(Entity.isColidding(this, current)){
					Game.entities.remove(i);
					atlApplee++;
					return;
				}
			}
		}

	}
	
	public void tick(){
		
		checkCollidingApple();
		
		if(Game.contApple == Game.player.atlApplee) {
			System.out.println("Próximo level");
			World.restartGame();
			return;
		}
		
		depth = 1;
		if(right && World.isFree((int)(x+speed),this.getY())) {
			x+=speed;
			lastDir = 1;
		}
		else if(left && World.isFree((int)(x-speed),this.getY())) {
			x-=speed;
			lastDir = 2;
		}
		if(up && World.isFree(this.getX(),(int)(y-speed))){
			y-=speed;
			lastDir = 3;
		}
		else if(down && World.isFree(this.getX(),(int)(y+speed))){
			y+=speed;
			lastDir = 4;
		}
	}
	
	public void render(Graphics g){
		
		if(lastDir == 1) {
			g.drawImage(sprite_right,this.getX() - Camera.x,this.getY() - Camera.y,null);
		}else if(lastDir == 2){
			g.drawImage(sprite_left,this.getX() - Camera.x,this.getY() - Camera.y,null);
		}else if(lastDir == 3){
			g.drawImage(sprite_up,this.getX() - Camera.x,this.getY() - Camera.y,null);
		}else if(lastDir == 4){
			g.drawImage(sprite_down,this.getX() - Camera.x,this.getY() - Camera.y,null);
		}
	}
}
