package kzone.board.hotarticle.consumer;

import kzone.board.common.event.Event;
import kzone.board.common.event.EventPayload;
import kzone.board.common.event.EventType;
import kzone.board.hotarticle.service.HotArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class HotArticleConsumer {
    private final HotArticleService hotArticleService;

    @KafkaListener(topics = {
            EventType.Topic.KZONE_BOARD_ARTICLE,
            EventType.Topic.KZONE_BOARD_COMMENT,
            EventType.Topic.KZONE_BOARD_LIKE,
            EventType.Topic.KZONE_BOARD_VIEW,
    })
    public void listen(String message, Acknowledgment ack) {
        log.info("[HotArticleEventConsumer.listen] received message={}", message);
        Event<EventPayload> event = Event.fromJson(message);

        if (event != null) {
            hotArticleService.handleEvent(event);
        }

        ack.acknowledge();
    }
}
