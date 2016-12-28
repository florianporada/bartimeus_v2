package nl.itopia.modwillie.core.init;

/**
 * All the configurations for SpringMVC and Hibernate reside in this class.
 * @author Robin de Jong
 */

import java.util.Properties;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(basePackages = "nl.itopia.modwillie")
@EnableWebMvc
@EnableAsync
@EnableTransactionManagement
@Import({SecurityConfiguration.class})
public class MvcConfiguration extends WebMvcConfigurerAdapter {
	private static final String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DATABASE_PASSWORD = "willie123";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/willie";
    private static final String DATABASE_USERNAME = "willie";

    private static final String HIBERNATE_DIALECT = "org.hibernate.dialect.MySQL5InnoDBDialect";
    private static final String HIBERNATE_SHOW_SQL = "true";
    private static final String ENTITYMANAGER_PACKAGES_TO_SCAN = "nl.itopia.modwillie.data.model";
    private static final String HIBERNATE_HBM2DDL_AUTO = "update";
//    private static final String HIBERNATE_HBM2DDL_AUTO = "create";
    
    private static final String HIBERNATE_DIALECT_KEY = "hibernate.dialect";
    private static final String HIBERNATE_SHOW_SQL_KEY = "hibernate.show_sql";
    private static final String HIBERNATE_HBM2DDL_AUTO_KEY = "hibernate.hbm2ddl.auto";
    
    @Resource
    private Environment environment;
    
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(DATABASE_DRIVER);
        dataSource.setUrl(DATABASE_URL);
        dataSource.setUsername(DATABASE_USERNAME);
        dataSource.setPassword(DATABASE_PASSWORD);
        
        return dataSource;
    }
    
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
    	LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setPackagesToScan(ENTITYMANAGER_PACKAGES_TO_SCAN);
        sessionFactoryBean.setHibernateProperties(hibProperties());
        
        return sessionFactoryBean;
    }

    private Properties hibProperties() {
    	Properties properties = new Properties();
        properties.put(HIBERNATE_DIALECT_KEY, HIBERNATE_DIALECT);
        properties.put(HIBERNATE_SHOW_SQL_KEY, HIBERNATE_SHOW_SQL);
        properties.put(HIBERNATE_HBM2DDL_AUTO_KEY, HIBERNATE_HBM2DDL_AUTO);
        
        return properties;
    }

    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }

    @Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/view/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**").addResourceLocations("/assets/");
        registry.addResourceHandler("/include/**").addResourceLocations("/include/");
    }
}