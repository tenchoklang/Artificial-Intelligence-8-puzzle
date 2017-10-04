
/*
 */

public class Board implements Comparable<Board>{
	
	public static final int DEFAULT_BOARD_SIZEX = 3;
	public static final int DEFAULT_BOARD_SIZEY = 3;
	
	public static int boardSizeX;
	public static int boardSizeY;
	
	public int misplacedTiles;
	
	public int manhattanDistance;
	
	public int heuristic;
	
	public int blankPosX;//X position of the blank tile
	public int blankPosY;//Y position of the blank tile

	
	private static final int[][] GOAL_STATE = new int[][]{
		{1,2,3},
		{8,0,4},
		{7,6,5}
	};
	
	public static boolean goalStatus = false;
	public static Board answerBoard;//the final answer that an algorithm has found, so we can backtrack the path
	
	public Board parentBoard;//helps with backtracking the path taken
	
	public int level = 0;
	
	public int[][] start_state;//state of board at the start
	public int[][] current_state;//state of board when changes are made
	
	
	
	public Board(int[][] board, int boardSizeX, int boardSizeY){//main constructor	
		Board.boardSizeX = boardSizeX;
		Board.boardSizeY = boardSizeY;
		start_state = new int[boardSizeY][boardSizeX];
		copyBoard(board);
		findBlankPos();
	}
	
	public Board(int[][] board){
		this(board, DEFAULT_BOARD_SIZEX,DEFAULT_BOARD_SIZEY);//calls main
	}
	
	
	private void checkGoalStatus(){
		if(misplacedTiles == 0){
			answerBoard = this;//backtrack from this path to find the right path
			goalStatus = true;
		}
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
	
	public void displayBoard(int board[][]){
	
		for(int y =0; y<boardSizeY;y++){	
			for(int x=0;x<boardSizeX;x++){
				System.out.print(board[y][x] +" ");
			}
			System.out.println();
		}
	}

	
	public int findMisplacedTiles(){//compares goal board with this board
		for(int y =0; y<boardSizeY;y++){	
			for(int x=0;x<boardSizeX;x++){
				if(GOAL_STATE[y][x] == start_state[y][x] || start_state[y][x] ==0){//if same or blank dont change anything

				}else{
					misplacedTiles++;
				}
			}
		}
		checkGoalStatus();//checks if this is the goal state after making the move
		return misplacedTiles;
	}	
	
	public int getHeuristic(String algorithm){
		
		algorithm.toLowerCase();
		switch(algorithm){
		
		case "astar heuristic":
			level = parentBoard.level + 1;//update the level
			heuristic = level + misplacedTiles;
			break;
			
		case "astar manhattan":
			level = parentBoard.level + 1;//update the level
			heuristic = level + findManhattanDistance(start_state, GOAL_STATE);
			break;
			
		case "astar iterative deepining":
			
			break;
		
		case "dfbnb":
		
			break;
			
		default: 
			System.out.println("Algorithm not found");
			
		}
		
		return heuristic;
	}

	
	public static void displayMisplacedBoard(String board[][]){
		
		for(int y =0; y<boardSizeY;y++){	
			for(int x=0;x<boardSizeX;x++){
				System.out.print(board[y][x] +" ");
			}
			System.out.println();
		}
	}
	

	public static int findManhattanDistance(int[][] easyBoard, int[][] GOAL_STATE){
		
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
					
					//System.out.println("EasyBoard Location of Number: " + easyBoard[y][x] + " X " + coordinate1[1] + " Y " + coordinate1[0]);
					//System.out.println("GoalBoard Location of Number: " + easyBoard[y][x] + " X " + coordinates[1] + " Y " + coordinates[0]);
				
					int verticalDistance = findDistance(x,XPositionInGoalState);
					int horizontalDistance = findDistance(y,YPositionInGoalState);
//					System.out.println("DISTANCE OF NUMBER: " + number);
//					System.out.println("Vertical Distance   = " + verticalDistance);
//					System.out.println("Horizontal Distance = " + horizontalDistance);
					
					totalDistance = totalDistance + (verticalDistance + horizontalDistance);
				
				}
				
			}
		}
		return totalDistance;
	}
	
	public static int findDistance(int position1, int position2){
		int distance = Math.abs(position1 - position2);
		return distance;	
	}
	
	public static int[] findNumber(int[][] board, int number){
		
		int[] coordinate = new int[2];
		
		for(int y=0;y<3;y++){
			for(int x=0;x<3;x++){
				if(board[y][x] == number){
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
	public int compareTo(Board otherBoard) {//override, used by binaryHeap to prioritize heuristic
		
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



