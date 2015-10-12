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
		
		Checker checker1 = new Checker(true, 1, 9);
		if ((checker1.getColumn() != 1) || checker1.getRow() != 1) return false;
	
		Checker checker2 = new Checker(true, 0, 2);
		if ((checker2.getColumn() != 1) || checker2.getRow() != 1) return false;
	
		Checker checker3 = new Checker(false, 1, 9);
		if ((checker3.getColumn() != 1) || checker3.getRow() != 1) return false;
	
		Checker checker4 = new Checker(false, 2, 0);
		if ((checker4.getColumn() != 1) || checker4.getRow() != 1) return false;

		Checker checker5 = new Checker(true, 2, 3);
		if ((checker5.getColumn() != 1) || checker5.getRow() != 1) return false;

		Checker checker6 = new Checker(true, 3, 2);
		if ((checker6.getColumn() != 1) || checker6.getRow() != 1) return false;


		Checker checker7 = new Checker(false, 2, 3);
		if ((checker7.getColumn() != 1) || checker7.getRow() != 1) return false;

		Checker checker8 = new Checker(false, 3, 2);
		if ((checker8.getColumn() != 1) || checker8.getRow() != 1) return false;

		Checker checker9 = new Checker(true, 2, 4);
		if ((checker9.getColumn() != 4) || checker9.getRow() != 2) return false;


		Checker checker10 = new Checker(false, 4, 2);
		if ((checker10.getColumn() != 2) || checker10.getRow() != 4) return false;

		Checker checker11 = new Checker(true);
		if ((checker11.getColumn() != 1) || checker11.getRow() != 1) return false;

		Checker checker12 = new Checker(false);
		if ((checker12.getColumn() != 1) || checker12.getRow() != 1) return false;

		return true;
	}

	public static boolean TestIsRed(){
		
		Checker redChecker = new Checker(true);
		if(!redChecker.isRed()) return false;

		redChecker = new Checker(true, 10, 10);
		if(!redChecker.isRed()) return false;	

		Checker blackChecker = new Checker(false);
		if(blackChecker.isRed()) return false;

		blackChecker = new Checker(false, -1, -1);
		if(blackChecker.isRed()) return false;

		return true;
	}

	public static boolean TestMove(){
		// the specified number of rows to move must be negative for red checkers and positive for white ones

		if(testNegtivePositive() && testMoveBoundary() && testSquare()) return true;
		else return false;

	}

	public static boolean testNegtivePositive(){

		//the specified number of rows to move must be negative for red checkers and positive for white ones
		
		Checker redChecker = new Checker(true, 4, 4);
		//int tmp = getMoveColumn(redChecker, -1, -1);
		// redChecker.move(1,-1);
		// System.out.println(redChecker.getRow());
		// System.out.println(redChecker.getColumn());
		redChecker.move(1, -1);
		if(redChecker.getRow() != 4 || redChecker.getColumn() != 4) return false;
		redChecker.move(1, 1);
		if(redChecker.getRow() != 4 || redChecker.getColumn() != 4) return false;

		// if( getMoveRow(redChecker,1, -1)  != 4 || 
		// 	getMoveRow(redChecker,1, 1)  != 4 || 
		// 	getMoveColumn(redChecker,1, -1) != 4 ||
		// 	getMoveColumn(redChecker,1, 1) != 4
		// )
		//return false;
		

		Checker blackChecker = new Checker(false, 5, 5);
		// if( getMoveRow(blackChecker, -1, 1) != 5 ||
		// 	getMoveRow(blackChecker, -1, -1) != 5 ||
		// 	getMoveColumn(blackChecker, -1, 1) != 5||
		// 	getMoveColumn(blackChecker, -1, -1) != 5
		// )
		blackChecker.move(-1, 1);
		if(blackChecker.getRow() != 5 || blackChecker.getRow() != 5) return false;
		blackChecker.move(-1, -1);
		if(blackChecker.getRow() != 5 || blackChecker.getRow() != 5) return false;

		//return false;

		return true;
	}

	public static boolean testMoveBoundary(){
		
		Checker redChecker = new Checker(true, 1, 1);
		// redChecker.move(-1,1);
		// System.out.println(redChecker.getRow());
		// System.out.println(redChecker.getColumn());
		redChecker.move(-1, -1);
		if( redChecker.getRow() != 1 || redChecker.getColumn() != 1 ) return false;

		redChecker.move(-1, 1);
		if( redChecker.getRow() != 1 || redChecker.getColumn() != 1 ) return false;	
		

	
		Checker blackChecker = new Checker(false, 8, 8);
		// blackChecker.move(1, -1);
		// System.out.println(blackChecker.getRow());
		// System.out.println(blackChecker.getColumn());
		// if( blackChecker.getColumn() != getMoveColumn(blackChecker, 1, 1) || 
		// 	blackChecker.getRow() != getMoveRow(blackChecker, 1, 1) ||
		// 	blackChecker.getColumn() != getMoveColumn(blackChecker, 1, -1) || 
		// 	blackChecker.getRow() != getMoveRow(blackChecker, 1, -1) )
		// return false;

		blackChecker.move(1, 1);
		if( blackChecker.getRow() != 8 || blackChecker.getColumn() != 8) return false;
		blackChecker.move(1, -1);
		if( blackChecker.getRow() != 8 || blackChecker.getColumn() != 8) return false;


		return true;
	
	}

	public static boolean testSquare(){
		
		Checker redChecker = new Checker(true, 5, 5);
		// redChecker.move(-1,1);
		// System.out.println(redChecker.getRow());
		// System.out.println(redChecker.getColumn());
		//System.out.println(getMoveRow(redChecker, -1, 1));
		if( getMoveRow(redChecker, -1, 0)  != 5 ||
			getMoveRow(redChecker, -1, 3) != 5 || 
			getMoveRow(redChecker, -1, -3) != 5 || 
			
			getMoveColumn(redChecker, 0, 1) != 5 || 
			getMoveColumn(redChecker, 3, 1) != 5 || 
			getMoveColumn(redChecker, -3, 1) != 5  )	
		return false;

		redChecker.move(-1, -1);
		if (redChecker.getRow() != 4 || redChecker.getColumn() != 4) return false;
		redChecker.move(-1, 1);
		if (redChecker.getRow() != 3 || redChecker.getColumn() != 5) return false;		
			

			//(redChecker.getRow() != getMoveRow(redChecker, -1, -1) + 1)  )//||
			//(redChecker.getColumn() != getMoveColumn(redChecker, -1, 1) - 1 )|| 
			//(redChecker.getRow() != getMoveRow(redChecker, -1, 1) + 1) )
			
		//return false;
		


	
		Checker blackChecker = new Checker(false, 4, 4);
		if( 
			getMoveRow(blackChecker, 1, 0) != 4 ||
			getMoveRow(blackChecker, 1, 3) != 4 ||
			//blackChecker.getColumn() != getMoveColumn(blackChecker, 1, -3) || 
			getMoveRow(blackChecker, 1, -3) != 4 ||
			
			getMoveColumn(blackChecker, 0, -1) != 4 || 
			//blackChecker.getRow() != getMoveRow(blackChecker, 0, -1) ||
			getMoveColumn(blackChecker, 3, -1) != 4|| 
			//blackChecker.getRow() != getMoveRow(blackChecker, 3, -1) ||
			getMoveColumn(blackChecker, -3, -1) != 4 )
			
			//blackChecker.getRow() != getMoveRow(blackChecker, -3, -1) ||
			//blackChecker.getColumn() != getMoveColumn(blackChecker, -1, -1) || 
			//blackChecker.getRow() != getMoveRow(blackChecker, -1, -1) ||
			//blackChecker.getColumn() != getMoveColumn(blackChecker, -1, -1) || 
			//blackChecker.getRow() != getMoveRow(blackChecker, -1, -1) ||
			//(blackChecker.getColumn() != getMoveColumn(blackChecker, 1, 1) - 1)|| 
			//(blackChecker.getRow() != getMoveRow(blackChecker, 1, 1) - 1) )
			// (blackChecker.getColumn() != getMoveColumn(blackChecker, 1, -1) + 1)|| 
			// (blackChecker.getRow() != getMoveRow(blackChecker, 1, -1) - 1) )
		return false;

		blackChecker.move(1, 1);
		if(blackChecker.getRow() != 5 || blackChecker.getColumn() != 5) return false;
		blackChecker.move(1, -1);
		if(blackChecker.getRow() != 6 || blackChecker.getColumn() != 4) return false;

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