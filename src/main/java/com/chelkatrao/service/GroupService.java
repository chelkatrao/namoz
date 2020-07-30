package com.chelkatrao.service;

import com.chelkatrao.model.Groups;
import com.chelkatrao.repository.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public void saveGroup(Long chatId) {
        Groups group = new Groups();
        group.setChatId(chatId);
        groupRepository.save(group);
    }

    public boolean findGroupByChatId(Long chatId) {
        if (groupRepository.findGroupByChatId(chatId) != null) {
            return false;
        } else {
            return true;
        }
    }

    public List<Groups> findAllGroups() {
        return groupRepository.findAll();
    }
}
