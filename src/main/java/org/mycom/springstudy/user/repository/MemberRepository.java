package org.mycom.springstudy.user.repository;

import org.mycom.springstudy.user.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
