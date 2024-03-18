/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package client;

import nostr.api.NIP01;
import nostr.api.NIP04;
import nostr.api.NIP44;
import nostr.base.PrivateKey;
import nostr.event.BaseTag;
import nostr.event.impl.DirectMessageEvent;
import nostr.event.impl.EncryptedPayloadEvent;
import nostr.event.impl.TextNoteEvent;
import nostr.event.list.PublicKeyList;
import nostr.id.Identity;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author eric
 */
public class NostrClient {

    public static void main(String[] args) {
        // Send messages
        send();
    }

    private static void send() {

        var relays = Map.of("local relay", "ws://localhost:5555/", "nostr band", "wss://relay.nostr.band/");

        // Create the event with the default identity, sign and send to the default relay
        var sender = Identity.getInstance(PrivateKey.generateRandomPrivKey());
        NIP01<TextNoteEvent> nip01 = new NIP01<>(sender);
        nip01.createTextNoteEvent("Hello Nostr World!").sign().send();

        // Create an event with a specific identity
        sender = Identity.generateRandomIdentity();
        var nip01_ = new NIP01<>(sender);
        nip01_.createTextNoteEvent("Bonjour Nostr Monde!").addTag(NIP01.createEventTag(nip01.getEvent().getId())).signAndSend();

        // Create an event with a random identity and tags, sign and send to the default relay
        sender = Identity.getInstance(new PrivateKey("8c26b94af1a35b5e47ee2555f98602237767747e79c91909c61963e49140f46f"));
        var recipient = NIP01.createPubKeyTag(Identity.generateRandomIdentity().getPublicKey());
        nip01.createTextNoteEvent("Hola Nostr Mundo!").setRecipient(Identity.generateRandomIdentity().getPublicKey()).signAndSend();

        // Send a DM
        var nip04 = new NIP04<DirectMessageEvent>(sender, recipient.getPublicKey());
        nip04.createDirectMessageEvent("Guten Tag Nostr Welt!").signAndSend(relays);

        var nip44 = new NIP44<EncryptedPayloadEvent>(sender, recipient.getPublicKey());
        nip44.createDirectMessageEvent("Ciao Nostr Mondo!").signAndSend(relays);

        // Create a filter
        PublicKeyList authors = new PublicKeyList();
        authors.add(sender.getPublicKey());
        var filter = NIP01.createFilters(null, authors, null, null, null, null, null, 20, null);
        nip01.setRelays(relays).send(filter, "subscription_" + sender.getPublicKey().toString());
    }
}
