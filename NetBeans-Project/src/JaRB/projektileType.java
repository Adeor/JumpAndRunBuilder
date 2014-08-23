package JaRB;

//@copyright(autor = "Nikolai Stemmer", eMail = "nikolai@diestemmers.de")
public enum projektileType {

    FIREBALL("res/images/projektile/fireball.png"), ENERGIEBALL("res/images/projektile/energieball.png"),
    SAMEN("res/images/projektile/samen.png"), TEST("res/images/units/test.png");
    
    public final String location;
    projektileType(String location) {
        this.location = location;
    }
}