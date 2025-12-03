package backjoon;

import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder answer = new StringBuilder();
    static int[][] chess;
    static boolean[][] visited;
    static int[] nx = {-2, -2, -1, -1, 1, 1, 2, 2};
    static int[] ny = {1, -1, 2, -2, 2, -2, 1, -1};
    static int I;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());        // 테스트 개수

        for (int i = 0; i < T; i++){
            I = Integer.parseInt(br.readLine());    // 체스판 한 변의 길이
            chess = new int[I][I];
            visited = new boolean[I][I];
            st = new StringTokenizer(br.readLine());    // 나이트 현재 위치
            int knightX1 = Integer.parseInt(st.nextToken());
            int knightY1 = Integer.parseInt(st.nextToken());
            chess[knightX1][knightY1] = 0;
            st = new StringTokenizer(br.readLine());    // 나이트 목적지
            int knightX2 = Integer.parseInt(st.nextToken());
            int knightY2 = Integer.parseInt(st.nextToken());
            chess[knightX2][knightY2] = -1;
            if (knightX1 == knightX2 && knightY1 == knightY2){
                answer.append("0").append("\n");
                continue;
            } // if end
            bfs(knightX1, knightY1);
        } // for end

        bw.write(answer.toString().trim());
        bw.flush();
        bw.close();
    } // main end
    static void bfs(int x, int y){
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(x, y));
        visited[x][y] = true;
        while (!queue.isEmpty()){
            Point point = queue.poll();
            for (int i = 0; i < 8; i++){
                int nextX = point.x + nx[i];
                int nextY = point.y + ny[i];
                if (nextX < 0 || nextX >= I || nextY < 0 || nextY >= I) continue;
                if (visited[nextX][nextY]) continue;

                if (chess[nextX][nextY] == -1){
                    answer.append(chess[point.x][point.y] + 1).append("\n");
                    return;
                } // if end

                chess[nextX][nextY] = chess[point.x][point.y] + 1;
                queue.add(new Point(nextX, nextY));
                visited[nextX][nextY] = true;
            } // for end
        } // while end
    } // func end
} // class end