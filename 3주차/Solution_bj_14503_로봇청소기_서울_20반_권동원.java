package a0000.camp;

import java.io.*;
import java.util.*;

public class Solution_bj_14503_로봇청소기_서울_20반_권동원 {
	static int N,M;
	static int r,c,d;
	static int cnt;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr= {-1,0,1,0}; //북 동 남 서
	static int[] dc= {0,1,0,-1};
	
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_14503.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		// N 입력
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		
		st = new StringTokenizer(br.readLine()," ");
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		
		//배열 입력
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		process();
		sb.append(cnt);
        	System.out.println(sb);
        	br.close();
	}
	
	static void process() {
		cnt++; // 처음은 무조건 청소 가능하기 때문에 +1해줌
		visited[r][c]=true;
		while(true) {
			if(isCanGo(r, c)) { // 앞으로 갈 수 있으면 가고 +1
				cnt++;
			}
			else if(!canBack()) { // 앞으로 갈 수 없으며, 뒤로 갈수 있으면 가고, 안되면 멈춤
				break;
			}
		}
	}
	
	// 청소 안한곳:0
	// 청소 한곳: 2
	// 벽: 1
	static boolean isCanGo(int x, int y) {
		for(int i=0; i<4; i++) {
			d=(d+3)%4; // 반시계 90도 예) 0북->3서, 1동->0북, 2남->1동, 3서->2남
			int nr = r + dr[d];
			int nc = c + dc[d];
			if(nr<0 || nr>=N || nc<0 || nc>=M) continue;  // 범위 넘어가면 넘어감
			if(map[nr][nc]==0 && !visited[nr][nc]) { // 청소한적 없으면(방문x && map==0)
				r=nr;
				c=nc;
				map[nr][nc]=2; // 청소하면 2로 바꿔줌
				return true;
			}
		}
		return false;
	}
	
	static boolean canBack() {
		int nr = r + dr[(d+2)%4]; // 뒤돌기 0북->2남, 1동->3서, 2남->0북, 3서->1동
		int nc = c + dc[(d+2)%4];
		if(nr<0 || nr>=N || nc<0 || nc>=M) return false; // 범위 넘어가면 false 리턴해서 멈춤
		if(map[nr][nc]==0 || map[nr][nc]==2) { // 갈 수 있으면(map== 0 || map== 2)
			r=nr;
			c=nc;
			return true; // 갈수 있으니 true 리턴
		}
		return false;
	}
}
