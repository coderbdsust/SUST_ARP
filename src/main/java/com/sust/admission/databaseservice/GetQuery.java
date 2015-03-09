/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sust.admission.databaseservice;

import com.sust.admission.constant.DbConstant;

/**
 *
 * @author Biswajit Debnath
 */
public class GetQuery extends DbConstant {

    //Selecing from database query
    public static String getQuota() {
        return "select quota_id, quota_name from " + ARP_SCHEMA + ".quota order by quota_id";
    }

    public static String getPosType() {
        return "select postype_id, postype_name from " + ARP_SCHEMA + ".position_type order by postype_id";
    }

    public static String getUnitId(String unitName) {
        return "select  unit_id from " + ARP_SCHEMA + ".unit where unit_name ='" + unitName + "'";
    }

    public static String getGroupNoId(String groupName) {
        return "select group_id from " + ARP_SCHEMA + ".group where group_name = '" + groupName + "'";
    }

    public static String getUnitName(String unitId) {
        return "select unit_name from " + ARP_SCHEMA + ".unit where unit_id=" + unitId;
    }

    public static String getGroupName(String groupId) {
        return "select group_name from " + ARP_SCHEMA + ".group where group_id=" + groupId;
    }

    public static String getUnitGroup() {
        return "select merit_list_name_id, unit_name, group_name from "
                + "((select * from " + ARP_SCHEMA + ".unit join " + ARP_SCHEMA + ".merit_list_name using(unit_id))"
                + " as sau join (select * from " + ARP_SCHEMA + ".group) as sug using(group_id)) order by merit_list_name_id";
    }

    public static String getResultQuery() {
        return "select query_key, unit_name, group_name, postype_name, quota_name, start_position, end_position, pdf_no, priority from "
                + "(((((select * from " + ARP_SCHEMA + ".merit_list_name join " + ARP_SCHEMA + ".result_query using (merit_list_name_id)) as rsq "
                + "join (select * from " + ARP_SCHEMA + ".unit) as sau using (unit_id)) join (select * from " + ARP_SCHEMA + ".group) as sug using (group_id))"
                + "join (select * from " + ARP_SCHEMA + ".position_type) as sup using(postype_id)) join (select * from " + ARP_SCHEMA + ".quota) as suq "
                + "using(quota_id)) order by merit_list_name_id, postype_id, quota_id";
    }

    public static String getStudentResultInRange(String meritList, String posType, String quotaName, String startPosition, String endPosition) {
        return "select exam_roll, t.position from " + ADM_SCHEMA + ".sust_result t "
                + "where t.merit_list_name like '" + meritList + "' and t.quota like '" + quotaName + "' and "
                + "t.position_type like '" + posType + "'"
                + " and t.position between " + startPosition + " and " + endPosition + " order by t.exam_roll";
    }

    public static String getStudentResult(String meritList, String posType, String quotaName) {
        return "select exam_roll, t.position from " + ADM_SCHEMA + ".sust_result t "
                + "where t.merit_list_name like '" + meritList + "' and t.quota like '" + quotaName + "' and "
                + "t.position_type like '" + posType + "'"
                + "order by t.exam_roll";
    }

    //Adding data to database query
    public static String addQuota(String quotaName) {
        return "INSERT INTO " + ARP_SCHEMA + ".quota (quota_name) "
                + "SELECT * FROM (SELECT '" + quotaName + "') AS temp "
                + "WHERE NOT EXISTS ("
                + "    SELECT quota_name FROM " + ARP_SCHEMA + ".quota WHERE quota_name = '"
                + quotaName + "') LIMIT 1";
    }

    public static String addPosType(String posType) {
        return "INSERT INTO " + ARP_SCHEMA + ".position_type (postype_name) "
                + "SELECT * FROM (SELECT '" + posType + "') AS temp "
                + "WHERE NOT EXISTS ("
                + "    SELECT postype_name FROM " + ARP_SCHEMA + ".position_type WHERE postype_name = '" + posType + "'"
                + ") LIMIT 1;";
    }

    public static String addUnit(String unitName) {
        return "INSERT INTO " + ARP_SCHEMA + ".unit (unit_name) "
                + "SELECT * FROM (SELECT '" + unitName + "' ) AS tmp "
                + "WHERE NOT EXISTS ("
                + " SELECT unit_name FROM " + ARP_SCHEMA + ".unit WHERE unit_name = '" + unitName + "') LIMIT 1";
    }

    public static String addGroup(String groupNo) {
        return "INSERT INTO " + ARP_SCHEMA + ".group (group_name) "
                + "SELECT * FROM (SELECT '" + groupNo + "' ) AS tmp "
                + "WHERE NOT EXISTS ( "
                + "SELECT group_name FROM " + ARP_SCHEMA + ".group WHERE group_name = '" + groupNo + "') LIMIT 1";
    }

    public static String addMeritListName(String unitName, String groupNo) {
        return "insert into " + ARP_SCHEMA + ".merit_list_name (unit_id, group_id) "
                + "select * from (select " + ARP_SCHEMA + ".unit.unit_id from " + ARP_SCHEMA + ".unit where unit_name like '"+unitName+"') as uids "
                + "join (select " + ARP_SCHEMA + ".group.group_id from " + ARP_SCHEMA + ".group where group_name like '"+groupNo+"') as gids "
                + "where (unit_id, group_id) not in("
                + "select unit_id , group_id from " + ARP_SCHEMA + ".merit_list_name where "
                + "unit_id=(select " + ARP_SCHEMA + ".unit.unit_id from " + ARP_SCHEMA + ".unit where unit_name like '"+unitName+"')"
                + "and group_id=(select " + ARP_SCHEMA + ".group.group_id from " + ARP_SCHEMA + ".group where group_name like '"+groupNo+"'))";
    }

    public static String addMeritList() {
        return "insert into " + ARP_SCHEMA + ".result_query( "
                + ARP_SCHEMA + ".result_query.merit_list_name_id, "
                + ARP_SCHEMA + ".result_query.postype_id, "
                + ARP_SCHEMA + ".result_query.quota_id "
                + " ) "
                + "select * from "
                + "	((select " + ARP_SCHEMA + ".merit_list_name.merit_list_name_id from " + ARP_SCHEMA + ".merit_list_name) as merit_list_ids"
                + " join (select " + ARP_SCHEMA + ".position_type.postype_id from " + ARP_SCHEMA + ".position_type) as postype_ids)"
                + " join (select " + ARP_SCHEMA + ".quota.quota_id from " + ARP_SCHEMA + ".quota) as quota_type_ids"
                + " where (merit_list_name_id, postype_id,quota_id) not in"
                + "(select " + ARP_SCHEMA + ".result_query.merit_list_name_id, "
                + ARP_SCHEMA + ".result_query.postype_id, " + ARP_SCHEMA + ".result_query.quota_id from " + ARP_SCHEMA + ".result_query)";
    }

    //Deleting data from database query
    public static String delUnit(String key) {
        return "delete from " + ARP_SCHEMA + ".unit where unit_id = " + key;
    }

    public static String delGroupNo(String key) {
        return "delete from " + ARP_SCHEMA + ".group where group_id = " + key;
    }

    public static String delQuota(String key) {
        return "delete from " + ARP_SCHEMA + ".quota where quota_id = " + key;
    }

    public static String delPosType(String key) {
        return "delete from " + ARP_SCHEMA + ".position_type where postype_id = " + key;
    }

    public static String delUnitGroup(String key) {
        return "delete from " + ARP_SCHEMA + ".merit_list_name where merit_list_name_id=" + key;
    }

    /*
     Updating database query
     */
    public static String updateResultQueryWithPosition(int sPosition, int ePosition, int pdf, int priority, int key) {
        return "update " + ARP_SCHEMA + ".result_query set start_position=" + sPosition + ", end_position=" + ePosition + ", pdf_no=" + pdf + ", priority=" + priority + " where query_key=" + key;
    }

    public static String updateResultQuery(int pdf, int priority, int key) {
        return "update " + ARP_SCHEMA + ".result_query set start_position=null, end_position=null ,pdf_no=" + pdf + ", priority=" + priority + " where query_key=" + key;
    }

    public static String updateNullResultQuery(int key) {
        return "update " + ARP_SCHEMA + ".result_query set start_position=null, end_position=null ,pdf_no=null, priority=null where query_key=" + key;
    }
    
    /*
    query for topsheet
    */
    
    public static String getTopSheetInRange(String meritList, String posType, String quotaType, String startPos, String endPos){
    
        return "select serial, position, exam_roll, contact_no from " + ADM_SCHEMA + ".sust_result"
        + " join " + ADM_SCHEMA + ".student_personal_info using(exam_roll) "
        + " where sust_result.MERIT_LIST_NAME like '" + meritList + "' and sust_result.quota like '"
        + quotaType + "'  and sust_result.position_type like '" + posType + "' and serial between "+
        startPos + " and " + endPos + " order by serial, position";
    }
    
    public static String getTopSheet(String meritList, String posType, String quotaType){
    
        return "select serial, position, exam_roll, contact_no from " + ADM_SCHEMA + ".sust_result"
        + " join " + ADM_SCHEMA + ".student_personal_info using(exam_roll) "
        + " where sust_result.MERIT_LIST_NAME like '" + meritList + "' and sust_result.quota like '"
        + quotaType + "'  and sust_result.position_type like '" + posType + "' order by serial, position";
    }

}
