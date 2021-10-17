package com.manuanand.messenger;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller	// This means that this class is a Controller
@RequestMapping(path="/message") // This means URL's start with /message (after Application path)
public class MessageController {
	@Autowired 
	private MessageRepository messageRepository;
	
	@GetMapping(path="/")
	public @ResponseBody Iterable<Message> getAllMessages() {
		
		// This returns a JSON or XML with the users
		return messageRepository.findAll();
	}

	@GetMapping(path="/sent/{userId}")
	public @ResponseBody Iterable<Message> getMessagesSentByUser(@PathVariable Integer userId) {
		
		return messageRepository.findByFromUserId(userId);
	}

	@GetMapping(path="/received/{userId}")
	public @ResponseBody Iterable<Message> getMessagesReceivedByUser(@PathVariable Integer userId) {
		
		return messageRepository.findByToUserId(userId);
	}
	
	@GetMapping(path="/{id}")
	public @ResponseBody Message getSpecificMessage(@PathVariable String id) {
		
		Integer messageId = null;
		try {
			messageId = Integer.parseInt(id);
		} catch (NumberFormatException ex) {
			return null;
		}

		Optional<Message> message = messageRepository.findById(messageId);
		if (!message.isEmpty()) {
			return message.get();
		} 

		return null;
	}

	@GetMapping(path="/deleteAll")
	public @ResponseBody void deleteAllMessages() {
		messageRepository.deleteAll();
	}
}