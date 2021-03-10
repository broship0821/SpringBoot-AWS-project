package com.peter.broship.boot.service.posts;

import com.peter.broship.boot.domain.posts.Posts;
import com.peter.broship.boot.domain.posts.PostsRepository;
import com.peter.broship.boot.web.dto.PostsResponseDto;
import com.peter.broship.boot.web.dto.PostsSaveRequestDto;
import com.peter.broship.boot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        //postsRepository.update()이런식으로 db 를 update하지 않아도
        //posts.update()만 해주면 트랜잭션이 끝나는 시점에 해당 테이블에 변경분을 반영함(더티 체킹)
        return id;
    }

    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        return new PostsResponseDto(entity);
    }
}
