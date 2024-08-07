package com.pup.api.user.domain;

import com.pup.api.dog.domain.Dog;
import com.pup.api.friend.domain.Friend;
import com.pup.api.user.event.vo.LoginResponse;
import com.pup.api.walkingTrail.domain.WalkingTrail;
import com.pup.api.walkingTrail.domain.WalkingTrailLike;
import com.pup.global.dto.TokenDto;
import com.pup.global.enums.OpenRangeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import com.pup.global.domain.BaseTimeEntity;

import java.time.LocalDateTime;
import java.util.List;
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
    @Column(nullable = false, name = "user_uid", length = 20)
    private String userUid;

    @Comment("소셜 타입")
    @Column(nullable = false, name = "social_type")
    @Enumerated(EnumType.STRING)
    private UserSocialTypeEnum socialType;

    @Comment("소셜 토큰")
    @Column(length = 255, nullable = true, name = "social_token")
    private String socialToken;

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

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Dog> dogList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Friend> friendList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<WalkingTrail> walkingTrailList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<WalkingTrailLike> walkingTrailLikeList;

    public void changeUser(String profile, String description) {
        this.profile = profile;
        this.description = description;
    }

    public void changeLastWakingDate() {
        this.lastWakingDate = LocalDateTime.now();
    }

    public void changePassword(String password) {
        this.password = password;
    }

    public LoginResponse toResponse(TokenDto token) {
        return new LoginResponse(this.userId, this.email, this.userUid, token);
    }
}
