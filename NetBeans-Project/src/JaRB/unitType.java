package JaRB;

//@copyright(autor = "Nikolai Stemmer", eMail = "nikolai@diestemmers.de")
public enum unitType {

    TEST("res/images/units/test.png", "res/images/units/testLeft.png", "res/images/units/testRight.png");
    
    public final String locationMid;
    public final String locationLeft;
    public final String locationRight;
    unitType(String locationMid, String locationLeft, String locationRight) {
        this.locationMid = locationMid;
        this.locationLeft = locationLeft;
        this.locationRight = locationRight;
    }
}