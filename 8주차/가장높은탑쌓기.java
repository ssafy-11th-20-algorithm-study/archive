package a0425;

import java.io.*;
import java.util.*;

class Brick implements Comparable<Brick> { // 벽돌 클래스
	int id, weight, area, height;

	public Brick(int id, int area, int height, int weight) {
		this.id = id;
		this.area = area;
		this.height = height;
		this.weight = weight;
	}

	public String toString() {
		return "";
	}

	@Override
	public int compareTo(Brick o) {
		return this.weight - o.weight;
	}
}

public class 가장높은탑쌓기 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/0425/가장높은탑쌓기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		ArrayList<Brick> list = new ArrayList<>();
		list.add(new Brick(0, 0, 0, 0)); // 비교를 위한 default 벽돌 쌓기

		StringTokenizer st;

		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine(), " ");
			int area = Integer.parseInt(st.nextToken());
			int height = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			list.add(new Brick(n + 1, area, height, weight)); // 벽돌 하나씩 추가
		}

		Collections.sort(list); // 무게 순서대로 오름차순으로 정렬

		int[] dp = new int[N + 1]; // 저장해놓을 dp 배열

		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < i; j++) {
				if (list.get(i).area > list.get(j).area) { // 벽돌이 쌓을 수 있는 벽돌일 경우 
					dp[i] = Math.max(dp[i], dp[j] + list.get(i).height); // 합을 더하여 쌓는다
				}
			}
		}

		int maxHeight = -1;

		for (int i = 0; i <= N; i++) { // 가장 높은 벽돌 높이를 찾는다.
			if (maxHeight < dp[i])
				maxHeight = dp[i];
		}

		int index = N;
		ArrayList<Integer> result = new ArrayList<>(); // 정답 배열

		while (index != 0) { // index 0일때 까지
			if (maxHeight == dp[index]) { // 역으로 감산
				result.add(list.get(index).id);
				maxHeight -= list.get(index).height;
			}
			index--;
		}
		System.out.println(result.size());
		for (int i = result.size() - 1; i >= 0; i--) {
			System.out.println(result.get(i));
		}

	}

}
