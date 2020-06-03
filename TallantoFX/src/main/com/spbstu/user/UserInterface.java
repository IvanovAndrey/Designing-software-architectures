/**
 * Created by Azat on 30.03.2017.
 */

package main.com.spbstu.user;

import main.com.spbstu.exceptions.NotAuthenticatedException;

public interface UserInterface {
    void setId(int id_);
    int getId();
    User getUser();
    void checkAuthenticated() throws NotAuthenticatedException;
}
