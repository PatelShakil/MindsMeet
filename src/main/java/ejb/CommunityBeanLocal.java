/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package ejb;

import com.techsavvy.mindsmeet.entity.CommunityMembers;
import com.techsavvy.mindsmeet.entity.CommunityMsg;
import com.techsavvy.mindsmeet.entity.CommunityMst;
import com.techsavvy.mindsmeet.entity.CommunityReply;
import java.util.Collection;
import javax.ejb.Local;
import javax.ws.rs.core.Response;

/**
 *
 * @author Acer
 */
@Local
public interface CommunityBeanLocal {
    
    //community
    public void joinCommunity(Integer cId, Integer userId);

    public void createCommunity(CommunityMst cm);

    public void leftCommunity(Integer cId, Integer userId);

    public void removeMemberToCommunity(Integer cmId, Integer cId);

    public void deleteCommunity(Integer cId);

    public void postMsgInCommunity(CommunityMsg cms);

    public void replyToCommunityMsg(CommunityReply cr);

    public void addMembersToCommunity(Collection<CommunityMembers> cms);

    public Collection<CommunityMsg> loadCommunityMsg(Integer cId);
    
    public Collection<CommunityMst> getJoinedCommunities(String email);
    
    public Collection<CommunityMst> getMyCommunities(String email);
    
    public Collection<CommunityMst> getAllCommunities();
    
    public CommunityMst getCommunity(Integer id);
    
    public Collection<CommunityMembers> getAllMembers(Integer id);
}
