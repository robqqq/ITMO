package networkMessages;

import person.Person;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

public class ResponseImpl implements Response, Serializable {
    private static final long serialVersionUID = -1158790976101708016L;
    private final ResponseType type;
    private final String content;
    private final Collection<Person> personCollection;

    public ResponseImpl(ResponseType type, String content, Collection<Person> personCollection) {
        this.type = type;
        this.content = content;
        this.personCollection = personCollection;
    }

    @Override
    public ResponseType getType() {
        return type;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public Collection<Person> getPersonCollection(){
        return personCollection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResponseImpl response = (ResponseImpl) o;
        return type == response.type && content.equals(response.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, content);
    }

    @Override
    public String toString() {
        return "ResponseImpl{" +
                "type=" + type +
                ", content='" + content + '\'' +
                '}';
    }
}