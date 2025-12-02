package backjoon;

import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder answer = new StringBuilder();
    static boolean[] visited;
    static int[] wNt = {-1, 1, 2};
    static int N, K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   // 수빈 위치 N
        K = Integer.parseInt(st.nextToken());   // 동생 위치 K
        visited = new boolean[100001];

        // 같은 위치면, BFS 실행 안하고 바로 0
        if (N == K){
            answer.append(0);
        } else {
            bfs(N);
        } // if end

        bw.write(answer.toString().trim());
        bw.flush();
        bw.close();
    } // main end
    static void bfs(int N){
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(N, 0));
        visited[N] = true;
        while (!queue.isEmpty()){
            Point point = queue.poll();
            for (int i = 0; i < 3; i++){
                int x = i == 2 ? point.x * wNt[i] : point.x + wNt[i];
                // 다음 위치가 동생 위치라면, 횟수 저장하고 종료
                if (x == K){
                    answer.append(point.y + 1);
                    return;
                } // if end
                // 범위 밖이라면, 다음 위치로
                if (x < 0 || x >= 100001) continue;
                // 왔던 위치라면, 다음 위치로
                if (visited[x]) continue;
                // 모든 조건 통과하면, 큐에 추가하여 반복 실행
                queue.add(new Point(x, point.y + 1));
                visited[x] = true;
            } // for end
        } // while end
    } // func end
} // class end