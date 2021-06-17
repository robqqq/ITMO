package responses;

import networkMessages.Response;
import person.Person;

import java.util.stream.Stream;

public interface ResponseFactory {

    Response createDefaultResponse(String content, Stream<Person> personStream);

    Response createErrorResponse(String content, Stream<Person> personStream);

    Response createNeedObjectResponse(Stream<Person> personStream);

    Response createAuthErrorResponse(String content, Stream<Person> personStream);

    Response createUpdateCollectionResponse(Stream<Person> personStream);

    Response createAuthAcceptResponse(Stream<Person> personStream);
}
