package com.chelkatrao;

import com.chelkatrao.model.Groups;
import com.chelkatrao.repository.GroupRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SuccessRest {

    private GroupRepository groupRepository;

    public SuccessRest(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @GetMapping
    public String success() {
        return "success";
    }

    @GetMapping("/groups")
    public List<Groups> getGroups() {
        return groupRepository.findAll();
    }

}
