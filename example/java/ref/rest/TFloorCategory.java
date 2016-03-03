package com.daigou.sg.rpc.category;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * This file is auto-generated by tgen
 * Don't change manually
 */

public class TFloorCategory implements Serializable {
    public int id;
    public String name;
    public ArrayList<String> names;
    public ArrayList<TCategory> subCategories;

    static TFloorCategory fromJSON(JsonParser jp) throws IOException {
        // Sanity check: verify that we got "Json Object":
        if (jp.nextToken() != JsonToken.START_OBJECT) {
            throw new IOException("Expected data to start with a TFloorCategory Object");
        }

        return doFromJSON(jp);
    }

    private static TFloorCategory doFromJSON(JsonParser jp) throws IOException {
        TFloorCategory result = new TFloorCategory();

        // Iterate over object fields:
        while (jp.nextToken() != JsonToken.END_OBJECT) {
            String fieldName = jp.getCurrentName();

            if (false) {
            } else if (fieldName.equals("id")) {
                jp.nextToken();
                result.id = jp.getIntValue();
            } else if (fieldName.equals("name")) {
                jp.nextToken();
                result.name = jp.getText();
            } else if (fieldName.equals("names")) {
                result.names = JsonUtils.readStringList(jp);
            } else if (fieldName.equals("subCategories")) {
                result.subCategories = TCategory.fromJSONArray(jp);
            }
        }
        return result;
    }

    static ArrayList<TFloorCategory> fromJSONArray(JsonParser jp) throws IOException {
        if (jp.nextToken() != JsonToken.START_ARRAY) {
            throw new IOException("Expected data to start with a TFloorCategory array");
        }

        ArrayList<TFloorCategory> result = new ArrayList<TFloorCategory>();

        // Iterate over object fields:
        while (jp.nextToken() != JsonToken.END_ARRAY) {
            result.add(doFromJSON(jp));
        }
        return result;
    }
}