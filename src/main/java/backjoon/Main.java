package backjoon;

import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder answer = new StringBuilder();
    static int[][] tomato;
    static boolean[][] visited;
    static Queue<Point> queue = new LinkedList<>();
    static int[] nx = {0, 0, 1, -1};
    static int[] ny = {1, -1, 0, 0};
    static int N, M, zero, one;
    static Point lastTomato = new Point(0, 0);
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());       // 가로 길이 N
        M = Integer.parseInt(st.nextToken());       // 세로 길이 M
        tomato = new int[M][N];
        visited = new boolean[M][N];
        zero = 0;
        one = 0;

        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++){
                int a = Integer.parseInt(st.nextToken());
                tomato[i][j] = a;
                if (a == 1){
                    queue.add(new Point(i, j));
                    visited[i][j] = true;
                    one++;
                } else if (a == 0){
                    zero++;
                } else if (a == -1){
                    one++;
                } // if end
            } // for end
        } // for end

        if (one == N * M){
            answer.append("0");
        } else {
            while (!queue.isEmpty()){
                Point point = queue.poll();
                for (int i = 0; i < 4; i++){
                    int nextX = point.x + nx[i];
                    int nextY = point.y + ny[i];
                    if (nextX < 0 || nextX >= M || nextY < 0 || nextY >= N) continue;
                    if (visited[nextX][nextY]) continue;
                    if (tomato[nextX][nextY] == -1) continue;
                    tomato[nextX][nextY] = tomato[point.x][point.y] + 1;
                    queue.add(new Point(nextX, nextY));
                    visited[nextX][nextY] = true;
                    lastTomato = new Point(nextX, nextY);
                    zero--;
                } // for end
            } // while end
            if (zero == 0){
                answer.append(tomato[lastTomato.x][lastTomato.y] - 1);
            } else {
                answer.append("-1");
            } // if end
        } // if end

        bw.write(answer.toString().trim());
        bw.flush();
        bw.close();
    } // main end
} // class end