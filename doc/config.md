# ICONSDK-EXPLORER Configuration

Configuration Path :
* `/${PROJECT_ROOT}/backend/src/main/resources/application-local.properties`
---------
1. icon-explorer port

```bash
server.port=8090
```
---------
2. Server name setting

```bash
server.name=local
```
---------
3. Server url setting

```bash
server.url=http://localhost:8090
```
---------
4. Cron pattern setting

```bash
block.cron.pattern=0/2 * * * * *
maintx.cron.pattern=1 0 0 * * *
```
---------
5. Data logging limit setting
```bash
limit.block=50000
limit.transaction=10000
limit.address=10000
limit.main.block=20
limit.main.tx=20
```
---------
6. Scheduler setting
```bash
config.useyn=false
scheduler.MaxTxCount=15000
scheduler.MaxBlockCount=99
```
---------
7. Database setting
```bash
db.default.name=explorer
db.validationQuery=select 1
db.testWhileIdle=true
db.driverClassName=com.mysql.jdbc.Driver
db.url=jdbc:mysql://127.0.0.1:3306
db.connectionProperties=useUnicode=yes;characterEncoding=utf8;
db.username=root
db.password=root
db.maxTotal=5
db.maxWaitMillis=7000
db.timeBetweenEvictionRunsMillis=30000
db.testOnBorrow=false
```
---------
8. Path setting
```bash
qrCode.path=/iconsdk_explorer/icon/qrcode/
score.path=/iconsdk_explorer/icon/score/
irc.path=
```
---------
9. Multi chain setting
```bash
multiChain.path=/iconsdk_explorer/chain/local_chain.json
sql.path=/iconsdk_explorer/erd/init(long).sql
```
---------
10. Report images setting
```bash
report.image.path=/iconsdk_explorer/report
report.image.size=5242880
```
