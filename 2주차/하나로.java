import java.io.*;
import java.util.*;

public class 하나로 {

	static int N;
	static double E;
	static int[] x, y;
	static boolean[] v;
	static double answer;
	static int[] b;

	static void dfs(int i, double sum, int cnt) {
		if (answer < sum)
			return;
		if (cnt == N) {
			answer = Math.min(answer, sum);
			return;
		}

		for (int k = 0; k < N; k++) {
			if (v[k])
				continue;
			v[k] = true;
			double distance = Math.sqrt(Math.pow(x[i] - x[k], 2) + Math.pow(y[i] - y[k], 2));
			double temp = E * Math.pow(distance, 2);
			dfs(k, sum + temp, cnt + 1);
			v[k] = false;
		}
	}

	static void perm(int cnt) {
		if (cnt == N) {
			
			double sum = 0;
			
			for (int i = 1; i < N; i ++) {
				double distance = Math.sqrt(Math.pow(x[b[i]] - x[b[i - 1]], 2) + Math.pow(y[b[i]] - y[b[i - 1]], 2));
				distance = E * Math.pow(distance, 2);
				sum += distance;
			}
			
			answer = Math.min(answer, sum);
			
			return;
		}

		for (int i = 0; i < N; i++) {
			if (v[i])
				continue;
			v[i] = true;
			b[cnt] = i;
			perm(cnt + 1);
			v[i] = false;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			x = new int[N];
			y = new int[N];
			StringTokenizer sx = new StringTokenizer(br.readLine(), " ");
			StringTokenizer sy = new StringTokenizer(br.readLine(), " ");

			for (int n = 0; n < N; n++) {
				x[n] = Integer.parseInt(sx.nextToken());
				y[n] = Integer.parseInt(sy.nextToken());
			}

			E = Double.parseDouble(br.readLine());
			v = new boolean[N];
			answer = Double.MAX_VALUE;
			b = new int[N];

//			for (int n = 0; n < N; n++) {
//				v[n] = true;
//				dfs(n, 0, 1);
//				v[n] = false;
//			}
			
			perm(0);

			System.out.println((int) answer);
		}
	}

}