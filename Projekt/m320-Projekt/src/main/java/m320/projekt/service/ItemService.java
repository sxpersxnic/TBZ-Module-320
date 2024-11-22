package m320.projekt.service;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import m320.projekt.lib.exceptions.FailedValidationException;
import m320.projekt.model.Item;
import m320.projekt.repository.ItemRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public Item findById(Integer id) {
        return itemRepository.findById(id).orElseThrow(EntityExistsException::new);
    }

    @Transactional
    public Item findByIdForUpdate(Integer id) {
        return itemRepository.findByIdForUpdate(id).orElseThrow(EntityNotFoundException::new);
    }

    public Item findByAuthorId(Integer id) {
        return itemRepository.findByAuthorId(id).orElseThrow(EntityNotFoundException::new);
    }

    public void deleteById(Integer id) {
        itemRepository.deleteById(id);
    }

    @Transactional
    public Item update(Item changing, Integer id) {
        Item existing = findByIdForUpdate(id);
        mergeItems(existing, changing);
        return itemRepository.save(existing);
    }

    public Item create(Item item) {
        return itemRepository.save(item);
    }

    private void mergeItems(Item existing, Item changing) {
        Map<String, List<String>> errors = new HashMap<>();

        if (changing.getTitle() != null) {
            if (StringUtils.isNotBlank(changing.getTitle())) {
                existing.setTitle(changing.getTitle());
            } else {
                errors.put("title", List.of("Title can not be empty!"));
            }
        }

        if (!errors.isEmpty()) {
            throw new FailedValidationException(errors);
        }
    }
}
