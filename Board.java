import java.util.Arrays;
import java.util.HashMap;

public class Board extends HashMap<Integer,Board> implements Comparable<Board>{
	
	public static final int DEFAULT_BOARD_SIZEX = 3;
	public static final int DEFAULT_BOARD_SIZEY = 3;
	
	public static int boardSizeX;
	public static int boardSizeY;
	
	private int[][] board;
	
	
	public String[][] misplacedBoard;
	public int misplacedTiles =0;
	
	public int manhattanDistance = 0;
	
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
	
	public Board parentBoard;
	
	public int level = 0;
	
	public int[][] start_state;//state of board at the start
	public int[][] current_state;//state of board when changes are made
	
	
	
	public Board(int[][] board, int boardSizeX, int boardSizeY){//main constructor	
		Board.boardSizeX = boardSizeX;
		Board.boardSizeY = boardSizeY;
		this.board = board;
		misplacedBoard = new String[boardSizeY][boardSizeX];
		start_state = new int[boardSizeY][boardSizeX];
		current_state = new int[boardSizeY][boardSizeX];
		initializeBoard(board);
	}
	
	public Board(int[][] board){
		this(board, DEFAULT_BOARD_SIZEX,DEFAULT_BOARD_SIZEY);//calls main
	}
	
	
	private void initializeBoard(int[][] board){
		if(board.length != boardSizeY || board[0].length != boardSizeX){//board size required if not default
			throw new IllegalArgumentException("Size of the board is required");
		}
		copyBoards();
		findBlankPos();		
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
	
	private void copyBoards(){//copy by value
		for(int y=0;y<boardSizeY;y++){
			for(int x=0;x<boardSizeX;x++){
				start_state[y][x] = board[y][x];
				current_state[y][x] = board[y][x];
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
					int value = start_state[y][x];
					misplacedBoard[y][x] = Integer.toString(value);
				}else{
					misplacedBoard[y][x] = "X";
					misplacedTiles++;
				}
			}
		}
		checkGoalStatus();
		return misplacedTiles;
	}	
	

//	public int[][] getBoard() {
//		return board;
//	}
//
//	public void setBoard(int[][] board) {
//		this.board = board;
//	}
//
//	public static int[][] getGoalState() {
//		return GOAL_STATE;
//	}
//	
	
	public static void displayMisplacedBoard(String board[][]){
		
		for(int y =0; y<boardSizeY;y++){	
			for(int x=0;x<boardSizeX;x++){
				System.out.print(board[y][x] +" ");
			}
			System.out.println();
		}
	}
	
	/*ex
	 * if board is 
	 *  {3,0,2},
	 *	{6,5,1},
	 *	{4,7,8}
	 *Then the HashCode would be 302651478
	 *that way no two Boards would have the same ID, unless they are the same board
	 */
	
	
	@Override
	public int compareTo(Board otherBoard) {//override, used by binaryHeap to prioritize
		
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


