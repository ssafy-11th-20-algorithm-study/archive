package a0403;

import java.io.*;
import java.util.*;

public class Solution_프로_5643_키_순서_서울_20반_심재운 {
	static int N, M, a, b;
	static int[][] g;

	static void dfs(int idx, boolean[] v, int sg) {

		for (int i = 1; i <= N; i++) {
			if (g[idx][i] == sg && !v[i]) {
				if (sg == 1) {
					v[i] = true;
					a++;
					dfs(i, v, sg);
				} else {
					v[i] = true;
					b++;
					dfs(i, v, sg);
				}
			}
		}

	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/0403/Solution_프로_5643_키_순서_서울_20반_심재운.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			int answer = 0;

			int[] order = new int[N + 1];
			g = new int[N + 1][N + 1];

			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine(), " ");
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				order[b] += 1;
				g[a][b] = 1; // a가 b 보다 키가 작을 때
				g[b][a] = 2; // b가 a 보다 키가 클 때
			}

			for (int i = 1; i <= N; i++) {
				boolean[] v1 = new boolean[N + 1];
				boolean[] v2 = new boolean[N + 1];

				v1[i] = true;
				v2[i] = true;

				a = 0;
				b = 0;

				dfs(i, v1, 1);
				dfs(i, v2, 2);

				if (a + b + 1 == N) answer++;
			}
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

}
