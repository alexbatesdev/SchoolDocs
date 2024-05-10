package com.example.fantasyapi.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long contractId;
    Long personId;
    Long devilId;

    public Contract(Long contractId, Long personId, Long devilId) {
        this.contractId = contractId;
        this.personId = personId;
        this.devilId = devilId;
    }

    public Contract() {

    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Long getDevilId() {
        return devilId;
    }

    public void setDevilId(Long devilId) {
        this.devilId = devilId;
    }


}
