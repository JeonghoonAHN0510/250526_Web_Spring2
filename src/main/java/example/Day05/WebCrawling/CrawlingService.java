package example.Day05.WebCrawling;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Map;

@Service    // 컨테이너에 빈 등록 -> IOC(제어의 역전)
public class CrawlingService {

    // 1. 다음 날씨정보 크롤링 -> https://weather.daum.net/
    public Map< String, String > task1(){
        // 1-1. 크롬 설치하기 -> WebDriverManager
        WebDriverManager.chromedriver().setup();
        // 1-2. 크롬 옵션 객체 생성하기
        ChromeOptions options = new ChromeOptions();
        // 1-3. 크롬을 사용하되, 브라우저는 실행하지않는 옵션 설정
        options.addArguments( "--headless=new", "--disable-gpu" );
        // 1-4. 웹드라이버(셀레니움) 객체 생성 -> 크롬 옵션 설정
        WebDriver driver = new ChromeDriver( options );
        // 1-5. 크롤링할 웹주소 설정하기
        String URL = "https://weather.daum.net/";
        // 1-6. 셀레니움에 웹주소 주입하기
        driver.get( URL );
        // 1-7. 셀레니움 대기 시키기 -> 동적 페이지여서, 데이터를 가져올 시간을 기다려야한다.
        WebDriverWait wait = new WebDriverWait( driver, Duration.ofSeconds( 3 ) );
        // 1-8. 크롤링할 HTML 가져오기 -> 지역, 온도, 상태 -> .부모식별자 .해당식별자 를 사용해서 중복을 방지
        // (1) 지역 -> .info_location .tit_location
        WebElement location = wait.until( ExpectedConditions.presenceOfElementLocated( By.cssSelector( ".info_location .tit_location" ) ) );
        System.out.println("location = " + location);
        // (2) 온도 -> .wrap_weather .num_deg
        WebElement degree = wait.until( ExpectedConditions.presenceOfElementLocated( By.cssSelector( ".wrap_weather .num_deg" ) ) );
        System.out.println("degree = " + degree);
        // (3) 상태 -> .wrap_weather .txt_sub
        WebElement status = wait.until( ExpectedConditions.presenceOfElementLocated( By.cssSelector( ".wrap_weather .txt_sub" ) ) );
        System.out.println("status = " + status);



        return null;
    } // func end
} // class end