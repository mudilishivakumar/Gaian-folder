package models;

import org.json.JSONObject;

public class RTC {

	private RTC(){}
	private static final JSONObject VOLUME_CONTROL = new JSONObject("{\n\"name\": \"RTC Test for Increase volume\",\n\"description\": \"RTC Test for Increase volume\",\n\"trigger\": {\n\"triggerType\": \"ONETIME\"\n},\n\"destination\": {\n\"type\": \"GROUP\",\n\"id\": \"60efd8d87981e50007813119\",\n\"iteration\": {\n\"type\": \"NONE\"\n}\n},\n\"channel\": {\n\"channelType\": \"MEF\",\n\"destinationAttribute\": \"model.entities[*].deviceId\",\n\"mefType\": \"RTC\",\n\"packageName\": \"MEF-VOLUME-T003\",\n\"command\": \"SET_VOLUME\",\n\"communication\": {\n\"type\": \"BROADBAND\"\n},\n\"parameters\": [\n{\n\"contentType\": \"STATIC_TEXT\",\n\"value\": \"{\\\"key\\\":\\\"volume\\\",\\\"value\\\":200}\",\n\"resultantType\": \"JSON\"\n}\n ]\n}\n}");
	private static final JSONObject SOFTWARE_UPDATE = new JSONObject("{\n \"name\": \"Software Upgrade\",\n \"trigger\": {\n \"triggerType\": \"ONETIME\"\n },\n \"destination\": {\n \"type\": \"GROUP\",\n \"id\": \"60efd8d87981e50007813119\",\n \"iteration\": {\n \"type\": \"NONE\"\n },\n \"filters\": [\n {\n \"type\": \"STATIC\",\n \"attribute\": \"deviceType\",\n \"value\": \"mobile\"\n }\n ]\n },\n \"channel\": {\n \"channelType\": \"MEF\",\n \"mefType\": \"SOFTWARE_UPGRADE\",\n \"packageName\": \"MEF-SW-086\",\n \"rpcFilename\": \"rpc.json\",\n \"contents\": [\n {\n \"contentType\": \"STATIC_URL\",\n \"url\": \"http://dev-ingress-gateway.gaiansolutions.com/content-service/v1.0/content/download/33650c91-098c-4a97-b92d-1802bec47c49\",\n \"name\": \"ngapp_0.7.5.apk\",\n \"async\": false\n },\n {\n \"contentType\": \"STATIC_TEXT\",\n \"value\": \"{\\\"product\\\": \\\"NGApp\\\", \\\"version\\\": \\\"0.7.5\\\", \\\"versionCode\\\": \\\"12\\\", \\\"upgradePolicy\\\": \\\"forced\\\", \\\"artifactInfo\\\": [ {\\\"fileType\\\": \\\"apk\\\", \\\"fileName\\\": \\\"ngapp_latest.apk\\\", \\\"fileSize\\\": 87445065, \\\"releaseTaype\\\": \\\"full\\\", \\\"md5sum\\\": \\\"84ddb5b1f9d9f4eae69e75864fcd9862\\\", \\\"timeStampEpoch\\\": 1234455, \\\"version\\\": \\\"0.7.5\\\"} ] }\",\n \"filename\": \"rpc.json\",\n \"async\": false\n }\n ],\n \"destinationAttribute\": \"model.entities[*].deviceId\",\n \"communication\": {\n \"type\": \"BROADBAND\"\n }\n }\n}");
	private static final JSONObject BRIGHTNESS_CONTROL = new JSONObject("{\n \"name\": \"RTC Test for Increase volume\",\n \"description\": \"RTC Test for Increase volume\",\n \"trigger\": {\n \"triggerType\": \"ONETIME\"\n },\n \"destination\": {\n \"type\": \"GROUP\",\n \"id\": \"60efd8d87981e50007813119\",\n \"iteration\": {\n \"type\": \"NONE\"\n }\n },\n \"channel\": {\n \"channelType\": \"MEF\",\n \"destinationAttribute\": \"model.entities[*].deviceId\",\n \"mefType\": \"RTC\",\n \"packageName\": \"MEF-BN-1019\",\n \"command\": \"SET_BRIGHTNESS\",\n \"communication\": {\n \"type\": \"BROADBAND\"\n },\n \"parameters\": [\n {\n \"contentType\": \"STATIC_TEXT\",\n \"value\": \"{\\\"key\\\":\\\"status\\\",\\\"value\\\":80}\",\n \"resultantType\": \"JSON\"\n }\n ]\n }\n}");

	private static JSONObject BA_DELIVERY = null;
	public static String getVolumeControl() {
		return VOLUME_CONTROL.toString();
	}

	public static String getSoftwareUpdate() {
		return SOFTWARE_UPDATE.toString();
	}

	public static String getBrightnessControl() {
		return BRIGHTNESS_CONTROL.toString();
	}

	public static String getBA(String packageName, String baId) {
		String BA_Delivery = "{\n" +
				"    \"name\": \"BA TEST\",\n" +
				"    \"trigger\": {\n" +
				"        \"triggerType\": \"ONETIME\"\n" +
				"    },\n" +
				"    \"destination\": {\n" +
				"        \"type\": \"GROUP\",\n" +
				"        \"id\": \"60efd8d87981e50007813119\",\n" +
				"        \"iteration\": {\n" +
				"            \"type\": \"NONE\"\n" +
				"        }\n" +
				"    },\n" +
				"    \"channel\": {\n" +
				"        \"channelType\": \"MEF\",\n" +
				"        \"mefType\": \"BROADCASTER_APP\",\n" +
				"        \"packageName\": \"" + packageName + "\",\n" +
				"        \"command\": \"MEF_HELD\",\n" +
				"        \"rpcFilename\": \"held.xml\",\n" +
				"        \"destinationAttribute\": \"model.entities[*].deviceId\",\n" +
				"        \"contents\": [\n" +
				"            {\n" +
				"                \"contentType\": \"BA\",\n" +
				"                \"baId\": \"" + baId + "\",\n" +
				"                \"userId\": \"1194\",\n" +
				"                \"serviceId\": \"1001\"\n" +
				"            }\n" +
				"        ],\n" +
				"        \"communication\": {\n" +
				"            \"type\": \"BROADBAND\"\n" +
				"        }\n" +
				"    }\n" +
				"}";
		BA_DELIVERY = new JSONObject(BA_Delivery);
		return BA_Delivery;
	}
}
