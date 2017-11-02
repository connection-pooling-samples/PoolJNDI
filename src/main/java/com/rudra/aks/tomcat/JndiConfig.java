package com.rudra.aks.tomcat;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.ContextResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiObjectFactoryBean;

@Configuration
public class JndiConfig {

	@Value("${jdbc.driverClassName}")
	private	String 	driverClassName;
	
	@Value("${jdbc.connectionUrl}")
	private String url;
	
	@Value("${jdbc.jndi-name}")
	private String jndiName;
	
	@Bean
	public TomcatEmbeddedServletContainerFactory tomcatFactory() {
	        return new TomcatEmbeddedServletContainerFactory() {

	            @Override
	            protected TomcatEmbeddedServletContainer getTomcatEmbeddedServletContainer(
	                    Tomcat tomcat) {
	                tomcat.enableNaming();
	                return super.getTomcatEmbeddedServletContainer(tomcat);
	            }

	            @Override
	            protected void postProcessContext(Context context) {
	                ContextResource resource = new ContextResource();
	                resource.setName(jndiName);
	                resource.setType(DataSource.class.getName());
	                resource.setProperty("driverClassName", driverClassName);
	                resource.setProperty("url", url);
	                resource.setProperty("username", "devuser");
	                resource.setProperty("password", "leo$123");
	                resource.setProperty("maxActive", "10");
	                resource.setProperty("maxIdle", "5");
	                resource.setProperty("minIdle", "2");
	                resource.setProperty("maxWait", "10000");
	                context.getNamingResources().addResource(resource);
	            }
	        };
	}

	@Bean(destroyMethod = "")
    public DataSource jndiDataSource() throws IllegalArgumentException, NamingException {
        JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
        bean.setJndiName("java:comp/env/jdbc/dataSource");
        bean.setProxyInterface(DataSource.class);
        bean.setLookupOnStartup(false);
        bean.afterPropertiesSet();
        return (DataSource) bean.getObject();
    }
	
}
