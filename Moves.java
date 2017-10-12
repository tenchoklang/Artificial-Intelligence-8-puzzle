/*
 * The move class does two main things
 * 1) It verifies if we can move in a certain direction 
 * 2) It move in the direction specified in the parameter here it also 
 * 	  after it moves the tile, it checks and updates the misplaced count
 */
public class Moves {

	private Moves(){//non instantiable
		
	}
	/*
	 * Checks if we can move in a specified direction
	 * 		  0   1   2
	 * 		--------------
	 * 	0	|__	|__	|__	|
	 * 	1	|__	|__	|__	|
	 * 	2	|	|	|	|	
	 * 		--------------
	 * imagine a board like this if we want to move up we know that it has to be > 0
	 * otherwise it would be out of bounds like wise if we want to move down
	 * it has to be < 2 in this case, otherwise it would be out of bounds 
	 * 
	 */
	public static boolean verifyMove(String direction, Board board){
		
		switch(direction){
		
		case "up":
			if(board.blankPosY > 0){//true = can move 
				return true;
			}
			break;

		case "down":
			if(board.blankPosY < Board.boardSizeY -1 ){
				return true;
			}
			break;
			
		case "left":
			if(board.blankPosX >0 ){
				return true;
			}
			break;
		case "right":
			if(board.blankPosX < Board.boardSizeX-1 ){
				return true;
			}

			break;
		}
		return false;
	}
	
	/*
	 * Moves in a specified direction
	 */
	public static void move(String direction, Board board){

		switch(direction){
		
		case "up":
			swapTiles(board, "up", board.blankPosY, board.blankPosX, board.blankPosY-1, board.blankPosX);
			board.blankPosY = board.blankPosY -1;//update the new blank position
			break;

		case "down":
			swapTiles(board, "down", board.blankPosY, board.blankPosX, board.blankPosY+1, board.blankPosX);
			board.blankPosY = board.blankPosY +1;
			break;
		
		case "left":			
			swapTiles(board, "left", board.blankPosY, board.blankPosX, board.blankPosY, board.blankPosX-1);
			board.blankPosX = board.blankPosX-1;
			break;
		
		case "right":
			swapTiles(board, "right", board.blankPosY, board.blankPosX, board.blankPosY, board.blankPosX+1);
			board.blankPosX = board.blankPosX +1;
			break;
		}
	}
	
	public static void swapTiles(Board board, String direction, int row, int col, int newRow, int newCol){
		int neighbor = board.start_state[newRow][newCol];
		board.start_state[newRow][newCol] = 0;//place 0 at neighbor value
		board.start_state[row][col] = neighbor;//place neighbor at blank
		//System.out.println("Moving "+ direction +" and Replacing " + neighbor + " with 0");
		Board.findMisplacedTiles(board, newRow, newCol, neighbor);
	}
}
