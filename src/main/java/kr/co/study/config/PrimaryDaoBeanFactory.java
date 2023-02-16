package kr.co.study.config;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

@EnableTransactionManagement
@Configuration
@MapperScan(basePackages="kr.co.newzensolution.**.**.mapper")
public class PrimaryDaoBeanFactory {
	
	@Bean(name="dataSource")
	@ConfigurationProperties(prefix="spring.datasource.hikari")
	public DataSource createDataSource() {
		return DataSourceBuilder.create().type(HikariDataSource.class).build();
	}
	
	@Bean
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(createDataSource());
	}

	@Primary
	@Bean(destroyMethod = "clearCache")
	public SqlSessionTemplate baseSessionTemplate() {
		try {
			PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		    Resource[] resources = resolver.getResources("classpath*:mapper/*.xml");

			SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
			sqlSessionFactory.setDataSource(createDataSource());
			sqlSessionFactory.setMapperLocations(resources);
			sqlSessionFactory.setTypeAliasesPackage("kr.co.study");

			return new SqlSessionTemplate(sqlSessionFactory.getObject());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}