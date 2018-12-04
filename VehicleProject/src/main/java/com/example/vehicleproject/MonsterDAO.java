package com.example.vehicleproject;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object - provide some specific data operations without exposing details of the database
 * Access data for the Greeting entity.
 * Repository annotation allows Spring to find and configure the DAO.
 * Transactional annonation will cause Spring to call begin() and commit()
 * at the start/end of the method. If exception occurs it will also call rollback().
 */
@Repository
@Transactional
public class MonsterDAO {

    //PersistenceContext annotation used to specify there is a database source.
    //EntityManager is used to create and remove persistent entity instances,
    //to find entities by their primary key, and to query over entities.
    @PersistenceContext
    private EntityManager entityManager;

    //Insert greeting into the database.
    public void create(Monster monster) {
        entityManager.persist(monster);
        return;
    }

    //Return the greeting with the passed-in id.
    public Monster getById(int id) {

        return entityManager.find(Monster.class, id);
    }

    /**
     * Delete the user from the database.
     */

    public Monster deleteMonster(int id) {
        Monster monster = entityManager.find(Monster.class, id);
        if (monster != null){
            entityManager.remove(monster);
        }
        return monster;
    }


    /**
     */

    @SuppressWarnings("unchecked")
    public List<Monster> getAllMonsters() {
        List<Monster> allMonsters = new ArrayList<Monster>();
        String sql = "SELECT * FROM monsters ORDER BY name";
        Query query = entityManager.createNativeQuery(sql, Monster.class);
        allMonsters = query.getResultList();
        return allMonsters;
        //return entityManager.createQuery("from User").getResultList();

    }

    public int getMonstersSize() {
        int num;
        String sql = "SELECT COUNT(name) FROM monsters";
        Query query = entityManager.createNativeQuery(sql, Monster.class);
        num = query.getFirstResult();
        return num;
    }



    public Monster updateMonster(Monster monster) {
        if (entityManager.find(Monster.class, monster.getId()) != null) {
            entityManager.merge(monster);
        }
        return monster;
    }



} // class UserDao

