package com.example.MoneyTransfer.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Data
@Table(name="customers")
@Entity
public class Customer {
    @ApiModelProperty(notes = "Уникальный идентификатор записи (id)", required = true)
    @Id
    @GeneratedValue(generator = "customer_generator")
    @SequenceGenerator(name = "customer_generator", sequenceName = "customer_sequence", initialValue = 1000)
    private Long id;
    @ApiModelProperty(notes = "Логин пользователя")
    @Column(name = "USERNAME")
    private String username;
    @ApiModelProperty(notes = "ИИН пользователя", required = true)
    @Column(name = "IIN")
    private String iin;
    @Column(name = "FIRST_NAME")
    @ApiModelProperty(notes = "Имя пользователя", required = true)
    private String firstName;
    @ApiModelProperty(notes = "Фамилия пользователя", required = true)
    @Column(name = "LAST_NAME")
    private String lastName;
}
