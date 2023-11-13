package com.express.addressapp.entity;

import lombok.*;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table(name = "m_address")
public class Address {

    @Id
    @GenericGenerator(strategy = "uuid2", name="system-uuid")
    @GeneratedValue(generator = "system-uuid")
    private String id;

    @Column(name = "lane_1")
    private String lane1;

    @Column(name = "lane_2")
    private String lane2;

    @Column(name = "state")
    private String state;

    @Column(name = "zip", unique = true)
    private String zip;

    @Column(name = "employee_id")
    private String employeeId;
}
