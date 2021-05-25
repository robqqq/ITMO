package responses;

import networkMessages.Response;
import networkMessages.ResponseImpl;
import networkMessages.ResponseType;
import person.Person;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ServerResponseFactory implements ResponseFactory{

    @Override
    public Response createDefaultResponse(String content, Stream<Person> personStream) {
        return new ResponseImpl(ResponseType.DEFAULT_RESPONSE, content,
                personStream == null ? null : personStream.collect(Collectors.toSet()));
    }

    @Override
    public Response createErrorResponse(String content, Stream<Person> personStream) {
        return new ResponseImpl(ResponseType.ERROR_RESPONSE, content,
                personStream == null ? null : personStream.collect(Collectors.toSet()));
    }

    @Override
    public Response createNeedObjectResponse(Stream<Person> personStream) {
        return new ResponseImpl(ResponseType.NEED_OBJECT_RESPONSE, null,
                personStream == null ? null : personStream.collect(Collectors.toSet()));
    }

    @Override
    public Response createAuthErrorResponse(String content, Stream<Person> personStream) {
        return new ResponseImpl(ResponseType.AUTH_ERROR_RESPONSE, content,
                personStream == null ? null : personStream.collect(Collectors.toSet()));
    }
}
