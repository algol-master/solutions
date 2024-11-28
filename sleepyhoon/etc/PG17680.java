package sleepyhoon.etc;

import java.util.HashMap;
import java.util.Map;

public class PG17680 {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        if(cacheSize==0) {
            return cities.length * 5;
        }
        Map<String,Integer> map = new HashMap<>(cacheSize);
        // 대소문자를 구분하지 않는다
        for(int i=0;i<cities.length;i++){
            String city = cities[i].toLowerCase();
            if(map.containsKey(city)){
                map.put(city,i);
                answer++;
            }
            else {
                if(map.size() == cacheSize) {
                    int min = Integer.MAX_VALUE;
                    String name = "";
                    for (Map.Entry<String, Integer> stringIntegerEntry : map.entrySet()) {
                        if(stringIntegerEntry.getValue() < min) {
                            min = stringIntegerEntry.getValue();
                            name = stringIntegerEntry.getKey();
                        }
                    }
                    map.remove(name);
                }
                map.put(city,i);
                answer+=5;
            }
        }
        return answer;
    }
}
