package co.edu.tdea.clinicapp.domain.model;

public final class Credentials {
    private static final String USERNAME_RX = "^[A-Za-z0-9]{1,15}$";
    private static final String PASSWORD_RX = "^(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z0-9]).{8,}$";

    private final String username;
    private final String password;

    public Credentials(String username, String password) {
        if (username == null || !username.matches(USERNAME_RX)) {
            throw new IllegalArgumentException("Nombre de usuario inválido: alfanumérico y máximo 15 caracteres.");
        }
        if (password == null || !password.matches(PASSWORD_RX)) {
            throw new IllegalArgumentException("Contraseña inválida: mínimo 8 caracteres, con mayúscula, número y carácter especial.");
        }
        this.username = username;
        this.password = password;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
}
