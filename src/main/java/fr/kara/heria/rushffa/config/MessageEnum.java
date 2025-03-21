package fr.kara.heria.rushffa.config;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum MessageEnum {
    DOUBLE1("§7explose tout avec un §c§ldouble kill §7!"),
    DOUBLE2("§7laisse un sillage de destruction, §c§ldouble kill §7!"),

    TRIPLE1("§7est en feu avec un §c§ltriplé §7!"),
    TRIPLE2("§7enchaîne avec un §c§ltriplé §7!"),
    TRIPLE3("§7fait trembler l’arène, §c§ltriplé §7!"),
    TRIPLE4("§7déclenche une tempête de kills, §c§ltriplé §7!"),

    QUADRA1("§7fait un massacre, §c§lquadruplé §7!"),
    QUADRA2("§7règne sur le champ de bataille, §c§lquadruplé §7!"),

    PENTA1("§7a réussi un §c§lquintuplé §7!"),
    PENTA2("§7fait un carnage ! §c§l5 ÉLIMINATIONS"),
    PENTA3("§7est imparable, §c§lquintuple élimination §7!"),
    PENTA4("§7est une machine à tuer, §c§lquintuplé §7!"),
    PENTA5("§7est une §6légende§7, §c§lquintuple élimination §7!"),
    PENTA6("§7ne laisse personne debout, §c§lquintuplé §7!"),
    ;

    private final String message;

    MessageEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public static String getRandomDoubleMessage() {
        List<MessageEnum> doubleMessages = Stream.of(MessageEnum.values())
                .filter(msg -> msg.name().startsWith("DOUBLE"))
                .collect(Collectors.toList());

        int randomIndex = ThreadLocalRandom.current().nextInt(doubleMessages.size());
        return doubleMessages.get(randomIndex).getMessage();
    }

    public static String getRandomTripleMessage() {
        List<MessageEnum> tripleMessages = Stream.of(MessageEnum.values())
                .filter(msg -> msg.name().startsWith("TRIPLE"))
                .collect(Collectors.toList());

        int randomIndex = ThreadLocalRandom.current().nextInt(tripleMessages.size());
        return tripleMessages.get(randomIndex).getMessage();
    }

    public static String getRandomQuadraMessage() {
        List<MessageEnum> quadraMessages = Stream.of(MessageEnum.values())
                .filter(msg -> msg.name().startsWith("QUADRA"))
                .collect(Collectors.toList());

        int randomIndex = ThreadLocalRandom.current().nextInt(quadraMessages.size());
        return quadraMessages.get(randomIndex).getMessage();
    }

    public static String getRandomPentaMessage() {
        List<MessageEnum> pentaMessages = Stream.of(MessageEnum.values())
                .filter(msg -> msg.name().startsWith("PENTA"))
                .collect(Collectors.toList());

        int randomIndex = ThreadLocalRandom.current().nextInt(pentaMessages.size());
        return pentaMessages.get(randomIndex).getMessage();
    }
}