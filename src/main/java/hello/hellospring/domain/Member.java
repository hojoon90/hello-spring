package hello.hellospring.domain;

import javax.persistence.*;

@Entity //JPA가 관리하는 Entity이다.
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)//DB가 키를 알아서 생성해주는 것을 Identity 전략이라고 한다.
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
