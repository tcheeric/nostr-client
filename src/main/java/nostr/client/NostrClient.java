/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package nostr.client;

import java.time.Instant;
import java.util.ArrayList;
import java.util.logging.Level;
import lombok.extern.java.Log;
import nostr.base.PublicKey;
import nostr.event.Kind;
import nostr.event.impl.Filters;
import nostr.event.impl.GenericMessage;
import nostr.event.impl.TextNoteEvent;
import nostr.event.list.KindList;
import nostr.event.list.PublicKeyList;
import nostr.event.message.EventMessage;
import nostr.event.message.ReqMessage;
import nostr.id.Client;
import nostr.id.Identity;
import nostr.util.NostrException;

/**
 *
 * @author eric
 */
@Log
public class NostrClient {

    public static void main(String[] args) throws NostrException {
        Client client = createClient();
        
        // Send a filter request. The results will be published in your custom command handler's onEvent method
        PublicKey recipient = new PublicKey(args[0]);
        filter(client, recipient);
        
        // Send a message
        send(client, args[1]);
    }

    private static void send(Client client, String content) throws NostrException {

        // Get the sender's identity
        final Identity identity = Identity.getInstance();
        PublicKey sender = identity.getPublicKey();

        // Create the event
        var event = new TextNoteEvent(sender, new ArrayList<>(), content);
        identity.sign(event);
        
        // Send the message
        GenericMessage message = new EventMessage(event);
        client.send(message);
    }

    public static Client createClient() {
        final var client = Client.getInstance();

        do {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        } while (client.getThreadPool().getCompletedTaskCount() < (client.getRelays().size() / 2));

        return client;
    }

    private static void filter(Client client, PublicKey recipient) {

        long since = Instant.now().getEpochSecond() - 300;

        var kinds = new KindList();

        kinds.add(Kind.TEXT_NOTE);
        var referencePubKeys = new PublicKeyList();
        referencePubKeys.add(recipient);

        log.log(Level.INFO, "Filtering event since {0}", since);
        var filters = Filters.builder().kinds(kinds).referencePubKeys(referencePubKeys).since(since).build();

        log.log(Level.INFO, "Filters: {0}", filters);

        GenericMessage message = new ReqMessage("nostr-bot", filters);

        log.log(Level.INFO, "Sending message {0}", message);
        client.send(message);
    }

}
