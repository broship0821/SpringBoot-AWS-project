package com.peter.broship.boot.service.posts;

import com.peter.broship.boot.domain.posts.Posts;
import com.peter.broship.boot.domain.posts.PostsRepository;
import com.peter.broship.boot.web.dto.PostsListResponseDto;
import com.peter.broship.boot.web.dto.PostsResponseDto;
import com.peter.broship.boot.web.dto.PostsSaveRequestDto;
import com.peter.broship.boot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
        /*
        postsRepository.findAllDesc()의 결과로 List<Posts> 가 반환됨
        .map(PostsListResponseDto::new) 로 Posts를 PostsListResponseDto로 변환
        결과로 List<PostsListResponseDto>가 반환됨
         */
    }

    @Transactional
    public void delete(Long id){
        //존재하는 Posts인지 확인
        Posts posts = postsRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        //확인 되었으면 삭제
        postsRepository.delete(posts);
    }
}
