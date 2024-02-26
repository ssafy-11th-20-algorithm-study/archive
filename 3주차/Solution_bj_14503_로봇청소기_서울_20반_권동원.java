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
		cnt++;
		visited[r][c]=true;
		while(true) {
			if(isCanGo(r, c)) {
				cnt++;
			}
			else if(!canBack()) {
				break;
			}
		}
	}
	
	static boolean isCanGo(int x, int y) {
		for(int i=0; i<4; i++) {
			d=(d+3)%4;
			int nr = r + dr[d];
			int nc = c + dc[d];
			if(nr<0 || nr>=N || nc<0 || nc>=M) continue;
			if(map[nr][nc]==0 && !visited[nr][nc]) {
				r=nr;
				c=nc;
				map[nr][nc]=2;
				return true;
			}
		}
		return false;
	}
	
	static boolean canBack() {
		int nr = r + dr[(d+2)%4];
		int nc = c + dc[(d+2)%4];
		if(nr<0 || nr>=N || nc<0 || nc>=M) return false;
		if(map[nr][nc]==0 || map[nr][nc]==2) {
			r=nr;
			c=nc;
			return true;
		}
		return false;
	}
}