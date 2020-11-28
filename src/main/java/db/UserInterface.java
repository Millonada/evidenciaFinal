package db;

import javax.swing.*;
import java.sql.SQLException;

public interface UserInterface {

    boolean create(User user,String table) throws SQLException;
    int delete(User user,String table) throws SQLException;
    int update(User user,String table) throws SQLException;


}
