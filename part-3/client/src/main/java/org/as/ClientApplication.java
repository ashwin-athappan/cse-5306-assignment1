package org.as;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.shell.jline.PromptProvider;

@SpringBootApplication
public class ClientApplication
{
    public static void main( String[] args ) {
        SpringApplication application = new SpringApplication(ClientApplication.class);
        application.run(args);
    }

    @Bean
    public PromptProvider myPromptProvider() {
        return () -> new AttributedString("add-sort-service:>",
                AttributedStyle.BOLD.foreground(AttributedStyle.BLUE));
    }
}
