package com.algorithm.study.eclipse;

import java.io.*;
import java.util.*;

public class Solution_bj_15684_사다리_조작_서울_20반_유경헌 {
	static int N, M, ans;
	static int[][] ladder;

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		ladder = new int[N][M];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			// 위치에 따라 표시 다르게
			ladder[a - 1][b - 1] = 1;
			ladder[a - 1][b] = 2;
		}
		ans = 4;
		ladder_make(0, 0, 0);
		System.out.print(ans == 4 ? -1 : ans);
	}

	static boolean ladder_check() {
		for (int i = 0; i < M; i++) {
			// 열을 조회하며 제대로 도착하는지 확인
			if (!line_check(i, i))
				return false;
		}
		return true;
	}

	static boolean line_check(int y, int st) {
		// 1이면 ++, 2면 --
		for (int x = 0; x < N; x++) {
			if (ladder[x][y] == 1) {
				y++;
			} else if (ladder[x][y] == 2) {
				y--;
			}
		}
		return y == st ? true : false;
	}

	static void ladder_make(int cnt, int row, int col) {
		if (cnt >= ans)
			return;
		if (ladder_check()) {
			ans = Math.min(ans, cnt);
			return;
		}
		for (int i = row; i < N; i++) {
			for (int j = i == row ? col : 0; j < M - 1; j++) {
				if (ladder[i][j] == 0 && ladder[i][j + 1] == 0) {
					ladder[i][j] = 1;
					ladder[i][j + 1] = 2;
					ladder_make(cnt + 1, i, j + 2);
					ladder[i][j] = 0;
					ladder[i][j + 1] = 0;
				}
			}
		}
	}
}