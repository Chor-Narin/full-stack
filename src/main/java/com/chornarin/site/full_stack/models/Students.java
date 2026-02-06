package com.chornarin.site.full_stack.models;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.chornarin.site.full_stack.Enum.StudentStatusEnum;
import com.chornarin.site.full_stack.annotations.EmailNotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "students")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Students {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="firstName", nullable = true, unique = false, columnDefinition = "varchar(255)")
    private String firstName;

    @Column(name="lastName", nullable = true, unique = false, columnDefinition = "varchar(255)")
    private String lastName;

    @Column(name="email", nullable = true, unique = true)
    @EmailNotNull
    private String email;

    @Enumerated(EnumType.ORDINAL)
    public StudentStatusEnum status;


    //===========================================> Many To One
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    // @JsonIgnore()
    private Departments department;


    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

}


