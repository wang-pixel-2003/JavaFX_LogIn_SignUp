package model;

import java.util.Objects;

/**
 * Clase MUser
 */
public class MUser {
    private String name;
    private String userName;
    private String password;
    private Integer ide;

    /**
     * Constructor de la clase MUser
     * @param name Nombre completo del usuario
     * @param userName Nombre de usuario
     * @param password Contraseña
     * @param ide Identificacion
     */
    public MUser(String name, String userName, String password, int ide) {
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.ide = ide;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIde() {
        return ide;
    }

    public void setIde(int ide) {
        this.ide = ide;
    }

    /**
     * Equals de la clase usuario comparando los usernames
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MUser)) return false;
        MUser usuario = (MUser) o;
        return getUserName().equals(usuario.getUserName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserName());
    }

    /**
     * ToString de la clase user
     * @return
     */
    @Override
    public String toString() {
        return "MUser{" +
                "name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", ide=" + ide +
                '}';
    }
}
