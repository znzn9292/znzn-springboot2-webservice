package com.znzn.book.springboot.domain.posts;

import javafx.geometry.Pos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostsRepository extends JpaRepository<Posts, Long> {

    //Optional<List<Posts>> findByAuthor(String author);

}
