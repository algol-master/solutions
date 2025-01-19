import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ10473 {
    static List<List<Node>> graph = new ArrayList<>();
    static int n;

    static class Node {
        float x;
        float y;
        float cost;

        Node(float x, float y, float cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        float startX = Float.parseFloat(st.nextToken());
        float startY = Float.parseFloat(st.nextToken());

        st = new StringTokenizer(br.readLine());
        float endX = Float.parseFloat(st.nextToken());
        float endY = Float.parseFloat(st.nextToken());

        n = Integer.parseInt(br.readLine());

        for (int i = 2; i < n + 2; i++) {
            st = new StringTokenizer(br.readLine());
            float cannonX = Float.parseFloat(st.nextToken());
            float cannonY = Float.parseFloat(st.nextToken());

        }
    }
}
