package member;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import global.DatabaseFactory;
import global.DatabaseFactory;
import global.Vendor;

public class MemberMain {
     public static void main(String[] args) {
          MemberDAO dao = MemberDAO.getInstance();
          List<MemberVO> list = new ArrayList<MemberVO>();
          MemberVO vo = new MemberVO();
          Scanner scanner = new Scanner(System.in);
           while (true ) {
              System. out.println("=== 회원관리 프로그램 === \n "
                        + "[회원등록:1][전체회원:2][ID조회:3][이름조회:4][전체회원수:5]"
                        + "[비번수정:6][회원탈퇴:7] [종료:9] ");
              
               switch (scanner .nextInt()) {
               case 1:
            	   System.out.println("== 회원가입 ==");
            	   System.out.println("아이디 : ");
            	   String userid = scanner.next();
            	   System.out.println("비번 : ");
            	   String password = scanner.next();
            	   System.out.println("이름 : ");
            	   String name = scanner.next();
            	   System.out.println("성별 : ");
            	   String gender = scanner.next(); // m : 남, w : 여
            	   System.out.println("생년 : ");
            	   String birth = scanner.next();
            	   System.out.println("전화번호 : ");
            	   String phone = scanner.next();
            	   System.out.println("이메일 : ");
            	   String email = scanner.next();
            	   System.out.println("주소 : ");
            	   String addr = scanner.next();
            	   MemberVO temp = new MemberVO(
            			  userid,password,name,birth, gender, phone, email,addr );
				if (dao.insert(temp) == 1) {
					System.out.println("성공적으로 추가됨");
				} else {
					System.out.println("회원가입 실패.");
				}
            	   break;
               case 2:
                    list = dao.selectAll();
                    for (MemberVO m : list ) {
                        System. out.println(m );
                   }
                    break;
               case 3:
                   System. out.println("검색 ID ? " );
                   System. out.println(dao .selectOneBy(scanner.next()).toString());
                    break;
               case 4: break ;
               case 5:
                   System. out.println(dao .count()+" 명");
                    break;
               case 6: break ;
               case 7: break ;
               case 9:
                   System. out.println("프로그램 종료..Good bye !!");
                    return;
               default:
                    break;
              }
          }
     }
}
