package com.example.vehicleproject;

import org.apache.commons.io.FileUtils;
import org.springframework.util.SerializationUtils;

import java.io.File;

//import org.testng.annotations.Ignore;
//import org.testng.annotations.Test;

public class SerializationTest {

    //@Test
    public void testSerialize() throws Exception
    {
        Vehicle benz = new Vehicle(1, "E 300", 2018, 50000);
        byte [] data = SerializationUtils.serialize(benz);
        File file = new File("res/benz.ser");
        FileUtils.writeByteArrayToFile(file, data);
    }

    //@Test
    public void testDeserialize() throws Exception
    {
        File file = new File("res/benz.ser");
        byte [] data = FileUtils.readFileToByteArray(file);
        //Vehicle benz = SerializationUtils.deserialize(data);
        //System.out.println(benz.getName() + " " + " ");
    }
}
