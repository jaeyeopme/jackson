package me.jaeyeop.dto.clazz;

public class NoDefaultConstructorAndWithSetterClass {

    private String name;

    private int age;

    public NoDefaultConstructorAndWithSetterClass(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setAge(final int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "NoDefaultConstructorAndWithSetterClass{" + "name='" + name + '\'' + ", age=" + age + '}';
    }

}
