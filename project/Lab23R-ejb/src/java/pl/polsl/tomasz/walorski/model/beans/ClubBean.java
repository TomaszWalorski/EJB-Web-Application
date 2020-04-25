package pl.polsl.tomasz.walorski.model.beans;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pl.polsl.tomasz.walorski.model.Club;
/**
 * Class is used for performing create/read/update/delete operations on data base for table of clubs.
 * @author Tomasz Walorski
 * @version 1.1
 */
@Stateless
@LocalBean
public class ClubBean {
    /**
     * EntityManager's field commit necessary methods to perform CRUD operations.
     */
    @PersistenceContext
    private EntityManager entityManager;
    /**
     * Method perform operation of saving new club's or footballer's row in database.
     * @param club Object sent to database.
     */
    public void create(Club club){
        entityManager.persist(club);
    }
    /**
     * Method search club object by its id.
     * @param id Primaty key of object.
     * @return Method return proper club's object, according to its id.
     */
    public Club read(int id){
        return entityManager.find(Club.class, id);
    }
    /**
     * Method find all club's object.
     * @return Method return list of all club's objects.
     */
    public List<Club> readAll(){
        return entityManager.createNamedQuery("Club.findAll").getResultList();
    }
    /**
     * Method update existing object.
     * @param club Existing object entered to update.
     */
    public void update(Club club){
        entityManager.merge(club);
    }
    /**
     * Method delete object from database.
     * @param club Chosen object to remove.
     */
    public void delete(Club club){
        if(!entityManager.contains(club)){
            club = entityManager.merge(club);
        }
        entityManager.remove(club);
    }
}
