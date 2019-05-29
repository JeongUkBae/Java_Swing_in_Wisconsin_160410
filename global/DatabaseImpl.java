package global;

import java.sql.Connection;
import java.sql.DriverManager;
/**
 * @file   : DatabaseImpl.java
 * @author : coolbeat@naver.com
 * @date   : 2015. 10. 9.
 * @story  : 데이터베이스와 프로젝트 연결
 * 이클립스에 Data Source Explorer 핑테스트 성공
 * C:\oraclexe\app \oracle\product\11.2.0\server\jdbc\ lib\ojdbc6.jar
 * 빌패스에 녹이기까지 해줘야 함
 */
public class DatabaseImpl implements Database{
     private String driver, url, userid, password;
     private Connection connection;
     
     public DatabaseImpl() {
           this("" ,"" ,"" ,"" );
     }
     
     public DatabaseImpl(String driver, String url,
              String userid, String password) {
           this.driver = driver ;
           this.url = url ;
           this.userid = userid ;
           this.password = password ;
     }
     @Override
     public Connection getConnection() {
           try {
              Class. forName(driver);
               connection  = DriverManager.getConnection(url,userid,password);
          } catch (Exception e ) {
               e.printStackTrace();
          }
           return connection ;
     }
}
