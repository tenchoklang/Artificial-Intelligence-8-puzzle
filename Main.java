import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/* In a test environment
 * 
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
		
		Board board = new Board(worstBoard);
		board.parentBoard = null;//this is the root node
		//board.findMisplacedTiles();
		int totalNodesVisited = 0;
		
		
		BinaryHeap<Board> openList = new BinaryHeap<>();
		HashMap<String, Board> closedList = new HashMap<>();//hashmap closedList
		
		Queue<Board> fifo = new LinkedList<>();//queue to search by level


		String algorithmType = "astar misplaced";
		

		while(Board.goalStatus == false){//add a way to check if there is any other lower heuristic function
			/*
			 * Main if, checks whether the board is already in the closedList
			 */
			if(!closedList.containsKey(Arrays.deepToString(board.start_state))){//if there is no same board in hash then proceed
				
				closedList.put(Arrays.deepToString(board.start_state), board);//add the board that we are currently expanding to the closedList
				System.out.println("#############################");
				System.out.println("START STATE");
				board.displayBoard(board.start_state);
				System.out.println("#############################");
				
				if(Moves.verifyMove("up", board)){
//					System.out.println("Old Board START STATE");
//					board.displayBoard(board.start_state);
					Board newBoard = new Board(board.start_state);//board with the changes made
					Moves.move("up", newBoard);
					System.out.println("New Board 1 START STATE");
					newBoard.displayBoard(newBoard.start_state);
					System.out.println("Misplaced tiles = " + newBoard.findMisplacedTiles());
					newBoard.parentBoard = board;//set the old board as the parent of newBoard
					System.out.println(newBoard.calculateHeuristic(algorithmType));
					addTo(newBoard, openList, fifo, algorithmType);
					totalNodesVisited++;
				}
				
				//System.out.println("|||||||||||||||||||||||||||||");
				
				if(Moves.verifyMove("down", board)){
//					System.out.println("Old Board START STATE");
//					board.displayBoard(board.start_state);
					Board newBoard = new Board(board.start_state);//board with the changes made
					//newBoard.level = board.level +1;
					Moves.move("down", newBoard);
					System.out.println("New Board 3 START STATE");
					newBoard.displayBoard(newBoard.start_state);
					System.out.println("Misplaced tiles = " + newBoard.findMisplacedTiles());
					//newBoard.heuristic = newBoard.level + newBoard.misplacedTiles;
					newBoard.parentBoard = board;
					System.out.println(newBoard.calculateHeuristic(algorithmType));
					addTo(newBoard, openList, fifo, algorithmType);

										
					totalNodesVisited++;

				}
				//System.out.println("|||||||||||||||||||||||||||||");
				
				if(Moves.verifyMove("left", board)){
//					System.out.println("Old Board START STATE");
//					board.displayBoard(board.start_state);
					Board newBoard = new Board(board.start_state);//board with the changes made
					//newBoard.level = board.level +1;
					Moves.move("left", newBoard);
					System.out.println("New Board 3 START STATE");
					newBoard.displayBoard(newBoard.start_state);
					System.out.println("Misplaced tiles = " + newBoard.findMisplacedTiles());
					//newBoard.heuristic = newBoard.level + newBoard.misplacedTiles;

					newBoard.parentBoard = board;
					System.out.println("Total Heuristic = "+newBoard.calculateHeuristic(algorithmType));
					addTo(newBoard, openList, fifo, algorithmType);



					totalNodesVisited++;


				}
				//System.out.println("|||||||||||||||||||||||||||||");
				
				if(Moves.verifyMove("right", board)){
//					System.out.println("Old Board START STATE");
//					board.displayBoard(board.start_state);
					Board newBoard = new Board(board.start_state);//board with the changes made
					//newBoard.level = board.level +1;
					Moves.move("right", newBoard);
					System.out.println("New Board 3 START STATE");
					newBoard.displayBoard(newBoard.start_state);
					System.out.println("Misplaced tiles = " + newBoard.findMisplacedTiles());
					//newBoard.heuristic = newBoard.level + newBoard.misplacedTiles;
					newBoard.parentBoard = board;
					System.out.println("Total Heuristic = "+newBoard.calculateHeuristic(algorithmType));
					addTo(newBoard, openList, fifo, algorithmType);



					totalNodesVisited++;

				}
			}
			
			board = removeFrom(board, openList, fifo, algorithmType);
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
	 * Adds the board to its appropriate data structure 
	 * If it is astar heuristic(Misplacement) or astar manhattan 
	 * it is added to priority Queue because lower the priority the better
	 * 
	 * But if it is iterative deepening search then it is added to a FIFO queue
	 * where it searches by the depth/level
	 */
	public static void addTo(Board board, BinaryHeap<Board> openList, Queue<Board> fifo, String algorithm){
		algorithm.toLowerCase();
		
		if(algorithm == "astar misplaced" || algorithm == "astar manhattan"){
			openList.insert(board);
		}
		if(algorithm == "astar ida"){
			fifo.add(board);
		}
	}
	
	public static Board removeFrom(Board board, BinaryHeap<Board> openList, Queue<Board> fifo, String algorithm){
		algorithm.toLowerCase();
		
		if(algorithm == "astar misplaced" || algorithm == "astar manhattan" && !openList.isEmpty()){
			 return openList.deleteMinimum();
		}
		if(algorithm == "astar ida"){
			Board depthBoard = fifo.remove();
			return depthBoard;
		}
		
		return board;
	}

}
