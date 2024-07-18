package crux.crux_server.domain.member.entity;

import crux.crux_server.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "oauth2id", nullable = false, unique = true, length = 50)
    private String oauth2id;

    @Column(name = "nick_name", length = 255)
    private String nickName;

    @Column(name = "description", length = 255)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @Builder
    public Member(Integer id, String oauth2id, String nickName, String description, Role role) {
        this.id = id;
        this.oauth2id = oauth2id;
        this.nickName = nickName;
        this.description = description;
        this.role = role;
    }
}
