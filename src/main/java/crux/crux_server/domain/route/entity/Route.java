package crux.crux_server.domain.route.entity;

import crux.crux_server.domain.sprint.entity.Sprint;
import crux.crux_server.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "route")
public class Route extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sprint_id", nullable = false)
    private Sprint sprint;

    @Column(name = "try_count", nullable = false)
    private Integer tryCount;

    @Column(name = "is_solved", nullable = false)
    private Boolean isSolved;

    @Column(name = "file_path", length = 255)
    private String filePath;

    @Column(name = "color")
    private Integer color;
}
