package com.example.crud_second_tour.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.xml.transform.sax.SAXResult;

@Entity
@Getter
@Setter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String homePhone;
    private String mobilePhone;
    private String workPhone;
    private String homeAddress;
    private String webPage;
    private String bankDetails;
    private String position;
    @ManyToOne()
    private Organization organization;
    @OneToOne(mappedBy = "employee")
    private User user;

}
