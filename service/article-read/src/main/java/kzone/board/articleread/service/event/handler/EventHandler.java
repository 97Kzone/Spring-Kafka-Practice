package kzone.board.articleread.service.event.handler;

import kzone.board.common.event.Event;
import kzone.board.common.event.EventPayload;

public interface EventHandler<T extends EventPayload> {
    void handle(Event<T> event);
    boolean supports(Event<T> event);
}
