import java.io.*;
import java.util.*;

public class Main {
	static int n,m;
	static int[][] map;
	static int[] di = {-1,0,1,0};
	static int[] dj = {0,1,0,-1};
	static int[][] temp;
	static List<int[]> tvs;
	static int r;
	static int[] b;
	static int max = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		temp = new int[n][m];
		tvs = new ArrayList<int[]>();
		for(int i =0;i<n;i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j =0;j<m;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				temp[i][j] = map[i][j];
				if(map[i][j]!= 0 && map[i][j]!=6) {
					tvs.add(new int[] {i,j});
				}
			}
		}
		r = tvs.size();
		b = new int[r];
		comb(0,0);
		System.out.println(max);
		
	}
	
	static void checking3(int dir,int i ,int j) {
		int ni = i+di[dir];
		int nj = j+dj[dir];
		while(true) {
			if(ni <0 || ni>=n || nj <0 || nj>=m)break;
			if(map[ni][nj]==6)break;
			if(map[ni][nj]==0)map[ni][nj]= -1;
			ni += di[dir];
			nj += dj[dir];
		}
		
		ni = i+di[(dir+1)%4];
		nj = j+dj[(dir+1)%4];
		while(true) {
			if(ni <0 || ni>=n || nj <0 || nj>=m)break;
			if(map[ni][nj]==6)break;
			if(map[ni][nj]==0)map[ni][nj]= -1;
			ni += di[(dir+1)%4];
			nj += dj[(dir+1)%4];
		}
		
	}
	
	static void checking4(int dir,int i ,int j) {
		int ni = i+di[dir];
		int nj = j+dj[dir];
		while(true) {
			if(ni <0 || ni>=n || nj <0 || nj>=m)break;
			if(map[ni][nj]==6)break;
			if(map[ni][nj]==0)map[ni][nj]= -1;
			ni += di[dir];
			nj += dj[dir];
		}
		
		ni = i+di[(dir+1)%4];
		nj = j+dj[(dir+1)%4];
		while(true) {
			if(ni <0 || ni>=n || nj <0 || nj>=m)break;
			if(map[ni][nj]==6)break;
			if(map[ni][nj]==0)map[ni][nj]= -1;
			ni += di[(dir+1)%4];
			nj += dj[(dir+1)%4];
		}
		
		ni = i+di[Math.abs(dir+3)%4];
		nj = j+dj[Math.abs(dir+3)%4];
		while(true) {
			if(ni <0 || ni>=n || nj <0 || nj>=m)break;
			if(map[ni][nj]==6)break;
			if(map[ni][nj]==0)map[ni][nj]= -1;
			ni += di[Math.abs(dir+3)%4];
			nj += dj[Math.abs(dir+3)%4];
		}
	}
	
	static void checking5(int dir,int i ,int j) {
		dir = 0;
		int ni = i+di[dir];
		int nj = j+dj[dir];
		while(true) {
			if(ni <0 || ni>=n || nj <0 || nj>=m)break;
			if(map[ni][nj]==6)break;
			if(map[ni][nj]==0)map[ni][nj]= -1;
			ni += di[dir];
			nj += dj[dir];
		}
		
		ni = i+di[(dir+1)%4];
		nj = j+dj[(dir+1)%4];
		while(true) {
			if(ni <0 || ni>=n || nj <0 || nj>=m)break;
			if(map[ni][nj]==6)break;
			if(map[ni][nj]==0)map[ni][nj]= -1;
			ni += di[(dir+1)%4];
			nj += dj[(dir+1)%4];
		}
		
		ni = i+di[(dir+2)%4];
		nj = j+dj[(dir+2)%4];
		while(true) {
			if(ni <0 || ni>=n || nj <0 || nj>=m)break;
			if(map[ni][nj]==6)break;
			if(map[ni][nj]==0)map[ni][nj]= -1;
			ni += di[(dir+2)%4];
			nj += dj[(dir+2)%4];
		}
		ni = i+di[(dir+3)%4];
		nj = j+dj[(dir+3)%4];
		while(true) {
			if(ni <0 || ni>=n || nj <0 || nj>=m)break;
			if(map[ni][nj]==6)break;
			if(map[ni][nj]==0)map[ni][nj]= -1;
			ni += di[(dir+3)%4];
			nj += dj[(dir+3)%4];
		}
	}
	
	
	static void comb(int start,int cnt) {
		if(cnt==r) {
			for(int i =0;i<r;i++) {
				int[] ar = tvs.get(i);
				int type = map[ar[0]][ar[1]];
				if(type == 1) {
					int ni = ar[0]+di[b[i]];
					int nj = ar[1]+dj[b[i]];
					while(true) {
						if(ni <0 || ni>=n || nj <0 || nj>=m)break;
						if(map[ni][nj]==6)break;
						if(map[ni][nj]==0)map[ni][nj]= -1;
						ni += di[b[i]];
						nj += dj[b[i]];
					}
				}else if(type == 2) {
					if(b[i]==0) {
						int ni = ar[0]+di[0];
						int nj = ar[1]+dj[0];
						while(true) {
							if(ni <0 || ni>=n || nj <0 || nj>=m)break;
							if(map[ni][nj]==6)break;
							if(map[ni][nj]==0)map[ni][nj]= -1;
							ni += di[0];
							nj += dj[0];
						}
						
						ni = ar[0]+di[2];
						nj = ar[1]+dj[2];
						while(true) {
							if(ni <0 || ni>=n || nj <0 || nj>=m)break;
							if(map[ni][nj]==6)break;
							if(map[ni][nj]==0)map[ni][nj]= -1;
							ni += di[2];
							nj += dj[2];
						}
					}else if(b[i]==1){
						int ni = ar[0]+di[1];
						int nj = ar[1]+dj[1];
						while(true) {
							if(ni <0 || ni>=n || nj <0 || nj>=m)break;
							if(map[ni][nj]==6)break;
							if(map[ni][nj]==0)map[ni][nj]= -1;
							ni += di[1];
							nj += dj[1];
						}
						
						ni = ar[0]+di[3];
						nj = ar[1]+dj[3];
						while(true) {
							if(ni <0 || ni>=n || nj <0 || nj>=m)break;
							if(map[ni][nj]==6)break;
							if(map[ni][nj]==0)map[ni][nj]= -1;
							ni += di[3];
							nj += dj[3];
						}
					}
					
				}else if(type == 3) {
					checking3(b[i],ar[0],ar[1]);
				}else if(type == 4) {
					checking4(b[i],ar[0],ar[1]);
				}else if(type == 5) {
					checking5(b[i],ar[0],ar[1]);
				}
			}
			int count = 0;
			for(int i =0;i<n;i++) {
				for(int j =0;j<m;j++) {
//					System.out.print(map[i][j] + " ");
					if(map[i][j]==0)count++;
					map[i][j] = temp[i][j];
				}
//				System.out.println();
			}
//			System.out.println();
			max = Math.min(max, count);
			return;
		}
		for(int i = start;i<r;i++) {
			int[] ar = tvs.get(i);
			int type = map[ar[0]][ar[1]];
			if(type == 1) {
				b[cnt] = 0;
				comb(i+1,cnt+1);
				b[cnt] = 1;
				comb(i+1,cnt+1);
				b[cnt] = 2;
				comb(i+1,cnt+1);
				b[cnt] = 3;
				comb(i+1,cnt+1);
			}else if(type == 2) {
				b[cnt] = 0;
				comb(i+1,cnt+1);
				b[cnt] = 1;
				comb(i+1,cnt+1);
			}else if(type == 3) {
				b[cnt] = 0;
				comb(i+1,cnt+1);
				b[cnt] = 1;
				comb(i+1,cnt+1);
				b[cnt] = 2;
				comb(i+1,cnt+1);
				b[cnt] = 3;
				comb(i+1,cnt+1);
			}else if(type == 4) {
				b[cnt] = 0;
				comb(i+1,cnt+1);
				b[cnt] = 1;
				comb(i+1,cnt+1);
				b[cnt] = 2;
				comb(i+1,cnt+1);
				b[cnt] = 3;
				comb(i+1,cnt+1);
			}else if(type == 5) {
				b[cnt] = 1;
				comb(i+1,cnt+1);
			}
		}
		
	}

}
