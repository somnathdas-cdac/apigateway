package com.microservice.ecart.apigateway.repository;




import com.microservice.ecart.apigateway.model.UserApp;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserAppRepository extends ReactiveCrudRepository<UserApp, Integer> {
    // Finds a user non-blockingly by username
   // Mono<UserApp> findByUsername(String username);
    Mono<UserApp> findByEmail(String email);
}
