package sleepyhoon.hash;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

// 12시 52분 시작
// 1시 49분 종료
// 시간 대소비교에서 에러가 나는듯?
public class BJ19583 {
    static class Time  {
        int hour;
        int minute;

        public Time(int hour,int minute) {
            this.hour = hour;
            this.minute = minute;
        }

        public boolean isLaterThan(Time time) {
            if(this.hour > time.hour) return true;
            else if(this.hour == time.hour) return this.minute > time.minute;
            return false;
        }

        public boolean isSame(Time time) {
            return this.hour == time.hour && this.minute == time.minute;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Time> map = new HashMap<>();
        int answer = 0;
        // 개총시작/개총끝/스트리밍끝
        Time[] timeList = new Time[3];
        // 시간을 나눔
        String[] init = br.readLine().split(" ");
        // 시/분을 나눠서 관리
        for(int i=0;i<3;i++){
            String[] times = init[i].split(":");
            int hour = Integer.parseInt(times[0]);
            int minute = Integer.parseInt(times[1]);
            timeList[i] = new Time(hour,minute);
        }

        while(true) {
            String s = br.readLine();
            if(s==null || s.isEmpty()) break;
            // input[0]은 시간, input[1]은 이름
            String[] input = s.split(" ");
            String[] comeTime = input[0].split(":");

            int hour = Integer.parseInt(comeTime[0]);
            int minute = Integer.parseInt(comeTime[1]);
            Time time = new Time(hour, minute);

            // 개총 시작 < time < 개총 종료 인 경우 무시해야함.
            if(time.isLaterThan(timeList[0]) && timeList[1].isLaterThan(time)) continue;
            // time < 개총 시작
            if(time.isSame(timeList[0]) || timeList[0].isLaterThan(time)) {
                map.put(input[1],map.getOrDefault(input[1],time));
            }
            // 개총 종료 <= time < 스트리밍 종료여야 출석이 인정됨.
            if(time.isSame(timeList[1]) || (time.isLaterThan(timeList[1]) && timeList[2].isLaterThan(time)) || time.isSame(timeList[2])){
                Time remove = map.remove(input[1]);
                if(remove!=null) answer++;
            }
        }
        System.out.println(answer);
    }
}
