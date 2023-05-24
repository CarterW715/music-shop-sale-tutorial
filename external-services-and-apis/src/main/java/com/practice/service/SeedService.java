package com.practice.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.graalvm.collections.Pair;

import java.util.Map;
import java.util.Random;

@ApplicationScoped
public class SeedService {

    Random rand = new Random();

    String[] firstNames = {
            "Carter",
            "John",
            "Ben",
            "Sam",
            "Aaron",
            "Grady",
            "Arman",
            "Abi",
            "Aileen",
            "Liz"
    };

    String[] lastNames = {
            "White",
            "Zwissler",
            "Beane",
            "Haskell",
            "Landrey",
            "Allen",
            "Reihani",
            "Yates",
            "Driscoll",
            "Wuest"
    };

    String[] shopNames = {
            "Music Shop of Texas",
            "Austin Instruments",
            "Dallas Emporium for Musicians",
            "El Paso House of Play",
            "Guitar Center"
    };

    Map<String, String> instrumentToCodeMap =
            Map.of(
                    "Fender Stratocaster", "fstrat2023",
                    "Fender Telecaster", "ftele1960",
                    "Martin D-10E", "MD10E2020",
                    "Gibson Les Paul", "glp1972",
                    "Bellafina Sonata", "bellas2000",
                    "Yamaha P71 88-Key", "yap7188k2023"
            );

    public int getRandomNumInRange(int min, int max) {
        return rand.nextInt((max - min) + 1) + min;
    }

    public double getRandomDouble(int max) {
        return getRandomDouble(0, max);
    }

    public double getRandomDouble(int min, int max) {
        var num = getRandomNumInRange(min, max);
        var decimal  = (double) rand.nextInt(100) / 100;
        return num + decimal;
    }

    public String getRandomShopName() {
        return shopNames[rand.nextInt(shopNames.length)];
    }

    public String getRandomFullName() {
        var firstName = firstNames[rand.nextInt(firstNames.length)];
        var lastName = lastNames[rand.nextInt(lastNames.length)];
        return firstName + " " + lastName;
    }

    public String getRandomManufactureNumber() {
        var manNum = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            manNum.append((char) (rand.nextInt(26) + 'a'));
        }
        manNum.append(getRandomNumInRange(100, 999));
        return manNum.toString();
    }

    public Pair<String, String> getRandomInstrumentWithCode() {
        var instruments = instrumentToCodeMap.keySet();
        var instrument = (String)instruments.toArray()[rand.nextInt(instruments.size())];
        var code = instrumentToCodeMap.get(instrument);
        return Pair.create(instrument, code);
    }
}
