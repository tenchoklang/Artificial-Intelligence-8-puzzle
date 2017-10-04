import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/*
 * Implement hash map to detect duplicate states
 * ---unsure of what the key to the hash map would be...abandoned that idea
 * 
 * Implement Linked Links to detect duplicate states
 */


public class Main {

	public static void main(String[] args) {
		
		int[][] easyBoard = new int[][]{
			{1,3,4},
			{8,6,2},
			{7,0,5}
		};
		
		int[][] mediumBoard = new int[][]{
			{2,8,1},
			{0,4,3},
			{7,6,5}
		};
		
		int[][] hardBoard = new int[][]{
			{2,8,1},
			{4,6,3},
			{0,7,5}
		};
		
		int[][] worstBoard = new int[][]{
			{5,6,7},
			{4,0,8},
			{3,2,1}
		};
		
		
		int[][] testBoard = new int[][]{
			{3,0,2},
			{6,5,1},
			{4,7,8}
		};
		
		int[][] testBoard2 = new int[][]{
			{3,0,2},
			{6,5,1},
			{4,7,8}
		};




		Board board = new Board(testBoard);
		board.parentBoard = null;
		board.findMisplacedTiles();
		Board board2 = new Board(testBoard2);

		BinaryHeap<Board> openList = new BinaryHeap<Board>();
		
		List<List<Integer>> testList = new ArrayList<>();
		
		testList.add(Arrays.asList(5,6,7));
		testList.add(Arrays.asList(4,0,8));
		testList.add(Arrays.asList(3,2,1));

		/*
		 * to add to the closed list first we need to get it into <List<List<Integer>> form which will 
		 * act as a 2d - array, we can't use 2d - array directly because it is not identifiable as a key
		 * closedList.put(Collections.unmodifiableList(testList), board);<-- key solution found on stack overflow
		 */
		HashMap<List<List<Integer>>, Board> closedList = new HashMap<>();
		
		
		addToClosedList(board, closedList);
		
		System.out.println(checkClosedList(board2, closedList));
		
		
//		closedList.put(Collections.unmodifiableList(testList), board);
//		
//		List<List<Integer>> checkList = new ArrayList<>();
//		
//		checkList.add(Arrays.asList(5,6,7));
//		checkList.add(Arrays.asList(4,0,8));
//		checkList.add(Arrays.asList(3,2,1));
//		
//		closedList.get(checkList).displayBoard(closedList.get(checkList).start_state);
//		
//		addToClosedList(board, closedList);
		
		
		
//		while(Board.goalStatus == false){//add a way to check if there is any other lower heuristic function
//			
//				System.out.println("|||||||||||||||||||||||||||||");
//				if(Moves.verifyMove("up", board)){
//					System.out.println("Old Board START STATE");
//					board.displayBoard(board.start_state);
//					Board newBoard = new Board(board.start_state);//board with the changes made
//					newBoard.level = board.level +1;//board level = parent board level + 1
//					Moves.move("up", newBoard);
//					System.out.println("New Board 1 START STATE");
//					newBoard.displayBoard(newBoard.start_state);
//					System.out.println("Misplaced tiles = " + newBoard.findMisplacedTiles());
//					newBoard.heuristic = newBoard.level + newBoard.misplacedTiles;
//					System.out.println("Heuristic = "+newBoard.heuristic);
//
//					Board.displayMisplacedBoard(newBoard.misplacedBoard);				
//					openList.insert(newBoard);
//					
//					newBoard.parentBoard = board;
//		
//				}
//				System.out.println("|||||||||||||||||||||||||||||");
//				
//				if(Moves.verifyMove("down", board)){
//					System.out.println("Old Board START STATE");
//					board.displayBoard(board.start_state);
//					Board newBoard = new Board(board.start_state);//board with the changes made
//					newBoard.level = board.level +1;
//					Moves.move("down", newBoard);
//					System.out.println("New Board 3 START STATE");
//					newBoard.displayBoard(newBoard.start_state);
//					System.out.println("Misplaced tiles = " + newBoard.findMisplacedTiles());
//					newBoard.heuristic = newBoard.level + newBoard.misplacedTiles;
//					System.out.println("Heuristic = "+newBoard.heuristic);
//
//					Board.displayMisplacedBoard(newBoard.misplacedBoard);
//					openList.insert(newBoard);
//					
//					newBoard.parentBoard = board;
//
//				}
//				System.out.println("|||||||||||||||||||||||||||||");
//				
//				if(Moves.verifyMove("left", board)){
//					System.out.println("Old Board START STATE");
//					board.displayBoard(board.start_state);
//					Board newBoard = new Board(board.start_state);//board with the changes made
//					newBoard.level = board.level +1;
//					Moves.move("left", newBoard);
//					System.out.println("New Board 3 START STATE");
//					newBoard.displayBoard(newBoard.start_state);
//					System.out.println("Misplaced tiles = " + newBoard.findMisplacedTiles());
//					newBoard.heuristic = newBoard.level + newBoard.misplacedTiles;
//					System.out.println("Heuristic = "+newBoard.heuristic);
//
//					Board.displayMisplacedBoard(newBoard.misplacedBoard);
//					openList.insert(newBoard);
//					
//					newBoard.parentBoard = board;
//
//				}
//				System.out.println("|||||||||||||||||||||||||||||");
//				
//				if(Moves.verifyMove("right", board)){
//					System.out.println("Old Board START STATE");
//					board.displayBoard(board.start_state);
//					Board newBoard = new Board(board.start_state);//board with the changes made
//					newBoard.level = board.level +1;
//					Moves.move("right", newBoard);
//					System.out.println("New Board 3 START STATE");
//					newBoard.displayBoard(newBoard.start_state);
//					System.out.println("Misplaced tiles = " + newBoard.findMisplacedTiles());
//					newBoard.heuristic = newBoard.level + newBoard.misplacedTiles;
//					System.out.println("Heuristic = "+newBoard.heuristic);
//					Board.displayMisplacedBoard(newBoard.misplacedBoard);
//					openList.insert(newBoard);
//					
//					newBoard.parentBoard = board;
//
//				}
//				System.out.println("|||||||||||||||||||||||||||||");
//			
//			
//			int boardLevel = board.level +1;
//			System.out.println("BOARD LEVEL " + boardLevel);
//			closedList.add(board.start_state);
//			board = openList.deleteMinimum();
//			board.findBlankPos();
//			ctr++;
//			System.out.println("--------------------------------");
//		}
//
//		
//		
//		System.out.println("ANSWER BOARD");
//		
//		Board currentState = Board.answerBoard;
//		//displays the path from start state to goal state
//		int nodesVisited = 0;
//		while(currentState != null){
//			currentState.displayBoard(currentState.start_state);
//			currentState  = currentState.parentBoard;
//			nodesVisited++;
//			System.out.println();
//		}
//		nodesVisited--;
//		System.out.println(nodesVisited);
		
		
		
		
		
	}
	
	public static void addToClosedList(Board board,  HashMap<List<List<Integer>>, Board> closedList){
		
		int arrayAsList[] = new int[3];
		List<List<Integer>> list = new ArrayList<>();
		
		for(int y =0; y<Board.boardSizeY;y++){	
			for(int x=0;x<Board.boardSizeY;x++){
				arrayAsList[x] = board.start_state[y][x];
			}
			list.add(Arrays.asList(arrayAsList[0],arrayAsList[1],arrayAsList[2]));
		}
		
		closedList.put(Collections.unmodifiableList(list), board);//list now can be used as a key in the hashMap
		
	}
	
	public static boolean checkClosedList(Board board, HashMap<List<List<Integer>>, Board> closedList){
		int arrayAsList[] = new int[3];//so we can do Arrays.asList(...) with the array as one of the rows
		List<List<Integer>> list = new ArrayList<>();
		
		for(int y =0; y<Board.boardSizeY;y++){	
			for(int x=0;x<Board.boardSizeY;x++){
				arrayAsList[x] = board.start_state[y][x];
			}
			list.add(Arrays.asList(arrayAsList[0],arrayAsList[1],arrayAsList[2]));
		}
		
		return closedList.containsKey(list);
	}
}
