package repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import domain.MembershipGroup;

public interface MembershipGroupDAO extends JpaRepository<MembershipGroup, Integer> {


}
