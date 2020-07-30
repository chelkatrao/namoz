package com.chelkatrao.repository;

import com.chelkatrao.model.Groups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Groups, Long> {
    Groups findGroupByChatId(Long chatId);
}
