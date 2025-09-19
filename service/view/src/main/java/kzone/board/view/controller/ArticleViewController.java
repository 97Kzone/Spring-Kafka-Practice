package kzone.board.view.controller;

import kzone.board.view.service.ArticleViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ArticleViewController {
    private final ArticleViewService articleViewService;

    @PostMapping("/v1/articles-views/articles/{articleId}/users/{usersId}")
    public Long increase(
            @PathVariable Long articleId,
            @PathVariable Long usersId
    ) {
        return articleViewService.increase(articleId, usersId);
    }

    @GetMapping("/v1/articles-views/articles/{articleId}/count")
    public Long count(@PathVariable("articleId") Long articleId) {
        return articleViewService.count(articleId);
    }
}
