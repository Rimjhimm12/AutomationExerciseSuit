package com.qa.ae.factory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
     Properties prop;



    public Properties initProperties() throws IOException {

        prop = new Properties();
        FileInputStream ip = new FileInputStream("src/main/resources/config/config.properties");
        prop.load(ip);
        return prop;

    }
}
