package com.agrios.agriosbackend.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.net.URI;

@Configuration
public class DatabaseConfig {

    @Value("${spring.datasource.url:jdbc:mysql://localhost:3306/agrios?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC}")
    private String rawUrl;

    @Value("${spring.datasource.username:root}")
    private String defaultUsername;

    @Value("${spring.datasource.password:Koushik@34}")
    private String defaultPassword;

    @Bean
    @Primary
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();

        String databaseUrl = System.getenv("DATABASE_URL");
        if (databaseUrl == null || databaseUrl.isBlank()) {
            databaseUrl = System.getenv("SPRING_DATASOURCE_URL");
        }
        if (databaseUrl == null || databaseUrl.isBlank()) {
            databaseUrl = rawUrl;
        }

        databaseUrl = databaseUrl.trim();
        // Remove surrounding quotes if any
        if (databaseUrl.startsWith("\"") && databaseUrl.endsWith("\"") && databaseUrl.length() > 1) {
            databaseUrl = databaseUrl.substring(1, databaseUrl.length() - 1).trim();
        }

        // Clean url for scheme parsing
        String cleanUrl = databaseUrl;
        if (cleanUrl.startsWith("jdbc:")) {
            cleanUrl = cleanUrl.substring(5);
        }

        // 1. PostgreSQL (Aiven, Render, Supabase, Neon, etc.)
        if (cleanUrl.startsWith("postgres://") || cleanUrl.startsWith("postgresql://")) {
            try {
                // Ensure URI parser has standard scheme
                String toParse = cleanUrl.startsWith("postgres://") ? cleanUrl : cleanUrl.replaceFirst("^postgresql://", "postgres://");
                URI uri = new URI(toParse);

                String username = defaultUsername;
                String password = defaultPassword;
                String userInfo = uri.getUserInfo();
                if (userInfo != null && userInfo.contains(":")) {
                    String[] parts = userInfo.split(":", 2);
                    username = parts[0];
                    password = parts[1];
                }

                int port = uri.getPort() == -1 ? 5432 : uri.getPort();
                String path = uri.getPath();
                String query = uri.getQuery();

                String jdbcUrl = "jdbc:postgresql://" + uri.getHost() + ":" + port + path;
                if (query != null && !query.isBlank()) {
                    jdbcUrl += "?" + query;
                }

                config.setJdbcUrl(jdbcUrl);
                config.setUsername(username);
                config.setPassword(password);
                config.setDriverClassName("org.postgresql.Driver");
            } catch (Exception e) {
                // Fallback: Ensure scheme is jdbc:postgresql://
                String fallback = databaseUrl.replaceFirst("^jdbc:postgres://", "jdbc:postgresql://");
                config.setJdbcUrl(fallback);
                config.setUsername(defaultUsername);
                config.setPassword(defaultPassword);
                config.setDriverClassName("org.postgresql.Driver");
            }
        } 
        // 2. MySQL (Aiven, Railway, Clever Cloud, PlanetScale)
        else if (cleanUrl.startsWith("mysql://")) {
            try {
                URI uri = new URI(cleanUrl);
                String username = defaultUsername;
                String password = defaultPassword;
                String userInfo = uri.getUserInfo();
                if (userInfo != null && userInfo.contains(":")) {
                    String[] parts = userInfo.split(":", 2);
                    username = parts[0];
                    password = parts[1];
                }

                int port = uri.getPort() == -1 ? 3306 : uri.getPort();
                String path = uri.getPath();
                String query = uri.getQuery();

                String jdbcUrl = "jdbc:mysql://" + uri.getHost() + ":" + port + path;
                if (query != null && !query.isBlank()) {
                    jdbcUrl += "?" + query;
                } else {
                    jdbcUrl += "?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
                }

                config.setJdbcUrl(jdbcUrl);
                config.setUsername(username);
                config.setPassword(password);
                config.setDriverClassName("com.mysql.cj.jdbc.Driver");
            } catch (Exception e) {
                config.setJdbcUrl(databaseUrl);
                config.setUsername(defaultUsername);
                config.setPassword(defaultPassword);
                config.setDriverClassName("com.mysql.cj.jdbc.Driver");
            }
        } 
        // 3. Standard JDBC URLs
        else {
            String finalUrl = databaseUrl;
            if (finalUrl.startsWith("jdbc:postgres://")) {
                finalUrl = finalUrl.replaceFirst("^jdbc:postgres://", "jdbc:postgresql://");
            }
            config.setJdbcUrl(finalUrl);
            config.setUsername(defaultUsername);
            config.setPassword(defaultPassword);
            if (finalUrl.startsWith("jdbc:mysql:")) {
                config.setDriverClassName("com.mysql.cj.jdbc.Driver");
            } else if (finalUrl.startsWith("jdbc:postgresql:")) {
                config.setDriverClassName("org.postgresql.Driver");
            }
        }

        // Connection pool optimization for Cloud Free Tier (e.g. Render 512MB RAM)
        config.setMaximumPoolSize(5);
        config.setMinimumIdle(1);
        config.setIdleTimeout(30000);
        config.setMaxLifetime(60000);

        return new HikariDataSource(config);
    }
}
