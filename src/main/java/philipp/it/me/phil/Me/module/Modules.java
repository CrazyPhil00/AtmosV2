package philipp.it.me.phil.Me.module;

import java.util.ArrayList;
import java.util.List;

public class Modules {

    public static List<String> getCombatSection() {
        List<String> Combat = new ArrayList<>();
        Combat.add("Killaura");
        Combat.add("Autoclicker");
        Combat.add("Criticals");
        Combat.add("Reflex");
        return Combat;
    }

    public static List<String> getRenderSection() {
        List<String> Render = new ArrayList<>();
        Render.add("Fullbright");
        Render.add("Tracer");
        Render.add("Freelook");
        Render.add("ESP");
        return Render;
    }

    public static List<String> getWorldSection() {
        List<String> World = new ArrayList<>();
        World.add("Scaffold");
        World.add("Tower");
        return World;
    }

    public static List<String> getMovementSection() {
        List<String> Movement = new ArrayList<>();
        Movement.add("Fly");
        Movement.add("Speed");
        Movement.add("Sprint");
        Movement.add("Longjump");
        return Movement;
    }

    public static List<String> getPlayerSection() {
        List<String> Player = new ArrayList<>();
        Player.add("Nofall");
        Player.add("InvCleaner");
        return Player;
    }

    public static List<String> getExploitSection() {
        List<String> Exploit = new ArrayList<>();
        Exploit.add("Invisibility");
        return Exploit;
    }

    public static List<String> getClientSection(){
        List<String> client = new ArrayList<>();
        client.add("ClickGui");
        client.add("CustomizeScreen");
        return client;
    }
}
