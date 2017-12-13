package com.shatskiy.repository.service.sort;

import com.shatskiy.repository.model.FileModelPOJO;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public class SortByName implements Comparator<FileModelPOJO> {

    @Override
    public int compare(FileModelPOJO o1, FileModelPOJO o2) {

        String str1 = o1.getFileName();
        String str2 = o2.getFileName();

        return str1.compareTo(str2);
    }

}
