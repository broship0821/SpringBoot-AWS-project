package com.peter.broship.boot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
//JpaRepository를 상속하면 기본적인 CRUD 생성됨, <Entity클래스,PK타입>
public interface PostsRepository extends JpaRepository<Posts,Long> {
}
