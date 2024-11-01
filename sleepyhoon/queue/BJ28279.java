package sleepyhoon.queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ28279 {

    static class Deque {
        int front;
        int rear;
        int capacity;
        int[] deque;
        int size;

        public Deque(int capacity) {
            this.deque = new int[capacity];
            this.front = -1;
            this.rear = -1;
            this.capacity = capacity;
            this.size = 0;
        }

        public void addFirst(int value) {
            if(size == 0 && front == -1) {
                front = 0;
                rear = 0;
            }
            else if (front == 0) front = capacity - 1;
            else front--;
            deque[front] = value;
            size++;
        }

        public void addLast(int value) {
            if(size == 0 && front == -1) {
                front = 0;
                rear = 0;
            }
            else rear = (rear + 1) % capacity;
            deque[rear] = value;
            size++;
        }

        public int removeFirst() {
            if(size == 0) return -1;
            int poll = deque[front];
            if(size == 1 && front == rear) {
                front = -1;
                rear = -1;
            } else if(front == capacity -1) front = 0;
            else front++;
            size--;
            return poll;
        }

        public int removeLast() {
            if(size == 0) return -1;
            int poll = deque[rear];
            if(size == 1 && front == rear) {
                front = -1;
                rear = -1;
            } else if(rear == 0) rear = capacity - 1;
            else rear--;
            size--;
            return poll;
        }

        public int getSize() {
            return size;
        }

        public int isEmpty() {
            return size == 0 ? 1 : 0;
        }

        public int getFront() {
            if(size == 0) return -1;
            else return deque[front];
        }

        public int getRear() {
            if(size == 0) return -1;
            else return deque[rear];
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        Deque deque = new Deque(n);

        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int input = Integer.parseInt(st.nextToken());

            switch(input) {
                case 1:
                    deque.addFirst(Integer.parseInt(st.nextToken()));
                    break;
                case 2:
                    deque.addLast(Integer.parseInt(st.nextToken()));
                    break;
                case 3:
                    sb.append(deque.removeFirst()).append("\n");
                    break;
                case 4:
                    sb.append(deque.removeLast()).append("\n");
                    break;
                case 5:
                    sb.append(deque.getSize()).append("\n");
                    break;
                case 6:
                    sb.append(deque.isEmpty()).append("\n");
                    break;
                case 7:
                    sb.append(deque.getFront()).append("\n");
                    break;
                case 8:
                    sb.append(deque.getRear()).append("\n");
                    break;
            }
        }
        System.out.println(sb);
    }
}