package com.pup.api.user.domain;

import com.pup.global.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "TB_USER_BLOCK")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserBlock extends BaseTimeEntity {
    @Id
    @Column(name = "user_block_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userBlockId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "target_id", nullable = false)
    private User target;
}
