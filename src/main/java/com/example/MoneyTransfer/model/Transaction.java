package com.example.MoneyTransfer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Data
@Table(name="transactions")
@Entity
public class Transaction {
    @ApiModelProperty(notes = "Уникальный идентификатор записи (id)", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_generator")
    @SequenceGenerator(name = "transaction_generator", sequenceName = "transaction_sequence")
    private Long id;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "accountSenderId")
    private Account sender;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "accountReceiverId")
    private Account receiver;
    @ApiModelProperty(notes = "Идентификатор отправителя", required = true)
    @NotNull(message = "senderId must not be null")
    @Column(name = "sender_id")
    private Long senderId;
    @ApiModelProperty(notes = "Идентификатор получателя", required = true)
    @NotNull(message = "receiverId must not be null")
    @Column(name = "receiver_id")
    private Long receiverId;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    @Column(name = "sum")
    private BigDecimal sum;
    @Column(name = "created_at" )
    private LocalDateTime createdAt;
    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;

}
