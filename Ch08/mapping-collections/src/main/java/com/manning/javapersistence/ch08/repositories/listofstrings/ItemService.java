package com.manning.javapersistence.ch08.repositories.listofstrings;

import org.springframework.stereotype.Component;


public interface ItemService {
    void removeImageFromItem( String imageName, Long itemId);
}
