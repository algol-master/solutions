package sleepyhoon.queue;

import java.util.*;

class PG42583 {
    static class Truck {
        int position;
        int weight;

        Truck(int position,int weight) {
            this.position = position;
            this.weight = weight;
        }

        public void move() {
            this.position++;
        }

        public int getPosition() {
            return this.position;
        }

        public int getWeight() {
            return this.weight;
        }
    }
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 1;
        Stack<Truck> st = new Stack<>();
        for(int i=truck_weights.length-1;i>=0;i--){
            st.push(new Truck(0,truck_weights[i]));
        }
        int sum = 0;
        Queue<Truck> q = new LinkedList<>();
        Truck pop = st.pop();
        sum += pop.getWeight();
        pop.move();
        q.offer(pop);
        while(sum!=0) {
            int size = q.size();
            // 다리 위에 있는 트럭을 하나 앞으로 전진
            for(int i=0;i<size;i++){
                Truck poll = q.poll();
                poll.move();
                if(poll.getPosition() == bridge_length + 1) {
                    sum -= poll.getWeight();
                } else {
                    q.offer(poll);
                }
            }
            // 다리 위에 올라갈 수 있다면 스택에서 트럭을 하나 꺼내서 다리 위에 놓음
            if(!st.isEmpty()) {
                Truck peek = st.peek();
                if(peek.getWeight() + sum <= weight) {
                    Truck pop1 = st.pop();
                    sum += pop1.getWeight();
                    pop1.move();
                    q.offer(pop1);
                }
            }
            answer++;
        }

        return answer;
    }
}