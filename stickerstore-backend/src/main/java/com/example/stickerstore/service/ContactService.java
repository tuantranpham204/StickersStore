package com.example.stickerstore.service;

import com.example.stickerstore.model.dto.request.ContactRequest;
import com.example.stickerstore.model.dto.response.ContactResponse;

import java.util.List;

public interface ContactService {
    public List<ContactResponse> findAll();

    ContactResponse createContact(ContactRequest contactRequest);
}
