/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb;

import com.techsavvy.mindsmeet.entity.CommunityMembers;
import com.techsavvy.mindsmeet.entity.CommunityMsg;
import com.techsavvy.mindsmeet.entity.CommunityMst;
import com.techsavvy.mindsmeet.entity.CommunityReply;
import com.techsavvy.mindsmeet.entity.FaqAnswers;
import com.techsavvy.mindsmeet.entity.FaqMst;
import com.techsavvy.mindsmeet.entity.FaqScreenshot;
import com.techsavvy.mindsmeet.entity.FaqVotes;
import com.techsavvy.mindsmeet.entity.GroupMst;
import com.techsavvy.mindsmeet.entity.GroupUsers;
import com.techsavvy.mindsmeet.entity.MsgMst;
import com.techsavvy.mindsmeet.entity.NoteComments;
import com.techsavvy.mindsmeet.entity.NoteReplies;
import com.techsavvy.mindsmeet.entity.Notes;
import com.techsavvy.mindsmeet.entity.PostComments;
import com.techsavvy.mindsmeet.entity.PostFeedMst;
import com.techsavvy.mindsmeet.entity.UserSettings;
import com.techsavvy.mindsmeet.entity.Users;
import jakarta.annotation.security.DeclareRoles;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import org.glassfish.soteria.identitystores.hash.Pbkdf2PasswordHashImpl;
import utils.Resource;
import utils.Utils;

/**
 *
 * @author M.SHAKIL PATEL
 */
@Stateless
public class UserBean implements UserBeanLocal {

    @PersistenceContext(unitName = "my_mindsmeet_pu")
    EntityManager em;

    @Override
    public Resource<Users> doLogin(String email, String password) {
        Resource<Users> res = new Resource<>(null, "", false);
        try {
            // Fetch user by email
            List<Users> users = em.createNamedQuery("Users.findByEmail", Users.class)
                    .setParameter("email", email)
                    .getResultList();

            // Check if any user exists with the provided email
            if (!users.isEmpty()) {
                Users user = users.get(0);  // Fetch the first user

                // Check password match
                if (user.getPassword().equals(password)) {
                    res.setMessage("User Found");
                    res.setStatus(true);
                    res.setObj(user);
                } else {
                    res.setMessage("Invalid password!");
                    res.setStatus(false);
                }
            } else {
                res.setMessage("Email not found!");
                res.setStatus(false);
            }
        } catch (Exception ex) {
            // Handle unexpected exceptions and return proper error message
            res.setMessage("An error occurred: " + ex.getMessage());
            res.setStatus(false);
        } finally {
            // Return the Resource object, ensuring it always returns something
            return res;
        }
    }

    @Override
    public Response doSignup(Users user) {
        Resource<Users> res = new Resource(null, "", false);

        System.out.print(user);

        try {
            Pbkdf2PasswordHashImpl passwordHasher = new Pbkdf2PasswordHashImpl();
            
            String hashedPassword = passwordHasher.generate(user.getPassword().toCharArray());
            user.setPassword(hashedPassword);
            System.out.println(hashedPassword);
            em.persist(user); // Persist the new user into the database
            em.flush();
            Users u = em.find(Users.class, user.getId()); // Retrieve the persisted user using its ID
            UserSettings us = new UserSettings();
            us.setUser(u);
            us.setIsPrivate(false);
            em.persist(us);
            GroupUsers gu = new GroupUsers();
            GroupMst gm = em.find(GroupMst.class, 1);
            gu.setGroupId(gm);
            gu.setUserId(user);
            em.persist(gu);
        } catch (ConstraintViolationException e) {
            e.getConstraintViolations().forEach(err -> System.out.println(err.toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Response.ok().build();
    }

    @Override
    public Response updateSetting(UserSettings us) {

        try {
            em.merge(us);
            em.flush();
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @Override
    public Resource<UserSettings> getSetting(Integer id) {
        Resource<UserSettings> res = new Resource();
        System.out.println(id.toString());
        res.setObj(em.find(UserSettings.class, id));
        return res;
    }

    @Override
    public Response updateUser(Users user) {
        try {
            em.merge(user);
            em.flush();
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @Override
    public Response uploadNote(Notes note) {
        try {
            em.persist(note);
            note.getNotesTextCollection().forEach((p) -> {
                p.setNotesId(note);
                em.persist(p);
            });
            return Response.ok("Note Uploaded Successfully").build();
        } catch (Exception e) {
            return Utils.getCatch(e);

        }
    }

    @Override
    public Response viewNotes() {
        try {
            return Response.ok(em.createNamedQuery("Notes.findAll").getResultList()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @Override
    public Response doCommentOnNotes(String cmtText, Integer noteId, Integer userId) {
        Users user = em.find(Users.class, userId);
        Notes note = em.find(Notes.class, noteId);

        NoteComments nc = new NoteComments();
        nc.setCommentText(cmtText);
        nc.setNoteId(note);
        nc.setUserId(user);
        try {
            em.persist(nc);
            return Response.ok("Commented Successfully").build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @Override
    public Response getNoteComment(Integer id) {
        NoteComments nc = em.find(NoteComments.class, id);
        return Response.ok(nc).build();
    }

    @Override
    public Response replyNotesComments(NoteReplies nr) {
        try {
            em.persist(nr);
            return Response.ok("Reply Added!!!").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @Override
    public Response editNotes(Notes note) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Response deleteNote(Integer nId) {
        try {
            Notes note = em.find(Notes.class, nId);
            em.remove(note);
            return Response.ok().build();
        } catch (Exception e) {
            return Utils.getCatch(e);
        }
    }

  

    @Override
    public Response getAllUsers() {
        try {
            return Response.ok(em.createNamedQuery("Users.findAll").getResultList()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @Override
    public Resource<Users> getUser(Integer id) {
        Users user = em.find(Users.class, id);
        Resource res = new Resource(null, "", false);
        if (user != null) {
            res.setMessage("User found");
            res.setStatus(true);
            res.setObj(user);
        } else {
            res.setMessage("User Not Found");
            res.setStatus(false);
            res.setObj(null);
        }
        return res;
    }

    @Override
    public Response getNoteById(Integer id) {
        try {
            Notes note = em.find(Notes.class, id);
            if (note != null) {
                return Response.ok(note).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();

        }
    }

  

    @Override
    public Response getUserByUsername(String username) {
        try{
            Users user =(Users) em.createQuery("select u from Users u where u.username = :username").setParameter("username", username).getSingleResult();
            if(user != null){
                return Response.ok(user).build();
            }else{
                return Response.status(404).build();
            }
        }catch(Exception e){
            return Response.serverError().build();
        }
    }

    @Override
    public Users getUserByEmail(String email) {
        Users user =(Users) em.createNamedQuery("Users.findByEmail").setParameter("email", email).getSingleResult();
    return user;
    }

    @Override
    public Collection<Notes> getNotesForUsers(String email) {
        Users user = getUserByEmail(email);
        
        Collection<Notes> notes = em.createQuery("select n from Notes n where n.userId = :u").setParameter("u", user).getResultList();
        System.out.println(notes.toString());
        return notes;
    }

}
