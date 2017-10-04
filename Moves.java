
public class Moves {

	private Moves(){
		
	}
	
	
	public static boolean verifyMove(String direction, Board board){
		
		switch(direction){
		
		case "up":
			if(board.blankPosY > 0){
				System.out.println("can move UP");
				return true;
			}
			System.out.println("can't move UP");
			break;

		case "down":
			if(board.blankPosY <2 ){
				System.out.println("can move DOWN");
				return true;
			}
			System.out.println("can't move DOWN");
			break;
			
		case "left":
			if(board.blankPosX >0 ){
				System.out.println("can move LEFT");
				return true;
			}
			System.out.println("can't move LEFT");
			break;
		case "right":
			if(board.blankPosX <2 ){
				System.out.println("can move RIGHT");
				return true;
			}
			System.out.println("can't move RIGHT");

			break;
		}
		return false;
	}
	
	
	public static void move(String direction, Board board){
		switch(direction){
		
		case "up":
			//board.current_state = board.start_state;//resetting the board
			int neighborUP = board.start_state[board.blankPosY-1][board.blankPosX];
			board.start_state[board.blankPosY-1][board.blankPosX] = 0;//place 0 at neighbor value
			board.start_state[board.blankPosY][board.blankPosX] = neighborUP;//place neighbor at blank
			System.out.println("Moving Up and Replacing " + neighborUP + " with 0");
			break;

		case "down":
			//board.current_state = board.start_state;//resetting the board
			int neighborDOWN = board.start_state[board.blankPosY+1][board.blankPosX];
			board.start_state[board.blankPosY+1][board.blankPosX] = 0;
			board.start_state[board.blankPosY][board.blankPosX] = neighborDOWN;
			System.out.println("Moving Down and Replacing " + neighborDOWN + " with 0");
			break;
		
		case "left":
			//board.current_state = board.start_state;//resetting the board
			
			int neighborLEFT = board.start_state[board.blankPosY][board.blankPosX-1];
			board.start_state[board.blankPosY][board.blankPosX-1] = 0;
			board.start_state[board.blankPosY][board.blankPosX] = neighborLEFT;
			System.out.println("Moving Left and Replacing " + neighborLEFT + " with 0");
			break;
		
		case "right":
			//board.start_state = board.start_state;//resetting the board
			int neighborRIGHT = board.start_state[board.blankPosY][board.blankPosX+1];
			board.start_state[board.blankPosY][board.blankPosX+1] = 0;
			board.start_state[board.blankPosY][board.blankPosX] = neighborRIGHT;
			System.out.println("Moving right and Replacing " + neighborRIGHT + " with 0");
			
			break;
		}
		
	}
	
}
