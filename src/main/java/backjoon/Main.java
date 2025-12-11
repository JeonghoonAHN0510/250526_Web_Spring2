package backjoon;

import java.awt.*;
import java.io.*;
import java.util.*;

class Point{
    int x, y, count, broken;
    public Point(int x, int y, int count, int broken){
        this.x = x;
        this.y = y;
        this.count = count;     // 걸리는 거리
        this.broken = broken;   // 벽을 부순 여부
    } // func end
} // class end

public class Main {
    static StringBuilder answer = new StringBuilder();
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int[][] map;
    static boolean[][][] visited;
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());       // 세로 N
        M = Integer.parseInt(st.nextToken());       // 가로 M
        map = new int[N][M];
        visited = new boolean[N][M][2];

        for (int i = 0; i < N; i++){
            String str = br.readLine();
            for (int j = 0; j < M; j++){
                char ch = str.charAt(j);
                map[i][j] = ch - '0';
            } // for end
        } // for end

        bfs();

        bw.write(answer.toString().trim());
        bw.flush();
        bw.close();
    } // main end
    static void bfs(){
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(0, 0, 1, 0));
        visited[0][0][0] = true;
        while (!queue.isEmpty()){
            Point current = queue.poll();
            if (current.x == N - 1 && current.y == M - 1){
                answer.append(current.count);
                return;
            } // if end
            for (int i = 0; i < 4; i++){
                int nextX = current.x + dx[i];
                int nextY = current.y + dy[i];
                if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= M) continue;
                // 다음 칸이 벽이 아닌 경우
                if (map[nextX][nextY] == 0){
                    // 현재 상태 그대로 방문했는지 체크
                    if (!visited[nextX][nextY][current.broken]){
                        visited[nextX][nextY][current.broken] = true;
                        queue.add(new Point(nextX, nextY, current.count + 1, current.broken));
                    } // if end
                } // if end
                // 다음 칸이 벽인 경우
                else if (map[nextX][nextY] == 1){
                    // 벽을 안 부순 상태면, 벽을 부수고 이동
                    if (current.broken == 0){
                        // 벽을 부순 상태로 방문했는지 체크
                        if (!visited[nextX][nextY][1]){
                            visited[nextX][nextY][1] = true;
                            queue.add(new Point(nextX, nextY, current.count + 1, 1));
                        } // if end
                    } // if end
                } // if end
            } // for end
        } // while end
        // 도착점에 도달할 수 없으면, -1 출력
        answer.append("-1");
    } // func end
} // class end