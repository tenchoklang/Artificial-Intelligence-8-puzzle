public class Main {

	public static void main(String[] args) {
		
		int[][] board = new int[][]{
			{2,8,1},
			{0,4,3},
			{7,6,5}
		};

		Board easyBoard = new Board(board);
	

		System.out.println("BOARD goal state");
		easyBoard.displayBoard(Board.getGoalState());
		System.out.println();
		
		System.out.println("BOARD start state");
		easyBoard.displayBoard(easyBoard.start_state);
		System.out.println("Blank Tile AT X:"+ easyBoard.blankPosX + " Y:"+ easyBoard.blankPosY);
		System.out.println();
		
		Moves.verifyMove("up", easyBoard);
		Moves.verifyMove("down", easyBoard);
		Moves.verifyMove("left", easyBoard);
		Moves.verifyMove("right", easyBoard);
		
		System.out.println("Misplaced tiles = " + easyBoard.compareBoards());
		Board.displayMisplacedBoard(easyBoard.misplacedBoard);
		System.out.println();

		System.out.println("|||||||||||||||||||||||||||||");
		if(Moves.verifyMove("up", easyBoard)){
			System.out.println("Old Board START STATE");
			easyBoard.displayBoard(easyBoard.start_state);
			Board newBoard1 = new Board(easyBoard.current_state);//board with the changes made
			Moves.move("up", newBoard1);
			System.out.println("New Board 1 START STATE");
			newBoard1.displayBoard(newBoard1.start_state);
			System.out.println("Misplaced tiles = " + newBoard1.compareBoards());
			Board.displayMisplacedBoard(newBoard1.misplacedBoard);
		}
		System.out.println("|||||||||||||||||||||||||||||");
		
		if(Moves.verifyMove("down", easyBoard)){
			System.out.println("Old Board START STATE");
			easyBoard.displayBoard(easyBoard.start_state);
			Board newBoard2 = new Board(easyBoard.current_state);//board with the changes made
			Moves.move("down", newBoard2);
			System.out.println("New Board 3 START STATE");
			newBoard2.displayBoard(newBoard2.start_state);
			System.out.println("Misplaced tiles = " + newBoard2.compareBoards());
			Board.displayMisplacedBoard(newBoard2.misplacedBoard);
		}
		System.out.println("|||||||||||||||||||||||||||||");
		
		if(Moves.verifyMove("left", easyBoard)){
			System.out.println("Old Board START STATE");
			easyBoard.displayBoard(easyBoard.start_state);
			Board newBoard3 = new Board(easyBoard.current_state);//board with the changes made
			Moves.move("left", newBoard3);
			System.out.println("New Board 3 START STATE");
			newBoard3.displayBoard(newBoard3.start_state);
			System.out.println("Misplaced tiles = " + newBoard3.compareBoards());
			Board.displayMisplacedBoard(newBoard3.misplacedBoard);
		}
		System.out.println("|||||||||||||||||||||||||||||");
		
		if(Moves.verifyMove("right", easyBoard)){
			System.out.println("Old Board START STATE");
			easyBoard.displayBoard(easyBoard.start_state);
			Board newBoard4 = new Board(easyBoard.current_state);//board with the changes made
			Moves.move("right", newBoard4);
			System.out.println("New Board 3 START STATE");
			newBoard4.displayBoard(newBoard4.start_state);
			System.out.println("Misplaced tiles = " + newBoard4.compareBoards());
			Board.displayMisplacedBoard(newBoard4.misplacedBoard);
		}
		System.out.println("|||||||||||||||||||||||||||||");
		
//		if(Moves.verifyMove("right", easyBoard)){
//			Moves.move("up", easyBoard);
//			Board newBoard4 = new Board(easyBoard.current_state);
//		}
		
		
		
	}

}
