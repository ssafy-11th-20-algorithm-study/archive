package a0226;

import java.io.*;
import java.util.*;

public class Main_bj_14503_로봇_청소기_서울_20반_심재운 {

	static int N, M, r, c, d, answer;
	static final int[] dx = { -1, 0, 1, 0 };
	static final int[] dy = { 0, 1, 0, -1 };
	static int[][] arr;

	static boolean clearTrue() { // 청소할 수 없을 때 
		int nd = (d + 2) % 4;
		int nr = r + dx[nd];
		int nc = c + dy[nd];
		
		if (0 > nr || nr >= N || 0 > nc || nc >= M || arr[nr][nc] == 1) {
			return false;
		}
		
		r = nr;
		c = nc;
		
		return true;
	}

	static void clearFalse() { // 청소 가능할 때 
		d = (3 + d) % 4;
		int nr = r + dx[d];
		int nc = c + dy[d];
		
		if (0 <= nr && nr < N && 0 <= nc && nc < M && arr[nr][nc] == 0) {
			r = nr;
			c = nc;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine(), " ");

		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		
		for (int n = 0; n < N; n ++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int m = 0 ; m < M; m ++) {
				arr[n][m] = Integer.parseInt(st.nextToken());
			}
		}

		label:while (true) {

			if (arr[r][c] == 0) {
				arr[r][c] = 2;
				answer++;
			}
			for (int nd = 0; nd < 4; nd++) { // 4방향 탐색해서 청소 안된 구역 찾
				int nr = r + dx[nd];
				int nc = c + dy[nd];

				if (0 <= nr && nr < N && 0 <= nc && nc < M) {
					if (arr[nr][nc] == 0) { // 청소가 가능할 경우
						clearFalse();
						continue label;
					}
				}
			}
			if (!clearTrue()) break;
		}

		System.out.println(answer);
		br.close();
	}

}