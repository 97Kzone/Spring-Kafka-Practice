package kzone.board.comment.controller;

import kzone.board.comment.service.CommentServiceV2;
import kzone.board.comment.service.request.CommentCreateRequestV2;
import kzone.board.comment.service.response.CommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentControllerV2 {
    private final CommentServiceV2 commentService;

    @GetMapping("/v2/comments/{commentId}")
    public CommentResponse read(
            @PathVariable("commentId") Long commentId
    ) {
        return commentService.read(commentId);
    }

    @PostMapping("/v2/comments")
    public CommentResponse create(@RequestBody CommentCreateRequestV2 request) {
        return commentService.create(request);
    }

    @DeleteMapping("/v2/comment/{commentId}")
    public void delete(
            @PathVariable("commentId") Long commentId) {
        commentService.delete(commentId);
    }
}
