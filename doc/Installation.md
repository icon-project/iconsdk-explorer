# ICONSDK-EXPLORER Installation
It is installed on a docker basis and runs with docker-compose.
## 1. Platform preparation

* Operating Environment
    ```
    Architecture ( x86_64 / arm64 )
    Docker ( Docker Version >= 18.09.6 ) 
  Java ( OpenJDK Version >= 11.0.3 )
  Database ( Mysql Version >= 5.7 )
    ```

## 2. Folder structure
```bash
├── configuration
    └── qrcode
    └── report
    └── score
├── log
├── mysql
```
* configuration : Directory with settings and other configuration files 
* log : log directory
* mysql : mysql directory

## 3. Configuration
```bash
<?xml version="1.0" encoding="UTF-8"?>
<configuration>

    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <filter class="ch.qos.logback.classic.filter.ThresholdFilter">
            <level>INFO</level>  #1   
                ...
    </appender>
    <appender name="RollingFile" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file>/log/iconsdk-explorer.log</file> #2
        ...
        <filter class="ch.qos.logback.classic.filter.ThresholdFilter">
            <level>DEBUG</level> #3
        <rollingPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy">
            <fileNamePattern>/log/iconsdk-explorer-%d{yyyy-MM-dd}.%i.log</fileNamePattern> #4
            <maxFileSize>10MB</maxFileSize> #5
            <cleanHistoryOnStart>true</cleanHistoryOnStart>
        </rollingPolicy>
        </filter>
                  ...
    </appender>
    <root>
        <appender-ref ref="STDOUT" />
        <appender-ref ref="RollingFile" />
    </root>

```
* 1 : Set console (stdout) log level (modifiable)
* 2 : Set file log path (modifiable)
* 3 : Set file log level (modifiable)
* 4 : Setting the Rolling file log file name and path (modifiable)
* 5 : Maximum log file size (modifiable)

## 4. Docker compose environment
- [Docker-compose](dockercompose/docker-compose.yml)

|Environment| Described               |
|---|-------------------------|
|LOGGING_CONFIG| logback.xml path        |
|MULTICHAIN_PATH| Node settings file path |
|QRCODE_PATH| Qrcode storage path     |
|SCORE_PATH| Score storage path      |
|REPORT_IMAGE_PATH| Report storage path     |
|DB_PASSWORD| Database password       |
