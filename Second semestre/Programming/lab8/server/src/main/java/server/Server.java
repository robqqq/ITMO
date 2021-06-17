package server;

import networkMessages.Request;
import networkMessages.Response;
import networkMessages.ResponseType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import requests.RequestReceiver;
import responses.ResponseSender;
import serverCommands.RequestHandler;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.channels.AsynchronousCloseException;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

public class Server {
    private final static Logger logger = LoggerFactory.getLogger(Server.class);
    private final ExecutorService receiveRequests = Executors.newFixedThreadPool(10);
    private final ExecutorService handleRequests = new ForkJoinPool(10);
    private final ExecutorService sendResponses = Executors.newFixedThreadPool(10);
    private final RequestReceiver requestReceiver;
    private final RequestHandler requestHandler;
    private final ResponseSender responseSender;


    public Server(RequestReceiver requestReceiver, RequestHandler requestHandler, ResponseSender responseSender) {
        this.requestReceiver = requestReceiver;
        this.requestHandler = requestHandler;
        this.responseSender = responseSender;
    }

    public void start(){
        Runnable receivingRequestRunnable = () -> {
            try {
                while (true) {
                    Request request = requestReceiver.receiveRequest();
                    if (!request.getCommand().equals("show")){
                        logger.debug("Request received: {}", request);
                    }
                    handleRequest(request, requestReceiver.getAddress());
                }
            } catch (AsynchronousCloseException ignored) {

            } catch (IOException | ClassNotFoundException e) {
                logger.debug("receive request error", e);
            }
        };
        receiveRequests.submit(receivingRequestRunnable);
    }

    private void handleRequest(Request request, SocketAddress address){
        Runnable handlingRequestRunnable = () -> {
            Response response = requestHandler.handleRequest(request);
            sendResponse(response, address);
        };
        handleRequests.submit(handlingRequestRunnable);
    }

    private void sendResponse(Response response, SocketAddress address){
        Runnable sendingResponseRunnable = () -> {
            if (!response.getContent().equals("disconnected")){
                responseSender.sendResponse(response, address);
            }
            if (response.getType() != ResponseType.UPDATE_COLLECTION_RESPONSE){
                logger.debug("Response sent: {}", response);
            }
        };
        sendResponses.submit(sendingResponseRunnable);
    }

    public void shutdownExecutorServices() {
        receiveRequests.shutdownNow();
        handleRequests.shutdownNow();
        sendResponses.shutdownNow();
    }
}
