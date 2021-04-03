package serverCommands;

import exceptions.NoSuchCommandException;
import messages.Messenger;

import java.util.Set;

public class HelpCommand implements ServerCommand{
    private Set<String> commands;
    private Messenger messenger;

    public HelpCommand(Set<String> commands, Messenger messenger){
        this.commands = commands;
        this.messenger = messenger;
    }

    @Override
    public String execute() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(messenger.getMsg("helpOutput"))
                .append(":\n");
        commands.stream().forEach(command -> stringBuilder.append(messenger.getMsg(command + "Description")).append("\n"));
        return stringBuilder.toString();
//        for (String commandName: commands){
//            try {
//                outputManager.printMsg(messenger.getCommandDescription(commandName) + "\n");
//            } catch (NoSuchCommandException e) {
//                outputManager.printErrorMsg(e.getMessage() + "\n");
//            }
//        }
    }
}
