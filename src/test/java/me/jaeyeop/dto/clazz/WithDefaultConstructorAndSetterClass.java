package me.jaeyeop.dto.clazz;

public class WithDefaultConstructorAndSetterClass {

    private String name;

    private int age;

    private WithDefaultConstructorAndSetterClass() {
    }

    public WithDefaultConstructorAndSetterClass(String name, int age) {
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
        return "WithDefaultConstructorAndSetterClass{" + "name='" + name + '\'' + ", age=" + age + '}';
    }

}
