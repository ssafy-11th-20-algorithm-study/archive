package a0000.camp;

import java.io.*;
import java.util.*;

public class Solution_bj_14889_스타트와링크_서울_20반_권동원 {
	static int answer = Integer.MAX_VALUE;
	static int N;
	static int[][] ability;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_14889.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		boolean[] visited = new boolean[N];
		for(int i=0; i<N; i++) {
			arr[i]=i+1;
		}
		ability = new int[N][N];
		
		// 배열 입력
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for(int j=0; j<N; j++) {
				ability[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		// 조합써야함
		combination(visited, 0, N, N/2);
		
		sb.append(answer);
		System.out.println(sb);
		br.close();
	}
	static void combination(boolean[] visited, int start, int n, int r) { // 재귀로 조합
		if(r == 0) {
			answer = Math.min(answer, combination2(visited));
			return ;
		}
			
		for(int i = start; i < n; i++) {
			visited[i] = true;
			combination(visited, i + 1, n, r - 1);
			visited[i] = false;
		}
	}
	static int combination2(boolean[] visited) { //완탐으로 조합 구하기 (두개로 나누는 경우라 가능)
		int start = 0; //visited true인 팀 start
		int link = 0; //visited false인 팀 link
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(visited[i]&&visited[j]) {
					start += ability[i][j];
				} else if (!visited[i] && !visited[j]) {
					link += ability[i][j];
				}
			}
		}
		return Math.abs(start-link);
	}
}