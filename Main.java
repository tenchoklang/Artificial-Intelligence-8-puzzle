import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/*
 * Implement hash map to detect duplicate states
 * ---unsure of what the key to the hash map would be...
 * 
 * Add hashKey as unmodifiable list
 */


public class Main {

	public static void main(String[] args) {
		
		
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


		/*
		 * to add to the closed list first we need to get it into <List<List<Integer>> form which will 
		 * act as a 2d - array, we can't use 2d - array directly because it is not identifiable as a key
		 * closedList.put(Collections.unmodifiableList(testList), board);<-- key solution found on stack overflow
		 */
		HashMap<List<List<Integer>>, Board> closedList = new HashMap<>();
		
		
		addToClosedList(board, closedList);
		
		addToClosedList(board, closedList);//this will not be added because this board already is in hash
		
		
		/*
		 * board and board2 are different because they do not reference the same 2d array
		 * but now that we set List<List<Integer>> and set it as unmodifiable, as the hashKey
		 * we can now check if a board is in the hash by solely comparing the value of the board 
		 */
		System.out.println(checkClosedList(board2, closedList));
		
		
		
		
		
	}
	
	public static void addToClosedList(Board board,  HashMap<List<List<Integer>>, Board> closedList){
		
		if(!checkClosedList(board, closedList)){
			int arrayAsList[] = new int[3];
			List<List<Integer>> list = new ArrayList<>();
			
			for(int y =0; y<Board.boardSizeY;y++){	
				for(int x=0;x<Board.boardSizeY;x++){
					arrayAsList[x] = board.start_state[y][x];
				}
				list.add(Arrays.asList(arrayAsList[0],arrayAsList[1],arrayAsList[2]));
			}
			closedList.put(Collections.unmodifiableList(list), board);//list now can be used as a key in the hashMap
			System.out.println("Added");
		}else{
			System.out.println("There is already the same board added");
		}
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
		return closedList.containsKey(list);//returns true of there is already the same board in hash
	}


}
