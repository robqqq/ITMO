package networkMessages;

import person.Person;

import java.util.Collection;

public interface Response {

    ResponseType getType();

    String getContent();

    Collection<Person> getPersonCollection();
}
