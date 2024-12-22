package exhaustive_search;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <pre>
 * 문제 : BJ10972
 * 문제 링크 : https://www.acmicpc.net/problem/10972
 * 난이도 : 실버 3
 * 걸린 시간 : 15분
 * 알고리즘 : 순열 / 다음순열
 *
 * 풀이
 * 일반적인 다음 순열을 사용하여 푸는 문제. 예외 상황은 다음과 같이 가정하고, 그 외에는 다음 순열로 풀었다.
 * 1. pivot이 -1 인 경우, 가장 마지막 순열이므로 -1을 반환한다.
 * 2. pivot이 N(길이)-1 즉, 가장 마지막의 이전값으로 선택된다면, 교환만 하면 되므로, 교환 후 반환한다.
 * </pre>
 */
public class BJ10972 {


    static int N;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int pivot = -1;
        for(int i = N - 2; i >= 0; i--) {
            if(arr[i] < arr[i + 1]) {
                pivot = i;
                break;
            }
        }

        if(pivot == -1) {
            System.out.println(-1);
            return;
        } else {
            for(int i = N - 1; i > pivot; i--) {
                if(arr[i] > arr[pivot]) {
                    swap(pivot, i);
                    break;
                }
            }
            Arrays.sort(arr, pivot + 1, N);
        }

        for(int i = 0; i < N; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void swap(int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }
}
