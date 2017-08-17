package com.OAthour2;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import sun.net.util.URLUtil;

import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Newx on 2016/11/30.
 * 汇联易数据导入GET请求分页模拟Demo
 */
public class main {

    private final static Logger log = LoggerFactory.getLogger(main.class);

    public static void main(String[] args){
        importData();
//        httpClient.exchange();
    }

    public static String gettoken(){
        HttpClient httpClient = new HttpClient();
        OAuth2RestTemplate oAuth2RestTemplate = httpClient.getHlyOAuth2RestTemplate();
        String token = oAuth2RestTemplate.getAccessToken().toString();
//        log.info("=================="+token);
        return token;
    }

    public static JSONArray importData(){

        int page = 0;
        int size = 20;
        String token = gettoken();
        Map requestBody = new HashMap<>();
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("2016-10-19 19:01:10");
        requestBody.put("startDate","2016-10-19 19:01:10");
        requestBody.put("companyOID","8caa950c-18b7-4a10-aff3-4a17aaece968");
        requestBody.put("page",page);
        requestBody.put("size",size);

//        Map<String, String> headers = new HashMap<String, String>();
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Authorization","Bearer " + token);
        headers.add("Content-Type","application/json");

        JSONArray response = new JSONArray();
        do{
            requestBody.put("page",page);
            requestBody.put("size",size);
            response = logRequest(headers,requestBody);
            log.info("==========正在请求第{}页数据==========",page);
            page++;
        } while (response.size() == size);
        log.info("==========请求返回的一页数据=========="+response);
        return response;

    }

    public static JSONArray logRequest(MultiValueMap<String, String> headers, Map<String, Object> requestBody){
        HttpClient httpClient = new HttpClient();
        URI url = null;
        try {
            url = new URI("http://apiuat.yunmart.com/api/implement/travel/applications/all");
//            url = new URI("http://apiuat.yunmart.com/api/implement/invoices?page=0&size=1&companyOID=cf2b3694-b4f8-4aca-b233-111748eb025b&beginTime=2014-12-12 12:00:00&endTime=2017-12-19 12:00:00&invoiceStatus=1001&invoiceStatus=1002&expenceTypeOIDs=d30023be-45d5-4fed-994d-91a2d51d2de5&expenceTypeOIDs=94be7ea4-5f59-4e95-9498-eb5f33da6423&invoiceCurrencyCodes=CNY&userOID=9fd4f538-5d98-4ff4-932e-709ad5f497c3");
        }catch (Exception e){

        }
        RequestEntity requestEntity = new RequestEntity(headers,HttpMethod.GET,url);

        ResponseEntity<String> response = httpClient.exchange(requestEntity,requestBody);
        log.info("===========response:=========="+response);
        String result = response.getBody();
        log.info("===========result:=========="+result);
        JSONArray jsonArray = JSONArray.parseArray(result);
       return jsonArray;
    }

    public static JSONObject formattingTravelRequestBody(JSONArray jsonArray){
        JSONObject requestBody = new JSONObject();
        for(int i=0;i<jsonArray.size();i++){

            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String employeeCode = jsonObject.getString("applicantOID");
            String unitCode = "　";
            String documentDate = jsonObject.getString("lastModifiedDate");
            String hlyNumber = "　";

            String travelType = jsonObject.getString("title");
            String unitCode1 = "　";
            String regional = "　";
            String trafficType = " ";
            JSONObject travelApplication = jsonObject.getJSONObject("travelApplication");
            JSONArray travelItinerarys = travelApplication.getJSONArray("travelItinerarys");
            for(int j=0;j<travelItinerarys.size();j++){
                JSONObject jsonObject1 = travelItinerarys.getJSONObject(j);
                JSONArray travelItineraryTraffics = jsonObject1.getJSONArray("travelItineraryTraffics");
                for (int q =0;q<travelItineraryTraffics.size();q++){
                    JSONObject jsonObject11 = travelItineraryTraffics.getJSONObject(q);
                    trafficType = jsonObject11.getString("trafficType");
                }
            }

            String amount = travelApplication.getString("totalBudget");
            String currency = travelApplication.getString("currencyCode");
            String iscash = travelApplication.getString("borrowFlag");
            String cashAmount = travelApplication.getString("borrowAmount");
            String cashCurrency = travelApplication.getString("currencyCode");
            String issales = "　";
            String departureDate = travelApplication.getString("startDate");
            String istraining = "　";
            String returnDate= travelApplication.getString("endDate");
            String iscar = "　";
            String trip = "　";
//            ----------------------------------
            String lineNumber = "　";
            String visitDate = "　";
            String company = "　";
            String place = "　";
            String activityId = "　";

            JSONArray lines = new JSONArray();
            JSONObject linesDetails = new JSONObject();
            linesDetails.put("_status","insert");
            linesDetails.put("line_number",lineNumber);
            linesDetails.put("visit_date",visitDate);
            linesDetails.put("company",company);
            linesDetails.put("place",place);
            linesDetails.put("activity_id",activityId);
            lines.add(linesDetails);

            JSONArray headers = new JSONArray();
            JSONObject headersDetails = new JSONObject();
            headersDetails.put("_status","insert");
            headersDetails.put("travel_type",travelType);
            headersDetails.put("unit_code",unitCode);
            headersDetails.put("regional",regional);
            headersDetails.put("traffic",trafficType);
            headersDetails.put("amount",amount);
            headersDetails.put("currency",currency);
            headersDetails.put("iscash",iscash);
            headersDetails.put("cash_amount",cashAmount);
            headersDetails.put("cash_currency",cashCurrency);
            headersDetails.put("issales",issales);      //
            headersDetails.put("departure_date",departureDate);
            headersDetails.put("istraining",istraining);
            headersDetails.put("iscar",iscar);
            headersDetails.put("trip",trip);
            headersDetails.put("lines",lines);
            headers.add(headersDetails);

            JSONArray parameter = new JSONArray();
            JSONObject parameterDetails = new JSONObject();
            parameterDetails.put("_status","insert");
            parameterDetails.put("employee_code",employeeCode);
            parameterDetails.put("document_date",documentDate);
            parameterDetails.put("hly_number",hlyNumber);
            parameterDetails.put("unit_code","");
            parameterDetails.put("headers",headers);
            parameter.add(parameterDetails);

//        Map<String,String> requestBody = new HashMap<String ,String>();
            requestBody.put("action", "SUBMIT");
            requestBody.put("data_type", "REPORT");
            requestBody.put("parameter", parameter);
            parameter = null;

            log.info("===========封装后的请求体:=========="+requestBody);

        }
        return requestBody;
    }
//    public static void formattingTravelRequestBody(JSONArray jsonArray){
//        for(int i=0;i<jsonArray.size();i++){
//            JSONObject jsonObject = jsonArray.getJSONObject(i);
//            JSONArray custFormValues = jsonObject.getJSONArray("custFormValues");
//
//            String employeeOID = custFormValues.getJSONObject(0).getString("createdBy"); //通过人员映射表找oid
//            String unitOID = custFormValues.getJSONObject(0).getString("value"); //同过部门映射表找部门Oid
//            String documentDate = custFormValues.getJSONObject(0).getString("createdDate");
//            String departureDate = custFormValues.getJSONObject(1).getString("value");
//            String ReturnDate = custFormValues.getJSONObject(2).getString("value");
//            JSONObject travelApplication = new JSONObject();
//            JSONArray travelItinerarys = new JSONArray();
//            JSONObject jsonObject1 = travelItinerarys.getJSONObject(0);
//            JSONArray travelElements = jsonObject1.getJSONArray("travelElements");
//            JSONArray travelItineraryTraffics = jsonObject1.getJSONArray("travelItineraryTraffics");
//            for (int q =0;q<travelItineraryTraffics.size();q++){
//                JSONObject jsonObject11 = travelItineraryTraffics.getJSONObject(q);
//                String trafficType = jsonObject11.getString("trafficType");
//            }
//            JSONArray jsonArray1 = travelElements.getJSONArray(0);
//            JSONObject jsonObject11 = jsonArray1.getJSONObject(0);
//            String activityId = jsonObject11.getString("value");
//            String hlyNumber = travelApplication.getString("businessCode");
//            String travelType = jsonObject.getString("formType");
//
//        }
//    }

}
