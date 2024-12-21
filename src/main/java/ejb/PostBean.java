/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb;

import com.techsavvy.mindsmeet.entity.PostComments;
import com.techsavvy.mindsmeet.entity.PostFeedMst;
import com.techsavvy.mindsmeet.entity.PostLikes;
import com.techsavvy.mindsmeet.entity.Users;
import java.util.Collection;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.Response;

/**
 *
 * @author Acer
 */
@Stateless
public class PostBean implements PostBeanLocal {

    @PersistenceContext(unitName = "my_mindsmeet_pu")
    EntityManager em;
    
    @EJB UserBeanLocal ubl;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public void doPost(PostFeedMst pfm) {
        Users user = ubl.getUserByEmail(pfm.getUserId().getEmail());
        pfm.setUserId(user);
        em.persist(pfm);
    }

    @Override
    public void likeOnPost(Integer postId, String email) {
        Users user = ubl.getUserByEmail(email); // Fetch user by email
        PostFeedMst post = getPostById(postId); // Fetch post by ID

        // Check if a "like" already exists
        PostLikes existingLike = em.createQuery(
                "SELECT pl FROM PostLikes pl WHERE pl.postId = :post AND pl.userId = :user", PostLikes.class)
                .setParameter("post", post)
                .setParameter("user", user)
                .getResultStream()
                .findFirst()
                .orElse(null);
        
        System.out.print(existingLike);

        if (existingLike != null) {
            // Unlike: Remove the existing "like"
            em.remove(existingLike);
        } else {
            // Like: Add a new "like"
            PostLikes like = new PostLikes();
            like.setPostId(post);
            like.setUserId(user);
            em.persist(like);
        }
        em.flush();

    }


    @Override
    public void doCommentOnPost(PostComments pc) {
        Users u = ubl.getUserByEmail(pc.getUserId().getEmail());
        PostFeedMst p = getPostById(pc.getPostId().getId());
        
        pc.setPostId(p);
        pc.setUserId(u);
        em.persist(pc);
    }

    @Override
    public void deletePost(Integer pId) {
        PostFeedMst p = getPostById(pId);

        em.remove(p);
    }

    @Override
    public Collection<PostFeedMst> viewPosts() {
        Collection<PostFeedMst> posts = em.createQuery("select p from PostFeedMst p order by p.createdAt DESC").getResultList();

        return posts;

    }

    @Override
    public PostFeedMst getPostById(Integer id) {
        PostFeedMst p = em.find(PostFeedMst.class, id);
        return p;
    }

    @Override
    public Collection<PostLikes> getPostLikes(Integer id) {
        Collection<PostLikes> likes = em.createQuery("select l from PostLikes l where l.postId.id = :id").setParameter("id", id).getResultList();
        return likes;
    }

    @Override
    public Collection<PostComments> getPostComments(Integer id) {
        Collection<PostComments> likes = em.createQuery("select l from PostComments l where l.postId.id = :id").setParameter("id", id).getResultList();
        return likes;

    }
}
