package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional // JPA를 사용할 때는 반드시 있어야 한다.
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) { // MemberService는 MemberRepository가 필요 (의존관계)
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     * 같은 이름이 있는 회원은 가입 불가
     */
    public Long join(Member member) {
        /**
         * AOP 관련
         * 시간 측정을 위한 코드 추가
         */
        long start = System.currentTimeMillis();

        try {
            validateDuplicateMember(member); // 중복 회원 검증

            memberRepository.save(member);

            return member.getId();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;

            System.out.println("join " + timeMs + "ms");
        }

    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() { // 서비스 클래스는 보통 비즈니스와 비슷한 이름을 사용한다. (Role에 맞도록 네이밍)
        long start = System.currentTimeMillis();

        try {
            return memberRepository.findAll();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;

            System.out.println("findMembers " + timeMs + "ms");
        }
    }
}