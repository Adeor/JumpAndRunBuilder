package JaRB;

//@copyright(autor = "Nikolai Stemmer", eMail = "nikolai@diestemmers.de")
public enum blockType {

    STONE("res/images/blocks/stone.png"), AIR("res/images/blocks/air.png"), DIRT("res/images/blocks/dirt.png"),
    GRAS("res/images/blocks/gras.png"), FIRE("res/images/blocks/fire.png"), BAUMSTAMM("res/images/blocks/baumstamm.png"),
    BAUMKRONE("res/images/blocks/baumkrone.png"), SAND("res/images/blocks/sand.png");
    
    public final String location;
    blockType(String location) {
        this.location = location;
    }
}