package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        //이거 만드는 순간 db랑 연결도 되고 다됨
        //엔티티 매니저 팩토리는 하나만 생성해서 애플리케이션 전체에서 공유
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello"); //persistence.xml에서 설정한 이름

        //엔티티 매니저는 쓰레드 간 공유 x -> 사용한 후 버려야 한다
        EntityManager em =  emf.createEntityManager();
        //실제 진행하는 코드 작성

        //트랜잭션 얻기 -> jpa 의 모든 데이터 변경은 트랜젝션 안에서 실행
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            //등록
            //비영속상태
/*            Member member = new Member();
            member.setId(1L);
            member.setName("HelloA");*/
            //persist로 객체 저장 -> 엔티티의 비영속에서 영속화
            // em.persist(member);
            //em.detach(member); 영속성을 지워벌임;;
            //em.remove(member); 삭제

            //조회
            Member findMember = em.find(Member.class, 1L); //pk 가 1L인 걸 찾기

            //삭제
            //em.remove(findMember);

            //수정 em.persist()안해도 수정됩니당
            //findMember.setName("이현");

            //jpa 는 테이블이 기준이 아니라 객체 기준으로 잡기 때문에 membervo에 해당하는거 다 가져와 하는거임
            List<Member> findMembers = em.createQuery("select m from Member as m", Member.class)
                    //pagenation에서 사용 -> 1부터 5개 가져왓 ㅋ
                    .setFirstResult(1)
                    .setMaxResults(5)
                    .getResultList();
            
            for (Member member : findMembers){
                System.out.println("members " + member.getName());
            }

            tx.commit(); //필 수
            //tx.commit()해야 db에 쿼리가 날라감
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}