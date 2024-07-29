package tech.nocountry.eco_rent.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import javax.sql.DataSource;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DataSourceConfig {

  private final Environment env;

  @Autowired
  public DataSourceConfig(Environment env) {
    this.env = env;
  }

  @Bean
  public DataSource dataSource() {
      
    String jdbcUrl =
        env.getProperty("SPRING_DATASOURCE_URL", env.getProperty("spring.datasource.url"));
    String username =
        env.getProperty(
            "SPRING_DATASOURCE_USERNAME", env.getProperty("spring.datasource.username"));
    String password =
        env.getProperty(
            "SPRING_DATASOURCE_PASSWORD", env.getProperty("spring.datasource.password"));

    if (jdbcUrl == null || username == null || password == null) {
      throw new IllegalStateException("Database connection properties must be set");
    }

    HikariDataSource dataSource = new HikariDataSource();
    dataSource.setJdbcUrl(jdbcUrl);
    dataSource.setUsername(username);
    dataSource.setPassword(password);
    return dataSource;
  }
}
