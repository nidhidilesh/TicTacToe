import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class TicTacToe {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char[][] grid = {{' ','|',' ','|',' '},
				{'-','+','-','+','-'},
				{' ','|',' ','|',' '},
				{'-','+','-','+','-'},
				{' ','|',' ','|',' '}};
		
		System.out.println("Do you want to play with computer or player ? (C/P) : ");
		Scanner sc = new Scanner(System.in);
		String input = sc.next();
		switch(input) {
		case "C" : 
			playWithComputer(grid,true);
			break;
		case "P" : 
			playWithPlayer(grid,true);
			break;
		default :
			break;
		}	
	}
	private static void playWithPlayer(char[][] grid,boolean flag) {
		// TODO Auto-generated method stub
		String player1 = "Player 1";
		String player2 = "Player 2";
		ArrayList<Integer> player1Pos = new ArrayList<Integer>();
		ArrayList<Integer> player2Pos = new ArrayList<Integer>();
		display(grid);
		int result;
		while(grid[0][0]==' ' || grid[0][2]==' ' || grid[0][4]==' ' || grid[2][0]==' ' || 
				grid[2][2]==' ' || grid[2][4]==' ' || grid[4][0]==' ' || 
				grid[4][2]==' ' || grid[4][4]==' ') {
		if(flag) {
			System.out.println(player1+" please enter your position from 1-9: ");
			acceptInputs(grid,player1,player1Pos,0);
			result = calculateResults(grid, player1Pos, player2Pos);
			flag=false;
		}	
		else {
			System.out.println(player2+" please enter your position from 1-9: ");
			acceptInputs(grid,player2,player2Pos,0);
			result = calculateResults(grid, player1Pos, player2Pos);
			flag=true;
		}
		if(result==0) {
			System.out.println("Player 2 won");
			break;
		}
		else if(result==1) {
			System.out.println("Player 1 won!");
			break;
		}
		else if(result<=1){
			break;
		}
		}
		
	}
	
	private static void playWithComputer(char[][] grid, boolean flag) {
		// TODO Auto-generated method stub
		ArrayList<Integer> computerPos = new ArrayList<Integer>();
		ArrayList<Integer> playerPos = new ArrayList<Integer>();
		String player1 = "Player 1";
		String player2 = "Computer";
		display(grid);
		int result;
		while(grid[0][0]==' ' || grid[0][2]==' ' || grid[0][4]==' ' || grid[2][0]==' ' || 
				grid[2][2]==' ' || grid[2][4]==' ' || grid[4][0]==' ' || 
				grid[4][2]==' ' || grid[4][4]==' ') {
		if(flag) {
			System.out.println("Player please enter your position from 1-9: ");
			acceptInputs(grid,player1,playerPos,0);
			result = calculateResults(grid, playerPos, computerPos);
			flag=false;
		}	
		else {
			Random r = new Random();
			Integer position = r.nextInt(9);
			System.out.println("Position before = "+position);
			Set<Integer> allPositions = new HashSet<>();
			allPositions.addAll(computerPos);
			allPositions.addAll(playerPos);
			while(true) {
				int count = 0;
				boolean flag1 = false;
				for(int i:allPositions) {
					count++;
					System.out.println("i = "+i);
					if(position==i) {
						System.out.println("Finding new position");
						position = r.nextInt(9)+1;
						flag1=true;
						System.out.println("position = "+position);
						break;
					}
				}
				if(flag1==false) {
					break;
				}
			}
			acceptInputs(grid,player2,computerPos,position);
			result = calculateResults(grid, playerPos, computerPos);
			flag=true;
		}
		if(result==0) {
			System.out.println("Computer won");
			break;
		}
		else if(result==1) {
			System.out.println("Congratulations!! You won!");
			break;
		}
		else if(result<=1){
			break;
		}
		}
		
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
			return 0;
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
		display(grid);
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
