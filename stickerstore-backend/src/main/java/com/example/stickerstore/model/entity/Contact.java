package com.example.stickerstore.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "contacts")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder()
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int contactId;

    private String contactName;

    private String contactEmail;

    private String contactPhone;

    private String contactMessage;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

}
