package ru.photorex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.photorex.dao.PartDao;
import ru.photorex.entity.Part;

import java.util.List;

@Service
@Transactional
public class PartServiceImpl implements PartService {

    @Autowired
    private PartDao partDao;

    @Override

    public List<Part> getListOfPart() {
        return partDao.getListOfPart();
    }

    @Override
    public void addPart(Part part) {
        partDao.addPart(part);
    }

    @Override
    public void deletePart(int partId) {
        partDao.deletePart(partId);
    }

    @Override
    public Part getPartFromDataBase(int partId) {
        return partDao.getPartFromDataBase(partId);
    }

    @Override
    public List<Part> getListOfSearchPart(String searchName) {
        return partDao.getListOfSearchPart(searchName);
    }

    @Override
    public List<Part> getListOfPartPerUse(String filter) {
        return partDao.getListOfPartPerUse(filter);
    }

    @Override
    public int getCountUseComputers() {
        return partDao.getCountUseComputers();
    }

    @Override
    public long getPartCount() {
        return partDao.getCustomersCount();
    }

    @Override
    public List<Part> getPartsByPage(int pageNumber) {
        return partDao.getPartsByPage(pageNumber);
    }
}
