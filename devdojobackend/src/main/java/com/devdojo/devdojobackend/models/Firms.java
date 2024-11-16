package com.devdojo.devdojobackend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "firms")
public class Firms {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    Long cid;
    String companyname;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public Long getCid() {
        return this.cid;
    }

    public void setCompanyName(String companyname) {
        this.companyname = companyname;
    }

    public String getCompanyName() {
        return this.companyname;
    }

    Firms() {

    }

    Firms(Long id, Long cid, String companyname) {
        this.id = id;
        this.cid = cid;
        this.companyname = companyname;
    }
}
