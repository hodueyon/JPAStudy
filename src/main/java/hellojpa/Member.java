package hellojpa;

import javax.persistence.*;
import java.util.Date;

@Entity //이걸 해야 jpa 쓰는 애라고 인식을함 - 기본생성자 필수 final, enum, interface, inner 클래스 사용 x, 저장할 필드에 final사용 X
//@Table(name="USER") //vo는 멤버지만 실제 테이블은 다를경우
public class Member {
    @Id //jpa에 이게 pk라고 알려주기
    private long id;
//    @Column (name="naming") 테이블에 있는 컬럼명과 vo에 있는 변수명이 다를경우 매핑해주기
//    @Column(unique = true, length = 10, nullable = false) //nullable = false : not null, unique는 잘 안 씀
//    @Column(columnDefinition = "varchar(100) default 'EMPTY'")
    private String name;

    //@Column(precision = 5, scale = 5) //precision - 소수점 포함한 전체 자리수 설정, scale - 소수점 자리수만 설정
    private int age;

    @Enumerated(EnumType.STRING) //-- enum이름을 저장 2)Enumerated(EnumType.Ordinal) - 사용하면 안됨, enum순서를 디비에저장
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP) //날짜
    private Date CreatedDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;

    @Lob //varchar을 넘어가는 큰 콘덴츠 - blob, clob
    private String description;

    @Transient //db에 안쓸거, 메모리에서만 할거 
     private int temp;
    public Member(){

    }
    public Member(Long id, String name){
        this.id = id;
        this.name = name;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public Date getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(Date createdDate) {
        CreatedDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }


}



























































