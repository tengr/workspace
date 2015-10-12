
public class Checker {

	    private boolean isRed = true;
	    private int row = 1;
	    private int column = 1;
	    private static final int MAX = 8;
	    private static final int MIN = 1;
	    
	    //creates constructor for a checker
	    public boolean isRed() {
	        return isRed;
	    }
	    
	    //get current row
	    public int getRow() {
	        return row;
	    }
	    
	    //get current column
	    public int getColumn() {
	        return column;
	    }
	    
	    //creates a checker at row 1 column 1
	    public Checker(boolean isRed) {
	        this.isRed = isRed;
	    }
	    
	    //creates a checker at the specific row and column
	    public Checker(boolean isRed, int row, int column) {
	        this.isRed = isRed;
	        if (out(row, column) || errorPlace(row, column))
	            return;

	        this.row = row;
	        this.column = column;
	    }
	    
	    //test checker still in table and not out of table that row and column should be bigger or equal to 1 and smaller or equal to 8
	    private boolean out(int row, int column) {
	        return row > MAX || row < MIN || column > MAX || column < MIN;
	    }
	    
	    //check the odd and even rows and column as they plus each other can be divided by 2
	    private boolean errorPlace(int row, int column){
	    	return (row + column)%2 == 1;
	    }
	    
	    //check the checker move is valid that white checker's row can only be 1 while red checker should be -1, the column is 1 or -1
	    public void move(int rows, int columns) {
    		//test red checker
    		if (this.isRed == true){
    			//check rows = -1
    			if (((rows == -1) && (columns==1)) || ((rows == -1) && (columns == -1))){
    				//check the checker still in the table after move
    				if ((this.row+rows>=1) && (this.row+rows <=8) && (this.column +columns>=1) && (this.column + columns <=8)){
    					this.row = this.row + rows;
    					this.column = this.column +columns;    				
    			}	
    			}
    			}
    		//test white checker
    		if (this.isRed == false){
    			//check rows =1
    			if (((rows == 1) && (columns==1)) || ((rows == 1) && (columns == -1))){
    				//check the checker still in the table after move
    				if ((this.row+rows>=1) && (this.row+rows <=8) && (this.column +columns>=1) && (this.column + columns <=8)){
    					//if valid move, the row and the column
    					this.row = this.row + rows;
    					this.column = this.column +columns;
    				}
    				}
    			}
    		//if invalid move the checker will stay at the original place
    		else {
    			this.row = this.row+0;
    			this.column = this.column+0;	
    		}
    	}

	   
	}


