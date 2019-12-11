package com.example.MoneyTransfer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Data
@Table(name="accounts")
@Entity
public class Account {
    @ApiModelProperty(notes = "Уникальный идентификатор счета (id)", required = true)
    @Id
    @GeneratedValue(generator = "account_generator")
    @SequenceGenerator(name = "account_generator", sequenceName = "account_sequence", initialValue = 1000)
    private Long id;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ApiModelProperty(notes = "Номмер счета", required = true)
    @Column(name = "account_number")
    private String account_number;
    @ApiModelProperty(notes = "Сумма счета", required = true)
    @Column(name = "balance")
    private BigDecimal balance;
    @ApiModelProperty(notes = "Заблокированная сумма счета", required = true)
    @Column(name = "blockedBalance")
    private BigDecimal blockedBalance;
    @ApiModelProperty(notes = "Валюта счета как ENUM", required = true, allowableValues = "KZT, EUR, USD")
    @Enumerated(EnumType.STRING)
    private Currency currency;



}
