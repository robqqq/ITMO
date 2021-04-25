package serverCommands;

import messages.Messenger;

import java.util.Set;

public class HelpCommand implements ServerCommand{
    private final Set<String> commands;
    private final Messenger messenger;

    public HelpCommand(Set<String> commands, Messenger messenger) {
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
    }


}
