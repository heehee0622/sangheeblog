package com.sanghee.test.sangheeblog;

import com.sanghee.test.sangheeblog.model.entity.Member;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class Test {
    @PersistenceContext
    private EntityManager em;

    @Before
    @Commit

    public void init() {
        Member member = Member.builder().name("김상희").email("omen4").build();
        em.persist(member);
    }
    @org.junit.Test
    public void testMerge() {
        Member email =  em.createQuery("select m from Member m where m.email= :email", Member.class).setParameter("email", "omen4").getSingleResult();
        System.out.println(email + "id: " + email.getSeq() );
        em.detach(email);
        email.setName("김소영");
        Member merge = em.merge(email);
        System.out.println(merge + "id: " + merge.getSeq() );


    }

}
