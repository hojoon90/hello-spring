package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest //스프링 연동 테스트는 이 어노테이션을 붙여준다.
@Transactional  //테스트 후 insert, update, delete 됐던 데이터들을 rollback 해준다.
public class MemberServiceIntegrationTest {

    //@Autowired로 Spring Container에서 객체를 가져온다.
    //또한 Test는 별도의 설계없이 그냥 테스트용이므로 편한 방법으로 Injection하면 된다.
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;  //인터페이스를 가져온다.

    //beforeEach, afterEach가 삭제되었다.

    @Test
    void 회원가입() {   //테스트는 메소드 이름을 한글로 써도 무방하다
        //테스트 할때 기본적인 패턴
        //given
        Member member = new Member();
        member.setName("hello10");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("test");

        Member member2 = new Member();
        member2.setName("test");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        //then
    }

}
