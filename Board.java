import java.util.HashMap;

/*
 */

public class Board extends HashMap<Integer, Board> implements Comparable<Board>{
	
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
	
	public int calculateHeuristic(String algorithm){//gets the Heuristic of the specified algorithm
		
		algorithm.toLowerCase();
		switch(algorithm){
		
		case "astar misplaced":
			level = parentBoard.level + 1;//update the level
			heuristic = level + misplacedTiles;
			break;
			
		case "astar manhattan":
			level = parentBoard.level + 1;//update the level
			System.out.println(findManhattanDistance(start_state, GOAL_STATE));
			heuristic = level + findManhattanDistance(start_state, GOAL_STATE);
			break;
			
		case "astar ida":
			level = parentBoard.level + 1;
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
					
					int verticalDistance = findDistance(x,XPositionInGoalState);
					int horizontalDistance = findDistance(y,YPositionInGoalState);;
					
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
	
	
	
	/*NEW way of getting hashKey Code
	 * This is hardCoded since we are only dealing with 3 x 3
	 * but can be easily changed into a loop, looks much cleaner/understandable this way
	 * 
	 * if board is 
	 *  {3,0,2},
	 *	{6,5,1},
	 *	{4,7,8}
	 *Then the ID would be 302651478
	 *that way no two Boards would have the same ID, unless they are the same board
	 */
	@Override
	public int hashCode(){
		
		int id1 = this.start_state[0][0];
		int id2 = this.start_state[0][1];
		int id3 = this.start_state[0][2];
		int id4 = this.start_state[1][0];
		int id5 = this.start_state[1][1];
		int id6 = this.start_state[1][2];
		int id7 = this.start_state[2][0];
		int id8 = this.start_state[2][1];
		int id9 = this.start_state[2][2];
		
		int id = Integer.valueOf(String.valueOf(id1) + 
								String.valueOf(id2) +
								String.valueOf(id3) + 
								String.valueOf(id4) +
								String.valueOf(id5) + 
								String.valueOf(id6) +
								String.valueOf(id7) + 
								String.valueOf(id8) +
								String.valueOf(id9));
		
		return id;
	}
	
	
	@Override
	public int compareTo(Board otherBoard) {//override, used by binaryHeap to prioritize lower heuristic
		
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



