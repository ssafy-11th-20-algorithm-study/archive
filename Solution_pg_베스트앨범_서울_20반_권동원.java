import java.util.*;
import java.io.*;

class Solution {
    public List<Integer> solution(String[] genres, int[] plays) {
        List<Integer> ansList = new ArrayList<>(); // 정답 리스트 (나중에 배열로 변환)
        int N = genres.length;
        HashMap<String, Integer> totalMap = new HashMap<>(); // key 장르 str, value에 플레이 시간합 int map 만들기
        HashMap<String, List<int[]>> indexMap = new HashMap<>(); // key 장르 str, value에 play시간 리스트 map 만들기
        List<int[]> list; // 임시 리스트
        
        for(int i=0; i<N; i++) { // totalMap과 indexMap 만들기
            if(!indexMap.containsKey(genres[i])){ // 장르가 키에 포함 안되어 있으면
                totalMap.put(genres[i], plays[i]); // totalMap 초기화
                
                list = new ArrayList<>(); // indexMap 초기화
                list.add(new int[] {i, plays[i]});
                indexMap.put(genres[i], list);
            } else { // 포함 되어 있으면
                totalMap.put(genres[i], totalMap.get(genres[i])+plays[i]); // totalMap 변경
                
                list = new ArrayList<>(indexMap.get(genres[i])); // indexMap 변경
                list.add(new int[] {i, plays[i]});
                indexMap.put(genres[i], list);
            }
        }
        List<String> keys = new ArrayList<>(totalMap.keySet()); // totalMap의 키 리스트
        Collections.sort(keys, (o1,o2) -> totalMap.get(o2) - totalMap.get(o1)); // 내림차순 정렬
        
        for(String key: keys) {
            list = indexMap.get(key); // indexMap의 value인 리스트<int[]> 받아오기
            Collections.sort(list, (o1,o2) -> o2[1] - o1[1]); // 재생횟수 기준 내림차순
            ansList.add(list.get(0)[0]); // 첫번째 고유번호 추가
            if(list.size()>1){ // 만약 리스트 길이가 1보다 크면
                ansList.add(list.get(1)[0]); // 두번째 고유번호 추가
            }
        }

        return ansList;
    }
}
