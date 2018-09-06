package ru.photorex.dao;

import ru.photorex.entity.Part;

import java.util.List;

public interface PartDao {

    List<Part> getListOfPart();
    void addPart(Part part);
    void deletePart(int partId);
    Part getPartFromDataBase(int partId);
    List<Part> getListOfSearchPart(String searchName);
    List<Part> getListOfPartPerUse(String filter);
    int getCountUseComputers();
    long getCustomersCount();
    List<Part> getPartsByPage(int pageNumber);

}
