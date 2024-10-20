package client.provider;

import lombok.NonNull;
import nostr.base.Relay;
import nostr.base.annotation.CustomHandler;
import nostr.event.BaseMessage;
import nostr.event.message.EoseMessage;

import java.util.logging.Level;

import static nostr.base.Command.EOSE;

@CustomHandler(command = EOSE)
public class EoseCustomHandler extends BaseCustomCommandHandler {

    public EoseCustomHandler() {
        super(EOSE);
    }

    @Override
    protected void onCommand(@NonNull BaseMessage message, @NonNull Relay relay) {
        var eoseMessage = (EoseMessage) message;
        var subId = eoseMessage.getSubscriptionId();
        log.log(Level.INFO, "<<< Received EOSE message from relay {0} with subscription id {1}", new Object[]{relay, subId});
    }
}
