package vttp.ssf.assessment.eventmanagement.services;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;
import vttp.ssf.assessment.eventmanagement.models.Event;
import vttp.ssf.assessment.eventmanagement.repositories.RedisRepository;

@Service
public class DatabaseService {

    @Autowired
    RedisRepository redisRepo;
    
    // TODO: Task 1
    List<Event> eventList = new ArrayList<>();


    public List<Event> readFile(String fileName) throws FileNotFoundException{

        
        try(JsonReader reader = Json.createReader(new FileReader(fileName))){
            JsonArray eventsArray = reader.readArray();

            for(int i = 0; i < eventsArray.size(); i++){
                int eventId = eventsArray.get(i).asJsonObject().getInt("eventId");
                String eventName = eventsArray.get(i).asJsonObject().getString("eventName");
                int eventSize = eventsArray.get(i).asJsonObject().getInt("eventSize");
                Long eventDate = eventsArray.get(i).asJsonObject().getJsonNumber("eventDate").longValue();
                int participants = eventsArray.get(i).asJsonObject().getInt("participants");

            // for(JsonValue value : eventsArray){
            //     int test = value.asJsonObject().getInt("eventDate");
            //     System.out.println("Date:" + test);
            //     System.out.println(value);
            // }

                // System.out.println(eventId);
                // System.out.println(eventName);
                // System.out.println(eventSize);
                // System.out.println(eventDate);
                // System.out.println(participants);
                eventList.add(new Event(eventId, eventName, eventSize, eventDate, participants));
            }

        } catch (Exception e){
            e.printStackTrace();
        }

        return eventList;
    }

    public void saveRecord(Event event){
        redisRepo.saveRecord(event);
    }

    public List<Event> getList(){
        return eventList;
    }

    public Event getEvent(Integer index){
        Event event = redisRepo.getEvent(index);

        return event;
    }
}
