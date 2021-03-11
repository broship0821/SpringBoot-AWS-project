package com.peter.broship.boot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//JpaRepository를 상속하면 기본적인 CRUD 생성됨, <Entity클래스,PK타입>
public interface PostsRepository extends JpaRepository<Posts,Long> {
    //SpringDataJpa에서 제공하지 않는 다른 쿼리 추가하는 방법
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
