package client.provider;

import lombok.NonNull;
import nostr.base.Command;
import nostr.base.Relay;
import nostr.base.annotation.CustomHandler;
import nostr.event.BaseMessage;
import nostr.event.message.OkMessage;

import java.util.logging.Level;

import static nostr.base.Command.OK;

@CustomHandler(command = OK)
public class OKCustomHandler extends BaseCustomCommandHandler {

    public OKCustomHandler() {
        super(OK);
    }

    @Override
    protected void onCommand(@NonNull BaseMessage message, @NonNull Relay relay) {
        var okMessage = (OkMessage) message;
        var eventId = okMessage.getEventId();
        var msg = okMessage.getMessage();
        var flag = okMessage.getFlag();
        log.log(Level.INFO, "<<< Received OK message: eventId={0}, message={1}, flag={2}", new Object[]{eventId, msg, flag});
    }
}
