package com.taskflow.service2.dto;

import lombok.Data;

import java.util.List;

@Data
public class TeamDTO {

    Integer teamId;
    List<Integer> teamMembersIds;
    public TeamDTO(
            Integer teamId,
            List<Integer> teamMembersIds
    ){
        this.teamId = teamId;
        this.teamMembersIds = teamMembersIds;
    }
}
