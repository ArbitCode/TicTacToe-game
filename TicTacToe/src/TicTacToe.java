import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

	static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
	static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();

	public static void main(String[] args) {

		boolean flag = true;
		while (flag) {
			clearConsole();
			char[][] gameBoard = { { ' ', '|', ' ', '|', ' ' }, { '-', '+', '-', '+', '-' },
					{ ' ', '|', ' ', '|', ' ' }, { '-', '+', '-', '+', '-' }, { ' ', '|', ' ', '|', ' ' } };
			playerPositions.clear();
			cpuPositions.clear();
			System.out.println("Let's play TicTacToe game!!!");
			printGameBoard(gameBoard);
			while (true) {

				Scanner scan = new Scanner(System.in);
				System.out.println("Enter your placement (1-9):");
				int playerPos = scan.nextInt();
				while (playerPos > 9) {
					System.out.println("Please enter position less than 9");
					playerPos = scan.nextInt();
				}
				while (playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)) {
					System.out.println("Position taken! Enter a correct Position");
					playerPos = scan.nextInt();
				}
				placePiece(gameBoard, playerPos, "player");
				String result = checkWinner();
				// String result = checkWinner();
				if (result.length() > 0) {
					System.out.println(result);
					printGameBoard(gameBoard);
					flag = playAgain();
					break;
				}
				Random rand = new Random();
				int cpuPos = rand.nextInt(9) + 1;
				while (playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {
					cpuPos = rand.nextInt(9) + 1;
				}
				placePiece(gameBoard, cpuPos, "cpu");
				result = checkWinner();
				if (result.length() > 0) {
					System.out.println(result);
					printGameBoard(gameBoard);
					flag = playAgain();
					break;
				}
				printGameBoard(gameBoard);

			}
		}

	}

	private static final void clearConsole() {
		try {
			final String os = System.getProperty("os.name");
			if (os.contains("windows")) {
				Runtime.getRuntime().exec("cls");
			} else {
				Runtime.getRuntime().exec("clear");

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private static boolean playAgain() {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		System.out.println("Do you want Play again enter Y/N");
		while (true) {
			String nextHope = scan.next();
			if (nextHope.equals("Y") | nextHope.equals("y")) {
				return true;

			}

			if (nextHope.equals("N") | nextHope.equals("n")) {

				System.out.println("Thank you! you can retry again!!");
				return false;

			} else {
				System.out.println("Please enter valid text (Y/N)");
			}
		}
	}

	private static String checkWinner() {
		List<Integer> topRow = Arrays.asList(1, 2, 3);
		List<Integer> midRow = Arrays.asList(4, 5, 6);
		List<Integer> botRow = Arrays.asList(7, 8, 9);
		List<Integer> leftCol = Arrays.asList(1, 4, 7);
		List<Integer> midCol = Arrays.asList(2, 5, 8);
		List<Integer> rightCol = Arrays.asList(3, 6, 9);
		List<Integer> cross1 = Arrays.asList(1, 5, 9);
		List<Integer> cross2 = Arrays.asList(7, 5, 3);

		List<List> winning = new ArrayList<List>();

		winning.add(topRow);
		winning.add(midRow);
		winning.add(botRow);
		winning.add(leftCol);
		winning.add(midCol);
		winning.add(rightCol);
		winning.add(cross1);
		winning.add(cross2);

		for (List l : winning) {
			if (playerPositions.size() + cpuPositions.size() < 9) {
				if (playerPositions.containsAll(l)) {
					return "Congratulations you won! ):";
				} else if (cpuPositions.containsAll(l)) {
					return "CPU wins! sorry :(";
				}
			} else if (playerPositions.size() + cpuPositions.size() == 9) {
				if (playerPositions.containsAll(l)) {
					return "Congratulations you won! ):";
				} else if (cpuPositions.containsAll(l)) {
					return "CPU wins! sorry :(";
				}
				return "tie-break";

			}
		}

		return "";
	}

	private static void placePiece(char[][] gameBoard, int pos, String user) {

		char symbol = ' ';
		if (user.equals("player")) {
			symbol = 'X';
			playerPositions.add(pos);
		} else if (user.equals("cpu")) {
			symbol = '0';
			cpuPositions.add(pos);
		}

		switch (pos) {
		case 1:
			gameBoard[0][0] = symbol;
			break;
		case 2:
			gameBoard[0][2] = symbol;
			break;
		case 3:
			gameBoard[0][4] = symbol;
			break;
		case 4:
			gameBoard[2][0] = symbol;
			break;
		case 5:
			gameBoard[2][2] = symbol;
			break;
		case 6:
			gameBoard[2][4] = symbol;
			break;
		case 7:
			gameBoard[4][0] = symbol;
			break;
		case 8:
			gameBoard[4][2] = symbol;
			break;
		case 9:
			gameBoard[4][4] = symbol;
			break;
		default:
			break;
		}

	}

	private static void printGameBoard(char[][] gameBoard) {
		for (char[] row : gameBoard) {
			for (char c : row) {
				System.out.print(c);
			}
			System.out.println();
		}
	}

}
