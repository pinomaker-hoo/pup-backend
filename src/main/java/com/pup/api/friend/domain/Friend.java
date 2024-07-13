package com.pup.api.friend.domain;

import com.pup.api.user.domain.User;
import com.pup.global.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "TB_FRIEND")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Friend extends BaseTimeEntity {
    @Id
    @Column(name = "friend_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer friendId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "friend_id", nullable = false)
    private User friend;
}
