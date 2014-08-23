package JaRB;

//@copyright(autor = "Nikolai Stemmer", eMail = "nikolai@diestemmers.de")
public enum effectType {

    EXPLOSION("res/images/effect/explosion/"/*+"name des Bilders/der Animation"*/, "src/JaRB/explosion");
    
    public final String effectFolder;
    public final String data;
    effectType(String effectFolder, String data) {
        this.effectFolder = effectFolder;
        this.data = data;
    }
}