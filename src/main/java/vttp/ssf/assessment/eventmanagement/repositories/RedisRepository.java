package vttp.ssf.assessment.eventmanagement.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import vttp.ssf.assessment.eventmanagement.models.Event;

@Repository
public class RedisRepository {

	@Autowired @Qualifier("myredis")
	RedisTemplate<String,String> template;

	// TODO: Task 2
	public void saveRecord(Event event){
		// HashOperations<String, String, String> hOps = template.opsForHash();
		// hOps.put(event.getEventId().toString(), "eventName", event.getEventName());
		// hOps.put(event.getEventId().toString(), "eventSize", event.getEventSize().toString());
		// hOps.put(event.getEventId().toString(), "eventName", event.getEventDate().toString());
		// hOps.put(event.getEventId().toString(), "participants", event.getParticipants().toString());

		ListOperations<String,String> listOps = template.opsForList();
		String value = "%d, %s, %d, %d, %d".formatted(event.getEventId(),event.getEventName(),event.getEventSize(), event.getEventDate(), event.getParticipants());
		listOps.rightPush("events", value);
		
		// listOps.rightPush(event.getEventId().toString(), event.getEventName());
		// listOps.rightPush(event.getEventId().toString(), event.getEventSize().toString());
		// listOps.rightPush(event.getEventId().toString(), event.getEventDate().toString());
		// listOps.rightPush(event.getEventId().toString(), event.getParticipants().toString());

	}

	// TODO: Task 3
	public Long getNumberOfEvents(){
		ListOperations<String,String> listOps = template.opsForList();
		Long numberOfEvents = listOps.size("events");

		return numberOfEvents;
	}

	// // TODO: Task 4
	public Event getEvent(Integer index){
		ListOperations<String,String> listOps = template.opsForList();
		String event = listOps.index("events", index);
		String [] eventAttributes = event.split(", ");
		int eventId = Integer.parseInt(eventAttributes[0]);
		String eventName = eventAttributes[1];
		int eventSize = Integer.parseInt(eventAttributes[2]);
		Long eventDate = Long.parseLong(eventAttributes[3]);
		int participants = Integer.parseInt(eventAttributes[4]);

		return new Event(eventId, eventName, eventSize, eventDate, participants);
	}

}
