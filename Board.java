public class Board {
	
	public static final int DEFAULT_BOARD_SIZEX = 3;
	public static final int DEFAULT_BOARD_SIZEY = 3;
	
	private static int boardSizeX;
	private static int boardSizeY;
	
	private int[][] board;
	
	public String[][] misplacedBoard = new String[10][10];
	
	public int misplacedTiles =0;
	
	public int blankPosX;
	public int blankPosY;

	
	private static final int[][] GOAL_STATE = new int[][]{
		{1,2,3},
		{8,0,4},
		{7,6,5}
	};
	public int[][] start_state;//state of board at the start
	public int[][] current_state;//state of board when changes are made
	
	
	
	public Board(int[][] board, int boardSizeX, int boardSizeY){//main constructor	
		Board.boardSizeX = boardSizeX;
		Board.boardSizeY = boardSizeY;
		this.board = board;
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
	
	private void findBlankPos(){//find blank position
		for(int y =0; y<boardSizeY;y++){	
			for(int x=0;x<boardSizeX;x++){
				if(board[y][x] == 0){
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

	
	public int compareBoards(){
		for(int y =0; y<boardSizeY;y++){	
			for(int x=0;x<boardSizeX;x++){
				if(GOAL_STATE[y][x] == start_state[y][x] || start_state[y][x] ==0){
					int value = start_state[y][x];
					misplacedBoard[y][x] = Integer.toString(value);
				}else{
					misplacedBoard[y][x] = "X";
					misplacedTiles++;
				}
			}
		}
		return misplacedTiles;
	}	
	

	public int[][] getBoard() {
		return board;
	}

	public void setBoard(int[][] board) {
		this.board = board;
	}

	public static int[][] getGoalState() {
		return GOAL_STATE;
	}
	
	
	public static void displayMisplacedBoard(String board[][]){
		
		for(int y =0; y<boardSizeY;y++){	
			for(int x=0;x<boardSizeX;x++){
				System.out.print(board[y][x] +" ");
			}
			System.out.println();
		}
	}
	
}


//real
//public int compareBoards(){
//	for(int x =0; x<boardSizeX;x++){	
//		for(int y=0;y<boardSizeY;y++){
//			if(GOAL_STATE[x][y] == board[x][y] || board[x][y] ==0){
//				int value = board[x][y];
//				misplacedBoard[x][y] = Integer.toString(value);
//			}else{
//				misplacedBoard[x][y] = "X";
//				misplacedTiles++;
//			}
//		}
//	}
//	return misplacedTiles;
//}

////real method
//public void displayBoard(int board[][]){
//	
//	for(int y =0; y<boardSizeX;y++){	
//		for(int x=0;x<boardSizeY;x++){
//			System.out.print(board[y][x] +" ");
//		}
//		System.out.println();
//	}
//}


