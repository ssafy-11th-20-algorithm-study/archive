package a0216;

import java.io.*;
import java.util.*;

public class Main_bj_15684_사다리_조작_서울_20반_심재운 {

	static int N, M, H;
	static int[][] arr;
	static int answer;
	static boolean flag;
	static int limit; // 제한

	static boolean ladder(int y) {
		int result = y;
		int x = 1;
		while (x <= H) {
			if (arr[x][result] != 0) {
				result = arr[x][result];
			}
			x++;
		}
		return y == result ? true : false;
	}

	static void perm(int cnt) {
		if (cnt == limit) {
			for (int i = 1; i <= N; i ++) {
				if (!ladder(i)) return;
			}
			
			flag = true;
			answer = limit;
			return;
		}
		for (int h = 1; h <= H; h++) {
			for (int n = 1; n < N; n++) {
				if (arr[h][n] != 0 || arr[h][n + 1] != 0) {
					continue;
				}
				arr[h][n] = n + 1;
				arr[h][n + 1] = n;
				perm(cnt + 1);
				arr[h][n] = 0;
				arr[h][n + 1] = 0;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/0216/Main_bj_15684_사다리_조작_서울_20반_심재운.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		arr = new int[H + 1][N + 1];

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			arr[x][y] = y + 1;
			arr[x][y + 1] = y;
		}

		limit = 0;
		while (limit <= 3 && !flag) {
			perm(0);
			limit++;
		}
		System.out.println(flag == false ? -1 : answer);
		br.close();
	}
}