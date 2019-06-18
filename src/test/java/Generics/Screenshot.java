package Generics;

public class Screenshot {
    private byte[] screenshot;
    private String type;

    public Screenshot(){}
    public Screenshot(byte[] screenshot){
        this.screenshot = screenshot;
        this.type="image/png";
    }

    public byte[] getScreenshot() {
        return screenshot;
    }

    public void setScreenshot(byte[] screenshot) {
        this.screenshot = screenshot;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
