package a0221;

import java.io.*;
import java.util.*;

public class Main_bj_14891_톱니바퀴_서울_20반_심재운 {

	static int[][] gears = new int[4][8];

	static void timeDir(int num) { // 시계 방향
		int[] temp = new int[8];
		temp[0] = gears[num][7];

		for (int i = 1; i < 8; i++) {
			temp[i] = gears[num][i - 1];
		}

		gears[num] = temp;
	}

	static void otherTimeDir(int num) { // 반시계 방향
		int[] temp = new int[8];
		temp[7] = gears[num][0];

		for (int i = 0; i < 7; i++) {
			temp[i] = gears[num][i + 1];
		}

		gears[num] = temp;
	}

	static int signal(int s) {
		return s * -1;
	}

	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("res/0221/"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int y = 0; y < 4; y++) {

			String str = br.readLine();

			for (int x = 0; x < 8; x++) {
				gears[y][x] = str.charAt(x) - '0';
			}
		}

		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;

		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine(), " ");
			ArrayList<Integer[]> sub = new ArrayList<>();
			int num = Integer.parseInt(st.nextToken()) - 1;
			int dir = Integer.parseInt(st.nextToken());

			sub.add(new Integer[] { num, dir });
			int d = signal(dir);
			for (int l = num + 1; l < 4; l++) {
				if (gears[l - 1][2] == gears[l][6])
					break;
				sub.add(new Integer[] { l, d });
				d = signal(d);
			}
			d = signal(dir);
			for (int f = num - 1; f >= 0; f--) {
				if (gears[f][2] == gears[f + 1][6])
					break;
				sub.add(new Integer[] { f, d });
				d = signal(d);
			}

			for (int k = 0; k < sub.size(); k++) {
				if (sub.get(k)[1] == 1) {
					timeDir(sub.get(k)[0]);
				} else {
					otherTimeDir(sub.get(k)[0]);
				}
			}
			
			//for (Integer[] a: sub) System.out.println(Arrays.toString(a));
		}

		int answer = 0;
		for (int i = 0; i < 4; i++) {
			if (gears[i][0] == 0) continue;
			answer += Math.pow(2, gears[i][0] * i);
		}

		System.out.println(answer);
		br.close();
	}
}
