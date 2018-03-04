package cl.koritsu.im.data;

import cl.koritsu.im.domain.Usuario;
/**
 * QuickTickets Dashboard backend API.
 */
public interface DataProvider {

    /**
     * @param userName
     * @param password
     * @return Authenticated used.
     */
    Usuario authenticate(String userName, String password);

    /**
     * @return The number of unread notifications for the current user.
     */
    int getUnreadNotificationsCount();

     /**
     * @return The total summed up revenue of sold movie tickets
     */
    double getTotalSum();

  
}
