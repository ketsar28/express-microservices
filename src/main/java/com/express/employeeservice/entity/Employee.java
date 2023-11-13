package com.express.employeeservice.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table(name = "m_employee")
public class Employee {

   @Id
   @GenericGenerator(strategy = "uuid2", name="system-uuid")
   @GeneratedValue(generator = "system-uuid")
   private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "blood_group")
    private String bloodGroup;
}
