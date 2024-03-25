package samsung;

import java.util.*;
import java.io.*;

/**
 * <문제>
 * 드래곤 커브를 주어진 규칙에 맞게 그린 후, 네 꼭짓점이 모두 드래곤 커브의 일부인 정사각형 개수를 구해야 합니다.
 * 드래곤 커브가 그려지는 규칙을 찾는 것이 핵심입니다.
 *  - n세대 커브를 그리는 방법은 n-1세대까지 그려진 커브를 뒤집어서 90도 돌린 것을 이어 붙인 형태입니다.
 *  - 즉, 반시계 방향으로 90도 돌린 것입니다.
 *  - 숫자로 나타낸 방향으로 말하자면, 이전까지 그린 방향을 역으로 돌며 +1을 해주면 방향을 구할 수 있습니다. (단, 3같은 경우에는 +1을 하면 0이 됩니다.)
 *
 * 풀이는 세가지 부분으로 나누어 하였습니다.
 * 1. 커브를 그리기위한 방향 구하기
 * 2. 드래곤 커브 그리기
 * 3. 정사각형 개수 세기
 */
public class B15685 {
    static final int[] dr = {0, -1, 0, 1}; //우상좌하
    static final int[] dc = {1, 0, -1, 0};
    static boolean[][] map;
    static final int MAX = 100;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        map = new boolean[MAX + 1][MAX + 1];

        int N = Integer.parseInt(br.readLine());
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            drawCurve(y, x, getDirections(d, g));
        }

        System.out.println(countSquare());
    }

    /**
     * <1. 커브를 그리기 위한 방향 구하기>
     * g세대 커브를 그리기 위해서는 g-1세대 그린 커브를 반시계 방향으로 90도 돌려야 함
     * => g-1 세대의 direction에 + 1
     */
    static List<Integer> getDirections(int d, int g) {
        List<Integer> directions = new ArrayList<>();
        directions.add(d); //0세대 방향

        while(g-- > 0) {
            for(int i = directions.size() - 1; i >= 0; i--) { //g-1 세대 방향을 역으로 돈다.
                int direction = (directions.get(i) + 1) % 4;  //반시계 방향으로 90도 돌린 값을 찾기 위해 +1
                directions.add(direction);
            }
        }

        return directions;
    }

    /**
     * <2. 드래곤 커브 그리기>
     * 주어진 방향 리스트를 바탕으로 이동하며 해당 좌표에 커브가 그려짐을 표시한다.
     */
    static void drawCurve(int r, int c, List<Integer> directions) {
        map[r][c] = true;
        for(int d : directions) {
            r += dr[d];
            c += dc[d];
            map[r][c] = true;
        }
    }

    /**
     * <3. 정사각형 개수 세기>
     * 정사각형의 가장 왼쪽 위의 꼭짓점을 기준으로 정사각형이 그려진다면 카운팅을 해준다.
     * 99까지만 탐색해서 ArrayIndexOutOfBoundsException 방지
     */
    static int countSquare() {
        int ans = 0;
        for(int i = 0; i < MAX; i++) {
            for(int j = 0; j < MAX; j++) {
                if(map[i][j] && map[i + 1][j] && map[i + 1][j + 1] && map[i][j + 1]) {
                        ans++;
                }
            }
        }
        return ans;
    }
}
