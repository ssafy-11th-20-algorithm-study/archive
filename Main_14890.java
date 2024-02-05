package homework;

import java.io.*;
import java.util.*;


public class Main_bj_14890_경사로_서울_20반_김도한 {
	static int[][] map;
	static boolean[][] checker;
	static boolean[][] checker2;
	static int n,l;
	static int answer=0;
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		
		map = new int[n][n];
		checker = new boolean[n][n];
		checker2 = new boolean[n][n];
		for(int i  =0;i<n;i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j = 0;j<n;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//가로 확인
		for(int i =0;i<n;i++) {
			boolean complete = true;
		flag : for(int j =1;j<n;j++) {
				if(map[i][j]==map[i][j-1])continue;
				else if(map[i][j]==map[i][j-1]-1) { // 작아진 경우 - j ~ j +l-1
					if(j+l-1 >n-1) { // 범위 판
						complete = false;
						break;
					}
					for(int k =j;k<=j+l-1;k++) { //경사로 가능여부 체크 
						if(map[i][k]!=map[i][j] || checker[i][k]) {
							complete = false;
							break flag;
						}
					}
					for(int k =j;k<=j+l-1;k++) { //경사로 설치 
						checker[i][k] = true;
					}
				}else if(map[i][j]-1==map[i][j-1]) { // 커진 경우 - j-1 ~ j-l
					if(j-l <0) {
						complete = false;
						break;
					}
					for(int k =j-1;k>=j-l;k--) {
						if(map[i][k]!=map[i][j-1] || checker[i][k]) {
							complete = false;
							break flag;
						}
					}
					for(int k =j-1;k>=j-l;k--) {
						checker[i][k] = true;
					}
				}else {
					complete = false;
					break;
				} 
			}
		if(complete) answer++;
		}
		//세로 확인 
				for(int i =0;i<n;i++) {
					boolean complete = true;
				flag : for(int j =1;j<n;j++) {
						if(map[j][i]==map[j-1][i])continue;
						else if(map[j][i]==map[j-1][i]-1) { // 작아진 경우 - j ~ j +l-1
							if(j+l-1 >n-1) { // 범위 판
								complete = false;
								break;
							}
							for(int k =j;k<=j+l-1;k++) {
								if(map[k][i]!=map[j][i] || checker2[k][i]) {
									complete = false;
									break flag;
								}
							}
							for(int k =j;k<=j+l-1;k++) {
								checker2[k][i] = true;
							}
						}else if(map[j][i]-1==map[j-1][i]) { // 커진 경우 - j-1 ~ j-l
							if(j-l <0) {
								complete = false;
								break;
							}
							for(int k =j-1;k>=j-l;k--) {
								if(map[k][i]!=map[j-1][i]|| checker2[k][i]) {
									complete = false;
									break flag;
								}
							}
							for(int k =j-1;k>j-l;k--) {
								checker2[k][i] = true;
							}
						}else {
							complete = false;
							break;
						} 
					}
					if(complete)answer++;
				}
		
		System.out.println(answer);
	}

}
	/* 경사로는 낮은 곳에만 놓을 수 있다.
	 * 겹경사로는 불가
	 * 가로,세로 한번식 탐색
	 * 탐색 방법 : 2번 칸부터 전칸과 비교 :
	 * 높아진다 : 1.차이가1  ->  자신포함뒤로 L개 확인 
	 * 			2.차이가 1보다 크다. -> 바로 종료 
	 * 낮아진다 : 1. 차이가 1보다 크다 -> 바로 종료
	 * 			2. 차이가 1 -> 앞에서 확인
	 * 경사로가 생성된 부분은 별도의 맵에서 체크  // 처음에는 가로세로 중복되면 안되는줄 알았으나 별개의 경사로로 취급한다.
     */