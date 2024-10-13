package com.banquemisr.moneytransactionservice.model;

import com.banquemisr.moneytransactionservice.dto.TransactionDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long transactionId;

    @Column(nullable = false)
    private double amount;

    @ManyToOne
    private Account fromAccount;

    @ManyToOne
    private Account toAccount;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public TransactionDTO toDTO() {
        return TransactionDTO
                .builder()
                .fromAccountNumber(this.fromAccount.getAccountNumber())
                .toAccountNumber(this.toAccount.getAccountNumber())
                .amount(this.amount)
                .build();
    }
}