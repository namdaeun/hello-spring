package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;


public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; // key 값을 생성해줌

    @Override
    public Member save(Member member) {  // Member라는 참조 타입의 member 매개변수
        member.setId(++sequence);  // Id 세팅
        store.put(member.getId(), member);  // store에 저장
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));     // ofNullable : null도 감싸줌
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()    // 스트림 생성
                .filter(member -> member.getName().equals(name))  // 같은 경우만 필터링
                .findAny();  // 찾은 경우 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // 멤버 수

    }

    public void clearStore() {
        store.clear();
    }
}
