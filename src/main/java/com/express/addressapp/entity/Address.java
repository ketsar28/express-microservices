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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "lane_1")
    private String lane1;

    @Column(name = "lane_2")
    private String lane2;

    @Column(name = "state")
    private String state;

    @Column(name = "zip", unique = true)
    private String zip;
}
