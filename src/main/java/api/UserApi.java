/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/JerseyClient.java to edit this template
 */
package api;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import rest.MyRestFilter;

/**
 * Jersey REST client generated for REST resource:UserResource [v1]<br>
 * USAGE:
 * <pre>
 *        UserApi client = new UserApi();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Acer
 */
public class UserApi {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/MindsMeet/api";
    


//    public UserApi(String token) {
//        client = javax.ws.rs.client.ClientBuilder.newClient();
//        client.register (new MyRestFilter(token));  // This automatically uses the @Inject mechanism if needed
//        webTarget = client.target(BASE_URI).path("v1");
//
//    }

    public UserApi() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        client.register (new MyRestFilter());  // This automatically uses the @Inject mechanism if needed
        webTarget = client.target(BASE_URI).path("v1");

    }

    public <T> T doSignup(Object requestEntity, Class<T> responseType) throws ClientErrorException {
        return webTarget.path("user/signup").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), responseType);
    }

    public <T> T getAllUsers(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("all-users");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getAllNotes(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("note/get-all");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T doLogin(Object requestEntity, Class<T> responseType) throws ClientErrorException {
        return webTarget.path("user/login").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), responseType);
    }

    public <T> T updateSetting(Object requestEntity, Class<T> responseType) throws ClientErrorException {
        return webTarget.path("user/update").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), responseType);
    }

    public <T> T uploadNote(Object requestEntity, Class<T> responseType) throws ClientErrorException {
        return webTarget.path("note/upload").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), responseType);
    }

    public <T> T getUserByUsername(Class<T> responseType, String username) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("user/{0}", new Object[]{username}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getNotesById(Class<T> responseType, String id) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("note/{0}", new Object[]{id}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T doCommentOnNote(Object requestEntity, Class<T> responseType, String note_id, String user_id) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("note/comment/{0}/{1}", new Object[]{note_id, user_id})).request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), responseType);
    }

    public <T> T doCommentReply(Object requestEntity, Class<T> responseType, String comment_id, String user_id) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("note/comment/reply/{0}/{1}", new Object[]{comment_id, user_id})).request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), responseType);
    }

    public <T> T getAllPosts(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("post/get-all");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void close() {
        client.close();
    }
    
}
