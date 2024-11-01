package sleepyhoon.queue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ18258 {

    static class Queue {
        int front;
        int rear;
        int[] q;
        int size;
        int capacity;

        public Queue(int capacity) {
            this.capacity = capacity;
            q = new int[capacity];
            front = -1;
            rear = -1;
            size = 0;
        }

        public void push(int value) {
            if(size == 0) {
                front = 0;
                rear = 0;
            }
            else if(rear == 0) rear = capacity - 1;
            else rear--;
            q[rear] = value;
            size++;
        }

        public int pop() {
            if(size == 0) return -1;
            int poll = q[front];
            if(front == 0) front = capacity - 1;
            else front--;
            size--;
            return poll;
        }

        public int getSize() {
            return size;
        }

        public int isEmpty() {
            if(size == 0) return 1;
            else return 0;
        }

        public int getFront() {
            if(size == 0) return -1;
            return q[front];
        }

        public int getRear() {
            if(size == 0) return -1;
            return q[rear];
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        Queue q = new Queue(n);
        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String operation = st.nextToken();
            switch (operation) {
                case "push":
                    int value = Integer.parseInt(st.nextToken());
                    q.push(value);
                    break;
                case "pop":
                    sb.append(q.pop()).append("\n");
                    break;
                case "size":
                    sb.append(q.getSize()).append("\n");
                    break;
                case "empty":
                    sb.append(q.isEmpty()).append("\n");
                    break;
                case "front":
                    sb.append(q.getFront()).append("\n");
                    break;
                case "back":
                    sb.append(q.getRear()).append("\n");
                    break;
            }
        }
        System.out.println(sb);
    }
}
