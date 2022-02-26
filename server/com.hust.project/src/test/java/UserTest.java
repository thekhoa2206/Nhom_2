import com.vuw17.StartWebServer;
import com.vuw17.configuration.JPAConf;
import com.vuw17.entities.User;
import com.vuw17.repositories.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

//@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
//@ContextConfiguration(classes = JPAConf.class, loader = AnnotationConfigContextLoader.class)
//@EnableAutoConfiguration
@SpringBootTest
public class UserTest {
    ApplicationContext applicationContext = SpringApplication.run(StartWebServer.class);
    UserRepository userRepository = applicationContext.getBean(UserRepository.class);
//    @Autowired
//    private UserRepository userRepository;

    @Test
    public void saveUser(){
        User user = new User();
        user.setAddress("AAAAAA");
        user.setEmail("khoa@gmail.com");
        user.setIdCard("21431312231");
        user.setName("Nguyễn Văn Định");
        userRepository.save(user);
        Assertions.assertThat(user.getId()).isGreaterThan(0);
    }

}
