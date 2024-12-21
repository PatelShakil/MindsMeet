/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb;

import com.techsavvy.mindsmeet.entity.CommunityMembers;
import com.techsavvy.mindsmeet.entity.CommunityMsg;
import com.techsavvy.mindsmeet.entity.CommunityMst;
import com.techsavvy.mindsmeet.entity.CommunityReply;
import com.techsavvy.mindsmeet.entity.Users;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import utils.Utils;

/**
 *
 * @author Acer
 */
@Stateless
public class CommunityBean implements CommunityBeanLocal {

    @PersistenceContext(unitName = "my_mindsmeet_pu")
    EntityManager em;

    @EJB
    UserBeanLocal ubl;

    @Override
    public void joinCommunity(Integer cId, Integer userId) {
        CommunityMembers cm = new CommunityMembers();
        Users u = new Users();
        u.setId(userId);
        cm.setUserId(u);
        CommunityMst c = new CommunityMst();
        c.setId(cId);
        cm.setCommunityId(c);
        Collection<CommunityMembers> members = new ArrayList<CommunityMembers>();
        members.add(cm);
        addMembersToCommunity(members);
    }

    @Override
    public void createCommunity(CommunityMst cm) {
        Users user = ubl.getUserByEmail(cm.getUserId().getEmail());
        cm.setUserId(user);
        cm.setIsActive(true);
        em.persist(cm);
    }

    @Override
    public void leftCommunity(Integer cId, Integer userId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void removeMemberToCommunity(Integer cmId, Integer cId) {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteCommunity(Integer cId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void postMsgInCommunity(CommunityMsg cms) {
        Users user = ubl.getUser(cms.getUserId().getId()).getObj();
        cms.setUserId(user);
        CommunityMst com = getCommunity(cms.getCommunityId().getId());
        cms.setCommunityId(com);
        em.persist(cms);
    }

    @Override
    public void replyToCommunityMsg(CommunityReply cr) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void addMembersToCommunity(Collection<CommunityMembers> cms) {
        cms.forEach((cm) -> {
            Users user = ubl.getUser(cm.getUserId().getId()).getObj();
            CommunityMst com = em.find(CommunityMst.class, cm.getCommunityId().getId());

            Boolean isAdded = !em.createQuery("select cm from CommunityMembers cm where cm.userId.id = :uid AND cm.communityId.id = :cid")
                    .setParameter("uid", user.getId())
                    .setParameter("cid", com.getId())
                    .getResultList().isEmpty();

            if (!isAdded) {
                cm.setCommunityId(com);
                cm.setUserId(user);
                em.persist(cm);
            }
        });

    }

    @Override
    public Collection<CommunityMsg> loadCommunityMsg(Integer cId) {
        Collection<CommunityMsg> msgs = em.createQuery("select msg from CommunityMsg msg where msg.communityId.id = :id")
                .setParameter("id", cId)
                .getResultList();
      
        return msgs;
    }

    @Override
    public Collection<CommunityMst> getJoinedCommunities(String email) {
        Users u = ubl.getUserByEmail(email);
        Collection<CommunityMst> com = em.createQuery("Select c from CommunityMst c where c.id in (select cm.communityId.id from CommunityMembers cm where cm.userId.id = :id)")
                .setParameter("id", u.getId())
                .getResultList();
        return com;

    }

    @Override
    public Collection<CommunityMst> getMyCommunities(String email) {
        Users u = ubl.getUserByEmail(email);
        Collection<CommunityMst> com = em.createQuery("select c from CommunityMst c where c.userId.id = :id")
                .setParameter("id", u.getId())
                .getResultList();
        return com;
    }

    @Override
    public Collection<CommunityMst> getAllCommunities() {
        Collection<CommunityMst> com = em.createNamedQuery("CommunityMst.findAll").getResultList();
        return com;
    }

    @Override
    public CommunityMst getCommunity(Integer id) {
        CommunityMst c = em.find(CommunityMst.class, id);
        return c;
    }

    @Override
    public Collection<CommunityMembers> getAllMembers(Integer id) {
        Collection<CommunityMembers> members = em.createQuery("select c from CommunityMembers c where c.communityId.id = :id")
                .setParameter("id", id)
                .getResultList();
        
        return members;
    }

}
