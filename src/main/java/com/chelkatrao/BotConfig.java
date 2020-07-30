package com.chelkatrao;

public enum BotConfig {

    BOT_TOKEN("1293443384:AAHG1B6INZKTbvv1-nOR1w3bIAWegNvco_E"),
    BOT_USERNAME("namozvaqtitaqvimbot");

    private String value;

    private BotConfig(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
