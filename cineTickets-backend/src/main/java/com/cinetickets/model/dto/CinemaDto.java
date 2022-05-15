package com.cinetickets.model.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CinemaDto {
    private long id;

    private String address;

    private MultipartFile image;

    private String name;

    private String city;

    private String phoneNumber;
}