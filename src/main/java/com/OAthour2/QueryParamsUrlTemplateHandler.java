package com.OAthour2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.DefaultUriTemplateHandler;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;

/**
 * Created by jiaquan on 2016/12/19.
 * 用于处理query parameters
 */
public class QueryParamsUrlTemplateHandler extends DefaultUriTemplateHandler {

    private static Logger log = LoggerFactory.getLogger(QueryParamsUrlTemplateHandler.class);

    @Override
    public URI expand(String uriTemplate, Map<String, ?> uriVariables) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(uriTemplate);
        if(null!=uriVariables){
            for (Map.Entry<String, ?> varEntry : uriVariables.entrySet()) {
                uriComponentsBuilder.queryParam(varEntry.getKey(), varEntry.getValue());
            }
        }
        uriTemplate = uriComponentsBuilder.build().toUriString();
        return super.expand(uriTemplate, uriVariables);
    }
}
