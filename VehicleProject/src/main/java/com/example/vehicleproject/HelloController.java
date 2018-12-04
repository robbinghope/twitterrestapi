package com.example.vehicleproject;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

@RestController
public class HelloController {

    private int count = 0;


    //End lecture 4

    //Serialize

    //*** Project 1 Start ***
    //addVehicle
    /*@RequestMapping(value="/addVehicle", method = RequestMethod.POST)
    public Vehicle addVehicle(@RequestBody Vehicle newVehicle) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        FileWriter output = new FileWriter("./inventory.txt", true);

        mapper.writeValue(output, newVehicle);

        FileUtils.writeStringToFile(new File("./inventory.txt"),
                System.lineSeparator(),
                Charset.defaultCharset(),
                true);
        return newVehicle;
    }*/

    //getVehicle
    /*@RequestMapping(value="/getVehicle/{id}", method=RequestMethod.GET)
    public Vehicle getVehicle(@PathVariable("id") int id) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        System.out.println("Called Get Vehicle");
        LineIterator inventory = FileUtils.lineIterator(new File("./inventory.txt"));

        String newVehicle;

        while (inventory.hasNext()) {
            String tVehicle = inventory.next();
            Vehicle v = mapper.readValue(tVehicle, Vehicle.class);

            if (v.getId() == id) {
                newVehicle = tVehicle;
                return mapper.readValue(newVehicle, Vehicle.class);

            }

        }
        return null;
    }*/

    //updateVehicle
    /*@RequestMapping(value="/updateVehicle", method=RequestMethod.PUT)
    public Vehicle updateVehicle(@RequestBody Vehicle newVehicle) throws IOException {
        //check for null
        ObjectMapper mapper = new ObjectMapper();
        FileWriter output = new FileWriter("./inventory.txt", true);
        LineIterator inventory = FileUtils.lineIterator(new File("./inventory.txt"));

        Vehicle v;
        while (inventory.hasNext()){
            String tVehicle = inventory.next();
            v = mapper.readValue(tVehicle, Vehicle.class);
            if (v.getId() == newVehicle.getId()) {
                mapper.writeValue(output, newVehicle);
                FileUtils.writeStringToFile(new File("./inventory.txt"),
                        System.lineSeparator(),
                        Charset.defaultCharset(),
                        true);

                return v;
            }
        }
        return null;
    }*/

    //deleteVehicle
    /*@RequestMapping(value="/deleteVehicle/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<String> deleteVehicle(@PathVariable("id") int id) throws IOException {
        //Check for null

        System.out.println("Called Delete Vehicle");
        ObjectMapper mapper = new ObjectMapper();
        FileWriter output = new FileWriter("./inventory.txt", true);
        LineIterator inventory = FileUtils.lineIterator(new File("./inventory.txt"));

        String vehicles = "";
        Vehicle v;
        while (inventory.hasNext()){
            String tVehicle = inventory.next();
            v = mapper.readValue(tVehicle, Vehicle.class);
            if (!(v.getId() == id)) {
                vehicles += tVehicle + System.lineSeparator();
                System.out.println("vehicles is " + vehicles);
                System.out.println("tVehicle is " + tVehicle);
            }
            else
            {
                System.out.println("Vehicle found and deleted.");
            }
        }
        mapper.writeValue(output, vehicles);
        FileUtils.writeStringToFile(new File("./inventory.txt"),
                System.lineSeparator(),
                Charset.defaultCharset(),
                true);
        return null;
    }*/

    //*** Project 2 Start ***
    @Autowired
    private VehicleDAO vehicleDAO;

    //addVehicle
    @RequestMapping(value = "/addVehicle", method = RequestMethod.POST)
    public Vehicle addVehicle(@RequestBody Vehicle newVehicle) throws IOException {
        vehicleDAO.create(newVehicle);
        return newVehicle;
    }

    //getVehicle
    @RequestMapping(value = "/getVehicle/{id}", method = RequestMethod.GET)
    public Vehicle getVehicle(@PathVariable("id") int id) throws IOException {
        return vehicleDAO.getById(id);
    }

    //updateVehicle
    @RequestMapping(value = "/updateVehicle", method = RequestMethod.PUT)
    public Vehicle updateVehicle(@RequestBody Vehicle newVehicle) throws IOException {
        vehicleDAO.updateVehicle(newVehicle);
        return newVehicle;
    }

    //get Latest Vehicles
    @RequestMapping(value = "/getLatestVehicles", method = RequestMethod.GET)
    public List<Vehicle> getLatestVehicles() throws IOException {
        return vehicleDAO.getLatestVehicles();
    }

    //deleteVehicle
    @RequestMapping(value = "/deleteVehicle/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteVehicle(@PathVariable("id") int id) throws IOException {
        vehicleDAO.deleteVehicle(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Project 3 Start
    //Get cheap vehicles
    @RequestMapping(value = "/getCheapVehicles", method = RequestMethod.GET)
    public List<Vehicle> getCheapVehicles() throws IOException{
        return vehicleDAO.getCheapVehicles();
    }

    //Get cheap vehicles
    @RequestMapping(value = "/getNewestExpensiveVehicle", method = RequestMethod.GET)
    public List<Vehicle> getNewestExpensiveVehicle() throws IOException{
        return vehicleDAO.getNewestExpensiveVehicle();
    }

    //Get all vehicles
    @RequestMapping(value = "/getAllVehicles", method = RequestMethod.GET)
    public List<Vehicle> getAllVehicles() throws IOException{
        return vehicleDAO.getAllVehicles();
    }

    @Autowired
    private MonsterDAO monsterDAO;

    //Get all monsters
    @RequestMapping(value = "/getAllMonsters", method = RequestMethod.GET)
    public List<Monster> getAllMonsters() throws IOException{
        return monsterDAO.getAllMonsters();
    }

    //getMonster
    @RequestMapping(value = "/getMonster/{id}", method = RequestMethod.GET)
    public Monster getMonster(@PathVariable("id") int id) throws IOException {
        return monsterDAO.getById(id);
    }

    //getMonster
    @RequestMapping(value = "/getMonstersSize", method = RequestMethod.GET)
    public int getMosntersSize() throws IOException {
        return monsterDAO.getMonstersSize();
    }
}