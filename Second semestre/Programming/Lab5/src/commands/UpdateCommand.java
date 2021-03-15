package commands;

import collectionManager.CollectionManager;
import collectionManager.PersonIdManager;
import exceptions.InvalidArgumentTypeException;
import exceptions.NoArgException;
import input.InputManager;
import messages.Messenger;
import output.OutputManager;
import person.Person;

import java.util.logging.Level;
import static log.Log.logger;

/**
 * Класс команды, которая обновляет значение элемента коллекции, id которого равен заданному
 */
public class UpdateCommand implements Command, RequiringArg<Integer>{
    private CollectionManager collectionManager;
    private Messenger messenger;
    private InputManager inputManager;
    private OutputManager outputManager;
    private int arg;

    public UpdateCommand(CollectionManager collectionManager, Messenger messenger, InputManager inputManager, OutputManager outputManager){
        this.collectionManager = collectionManager;
        this.messenger = messenger;
        this.inputManager = inputManager;
        this.outputManager = outputManager;
    }

    /**
     * Метод, который запускает команду
     */
    @Override
    public void execute() {
        if (!PersonIdManager.getInstance().idIsFree(arg)){
            PersonIdManager.getInstance().removeId(arg);
            Person oldPerson = collectionManager.getPersonStream().filter(person -> person.getId() == arg).findAny().get();
            Person person = inputManager.readPerson(oldPerson.getId(), oldPerson.getCreationDate());
            collectionManager.removeElement(arg);
            collectionManager.addElement(person);
        } else {
            outputManager.printErrorMsg(messenger.getExceptionMsg("noSuchId") + "\n");
        }
    }

    @Override
    public void setArg(Integer arg) {
        this.arg = arg;
    }

    @Override
    public void acceptInvoker(CommandInvoker commandInvoker) throws NoArgException, InvalidArgumentTypeException {
        commandInvoker.setIntegerArg(this);
        commandInvoker.invokeCommand(this);
    }
}
