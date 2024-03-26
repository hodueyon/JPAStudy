package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain3Flush {
    //flush() -> 변경감지,
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em =  emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member mem = new Member(200L, "member20");
            em.persist(mem);

            em.flush(); //강제로 호출 - 이 시점에 db에 즉시 쿼리 실행됨 1차캐시를 지우는게 아님,
            //쓰기 지연 sql저장소에 있는거, 변경감지 일어난거 같은거 싹 당장 해 ~!


            System.out.println("------------------");
            tx.commit();

        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();

    }
}
