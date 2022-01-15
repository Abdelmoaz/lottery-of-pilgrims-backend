package com.example.lotteryofpilgrims.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "candidate")
public class Candidate {

    @Id
    int id;
    String name;
    int age;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "lottery", nullable = false)
    Lottery lottery;
}
