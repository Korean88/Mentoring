package com.epam.threads.utils;

import org.junit.Test;

import java.util.Properties;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by Andrey Yun on 28.01.16.
 */
public class FileUtilTest {

    @Test
    public void shouldReadProperties() {
        Properties properties = FileUtil.loadPropertiesFromFile("rates/exchangeRate.properties");
        assertThat(properties.keySet(), not(empty()));
        String rootLogger = properties.getProperty("UsdEur");
        assertThat(rootLogger, equalTo("0.90"));
    }

    @Test
    public void shouldRewritePropertiesInFile() {
        String filename = "test.properties";
        Properties props = new Properties();
        props.put("fromTest1", "Val1");
        props.put("fromTest2", "Val2");
        props.put("fromTest3", "Val3");
        FileUtil.savePropertiesToFile(filename, props);
        Properties saved = FileUtil.loadPropertiesFromFile(filename);
        assertThat(saved.keySet(), is(notNullValue()));
        assertThat(saved.keySet().size(), is(3));
    }

    @Test
    public void shouldDelete() {
        FileUtil.removeFile("datafiles/123.txt");
    }

}
