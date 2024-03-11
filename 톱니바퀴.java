import java.io.*;
import java.util.*;

public class Main {
	static int k;
	static int[][] map;
	static int index1, index2, index3, index4 = 0;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb;
		index1 = 0;
		index2 = 0;
		index3 = 0;
		index4 = 0;
		map = new int[4][8];
		for (int i = 0; i < 4; i++) {
			String s = br.readLine();
			for (int j = 0; j < 8; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}
		k = Integer.parseInt(br.readLine());
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int target = Integer.parseInt(st.nextToken()) - 1;
			int direction = Integer.parseInt(st.nextToken());
			if (target == 0) {
				if (map[0][(index1 + 2) % 8] == map[1][(index2 + 6) % 8]) {
					rotate(direction, 0);
				} else {
					if (map[1][(index2 + 2) % 8] == map[2][(index3 + 6) % 8]) {
						rotate(direction, 0);
						rotate(-direction, 1);
					} else {
						if (map[2][(index3 + 2) % 8] == map[3][(index4 + 6) % 8]) {
							rotate(direction, 0);
							rotate(-direction, 1);
							rotate(direction, 2);
						} else {
							rotate(direction, 0);
							rotate(-direction, 1);
							rotate(direction, 2);
							rotate(-direction, 3);
						}
					}
				}
			} else if (target == 1) {
				if (map[0][(index1 + 2) % 8] != map[1][(index2 + 6) % 8])
					rotate(-direction, 0);
				if (map[1][(index2 + 2) % 8] == map[2][(index3 + 6) % 8]) {
					rotate(direction, 1);
				} else {
					if (map[2][(index3 + 2) % 8] == map[3][(index4 + 6) % 8]) {
						rotate(direction, 1);
						rotate(-direction, 2);
					} else {
						rotate(direction, 1);
						rotate(-direction, 2);
						rotate(direction, 3);
					}
				}
			} else if (target == 2) {
				if (map[2][(index3 + 2) % 8] != map[3][(index4 + 6) % 8])
					rotate(-direction, 3);
				if (map[1][(index2 + 2) % 8] == map[2][(index3 + 6) % 8]) {
					rotate(direction, 2);
				} else {
					if (map[0][(index1 + 2) % 8] == map[1][(index2 + 6) % 8]) {

						rotate(direction, 2);
						rotate(-direction, 1);
					} else {
//                          System.out.println("돌렸다");
						rotate(direction, 2);
						rotate(-direction, 1);
						rotate(direction, 0);
					}
				}
			} else if (target == 3) {
				if (map[2][(index3 + 2) % 8] == map[3][(index4 + 6) % 8]) {
					rotate(direction, 3);
				} else {
					if (map[1][(index2 + 2) % 8] == map[2][(index3 + 6) % 8]) {
						rotate(direction, 3);
						rotate(-direction, 2);
					} else {
						if (map[0][(index1 + 2) % 8] == map[1][(index2 + 6) % 8]) {
							rotate(direction, 3);
							rotate(-direction, 2);
							rotate(direction, 1);
						} else {
							rotate(direction, 3);
							rotate(-direction, 2);
							rotate(direction, 1);
							rotate(-direction, 0);
						}
					}
				}
			}

		}
		int score = 0;
		if (map[0][index1] == 1)
			score += 1;
		if (map[1][index2] == 1)
			score += 2;
		if (map[2][index3] == 1)
			score += 4;
		if (map[3][index4] == 1)
			score += 8;

		sb = new StringBuilder();
		sb.append(score);
		System.out.println(sb.toString());

	}

	static void rotate(int direction, int target) {
		if (direction == 1) { // 시계방향
			if (target == 0) {
				index1 = (index1 - 1);
				if (index1 < 0)
					index1 = 7;
			} else if (target == 1) {
				index2 = (index2 - 1);
				if (index2 < 0)
					index2 = 7;
			} else if (target == 2) {
				index3 = (index3 - 1);
				if (index3 < 0)
					index3 = 7;
			} else if (target == 3) {
				index4 = (index4 - 1);
				if (index4 < 0)
					index4 = 7;
			}
		} else if (direction == -1) { // 반시계방향
			if (target == 0) {
				index1 = (index1 + 1);
				if (index1 >= 8)
					index1 = 0;
			} else if (target == 1) {
				index2 = (index2 + 1);
				if (index2 >= 8)
					index2 = 0;
			} else if (target == 2) {
				index3 = (index3 + 1);
				if (index3 >= 8)
					index3 = 0;
			} else if (target == 3) {
				index4 = (index4 + 1);
				if (index4 >= 8)
					index4 = 0;
			}
		}
	}
}
