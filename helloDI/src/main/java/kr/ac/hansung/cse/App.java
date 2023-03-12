package kr.ac.hansung.cse;

import kr.ac.hansung.cse.animals.AnimalType;
import kr.ac.hansung.cse.animals.PetOwner;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        // 스프링 IoC 컨테이너 생성
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("conf/animal.xml");

        PetOwner person = (PetOwner) context.getBean("petOwnerId");
        person.play();

        context.close();
    }
}
