package a0000.study;

import java.io.*;
import java.util.*;

public class Solution_bj_16235_나무재테크_서울_20반_권동원 {

	static int N, M, K;
	static int[][] add;
	static int[][] map;
	
	static int[] dr = {-1, 1, 0, 0, -1, 1, -1, 1};
	static int[] dc = {0, 0, -1, 1, -1, -1, 1, 1};
	static Queue<Tree> q;
	
	static class Tree implements Comparable<Tree>{
		int x;
		int y;
		int age;
		public Tree(int x, int y, int age) {
			this.x = x;
			this.y = y;
			this.age = age;
		}
		@Override
		public int compareTo(Tree o) {
			return Integer.compare(this.age, o.age);
		}
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_16235.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][N]; 
		add = new int[N][N];
		q = new LinkedList<>();
		
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				add[i][j] = Integer.parseInt(st.nextToken());
				map[i][j] = 5; // 가장 처음에 양분은 모든 칸에 5만큼 들어있다.
			}
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());
			q.add(new Tree(x-1, y-1, age)); // r과 c는 1부터 시작하기 때문에 -1
		}
		
		// 한번 정렬 해놓으면 정렬 필요x 그래서 우선순위큐 안 써도됨
		Collections.sort((List<Tree>) q);
		
		for(int a=0; a<K; a++) { // K년 만큼 반복
			ArrayList<Tree> dead = new ArrayList<>();
			// 봄
			int q_len = q.size();
			
			while(q_len-- > 0) {
				Tree tree = q.poll(); // 정렬 해놔서 가장 어린 나무부터 나옴
				if(tree.age <= map[tree.x][tree.y]) { // 양분이 더 크거나 같으면
					map[tree.x][tree.y] -= tree.age;
					q.add(new Tree(tree.x, tree.y, tree.age+1)); // q에 age+1된 tree추가
				} else { // 양분이 작으면
					dead.add(new Tree(tree.x, tree.y, tree.age)); //dead에 추가
				}
			}
			
			// 여름
			for(Tree tree:dead) {
				map[tree.x][tree.y] += tree.age/2;// 죽은 나무마다 나이를 2로 나눈 값이 나무가 있던 칸에 양분으로 추가
			}
			ArrayList<Tree> parent = new ArrayList<>(); // 부모 나무 리스트
			q_len = q.size();
			
			// 가을
			while(q_len-- > 0) {
				Tree tree = q.poll();
				parent.add(tree);
				if(tree.age % 5 == 0) { // 나이가 5의 배수이면
					for(int d=0; d<8; d++) { // 8방탐색
						int nr = tree.x + dr[d];
						int nc = tree.y + dc[d];
						if(0<=nr&&nr<N && 0<=nc&&nc<N) { //범위 안이면
							q.add(new Tree(nr,nc,1)); // 나이가 1인 tree 추가
						}
					}
				}
			}
			
			for(Tree tree:parent) { //q에다 부모트리리스트 추가
				q.add(tree);
			}
			
			// 겨울
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					map[i][j] += add[i][j]; //양분 추가
				}
			}
		}
		
		System.out.println(q.size());
		
	}
}
