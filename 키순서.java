import java.io.*;
import java.util.*;

public class Solution {
	
	static int n,m;
	static int[][] arr;
	static int[][] rra;
	static int cnt;
	
	static void right(int i,boolean[] visit) {
		visit[i] = true;
		for(int k = 1;k<=n;k++) {
			if(arr[i][k]==1 && !visit[k]) {
				cnt++;
				right(k,visit);
			}
		}
	}
	static void left(int i,boolean[] visit) {
		visit[i] = true;
		for(int k = 1;k<=n;k++) {
			if(rra[i][k]==1 && !visit[k]) {
				cnt++;
				left(k,visit);
			}
		}
	}
	static void find(int i) {
		cnt = 0;
		right(i,new boolean[n+1]);
		left(i,new boolean[n+1]);
	}
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb;
		StringTokenizer st;
		
		int tc = Integer.parseInt(br.readLine());
		for(int i =1;i<=tc;i++) {
			int answer = 0;
			n = Integer.parseInt(br.readLine());
			m = Integer.parseInt(br.readLine());
			arr = new int[n+1][n+1];
			rra = new int[n+1][n+1];
			for(int j=0;j<m;j++) {
				st = new StringTokenizer(br.readLine()," ");
				int l = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				arr[l][r] = 1;
				rra[r][l] = 1;
			}
			
			for(int k =1;k<=n;k++) {
				find(k);
				if(cnt==n-1)answer++;
			}
			
			sb = new StringBuilder();
			sb.append("#").append(i).append(" ").append(answer);
			System.out.println(sb.toString());
		}
		
	}

}
