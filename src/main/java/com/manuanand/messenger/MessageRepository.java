package com.manuanand.messenger;

import org.springframework.data.repository.CrudRepository;

import com.manuanand.messenger.Message;

public interface MessageRepository extends CrudRepository<Message, Integer>{
    
	boolean existsById(Integer var1);
    
    Iterable<Message> findByFromUserId(Integer userId);
    
    Iterable<Message> findByToUserId(Integer userId);
}
