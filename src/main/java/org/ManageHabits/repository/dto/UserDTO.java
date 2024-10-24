package org.ManageHabits.repository.dto;

public class UserDTO {


    private String email;
    private String password;
    private String name;
    private int id;

//    @Default
//    @JsonCreator
//    public UserDTO(String email, String password, String name, int id) {
//        if (email == null || password == null || name == null) {
//            throw new IllegalArgumentException("Email, password and name cannot be null");
//        }
//
//        if (email.isEmpty() || password.isEmpty() || name.isEmpty()) {
//            throw new IllegalArgumentException("Email, имя и пароль не могут быть пустыми");
//        }
//
//        if (!email.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]+$")) {
//            throw new IllegalArgumentException("Неверный формат email, он должен содержать хотя бы один символ," +
//                    " символ '@', хотя бы один символ до и псоле точки");
//        }
//
//        this.name = name;
//        this.email = email;
//        this.password = password;
//        this.id = id;
//    }
//
//    @JsonCreator
//    public UserDTO(String email, String password, String name) {
//        this(email, password, name, 0);
//    }
//
//    @JsonCreator
//    public UserDTO(@JsonProperty("email") String email, @JsonProperty("password") String password) {
//        this(email, password, "", 0);
//    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO dto = (UserDTO) o;
        return id == dto.id && email.equals(dto.email) && password.equals(dto.password) && name.equals(dto.name);
    }
}
