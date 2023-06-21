# nostr-client

This is a test application for the nostr-client, utilizing the nostr-java library.

## Setup

Before using the **nostr-client**, you need to perform some setup steps:

1. Create the following files in the `$HOME/.nostr-java` folder:
   - `relays.properties`: for list of relays to connect to.
   - `profile.properties`: for the private and public keys used for signing messages.

### Example:

```properties
# Syntax relay_<identifier>=<relay URI>
relay_global=global.relay.red
relay_nostrview=relay.nostrview.com
```

## Writing the Client Code

### Custom Command Handler
The client utilises a custom command handler, `nostr.client.provider.CustomCommandHandler`, which implements the different relay command events: `onOk`, `onNotice`, `onError`, `onEose`, and `onEvent`.

To register the custom handler, add the following entry to the `module-info.java` file:

```java
provides nostr.ws.handler.command.spi.ICommandHandler with nostr.client.provider.CustomCommandHandler;
```

## Compiling and Running the Code
To compile and run the **nostr-client** application, follow these steps:

 - Build the project using Maven in your preferred IDE.
 - Provide two parameters when running the application:
	 - The public key in hex format.
	 - The content of the note to publish.

```
java -jar nostr-client.jar <public_key_hex> <note_content>
```
Make sure to replace **<public_key_hex>** with the actual public key in hex format and 
**<note_content>** with the desired note content.

The application will submit a filter request to the relay, fetching all notes from the provided public key that were submitted within the last 5 minutes. Additionally, it will publish a note with the provided content.

    cd /home/eric/NetBeansProjects/nostr-client; JAVA_HOME=/usr/lib/jvm/jdk-17 /snap/netbeans/80/netbeans/java/maven/bin/mvn -Dexec.vmArgs= "-Dexec.args=${exec.vmArgs} -classpath %classpath ${exec.mainClass} ${exec.appArgs}" "-Dexec.appArgs=3bf0c63fcb93463407af97a5e5ee64fa883d107ef9e558472c4eb9aaaefa459d 'Hello Memory-of-Snow!'" -Dexec.mainClass=nostr.client.NostrClient -Dexec.executable=/usr/lib/jvm/jdk-17/bin/java org.codehaus.mojo:exec-maven-plugin:3.1.0:exec
    Running NetBeans Compile On Save execution. Phase execution is skipped and output directories of dependency projects (with Compile on Save turned on) will be used instead of their jar artifacts.
    Scanning for projects...

    ---------------------< nostr-client:nostr-client >----------------------
    Building nostr-client 0.1-SNAPSHOT
      from pom.xml
    --------------------------------[ jar ]---------------------------------

    --- exec:3.1.0:exec (default-cli) @ nostr-client ---
    Jun 20, 2023 10:59:01 PM nostr.util.ApplicationConfiguration <init>
    INFO: Loading the application configuration file...
    Jun 20, 2023 10:59:01 PM nostr.util.AbstractBaseConfiguration load
    INFO: loading configuration file: /relays.properties
    Jun 20, 2023 10:59:01 PM nostr.util.AbstractBaseConfiguration load
    INFO: Configuration folder location: .nostr-java
    Jun 20, 2023 10:59:01 PM nostr.util.AbstractBaseConfiguration loadFromConfigDir
    INFO: Configuration file /home/eric/.nostr-java/relays.properties
    Jun 20, 2023 10:59:01 PM nostr.util.AbstractBaseConfiguration loadFromConfigDir
    INFO: Loading configuration file from /home/eric/.nostr-java
    Jun 20, 2023 10:59:02 PM nostr.ws.Connection serverURI
    INFO: Openning a secure connection to global.relay.red
    Jun 20, 2023 10:59:02 PM nostr.ws.Connection serverURI
    INFO: Openning a secure connection to relay.nostrview.com
    SLF4J: No SLF4J providers were found.
    SLF4J: Defaulting to no-operation (NOP) logger implementation
    SLF4J: See https://www.slf4j.org/codes.html#noProviders for further details.
    Jun 20, 2023 10:59:04 PM nostr.ws.ClientListenerEndPoint onConnect
    INFO: Connected
    Jun 20, 2023 10:59:04 PM nostr.ws.Connection connect
    INFO: The session is now open to wss://relay.nostrview.com
    Jun 20, 2023 10:59:05 PM nostr.ws.Connection updateRelayMetadata
    INFO: Relay information: {"name":"relay.nostrview.com","description":"Nostrview relay","pubkey":"2e9397a8c9268585668b76479f88e359d0ee261f8e8ea07b3b3450546d1601c8","contact":"2e9397a8c9268585668b76479f88e359d0ee261f8e8ea07b3b3450546d1601c8","supported_nips":[1,2,4,9,11,12,15,16,20,22,26,28,33,40,111],"software":"git+https://github.com/Cameri/nostream.git","version":"1.22.2","limitation":{"max_message_length":524288,"max_subscriptions":10,"max_filters":10,"max_limit":5000,"max_subid_length":256,"min_prefix":4,"max_event_tags":2500,"max_content_length":102400,"min_pow_difficulty":0,"auth_required":false,"payment_required":true},"payments_url":"https://relay.nostrview.com/invoices","fees":{"admission":[{"amount":4000000,"unit":"msats"}]}}
    Jun 20, 2023 10:59:05 PM nostr.ws.ClientListenerEndPoint onConnect
    INFO: Connected
    Jun 20, 2023 10:59:05 PM nostr.ws.Connection connect
    INFO: The session is now open to wss://global.relay.red
    Jun 20, 2023 10:59:06 PM nostr.ws.Connection updateRelayMetadata
    INFO: Relay information: {"name":"global.relay.red","description":"A nostr relay written in Typescript.","pubkey":"c61ca1ee4f85a8d0733a07434fd460602da9345abf9a0592a0a5948f16dd3c5e","contact":"sebastian@relay.red","supported_nips":[1,2,4,9,11,12,15,16,20,22,26,28,33,40],"supported_nip_extensions":["11a"],"software":"git+https://github.com/Cameri/nostream.git","version":"1.22.6","limitation":{"max_message_length":524288,"max_subscriptions":10,"max_filters":10,"max_limit":5000,"max_subid_length":256,"min_prefix":4,"max_event_tags":2500,"max_content_length":102400,"min_pow_difficulty":0,"auth_required":false,"payment_required":false},"payments_url":"https://global.relay.red/invoices","fees":{"admission":[]}}
    Jun 20, 2023 10:59:06 PM nostr.client.NostrClient filter
    INFO: Filtering event since 1,687,298,046
    Jun 20, 2023 10:59:06 PM nostr.client.NostrClient filter
    INFO: Filters: Filters(events=null, authors=null, kinds=BaseList(list=[1]), referencedEvents=null, referencePubKeys=BaseList(list=[3bf0c63fcb93463407af97a5e5ee64fa883d107ef9e558472c4eb9aaaefa459d]), since=1687298046, until=null, limit=null, genericTagQueryList=null)
    Jun 20, 2023 10:59:06 PM nostr.client.NostrClient filter
    INFO: Sending message ReqMessage(super=GenericMessage(command=REQ, attributes=[], nip=1), subscriptionId=nostr-bot, filters=Filters(events=null, authors=null, kinds=BaseList(list=[1]), referencedEvents=null, referencePubKeys=BaseList(list=[3bf0c63fcb93463407af97a5e5ee64fa883d107ef9e558472c4eb9aaaefa459d]), since=1687298046, until=null, limit=null, genericTagQueryList=null))
    Jun 20, 2023 10:59:06 PM nostr.ws.ClientListenerEndPoint onConnect
    INFO: Connected
    Jun 20, 2023 10:59:06 PM nostr.ws.Connection connect
    INFO: The session is now open to wss://relay.nostrview.com
    Jun 20, 2023 10:59:06 PM nostr.ws.request.handler.provider.DefaultRequestHandler sendMessage
    INFO: >>> Sending Message: ["REQ","nostr-bot",{"kinds":[1],"since":1687298046,"#p":["3bf0c63fcb93463407af97a5e5ee64fa883d107ef9e558472c4eb9aaaefa459d"]}]
    Jun 20, 2023 10:59:07 PM nostr.ws.ClientListenerEndPoint onConnect
    INFO: Connected
    Jun 20, 2023 10:59:07 PM nostr.ws.Connection connect
    INFO: The session is now open to wss://global.relay.red
    Jun 20, 2023 10:59:07 PM nostr.ws.request.handler.provider.DefaultRequestHandler sendMessage
    INFO: >>> Sending Message: ["REQ","nostr-bot",{"kinds":[1],"since":1687298046,"#p":["3bf0c63fcb93463407af97a5e5ee64fa883d107ef9e558472c4eb9aaaefa459d"]}]
    Jun 20, 2023 10:59:07 PM nostr.util.ApplicationConfiguration <init>
    INFO: Loading the application configuration file...
    Jun 20, 2023 10:59:07 PM nostr.util.AbstractBaseConfiguration load
    INFO: loading configuration file: /profile.properties
    Jun 20, 2023 10:59:07 PM nostr.util.AbstractBaseConfiguration load
    INFO: Configuration folder location: .nostr-java
    Jun 20, 2023 10:59:07 PM nostr.util.AbstractBaseConfiguration loadFromConfigDir
    INFO: Configuration file /home/eric/.nostr-java/profile.properties
    Jun 20, 2023 10:59:07 PM nostr.util.AbstractBaseConfiguration loadFromConfigDir
    INFO: Loading configuration file from /home/eric/.nostr-java
    Jun 20, 2023 10:59:07 PM nostr.ws.response.handler.provider.ResponseHandlerImpl process
    INFO: Process Message: ["EOSE","nostr-bot"] from relay: Relay(uri=global.relay.red)
    Jun 20, 2023 10:59:07 PM nostr.client.provider.CustomCommandHandler onEose
    INFO: Command: EOSE - Subscription ID: nostr-bot - Relay Relay(uri=global.relay.red)
    Jun 20, 2023 10:59:07 PM nostr.ws.ClientListenerEndPoint onConnect
    INFO: Connected
    Jun 20, 2023 10:59:07 PM nostr.ws.Connection connect
    INFO: The session is now open to wss://relay.nostrview.com
    Jun 20, 2023 10:59:07 PM nostr.ws.request.handler.provider.DefaultRequestHandler sendMessage
    INFO: >>> Sending Message: ["EVENT",{"id":"7f102092369d10bf541848bc0e671ced65e95bfc7df7d748f34e1c79b24364e1","kind":1,"content":"Hello Memory-of-Snow!","pubkey":"62d05a3e25e7c5948f345eed0372749f5632ec06b91156d1504b154d615a5928","created_at":1687298347,"tags":[],"sig":"6f0b00dc020e77819274bf07593ed59930b643b418b2d83f4ba2d930457e706a54ba40b4bc0a49305bf2208745e1932259b61514c9adf7a8bd3e48f1518cc229"}]
    Jun 20, 2023 10:59:08 PM nostr.ws.ClientListenerEndPoint onConnect
    INFO: Connected
    Jun 20, 2023 10:59:08 PM nostr.ws.Connection connect
    INFO: The session is now open to wss://global.relay.red
    Jun 20, 2023 10:59:08 PM nostr.ws.request.handler.provider.DefaultRequestHandler sendMessage
    INFO: >>> Sending Message: ["EVENT",{"id":"7f102092369d10bf541848bc0e671ced65e95bfc7df7d748f34e1c79b24364e1","kind":1,"content":"Hello Memory-of-Snow!","pubkey":"62d05a3e25e7c5948f345eed0372749f5632ec06b91156d1504b154d615a5928","created_at":1687298347,"tags":[],"sig":"6f0b00dc020e77819274bf07593ed59930b643b418b2d83f4ba2d930457e706a54ba40b4bc0a49305bf2208745e1932259b61514c9adf7a8bd3e48f1518cc229"}]
    Jun 20, 2023 10:59:08 PM nostr.ws.response.handler.provider.ResponseHandlerImpl process
    INFO: Process Message: ["OK","7f102092369d10bf541848bc0e671ced65e95bfc7df7d748f34e1c79b24364e1",true,""] from relay: Relay(uri=global.relay.red)
    Jun 20, 2023 10:59:08 PM nostr.client.provider.CustomCommandHandler onOk
    INFO: Command: OK - Event ID: 7f102092369d10bf541848bc0e671ced65e95bfc7df7d748f34e1c79b24364e1 - Reason: UNDEFINED () - Result: true - Relay Relay(uri=global.relay.red)