package philipp.it.me.phil.Me.module;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import philipp.it.me.phil.Me.Mod;
import philipp.it.me.phil.Me.module.exploits.Invisible;
import philipp.it.me.phil.Me.module.movement.*;
import philipp.it.me.phil.Me.module.player.FollowEntity;
import philipp.it.me.phil.Me.module.player.InventoryCleaner;
import philipp.it.me.phil.Me.module.player.NoFall;
import philipp.it.me.phil.Me.module.pvp.*;
import philipp.it.me.phil.Me.module.render.*;
import philipp.it.me.phil.Me.module.world.ScaffoldWalk;
import philipp.it.me.phil.Me.module.world.Tower;
import philipp.it.me.phil.Me.ui.clickgui.ClickGuiToggle;
import philipp.it.me.phil.Me.ui.customize.CustomizeScreenToggle;
import philipp.it.me.phil.Me.utils.ConfigHandler;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ModuleManager {

    public static ArrayList<Module> modules;

    public ModuleManager() {
        (modules = new ArrayList<Module>()).clear();
        //player
        modules.add(new NoFall());
        modules.add(new InventoryCleaner());
        modules.add(new Test());
        //render
        modules.add(new Backlook());
        modules.add(new Fullbright());
        modules.add(new Tracer());
        modules.add(new Esp());
        //exploits
        modules.add(new FollowEntity());
        modules.add(new Invisible());
        //movement
        modules.add(new Fly());
        modules.add(new FlySpeed());
        modules.add(new Sprint());
        modules.add(new LongJump());
        modules.add(new Speed());
        //combat
        modules.add(new Killaura());
        modules.add(new Criticals());
        modules.add(new AutoClicker());
        modules.add(new Aimbot());
        modules.add(new Reflex());
        //world
        modules.add(new ScaffoldWalk());
        modules.add(new Tower());
        //client
        modules.add(Mod.getInstance().clickGui = new ClickGuiToggle());
        modules.add(Mod.getInstance().customizeScreen = new CustomizeScreenToggle());



    }

    @SubscribeEvent
    public void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        /*for (Module m : modules) {
            if (m.getCategory() == Category.CLIENT) return;
            try {
                if (ConfigHandler.getBoolean("module", m.name)) m.toggle();
            } catch (AWTException e) {
                throw new RuntimeException(e);
            }
        }

         */
    }

    @SubscribeEvent
    public void onPlayerLeft(PlayerEvent.PlayerLoggedOutEvent event) {
        reloadModules();
    }

    public static void reloadModules() {
        for (Module m : ModuleManager.modules) {
            ConfigHandler.writeConfig("module", m.name, m.isToggled());
        }
    }

    public static Module getModulebyString(String name) {
        for (Module m : modules) {
            if (m.getName().equalsIgnoreCase(name)) {
                return m;
            }
        }
        return null;
    }

    public ArrayList<Module> getModuleList() {
        return modules;
    }

    public static List<Module> getModulesByCategory(Category c) {
        List<Module> modules = new ArrayList<Module>();
        for (Module m : ModuleManager.modules) {
            if (m.getCategory() == c) {
                modules.add(m);
            }
        }
        return modules;
    }


    public static Module getModulebyNumber(int module, Category category) {
        ArrayList<Module> modules = new ArrayList<>();

        int t = 0;
        for (Module m : ModuleManager.modules) {
            modules.add(t, m);
            t++;
        }


        return modules.get(module);

    }

    public static Category getCategorybyNumber(int category){
        ArrayList<Category> categories = new ArrayList<>();

        categories.add(0, Category.COMBAT);
        categories.add(1, Category.RENDER);
        categories.add(2, Category.WORLD);
        categories.add(3, Category.MOVEMENT);
        categories.add(4, Category.PLAYER);
        categories.add(5, Category.EXPLOITS);
        categories.add(6, Category.CLIENT);
        return categories.get(category);
    }


    public static Module getModule(Category c, int module){

        if (c == Category.COMBAT) {
            int t = 0;
            for (String sC : Modules.getCombatSection()) {
                if (t == module) {
                    return getModulebyString(sC);
                }
                t ++;
            }
        }

        if (c == Category.RENDER) {
            int t = 0;
            for (String sC : Modules.getRenderSection()) {
                if (t == module) {
                    return getModulebyString(sC);
                }
                t ++;
            }
        }

        if (c == Category.EXPLOITS) {
            int t = 0;
            for (String sC : Modules.getExploitSection()) {
                if (t == module) {
                    return getModulebyString(sC);
                }
                t ++;
            }
        }

        if (c == Category.PLAYER) {
            int t = 0;
            for (String sC : Modules.getPlayerSection()) {
                if (t == module) {
                    return getModulebyString(sC);
                }
                t ++;
            }
        }

        if (c == Category.WORLD) {
            int t = 0;
            for (String sC : Modules.getWorldSection()) {
                if (t == module) {
                    return getModulebyString(sC);
                }
                t ++;
            }
        }

        if (c == Category.MOVEMENT) {
            int t = 0;
            for (String sC : Modules.getMovementSection()) {
                if (t == module) {
                    return getModulebyString(sC);
                }
                t ++;
            }
        }

        if (c == Category.CLIENT) {
            int t =0;
            for (String sC : Modules.getClientSection()) {
                if (t == module) {
                    return getModulebyString(sC);
                }
                t ++;
            }
        }

        // if found nothing just give him a random one
        return modules.get(11);
    }
}
