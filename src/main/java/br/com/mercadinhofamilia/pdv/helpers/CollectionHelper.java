package br.com.mercadinhofamilia.pdv.helpers;

import java.util.Collection;

import static java.util.Objects.isNull;

public class CollectionHelper {
    
    private CollectionHelper() {}
    
    public static boolean isEmpty(Collection<?> collection) {
        return isNull(collection) || collection.isEmpty();
    }

    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }
}
