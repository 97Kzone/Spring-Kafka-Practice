package kzone.board.common.outboxmessagerelay;

import kzone.board.common.event.Event;
import kzone.board.common.event.EventPayload;
import kzone.board.common.event.EventType;
import kzone.board.common.snowflake.Snowflake;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OutboxEventPublisher {
    private final Snowflake outboxIdSnowflake = new  Snowflake();
    private final Snowflake eventIdSnowflake = new  Snowflake();
    private final ApplicationEventPublisher applicationEventPublisher;

    public void publish(EventType type, EventPayload payload, Long shardKey) {
        Outbox outbox = Outbox.create(
                outboxIdSnowflake.nextId(),
                type,
                Event.of(
                        eventIdSnowflake.nextId(), type, payload
                ).toJson(),
                shardKey % MessageRelayConstants.SHARD_COUNT
        );

        applicationEventPublisher.publishEvent(OutboxEvent.of(outbox));
    }

}
