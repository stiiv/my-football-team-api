package com.stiiv.myfootballteam.api.controllers;

import com.stiiv.myfootballteam.api.models.TeamMember;
import com.stiiv.myfootballteam.api.repositories.TeamMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/team")
public class TeamMembersController {

    @Autowired
    private TeamMemberRepository teamMemberRepository;

    @Value("${my.place_and_time}")
    private String placeAndTime;

    @GetMapping("/members")
    public Iterable<TeamMember> getMembers() {
        return teamMemberRepository.findAll();
    }

    @GetMapping("/place-and-time")
    public String getPlaceAndTime() {
        return placeAndTime;
    }

    @PostMapping("/members")
    public Optional<TeamMember> createMember(@RequestBody @Valid TeamMember member) {
        teamMemberRepository.save(member);
        return teamMemberRepository.findById(member.getId());
    }

    @PutMapping("/members")
    public Optional<TeamMember> updateMember(@RequestBody @Valid TeamMember member) {
        TeamMember m = teamMemberRepository.findById(member.getId()).orElseThrow(EntityNotFoundException::new);
        m.setName(member.getName());
        teamMemberRepository.save(m);
        return teamMemberRepository.findById(m.getId());
    }

    @DeleteMapping
    public TeamMember deleteMember(@RequestBody @Valid TeamMember member) {
        teamMemberRepository.findById(member.getId()).orElseThrow(EntityNotFoundException::new);
        teamMemberRepository.delete(member);
        return member;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
