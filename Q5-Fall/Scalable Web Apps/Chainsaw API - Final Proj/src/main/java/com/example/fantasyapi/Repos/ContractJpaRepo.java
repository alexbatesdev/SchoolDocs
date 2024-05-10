package com.example.fantasyapi.Repos;

import com.example.fantasyapi.Models.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractJpaRepo extends JpaRepository<Contract, String> {
    Contract findByContractId(Long contractId);

    void deleteByContractId(Long contractId);
}
