package io.javasmithy.data;

import java.util.Objects;

public class Name {
    private String name;
    private char gender;

    public Name(String name, char gender){
        this.name = name;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name1 = (Name) o;
        return gender == name1.gender &&
                Objects.equals(name, name1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, gender);
    }

    public String toString(){
        return this.name;
    }

    public String toStringWithGender(){
        return this.name + ", " + this.gender;
    }
}
