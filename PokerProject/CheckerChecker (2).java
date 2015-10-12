public class CheckerChecker{
//test cases:
	//1. n particular, the row or column of a checker must always be in the range of 1 to 8 inclusive, and must remain so after a move.
	//2.  the specified number of rows to move must be negative for red checkers and positive for white ones

	public static void main(String[] agrs){
		//System.out.println("hello world");
		// Checker checker = new Checker(false, 3, 3);
		// System.out.println(checker.getRow());
		// System.out.println(checker.getColumn());
		// checker.move(1,1);
		// System.out.println(checker.getRow());
		// System.out.println(checker.getColumn());
		if(TestContructor() &&
			TestIsRed() &&
			TestMove() ) 
		{
			System.out.println("CORRECT");
		}
		else System.out.println("BUG");
	}

	public static boolean TestContructor(){
		
		Checker checker1 = new Checker(true, 9, 2);
		if ((checker1.getColumn() != 1) || checker1.getRow() != 1) return false;
	

	
		Checker checker2 = new Checker(true, -1, 2);
		if ((checker2.getColumn() != 1) || checker2.getRow() != 1) return false;
	

	
		Checker checker3 = new Checker(true, 2, 9);
		if ((checker3.getColumn() != 1) || checker3.getRow() != 1) return false;


	
		Checker checker4 = new Checker(true, 2, -1);
		if ((checker4.getColumn() != 1) || checker4.getRow() != 1) return false;



		Checker checker5 = new Checker(true, 2, 3);
		if ((checker5.getColumn() != 1) || checker5.getRow() != 1) return false;

		Checker checker6 = new Checker(true, 3, 2);
		if ((checker6.getColumn() != 1) || checker6.getRow() != 1) return false;


		Checker checker7 = new Checker(true, 2, 3);
		if ((checker7.getColumn() != 1) || checker7.getRow() != 1) return false;

		Checker checker8 = new Checker(true, 3, 2);
		if ((checker8.getColumn() != 1) || checker8.getRow() != 1) return false;
		
		return true;
	}

	public static boolean TestIsRed(){
		
		Checker redChecker = new Checker(true);
		if(!redChecker.isRed()) return false;	

		Checker blackChecker = new Checker(false);
		if(blackChecker.isRed()) return false;

		return true;
	}

	public static boolean TestMove(){
		// the specified number of rows to move must be negative for red checkers and positive for white ones

		if(testNegtivePositive() && testMoveStep()) return true;
		else return false;

	}

	public static boolean testNegtivePositive(){
		
		Checker redChecker = new Checker(true, 2, 4);
		if( redChecker.getColumn() != getMoveColumn(redChecker, 1, 1) || 
			redChecker.getRow() != getMoveRow(redChecker,1,1) )
		return false;
		

		Checker blackChecker = new Checker(false, 2, 4);
		if( blackChecker.getColumn() != getMoveColumn(blackChecker, -1,-1) || 
			blackChecker.getRow() != getMoveRow(blackChecker, -1,-1) )
		return false;

		return true;
	}

	public static boolean testMoveStep(){
		
		Checker redChecker = new Checker(true, 2, 4);
		if( redChecker.getColumn() != getMoveColumn(redChecker, -3,-3) || 
			redChecker.getRow() != getMoveRow(redChecker, -3, -3) )
		return false;
		

	
		Checker blackChecker = new Checker(false, 2, 4);
		if( blackChecker.getColumn() != getMoveColumn(blackChecker, 3, 3) || 
			blackChecker.getRow() != getMoveRow(blackChecker, 3, 3) )
		return false;

		return true;
	
	}


	public static int getMoveColumn(Checker checker, int rows, int columns){

		checker.move(rows, columns);

		return checker.getColumn();

	}

	public static int getMoveRow(Checker checker, int rows, int columns){

		checker.move(rows, columns);

		return checker.getRow();

	}


}