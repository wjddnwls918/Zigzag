import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Snake {

	public static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		SnakesLadders snakesLadders = new SnakesLadders(getLadders());

		playGame(snakesLadders);

	}

	public static HashMap getLadders() {

		HashMap<Integer, Integer> ladders = new HashMap();
		ladders.put(2, 38);
		ladders.put(7, 14);
		ladders.put(8, 31);
		ladders.put(15, 26);
		ladders.put(21, 42);
		ladders.put(28, 84);
		ladders.put(36, 44);
		ladders.put(51, 67);
		ladders.put(71, 91);
		ladders.put(78, 98);

		ladders.put(16, 6);
		ladders.put(46, 25);
		ladders.put(49, 11);
		ladders.put(62, 19);
		ladders.put(64, 60);
		ladders.put(74, 53);
		ladders.put(89, 68);
		ladders.put(92, 88);
		ladders.put(95, 75);
		ladders.put(99, 80);

		return ladders;
	}

	public static void playGame(SnakesLadders snakeLadders) throws Exception {

		while (true) {

			int dice1, dice2;
			/*
			 * dice1 = (int) (Math.random() * 6) + 1; dice2 = (int) (Math.random() * 6) + 1;
			 */

			System.out.print("dice1 : ");
			dice1 = Integer.parseInt(in.readLine());
			System.out.print("dice2 : ");
			dice2 = Integer.parseInt(in.readLine());

			System.out.println(snakeLadders.play(dice1, dice2));

		}

	}

}

class SnakesLadders {

	HashMap<Integer, Integer> ladders;
	int turn;
	boolean isEnd;
	Player p[] = new Player[2];

	public SnakesLadders(HashMap ladders) {
		this.ladders = ladders;

		for (int i = 0; i < 2; i++) {
			p[i] = new Player();
		}

	}

	public String play(int dice1, int dice2) {

		if (isEnd) {
			return "Game Over!";
		}

		// 위치 이동
		p[turn].position += (dice1 + dice2);

		if (p[turn].position > 100) {
			p[turn].position = 100 - (p[turn].position - 100);
		}

		if (ladders.containsKey(p[turn].position)) {
			p[turn].position = ladders.get(p[turn].position);
		}

		// 결과 반환
		if (p[turn].position == 100) {
			isEnd = true;
			return "Player " + (turn + 1) + " Wins!";
		} else {
			int tempTurn = turn;
			int tempPosition = p[turn].position;

			// 같지 않은 경우 턴 넘기기
			if (dice1 != dice2)
				turn = (turn + 1) % 2;

			return "Player " + (tempTurn + 1) + " is on square " + tempPosition;
		}
	}

	class Player {
		int position;
	}
}
