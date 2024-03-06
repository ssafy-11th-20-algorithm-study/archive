package a0208;

import java.io.*;
import java.util.*;

public class Main_bj_16234_인구_이동_서울_20반_심재운 {

	static int N, L, R;
	static int[][] maps;
	static boolean flag;
	static boolean[][] v;
	static final int[] dx = { 0, 0, -1, 1 };
	static final int[] dy = { 1, -1, 0, 0 };

	static void moving(int y, int x) {
		ArrayDeque<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] { y, x });
		v[y][x] = true;
		ArrayList<int[]> sub = new ArrayList<>();
		sub.add(new int[] { y, x });
		int sum = maps[y][x];

		while (!queue.isEmpty()) {
			int[] rc = queue.poll();
			int r = rc[0];
			int c = rc[1];

			for (int d = 0; d < 4; d++) {
				int nr = r + dy[d];
				int nc = c + dx[d];

				if (0 <= nr && nr < N && 0 <= nc && nc < N && !v[nr][nc]) {
					int gap = Math.abs(maps[r][c] - maps[nr][nc]);
					if (L <= gap && gap <= R) {
						v[nr][nc] = true;
						sum += maps[nr][nc];
						queue.offer(new int[] { nr, nc });
						sub.add(new int[] { nr, nc });
					}
				}
			}
		}
		if (sub.size() == 1)
			return;
		sum = sum / sub.size();
		flag = true;
		for (int s = 0; s < sub.size(); s++) {
			maps[sub.get(s)[0]][sub.get(s)[1]] = sum;
		}
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/0208/Main_bj_16234_인구_이동_서울_20반_심재운.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		maps = new int[N][N];

		for (int y = 0; y < N; y++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int x = 0; x < N; x++) {
				maps[y][x] = Integer.parseInt(st.nextToken());
			}
		}

		int answer = 0;

		while (true) {
			flag = false;
			v = new boolean[N][N];
			for (int y = 0; y < N; y++) {
				for (int x = 0; x < N; x++) {
					if (!v[y][x])
						moving(y, x);
				}
			}
			if (!flag)
				break;

			answer++;
		}

		System.out.println(answer);
	}

}
