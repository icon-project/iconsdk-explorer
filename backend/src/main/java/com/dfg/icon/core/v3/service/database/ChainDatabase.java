package com.dfg.icon.core.v3.service.database;

import com.dfg.icon.core.v3.vo.MultiChainInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
@Service
public class ChainDatabase {
    private static final Logger logger = LoggerFactory.getLogger(ChainDatabase.class);

    @Autowired
    Environment env;

    @Value("${multiChain.path}")
    String MULTICHAIN_PATH;

    @Value("${sql.path}")
    String SQL_PATH;

    private MultiChainInfo chains;

    public void crateDatabase(){
        String option = "serverTimezone=UTC";
        try{
            chains = readMultiChain(MULTICHAIN_PATH);
        }catch (IOException e){
            logger.error("error message : " + e.getMessage());
        }

        //database
        createDatabase(env.getProperty("db.url")+"/?" + option,
                env.getProperty("db.username"), env.getProperty("db.password"), "explorer");
        try{
            createTable(env.getProperty("db.url")+"/explorer" + "?" + option,
                    env.getProperty("db.username"), env.getProperty("db.password"), SQL_PATH);
        }catch (Exception e){
            logger.error("e message : " + e.getMessage());
        }

        for(MultiChainInfo.ChainInfo chainInfo : chains.getChainInfos()) {
            createDatabase(env.getProperty("db.url")+"/?" + option,
                    env.getProperty("db.username"), env.getProperty("db.password"), chainInfo.getChannel());
            try{
                createTable(env.getProperty("db.url")+"/"+chainInfo.getChannel() + "?" + option,
                        env.getProperty("db.username"), env.getProperty("db.password"), SQL_PATH);
            }catch (Exception e){
                logger.error("e message : " + e.getMessage());
            }
        }
    }



    public MultiChainInfo getMultichainInfo(){
        return this.chains;
    }


    private MultiChainInfo readMultiChain(String path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(path), MultiChainInfo.class);
    }

    private void createDatabase(String url, String user, String pw, String dbName){
        try {
            Connection con1 = DriverManager.getConnection(url, user, pw);
            Statement s1 = con1.createStatement();
            String createTable = "CREATE DATABASE IF NOT EXISTS `"+ dbName +"`";
            String grantUser = "GRANT ALL PRIVILEGES ON " + dbName + ".* TO '" + user + "'" + "@'%'";

            s1.executeUpdate(createTable);
            s1.executeUpdate(grantUser);
        } catch(SQLException err) {
            logger.error(err.getMessage());
        }

    }

    private void createTable(String url, String user, String pw, String sqlPath) throws Exception {
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        String mysqlUrl = url;
        Connection con = DriverManager.getConnection(mysqlUrl, user, pw);
        logger.info("Connection established : " + url);
        ScriptRunner sr = new ScriptRunner(con);
        Reader reader = new BufferedReader(new FileReader(sqlPath));
        sr.runScript(reader);
    }


}
