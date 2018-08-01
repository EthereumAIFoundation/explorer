# eaiscan-explorer
Yet another implementation of eaiscan.io



## web3j-app
It is a java project using spring boot and web3j. Jdk8, Gradle and Mysql should be pre-installed.

The folder web3j-app/app is the core module doing sync job and providing rest api for frontend.

The project should be started after geai, because it will use geai ipc file to sync blockchain data to mysql.

Custom configurations are made in files:

1. web3j-app/app/src/main/resources/application.properties

2. web3j-app/app/src/main/java/me/bing/web3j/app/common/Constant.java

3. web3j-app/app/src/main/resources/logback.xml

For example: 

```java
//web3j-app/app/src/main/java/me/bing/web3j/app/common/Constant.java
public static String IpcAddress = "/Users/bing/test/data/geai.ipc";
public static String CapAddress = "https://api.coinmarketcap.com/v1/ticker/ethereumai/";
```

```properties
//web3j-app/app/src/main/resources/application.properties
spring.datasource.url = jdbc:mysql://localhost:3306/eai_test?useUnicode=true&characterEncoding=utf-8&useSSL=false
spring.datasource.username = root
```

```xml
<property name="log_path" value="/data/applogs/web3j-app"/>
```

## frontend
It is a project copied from [https://github.com/nebulasio/explorer/tree/develop/explorer-front](https://github.com/nebulasio/explorer/tree/develop/explorer-front) with little modification.

```bash
npm run dev # start in development
npm run build # build html&js which are in dist folder
```

## Your contributions are welcome
The author is a newbie of gradle and vue, so there must be contributions you are willing to contribute.
