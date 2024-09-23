package zetzet.workspace.sdk_voting_t1.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@Getter
@Setter
public class DatabaseConfig {

    private String url;
    private String username;
    private String password;

    public DatabaseConfig() {
    }



    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(url); // URL вашей БД
        dataSource.setUsername(username); // Имя пользователя
        dataSource.setPassword(password); // Пароль

        return dataSource;
    }

}
