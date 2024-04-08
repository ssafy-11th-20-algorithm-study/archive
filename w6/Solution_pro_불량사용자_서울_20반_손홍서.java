package study;

import java.util.*;

/**
 * 문제
 * 응모자 아이디와 불량 사용자 아이디가 주어진다.
 * 불량 사용자 목록에 매칭된 응모자아이디를 제재 아이디라고 한다.
 *
 * 제재 아이디 목록의 가능한 경우의 수를 구하라!
 * 단, 나열된 순서와 상관없이 내용이 동일하면 같은 것이다.
 *
 * 1. 각 불량 사용자 아이디마다 가능한 사용자 아이디 구하기
 * 2. 제재 아이디 목록 구하기 -> 완탐
 */
class Solution_pro_불량사용자_서울_20반_손홍서 {
    static int M;
    static Set<Integer> ansSet;
    static ArrayList<Integer>[] ableList; // 불량 사용자 의심 아이디 목록: 0번 인덱스의 불량 사용자 아이디 조건에 부합하는 사용자 아이디를 리스트로 저장
    public int solution(String[] user_id, String[] banned_id) {
        M = banned_id.length;
        ableList = new ArrayList[M];
        for(int i = 0; i < M; i++) {
            ableList[i] = new ArrayList<>();
        }

        //1. 각 불량 사용자 아이디마다 가능한 사용자 아이디 구하기
        for(int i = 0; i < M; i++) {
            for(int j = 0; j < user_id.length; j++) {
                if(isValid(banned_id[i], user_id[j])) {
                    ableList[i].add(j);
                }
            }
        }

        ansSet = new HashSet<>();
        //2. 제 아이디 목록 구하기
        countAns(0, 0);
        return ansSet.size();
    }

    //해당 유저 아이디가 불량 아이디가 될 수 있다면 true리턴 아니면 false 리턴
    static boolean isValid(String banned, String s) {

        if(banned.length() != s.length()) { //일단 두 아이디의 길이가 다르면 false 리턴
            return false;
        }

        for(int i = 0; i < banned.length(); i++) {
            if(banned.charAt(i) != '*' && banned.charAt(i) != s.charAt(i)) {
                //만약, 불량아이디의 해당 위치 문자가 *이 아닌데 다르다면 false를 리턴 ex. banned = a*b, s = bbb 에서 0번 문자같은 경우(a =/= b)
                return false;
            }
        }

        return true;
    }

    //비트마스킹을 이용해 사용한 아이디 인덱스를 표시!
    static void countAns(int cnt, int v) {
        //모든 불량 아이디에 적합한 아이디를 매칭했다면 cnt == M이므로 set에 넣어주고 리턴
        if(cnt == M) {
            //set에 넣어서 알아서 중복은 제거되도록 만든다.
            ansSet.add(v);
            return;
        }
        //각 불량 아이디에 적합한 제재 아이디를 고른다.
        for(int i = 0; i < ableList[cnt].size(); i++) {
            int now = ableList[cnt].get(i);
            //만약 해당 아이디를 이미 이전에 사용했다면 또 사용할 수 없으므로 다음으로 넘어간다.
            if((v & (1 << now)) != 0) {
                continue;
            }
            countAns(cnt + 1, (v | (1 << now)));
        }
    }
}
