package com.backend.magicarpe.repositories;

import java.util.Optional;
import com.backend.magicarpe.model.ERole;
import com.backend.magicarpe.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}