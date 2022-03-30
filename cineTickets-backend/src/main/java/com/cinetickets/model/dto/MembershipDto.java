package com.cinetickets.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class MembershipDto {

    private String description;

    private String image;

    private String name;

    private int price;

    private List<Long> usersId;
}