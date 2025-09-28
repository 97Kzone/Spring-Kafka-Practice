package kzone.board.articleread.consumer;

import kzone.board.articleread.service.ArticleReadService;
import kzone.board.common.event.Event;
import kzone.board.common.event.EventPayload;
import kzone.board.common.event.EventType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ArticleReadEvnetConsumer {
    private final ArticleReadService articleReadService;

    @KafkaListener(topics = {
            EventType.Topic.KZONE_BOARD_ARTICLE,
            EventType.Topic.KZONE_BOARD_COMMENT,
            EventType.Topic.KZONE_BOARD_LIKE
    })
    public void listen(String message, Acknowledgment ack) {
        log.info("[ArticleReadEventConsumer.listen] message = {}", message);
        Event<EventPayload> event = Event.fromJson(message);
        if (event != null) {
            articleReadService.handleEvent(event);
        }
        ack.acknowledge();

    }
}
