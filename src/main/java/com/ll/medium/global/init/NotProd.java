package com.ll.medium.global.init;

import com.ll.medium.domain.member.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class NotProd {
    private final MemberService memberService;

    @Bean
    public ApplicationRunner initNotProd() {
        return args -> {
            if (memberService.count() > 0) return;

            // ddl-auto가 업데이트인 경우라면 아래 코드들이 앱 실행시 마다 실행되는게 문제가 된다.
            // 그러기에 위 코드에서 count로 회원 수를 확인후 그것이 오직 0인 경우에만 아래 코드들을 실행하도록 한다.

            memberService.join("system", "1234");
            memberService.join("admin", "1234");
            memberService.join("user1", "1234");
            memberService.join("user2", "1234");
        };
    }
}
