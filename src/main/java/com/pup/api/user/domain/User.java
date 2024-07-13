package com.pup.api.user.domain;

import com.pup.global.enums.OpenRangeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import com.pup.global.domain.BaseTimeEntity;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "TB_USER")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseTimeEntity {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Comment("이메일")
    @Column(length = 100, unique = true, nullable = false)
    private String email;

    @Comment("비밀번호")
    @Column(length = 150, nullable = true)
    private String password;

    @Comment("유저 UID")
    @Column(nullable = false, name = "user_uid")
    private UUID userUid;

    @Comment("소셜 타입")
    @Column(nullable = false, name = "social_type")
    @Enumerated(EnumType.STRING)
    private UserSocialTypeEnum socialType;

    @Comment("소셜 토큰")
    @Column(length = 255, nullable = true, name = "social_token")
    private String socialToken;

    @Comment("닉네임")
    @Column(length = 50, nullable = false)
    private String nickname;

    @Comment("프로필 이미지")
    @Column(length = 255, nullable = false)
    private String profile;

    @Comment("자기 소개")
    @Column(length = 30, nullable = false)
    private String description;

    @Comment("산책 기록 공개 범위")
    @Column(nullable = false, name = "open_range")
    @Enumerated(EnumType.STRING)
    private OpenRangeEnum openRange;

    @Comment("마지막 산책 시간")
    @Column(nullable = true, name = "last_waking_date")
    private LocalDateTime lastWakingDate;
}
