/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package client;

import nostr.api.NIP01;
import nostr.api.NIP04;
import nostr.api.NIP44;
import nostr.event.Kind;
import nostr.event.impl.DirectMessageEvent;
import nostr.event.impl.EncryptedPayloadEvent;
import nostr.event.impl.Filters;
import nostr.event.impl.TextNoteEvent;
import nostr.id.Identity;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;

/**
 * @author eric
 */
public class NostrClient {

    final static Map RELAYS = Map.of(/*"nostr-rs-relay relay", "ws://localhost:5555",*/ "strfry", "ws://localhost:3333");

    public static void main(String[] args) {

        // Send messages
        send();

        // Retrieve the Type-1 notes from relay
        Instant since = Instant.now().minus(Duration.ofMinutes(1));
        readFromGlobal(since.getEpochSecond());
    }

    private static void send() {

        // Create the event with a random identity, sign and send to the relays
        var sender = Identity.generateRandomIdentity();
        NIP01<TextNoteEvent> nip01 = new NIP01<>(sender);
        nip01.createTextNoteEvent("Hello Nostr World!").setSender(sender).sign().send(RELAYS);

        // Create an event with a random identity, and an e-tag, sign and send to the relays
        sender = Identity.generateRandomIdentity();
        var nip01_ = new NIP01<>(sender);
        nip01_.createTextNoteEvent("Bonjour Nostr Monde!").setSender(sender).addTag(NIP01.createEventTag(nip01.getEvent().getId())).signAndSend(RELAYS);

        // Create an event with a specific identity and p-tag (the recipient), sign and send to the default relay
        sender = Identity.create("8c26b94af1a35b5e47ee2555f98602237767747e79c91909c61963e49140f46f");
        var recipient = NIP01.createPubKeyTag(Identity.generateRandomIdentity().getPublicKey());
        nip01.createTextNoteEvent("Hola Nostr Mundo!").setSender(sender).setRecipient(Identity.generateRandomIdentity().getPublicKey()).signAndSend(RELAYS);

        // Send a NIP-04 DM
        var nip04 = new NIP04<DirectMessageEvent>(sender, recipient.getPublicKey());
        nip04.createDirectMessageEvent("Guten Tag Nostr Welt!").signAndSend(RELAYS);

        // Send a NIP-44 DM
        var nip44 = new NIP44<EncryptedPayloadEvent>(sender, recipient.getPublicKey());
        nip44.createDirectMessageEvent("Ciao Nostr Mondo!").signAndSend(RELAYS);

    }

    private static void readFromGlobal(Long sinceTimestamp) {
        // Create a filter
        Filters filters = Filters.builder().kinds(List.of(Kind.TEXT_NOTE)).since(sinceTimestamp).build();

        var sender = Identity.generateRandomIdentity();
        NIP01<TextNoteEvent> nip01 = new NIP01<>(sender);
        nip01.setRelays(RELAYS).send(filters, "sub_" + sender.getPublicKey().toString());
    }
}
