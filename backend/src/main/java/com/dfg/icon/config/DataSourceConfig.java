package com.dfg.icon.config;

import com.dfg.icon.core.v3.service.database.ChainDatabase;
import com.dfg.icon.core.v3.service.database.MultiChainInfo;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Configurable
@EnableTransactionManagement
@MapperScan(
        basePackages = {"com.dfg.icon.core.mappers.icon"}
)
public class DataSourceConfig {

    private static final Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

    @Autowired
    Environment env;

    @Autowired
    ChainDatabase chainDatabase;

//    //TODO 삭제하고 테스트 해보기
//    @Bean
//    public DataSource dataSource() throws Exception {
//
//        BasicDataSource dataSource = new BasicDataSource();
//
//        dataSource.setDriverClassName(env.getProperty("db.driverClassName"));
//        dataSource.setUrl(env.getProperty("db.url"));
//        dataSource.setUsername(env.getProperty("db.username"));
//        dataSource.setPassword(env.getProperty("db.password"));
//        dataSource.setConnectionProperties(env.getProperty("db.connectionProperties"));
//        dataSource.setValidationQuery(env.getProperty("db.validationQuery"));
//        dataSource.setTestWhileIdle(env.getProperty("db.testWhileIdle", Boolean.class));
//        dataSource.setMaxTotal(env.getProperty("db.maxTotal", Integer.class));
//        dataSource.setMaxWaitMillis(env.getProperty("db.maxWaitMillis", Long.class));
//        dataSource.setTimeBetweenEvictionRunsMillis(Integer.parseInt(env.getProperty("db.timeBetweenEvictionRunsMillis")));
//        dataSource.setTestOnBorrow(Boolean.valueOf(env.getProperty("db.testOnBorrow")));
//
//        logger.info("Init DataSource icon Url : {} ", dataSource.getUrl());
//        return dataSource;
//    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(
            DataSource dataSource,
            ApplicationContext applicationContext) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath*:core/mappers/icon/*.xml"));
        return sqlSessionFactoryBean;
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(
             SqlSessionFactory sqlSessionFactory
    ) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    public PlatformTransactionManager transactionManager() throws Exception {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(this.multitenantDataSource());
        return transactionManager;
    }

    @Bean
    public MultitenantDataSource multitenantDataSource() {
        MultitenantDataSource dataSource = new MultitenantDataSource();
        String option = "serverTimezone=UTC&";
        Map<Object,Object> resolvedDataSources = new HashMap<>();
        MultiChainInfo chains = chainDatabase.getMultichainInfo();
        Field props = null;
        try {
            props = DataSourceBuilder.class.getDeclaredField("properties");
            //default
            DataSourceBuilder defaultDataSourceBuilder = DataSourceBuilder.create()
                    .url(env.getProperty("db.url")+"/"+ env.getProperty("db.default.name") + "?" + option)
                    .username(env.getProperty("db.username"))
                    .password(env.getProperty("db.password"));
            resolvedDataSources.put("explorer",defaultDataSourceBuilder.build());

            for (int i = 0; i < chains.getChainInfos().size(); i++) {
                DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create()
                        .url(env.getProperty("db.url")+"/"+ chains.getChainInfos().get(i).getChannel() + "?" + option)
                        .username(env.getProperty("db.username"))
                        .password(env.getProperty("db.password"));
//                Map<String, String> properties = (Map<String, String>) props.get(dataSourceBuilder);
                resolvedDataSources.put(chains.getChainInfos().get(i).getChannel(),dataSourceBuilder.build());
            }


            dataSource.setDefaultTargetDataSource(resolvedDataSources.get("explorer"));
            dataSource.setTargetDataSources(resolvedDataSources);

            dataSource.afterPropertiesSet();
        } catch (NoSuchFieldException e) {
            //TODO multi chain Exception
        }

        return dataSource;
    }

}
