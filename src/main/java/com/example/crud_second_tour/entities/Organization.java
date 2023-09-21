package com.example.crud_second_tour.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ownershipType;
    private String legalForm;
    private String organizationName;
    private String headName;
    private String fax;
    private String email;
    private String phone;
    private String webPage;
    private String login;
    private String licenseNumber;
    private String licenseAcquisitionDate;
    private String certificateNumber;
    private String certificateAcquisitionDate;
    private String address;
    private String attachedFiles;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Employee> employeeList;
    @OneToOne(mappedBy = "organization")
    private User user;

}
//        Сотрудник (Employee):
//
//        • Почта - email
//        • Домашний телефон - homePhone
//        • Мобильный телефон - mobilePhone
//        • Рабочий телефон - workPhone
//        • Домашний адрес - homeAddress
//        • Веб страница - webPage
//        • Банковские реквизиты - bankDetails
//        • Должность - position
//
//        Эти названия переменных основаны на стандартных соглашениях именования переменных в Java и они подходят для использования в модели данных.






