package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
public class MemberService {

    // 회원 리포지토리
        private final MemberRepository memberRepository;

        @Autowired
        public MemberService(MemberRepository memberRepository) {     // 외부에서 memberRepository를 넣어줌
            this.memberRepository = memberRepository;
        }

        /** 회원 가입 */
        public Long join(Member member) {
            // 같은 이름이 있는 중복 회원 X
            ValidateDuplicateMember(member); // 중복 회원 검증
            memberRepository.save(member);
            return member.getId();

        }

        private void ValidateDuplicateMember(Member member) {
            memberRepository.findByName(member.getName())
                    .ifPresent(m -> {    // 값이 존재하면
                        throw new IllegalStateException("이미 존재하는 회원입니다.");
                     });
        }

    /**
     * 전체 회원 조회 *
     */
        public List<Member> findMembers() {
            return memberRepository.findAll();
        }

        public Optional <Member> findOne(Long memberId) {
            return memberRepository.findById(memberId);
        }
}
