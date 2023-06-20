package ru.crystal.springboot.CRUDBoot_312.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.crystal.springboot.CRUDBoot_312.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
