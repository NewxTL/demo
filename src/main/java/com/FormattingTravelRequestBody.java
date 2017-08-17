package com;

import com.BPMDemo.ArtemisDataUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Newx on 2016/12/23.
 */
public class FormattingTravelRequestBody {

    private static final Logger log = LoggerFactory.getLogger(FormattingTravelRequestBody.class);

    public static Map fomattingTravel(JSONObject jsonObject){

        JSONArray custFormValues = jsonObject.getJSONArray("custFormValues");
        JSONObject obj = configJsonobject(custFormValues);
        log.info("===========map:"+obj);

        String returnDate = obj.getString("返回日期");
        log.info("returnDate"+returnDate);
        if(StringUtils.isBlank(returnDate)){
            returnDate = "";
        }
        String newReturnDate = ArtemisDataUtil.shortFormat(returnDate);
        if(StringUtils.isBlank(returnDate)){
            newReturnDate = "";
        }
        String traffic = obj.getString("交通工具");
        if(StringUtils.isBlank(traffic)){
            traffic = "";
        }
        String istraining = obj.getString("是否培训") ;
        if(StringUtils.isBlank(istraining)){
            istraining = "";
        }
        String unitCodeOID= obj.getString("部门");
        if(StringUtils.isBlank(unitCodeOID)){
            unitCodeOID = "";
        }
        String iscar = obj.getString("是否派车");
        if(StringUtils.isBlank(iscar)){
            iscar = "";
        }
        String regional = obj.getString("地域");
        if(StringUtils.isBlank(regional)){
            regional = "";
        }
        String travelType = obj.getString("出差类型");
        if(StringUtils.isBlank(travelType)){
            travelType = "";
        }
        String currency = obj.getString("币种");
        if(StringUtils.isBlank(currency)){
            currency = "";
        }
        String departureDate = obj.getString("出发日期");
        if(StringUtils.isBlank(departureDate)){
            departureDate = "";
        }
        String newdepartureDate = ArtemisDataUtil.shortFormat(departureDate);
        if(StringUtils.isBlank(newdepartureDate)){
            newdepartureDate = "";
        }
        String iscash = obj.getString("是否借支");
        if(iscash == "false"){
            iscash = "N";
        }else {
            iscash = "Y";
        }
        String cashAmount = obj.getString("借支金额");
        if(StringUtils.isBlank(cashAmount)){
            cashAmount = "";
        }
        String cashCurrency = currency;
        String jsonSting = obj.getString("费用类型及金额");
        if(StringUtils.isBlank(jsonSting)){
            jsonSting = "";
        }
        String trip = obj.getString("行程");
        if(StringUtils.isBlank(trip)){
            trip = "";
        }
        String amount = "";
        try {
            amount = JSONObject.parseObject(jsonSting).getString("amount");
            log.info("=========amount:" + amount);
        }catch (Exception e){
            log.error("==========未填写该项");
        }

        String issales = "Y";
        String applicationOID = jsonObject.getString("applicationOID"); //申请单OID
        String applicantOID = jsonObject.getString("applicantOID"); //申请人OID
        String status = jsonObject.getString("status");  //单据状态
        String rejectType = jsonObject.getString("status");  //驳回状态
        String documentDate = jsonObject.getString("createdDate");
        String newDocumentDate = ArtemisDataUtil.shortFormat(documentDate);
        JSONObject travelApplication = jsonObject.getJSONObject("travelApplication");
        String hlyNumber = travelApplication.getString("businessCode");
        //提取差旅报销单非必填字段
        JSONArray lines = new JSONArray();
        JSONObject linesDetails = new JSONObject();
        JSONArray travelItinerarys = travelApplication.getJSONArray("travelItinerarys");
        for(int i=0;i<travelItinerarys.size();i++){
            JSONObject object = travelItinerarys.getJSONObject(i);
            String visitDate = ArtemisDataUtil.shortFormat(object.getString("itineraryDate"));
            log.info("=========vistData:"+visitDate);
            JSONArray travelElements = object.getJSONArray("travelElements");
            log.info("=========travelElements:"+travelElements.toString());
            String activityOid = "";
            String place = "";
            String company = "";
            for(int j=0;j<travelElements.size();j++){
                linesDetails = new JSONObject();
                if(j<travelElements.size()/2) {
                    JSONArray jsonArray1 = travelElements.getJSONArray(j);
                    JSONArray jsonArray2 = travelElements.getJSONArray(j + travelElements.size() / 2);
                    log.info("=========jsonArray2:"+jsonArray2.toString());
                    JSONObject jsonObject1 = jsonArray1.getJSONObject(0);
                    String activityOID = jsonObject1.getString("value");//待替换
                    log.info("==========activityOID:"+activityOID);
                    activityOid = "300000002617048";

                    JSONObject jsonObject2 = configJsonobject(jsonArray2);
                    log.info("=========jsonObject2:"+jsonObject2.toString());
                    place = jsonObject2.getString("地点");
                    company = jsonObject2.getString("客户名称");
                }
                linesDetails.put("_status","insert");
                linesDetails.put("line_number","");
                linesDetails.put("place",place);
                linesDetails.put("company",company);
                linesDetails.put("visit_date",visitDate);
                linesDetails.put("activity_id",activityOid);
                lines.add(linesDetails);
            }
        }
        log.info("================hly_bumber:"+hlyNumber);

//        JSONArray lines = new JSONArray();
//        JSONObject linesDetails = new JSONObject();


        JSONArray headers = new JSONArray();
        JSONObject headersDetails = new JSONObject();
        headersDetails.put("_status","insert");
        headersDetails.put("travel_type",travelType);
        headersDetails.put("unit_code","10001477"); //待替换
        headersDetails.put("regional",regional);
        headersDetails.put("traffic",traffic);
        headersDetails.put("amount",amount);
        headersDetails.put("currency",currency);
        headersDetails.put("iscash",iscash);
        headersDetails.put("cash_amount",cashAmount);
        headersDetails.put("cash_currency",cashCurrency);
        headersDetails.put("issales",issales);      //待替换
        headersDetails.put("departure_date",newDocumentDate);
        headersDetails.put("istraining",istraining);
        headersDetails.put("iscar",iscar);
        headersDetails.put("trip",trip);
        headersDetails.put("lines",lines);
        headers.add(headersDetails);

        JSONArray parameter = new JSONArray();
        JSONObject parameterDetails = new JSONObject();
        parameterDetails.put("_status","insert");
        parameterDetails.put("employee_code","10210033"); //待替换
        parameterDetails.put("document_date",newDocumentDate); //
        parameterDetails.put("hly_number",hlyNumber);
        parameterDetails.put("unit_code","10001477");       //待替换
        parameterDetails.put("headers",headers);
        parameter.add(parameterDetails);

        JSONObject requestBody = new JSONObject();
        requestBody.put("action", "SUBMIT");
        requestBody.put("data_type", "TRL_REQUISITION");
        requestBody.put("parameter", parameter);
        parameter = null;

        JSONObject mappingValue = new JSONObject();
        mappingValue.put("applicationOID",applicationOID);
        mappingValue.put("createDate",newDocumentDate);
        mappingValue.put("artStatus",status);   //待添加
        mappingValue.put("customerStatus","1");  //待添加
        log.info("===========封装后的请求体:=========="+requestBody);

        Map map = new HashMap<>();
        map.put("requestBody",requestBody);
        map.put("mappingValue",mappingValue);
        return map;
    }

    private static JSONObject configJsonobject(JSONArray jsonArray){
        JSONObject jsonObject = new JSONObject();
        for (int i=0;i<jsonArray.size();i++) {
            JSONObject object = jsonArray.getJSONObject(i);
            String fieldName = object.getString("fieldName");
            String value = object.getString("value");
            jsonObject.put(fieldName, value);
        }
        return jsonObject;

    }

}
