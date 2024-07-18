package crux.crux_server.domain.member.controller;

import crux.crux_server.domain.member.entity.Member;
import crux.crux_server.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @QueryMapping
    public Member getMember(@Argument Integer id) {
        return memberService.getMember(id);
    }
}
