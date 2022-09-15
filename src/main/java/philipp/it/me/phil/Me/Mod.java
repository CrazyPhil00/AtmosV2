package philipp.it.me.phil.Me;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.command.ICommand;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import philipp.it.me.phil.Me.commands.TestCommand;
import philipp.it.me.phil.Me.events.Events;
import philipp.it.me.phil.Me.listener.EventKey;
import philipp.it.me.phil.Me.module.Category;
import philipp.it.me.phil.Me.module.Module;
import philipp.it.me.phil.Me.module.ModuleManager;
import philipp.it.me.phil.Me.module.keystrokes.KeystrokesSettings;
import philipp.it.me.phil.Me.module.keystrokes.render.GuiScreenKeystrokes;
import philipp.it.me.phil.Me.module.keystrokes.render.KeystrokesRenderer;
import philipp.it.me.phil.Me.module.player.FollowEntity;
import philipp.it.me.phil.Me.module.player.NoFall;
import philipp.it.me.phil.Me.module.pvp.Killaura;
import philipp.it.me.phil.Me.module.render.TabGUI;
import philipp.it.me.phil.Me.module.render.TabGUIToggle;
import philipp.it.me.phil.Me.ui.Hud;
import philipp.it.me.phil.Me.ui.clickgui.ClickGuiToggle;
import philipp.it.me.phil.Me.ui.customize.Colors;
import philipp.it.me.phil.Me.ui.customize.CustomizeScreenToggle;
import philipp.it.me.phil.Me.utils.ConfigHandler;
import philipp.it.me.phil.Me.utils.PlayerTickHandler;
import philipp.it.me.phil.Me.utils.Reference;
import philipp.it.me.phil.Me.utils.RenderWorldLastHandler;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@net.minecraftforge.fml.common.Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)

public class Mod {


    public static String MODID = Reference.MOD_ID;
    public static ModuleManager moduleManager;
    public static Hud hud;
    public ClickGuiToggle clickGui;
    public CustomizeScreenToggle customizeScreen;

    @net.minecraftforge.fml.common.Mod.Instance
    public static Mod instance;




    @net.minecraftforge.fml.common.Mod.EventHandler
    public void PreInit(FMLPreInitializationEvent event) {
        Display.setTitle("loading Atmos Client ...");
        // Display.setLocation(5 , 5);
        Display.setInitialBackground(10,0,10);


    }

    @net.minecraftforge.fml.common.Mod.EventHandler
    public void init (FMLInitializationEvent event) throws AWTException {
        MinecraftForge.EVENT_BUS.register(instance);
        MinecraftForge.EVENT_BUS.register(new Hud());
        MinecraftForge.EVENT_BUS.register(new Killaura());
        MinecraftForge.EVENT_BUS.register(new NoFall());
        MinecraftForge.EVENT_BUS.register(new PlayerTickHandler());
        MinecraftForge.EVENT_BUS.register(new RenderWorldLastHandler());
        MinecraftForge.EVENT_BUS.register(new ModuleManager());


        TabGUIToggle tabGUIToggle = new TabGUIToggle();
        tabGUIToggle.setToggled(true);
        MinecraftForge.EVENT_BUS.register(new FollowEntity());

    }

    @net.minecraftforge.fml.common.Mod.EventHandler
    public void PostInit(FMLPostInitializationEvent event) {
        Display.setTitle("Atmos Client");

        /*for (Module m : ModuleManager.modules) {
            if (m.name.equalsIgnoreCase("ClickGui") || m.name.equalsIgnoreCase("Customize Screen")) return;
            try {
                m.setToggled(ConfigHandler.getBoolean("module", m.name));
            } catch (AWTException e) {
                throw new RuntimeException(e);
            }

        }

         */

    }


    public static void onEvent(Events e) {
        for (Module m : ModuleManager.modules) {
            if (!m.isToggled())
                m.onEvent(e);
        }
    }


    @SubscribeEvent
    public void key(InputEvent.KeyInputEvent event) {
        if (Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null) return;

        try {
            if (Keyboard.isCreated()) {
                if (Keyboard.getEventKeyState()) {

                    int keyCode = Keyboard.getEventKey();
                    if (keyCode <= 0) return;
                    keyPress(keyCode);
                    for (Module m : ModuleManager.modules) {
                        if (m.getKey() == keyCode && keyCode>= 0) {
                            m.toggle();
                        }
                    }
                }
            }

        }catch (Exception q) {
            q.printStackTrace();
        }

    }

    public static List<Module> getModuleByCategory(Category c) {
        List<Module> modules = new ArrayList<>();

        for (Module m : modules) {
            if (m.getCategory() == c) {
                modules.add(m);
            }
        }
        return modules;
    }

    public static void keyPress(int key) {
        onEvent(new EventKey(key));
    }

    //load config
    @net.minecraftforge.fml.common.Mod.EventHandler
    public void onInitialization(FMLPostInitializationEvent event) {


        Hud.modulesPosX = ConfigHandler.getInt("Modules", "posX");
        Hud.modulesPosY = ConfigHandler.getInt("Modules", "posY");

        Hud.FpsX = ConfigHandler.getInt("Fps", "posX");
        Hud.FpsY = ConfigHandler.getInt("Fps", "posY");

        TabGUI.guiPosX = ConfigHandler.getInt("TabGui", "posX");
        TabGUI.guiPosY = ConfigHandler.getInt("TabGui", "posY");

        Hud.CordX = ConfigHandler.getInt("Coord", "posX");
        Hud.CordY = ConfigHandler.getInt("Coord", "posY");


    }

    @net.minecraftforge.fml.common.Mod.EventHandler
    public void init(FMLPreInitializationEvent event) {
        moduleManager = new ModuleManager();


        if (!ConfigHandler.hasCategory("TabGui") && !ConfigHandler.hasCategory("Fps") && !ConfigHandler.hasCategory("Coord") && !ConfigHandler.hasCategory("Modules") && !ConfigHandler.hasCategory("Colors")) {
            //Positions
            ConfigHandler.writeConfig("TabGui", "posX", 10);
            ConfigHandler.writeConfig("TabGui", "posY", 35);

            ConfigHandler.writeConfig("Fps", "posX", 5);
            ConfigHandler.writeConfig("Fps", "posY", 565);

            ConfigHandler.writeConfig("Coord", "posX", 5);
            ConfigHandler.writeConfig("Coord", "posY", 583);

            ConfigHandler.writeConfig("Modules", "posX", 0);
            ConfigHandler.writeConfig("Modules", "posY", 0);

            //Colors
            ConfigHandler.writeConfig("TextColor", "White" , "255,255,255");

            ConfigHandler.writeConfig("TextColor", "Red" , "255,0,0");

            ConfigHandler.writeConfig("TextColor", "Green" , "0,255,0");

            ConfigHandler.writeConfig("TextColor", "Blue" , "0,0,255");

            ConfigHandler.writeConfig("TextColor", "Yellow" , "0,255,255");

            ConfigHandler.writeConfig("TextColor", "Orange" , "255,100,0");

            ConfigHandler.writeConfig("TextColor", "Turquoise" , "0,255,234");

            ConfigHandler.writeConfig("TextColor", "Purple" , "100,0,255");

            ConfigHandler.writeConfig("TextColor", "Pink" , "255,0,255");

            ConfigHandler.writeConfig("BackGroundColor", "Background" , "0x30000000");

            ConfigHandler.writeConfig("Color", "Tabgui", 7);

            ConfigHandler.writeConfig("Color", "Module", 7);

            ConfigHandler.writeConfig("Color", "Fps", 7);

            ConfigHandler.writeConfig("Color", "Coord", 7);


            ConfigHandler.writeConfig("ClickGui", "Combat-X", 50);
            ConfigHandler.writeConfig("ClickGui", "Combat-Y", 50);

            ConfigHandler.writeConfig("ClickGui", "Render-X", 50);
            ConfigHandler.writeConfig("ClickGui", "Render-Y", 150);

            ConfigHandler.writeConfig("ClickGui", "Player-X", 50);
            ConfigHandler.writeConfig("ClickGui", "Player-Y", 250);

            ConfigHandler.writeConfig("ClickGui", "World-X", 50);
            ConfigHandler.writeConfig("ClickGui", "World-Y", 350);

            ConfigHandler.writeConfig("ClickGui", "Movement-X", 50);
            ConfigHandler.writeConfig("ClickGui", "Movement-Y", 450);

            ConfigHandler.writeConfig("ClickGui", "Exploits-X", 50);
            ConfigHandler.writeConfig("ClickGui", "Exploits-Y", 550);

            ConfigHandler.writeConfig("ClickGui", "Client-X", 50);
            ConfigHandler.writeConfig("ClickGui", "Client-Y", 650);


            for (Module m : ModuleManager.modules) {
                if (m.getCategory().equals(Category.CLIENT)) return;
                ConfigHandler.writeConfig("module", m.name, m.isToggled());
            }


        }
        Colors.loadColors();

    }


    //todo Keystrokes

    private static KeystrokesSettings settings;
    private static KeystrokesRenderer renderer;
    private static boolean openGui;

    @net.minecraftforge.fml.common.Mod.EventHandler
    public void initt(final FMLInitializationEvent event) throws IOException {
        //settings = new KeystrokesSettings(new File(Minecraft.getMinecraft().gameDir, "keystrokes.dat"));
        //renderer = new KeystrokesRenderer();
        //settings.load();
        ClientCommandHandler.instance.registerCommand(new TestCommand());
        //ClientCommandHandler.instance.registerCommand((ICommand)new CommandKeystrokes());
        //MinecraftForge.EVENT_BUS.register(new KeystrokesRenderer());
    }



    @SubscribeEvent
    public void onClientTick(final TickEvent.ClientTickEvent event) {
        if (openGui) {
            Minecraft.getMinecraft().displayGuiScreen(new GuiScreenKeystrokes());
            openGui = false;
        }
    }

    public static KeystrokesSettings getSettings() {
        return settings;
    }

    public static KeystrokesRenderer getRenderer() {
        return renderer;
    }

    public static void openGui() {
        openGui = true;
    }

    public static Mod getInstance() {
        return instance;
    }
}

