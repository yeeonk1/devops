package org.cloud.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration // Spring 설정 클래스 선언 (Bean 설정 관리)
@PropertySource("classpath:/application.properties") // application.properties 파일의 값 읽어오기
public class DatabaseConfig { // classpath = resources 폴더

   @Autowired // 객체를 자동으로 주입 (new ApplicationContext 필요 없음)
	// mapper/.xml과 자동 연결
   private ApplicationContext applicationContext; // = Spring 객체 창고
	// Bean을 관리하는 핵심 객체 (Spring에서는 모든 객체들을 Bean으로 관리)
	// Bean 생성, Bean 관리, Bean 주입 담당
   
   @Bean
   @ConfigurationProperties(prefix = "spring.datasource.hikari")
   public HikariConfig hikariConfig() {
      return new HikariConfig();
   }
   
   @Bean
   public DataSource dataSource() throws Exception {
      DataSource dataSource = new HikariDataSource(hikariConfig());
      return dataSource;
   }
   
   @Bean
   public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
      SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
      sqlSessionFactoryBean.setDataSource(dataSource);
      sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/mapper/**/sql-*.xml"));
      sqlSessionFactoryBean.setConfiguration(mybatisConfig());
      return sqlSessionFactoryBean.getObject();
   }
   
   @Bean
   public SqlSessionTemplate sqlSesstionTemplate(SqlSessionFactory sqlSessionFactory) {
      return new SqlSessionTemplate(sqlSessionFactory);
   }
   
   
   @Bean
   @ConfigurationProperties(prefix = "mybatis.configuration")
   public org.apache.ibatis.session.Configuration mybatisConfig() {
      return new org.apache.ibatis.session.Configuration();
   }
}

