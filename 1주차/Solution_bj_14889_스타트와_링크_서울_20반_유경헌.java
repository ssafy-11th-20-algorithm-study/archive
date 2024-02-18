package com.algorithm.study.eclipse;

import java.io.*;
import java.util.*;

public class Solution_bj_14889_스타트와_링크_서울_20반_유경헌 {
	static int N, ans, level[][];
	static boolean vis[];

	public static void main(String args[]) throws Exception {
		//System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		level = new int[N][N];
		vis = new boolean[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				level[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		ans = Integer.MAX_VALUE;
		backTracking(0, 0);
		System.out.println(ans);
	}
	
	static void backTracking(int depth, int idx) {
		
		// N/2명 선택 되면 점수 계산해서 답 갱신
		if (depth == N / 2) {
			int t1 = 0, t2 = 0;
			// 두명씩 확인하면 된다
			for (int i = 0; i < N - 1; i++) {
				for (int j = i + 1; j < N; j++) {
					if (vis[i] && vis[j])
						t1 += level[i][j] + level[j][i];
					else if (!vis[i] && !vis[j])
						t2 += level[i][j] + level[j][i];
				}
			}
			ans = Math.min(ans, Math.abs(t1 - t2));
			return;
		}

		// 팀 만들기
		for (int i = idx; i < N; i++) {
			if (!vis[i]) {
				vis[i] = true;
				backTracking(depth + 1, i + 1);
				vis[i] = false;
			}
		}
	}
}