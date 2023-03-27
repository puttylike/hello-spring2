package hello.hellospring.repository;

import hello.hellospring.domain.Member; // Member Class를 import했다.

import java.util.*;

// MemberRepository Interface를 구현할 클래스를 만들자
public class MemoryMemberRepository implements MemberRepository{    // Ait + Enter 로 Interface에 있는 모든 메소드들을 가져올 수 있다.

    // 데이터를 저장할 공간을 만들자
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    // 회원 저장 메소드
    public Member save(Member member) {     // member 객체 안에 name은 저장되어 넘어온 것이라 보면 된다.
        member.setId(++sequence);   // member 객체의 id에 시퀀스를 1증가시켜 저장
        store.put(member.getId(), member);
        // Map<Long, Member>타입의 Long자리에 1증가시켰던 시퀀스를,
        // Member자리에 member객체를 저장한다. (1증가된 시퀀스와, 원래 있던 이름이 있음)

        return member;  // 그리고 member 객체를 반환한다.
    }

    @Override
    // id로 회원 찾는 메서드 -> Optional<Member>으로 반환한다.
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
        //Null이 반환될 가능성이 있으면 Optional.ofNullable()로 감싸준다
    }

    @Override
    // name으로 회원 찾는 메서드 -> Optional<Member>으로 반환한다.
    public Optional<Member> findByName(String name) {
        return store.values().stream().filter(member -> member.getName().equals(name))
                .findAny();
        // member객체에 있는 name이랑 매개변수로 넘어온 name이 같은지 확인하여
        // 일치하는 경우에만 필터링이 되고, 그 중에서 Member객체를 찾으면 반환이 된다.
        // 찾았는데 없으면 Optional에 null이 포함되어 반환된다.
    }

    @Override
    // 모든 회원 List를 조회하는 메소드 -> List<Member>로 반환한다.
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
        // 리스트를 많이 쓴다. 위처럼 반환하면 된다.
        // values()는 member다.
    }

    // store객체를 비우도록 하는 메소드
    public void clearStore() {
        store.clear();
    }
} // 이제 이것이 잘 동작하는지 확인하기 위해 testcase를 작성한다.