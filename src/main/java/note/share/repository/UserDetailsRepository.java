package note.share.repository;

import note.share.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
    Optional<UserDetails> findByUserId(Long id);

    Optional<UserDetails> findByUserEmail(String email);

    Optional<UserDetails> findByUserUserName(String userName);
}
