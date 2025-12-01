package backjoon;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class Main {
    static StringBuilder answer = new StringBuilder();
    static int[][] array;
    static List<Point> points;
    static Stack<Point> stack;
    static boolean[][] visited;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int N, M, count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());        // 테스트케이스 개수 T

        for (int t = 1; t <= T; t++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());       // 가로길이 N
            M = Integer.parseInt(st.nextToken());       // 세로길이 M
            array = new int[N][M];
            visited = new boolean[N][M];
            count = 0;
            points = new ArrayList<>();
            stack = new Stack<>();
            int K = Integer.parseInt(st.nextToken());   // 배추 위치 개수 K
            for (int i = 0; i < K; i++){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                array[x][y] = 1;
                points.add(new Point(x, y));
            } // for end
            for (Point point : points){
                dfs(point.x, point.y);
            } // for end
            answer.append(count).append("\n");
        } // for end

        bw.write(answer.toString().trim());
        bw.flush();
        bw.close();
    } // main end
    public static void dfs(int x, int y){
        stack.add(new Point(x, y));
        if (visited[x][y]) return;
        visited[x][y] = true;
        while (!stack.isEmpty()){
            Point point = stack.pop();
            for (int i = 0; i < 4; i++) {
                int nextX = point.x + dx[i];
                int nextY = point.y + dy[i];
                // 범위 유효성 체크
                if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= M) continue;
                // 배추 체크
                if (array[nextX][nextY] == 0) continue;
                // visited 체크
                if (visited[nextX][nextY]) continue;
                // queue에 삽입
                stack.add(new Point(nextX, nextY));
                visited[nextX][nextY] = true;
            } // for end
        } // while end
        count++;
    } // func end
} // class end