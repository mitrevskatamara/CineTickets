package com.cinetickets.service.impl;

import com.cinetickets.model.Membership;
import com.cinetickets.model.User;
import com.cinetickets.model.dto.MembershipDto;
import com.cinetickets.repository.MembershipRepository;
import com.cinetickets.repository.UserRepository;
import com.cinetickets.service.MembershipService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MembershipServiceImpl implements MembershipService {

    private final MembershipRepository membershipRepository;
    private final UserRepository userRepository;

    @Override
    public Membership findById(Long id) {
        return this.membershipRepository.findById(id).orElse(null);
    }

    @Override
    public List<Membership> lisAll() {
        return this.membershipRepository.findAll();
    }

    @Override
    public Membership create(MembershipDto membershipDto) {
        List<User> users = userRepository.findAllById(membershipDto.getUsersId());
        Membership membership = new Membership(membershipDto.getDescription(), membershipDto.getImage(),
                membershipDto.getImage(), membershipDto.getPrice(), users);

        return this.membershipRepository.save(membership);
    }

    @Override
    public Membership update(Long id, MembershipDto membershipDto) {
        Membership membership = this.findById(id);
        List<User> users = userRepository.findAllById(membershipDto.getUsersId());

        membership.setDescription(membershipDto.getDescription());
        membership.setImage(membershipDto.getImage());
        membership.setName(membershipDto.getName());
        membership.setUsers(users);
        membership.setPrice(membershipDto.getPrice());

        return this.membershipRepository.save(membership);
    }

    @Override
    public void delete(Long id) {
        Membership membership = this.findById(id);
        this.membershipRepository.delete(membership);
    }
}