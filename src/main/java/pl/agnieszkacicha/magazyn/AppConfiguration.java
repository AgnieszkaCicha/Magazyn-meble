package pl.agnieszkacicha.magazyn;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pl.agnieszkacicha.magazyn.database.UserRepositoryImpl;

@Configuration
@ComponentScan("pl.agnieszkacicha.magazyn.gui")
public class AppConfiguration {

    @Bean
    public UserRepositoryImpl userRepository() {
        return new UserRepositoryImpl();
    }

}
