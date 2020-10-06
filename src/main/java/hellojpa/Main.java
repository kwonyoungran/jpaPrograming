package hellojpa;

import hellojpa.entity.Member;
import hellojpa.entity.Member2;
import hellojpa.entity.MemberType;
import hellojpa.entity.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        //설정정보 가져오기
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("hello");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        try {
            //Team 저장
            Team team = new Team();
            team.setName("TeamA");
            entityManager.persist(team);

            //Member 저장
            Member2 member = new Member2();
            member.setName("member1");
//            member.setTeamId(team.getId());
            member.setTeam(team);   //단방향 연관관계 설정, 참조 저장
            entityManager.persist(member);
            
            //양방향 매핑시 가장 많이 하는 실수
            Team team1 = new Team();
            team1.setName("TeamB");
            entityManager.persist(team1);
            
            Member2 member3 = new Member2();
            member3.setName("member2");

            //역방향(주인이 아닌 방향)만 연관관계 설정
            team1.getMembers().add(member3);
            //주인인 경우 저장됨
            member3.setTeam(team1);
            entityManager.persist(member3);

            entityManager.flush();  //쿼리를 DB에 보냄
            entityManager.clear();  //캐시삭제

            //조회
            Member2 findMember = entityManager.find(Member2.class, member.getId());
            //연관관계 없음
//            Team findTeam = entityManager.find(Team.class, findMember.getTeamId());
            Team findTeam = findMember.getTeam();
            findTeam.getName();

            List<Member2> members = findTeam.getMembers();
            for(Member2 member2 : members) {
                System.out.println("member2 = " + member2);
            }

            int memberSize = findTeam.getMembers().size();

            entityTransaction.commit();
        } catch (Exception e) {
            entityTransaction.rollback();
        } finally {
            entityManager.close();
        }

        entityManagerFactory.close();
    }
}
