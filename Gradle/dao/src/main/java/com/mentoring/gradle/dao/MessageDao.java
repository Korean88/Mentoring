package com.mentoring.gradle.dao;

import java.io.IOException;

/**
 * Created by Andrey Yun on 28.02.2016.
 */
public interface MessageDao {

    String retrieveMessageFromFile() throws IOException;
}
