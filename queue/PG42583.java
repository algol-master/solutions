import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> bridge = new LinkedList<>();
        Queue<Integer> times = new LinkedList<>();
        int time = 0;
        int index = 0;
        int currentWeight = 0;

        while (index < truck_weights.length || !bridge.isEmpty()) {
            time++;

            if (!times.isEmpty() && times.peek() == time) {
                times.poll();
                currentWeight -= bridge.poll();
            }

            if (index < truck_weights.length && currentWeight + truck_weights[index] <= weight && bridge.size() < bridge_length) {
                bridge.offer(truck_weights[index]);
                currentWeight += truck_weights[index];
                times.offer(time + bridge_length);
                index++;
            }
        }

        return time;
    }
}
