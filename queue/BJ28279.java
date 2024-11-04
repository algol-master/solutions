
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ28279 {

    static int size;
    static int front;
    static int rear;
    static int[] deque;
    static int num;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        num = Integer.parseInt(br.readLine());
        size = 0;
        deque = new int[num];
        front = -1;
        rear = 0;

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < num; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int menu = Integer.parseInt(st.nextToken());
            int val;
            switch (menu) {
                case 1 :
                    val = Integer.parseInt(st.nextToken());
                    addFirst(val);
                    break;
                case 2 :
                    val = Integer.parseInt(st.nextToken());
                    addLast(val);
                    break;
                case 3 :
                    try {
                        val = removeFirst();
                        sb.append(val).append('\n');
                    } catch (Exception e) {
                        sb.append("-1\n");
                    }
                    break;
                case 4 :
                    try {
                        val = removeLast();
                        sb.append(val).append('\n');
                    } catch (Exception e) {
                        sb.append("-1\n");
                    }
                    break;
                case 5 :
                        sb.append(size).append('\n');
                    break;
                case 6 :
                    if(size == 0) {
                        sb.append("1\n");
                    } else {
                        sb.append("0\n");
                    }
                    break;
                case 7 :
                    try {
                        val = peekFirst();
                        sb.append(val).append('\n');
                    } catch (Exception e) {
                        sb.append("-1\n");
                    }
                    break;
                case 8 :
                    try {
                        val = peekLast();
                        sb.append(val).append('\n');
                    } catch (Exception e) {
                        sb.append("-1\n");
                    }
                    break;
            }
        }
        System.out.print(sb);
    }

    private static void addFirst(int value) {
        if(size == num) {
            throw new IllegalArgumentException();
        }
        if(size == 0 && front == -1) {
            front = 0;
        } else if(front == 0) {
            front = num - 1;
        } else {
            front--;
        }

        deque[front] = value;
        size++;
    }

    private static int removeFirst() {
        if(size == 0) {
            throw new IllegalArgumentException();
        }
        int result = deque[front];
        if(front == rear) {
            front = -1;
            rear = 0;
        } else if(front == num - 1) {
            front = 0;
        } else {
            front++;
        }

        size--;
        return result;
    }

    private static int peekFirst() {
        if(size == 0) {
            throw new IllegalArgumentException();
        }
        return deque[front];
    }

    private static void addLast(int value) {
        if(size == num) {
            throw new IllegalArgumentException();
        }
        if(size == 0 && front == -1) {
            front = 0;
            rear = 0;
        } else if(rear == num - 1) {
            rear = 0;
        } else {
            rear++;
        }
        deque[rear] = value;
        size++;
    }

    private static int removeLast() {
        if(size == 0) {
            throw new IllegalArgumentException();
        }
        int result = deque[rear];
        if(front == rear) {
            front = -1;
            rear = 0;
        } else if(rear == 0) {
            rear = num - 1;
        } else {
            rear--;
        }
        size--;
        return result;
    }

    private static int peekLast() {
        if(size == 0) {
            throw new IllegalArgumentException();
        }
        return deque[rear];
    }

}
