package c10y.db;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Defines the MySQL 'integration' data source.
 * 
 * @author Jean-Marc Reynaud
 *
 */
@Component
public class IntegrationDataSource {

	/**
	 * @return the MySQL 'integration' data source.
	 */
	@Bean(name = "integration")
	@ConfigurationProperties(prefix="integration.datasource")
	private DataSource getDataSource() {
		return DataSourceBuilder.create().build();
	}

}
