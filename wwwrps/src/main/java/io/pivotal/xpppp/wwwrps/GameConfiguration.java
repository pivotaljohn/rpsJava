package io.pivotal.xpppp.wwwrps;

import com.awesomeautomation.rps.Game;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GameConfiguration {

    @Bean
    public Game game() {
        return new Game();
    }
}
