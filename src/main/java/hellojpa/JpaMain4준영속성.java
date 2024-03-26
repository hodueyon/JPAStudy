package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain4준영속성 {
    //flush() -> 변경감지,
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em =  emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member mem = em.find(Member.class, 200L);
            mem.setName("이현이현");

            //em.detach(mem); //준영속상태로 만들기

            em.clear(); ///1차 캐시 통으로 다 날리기 ----> 다시 db로 가서 조회하게끔 함 : 영속성 컨텍스트 완죠니 초기화

            Member mem2 = em.find(Member.class, 200L);
            System.out.println("------------------");
            tx.commit();

        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close(); //영속성 영속성종료 ?
        }

        emf.close();

    }
}
