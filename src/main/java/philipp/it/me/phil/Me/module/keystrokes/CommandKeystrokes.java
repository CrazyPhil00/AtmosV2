package philipp.it.me.phil.Me.module.keystrokes;

import net.minecraft.server.*;
import net.minecraft.command.*;
import philipp.it.me.phil.Me.Mod;

public class CommandKeystrokes extends CommandBase
{
    public String getName() {
        return "keystrokesmods";
    }
    
    public void execute(final MinecraftServer server, final ICommandSender sender, final String[] args) throws CommandException {
        Mod.openGui();
    }
    
    public String getUsage(final ICommandSender sender) {
        return "/keystrokesmod";
    }
    
    public int getRequiredPermissionLevel() {
        return 0;
    }
    
    public boolean canCommandSenderUseCommand(final ICommandSender sender) {
        return true;
    }
}
