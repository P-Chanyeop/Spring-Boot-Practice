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
        // 이미 findByName함수가 Optinal로 반환하기 때문에, Optinal<Memeber> result는 지워도된다.
        Optional<Member> result = memberRepository.findByName(member.getName());

        // isPresent함수는 값이 있음, 없음을 체크하는 함수. Optinal 타입일 경우만 가능하다.
        // orElseGet같은 함수도 사용가능.
        if (result.isPresent()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }

        memberRepository.save(member);
        return member.getId();
    }
}
