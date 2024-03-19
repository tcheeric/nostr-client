package client.provider;

import lombok.NoArgsConstructor;
import nostr.api.Nostr;
import nostr.base.Command;
import nostr.base.Relay;
import nostr.ws.handler.command.spi.ICommandHandler;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author eric
 */
@NoArgsConstructor
public class CustomCommandHandler implements ICommandHandler {

    private static final Logger log = Logger.getLogger(CustomCommandHandler.class.getName());

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

    /**
     * Log the event received from the relay
     * @param jsonEvent the event
     * @param subId the subscription id
     * @param relay the relay
     */
    @Override
    public void onEvent(String jsonEvent, String subId, Relay relay) {
        var event = Nostr.Json.decodeEvent(jsonEvent);
        log.log(Level.INFO, "Event {0} received from relay {1} with subscription id {2}", new Object[]{event, relay, subId});
    }

    @Override
    public void onAuth(String challenge, Relay relay) {
        log.log(Level.INFO, "Command: {0} - Challenge: {1} - Relay {3}", new Object[]{Command.AUTH, challenge, relay});
    }
}
