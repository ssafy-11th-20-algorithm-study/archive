package a0226;

import java.io.*;
import java.util.*;

public class Main_bj_17136_색종이_붙이기_서울_20반_심재운 {

	static int[][] maps = new int[10][10];
	static int[] papers = new int[] { 0, 5, 5, 5, 5, 5 };
	static int answer = -1;

	static boolean isSquare(int x, int y, int s) {
		for (int i = 0; i < s; i++) {
			for (int j = 0; j < s; j++) {
				if (maps[x + i][y + j] == 0)
					return false;
			}
		}
		return true;
	}

	static void makeSquare(int x, int y, int s, int useSquare) {
		if (useSquare == 1)
			papers[s]++;
		else
			papers[s]--;

		for (int i = 0; i < s; i++) {
			for (int j = 0; j < s; j++) {
				maps[x + i][y + j] = useSquare;
			}
		}
	}

	static void backtracking(int x, int y, int cnt) {
		if (y > 9) {
			backtracking(x + 1, 0, cnt);
			return;
		}

		if (x > 9) { // 전부 탐색했을 경우
			if (answer == -1) answer = cnt;
			else if (answer > cnt) answer = cnt;
			return;
		}

		if (maps[x][y] == 0) {
			backtracking(x, y + 1, cnt);
			return;
		}

		for (int s = 5; s >= 1; s--) {
			if (papers[s] != 0 && x + s <= 10 && y + s <= 10) {
				if (isSquare(x, y, s)) {
					makeSquare(x, y, s, 0);
					backtracking(x, y + 1, cnt + 1);
					makeSquare(x, y, s, 1);
				}
			}
		}

	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/0226/Main_bj_17136_색종이_붙이기_서울_20반_심재운.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for (int x = 0; x < 10; x++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int y = 0; y < 10; y++) {
				maps[x][y] = Integer.parseInt(st.nextToken());
			}
		}
		backtracking(0, 0, 0);
		System.out.println(answer);
	}

}
