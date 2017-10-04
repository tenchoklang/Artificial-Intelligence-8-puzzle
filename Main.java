import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

/* In a test environment
 * A Little bit of refractoring and implementing Manhattan distance heuristic
 */


public class Main {

	public static void main(String[] args) {
		
		long startTime = System.currentTimeMillis();		
		
		
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
			{1,2,3},
			{8,0,4},
			{7,6,5}
		};
		
		Board board = new Board(easyBoard);
		board.parentBoard = null;//this is the root node
		//board.findMisplacedTiles();
		int totalNodesVisited = 0;

		BinaryHeap<Board> openList = new BinaryHeap<>();
		
		//PriorityQueue<Board> openList = new PriorityQueue<>();
		//BinaryHeap<Board> openList = new BinaryHeap<Board>();//priorityQueue openList
		HashMap< List<List<Integer>>, Board> closedList = new HashMap<>();//hashmap closedList

		String algorithmType = "astar manhattan";

		while(Board.goalStatus == false){//add a way to check if there is any other lower heuristic function
			/*
			 * Main if, checks whether the board is already in the closedList
			 */
			if(!checkClosedList(board,closedList)){
				
				addToClosedList(board, closedList);//add the board that we are currently expanding to the closedList
				System.out.println("|||||||||||||||||||||||||||||");
				
				if(Moves.verifyMove("up", board)){
					System.out.println("Old Board START STATE");
					board.displayBoard(board.start_state);
					Board newBoard = new Board(board.start_state);//board with the changes made
					Moves.move("up", newBoard);
					System.out.println("New Board 1 START STATE");
					newBoard.displayBoard(newBoard.start_state);
					System.out.println("Misplaced tiles = " + newBoard.findMisplacedTiles());
					newBoard.parentBoard = board;//set the old board as the parent of newBoard
					System.out.println(newBoard.getHeuristic(algorithmType));
					openList.insert(newBoard);					
					totalNodesVisited++;
				}
				
				System.out.println("|||||||||||||||||||||||||||||");
				
				if(Moves.verifyMove("down", board)){
					System.out.println("Old Board START STATE");
					board.displayBoard(board.start_state);
					Board newBoard = new Board(board.start_state);//board with the changes made
					//newBoard.level = board.level +1;
					Moves.move("down", newBoard);
					System.out.println("New Board 3 START STATE");
					newBoard.displayBoard(newBoard.start_state);
					System.out.println("Misplaced tiles = " + newBoard.findMisplacedTiles());
					//newBoard.heuristic = newBoard.level + newBoard.misplacedTiles;
					newBoard.parentBoard = board;
					System.out.println(newBoard.getHeuristic(algorithmType));
					openList.insert(newBoard);
										
					totalNodesVisited++;

				}
				System.out.println("|||||||||||||||||||||||||||||");
				
				if(Moves.verifyMove("left", board)){
					System.out.println("Old Board START STATE");
					board.displayBoard(board.start_state);
					Board newBoard = new Board(board.start_state);//board with the changes made
					//newBoard.level = board.level +1;
					Moves.move("left", newBoard);
					System.out.println("New Board 3 START STATE");
					newBoard.displayBoard(newBoard.start_state);
					System.out.println("Misplaced tiles = " + newBoard.findMisplacedTiles());
					//newBoard.heuristic = newBoard.level + newBoard.misplacedTiles;

					newBoard.parentBoard = board;
					System.out.println(newBoard.getHeuristic(algorithmType));
					openList.insert(newBoard);


					totalNodesVisited++;


				}
				System.out.println("|||||||||||||||||||||||||||||");
				
				if(Moves.verifyMove("right", board)){
					System.out.println("Old Board START STATE");
					board.displayBoard(board.start_state);
					Board newBoard = new Board(board.start_state);//board with the changes made
					//newBoard.level = board.level +1;
					Moves.move("right", newBoard);
					System.out.println("New Board 3 START STATE");
					newBoard.displayBoard(newBoard.start_state);
					System.out.println("Misplaced tiles = " + newBoard.findMisplacedTiles());
					//newBoard.heuristic = newBoard.level + newBoard.misplacedTiles;
					newBoard.parentBoard = board;
					System.out.println(newBoard.getHeuristic(algorithmType));
					openList.insert(newBoard);


					totalNodesVisited++;

				}
				System.out.println("|||||||||||||||||||||||||||||");
			}
			
			board = openList.deleteMinimum();
			board.findBlankPos();
			int boardLevel = board.level;
			System.out.println("BOARD LEVEL " + boardLevel);
			
			System.out.println("--------------------------------");
		}

		
		
		System.out.println("ANSWER BOARD");
		
		Board currentState = Board.answerBoard;
		int nodesVisited = 0;
		//displays the path from start state to goal state
		while(currentState != null){
			currentState.displayBoard(currentState.start_state);
			currentState  = currentState.parentBoard;
			nodesVisited++;
			System.out.println();
		}
		nodesVisited--;
		
		System.out.println("Optimal Solution Nodes: "+nodesVisited);
		
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("Total Time: "+totalTime);//6760 = 6.7s
		System.out.println("Total Nodes Visited: "+totalNodesVisited);//6760 = 6.7s
		System.out.println("Total Nodes Expanded: "+closedList.size());//6760 = 6.7s
		
	}	
	
	
	
	
	
	
	/*
	 * Adds to the closed list
	 * But first it needs to convert 2d-Array to a 2d-ArrayList so we can use it as a key
	 */
	public static void addToClosedList(Board board,  HashMap<List<List<Integer>>, Board> closedList){
		
		if(!checkClosedList(board, closedList)){
			int arrayAsList[] = new int[3];
			List<List<Integer>> list = new ArrayList<>();
			
			for(int y =0; y<Board.boardSizeY;y++){	
				for(int x=0;x<Board.boardSizeY;x++){
					arrayAsList[x] = board.start_state[y][x];//store this into arrayAsList so later we can do Arrays.asList()
				}
				list.add(Arrays.asList(arrayAsList[0],arrayAsList[1],arrayAsList[2]));
			}
			closedList.put(Collections.unmodifiableList(list), board);//list now can be used as a key in the hashMap
		}else{
			System.out.println("{{{{{{{There is already the same board added}}}}}}}");
		}
	}
	
	/*
	 * Check if a specific board is in the closed list
	 * return true if it is
	 */
	public static boolean checkClosedList(Board board, HashMap<List<List<Integer>>, Board> closedList){
		int arrayAsList[] = new int[3];//so we can do Arrays.asList(...) with the array as one of the rows of the board
		List<List<Integer>> list = new ArrayList<>();
		
		for(int y =0; y<Board.boardSizeY;y++){	
			for(int x=0;x<Board.boardSizeY;x++){
				arrayAsList[x] = board.start_state[y][x];
			}
			list.add(Arrays.asList(arrayAsList[0],arrayAsList[1],arrayAsList[2]));//adds the array value to the list
		}
		return closedList.containsKey(list);//returns true of there is already the same board in hash
	}

}
