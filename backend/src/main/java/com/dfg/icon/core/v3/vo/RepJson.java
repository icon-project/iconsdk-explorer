package com.dfg.icon.core.v3.vo;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Created by lyj01702 on 2019-07-30.
 */
@Data
public class RepJson {
    RepRepresentative representative;

    RepServer server;

    public RepJson(){}

    public RepJson(String data){
        this.representative = new RepRepresentative();
        this.server = new RepServer();
        System.out.println(data);
        data = data.replace("\n", "").replace("\r", "").replace("\t", "");

        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(data);
        JsonObject rootObject =  element.getAsJsonObject();

        JsonObject representativeObj = rootObject.get("representative").getAsJsonObject();
        if(representativeObj.get("logo") != null && !representativeObj.get("logo").isJsonNull()){
            RepLogo logo = new RepLogo();
            JsonObject logoObj = representativeObj.get("logo").getAsJsonObject();
            if(logoObj.get("logo_256") != null && !logoObj.get("logo_256").isJsonNull()) {
                logo.setLogo_256(logoObj.get("logo_256").getAsString());
            }
            if(logoObj.get("logo_1024") != null && !logoObj.get("logo_1024").isJsonNull()) {
                logo.setLogo_1024(logoObj.get("logo_1024").getAsString());
            }
            if(logoObj.get("logo_svg") != null && !logoObj.get("logo_svg").isJsonNull()) {
                logo.setLogo_svg(logoObj.get("logo_svg").getAsString());
            }
            this.representative.setLogo(logo);
        }

        if(representativeObj.get("media") != null && !representativeObj.get("media").isJsonNull()){
            RepMedia media = new RepMedia();
            JsonObject mediaObj = representativeObj.get("media").getAsJsonObject();
            if(mediaObj.get("steemit") != null && !mediaObj.get("steemit").isJsonNull()) {
                media.setSteemit(mediaObj.get("steemit").getAsString());
            }
            if(mediaObj.get("twitter") != null && !mediaObj.get("twitter").isJsonNull()) {
                media.setTwitter(mediaObj.get("twitter").getAsString());
            }
            if(mediaObj.get("youtube") != null && !mediaObj.get("youtube").isJsonNull()) {
                media.setYoutube(mediaObj.get("youtube").getAsString());
            }
            if(mediaObj.get("facebook") != null && !mediaObj.get("facebook").isJsonNull()) {
                media.setFacebook(mediaObj.get("facebook").getAsString());
            }
            if(mediaObj.get("github") != null && !mediaObj.get("github").isJsonNull()) {
                media.setGithub(mediaObj.get("github").getAsString());
            }
            if(mediaObj.get("reddit") != null && !mediaObj.get("reddit").isJsonNull()) {
                media.setReddit(mediaObj.get("reddit").getAsString());
            }
            if(mediaObj.get("keybase") != null && !mediaObj.get("keybase").isJsonNull()) {
                media.setKeybase(mediaObj.get("keybase").getAsString());
            }
            if(mediaObj.get("telegram") != null && !mediaObj.get("telegram").isJsonNull()) {
                media.setTelegram(mediaObj.get("telegram").getAsString());
            }
            if(mediaObj.get("wechat") != null && !mediaObj.get("wechat").isJsonNull()) {
                media.setWechat(mediaObj.get("wechat").getAsString());
            }
            this.representative.setMedia(media);
        }
        JsonObject serverObj = rootObject.get("server").getAsJsonObject();
        if(serverObj.get("location") != null && !serverObj.get("location").isJsonNull()){
            RepLocation location = new RepLocation();
            JsonObject locationObj = serverObj.get("location").getAsJsonObject();
            if(locationObj.get("country") != null && !locationObj.get("country").isJsonNull()) {
                location.setCountry(locationObj.get("country").getAsString());
            }
            if(locationObj.get("city") != null && !locationObj.get("city").isJsonNull()) {
                location.setCity(locationObj.get("city").getAsString());
            }
            this.server.setLocation(location);
        }
        if(serverObj.get("server_type") != null && !serverObj.get("server_type").isJsonNull()){
            this.server.setServer_type(serverObj.get("server_type").getAsString());
        }
        if(serverObj.get("api_endpoint") != null && !serverObj.get("api_endpoint").isJsonNull()){
            this.server.setApi_endpoint(serverObj.get("api_endpoint").getAsString());
        }
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
