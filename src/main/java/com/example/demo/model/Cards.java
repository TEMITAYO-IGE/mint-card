package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Osagie Erhabor on 24/02/2020.
 */
@Data
@Entity
public class Cards{

    public Cards(@NotNull Long cardId, @NotNull String scheme, @NotNull String type, String bank) {
        this.cardId = cardId;
        this.scheme = scheme;
        this.type = type;
        this.bank = bank;
    }
    public Cards(){

    }

    @GeneratedValue(strategy= GenerationType.AUTO)
    @Id
    @NotNull
    @JsonIgnore
    private int id;

    @NotNull
    @Column(length = 40, unique = true)
    @JsonIgnore
    private Long cardId;

    @NotNull
    @Column(length = 40)
    private String scheme;

    @NotNull
    @Column(length = 40)
    private String type;

    @Column
    private String bank;
}
