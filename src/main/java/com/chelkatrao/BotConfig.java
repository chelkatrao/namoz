package com.chelkatrao;

public enum BotConfig {

    BOT_TOKEN("1119368061:AAHgLw7t-X2lims5T2M6hy50Cl_Y6ZwITwY"),
    BOT_USERNAME("chelkatrao_bot");

    private String value;

    private BotConfig(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
