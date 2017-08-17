package com.OAthour2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Created by jiaquan on 2016/12/19.
 */
@Configuration
public class HttpClient {

    private static Logger log = LoggerFactory.getLogger(HttpClient.class);

    String accessTokenURI = "";

    String clientID = "";

    String clientSecret = "";

    String clientAuthenticationScheme = "";

    String grantType = "";

    ClientCredentialsResourceDetails resourceDetails() {
        ClientCredentialsResourceDetails details = new ClientCredentialsResourceDetails();
        details.setAccessTokenUri(accessTokenURI);
        details.setClientId(clientID);
        details.setClientSecret(clientSecret);
        details.setGrantType(grantType);
//        details.setAuthenticationScheme(AuthenticationScheme.valueOf(clientAuthenticationScheme));
        return details;
    }

    /**
     * 获取OAuth2RestTemplate
     * @return  OAuth2RestTemplate
     */
    public OAuth2RestTemplate getHlyOAuth2RestTemplate() {
        OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resourceDetails(), new DefaultOAuth2ClientContext());
        return restTemplate;
    }

    /**
     * 获取处理Query Params OAuth2RestTemplate
     * @return  OAuth2RestTemplate
     */
    public OAuth2RestTemplate getHlyQueryParamsOAuth2RestTemplate() {
        OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resourceDetails(), new DefaultOAuth2ClientContext());
        restTemplate.setUriTemplateHandler(new QueryParamsUrlTemplateHandler());
        return restTemplate;
    }

    /**
     * 获取默认配置的RestTemplate
     * @return 默认RestTemplate
     */
    public RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }

    /**
     * 获取默认配置的处理Query Params RestTemplate
     * @return Query Params RestTemplate
     */
    public RestTemplate getQueryParamsRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setUriTemplateHandler(new QueryParamsUrlTemplateHandler());
        return restTemplate;
    }

    /**
     * 发送各种http请求，可以通过RequestEntity添加header,可以通过uriVariables添加path params 和query params
     * @param requestEntity
     * @param uriVariables path params 和query params的map
     * @return
     */
    public ResponseEntity<String> exchange(RequestEntity<?> requestEntity,  Map<String, Object> uriVariables){
        RestTemplate restTemplate = getQueryParamsRestTemplate();
        return restTemplate.exchange(requestEntity.getUrl().toString(), requestEntity.getMethod(), requestEntity, String.class, uriVariables);
    }

    /**
     * 发送http各种请求，可以通过RequestEntity添加header
     * @param requestEntity
     * @return
     */
    public ResponseEntity<String> exchange(RequestEntity<?> requestEntity){
        RestTemplate restTemplate = getRestTemplate();
        return restTemplate.exchange(requestEntity, String.class);
    }

    /**
     * 通过OAuth2向 发送http各种请求，可以通过RequestEntity添加header
     * @param requestEntity
     * @return
     */
    public ResponseEntity<String> exchangeHly(RequestEntity<?> requestEntity){
        OAuth2RestTemplate oAuth2RestTemplate = getHlyOAuth2RestTemplate();
        return oAuth2RestTemplate.exchange(requestEntity, String.class);
    }

}
