package com.dfg.icon.core.v3.service.database;

import com.dfg.icon.core.dao.icon.TChainInfo;
import com.dfg.icon.core.mappers.icon.TChainInfoMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
@Service
public class ChainDatabase {
    private static final Logger logger = LoggerFactory.getLogger(ChainDatabase.class);

    @Autowired
    TChainInfoMapper chainInfoMapper;

    @Autowired
    Environment env;

    @Value("${multiChain.path}")
    String MULTICHAIN_PATH;

    @Value("${sql.path}")
    String SQL_PATH;

    private MultiChainInfo chains;

    @PostConstruct
    public void init(){
        String option = "serverTimezone=UTC";
        try{
            chains = readMultiChain(MULTICHAIN_PATH);
        }catch (IOException e){
            //TODO exception message
            logger.error("error message : " + e.getMessage());
        }

        //database
        for(MultiChainInfo.ChainInfo chainInfo : chains.getChainInfos()) {
            createDatabase(env.getProperty("db.url")+"/?" + option,
                    env.getProperty("db.username"), env.getProperty("db.password"), chainInfo.getChannel());
            try{
                createTable(env.getProperty("db.url")+"/"+chainInfo.getChannel() + "?" + option,
                        env.getProperty("db.username"), env.getProperty("db.password"), SQL_PATH);
            }catch (Exception e){
                //TODO exception message
                logger.error("e message : " + e.getMessage());
            }
            //TODO refactoring (add list insert for mapper)
            TChainInfo tChainInfo = new TChainInfo();
            tChainInfo.setChainName(chainInfo.getName());
            tChainInfo.setApi(chainInfo.getApi());
            tChainInfo.setVersion(chainInfo.getVersion());
            tChainInfo.setHost(chainInfo.getHost());
            tChainInfo.setChannel(chainInfo.getChannel());
            chainInfoMapper.insert(tChainInfo);
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
            String s = "CREATE DATABASE IF NOT EXISTS `"+ dbName +"`";
            s1.executeUpdate(s);
        }
        catch(SQLException err) {
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
