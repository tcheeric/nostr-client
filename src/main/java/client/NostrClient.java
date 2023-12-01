/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package client;

import nostr.api.NIP01;
import nostr.api.NIP04;
import nostr.api.Nostr;
import nostr.event.BaseTag;
import nostr.event.list.PublicKeyList;
import nostr.id.Identity;

import java.util.ArrayList;

/**
 *
 * @author eric
 */
public class NostrClient {

    public static void main(String[] args) {
        // Send messages
        send();
    }

    private static void send() {

        // Create the event with the default identity
        var event = NIP01.createTextNoteEvent("Hello Nostr World!");
        Nostr.sign(event);
        Nostr.send(event);

        // Create an event with a specific identity
        final Identity identity = Identity.getInstance();
        event = NIP01.createTextNoteEvent(identity, "Bonjour Nostr Monde!");
        Nostr.sign(event);
        Nostr.send(event);

        // Create an event with a specific identity and tags
        var recipient = NIP01.createPubKeyTag(Identity.generateRandomIdentity().getPublicKey());
        var tags = new ArrayList<BaseTag>();
        tags.add(recipient);
        event = NIP01.createTextNoteEvent(identity, tags, "Hola Nostr Mundo!");
        Nostr.sign(event);
        Nostr.send(event);

        // Send a DM
        var directMessageEvent = NIP04.createDirectMessageEvent(Identity.generateRandomIdentity().getPublicKey(), "Guten Tag Nostr Welt!");
        Nostr.sign(directMessageEvent);
        Nostr.send(directMessageEvent);

        // Create a filter
        PublicKeyList authors = new PublicKeyList();
        authors.add(identity.getPublicKey());
        var filter = NIP01.createFilters(null, authors, null, null, null, null, null, 20, null);
        Nostr.send(filter);
    }
}
