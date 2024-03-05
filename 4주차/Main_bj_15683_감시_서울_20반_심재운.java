package a0221;

import java.io.*;
import java.util.*;

public class Main_bj_15683_감시_서울_20반_심재운 {

	static int N, M, answer = Integer.MAX_VALUE;
	static int[][] maps;
	// 북 동 남 서
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	static int[][][] cctv = { { { 0 }, { 1 }, { 2 }, { 3 } }, { { 0, 2 }, { 1, 3 } },
			{ { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 0 } }, { { 3, 0, 1 }, { 0, 1, 2 }, { 1, 2, 3 }, { 2, 3, 0 } },
			{ { 0, 1, 2, 3 } } };

	static void dfs(int y, int x) {
		if (x >= M) {
			dfs(y + 1, 0);
			return;
		}
		if (y >= N) {
			// 로직
			int sum = 0;

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if (maps[r][c] == 0)
						sum++;
				}
			}

			answer = Math.min(sum, answer);

			return;
		}
		//
		if (maps[y][x] > 0 && maps[y][x] < 6) {
			int direction = maps[y][x] - 1;

			for (int d = 0; d < cctv[direction].length; d++) {
				for (int k = 0; k < cctv[direction][d].length; k++) {
					change(cctv[direction][d][k], y, x, 1);
				}
				dfs(y, x + 1);
				for (int k = 0; k < cctv[direction][d].length; k++) {
					change(cctv[direction][d][k], y, x, 0);
				}
			}
			return;
		}
		dfs(y, x + 1);
	}

	static void change(int signal, int y, int x, int d) {
		int ny = y + dy[signal];
		int nx = x + dx[signal];

		if (d == 1) {
			while (0 <= ny && ny < N && 0 <= nx && nx < M) {
				if (maps[ny][nx] == 6) {
					break;
				}
				if (maps[ny][nx] == 0 || maps[ny][nx] >= 10) {
					maps[ny][nx] += 10;
				}
				ny = ny + dy[signal];
				nx = nx + dx[signal];
			}
		} else {
			while (0 <= ny && ny < N && 0 <= nx && nx < M) {
				if (maps[ny][nx] == 6) {
					break;
				}
				if (maps[ny][nx] >= 10) {
					maps[ny][nx] -= 10;
				}
				ny = ny + dy[signal];
				nx = nx + dx[signal];
			}
		}
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/0221/Main_bj_15683_감시_서울_20반_심재운.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		maps = new int[N][M];

		for (int y = 0; y < N; y++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int x = 0; x < M; x++) {
				maps[y][x] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(0, 0);
//		change(2, 0, 0, 1);
//		change(3, 1, 1, 1);
//		for (int[] a: maps) System.out.println(Arrays.toString(a));

		System.out.println(answer);
		br.close();
	}

}
