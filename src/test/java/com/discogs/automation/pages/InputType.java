package com.discogs.automation.pages;

public enum InputType {
    TITLE("title"), ARTIST("artist"), LABEL("label"),
    TRACK("track"), CATALOG("catno"), BARCODE("barcode"),
    ANV("anv"), FORMAT("format"), CREDIT("credit"),
    GENRE("genre", "Genre"), STYLE("style", "Style"), COUNTRY("country"),
    YEAR("year", "Year"), SUBMITTER("submitter"), CONTRIBUTOR("contributor"),
    MATRIX("matrix");
    private String inputId;
    private String infoHeaderName;

    private InputType(String inputId) {
        this.inputId = inputId;
    }

    private InputType(String inputId, String infoHeaderName) {
        this.inputId = inputId;
        this.infoHeaderName = infoHeaderName;
    }

    public String getInputId() {
        return inputId;
    }

    public String getInfoHeaderName(){
        return infoHeaderName;
    }
}
