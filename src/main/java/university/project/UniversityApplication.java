package university.project;
import university.project.info.LoggerInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class UniversityApplication {

    public static void main(String[] args) {
//        LoggerInfo loggerInfo = new LoggerInfo();
//        loggerInfo.loggerMethod();
        SpringApplication.run(UniversityApplication.class, args);
    }

}
