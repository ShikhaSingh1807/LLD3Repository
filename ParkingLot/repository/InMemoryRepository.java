package repository;

import models.BaseModal;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryRepository<T extends BaseModal>
{
    //This class will contain common methods for all models - common repo for all models

    private Map<Long,T> map = new ConcurrentHashMap<>();
    private static AtomicLong idCounter = new AtomicLong(0);

    //Add or create a modal and save in Map repo
    public T save(T item)
    {
        if(item.getId()==null)
        {
            item.setId(idCounter.incrementAndGet());
        }

        if(item.getCreated_at()==null)
        {
            item.setCreated_at(LocalDateTime.now());
        }

        item.setUpdated_at(LocalDateTime.now());

        map.put(item.getId(),item);

        return item;
    }

    //To get a entity based on its id
    public Optional<T> getById(Long id)
    {
        return Optional.ofNullable(map.get(id));
    }

    //To get All the entities of a type
    public List<T> findAll()
    {
        return new ArrayList<>(map.values());
    }

}
