package kzone.board.common.event;

import kzone.board.common.event.payload.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@RequiredArgsConstructor
public enum EventType {
    ARTICLE_CREATED(ArticleCreatedEventPayload.class, Topic.KZONE_BOARD_ARTICLE),
    ARTICLE_UPDATED(ArticleUpdatedEventPayload.class, Topic.KZONE_BOARD_ARTICLE),
    ARTICLE_DELETED(ArticleDeletedEventPayload.class, Topic.KZONE_BOARD_ARTICLE),
    COMMENT_CREATED(CommentCreatedEventPayload.class, Topic.KZONE_BOARD_COMMENT),
    COMMENT_DELETED(CommentDeletedEventPayload.class, Topic.KZONE_BOARD_COMMENT),
    ARTICLE_LIKED(ArticleLikedEventPayload.class, Topic.KZONE_BOARD_LIKE),
    ARTICLE_UNLIKED(ArticleUnlikedEventPayload.class, Topic.KZONE_BOARD_LIKE),
    ARTICLE_VIEWED(ArticleViewedEventPayload.class, Topic.KZONE_BOARD_VIEW),;

    private final Class<? extends EventPayload> payloadClass;
    private final String topic;

    public static EventType from(String type) {
        try {
            return valueOf(type);
        } catch (Exception e) {
            log.error("[EventType.from] type = {}", type, e);
            return null;
        }

    }

    public static class Topic {
        public static final String KZONE_BOARD_ARTICLE = "kzone-board-article";
        public static final String KZONE_BOARD_COMMENT = "kzone-board-comment";
        public static final String KZONE_BOARD_LIKE = "kzone-board-like";
        public static final String KZONE_BOARD_VIEW = "kzone-board-view";
    }
}
