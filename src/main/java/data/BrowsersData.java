package data;

public enum BrowsersData {
    CHROME("chrome");
    private String name;

    BrowsersData(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}