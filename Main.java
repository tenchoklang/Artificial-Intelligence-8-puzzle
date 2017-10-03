/*
 * Able to solve easy, medium and hard board
 * Worst case board not being solved, possibility of it looping the same path again and again
 */



public class Main {

	public static void main(String[] args) {
		
		int[][] board = new int[][]{
			{2,8,1},
			{4,6,3},
			{0,7,5}
		};

		Board easyBoard = new Board(board);
		easyBoard.compareBoards();
		

//		System.out.println("BOARD goal state");
//		easyBoard.displayBoard(Board.getGoalState());
//		System.out.println();
//		
//		System.out.println("BOARD start state");
//		easyBoard.displayBoard(easyBoard.start_state);
//		System.out.println("Blank Tile AT X:"+ easyBoard.blankPosX + " Y:"+ easyBoard.blankPosY);
//		System.out.println();
//		
//		Moves.verifyMove("up", easyBoard);
//		Moves.verifyMove("down", easyBoard);
//		Moves.verifyMove("left", easyBoard);
//		Moves.verifyMove("right", easyBoard);
		
//		System.out.println("Misplaced tiles = " + easyBoard.compareBoards());
//		Board.displayMisplacedBoard(easyBoard.misplacedBoard);
//		System.out.println();
//		BinaryHeap<Board> priorityQueue = new BinaryHeap<Board>();
//		//priorityQueue.insert(easyBoard);
//
//		System.out.println("|||||||||||||||||||||||||||||");
//		if(Moves.verifyMove("up", easyBoard)){
//			System.out.println("Old Board START STATE");
//			easyBoard.displayBoard(easyBoard.start_state);
//			Board newBoard1 = new Board(easyBoard.current_state);//board with the changes made
//			Moves.move("up", newBoard1);
//			System.out.println("New Board 1 START STATE");
//			newBoard1.displayBoard(newBoard1.start_state);
//			System.out.println("Misplaced tiles = " + newBoard1.compareBoards());
//			Board.displayMisplacedBoard(newBoard1.misplacedBoard);
//			
//			newBoard1.heuristic = 1+ newBoard1.misplacedTiles;
//			priorityQueue.insert(newBoard1);
//
//		}
//		System.out.println("|||||||||||||||||||||||||||||");
//		
//		if(Moves.verifyMove("down", easyBoard)){
//			System.out.println("Old Board START STATE");
//			easyBoard.displayBoard(easyBoard.start_state);
//			Board newBoard2 = new Board(easyBoard.current_state);//board with the changes made
//			Moves.move("down", newBoard2);
//			System.out.println("New Board 3 START STATE");
//			newBoard2.displayBoard(newBoard2.start_state);
//			System.out.println("Misplaced tiles = " + newBoard2.compareBoards());
//			Board.displayMisplacedBoard(newBoard2.misplacedBoard);
//			
//			newBoard2.heuristic = 1+ newBoard2.misplacedTiles;
//			priorityQueue.insert(newBoard2);
//		}
//		System.out.println("|||||||||||||||||||||||||||||");
//		
//		if(Moves.verifyMove("left", easyBoard)){
//			System.out.println("Old Board START STATE");
//			easyBoard.displayBoard(easyBoard.start_state);
//			Board newBoard3 = new Board(easyBoard.current_state);//board with the changes made
//			Moves.move("left", newBoard3);
//			System.out.println("New Board 3 START STATE");
//			newBoard3.displayBoard(newBoard3.start_state);
//			System.out.println("Misplaced tiles = " + newBoard3.compareBoards());
//			Board.displayMisplacedBoard(newBoard3.misplacedBoard);
//			
//			newBoard3.heuristic = 1+ newBoard3.misplacedTiles;
//			priorityQueue.insert(newBoard3);
//		}
//		System.out.println("|||||||||||||||||||||||||||||");
//		
//		if(Moves.verifyMove("right", easyBoard)){
//			System.out.println("Old Board START STATE");
//			easyBoard.displayBoard(easyBoard.start_state);
//			Board newBoard4 = new Board(easyBoard.current_state);//board with the changes made
//			Moves.move("right", newBoard4);
//			System.out.println("New Board 3 START STATE");
//			newBoard4.displayBoard(newBoard4.start_state);
//			System.out.println("Misplaced tiles = " + newBoard4.compareBoards());
//			Board.displayMisplacedBoard(newBoard4.misplacedBoard);
//			
//			newBoard4.heuristic = 1+ newBoard4.misplacedTiles;
//			priorityQueue.insert(newBoard4);
//		}
//		System.out.println("|||||||||||||||||||||||||||||");


		
		
		BinaryHeap<Board> priorityQueue = new BinaryHeap<Board>();
		
		while(Board.goalStatus == false){//add a way to check if there is any other lower heuristic function
			System.out.println("|||||||||||||||||||||||||||||");
			if(Moves.verifyMove("up", easyBoard)){
				System.out.println("Old Board START STATE");
				easyBoard.displayBoard(easyBoard.start_state);
				Board newBoard = new Board(easyBoard.start_state);//board with the changes made
				newBoard.level = easyBoard.level +1;
				Moves.move("up", newBoard);
				System.out.println("New Board 1 START STATE");
				newBoard.displayBoard(newBoard.start_state);
				System.out.println("Misplaced tiles = " + newBoard.compareBoards());
				newBoard.heuristic = newBoard.level + newBoard.misplacedTiles;
				System.out.println("Heuristic = "+newBoard.heuristic);

				Board.displayMisplacedBoard(newBoard.misplacedBoard);				
				priorityQueue.insert(newBoard);
	
			}
			System.out.println("|||||||||||||||||||||||||||||");
			
			if(Moves.verifyMove("down", easyBoard)){
				System.out.println("Old Board START STATE");
				easyBoard.displayBoard(easyBoard.start_state);
				Board newBoard = new Board(easyBoard.start_state);//board with the changes made
				newBoard.level = easyBoard.level +1;
				Moves.move("down", newBoard);
				System.out.println("New Board 3 START STATE");
				newBoard.displayBoard(newBoard.start_state);
				System.out.println("Misplaced tiles = " + newBoard.compareBoards());
				newBoard.heuristic = newBoard.level + newBoard.misplacedTiles;
				System.out.println("Heuristic = "+newBoard.heuristic);

				Board.displayMisplacedBoard(newBoard.misplacedBoard);
				priorityQueue.insert(newBoard);
			}
			System.out.println("|||||||||||||||||||||||||||||");
			
			if(Moves.verifyMove("left", easyBoard)){
				System.out.println("Old Board START STATE");
				easyBoard.displayBoard(easyBoard.start_state);
				Board newBoard = new Board(easyBoard.start_state);//board with the changes made
				newBoard.level = easyBoard.level +1;
				Moves.move("left", newBoard);
				System.out.println("New Board 3 START STATE");
				newBoard.displayBoard(newBoard.start_state);
				System.out.println("Misplaced tiles = " + newBoard.compareBoards());
				newBoard.heuristic = newBoard.level + newBoard.misplacedTiles;
				System.out.println("Heuristic = "+newBoard.heuristic);

				Board.displayMisplacedBoard(newBoard.misplacedBoard);
				priorityQueue.insert(newBoard);
			}
			System.out.println("|||||||||||||||||||||||||||||");
			
			if(Moves.verifyMove("right", easyBoard)){
				System.out.println("Old Board START STATE");
				easyBoard.displayBoard(easyBoard.start_state);
				Board newBoard = new Board(easyBoard.start_state);//board with the changes made
				newBoard.level = easyBoard.level +1;
				Moves.move("right", newBoard);
				System.out.println("New Board 3 START STATE");
				newBoard.displayBoard(newBoard.start_state);
				System.out.println("Misplaced tiles = " + newBoard.compareBoards());
				newBoard.heuristic = newBoard.level + newBoard.misplacedTiles;
				System.out.println("Heuristic = "+newBoard.heuristic);
				Board.displayMisplacedBoard(newBoard.misplacedBoard);
				priorityQueue.insert(newBoard);
			}
			System.out.println("|||||||||||||||||||||||||||||");
			
			int boardLevel = easyBoard.level +1;
			System.out.println("BOARD LEVEL " + boardLevel);
			easyBoard = priorityQueue.deleteMinimum();
			easyBoard.findBlankPos();
			
			
			
			System.out.println("--------------------------------");
		}
		
		
		
		
		
		
	}

}
