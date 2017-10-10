import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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


public class Main extends HashMap<Integer, Board> {

	public static void main(String[] args){
		
		
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

		int[][] testBoard3 = new int[][]{
			{3,0,2},
			{6,5,1},
			{4,7,8}
		};
		
		System.out.println("TEST");



		Board board = new Board(testBoard);
		board.parentBoard = null;
		board.findMisplacedTiles();
		
		Board board2 = new Board(testBoard2);
		Board board3 = new Board(testBoard3);


		/*
		 * to add to the closed list first we need to get it into <List<List<Integer>> form which will 
		 * act as a 2d - array, we can't use 2d - array directly because it is not identifiable as a key
		 * closedList.put(Collections.unmodifiableList(testList), board);<-- key solution found on stack overflow
		 */
		HashMap<Integer, Board> closedList = new HashMap<>();

		closedList.put(Arrays.deepHashCode(board.start_state), board);
		System.out.println(Arrays.deepHashCode(board.start_state));
		closedList.put(Arrays.deepHashCode(board2.start_state), board2);
		System.out.println(Arrays.deepHashCode(board2.start_state));
		
		
		System.out.println(closedList.containsKey(Arrays.deepHashCode(board3.start_state)));
				
		
		
	}
//	/*NEW way of getting hashKey Code
//	 * This is hardCoded since we are only dealing with 3 x 3
//	 * but can be easily changed into a loop, looks much cleaner/understandable this way
//	 * 
//	 * if board is 
//	 *  {3,0,2},
//	 *	{6,5,1},
//	 *	{4,7,8}
//	 *Then the ID would be 302651478
//	 *that way no two Boards would have the same ID, unless they are the same board
//	 */
//	public static int getID(Board board){
//
//		int id1 = board.current_state[0][0];
//		int id2 = board.current_state[0][1];
//		int id3 = board.current_state[0][2];
//		int id4 = board.current_state[1][0];
//		int id5 = board.current_state[1][1];
//		int id6 = board.current_state[1][2];
//		int id7 = board.current_state[2][0];
//		int id8 = board.current_state[2][1];
//		int id9 = board.current_state[2][2];
//		
//		int id = Integer.valueOf(String.valueOf(id1) + 
//								String.valueOf(id2) +
//								String.valueOf(id3) + 
//								String.valueOf(id4) +
//								String.valueOf(id5) + 
//								String.valueOf(id6) +
//								String.valueOf(id7) + 
//								String.valueOf(id8) +
//								String.valueOf(id9));
//		
//		return id;
//	}
//	
	/*OLD way of having unique HashKey code
	 * public static void addToClosedList(Board board,  HashMap<List<List<Integer>>, Board> closedList){
		
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
	
	
	 // Check if a specific board is in the closed list
	 //return true if it is
	
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
	 */
	
	

}
