import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

/* In a test environment
 * 
 */
public class Algorithm {

	private static String algorithmName;
	private static int costBound;//used by iterative deepening A*
		
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
	LinkedList<int[][]> puzzleBoard = new LinkedList<>();//to store the four puzzles
	
	/*
	 * DATA STRUCTURES
	 */
	private static PriorityQueue<Board> openList = new PriorityQueue<>();
	private static HashMap<String, Board> closedList = new HashMap<>();
	private static Queue<Board> fifo = new LinkedList<>();//queue to search by level
	private static Stack<Board> lifo = new Stack<>();
	
	
	public Algorithm(String algorithmName){
		Algorithm.algorithmName = algorithmName;
		puzzleBoard.add(easyBoard);
		puzzleBoard.add(mediumBoard);
		puzzleBoard.add(hardBoard);
		puzzleBoard.add(worstBoard);
	}
		
	public void init(){
	
		int puzzleBoardDifficulty = 0;
		
		while(puzzleBoardDifficulty < puzzleBoard.size()){
			
			long startTime = System.currentTimeMillis();		
			
			Board board = new Board(puzzleBoard.get(puzzleBoardDifficulty));
			Board.findMisplacedTiles(board, 0, 0, 0);
			costBound = Board.findManhattanDistance(puzzleBoard.get(puzzleBoardDifficulty));
			//System.out.println("Starting cost Bound: " + costBound);
			int totalNodesVisited = 0;
			
			while(Board.goalStatus == false){
				//System.out.println("Parent Board");
				//System.out.println(board.toString());
				if(!closedList.containsKey(Arrays.deepToString(board.start_state))){//if there is no same board in hash then proceed
					
					closedList.put(Arrays.deepToString(board.start_state), board);//add the board that we are currently expanding to the closedList
					
					if(Moves.verifyMove("up", board)){
						newBoard(board, "up",openList);
						totalNodesVisited++;
					}
					
					if(Moves.verifyMove("down", board)){
						newBoard(board, "down",openList);
						totalNodesVisited++;
					}
					
					if(Moves.verifyMove("left", board)){
						newBoard(board, "left",openList);
						totalNodesVisited++;
					}
					
					if(Moves.verifyMove("right", board)){
						newBoard(board, "right",openList);
						totalNodesVisited++;
					}
				}
				if(openList.isEmpty() && fifo.isEmpty() && lifo.isEmpty()){
					System.out.println("End of Program");
					break;
				}else{
					board = removeFrom(board, algorithmName);
				}
				//System.out.println("BOARD LEVEL " + board.level);
				//System.out.println("PICKED BOUND: " + costBound);
//				System.out.println("QUEUE SIZE: " + fifo.size());
				//System.out.println("-------------------------------");	
			}
			
			System.out.println("ANSWER BOARD");
			Board currentState = Board.answerBoard;
			int nodesVisited = 0;
			while(currentState != null){//displays the path from start state to goal state
				System.out.println(currentState.toString());
				currentState  = currentState.parentBoard;
				nodesVisited++;
			}
			
			System.out.println("Optimal Solution Nodes: "+(nodesVisited-1));
			//System.out.println("Total Nodes Visited: "+totalNodesVisited);//6760 = 6.7s
			System.out.println("Total Nodes Expanded: "+closedList.size());//6760 = 6.7s
			System.out.println();
			long endTime   = System.currentTimeMillis();
			long totalTime = endTime - startTime;
			System.out.println("Total Time: "+totalTime + " miliseconds");
			
			openList.clear();//clear the data structures for the next puzzle board
			closedList.clear();
			fifo.clear();
			lifo.clear();
			Board.goalStatus = false;//set board to false for next algorithm
			puzzleBoardDifficulty++;
		}
	}
	
	public static void newBoard(Board board, String direction, PriorityQueue<Board> openList){
		Board newBoard = new Board(board.start_state);
		Moves.move(direction, newBoard);
		newBoard.parentBoard = board;
		newBoard.calculateHeuristic(algorithmName);
		addTo(newBoard,algorithmName);
		//System.out.println("Blank Position " + newBoard.blankPosY + " " + newBoard.blankPosX);
		//System.out.println(newBoard.toString());
	}
	public static void addTo(Board board, String algorithm){
		algorithm.toLowerCase();
		//System.out.println("BOARD costBound: " + board.manhattanDistance);
		if(algorithm == "astar misplaced" || algorithm == "astar manhattan"){
			openList.add(board);
		}
		if(algorithm == "astar ida"){
			if(board.heuristic <= costBound + 1){//set it to 23 to get a smaller result
				fifo.add(board);
			}
		}
		if(algorithm == "dfbnb"){
			if(board.manhattanDistance <= costBound){
				costBound = board.manhattanDistance;
			}
			lifo.push(board);
		}
	}

	public static Board removeFrom(Board board, String algorithm){
		algorithm.toLowerCase();
		
		if(algorithm == "astar misplaced" || algorithm == "astar manhattan" && !openList.isEmpty()){
			 return openList.remove();
		}
		if(algorithm == "astar ida"){
			costBound = fifo.peek().heuristic;
			return fifo.remove();
		}
		if(algorithm == "dfbnb"){
			while(true){
				if(lifo.peek().manhattanDistance == costBound){
					Board returnBoard = lifo.pop();
					lifo.clear();
					return returnBoard;
				}
				else{
					if(lifo.size() ==1){//if its the last element in the stack then pop
						return lifo.pop();
					}
					lifo.pop(); //keep popping those who have lower costBounds
				}
			}
		}
		
		return null;
	}
}

//while(true){
//	if(lifo.peek().manhattanDistance <= costBound+1){
//		if(lifo.peek().manhattanDistance<= costBound+1){
//			costBound = lifo.peek().manhattanDistance;
//		}
//		board = lifo.pop();
//		break;
//	}else{
//		if(lifo.size() ==1){
//			board = lifo.pop();
//			break;
//		}
//		lifo.pop();
//	}
//}
//return board;
