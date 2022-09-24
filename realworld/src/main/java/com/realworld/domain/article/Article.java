package com.realworld.domain.article;

/**
 * @author Taewoo
 */


import java.time.LocalDateTime;

import com.realworld.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String slug;

    private String title;

    private String description;

    private String body;

    @Column(updatable = false)
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    private int favoritesCount;

    @OneToOne
    private User author;


}

















