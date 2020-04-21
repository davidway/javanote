package dto;

import java.util.Arrays;

public class UserDto {
    String email;
    String password;
    String[] skill;
    String sex;
    Integer sallary;
    String info;

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

    public String[] getSkill() {
        return skill;
    }

    public void setSkill(String[] skill) {
        this.skill = skill;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getSallary() {
        return sallary;
    }

    public void setSallary(Integer sallary) {
        this.sallary = sallary;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", skill=" + Arrays.toString(skill) +
                ", sex='" + sex + '\'' +
                ", sallary=" + sallary +
                ", info='" + info + '\'' +
                '}';
    }
}
