package backjoon;

import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder answer = new StringBuilder();
    static int[][] maze;
    static boolean[][] visited;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   // 세로 N
        M = Integer.parseInt(st.nextToken());   // 가로 M
        maze = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++){
            String str = br.readLine();
            for (int j = 0; j < M; j++){
                maze[i][j] = str.charAt(j) - '0';
            } // for end
        } // for end

        bfs(0, 0);

        answer.append(maze[N - 1][M - 1]);

        bw.write(answer.toString().trim());
        bw.flush();
        bw.close();
    } // main end
    public static void bfs(int x, int y){
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(x, y));
        visited[x][y] = true;
        while (!queue.isEmpty()){
            Point p = queue.poll();
            for (int i = 0; i < 4; i++){
                int nextX = p.x + dx[i];
                int nextY = p.y + dy[i];
                // 범위에 없으면, 다음 지점으로
                if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= M) continue;
                // 못가는 지점이면, 다음 지점으로
                if (maze[nextX][nextY] == 0) continue;
                // 왔던 지점으면, 다음 지점으로
                if (visited[nextX][nextY]) continue;
                // 모든 조건 통과했으면, queue에 삽입
                queue.add(new Point(nextX, nextY));
                // 방문 처리
                visited[nextX][nextY] = true;
                // 왔던 거리 누적
                maze[nextX][nextY] = maze[p.x][p.y] + 1;
            } // for end
        } // while end
    } // func end
} // class end