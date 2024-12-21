package api;

import com.techsavvy.mindsmeet.entity.PostComments;
import com.techsavvy.mindsmeet.entity.PostFeedMst;
import com.techsavvy.mindsmeet.entity.PostLikes;
import ejb.PostBeanLocal;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

@Path("posts")
public class PostResource {

    @EJB
    private PostBeanLocal postBean;

    // GET /posts - View all posts
    @GET
    @Path("get-all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response viewPosts() {
        Collection<PostFeedMst> posts = postBean.viewPosts();
        return Response.ok(posts).build();
    }

    // POST /posts - Create a new post
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void doPost(PostFeedMst postFeedMst) {
        postBean.doPost(postFeedMst);
    }

    // PUT /posts/{postId}/like - Like a post
    @GET
    @Path("like/{postId}/{email}")
    public void likeOnPost(@PathParam("postId") Integer postId, @PathParam("email") String email) {
        postBean.likeOnPost(postId, email);
    }

    // POST /posts/{postId}/comments - Comment on a post
    @POST
    @Path("comment")
    @Consumes(MediaType.APPLICATION_JSON)
    public void doCommentOnPost(PostComments postComment) {
        postBean.doCommentOnPost(postComment);
    }

    // DELETE /posts/{postId} - Delete a post
    @DELETE
    @Path("delete/{postId}")
    public void deletePost(@PathParam("postId") Integer postId) {
        postBean.deletePost(postId);
    }

    // GET /posts/{postId} - Get a specific post by ID
    @GET
    @Path("get/{postId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPostById(@PathParam("postId") Integer postId) {
        PostFeedMst post = postBean.getPostById(postId);
        return Response.ok(post).build();
    }

    // GET /posts/{postId}/likes - Get all likes for a specific post
    @GET
    @Path("likes/{postId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPostLikes(@PathParam("postId") Integer postId) {
        Collection<PostLikes> likes = postBean.getPostLikes(postId);
        return Response.ok(likes).build();
    }

    // GET /posts/{postId}/comments - Get all comments for a specific post
    @GET
    @Path("comments/{postId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPostComments(@PathParam("postId") Integer postId) {
        Collection<PostComments> comments = postBean.getPostComments(postId);
        return Response.ok(comments).build();
    }
}
