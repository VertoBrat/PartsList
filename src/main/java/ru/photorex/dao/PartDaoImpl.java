package ru.photorex.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import ru.photorex.entity.Part;

import java.util.Comparator;
import java.util.List;

@Repository
public class PartDaoImpl implements PartDao {

    @Autowired
    private SessionFactory factory;

    private int pageSize = 10;

    @Override
    public List<Part> getListOfPart() {
        Session session = factory.getCurrentSession();
        List<Part> list = session.createQuery("from ru.photorex.entity.Part", Part.class).getResultList();
        return list;
    }

    @Override
    public void addPart(Part part) {
        Session session = factory.getCurrentSession();
        session.saveOrUpdate(part);
    }

    @Override
    public void deletePart(int partId) {
        Session session = factory.getCurrentSession();
        Part part = session.get(Part.class, partId);
        session.delete(part);
    }

    @Override
    public Part getPartFromDataBase(int partId) {
        Session session = factory.getCurrentSession();
        Part part = session.get(Part.class, partId);
        return part;
    }

    @Override
    public List<Part> getListOfSearchPart(String searchName) {
        Session session = factory.getCurrentSession();
        Query<Part> query;
        if (searchName != null && searchName.trim().length() > 0) {
            query = session.createQuery("from ru.photorex.entity.Part where lower(computePart) like :name", Part.class);
            query.setParameter("name", "%" + searchName.toLowerCase() + "%");

            System.out.println(query.toString());
        } else {
            query = session.createQuery("from ru.photorex.entity.Part", Part.class);
        }

        List<Part> list = query.getResultList();

        return list;
    }

    @Override
    public List<Part> getListOfPartPerUse(String filter) {
        Session session = factory.getCurrentSession();
        List<Part> list;
        Query<Part> query;
        if (filter.equals("use")) {
            query = session.createQuery("from ru.photorex.entity.Part where isNeeded=:use",Part.class);
            query.setParameter("use", '1');
            list = query.getResultList();
        } else {
            query = session.createQuery("from ru.photorex.entity.Part where isNeeded is null", Part.class);
            list = query.getResultList();
        }
        return list;
    }

    public int getCountUseComputers() {
        List<Part> list = getListOfPartPerUse("use");
        Part part = list.stream().min(Comparator.comparing(Part::getQuantity)).get();
        return part.getQuantity();
    }

    @Override
    public long getCustomersCount() {
        Session session = factory.getCurrentSession();
        Query<Long> query = session.createQuery("select count(*) from ru.photorex.entity.Part", Long.class);
        long count = query.getSingleResult();
        return count;
    }

    @Override
    public List<Part> getPartsByPage(int pageNumber) {
        Session currentSession = factory.getCurrentSession();

        Query<Part> query = currentSession.createQuery("From ru.photorex.entity.Part", Part.class);
        query.setFirstResult((pageNumber-1) * pageSize);
        query.setMaxResults(pageSize);

        List<Part> list = query.getResultList();

        return list;
    }
}
