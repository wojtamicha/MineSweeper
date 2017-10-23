public class Sweeper implements MineSweeper{
	
	
	/**
	 * 
	 * Description of how it works in interface
	 * Checks if mine field is written properly
	 * Works for every properly written mine field
	 * 
	 * 
	 * @author Micha³ Wojtaszek
	 * @version 1.0
	 * @since 2017-10-23
	 * 
	 * 
	 */
	
	int INITIALIZE=0;  //value that gives information if mine field has been initialized, 0-no, 1-yes
	String s;
	
	public Sweeper(String s) {
		this.s=s;
	}
	
	@Override
	public void setMineField(String mineField) throws IllegalArgumentException {
		
		char[] all=mineField.toCharArray();
		
		int count_rows=0;
		
		for(char c: all) {
			if( c=='\n' ) {
				
				//numbers_of_rows.add(count_rows); 
				count_rows++;
				//positions_of_n.add(count_position);
			}		
		}
				
		if(count_rows==0) throw new IllegalArgumentException("There is only one row - try entering a field"); 
		// if there is olny one row
		
		else {
			
			String [] rows=mineField.split("\n");  //split input string to rows
			int length_of_rows=rows[0].length();  // number of rows
			
			for(String s: rows) {
				if (length_of_rows != s.length()) throw new IllegalArgumentException("Rows are of different length"); 
				// if rows are of different length
				
			}
			
			for(String s: rows) { //checking if there are only '.' and '*' in rows
				
				char [] c=s.toCharArray();
				
				for(char x: c) {
					if(!(x == '.' || x == '*')) throw new IllegalArgumentException("In rows are illegal arguments");
				}
			}
			
			INITIALIZE=1; 
			s=mineField;
			System.out.println("This is your mine field:\n"+mineField);
				
		}
		
	}

	@Override
	public String getHintField() throws IllegalStateException {
				
		if(INITIALIZE==0) throw new IllegalStateException("Mine field not initialized");  
		// if mine field has not been initialized -> throws exception
		
		else {
			
			String [] rows=s.split("\n");  //because mine field is already set properly
			
			int row_l=rows.length;        //number of rows
			int col_l=rows[0].length();   //number of columns
			
			char [][] field=new char[row_l][col_l]; // 2D char mine field
			
			for(int i=0; i<row_l; i++) {
				field[i]=rows[i].toCharArray();
			}
						
			char [][] hint_char=new char[row_l][col_l];  // 2D array for hints
			
			int mines=0;
						
			for(int i=0; i<rows.length; i++) {
				
				for(int j=0; j<field[0].length; j++) {
					
					mines=0;
					
					if(field[i][j]=='*') hint_char[i][j]='*';
					
					else { // checking every possible surrounding square
						
						if((i-1>=0) && (j-1>=0)       && field[i-1][j-1]=='*') mines++;
						if((i-1>=0)                   && field[i-1][j]=='*')   mines++;
						if((i-1>=0) && (j+1<col_l)    && field[i-1][j+1]=='*') mines++;
						if(            (j-1>=0)       && field[i][j-1]=='*')   mines++;
						if(                              field[i][j]=='*')     mines++;
						if(            (j+1<col_l)    && field[i][j+1]=='*')   mines++;
						if((i+1<row_l) && (j-1>=0)    && field[i+1][j-1]=='*') mines++;
						if((i+1<row_l)                && field[i+1][j]=='*')   mines++;
						if((i+1<row_l) && (j+1<col_l) && field[i+1][j+1]=='*') mines++;
						
						hint_char[i][j]=Character.forDigit(mines,10);
					
					}
										
				}
			}
			
			String outcome=new String("");
			
			int licz=0;
			
			for (char[] c: hint_char) {
				
				if(licz<row_l-1) {
					
					outcome+=(new String(c))+"\n";
					licz++;
				}
				
				else outcome+=(new String(c));
			}
			
			return outcome;
		}
	}
	
	public static void main(String args[]) {
		
		Sweeper sweep=new Sweeper("*...\n..*.\n....");
		sweep.setMineField("*...\n..*.\n....");
		//sweep.setMineField("***...\n*.*.*.\n..*...\n......\n*.*.*.");
		System.out.println("Here is your hint:\n"+sweep.getHintField());
		
	}
}
