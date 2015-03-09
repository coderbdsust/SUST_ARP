/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sust.admission.jsondata;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;

/**
 *
 * @author Biswajit Debnath
 */
public class JsonConverter {

    public static JSONArray convertUnitGroup(ResultSet resultSet) {
        JSONArray jsonArray = new JSONArray();

        String UNIT_NAME = "unit_name";
        String GROUP_NAME = "group_name";
        String MERIT_LIST_NAME_ID = "merit_list_name_id";

        try {
            while (resultSet.next()) {
                JSONObject object = new JSONObject();

                String meritListNameId = resultSet.getString(MERIT_LIST_NAME_ID);
                String unitName = resultSet.getString(UNIT_NAME);

                String groupName = resultSet.getString(GROUP_NAME);
                groupName = groupName.replace("0", "");
                object.put(MERIT_LIST_NAME_ID, meritListNameId);
                object.put(UNIT_NAME, unitName);
                object.put(GROUP_NAME, groupName);

                jsonArray.put(object);
            }
        } catch (SQLException error) {
            error.printStackTrace();
        } catch (JSONException error) {
            error.printStackTrace();
        } catch (Exception error) {
            error.printStackTrace();
        }

        return jsonArray;
    }

    public static JSONArray convertQuota(ResultSet resultSet) {
        JSONArray jsonArray = new JSONArray();

        String QUOTA_NAME = "quota_name";
        String QUOTA_ID = "quota_id";

        try {
            while (resultSet.next()) {
                JSONObject object = new JSONObject();

                String qutoaId = resultSet.getString(QUOTA_ID);
                String quotaName = resultSet.getString(QUOTA_NAME);

                object.put(QUOTA_ID, qutoaId);
                object.put(QUOTA_NAME, quotaName);
                jsonArray.put(object);
            }
        } catch (SQLException error) {
            error.printStackTrace();
        } catch (Exception error) {
            error.printStackTrace();
        }
        return jsonArray;
    }

    public static JSONArray convertPositionType(ResultSet resultSet) {
        JSONArray jsonArray = new JSONArray();

        String POSITION_TYPE_NAME = "postype_name";
        String POSITION_ID = "postype_id";

        try {
            while (resultSet.next()) {
                JSONObject object = new JSONObject();
                String positionTypeName = resultSet.getString(POSITION_TYPE_NAME);
                String positionId = resultSet.getString(POSITION_ID);
                object.put(POSITION_ID, positionId);
                object.put(POSITION_TYPE_NAME, positionTypeName);
                jsonArray.put(object);
            }
        } catch (SQLException error) {
            error.printStackTrace();
        } catch (JSONException error) {
            error.printStackTrace();
        }
        return jsonArray;

    }
}
