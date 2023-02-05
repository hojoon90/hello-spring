package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);   //시스템이 정해주는 ID. name 은 사용자가 지정하는 값.
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) { //요즘은 Null처리를 Optional로 처리해준다.
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()  //Stream은 Optional로 리턴이 가능하기 때문에 스트림으로 바로 리턴 가능.
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
