package com;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Newx on 2016/12/22.
 */
public class main {

    private static Logger log = LoggerFactory.getLogger(main.class);


    public static void main(String[] args){
        JSONObject j = new JSONObject();
        j.put("status","");
        System.out.println(j.toString());
    }

}
