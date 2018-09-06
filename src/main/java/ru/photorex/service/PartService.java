package ru.photorex.service;

import ru.photorex.entity.Part;

import java.util.List;

public interface PartService {

    List<Part> getListOfPart();
    void addPart(Part part);
    void deletePart(int partId);
    Part getPartFromDataBase(int partId);
    List<Part> getListOfSearchPart(String searchName);
    List<Part> getListOfPartPerUse(String filter);
    int getCountUseComputers();
    long getPartCount();
    List<Part> getPartsByPage(int pageNumber);
}
