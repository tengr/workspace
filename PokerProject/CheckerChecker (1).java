
public class CheckerChecker {
	private static int i;
	private static int j;
	
	public static void main(String[] args){

		//Create new checkers and specify the special situations
		Checker c1=new Checker (true,i,j);
		Checker c2=new Checker (false,i,j);
		 
		 
		//c1 is in table
		if ((c1.getRow()>=1 && c1.getColumn()>=1)
			||(c1.getRow()<=8 && c1.getColumn()<=8))
		{
			//c2 is in table
			if ((c2.getRow()>=1 && c2.getColumn()>=1)
					||(c2.getRow()<=8 && c2.getColumn()<=8)) {
				
				//move is 1 or -1
				if ((c1.getRow()-i ==1 && c1.getColumn()-j ==1)
					|| (c1.getRow()-i == 1 && c1.getColumn()-j == -1)
					|| (c1.getRow()-i == -1 && c1.getColumn()-j == 1)
					|| (c1.getRow()-i == -1 && c1.getColumn()-j == -1)
					)
				{
					if ((c2.getRow()-i ==1 && c2.getColumn()-j ==1)
							|| (c2.getRow()-i == 1 && c2.getColumn()-j == -1)
							|| (c2.getRow()-i == -1 && c2.getColumn()-j == 1)
							|| (c2.getRow()-i == -1 && c2.getColumn()-j == -1)
							){
						if (i>=1 || i<=8 || j >=1 || j <=8) {
							if (i+j == 2 || i+j==4 || i+j==6 ||i+j==8||i+j==10||i+j==12||i+j==14||i+j==16)
								 {
					
		System.out.println("CORRECT");
								 }
						}
					}
				}
			}
	}
		
		else {
			System.out.println("BUG");
		}
	}
}


		

