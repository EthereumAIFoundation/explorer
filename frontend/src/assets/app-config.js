
module.exports = {
    apiPrefixes: {
        // http://192.168.1.168:8080/api/
        // https://52.53.225.118/api/
        // https://explorer.nebulas.io/api/
        // first item is default
        mainnet: {
            name: "Mainnet",
            //url: "https://explorer.nebulas.io/main/api/"
            //url: "http://178.128.3.xx:8080/api/"
            url: "http://localhost:8080/api/"
        },
        testnet: {
            name: "Testnet",
            url: "https://explorer.nebulas.io/test/api/"
        }
    }
};
