package com.example.stickerstore.controller;

import com.example.stickerstore.model.dto.request.ContactRequest;
import com.example.stickerstore.model.dto.response.ApiResponse;
import com.example.stickerstore.model.dto.response.ContactResponse;
import com.example.stickerstore.service.ContactService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/contacts")
@RequiredArgsConstructor
@Tag(name="Contact API")
public class ContactController {
    private final ContactService contactService;

    @Operation(summary = "create contact")
    @PostMapping()
    public ResponseEntity<ApiResponse> createContact(@RequestBody @Valid ContactRequest contactRequest) {
        ApiResponse response = ApiResponse.succeed(contactService.createContact(contactRequest));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "get all contacts")
    @GetMapping()
    public ResponseEntity<ApiResponse> findAllContacts() {
        ApiResponse response = ApiResponse.succeed(contactService.findAll());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
