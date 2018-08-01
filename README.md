# EthereumAI explorer

## web3j-app

It is a java project using spring boot and web3j. Jdk8, Gradle and Mysql should be pre-installed.

The folder web3j-app/app is the core module doing sync job and providing rest api for frontend.

The project should be started after geai, because it will use geai ipc file to sync blockchain data to mysql.

## frontend

It is a project copied from [https://github.com/nebulasio/explorer/tree/develop/explorer-front](https://github.com/nebulasio/explorer/tree/develop/explorer-front) with little modification.

```bash
npm run dev # start in development
npm run build # build html&js which are in dist folder
```