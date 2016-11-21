package com.niku.andrew.quiz;

/**
 * Created by andrew on 12.11.16.
 */
public class DBCore {

    private static DBCore ourInstance = new DBCore();

    public static DBCore getInstance() {
        return ourInstance;
    }

    private DBCore() {
    }

}
