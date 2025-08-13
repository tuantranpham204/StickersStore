package com.example.stickerstore.service.implementations;

import com.example.stickerstore.model.dto.request.ContactRequest;
import com.example.stickerstore.model.dto.response.ContactResponse;
import com.example.stickerstore.model.entity.Contact;
import com.example.stickerstore.repository.ContactRepository;
import com.example.stickerstore.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;

    private final ModelMapper modelMapper;

    @Override
    public List<ContactResponse> findAll() {
        return contactRepository.findAll().stream().map(product -> modelMapper.map(product, ContactResponse.class)).collect(Collectors.toList());
    }

    @Override
    public ContactResponse createContact(ContactRequest contactRequest) {
        Contact contact =  modelMapper.map(contactRequest, Contact.class);
        contact.setCreatedDate(LocalDateTime.now());
        contact.setUpdatedDate(LocalDateTime.now());
        return modelMapper.map(contactRepository.save(contact), ContactResponse.class);
    }
}
