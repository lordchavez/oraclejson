package com.web.config;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.web.repository"})
@PropertySource("classpath:application.properties")
@ComponentScan("com.web.repository")
public class PersistenceConfig {
	
	private static final String PROPERTY_NAME_DATABASE_URL = "db.url";
    private static final String PROPERTY_NAME_DATABASE_DRIVER = "db.driver";
    private static final String PROPERTY_NAME_DATABASE_USERNAME = "db.username";
    private static final String PROPERTY_NAME_DATABASE_PASSWORD = "db.password";
	private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String PROPERTY_NAME_HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
    private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
//    private static final String PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY = "hibernate.ejb.naming_strategy";
    private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "entitymanager.packages.to.scan";
	
	@Resource
    private Environment environment;

	/**
	 * @return
	 */
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(new String[] { "com.web.entity" });
		sessionFactory.setHibernateProperties(hibernateProperties());
		
		return sessionFactory;
	}
	
	/**
	 * @return
	 */
	@Bean
	public DataSource dataSource() {
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName( environment.getRequiredProperty( PROPERTY_NAME_DATABASE_DRIVER ) );
		dataSource.setUrl( environment.getRequiredProperty( PROPERTY_NAME_DATABASE_URL ) );
		dataSource.setUsername( environment.getRequiredProperty( PROPERTY_NAME_DATABASE_USERNAME ) );
		dataSource.setPassword( environment.getRequiredProperty( PROPERTY_NAME_DATABASE_PASSWORD ) );
		return dataSource;
		
	}
	
	/**
	 * @param sessionFactory
	 * @return
	 */
	/*@Bean
	public PlatformTransactionManager transactionManager(SessionFactory sessionFactory) {
		
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);
		
		return txManager;
		
	}*/
	
	/**
	 * @return
	 */
	@Bean
	public PlatformTransactionManager transactionManager() throws ClassNotFoundException {

		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return txManager;
	}

	@Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws ClassNotFoundException {
		
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();

        entityManagerFactory.setDataSource(dataSource());
        entityManagerFactory.setPackagesToScan(environment.getRequiredProperty( PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN ) );
        entityManagerFactory.setPersistenceProviderClass( HibernatePersistenceProvider.class );

        Properties jpaProterties = new Properties();
        jpaProterties.put(PROPERTY_NAME_HIBERNATE_DIALECT, environment.getRequiredProperty( PROPERTY_NAME_HIBERNATE_DIALECT ) );
        jpaProterties.put(PROPERTY_NAME_HIBERNATE_FORMAT_SQL, environment.getRequiredProperty( PROPERTY_NAME_HIBERNATE_FORMAT_SQL ) );
        
//        jpaProterties.put(PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY, environment.getRequiredProperty( PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY ) );
        jpaProterties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, environment.getRequiredProperty( PROPERTY_NAME_HIBERNATE_SHOW_SQL ) );
        
        entityManagerFactory.setJpaProperties(jpaProterties);

        return entityManagerFactory;
    }
	
	/**
	 * @return
	 */
	Properties hibernateProperties() {
		
		return new Properties() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				setProperty(PROPERTY_NAME_HIBERNATE_DIALECT, environment.getRequiredProperty( PROPERTY_NAME_HIBERNATE_DIALECT ) );
//				setProperty(PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY, environment.getRequiredProperty( PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY ) );
				setProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL, environment.getProperty( PROPERTY_NAME_HIBERNATE_SHOW_SQL ) );
				setProperty(PROPERTY_NAME_HIBERNATE_FORMAT_SQL, environment.getProperty( PROPERTY_NAME_HIBERNATE_FORMAT_SQL ) );
				
			}
		};
	}	

}
