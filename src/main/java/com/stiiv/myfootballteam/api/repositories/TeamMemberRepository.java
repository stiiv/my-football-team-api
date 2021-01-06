package com.stiiv.myfootballteam.api.repositories;

import com.stiiv.myfootballteam.api.models.TeamMember;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

public interface TeamMemberRepository extends CrudRepository<TeamMember, Integer> {

    public TeamMember findByName(String name);
}
