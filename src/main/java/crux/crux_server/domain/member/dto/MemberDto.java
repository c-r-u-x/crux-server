package crux.crux_server.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class MemberDto {
    private Long id;
    private String kakaoId;
    private String role;
}
