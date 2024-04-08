package a0408;

import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        int[] subs = new int[]{0,0}; // 가능한 구간을 저장하는 int 배열
        int[] answer = new int[]{0,0}; // 정답 배열
        int subSize = Integer.MAX_VALUE; // 구간 사이즈
        
        HashSet<String> hs = new HashSet<>(Arrays.asList(gems)); // 집합으로 전체 보석갯수 계산
        
        int count = hs.size(); // 전체 중복없는 보석 갯수
        
        HashMap<String, Integer> hm = new HashMap<>(); // 구간에 있는 보석개수를 파악하기 위한 해쉬맵 생성
        
        //System.out.println(values);
        
        int start = 0; // 시작 구간
        int curSize = 0; // 현재 중복없는 보석개수
        
        for (int i = 0; i < gems.length; i ++) { // 전체 보석을 돌면서
            if (hm.containsKey(gems[i])) { // 이미 있는 보석일 경우 갯수 + 1
                if (hm.get(gems[i]) == 0) curSize++;
                hm.replace(gems[i], hm.get(gems[i]) + 1);
            } else {
                hm.put(gems[i], 1); // 없는 보석일 경우 새롭게 보석 추가
                curSize++; // 새로운 보석이므로 중복없는 보석갯수 + 1
            }
            
            subs[1] = i; // 구간 증가
            
            while (curSize == count) { // 만약에 현재 보석갯수가 중복없는 보석갯수랑 같을경우
            	subs[0] = start; // 시작 구간으로 구간 설정
                hm.replace(gems[start], hm.get(gems[start]) - 1); // 첫번째 구간 보석갯수 -1
                if (hm.get(gems[start]) == 0) curSize--; // 만약에 감소한 보석이 0이 될경우 현재 보석 갯수 - 1 
                start++; // 시작 구간 인덱스 + 1
                
                if (subs[1] - subs[0] < subSize) { // 구간이 더 작을 경우
                    subSize = subs[1] - subs[0]; // 현재 구간으로 크기로 변경
                    answer[0] = subs[0] + 1;
                    answer[1] = subs[1] + 1;
                }
                
                //System.out.println(Arrays.toString(answer));
            }
        }
        
        return answer;
    }
}