package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    // 회원 가입

    public Long join(Member member) {
        // 같은 이름의 중복 회원 X
        // command + option + v 입력시 표현식 optinal 자동완성!! <<중요>>
        Optional<Member> result = memberRepository.findByName(member.getName());
        result.isPresent(m -> {
            try {
                System.out.println("hello");
            } catch (IllegalStateException) {
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            }
        });

        memberRepository.save(member);
        return member.getId();
    }
}
