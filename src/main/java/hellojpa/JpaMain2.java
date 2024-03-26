package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain2 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em =  emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            //비영속
//            Member member = new Member();
//            member.setId(100L);
//            member.setName("HelloJpa");
//
//            //영속
//            em.persist(member);
//
//            Member findMember = em.find(Member.class, 100L);
//            //select 쿼리 안나옴 -> 1차캐시에서 조회했다는 것
//            System.out.println(findMember.getName() + findMember.getId());

//            Member member1 = new Member(150L, "A");
//            Member member2 = new Member(160L, "B");

//            em.persist(member1);
//            em.persist(member2);

            Member findMember = em.find(Member.class, 150L);
            findMember.setName("AAAAAA"); //변경 감지 기능 -> persist나 update()란게 필요없다 update는 있지도 않지만~
            //수정할떄는 persist호출 안해도됨
            System.out.println("======================");
            tx.commit();
            //commit 한 후에 실제 dba에 쿼리가 동작됩니다.

        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();

    }
}
