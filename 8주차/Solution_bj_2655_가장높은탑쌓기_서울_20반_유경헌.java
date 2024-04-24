package com.algorithm.study.eclipse;

import java.util.*;
import java.io.*;

public class Solution_bj_2655_가장높은탑쌓기_서울_20반_유경헌 {
	static int N;

	static class Block {
		int wi, he, we;

		public Block(int wi, int he, int we) {
			super();
			this.wi = wi;
			this.he = he;
			this.we = we;
		}
	}

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		Block[] blocks = new Block[N + 1];
		int dp[][] = new int[N + 1][N + 1]; // D[N][M] : N번째로 M번 블록을 쌓았을 때 최대 높이
		int pre[][] = new int[N + 1][N + 1]; // P[N][M] : N번째로 M번 블록을 쌓기전 블록의 번호
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int wi = Integer.parseInt(st.nextToken());
			int he = Integer.parseInt(st.nextToken());
			int we = Integer.parseInt(st.nextToken());
			blocks[i] = new Block(wi, he, we);
		}
		int maxK = 0, maxI = 0, maxV = 0;
		for (int i = 1; i <= N; i++) {
			dp[1][i] = blocks[i].he;
			if (maxV < dp[1][i]) {
				maxK = 1;
				maxI = i;
				maxV = dp[1][i];
			}
		}
		for (int k = 2; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (blocks[i].we < blocks[j].we && blocks[i].wi < blocks[j].wi) {
						if (dp[k][i] < dp[k - 1][j] + blocks[i].he) {
							dp[k][i] = dp[k - 1][j] + blocks[i].he;
							pre[k][i] = j;
						}
					}
					if (maxV < dp[k][i]) {
						maxK = k;
						maxI = i;
						maxV = dp[k][i];
					}
				}
			}
		}
		System.out.println(maxK);
		ArrayDeque<Integer> s = new ArrayDeque<>();
		while (maxK != 0) {
			s.offer(maxI);
			maxI = pre[maxK][maxI];
			maxK--;
		}
		for (int v : s) {
			System.out.println(v);
		}
	}
}