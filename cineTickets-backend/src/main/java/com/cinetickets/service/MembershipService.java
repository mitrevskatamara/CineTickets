package com.cinetickets.service;

import com.cinetickets.model.Membership;
import com.cinetickets.model.dto.MembershipDto;

import java.util.List;

public interface MembershipService {

    Membership findById(Long id);

    List<Membership> lisAll();

    Membership create(MembershipDto membershipDto);

    Membership update(Long id, MembershipDto membershipDto);

    void delete(Long id);
}
