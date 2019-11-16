package com.xiao.demo.datasource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.lang3.StringUtils;
import org.quartz.utils.ConnectionProvider;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by xiao on 2019/11/13.
 */
public class HikariCPConnectionProvider implements ConnectionProvider {
    /*
     * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * 常量配置，与quartz.properties文件的key保持一致(去掉前缀)，同时提供set方法，Quartz框架自动注入值。
     * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     */
    //JDBC驱动
    public String driverClassName;
    //JDBC连接串
    public String jdbcUrl;
    //数据库用户名
    public String username;
    //数据库用户密码
    public String password;

    public String connectionTimeout;
    public String idleTimeout;
    public String maxLifetime;
    public String maximumPoolSize;
    public String minimumIdle;
    public String autoCommit;

    public static final String JDBC_CONNECTIONTIMEOUT = "connectionTimeout";
    public static final String JDBC_IDLETIMEOUT = "idleTimeout";
    public static final String JDBC_MAXLIFETIME = "maxLifetime";
    public static final String JDBC_AUTOCOMMIT = "autoCommit";
    public static final String JDBC_MAXIMUMPOOLSIZE = "maximumPoolSize";
    public static final String JDBC_MINIMUMIDLE = "minimumIdle";

    private HikariDataSource datasource;

    @Override
    public Connection getConnection() throws SQLException {
        return datasource.getConnection();
    }

    @Override
    public void shutdown() throws SQLException {
        datasource.close();
    }

    @Override
    public void initialize() throws SQLException {
        if (StringUtils.isBlank(this.jdbcUrl)) {
            throw new SQLException("DBPool could not be created: DB jdbcUrl cannot be null");
        }
        if (StringUtils.isBlank(this.driverClassName)) {
            throw new SQLException("DBPool driver could not be created: DB driver class name cannot be null!");
        }

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(this.jdbcUrl);
        config.setUsername(this.username);
        config.setPassword(this.password);
        config.setDriverClassName(this.driverClassName);

        if(StringUtils.isNotBlank(this.connectionTimeout)){
            config.addDataSourceProperty(JDBC_CONNECTIONTIMEOUT, this.connectionTimeout);
        }
        if(StringUtils.isNotBlank(this.idleTimeout)){
            config.addDataSourceProperty(JDBC_IDLETIMEOUT,  this.idleTimeout);
        }
        if(StringUtils.isNotBlank(this.maxLifetime)){
            config.addDataSourceProperty(JDBC_MAXLIFETIME,  this.maxLifetime);
        }
        if(StringUtils.isNotBlank(this.autoCommit)){
            config.addDataSourceProperty(JDBC_AUTOCOMMIT, this.autoCommit);
        }
        if(StringUtils.isNotBlank(this.maximumPoolSize)){
            config.addDataSourceProperty(JDBC_MAXIMUMPOOLSIZE,  this.maximumPoolSize);
        }
        if(StringUtils.isNotBlank(this.minimumIdle)){
            config.addDataSourceProperty(JDBC_MINIMUMIDLE,  this.minimumIdle);
        }

        datasource = new HikariDataSource(config);
    }


    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(String connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public String getIdleTimeout() {
        return idleTimeout;
    }

    public void setIdleTimeout(String idleTimeout) {
        this.idleTimeout = idleTimeout;
    }

    public String getMaxLifetime() {
        return maxLifetime;
    }

    public void setMaxLifetime(String maxLifetime) {
        this.maxLifetime = maxLifetime;
    }

    public String getMaximumPoolSize() {
        return maximumPoolSize;
    }

    public void setMaximumPoolSize(String maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
    }

    public String getMinimumIdle() {
        return minimumIdle;
    }

    public void setMinimumIdle(String minimumIdle) {
        this.minimumIdle = minimumIdle;
    }

    public String getAutoCommit() {
        return autoCommit;
    }

    public void setAutoCommit(String autoCommit) {
        this.autoCommit = autoCommit;
    }
}
