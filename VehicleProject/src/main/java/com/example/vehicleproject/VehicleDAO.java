package com.example.vehicleproject;

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
public class VehicleDAO {

    //PersistenceContext annotation used to specify there is a database source.
    //EntityManager is used to create and remove persistent entity instances,
    //to find entities by their primary key, and to query over entities.
    @PersistenceContext
    private EntityManager entityManager;

    //Insert greeting into the database.
    public void create(Vehicle vehicle) {
        entityManager.persist(vehicle);
        return;
    }

    //Return the greeting with the passed-in id.
    public Vehicle getById(int id) {

        return entityManager.find(Vehicle.class, id);
    }

    /**
     * Delete the user from the database.
     */

    public Vehicle deleteVehicle(int id) {
        Vehicle vehicle = entityManager.find(Vehicle.class, id);
        if (vehicle != null){
            entityManager.remove(vehicle);
        }
        return vehicle;
    }


    /**
     */

    @SuppressWarnings("unchecked")
    public List<Vehicle> getAllVehicles() {
        List<Vehicle> allVehicles = new ArrayList<Vehicle>();
        String sql = "SELECT * FROM vehicles";
        Query query = entityManager.createNativeQuery(sql, Vehicle.class);
        allVehicles = query.getResultList();
        return allVehicles;
        //return entityManager.createQuery("from User").getResultList();

    }



    /**
     * Return the Vehicle having the passed makeModel.
     */
    /*
    public Vehicle getByMakeModel(String makeModel) {
        return (Vehicle) entityManager.createQuery(
                "from User where makeModel = :makeModel")
                .setParameter("makeModel", makeModel)
                .getSingleResult();
    }
    */

    /**
     * Update the passed Vehicle in the database.
     */

    public Vehicle updateVehicle(Vehicle vehicle) {
        if (entityManager.find(Vehicle.class, vehicle.getId()) != null) {
            entityManager.merge(vehicle);
        }
        return vehicle;
    }


    public List<Vehicle> getLatestVehicles() {
        List<Vehicle> latestVehicles = new ArrayList<Vehicle>();
        String sql = "SELECT * FROM vehicles ORDER BY year DESC LIMIT 10";
        Query query = entityManager.createNativeQuery(sql, Vehicle.class);
        latestVehicles = query.getResultList();
        return latestVehicles;
    }

    //Gets the cheapest vehicles
    public List<Vehicle> getCheapVehicles()
    {
        List<Vehicle> cheapVehicles = new ArrayList<>();
        String sql = "SELECT * FROM vehicles ORDER BY retailPrice LIMIT 10";
        Query query = entityManager.createNativeQuery(sql, Vehicle.class);
        cheapVehicles = query.getResultList();
        return cheapVehicles;
    }

    public List<Vehicle> getNewestExpensiveVehicle(){
        List<Vehicle> newExpVehicle = new ArrayList<>();
        String sql = "SELECT * FROM vehicles WHERE year > 2014 ORDER BY retailPrice DESC LIMIT 1";
        Query query = entityManager.createNativeQuery(sql, Vehicle.class);
        newExpVehicle = query.getResultList();
        return newExpVehicle;
    }



} // class UserDao

