import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class TicTacToeWithAl {

	static int row=0;
	static int col=0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char[][] grid = {{' ','|',' ','|',' '},
				{'-','+','-','+','-'},
				{' ','|',' ','|',' '},
				{'-','+','-','+','-'},
				{' ','|',' ','|',' '}};
		
			playWithComputer(grid,true);
			
	}
	
	private static void playWithComputer(char[][] grid, boolean flag) {
		// TODO Auto-generated method stub
		ArrayList<Integer> computerPos = new ArrayList<Integer>();
		ArrayList<Integer> playerPos = new ArrayList<Integer>();
		String player1 = "Player 1";
		String player2 = "Computer";
		display(grid);
		int result=0;
		
		while(grid[0][0]==' ' || grid[0][2]==' ' || grid[0][4]==' ' || grid[2][0]==' ' || 
				grid[2][2]==' ' || grid[2][4]==' ' || grid[4][0]==' ' || 
				grid[4][2]==' ' || grid[4][4]==' ') {
		if(flag) {
			System.out.println("Player please enter your position from 1-9: ");
			acceptInputs(grid,player1,playerPos,0);
			result = checkWinner(grid);
					//calculateResults(grid, playerPos, computerPos);
			flag=false;
		}	
		else {
			//Implement AI here
			minimax(grid, false);
			grid[row][col]='O';
			display(grid);
			result = checkWinner(grid);
			flag = true;
		}
		if(result==-1) {
			System.out.println("Computer won");
			break;
		}
		else if(result==1) {
			System.out.println("Congratulations!! You won!");
			break;
		}
		else if(result==0) {
			System.out.println("It is a tie");
			break;
		}
		}
		
	}
	private static int minimax(char[][] grid, boolean isMax) {
		// TODO Auto-generated method stub
		int result = checkWinner(grid);
		int score=0;

		if(result==-1 || result==1 || result==0) {
			System.out.println("result = "+result);
			return result;
			
		}
		else {
			if(isMax) {
				//X
				int bestScore = (int)Double.NEGATIVE_INFINITY;
				for(int i = 0;i<=4;i=i+2) {
					for(int j = 0;j<=4;j=j+2) {
						if(grid[i][j]==' ') {
							grid[i][j]='X';
							score = minimax(grid, false);
							grid[i][j]=' ';
							if(score>bestScore) {
								bestScore=score;
								row=i;
								col=j;
							}
							//bestScore = Math.max(score, bestScore);
						}
					}
				}
				//grid[row][col]='X';
				//display(grid);
				return bestScore;
			}
			else {
				//O
				int bestScore = (int)Double.POSITIVE_INFINITY;
				for(int i = 0;i<=4;i=i+2) {
					for(int j = 0;j<=4;j=j+2) {
						if(grid[i][j]==' ') {
							grid[i][j]='O';
							score = minimax(grid, true);
							grid[i][j]=' ';
							if(score<bestScore) {
								row=i;
								col=j;
							}
							bestScore = Math.min(score, bestScore);
						}
					}
				}
				//grid[row][col]='O';
				//display(grid);
				return bestScore;
			}
		}
		
	}
	//algorithm which will find out the best move

	private static int checkWinner(char[][] grid) {
		// TODO Auto-generated method stub
		if((grid[0][0]=='X' && grid[0][2]=='X' && grid[0][4]=='X') ||
				(grid[2][0]=='X' && grid[2][2]=='X' && grid[2][4]=='X') ||
				(grid[4][0]=='X' && grid[4][2]=='X' && grid[4][4]=='X') ||
				(grid[0][0]=='X' && grid[2][0]=='X' && grid[4][0]=='X') ||
				(grid[0][2]=='X' && grid[2][2]=='X' && grid[4][2]=='X') ||
				(grid[0][4]=='X' && grid[2][4]=='X' && grid[4][4]=='X') ||
				(grid[0][4]=='X' && grid[2][2]=='X' && grid[4][0]=='X') ||
				(grid[0][0]=='X' && grid[2][2]=='X' && grid[4][4]=='X') ) {
			return 1;
		}
		else if((grid[0][0]=='O' && grid[0][2]=='O' && grid[0][4]=='O') ||
				(grid[2][0]=='O' && grid[2][2]=='O' && grid[2][4]=='O') ||
				(grid[4][0]=='O' && grid[4][2]=='O' && grid[4][4]=='O') ||
				(grid[0][0]=='O' && grid[2][0]=='O' && grid[4][0]=='O') ||
				(grid[0][2]=='O' && grid[2][2]=='O' && grid[4][2]=='O') ||
				(grid[0][4]=='O' && grid[2][4]=='O' && grid[4][4]=='O') ||
				(grid[0][4]=='O' && grid[2][2]=='O' && grid[4][0]=='O')||
				(grid[0][0]=='O' && grid[2][2]=='O' && grid[4][4]=='O') ) {
			return -1;
		}
		else if(grid[0][0]!=' ' && grid[0][2]!=' ' && grid[0][4]!=' ' && grid[2][0]!=' ' && 
				grid[2][2]!=' ' && grid[2][4]!=' ' && grid[4][0]!=' ' && 
				grid[4][2]!=' ' && grid[4][4]!=' '){
			return 0;
		}
		return 2;
	}
	private static int calculateResults(char[][] grid, ArrayList<Integer> player1Pos,ArrayList<Integer> player2Pos) {
		// TODO Auto-generated method stub
		List<Integer> firstCol = Arrays.asList(1,4,7);
		List<Integer> middleCol = Arrays.asList(2,5,8);
		List<Integer> lastCol = Arrays.asList(3,6,9);
		List<Integer> firstRow = Arrays.asList(1,2,3);
		List<Integer> middleRow = Arrays.asList(4,5,6);
		List<Integer> lastRow = Arrays.asList(7,8,9);
		List<Integer> firstDia = Arrays.asList(1,5,9);
		List<Integer> secondDia = Arrays.asList(3,5,7);
		
		if(player1Pos.containsAll(firstCol) || player1Pos.containsAll(middleCol) || 
				player1Pos.containsAll(lastCol) || player1Pos.containsAll(firstRow) || 
				player1Pos.containsAll(middleRow) || player1Pos.containsAll(lastRow) || 
				player1Pos.containsAll(firstDia) || player1Pos.containsAll(secondDia)) {
			return 1;
		}
		else if(player2Pos.containsAll(firstCol) || player2Pos.containsAll(middleCol) || 
				player2Pos.containsAll(lastCol) || player2Pos.containsAll(firstRow) || 
				player2Pos.containsAll(middleRow) || player2Pos.containsAll(lastRow) || 
				player2Pos.containsAll(firstDia) || player2Pos.containsAll(secondDia)) {
			return -1;
		}

		return 2; 
	}
	
	private static void acceptInputs(char[][] grid,String player, ArrayList<Integer> playerPos, int position) {
		// TODO Auto-generated method stub	
		int pos = position;
		if(position==0) {
		Scanner sc = new Scanner(System.in);
		pos = sc.nextInt();
		}
		char XorO = ' ';
		if(player.equals("Player 1")) {
			XorO = 'X';
		}
		else {
			XorO = 'O';
		}
		
		switch(pos) {
		case 1: 
			grid[0][0] = XorO;
		    break;
		case 2: 
			grid[0][2] = XorO;
		    break;
		case 3: 
			grid[0][4] = XorO;
		    break;
		case 4: 
			grid[2][0] = XorO;
		    break;
		case 5: 
			grid[2][2] = XorO;
		    break;
		case 6: 
			grid[2][4] = XorO;
		    break;
		case 7: 
			grid[4][0] = XorO;
		    break;
		case 8: 
			grid[4][2] = XorO;
		    break;
		case 9: 
			grid[4][4] = XorO;
		    break;
		    
		}
		playerPos.add(pos);
		//display(grid);
	}
	
	public static void display(char[][] grid) {
		for(char [] row : grid) {
			for(char c : row) {
				System.out.print(c);
			}
			System.out.println();
		}
	}

}
