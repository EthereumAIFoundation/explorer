Management APIs
===============

In addition to implementing the standard
`JSON-RPC <https://github.com/ethereumai/wiki/wiki/JSON-RPC>`_ API, EthereumAI clients, such as
`Geai <https://github.com/ethereumai/go-ethereumai/wiki/geai>`__ and
`Parity <https://github.com/paritytech/parity>`__ provide additional management via JSON-RPC.

One of the key common pieces of functionality that they provide is the ability to create and
unlock EthereumAI accounts for transacting on the network. In Geai and Parity, this is implemented
in their Personal modules, details of which are available below:

- `Parity <https://github.com/paritytech/parity/wiki/JSONRPC-personal-module>`__
- `Geai <https://github.com/ethereumai/go-ethereumai/wiki/Management-APIs#personal>`__

Support for the personal modules is available in web3j. Those methods that are common to both Geai
and Parity reside in the `Admin <https://github.com/web3j/web3j/blob/master/core/src/main/java/org/web3j/protocol/admin/Admin.java>`_ module of web3j.

You can initialise a new web3j connector that supports this module using the factory method::

   Admin web3j = Admin.build(new HttpService());  // defaults to http://localhost:8545/
   PersonalUnlockAccount personalUnlockAccount = admin.personalUnlockAccount("0x000...", "a password").send();
   if (personalUnlockAccount.accountUnlocked()) {
       // send a transaction
   }

For Geai specific methods, you can use the
`Geai <https://github.com/web3j/web3j/blob/master/geai/src/main/java/org/web3j/protocol/geai/Geai.java>`_
connector, and for Parity you can use the associated
`Parity <https://github.com/web3j/web3j/blob/master/parity/src/main/java/org/web3j/protocol/parity/Parity.java>`_
connector. The *Parity* connector also provides support for Parity's
`Trace <https://github.com/paritytech/parity/wiki/JSONRPC-trace-module>`_ module. These connectors
are available in the web3j *geai* and *parity* modules respectively.

You can refer to the integration test
`ParityIT <https://github.com/web3j/web3j/blob/master/integration-tests/src/test/java/org/web3j/protocol/parity/ParityIT.java>`_
for further examples of working with these APIs.
