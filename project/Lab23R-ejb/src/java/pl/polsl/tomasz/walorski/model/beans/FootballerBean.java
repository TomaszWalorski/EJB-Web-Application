package pl.polsl.tomasz.walorski.model.beans;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pl.polsl.tomasz.walorski.model.Footballer;
/**
 * Class is used for performing create/read/update/delete operations on data base for table of footballers.
 * @author Tomasz Walorski
 * @version 1.1
 */
@Stateless
@LocalBean
public class FootballerBean {
    /**
     * EntityManager's field commit necessary methods to perform CRUD operations.
     */
    @PersistenceContext
    private EntityManager entityManager;
    /**
     * Method perform operation of saving new club's or footballer's row in database.
     * @param footballer Object sent to database.
     */
    public void create(Footballer footballer){
        entityManager.persist(footballer);
    }
    /**
     * Method search footballer object by its id.
     * @param id Primaty key of object.
     * @return Method return proper footballer's object, according to its id.
     */
    public Footballer read(int id){
        return entityManager.find(Footballer.class, id);
    }
    /**
     * Method find all footballer's object.
     * @return Method return list of all footballer's objects.
     */
    public List<Footballer> readAll(){
        return entityManager.createNamedQuery("Footballer.findAll").getResultList();
    }
    /**
     * Method update existing object.
     * @param footballer Existing object entered to update.
     */
    public void update(Footballer footballer){
        entityManager.merge(footballer);
    }
    /**
     * Method delete object from database.
     * @param footballer Chosen object to remove.
     */
    public void delete(Footballer footballer){
        if(!entityManager.contains(footballer)){
            footballer = entityManager.merge(footballer);
        }
        entityManager.remove(footballer);
    }
}
