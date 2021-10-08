package za.ac.cput.factory;

/**
 * LoginFactory.java
 * Factory class for the Login
 * Author: Jason Jaftha (217009301)
 * Date: 13 September 2021
 **/

import za.ac.cput.entity.Login;

public class LoginFactory {

    public static Login createLogin(String studentNumber, String username, String password)
    {
        return new Login.Builder().setStudentNo(studentNumber).setUsername(username).setPassword(password).build();
    }
}
