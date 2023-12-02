
# nostr-client

This is a demo nostr client application using the **nostr-java** library, and implemented as a java module.

## Setup

Before using the **nostr-client**, you need to perform some setup steps:

1. Create the folder `$HOME/.nostr-java` and add the following files:
  - `relays.properties`: for list of relays to connect to.
  
```properties
#Syntax relay_[identifier]=[relay URI]
relay_global=global.relay.red
relay_nostrview=relay.nostrview.com
```

   - `profile.properties`: for the private and public keys used for signing messages.
   ```properties
privateKey=<private key in hex format>
publicKey=<pub key in hex format>
```

## Writing the Client Code

### Custom Command Handler
The client provides a custom command handler, `client.provider.CustomCommandHandler`, for implementing a custom behaviour for the `onOk`, `onNotice`, `onError`, `onEose`, and `onEvent` relay events, overriding the existing default command handler.

To register the custom command handler:

1. Add the following entry to the `module-info.java` file:
```java
provides nostr.ws.handler.command.spi.ICommandHandler with client.provider.CustomCommandHandler;
```
2. You will also need to create the file `sr/main/resources/META-INF/services/nostr.ws.handler.command.spi.ICommandHandler` with the content:

```java
client.provider.CustomCommandHandler
```

## Compiling and Running the Code
To compile and run the **nostr-client** application, follow these steps:

 - Build the project using Maven in your preferred IDE.
 - Run the application using the following command:
```
java -jar nostr-client.jar 
```
