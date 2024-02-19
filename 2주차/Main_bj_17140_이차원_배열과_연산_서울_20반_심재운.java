package a0219;

import java.io.*;
import java.util.*;

public class Main_bj_17140_이차원_배열과_연산_서울_20반_심재운 {

	static int r, c, k;
	static int[][] arr = new int[3][3];

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/0219/Main_bj_17140_이차원_배열과_연산_서울_20반_심재운.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		for (int x = 0; x < 3; x++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int y = 0; y < 3; y++) {
				arr[x][y] = Integer.parseInt(st.nextToken());
			}
		}

		int count = 0;

//		while (arr.get(r).get(c) != k) {
//			arrFunc();
//			if (count == 100)
//				break;
//			count++;
//		}
		arrFunc();
		
		for (int[] a : arr) System.out.println(Arrays.toString(a));

	}

	static void arrFunc() {
		// R 연산

		List<ArrayList<Integer>> temp = new ArrayList<>();
		int maxValue = 0;

		if (arr.length > arr[0].length) {
			for (int x = 0; x < arr.length; x++) {
				int[] counter = new int[101];
				List<int[]> row = new ArrayList<>();
				for (int y = 0; y < arr[0].length; y++) {
					counter[arr[x][y]] += 1;
				}
				for (int i = 0; i < 101; i++) {
					if (counter[i] > 0 && i > 0)
						row.add(new int[] { i, counter[i] });
				}

				Collections.sort(row, (f, l) -> f[1] == l[1] ? f[0] - l[0] : f[1] - l[1]);
				ArrayList<Integer> rowTemp = new ArrayList<>();
				for (int r = 0; r < row.size(); r++) {
					rowTemp.add(row.get(r)[0]);
					rowTemp.add(row.get(r)[1]);
				}
				temp.add(rowTemp);
				maxValue = Math.max(maxValue, rowTemp.size());
			}

			for (int i = 0; i < temp.size(); i++) {
				if (temp.get(i).size() < maxValue) {
					int limit = maxValue - temp.get(i).size();
					for (int k = 0; k < limit; k++) {
						temp.get(i).add(0);
					}
				}
			}

			arr = new int[temp.size()][temp.get(0).size()];

			for (int x = 0; x < temp.size(); x++) {
				for (int y = 0; y < temp.get(0).size(); y++) {
					arr[x][y] = temp.get(x).get(y);
				}
			}

		} else { // C 연산
			for (int y = 0; y < arr[0].length; y++) {
				int[] counter = new int[101];
				List<int[]> column = new ArrayList<>();
				for (int x = 0; x < arr.length; x++) {
					counter[arr[x][y]] += 1;
				}
				for (int i = 0; i < 101; i++) {
					if (counter[i] > 0 && i > 0)
						column.add(new int[] { i, counter[i] });
				}

				Collections.sort(column, (f, l) -> f[1] == l[1] ? f[0] - l[0] : f[1] - l[1]);
				ArrayList<Integer> columnTemp = new ArrayList<>();
				for (int r = 0; r < column.size(); r++) {
					columnTemp.add(column.get(r)[0]);
					columnTemp.add(column.get(r)[1]);
				}
				temp.add(columnTemp);
				maxValue = Math.max(maxValue, columnTemp.size());
			}

			for (int i = 0; i < temp.size(); i++) {
				if (temp.get(i).size() < maxValue) {
					int limit = maxValue - temp.get(i).size();
					for (int k = 0; k < limit; k++) {
						temp.get(i).add(0);
					}
				}
			}

			arr = new int[temp.get(0).size()][temp.size()];

			for (int y = 0; y < temp.size(); y++) {
				for (int x = 0; x < temp.get(0).size(); x++) {
					arr[x][y] = temp.get(x).get(y);
				}
			}
		}
	}

}