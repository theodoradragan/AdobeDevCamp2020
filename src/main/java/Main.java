/*
 ************************************************************************
 * ADOBE CONFIDENTIAL
 * ___________________
 *
 * Copyright 2018 Adobe Systems Incorporated
 * All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains
 * the property of Adobe Systems Incorporated and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * herein are proprietary to Adobe Systems Incorporated and its
 * suppliers and are protected by all applicable intellectual property laws,
 * including trade secret and copyright laws.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Adobe Systems Incorporated.
 ***********************************************************************
 */

import com.adobe.test_app.model.Company;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.fasterxml.jackson.annotation.PropertyAccessor.FIELD;
import static java.util.Objects.nonNull;

public class Main {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        objectMapper.setVisibility(FIELD, JsonAutoDetect.Visibility.ANY);

        final Company adobe = new Company("Adobe", 21_000);
        Company company = null;
        try {
            logger.info("Object before serialization: {}", adobe);
            final String adobeObjectAsJson = objectMapper.writeValueAsString(adobe);

            company = objectMapper.readValue(adobeObjectAsJson, Company.class);
            logger.info("Object after serialization: {}", company);
        } catch (JsonProcessingException e) {
            logger.error("Errors while trying to serialize/deserialize Company objects");
        }

        final String databaseURL = "jdbc:mysql://localhost:3306/test_db";
        final String user = "root";
        final String password = "";

        testInsertToDb(databaseURL, user, password, adobe);
    }

    public static void testInsertToDb(String databaseURL, String user, String password, Company company) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(databaseURL, user, password);
            logger.info("Connected to the database");

            final String sql = "INSERT INTO companies (name, number_of_employees) VALUES (?, ?)";

            int rowsInserted;
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, company.getName());
                statement.setLong(2, company.getNumberOfEmployees());

                rowsInserted = statement.executeUpdate();
            }
            if (rowsInserted > 0) {
                logger.info("A new company has been inserted successfully!");
            }
        } catch (SQLException ex) {
            logger.error("An error occurred due to {}", ex.getMessage());
        } finally {
            if (nonNull(conn)) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    logger.error("Error while closing DB connection");
                }
            }
        }
    }
}
