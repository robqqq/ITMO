package responses;

import networkMessages.Response;

public interface ResponseFactory {

    Response createDefaultResponse(String content);

    Response createErrorResponse(String content);

    Response createNeedObjectResponse();

    Response createAuthErrorResponse(String content);
}
