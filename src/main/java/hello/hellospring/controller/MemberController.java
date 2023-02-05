package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller //스프링이 실행되고 스프링 컨테이너가 생성될 때, 이 객체를 생성하여 스프링 컨테이너에 넣어둔다.
public class MemberController {

    private final MemberService memberService;  //new 를 사용하여 생성해도 되지만, 스프링 컨테이너에 등록하여 사용하는게 더 이익이 많다.

    @Autowired  //Spring 컨테이너에서 MemberService를 가져온다.
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> memberList = memberService.findMembers();
        model.addAttribute("members", memberList);
        return "members/memberList";

    }

}
