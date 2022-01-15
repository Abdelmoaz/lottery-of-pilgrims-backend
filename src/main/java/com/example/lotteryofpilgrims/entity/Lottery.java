package com.example.lotteryofpilgrims.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Collection;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "lottery")
public class Lottery {

    @Id
    @GeneratedValue(generator = "sqlite")
    @TableGenerator(name = "sqlite", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "lottery")
    int id;

    int archived;

    @JsonIgnore
    @OneToMany(cascade = ALL, fetch = EAGER, mappedBy = "lottery")
    private Collection<Candidate> candidates = new ArrayList<>();
}
