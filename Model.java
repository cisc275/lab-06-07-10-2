/**
 * Model: Contains all the state and logic
 * Does not contain anything about images or graphics, must ask view for that
 *
 * has methods to
 *  detect collision with boundaries
 * decide next direction
 * provide direction
 * provide location
 **/
public class Model{
	int changeDirection = -1;
	int Width;
	int Height;
	int ImgHeight;
	int ImgWidth;
	int xLocation= 0;
	int yLocation = 0;
	int xIncr = 8;
	int yIncr = 2;
	Direction direction = Direction.SOUTHEAST;
	
	Model(int width,int height,int imgWidth,int imgHeight){
		Width = width;
		Height = height;
		ImgWidth = imgWidth;
		ImgHeight = imgHeight;
	}
	public void updateLocationAndDirection() {
		//Updates the location and direction fields based on the game logic
		xLocation += xIncr;
    	yLocation += yIncr;
		if (xLocation >= Width - ImgWidth) {
    		xIncr = xIncr *changeDirection;
    	}
		else if (xLocation < 0 ) {
    		xIncr = xIncr * changeDirection;
    	}                      
		else if (yLocation >=  Height - ImgHeight) {
    		yIncr = yIncr* changeDirection;
    	}
		else if (yLocation < 0) {
    		yIncr = yIncr * changeDirection;
    	}
    	
    	
    	if (xIncr > 0) {
    		if (yIncr == 0) {
    			direction = Direction.EAST;
    		}
    		else if (yIncr > 0) {
    			direction = Direction.SOUTHEAST;
    		}
    		else if (yIncr < 0) {
    			direction = Direction.NORTHEAST;
    		}	
    	}
    	else if(xIncr < 0) {
    		if (yIncr == 0) {
    			direction = Direction.WEST;
    		}
    		if (yIncr > 0) {
    			direction = Direction.SOUTHWEST;
    		}
    		else if (yIncr < 0) {
    			direction = Direction.NORTHWEST;
    		}
    	
    	}
    	else if(xIncr == 0) {
    		if (yIncr > 0) {
    			direction = Direction.SOUTH;
    		}
    		else if (yIncr < 0) {
    			direction = Direction.NORTH;
    		}
    	}
	}
	
	int getX(){
		return xLocation;
	}
	int getY() {
		return yLocation;
	}
	Direction getDirect(){
		return direction;
	}
}