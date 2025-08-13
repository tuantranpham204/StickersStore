package com.example.stickerstore.model.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder()
public class ContactRequest {
    private String contactName;

    private String contactEmail;

    private String contactPhone;

    private String contactMessage;
}
