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

package com.adobe.test_app.model;

import java.util.Objects;
import java.util.UUID;

public final class Company {
    private String id;
    private String name;
    private long numberOfEmployees;

    public Company() {
    }

    public Company(String name, long numberOfEmployees) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.numberOfEmployees = numberOfEmployees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Company)) {
            return false;
        }
        final Company company = (Company) o;
        return numberOfEmployees == company.numberOfEmployees &&
                Objects.equals(id, company.id) &&
                Objects.equals(name, company.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, numberOfEmployees);
    }

    @Override
    public String toString() {
        return "Company{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", numberOfEmployees=" + numberOfEmployees +
                '}';
    }
}
