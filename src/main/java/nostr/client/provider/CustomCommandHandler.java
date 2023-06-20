/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nostr.client.provider;

import java.util.logging.Level;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import nostr.base.Command;
import nostr.base.Relay;
import nostr.event.BaseEvent;
import nostr.event.json.codec.BaseEventDecoder;
import nostr.id.Client;
import nostr.id.Identity;
import nostr.util.NostrException;
import nostr.ws.handler.command.spi.ICommandHandler;

/**
 *
 * @author eric
 */
@Log
@NoArgsConstructor
public class CustomCommandHandler implements ICommandHandler {

    @Override
    public void onEose(String subscriptionId, Relay relay) {
        log.log(Level.INFO, "Command: {0} - Subscription ID: {1} - Relay {2}", new Object[]{Command.EOSE, subscriptionId, relay});
    }

    @Override
    public void onOk(String eventId, String reasonMessage, Reason reason, boolean result, Relay relay) {
        log.log(Level.INFO, "Command: {0} - Event ID: {1} - Reason: {2} ({3}) - Result: {4} - Relay {5}", new Object[]{Command.OK, eventId, reason, reasonMessage, result, relay});
    }

    @Override
    public void onNotice(String message) {
        log.log(Level.WARNING, "Command: {0} - Message: {1}", new Object[]{Command.NOTICE, message});
    }

    @Override
    public void onEvent(String jsonEvent, String subId, Relay relay) {
        try {
            var event = unmarshallEvent(jsonEvent);
            log.log(Level.INFO, "Event {0} received from relay {1} with subscription id {2}", new Object[]{event, relay, subId});
        } catch (NostrException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void onAuth(String challenge, Relay relay) throws NostrException {
        log.log(Level.INFO, "Command: {0} - Challenge: {1} - Relay {3}", new Object[]{Command.AUTH, challenge, relay});

        var client = Client.getInstance();
        var identity = Identity.getInstance();

        client.auth(identity, challenge, relay);
    }

    private BaseEvent unmarshallEvent(String jsonEvent) throws NostrException {
        var decoder = new BaseEventDecoder(jsonEvent);
        return decoder.decode();
    }
}
