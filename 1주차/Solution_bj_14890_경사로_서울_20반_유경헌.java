package com.algorithm.study.eclipse;

import java.io.*;
import java.util.*;

public class Solution_bj_14890_경사로_서울_20반_유경헌 {
	static int N, L , map[][], ans;
	static boolean[][] check;

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		solve();
		System.out.println(ans);

	}

	static void solve() {
		// 행 확인
		check = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			if (check_row(i)) {
				ans++;
			}
		}
		// 열 확인
		check = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			if (check_col(i)) {
				ans++;
			}
		}
	}

	// 범위 제한 확인
	static boolean limit(int x) {
		return x >= 0 && x < N;
	}

	static boolean check_row(int r) {
		for (int j = 0; j < N - 1; j++) {
			int nxt = j + 1;
			if (map[r][j] == map[r][nxt])
				continue;
			else if (map[r][j] + 1 == map[r][nxt]) {
				if (limit(j - L + 1)) {
					for (int k = j - L + 1; k <= j; k++) {
						if (!check[r][k] && map[r][k] == map[r][j]) {
							check[r][k] = true;
						} else {
							return false;
						}
					}
				} else {
					return false;
				}
			} else if (map[r][j] - 1 == map[r][nxt]) {
				if (limit(nxt + L - 1)) {
					for (int k = nxt; k <= nxt + L - 1; k++) {
						if (!check[r][k] && map[r][k] == map[r][nxt]) {
							check[r][k] = true;
						} else {
							return false;
						}
					}
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
		return true;
	}

	static boolean check_col(int c) {
		for (int i = 0; i < N - 1; i++) {
			int nxt = i + 1;
			if (map[i][c] == map[nxt][c])
				continue;
			else if (map[i][c] + 1 == map[nxt][c]) {
				if (limit(i - L + 1)) {
					for (int k = i - L + 1; k <= i; k++) {
						if (!check[k][c] && map[k][c] == map[i][c]) {
							check[k][c] = true;
						} else {
							return false;
						}
					}
				} else {
					return false;
				}
			} else if (map[i][c] - 1 == map[nxt][c]) {
				if (limit(nxt + L - 1)) {
					for (int k = nxt; k <= nxt + L - 1; k++) {
						if (!check[k][c] && map[k][c] == map[nxt][c]) {
							check[k][c] = true;
						} else {
							return false;
						}
					}
				} else {
					return false;
				}
			} else {
				return false;
			}

		}
		return true;
	}

}
