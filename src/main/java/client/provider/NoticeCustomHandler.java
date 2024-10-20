package client.provider;

import lombok.NonNull;
import nostr.base.Command;
import nostr.base.Relay;
import nostr.base.annotation.CustomHandler;
import nostr.event.BaseMessage;
import nostr.event.message.NoticeMessage;

import java.util.logging.Level;

import static nostr.base.Command.NOTICE;

@CustomHandler(command = NOTICE)
public class NoticeCustomHandler extends BaseCustomCommandHandler {

    public NoticeCustomHandler() {
        super(NOTICE);
    }

    @Override
    protected void onCommand(@NonNull BaseMessage message, @NonNull Relay relay) {
        var noticeMessage = (NoticeMessage) message;
        var msg = noticeMessage.getMessage();
        log.log(Level.INFO, "<<< Received NOTICE message: {0} from relay {1}", new Object[]{msg, relay});
    }
}