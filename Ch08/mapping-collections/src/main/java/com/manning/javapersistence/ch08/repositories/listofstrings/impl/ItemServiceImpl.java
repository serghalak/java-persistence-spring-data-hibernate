package com.manning.javapersistence.ch08.repositories.listofstrings.impl;


import com.manning.javapersistence.ch08.listofstrings.Item;
import com.manning.javapersistence.ch08.repositories.listofstrings.ItemRepository;
import com.manning.javapersistence.ch08.repositories.listofstrings.ItemService;

import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    // Конструктор інжектує репозиторій
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    @Transactional
    public void removeImageFromItem( String imageName, Long itemId) {
        Item item = itemRepository.findItemWithImages(itemId); //.orElseThrow(() -> new EntityNotFoundException("Item not found with id: " + itemId));

        List<String> mutableImages = new ArrayList<>(item.getImages());
        mutableImages.remove(imageName);
        item.setImages(mutableImages);
        itemRepository.save(item);
        // Видаляємо назву картинки зі списку, Hibernate сам оновить БД
        //item.getImages();
    }
}
