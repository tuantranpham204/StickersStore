package com.example.stickerstore.model.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder()
public class ContactResponse {
    private String contactId;

    private String contactName;

    private String contactEmail;

    private String contactPhone;

    private String contactMessage;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;
}
