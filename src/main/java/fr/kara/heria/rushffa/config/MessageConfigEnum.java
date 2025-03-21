package fr.kara.heria.rushffa.config;

public enum MessageConfigEnum {
    PREFIX("§6§lRUSHFFA §8┃"),
    ;




    // Paramater
    private final String click;

    MessageConfigEnum(String click) {
        this.click = click;
    }

    public String getClick() {
        return click;
    }

    @Override
    public String toString() {
        return click;
    }
}
