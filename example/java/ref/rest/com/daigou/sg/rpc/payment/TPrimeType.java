package com.daigou.sg.rpc.payment;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * This file is auto-generated by tgen
 * Don't change manually
 */

public class TPrimeType implements Serializable {
    public String primeTypeName;
    public String price;

    static TPrimeType fromJSON(JsonParser jp) throws IOException {
        // Sanity check: verify that we got "Json Object":
        if (jp.nextToken() != JsonToken.START_OBJECT) {
            throw new IOException("Expected data to start with a TPrimeType Object");
        }

        return doFromJSON(jp);
    }

    private static TPrimeType doFromJSON(JsonParser jp) throws IOException {
        TPrimeType result = new TPrimeType();

        // Iterate over object fields:
        while (jp.nextToken() != JsonToken.END_OBJECT) {
            String fieldName = jp.getCurrentName();

            if (false) {
            } else if (fieldName.equals("primeTypeName")) {
                jp.nextToken();
                result.primeTypeName = jp.getText();
            } else if (fieldName.equals("price")) {
                jp.nextToken();
                result.price = jp.getText();
            }
        }
        return result;
    }

    static ArrayList<TPrimeType> fromJSONArray(JsonParser jp) throws IOException {
        if (jp.nextToken() != JsonToken.START_ARRAY) {
            throw new IOException("Expected data to start with a TPrimeType array");
        }

        ArrayList<TPrimeType> result = new ArrayList<TPrimeType>();

        // Iterate over object fields:
        while (jp.nextToken() != JsonToken.END_ARRAY) {
            result.add(doFromJSON(jp));
        }
        return result;
    }
}