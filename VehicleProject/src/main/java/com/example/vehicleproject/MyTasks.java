package com.example.vehicleproject;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;

@Component
public class MyTasks {

    //Scheduled Tasks
    RestTemplate restTemplate = new RestTemplate();

    @Logged
    @Tallied
    @Timed
    //@Scheduled(cron = "*/10 * * * * *")
    public void addVehicle() {
        String url = "http://localhost:8181/addVehicle";
        Vehicle newVehicle = new Vehicle(RandomStringUtils.randomAlphabetic(10),
                RandomUtils.nextInt(1986, 2018), RandomUtils.nextInt(15000, 45000));
        restTemplate.postForObject(url, newVehicle, Vehicle.class);
        System.out.println("ID: " + newVehicle.getId() + "ToString: " + newVehicle.toString() + "Added to file.");
    }

    @Logged
    @Tallied
    @Timed
    //@Scheduled(cron = "*/5 * * * * *")
    public void deleteVehicle() {
        int randId = RandomUtils.nextInt(1, 20);
        String url = "http://localhost:8181/deleteVehicle/" + randId;
        restTemplate.delete(url);
    }

    @Logged
    @Tallied
    @Timed
    //@Scheduled(cron = "*/15 * * * * *")
    public void updateVehicle() {
        String url = "http://localhost:8080/updateVehicle";
        int randID = RandomUtils.nextInt(1, 20);
        Vehicle newVehicle = new Vehicle(randID, RandomStringUtils.randomAlphabetic(10),
                RandomUtils.nextInt(1986, 2016), RandomUtils.nextInt(15000, 45000));
        restTemplate.put(url, newVehicle);
    }

    @Logged
    @Tallied
    @Timed
    //@Scheduled(cron="*/5 * * * * *")
    public void getLatestVehicles() {
        String url = "http://localhost:8181/getLatestVehicles";
        List<Vehicle> vehicles = restTemplate.getForObject(url, List.class);
        for (int i = vehicles.size() - 1; i >= 0; i--) {
            System.out.println(vehicles.get(i));
        }
    }

    @Logged
    @Tallied
    @Timed
    //@Scheduled(cron="*/10 * * * * *")
    public void getCheapVehicles() {
        String url = "http://localhost:8181/getCheapVehicles";
        List<Vehicle> vehicles = restTemplate.getForObject(url, List.class);
        for (int i = vehicles.size() - 1; i >= 0; i--) {
            System.out.println(vehicles.get(i));
        }
    }

    @Logged
    @Tallied
    @Timed
    //@Scheduled(cron = "*/10 * * * * *")
    public void getNewestExpensiveVehicle() {
        String url = "http://localhost:8181/getNewestExpensiveVehicle";
        List<Vehicle> vehicles = restTemplate.getForObject(url, List.class);
        for (int i = vehicles.size() - 1; i >= 0; i--) {
            System.out.println(vehicles.get(i));
        }
    }


    @Logged
    @Tallied
    @Timed
    //@Scheduled(cron = "*/10 * * * * *")
    public void allVehicles() {
        String url = "http://localhost:8181/getAllVehicles";
        List<Vehicle> vehicles = restTemplate.getForObject(url, List.class);
        for (int i = vehicles.size() - 1; i >= 0; i--) {
            System.out.println(vehicles.get(i));
        }
    }

    @Logged
    @Tallied
    @Timed
    //@Scheduled(cron = "*/5 * * * * *")
    public void allMonsters() {
        String url = "http://localhost:8181/getAllMonsters";
        List<Monster> monsters = restTemplate.getForObject(url, List.class);
        for (int i = monsters.size() - 1; i >= 0; i--) {
            System.out.println(monsters.get(i));
        }
    }

    @Logged
    @Tallied
    @Timed
    //@Scheduled(cron = "*/5 * * * * *")
    public void getMonster() {
        int randId = RandomUtils.nextInt(1, 20);
        //int randId = 5;
        String url = "http://localhost:8181/getMonster/" + randId;
        Monster monster = restTemplate.getForObject(url, Monster.class);
        String name = monster.getName();
        System.out.println(name);
    }

    public String getMonsterName(){
        int randId = RandomUtils.nextInt(1, 20);
        //int randId = 5;
        String url = "http://localhost:8181/getMonster/" + randId;
        Monster monster = restTemplate.getForObject(url, Monster.class);
        String name = monster.getName();
        return name;
    }
    /*@Logged
    @Tallied
    @Timed*/
    //@Scheduled(cron = "*/5 * * * * *")
    /*public void getMonstersSize() {
        int randId = RandomUtils.nextInt(1, 20);
        String url = "http://localhost:8181/getMonstersSize";
        int num = restTemplate.getForEntity(url, Monster.class);
        System.out.println(num);
    }*/

    private static String consumerKeyStr = "gzfCzHJ8iP4vcOqO0LmgWGP5H";
    private static String consumerSecretStr = "ogYUPzrb5S4D70fJ2fpQhFduXk0NqZDvvb2NaNYjJmvSQOlmfZ";
    private static String accessTokenStr = "1045396632030904321-ouUBJ6UgKvqMlc97ndBMYGhk1bWACf";
    private static String accessTokenSecretStr = "9wYkRXkrrdMTfeoyXgDY3sqmYrtzzlPGGv0GnljBcgEO2";

    @Scheduled(cron = "*/10 * * * * *")
    public void postExample() throws Exception {

        OAuthConsumer oAuthConsumer = new CommonsHttpOAuthConsumer(consumerKeyStr, consumerSecretStr);
        oAuthConsumer.setTokenWithSecret(accessTokenStr, accessTokenSecretStr);
        String str = RandomStringUtils.randomAlphabetic(10);
        String dndBeyond = "https://www.dndbeyond.com/monsters/";
        String monster = getMonsterName();
        String monsterLink = dndBeyond + monster;
        String standardLink = "https://api.twitter.com/1.1/statuses/update.json?status=";
        String profile = "https://twitter.com/JoePelletier12";
        String tweet = "https://twitter.com/JoePelletier12/status/1069946249955209216";
        String postLink = standardLink + monster;
        HttpPost httpPost = new HttpPost(postLink);
        System.out.println(postLink);
        oAuthConsumer.sign(httpPost);
        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse httpResponse = httpClient.execute(httpPost);
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        System.out.println(statusCode + ':' + httpResponse.getStatusLine().getReasonPhrase());
        System.out.println(IOUtils.toString(httpResponse.getEntity().getContent()));
    }



}
