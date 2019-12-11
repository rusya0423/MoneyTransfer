package com.example.MoneyTransfer.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Data
@Table(name="transactions")
@Entity
public class CurrencyRate {
    @ApiModelProperty(notes = "Уникальный идентификатор записи (id)", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "currency_rate_generator")
    @SequenceGenerator(name = "currency_rate_generator", sequenceName = "currency_rate_sequence")
    private Long id;
    @ApiModelProperty(notes = "Валюта счета как ENUM", required = true, allowableValues = "KZT, EUR, USD")
    @Enumerated(EnumType.STRING)
    @Column(name = "currency_from")
    private Currency currencyFrom;
    @ApiModelProperty(notes = "Валюта счета как ENUM", required = true, allowableValues = "KZT, EUR, USD")
    @Column(name = "currency_to")
    @Enumerated(EnumType.STRING)
    private Currency currencyTo;
    @ApiModelProperty(notes = "Курс", required = true)
    @Column(name = "rate")
    private BigDecimal rate;
    @ApiModelProperty(notes = "Время создания", required = true)
    @Column(name = "created_date")
    private LocalDateTime createdDate;
}
