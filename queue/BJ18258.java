import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;
import static java.lang.System.out;

public class BJ18258 {
    static int front;
    static int rear;
    static int[] queue;
    static int currentSize;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        int maxSize = Integer.parseInt(br.readLine());
        queue = new int[maxSize];
        front = 0;
        rear = 0;
        currentSize = 0;

        int val;

        for(int i = 0; i < maxSize; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String menu = st.nextToken();
            switch (menu) {
                case "push" :
                    val = Integer.parseInt(st.nextToken());
                    push(val);
                    break;
                case "pop" :
                    val = pop();
                    sb.append(val).append("\n");
                    break;
                case "size" :
                    val = size();
                    sb.append(val).append("\n");
                    break;
                case "empty" :
                    val = empty();
                    sb.append(val).append("\n");
                    break;
                case "front" :
                    val = front();
                    sb.append(val).append("\n");
                    break;
                case "back" :
                    val = back();
                    sb.append(val).append("\n");
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        }

        out.print(sb);
    }

    private static void push(int x) {
        if(currentSize == queue.length) {
            throw new IllegalStateException();
        }
        queue[rear] = x;
        rear = (rear + 1) % queue.length;
        currentSize++;
    }

    private static int pop() {
        if(currentSize == 0) {
            return -1;
        }
        int result = queue[front];
        front = (front + 1) % queue.length;
        currentSize--;
        return result;
    }

    private static int size() {
        return currentSize;
    }

    private static int empty() {
        return (currentSize == 0) ? 1 : 0;
    }

    private static int front() {
        if(currentSize == 0) {
            return -1;
        }
        return queue[front];
    }

    private static int back() {
        if(currentSize == 0) {
            return -1;
        }
        return queue[(rear - 1 + queue.length) % queue.length];
    }
}
