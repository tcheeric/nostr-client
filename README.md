# nostr-client

This is a demo nostr client application using the **nostr-java** library, implemented as a java module.

## Writing the Client Code

### Maven setup
To use the **nostr-java** library in the project, 

I add the following dependency to the `pom.xml` file:
```xml
        <dependency>
            <groupId>nostr-java</groupId>
            <artifactId>nostr-java-api</artifactId>
            <version>${nostr-java-version}</version>
        </dependency>
```

The library is currently hosted in the jitpack.io repository
```xml
    <repositories>
        <repository>
            <id>jitpack.io</id>
            <url>https://jitpack.io</url>
        </repository>
    </repositories>
```

### Custom Command Handler
The client provides custom command handlers for the `Ok`, `Notice`, `Error`, `Eose`, and `Event` events, in replacement of the default OOTB dummy command handlers.

To register the custom command handlers:

1. I add the following entry to the `module-info.java` file:

```java
import client.provider.EoseCustomHandler;
import client.provider.EventCustomHandler;
import client.provider.NoticeCustomHandler;
import client.provider.CustomOKCommandHandler;
import client.provider.OKCustomHandler;


module NostrClient {
    ...

    provides nostr.command.CommandHandler with EventCustomHandler, OKCustomHandler, NoticeCustomHandler, EoseCustomHandler;
}
```
2. I also need to create the file `sr/main/resources/META-INF/services/nostr.command.CommandHandler` with the content:

```java
client.provider.EventCustomHandler
client.provider.NoticeCustomHandler
client.provider.OKCustomHandler
client.provider.EoseCustomHandler
```

## Compiling and Running the Code
To compile and run the **nostr-client** application, follow these steps:

 - Build the project using Maven in your preferred IDE.
 - Run the application using the following command:
```
java -jar nostr-client.jar 

Oct 20, 2024 10:24:36 PM nostr.connection.impl.ConnectionPool connect
INFO: Connecting to relays
Oct 20, 2024 10:24:36 PM nostr.connection.impl.ConnectionImpl connect
INFO: Connecting to Relay(scheme=ws, host=localhost:3333)... httpUrl = http://localhost:3333/
Oct 20, 2024 10:24:36 PM nostr.connection.impl.ConnectionImpl connect
INFO: Connected to Relay(scheme=ws, host=localhost:3333)
Oct 20, 2024 10:24:36 PM nostr.connection.impl.listeners.OpenListener onOpen
INFO: WebSocket opened to Relay(scheme=ws, host=localhost:3333)
Oct 20, 2024 10:24:36 PM nostr.connection.impl.ConnectionImpl send
INFO: Sending message: ["EVENT",{"id":"86aa0dc2a82078b41548597470e1c6ab9f5c41a3d2d6763cabd3e0b3cf8b11be","kind":1,"content":"Hello Nostr World!","pubkey":"b178aa906ad89352d82da90ec5a732093aa7331100bfc6dfaca9b3642bb20295","created_at":1729459475,"tags":[],"sig":"4ff2d22b8d7cde7a39a172b48b0d139158311f3a92d2e2e85625b9b230bf6dae66d6dcf2ce2c96e5b07f88668e6ae706e93e7a16d829873358e77822b45b182b"}] - Relay: Relay(scheme=ws, host=localhost:3333)
Oct 20, 2024 10:24:36 PM nostr.connection.impl.listeners.TextListener onMessage
INFO: WebSocket received: ["OK","86aa0dc2a82078b41548597470e1c6ab9f5c41a3d2d6763cabd3e0b3cf8b11be",true,""] - Relay: Relay(scheme=ws, host=localhost:3333)
Oct 20, 2024 10:24:36 PM nostr.connection.impl.listeners.TextListener handleReceivedText
INFO: Received message ["OK","86aa0dc2a82078b41548597470e1c6ab9f5c41a3d2d6763cabd3e0b3cf8b11be",true,""] from Relay(scheme=ws, host=localhost:3333)
Oct 20, 2024 10:24:36 PM client.provider.OKCustomHandler onCommand
INFO: <<< Received OK message: eventId=86aa0dc2a82078b41548597470e1c6ab9f5c41a3d2d6763cabd3e0b3cf8b11be, message=, flag=true
Oct 20, 2024 10:24:36 PM nostr.connection.impl.ConnectionPool connect
INFO: Connecting to relays
Oct 20, 2024 10:24:37 PM nostr.connection.impl.ConnectionImpl send
INFO: Sending message: ["EVENT",{"id":"cefc7ec402312f8a9e6cfc063b5ca939236e91a189b213218419e088d00581bf","kind":1,"content":"Bonjour Nostr Monde!","pubkey":"287bef38850f805ee37e1a4241517cef91e684b7602708eff08dcebfff546afc","created_at":1729459476,"tags":[["e","86aa0dc2a82078b41548597470e1c6ab9f5c41a3d2d6763cabd3e0b3cf8b11be"]],"sig":"148945be91aa410de5acb83fc4e3bf52cee2fb9e064b96c7d83e2eaa506b636659c11676151dbbb0ff056335752afdfcd0c71667dd7456f3d1e1edcdc0be253b"}] - Relay: Relay(scheme=ws, host=localhost:3333)
Oct 20, 2024 10:24:37 PM nostr.connection.impl.listeners.TextListener onMessage
INFO: WebSocket received: ["OK","cefc7ec402312f8a9e6cfc063b5ca939236e91a189b213218419e088d00581bf",true,""] - Relay: Relay(scheme=ws, host=localhost:3333)
Oct 20, 2024 10:24:37 PM nostr.connection.impl.listeners.TextListener handleReceivedText
INFO: Received message ["OK","cefc7ec402312f8a9e6cfc063b5ca939236e91a189b213218419e088d00581bf",true,""] from Relay(scheme=ws, host=localhost:3333)
Oct 20, 2024 10:24:37 PM client.provider.OKCustomHandler onCommand
INFO: <<< Received OK message: eventId=cefc7ec402312f8a9e6cfc063b5ca939236e91a189b213218419e088d00581bf, message=, flag=true
Oct 20, 2024 10:24:37 PM nostr.connection.impl.ConnectionPool connect
INFO: Connecting to relays
Oct 20, 2024 10:24:37 PM nostr.connection.impl.ConnectionImpl send
INFO: Sending message: ["EVENT",{"id":"232bfbc5dae2ee3ad09554f37eb58632f8f518fdf5af4f26eea63b896e417c80","kind":1,"content":"Hola Nostr Mundo!","pubkey":"b178aa906ad89352d82da90ec5a732093aa7331100bfc6dfaca9b3642bb20295","created_at":1729459477,"tags":[],"sig":"e5b117d49c537083c615d9fda24f57034ef3fb73ff7f4c4b1e953ad6ddf30b82713fa8afe50363e4c05da8f0c2a6f5dec2e47d2813015e07031c0abc2bc3cb5c"}] - Relay: Relay(scheme=ws, host=localhost:3333)
Oct 20, 2024 10:24:37 PM nostr.connection.impl.listeners.TextListener onMessage
INFO: WebSocket received: ["OK","232bfbc5dae2ee3ad09554f37eb58632f8f518fdf5af4f26eea63b896e417c80",false,"invalid: bad signature"] - Relay: Relay(scheme=ws, host=localhost:3333)
Oct 20, 2024 10:24:37 PM nostr.connection.impl.listeners.TextListener handleReceivedText
INFO: Received message ["OK","232bfbc5dae2ee3ad09554f37eb58632f8f518fdf5af4f26eea63b896e417c80",false,"invalid: bad signature"] from Relay(scheme=ws, host=localhost:3333)
Oct 20, 2024 10:24:37 PM client.provider.OKCustomHandler onCommand
INFO: <<< Received OK message: eventId=232bfbc5dae2ee3ad09554f37eb58632f8f518fdf5af4f26eea63b896e417c80, message=invalid: bad signature, flag=false
Oct 20, 2024 10:24:37 PM nostr.connection.impl.ConnectionPool connect
INFO: Connecting to relays
Oct 20, 2024 10:24:37 PM nostr.connection.impl.ConnectionImpl send
INFO: Sending message: ["EVENT",{"id":"40dbdefc8d47939964d9969858dd8a9e60027456cc8a5e8fed9e968f39e24afb","kind":4,"content":"PCK1/dANkWyTqgYnaoaiRzD2P12JCvBlvYwkRATXTuE=?iv=3PgEPD95xHgfWbk3N1LjSA==","pubkey":"edd898fc2817ee64f7ee1941d193d53c2daa77db4b8409240565fc9644626878","created_at":1729459477,"tags":[["p","40b1054933b5f32c567a02f4ea1c89803e1aba7936f92cb9d3c6a26e1f3d02aa"]],"sig":"57fbd20c743f5342c24535189b62e6aa50f62ce2436100e5891879b6ce195a16b8aa822ac8bbad371a2daa206c7f2f4c2f598a3e69f75c43bc20398a3e178010"}] - Relay: Relay(scheme=ws, host=localhost:3333)
Oct 20, 2024 10:24:37 PM nostr.connection.impl.listeners.TextListener onMessage
INFO: WebSocket received: ["OK","40dbdefc8d47939964d9969858dd8a9e60027456cc8a5e8fed9e968f39e24afb",true,""] - Relay: Relay(scheme=ws, host=localhost:3333)
Oct 20, 2024 10:24:37 PM nostr.connection.impl.listeners.TextListener handleReceivedText
INFO: Received message ["OK","40dbdefc8d47939964d9969858dd8a9e60027456cc8a5e8fed9e968f39e24afb",true,""] from Relay(scheme=ws, host=localhost:3333)
Oct 20, 2024 10:24:37 PM client.provider.OKCustomHandler onCommand
INFO: <<< Received OK message: eventId=40dbdefc8d47939964d9969858dd8a9e60027456cc8a5e8fed9e968f39e24afb, message=, flag=true
Oct 20, 2024 10:24:38 PM nostr.connection.impl.ConnectionPool connect
INFO: Connecting to relays
Oct 20, 2024 10:24:38 PM nostr.connection.impl.ConnectionImpl send
INFO: Sending message: ["EVENT",{"id":"aeb3a372c47c8992b9932cc3a36647b272f24136c53d92516d16eb21f2094f22","kind":44,"content":"AhLUSc6aDL7a/OIaRXrNY+jCmKipJvzupDfCKAxGLWKEetH8eJ58ACGE/m2dPiKHelKvO0Kv6ey35nlYEy+cJlxtFK66F8PSzp4zf4+nSQpyCfQl4R6r2aqY3ytA2pRsZTb+","pubkey":"edd898fc2817ee64f7ee1941d193d53c2daa77db4b8409240565fc9644626878","created_at":1729459478,"tags":[["p","40b1054933b5f32c567a02f4ea1c89803e1aba7936f92cb9d3c6a26e1f3d02aa"]],"sig":"4ec5f2ef7cd4509b7ecdb1c7d5b2b33891bf6122e58cdc1af227a09b094a947aad123b6e70f824552977146299974a6aeaf311b574aac73bd865cee671a86e8a"}] - Relay: Relay(scheme=ws, host=localhost:3333)
Oct 20, 2024 10:24:38 PM nostr.connection.impl.listeners.TextListener onMessage
INFO: WebSocket received: ["OK","aeb3a372c47c8992b9932cc3a36647b272f24136c53d92516d16eb21f2094f22",true,""] - Relay: Relay(scheme=ws, host=localhost:3333)
Oct 20, 2024 10:24:38 PM nostr.connection.impl.listeners.TextListener handleReceivedText
INFO: Received message ["OK","aeb3a372c47c8992b9932cc3a36647b272f24136c53d92516d16eb21f2094f22",true,""] from Relay(scheme=ws, host=localhost:3333)
Oct 20, 2024 10:24:38 PM client.provider.OKCustomHandler onCommand
INFO: <<< Received OK message: eventId=aeb3a372c47c8992b9932cc3a36647b272f24136c53d92516d16eb21f2094f22, message=, flag=true
Oct 20, 2024 10:24:38 PM nostr.connection.impl.ConnectionPool connect
INFO: Connecting to relays
Oct 20, 2024 10:24:38 PM nostr.connection.impl.ConnectionImpl send
INFO: Sending message: ["REQ","sub_11c9e2525da2df874123a78950590a1b1f784ce6d65b1fb9c784554b88bffb50",{"kinds":[1],"since":1729459418}] - Relay: Relay(scheme=ws, host=localhost:3333)
Oct 20, 2024 10:24:38 PM nostr.connection.impl.listeners.TextListener onMessage
INFO: WebSocket received: ["EVENT","sub_11c9e2525da2df874123a78950590a1b1f784ce6d65b1fb9c784554b88bffb50",{"content":"Bonjour Nostr Monde!","created_at":1729459476,"id":"cefc7ec402312f8a9e6cfc063b5ca939236e91a189b213218419e088d00581bf","kind":1,"pubkey":"287bef38850f805ee37e1a4241517cef91e684b7602708eff08dcebfff546afc","sig":"148945be91aa410de5acb83fc4e3bf52cee2fb9e064b96c7d83e2eaa506b636659c11676151dbbb0ff056335752afdfcd0c71667dd7456f3d1e1edcdc0be253b","tags":[["e","86aa0dc2a82078b41548597470e1c6ab9f5c41a3d2d6763cabd3e0b3cf8b11be"]]}] - Relay: {0}
Oct 20, 2024 10:24:38 PM nostr.connection.impl.listeners.TextListener handleReceivedText
INFO: Received message ["EVENT","sub_11c9e2525da2df874123a78950590a1b1f784ce6d65b1fb9c784554b88bffb50",{"content":"Bonjour Nostr Monde!","created_at":1729459476,"id":"cefc7ec402312f8a9e6cfc063b5ca939236e91a189b213218419e088d00581bf","kind":1,"pubkey":"287bef38850f805ee37e1a4241517cef91e684b7602708eff08dcebfff546afc","sig":"148945be91aa410de5acb83fc4e3bf52cee2fb9e064b96c7d83e2eaa506b636659c11676151dbbb0ff056335752afdfcd0c71667dd7456f3d1e1edcdc0be253b","tags":[["e","86aa0dc2a82078b41548597470e1c6ab9f5c41a3d2d6763cabd3e0b3cf8b11be"]]}] from Relay(scheme=ws, host=localhost:3333)
**Oct 20, 2024 10:24:38 PM client.provider.EventCustomHandler onCommand
INFO: <<< Received EVENT GenericEvent(id=cefc7ec402312f8a9e6cfc063b5ca939236e91a189b213218419e088d00581bf, pubKey=287bef38850f805ee37e1a4241517cef91e684b7602708eff08dcebfff546afc, createdAt=1729459476, kind=1, tags=[EventTag(idEvent=86aa0dc2a82078b41548597470e1c6ab9f5c41a3d2d6763cabd3e0b3cf8b11be, recommendedRelayUrl=null, marker=null)], content=Bonjour Nostr Monde!, signature=148945be91aa410de5acb83fc4e3bf52cee2fb9e064b96c7d83e2eaa506b636659c11676151dbbb0ff056335752afdfcd0c71667dd7456f3d1e1edcdc0be253b, _serializedEvent=null, nip=null, attributes=[]) from relay Relay(scheme=ws, host=localhost:3333) with subscription id sub_11c9e2525da2df874123a78950590a1b1f784ce6d65b1fb9c784554b88bffb50**
Oct 20, 2024 10:24:38 PM nostr.connection.impl.listeners.TextListener onMessage
INFO: WebSocket received: ["EVENT","sub_11c9e2525da2df874123a78950590a1b1f784ce6d65b1fb9c784554b88bffb50",{"content":"Hello Nostr World!","created_at":1729459475,"id":"86aa0dc2a82078b41548597470e1c6ab9f5c41a3d2d6763cabd3e0b3cf8b11be","kind":1,"pubkey":"b178aa906ad89352d82da90ec5a732093aa7331100bfc6dfaca9b3642bb20295","sig":"4ff2d22b8d7cde7a39a172b48b0d139158311f3a92d2e2e85625b9b230bf6dae66d6dcf2ce2c96e5b07f88668e6ae706e93e7a16d829873358e77822b45b182b","tags":[]}] - Relay: {0}
Oct 20, 2024 10:24:38 PM nostr.connection.impl.listeners.TextListener handleReceivedText
INFO: Received message ["EVENT","sub_11c9e2525da2df874123a78950590a1b1f784ce6d65b1fb9c784554b88bffb50",{"content":"Hello Nostr World!","created_at":1729459475,"id":"86aa0dc2a82078b41548597470e1c6ab9f5c41a3d2d6763cabd3e0b3cf8b11be","kind":1,"pubkey":"b178aa906ad89352d82da90ec5a732093aa7331100bfc6dfaca9b3642bb20295","sig":"4ff2d22b8d7cde7a39a172b48b0d139158311f3a92d2e2e85625b9b230bf6dae66d6dcf2ce2c96e5b07f88668e6ae706e93e7a16d829873358e77822b45b182b","tags":[]}] from Relay(scheme=ws, host=localhost:3333)
**Oct 20, 2024 10:24:38 PM client.provider.EventCustomHandler onCommand
INFO: <<< Received EVENT GenericEvent(id=86aa0dc2a82078b41548597470e1c6ab9f5c41a3d2d6763cabd3e0b3cf8b11be, pubKey=b178aa906ad89352d82da90ec5a732093aa7331100bfc6dfaca9b3642bb20295, createdAt=1729459475, kind=1, tags=[], content=Hello Nostr World!, signature=4ff2d22b8d7cde7a39a172b48b0d139158311f3a92d2e2e85625b9b230bf6dae66d6dcf2ce2c96e5b07f88668e6ae706e93e7a16d829873358e77822b45b182b, _serializedEvent=null, nip=null, attributes=[]) from relay Relay(scheme=ws, host=localhost:3333) with subscription id sub_11c9e2525da2df874123a78950590a1b1f784ce6d65b1fb9c784554b88bffb50**
Oct 20, 2024 10:24:38 PM nostr.connection.impl.listeners.TextListener onMessage
INFO: WebSocket received: ["EOSE","sub_11c9e2525da2df874123a78950590a1b1f784ce6d65b1fb9c784554b88bffb50"] - Relay: Relay(scheme=ws, host=localhost:3333)
Oct 20, 2024 10:24:38 PM nostr.connection.impl.listeners.TextListener handleReceivedText
INFO: Received message ["EOSE","sub_11c9e2525da2df874123a78950590a1b1f784ce6d65b1fb9c784554b88bffb50"] from Relay(scheme=ws, host=localhost:3333)
Oct 20, 2024 10:24:38 PM nostr.command.provider.EoseCommandHandler handle
INFO: onEose event - DefaultCommandContext(message=nostr.event.message.EoseMessage@640c572d, challenge=null, relay=Relay(scheme=ws, host=localhost:3333), privateKey=[-86, 102, -53, 60, 67, 62, 117, -78, -75, -87, 51, 88, 18, -113, 119, -57, 51, 68, 89, -51, 92, -114, -108, 41, 120, -85, 19, -48, -25, 109, 95, -65])

```
