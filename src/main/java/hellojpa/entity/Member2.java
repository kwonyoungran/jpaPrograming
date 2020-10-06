package hellojpa.entity;

import javax.persistence.*;

@Entity
public class Member2 {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name="USERNAME")
    private String name;

    private int age;

    //단방향 연관관계
    @ManyToOne(fetch = FetchType.LAZY)  //지연로딩(실제 사용할때 값을 가져온다, 권장) default는 EAGER
    @JoinColumn(name = "TEAM_ID")
    private Team team;

//    연관관계 없음
//    @Column(name="TEAM_ID")
//    private Long teamId;

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

//    public Long getTeamId() {
//        return teamId;
//    }
//
//    public void setTeamId(Long teamId) {
//        this.teamId = teamId;
//    }


    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "Member2{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", team=" + team +
                '}';
    }
}
