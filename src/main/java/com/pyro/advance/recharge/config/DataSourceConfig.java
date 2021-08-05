package com.pyro.advance.recharge.config;


import Cryptography.ST_decrypt;
import Cryptography.ST_encrypt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import javax.sql.DataSource;

@Configuration
@PropertySources({
        @PropertySource("file:${advanceRechargePath}/database.properties")
        //@PropertySource("classpath:/database.properties")
})
public class DataSourceConfig {

    @Value( "${datasource.username}" )
    private String username;

    @Value("${datasource.password}")
    private String password;

    @Value("${datasource.driverClassName}")
    private String driverClassName;

    @Value("${datasource.url}")
    private String url;

    @Bean
    public DataSource getDataSource() {
        ST_decrypt de = new ST_decrypt();
        String publicKey = "90028472125233882888";
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(driverClassName);
        dataSourceBuilder.url(url);
        dataSourceBuilder.username(username);
        dataSourceBuilder.password(de.st_decrypt(password,publicKey));
        return dataSourceBuilder.build();
    }

    public static void main(String[] args){
        ST_decrypt de = new ST_decrypt();
        ST_encrypt en = new ST_encrypt();
        String publicKey = "90028472125233882888";
        //System.out.println(   de.st_decrypt("022f35a894474a92cd", publicKey));
        System.out.println(en.encrypt("mvoucher", publicKey));
        //00df0c3e3d92b9550b7bc243f81135b2f6
    }
}
