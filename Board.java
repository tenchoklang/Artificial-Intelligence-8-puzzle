
public class Board implements Comparable<Board>{
	
	public static int boardSizeX;
	public static int boardSizeY;
	
	public int misplacedTiles;
	
	public int manhattanDistance;
	
	public int heuristic;
	
	public int blankPosX;//X position of the blank tile
	public int blankPosY;//Y position of the blank tile

	
	public static final int[][] GOAL_STATE = new int[][]{
		{1,2,3},
		{8,0,4},
		{7,6,5}
	};
	
	public static boolean goalStatus = false;
	public static Board answerBoard;//the final answer that an algorithm has found, so we can backtrack the path
	
	public Board parentBoard;//helps with backtracking the path taken
	public Board favoriteChild;
	
	public int level = 0;
	
	public int[][] start_state;//state of board at the start
	
	public Board(int[][] board){
		Board.boardSizeX = board[0].length;
		Board.boardSizeY = board.length;
		start_state = new int[boardSizeY][boardSizeX];
		copyBoard(board);
		findBlankPos();//locates the blank tile
	}	
	
	public void findBlankPos(){//find blank position
		for(int y =0; y<boardSizeY;y++){	
			for(int x=0;x<boardSizeX;x++){
				if(start_state[y][x] == 0){
					blankPosX = x;
					blankPosY = y;
				}
			}
		}
	}
	
	private void copyBoard(int[][] board){//copy by value
		for(int y=0;y<boardSizeY;y++){
			for(int x=0;x<boardSizeX;x++){
				start_state[y][x] = board[y][x];
			}
		}
	}
	
	private static void checkGoalStatus(Board board){
		if(board.misplacedTiles == 0){
			answerBoard = board;//backtrack from this path to find the right path
			goalStatus = true;
		}
	}
	
	/*
	 * if the boards parent is null then it we search the entire board for misplaced
	 * Otherwise we inherit the misplaced from parent, then we check the one tile that 
	 * we moved and compare that to the goal state
	 * if it is the same then misplacedTiles--, else misplacedTiles++
	 */
	public static void findMisplacedTiles(Board board, int x, int y, int neighbor){
		if(board.parentBoard == null){//start from searching the whole board for misplaced, only first board uses this
			for(int row =0; row<Board.boardSizeY;row++){	
				for(int col=0;col<Board.boardSizeX;col++){
					if(board.start_state[row][col] != 0 && Board.GOAL_STATE[row][col]!= board.start_state[row][col]){
						board.misplacedTiles++;
					}
				}
			}
		}else{//other wise misplaced tiles is inherited then we check that single tile we moved for misplacement 
			board.misplacedTiles = board.parentBoard.misplacedTiles;//inherit misplaced tiles from parent
			if(Board.GOAL_STATE[y][x] == neighbor){
			board.misplacedTiles--;
			}else{ board.misplacedTiles++; }
		}
		//goal met if misplaced is zero
		checkGoalStatus(board);
	}
	
	public int calculateHeuristic(String algorithm){//gets the Heuristic of the specified algorithm
		
		algorithm.toLowerCase();
		switch(algorithm){
		
		case "astar misplaced":
			level = parentBoard.level + 1;//update the level
			this.heuristic = level + misplacedTiles;
			break;
			
		case "astar manhattan":
			level = parentBoard.level + 1;//update the level
			manhattanDistance = findManhattanDistance(start_state);
			heuristic = level + manhattanDistance;
			break;
			
		case "astar ida":
			level = parentBoard.level + 1;
			manhattanDistance = findManhattanDistance(start_state);
			heuristic = manhattanDistance;
			break;
		
		case "dfbnb":
			level = parentBoard.level + 1;
			manhattanDistance = findManhattanDistance(start_state);
			heuristic = manhattanDistance;
			break;
			
		default: 
			System.out.println("Algorithm not found");
			
		}
		
		return heuristic;
	}
	
/* Note to self:
 * Make manhattan distance like misplaced, where only the parent needs to go through
 * the loop initially, then all boards after that are either incremented or decremented
 * since we are only moving one tile
 */
public static int findManhattanDistance(int[][] easyBoard){
		
		int totalDistance = 0;
		for(int y=0;y<3;y++){
			for(int x=0;x<3;x++){
				if(easyBoard[y][x] != 0){
					int number = easyBoard[y][x];
					/*
					 * Finds the Number in the GOAL_STATE that you know the coordinate to in the Board
					 */
					int[] coordinates = findNumber(GOAL_STATE, number);
					int XPositionInGoalState = coordinates[1];
					int YPositionInGoalState = coordinates[0];
					
					int verticalDistance = findDistance(x,XPositionInGoalState);
					int horizontalDistance = findDistance(y,YPositionInGoalState);
					
					totalDistance = totalDistance + (verticalDistance + horizontalDistance);
				}
				
			}
		}
		return totalDistance;
	}
	
	/*
	 * Finds the positive distance given two points
	 */
	public static int findDistance(int position1, int position2){
		int distance = Math.abs(position1 - position2);
		return distance;	
	}
	
	/*
	 * Finds the coordinates of a number
	 */
	public static int[] findNumber(int[][] goal_state, int number){
		
		int[] coordinate = new int[2];
		
		for(int y=0;y<3;y++){
			for(int x=0;x<3;x++){
				if(goal_state[y][x] == number){
					coordinate[0] = y;
					coordinate[1] = x;
					return coordinate;
				}
			}
		}
		System.out.println("NUMBER NOT FOUND");
		return null;
	}
	
	
	@Override
	public String toString(){
		String result = "";
		  for(int row = 0; row < Board.boardSizeY; row++) {
		     for(int col = 0; col < Board.boardSizeX; col++) {
			     result += " " + start_state[row][col];
		     }
		     result += "\r\n"; //next line
		  }
		return result;
	}
	
	@Override
	public int compareTo(Board otherBoard) {//override, used by priority queue to prioritize lower heuristic
		
		if(this.heuristic<otherBoard.heuristic)
		{
			return -1;
		}
		else if (this.heuristic>otherBoard.heuristic)
		{
			return 1;
		}
		return 0;
	}
	
}