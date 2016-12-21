package data.dao.rmi.query;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import po.order.comment.CommentPo;

/**
 * Remote interface for {@code CommentQueryDao}
 * 
 * @see data.dao.query.CommentQueryDao
 * @author paranoia
 *
 */
/* final */
public interface RemoteCommentQueryDao extends Remote{
	public List<CommentPo> findByHotelId(int hotelId) throws RemoteException;
}
