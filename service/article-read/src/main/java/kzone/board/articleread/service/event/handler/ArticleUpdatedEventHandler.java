package kzone.board.articleread.service.event.handler;

import kzone.board.articleread.repository.ArticleQueryModel;
import kzone.board.articleread.repository.ArticleQueryModelRepository;
import kzone.board.common.event.Event;
import kzone.board.common.event.EventType;
import kzone.board.common.event.payload.ArticleCreatedEventPayload;
import kzone.board.common.event.payload.ArticleUpdatedEventPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@RequiredArgsConstructor
public class ArticleUpdatedEventHandler implements EventHandler<ArticleUpdatedEventPayload> {
    private final ArticleQueryModelRepository articleQueryModelRepository;

    @Override
    public void handle(Event<ArticleUpdatedEventPayload> event) {
        articleQueryModelRepository.read(event.getPayload().getArticleId())
                .ifPresent(articleQueryModel -> {
                    articleQueryModel.updateBy(event.getPayload());
                    articleQueryModelRepository.update(articleQueryModel);
                });
    }

    @Override
    public boolean supports(Event<ArticleUpdatedEventPayload> event) {
        return EventType.ARTICLE_UPDATED == event.getType();
    }
}
