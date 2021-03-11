package com.peter.broship.boot.web;

import com.peter.broship.boot.service.posts.PostsService;
import com.peter.broship.boot.web.dto.PostsResponseDto;
import com.peter.broship.boot.web.dto.PostsSaveRequestDto;
import com.peter.broship.boot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostsService postsService;

    @PostMapping("/api/v1/posts")//등록 insert
    public long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")//수정 update
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id,requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")//조회 select
    public PostsResponseDto findById(@PathVariable Long id){
        return postsService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")//삭제 delete
    public Long delete(@PathVariable Long id){
        postsService.delete(id);
        return id;
    }
}
