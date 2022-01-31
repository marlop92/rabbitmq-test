package pl.mlopatka.receiver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import pl.mlopatka.receiver.user.config.UserConfig;

@SpringBootApplication
@Import({UserConfig.class})
public class ReceiverApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReceiverApplication.class, args);
    }

}
