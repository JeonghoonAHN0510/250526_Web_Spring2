package example.task.task01;

import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TaskDao {

    // * DB 연동에 필요한 정보
    private String DB_URL = "jdbc:mysql://localhost:3306/springweb2";
    private String DB_USER = "root";
    private String DB_PASSWORD = "1234";
    private Connection conn;         // 하위클래스에서 사용할 수 있게 public으로 선언
    // * DB 연동 생성자
    public TaskDao(){ DBConnect(); }
    // * DB 연동 메소드
    private void DBConnect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection( DB_URL, DB_USER, DB_PASSWORD );
            System.out.println("Dao.DBConnect");
        }catch( Exception e ){
            System.out.println( e );
        } // try-catch end
    } // func end

    // [1] 매 30초마다 모든 제품의 재고가 3개씩 감소한다.
    public void task1(){
        try {
            String SQL = "update products set stock_quantity = stock_quantity - 3;";
            PreparedStatement ps = conn.prepareStatement( SQL );
            ps.executeUpdate();
        } catch ( SQLException e ){
            System.out.println( e );
        } // try-catch end
    } // func end
    // [2] 매 1분마다 모든 제품의 정보를 조회한다. - console에 모든 제품 정보 조회
    public List<Map< String,String >> task2(){
        List<Map< String,String >> list = new ArrayList<>();
        try {
            String SQL = "select * from products";
            PreparedStatement ps = conn.prepareStatement( SQL );
            ResultSet rs = ps.executeQuery();
            while ( rs.next() ){
                Map< String, String > map = new HashMap<>();
                map.put( "name", rs.getString( "product_name" ) );
                map.put( "quantity", rs.getString( "stock_quantity" ) );
                list.add( map );
            } // while end
        } catch ( SQLException e ){
            System.out.println( e );
        } // try-catch end
        return list;
    } // func end

    // [3] 매 5분마다 재고가 10개 이하인 상품의 재고를 20개 추가한다.
    public void task3(){
        try {
            String SQL = "update products set stock_quantity = stock_quantity + 20 where stock_quantity <= 10;";
            PreparedStatement ps = conn.prepareStatement( SQL );
            ps.executeUpdate();
        } catch ( SQLException e ){
            System.out.println( e );
        } // try-catch end
    } // func end
} // class end