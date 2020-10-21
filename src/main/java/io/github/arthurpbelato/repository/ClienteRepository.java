package io.github.arthurpbelato.repository;

import io.github.arthurpbelato.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface ClienteRepository extends JpaRepository<Cliente, BigInteger> {
    
}
