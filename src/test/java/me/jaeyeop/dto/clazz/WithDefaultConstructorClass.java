package me.jaeyeop.dto.clazz;

public class WithDefaultConstructorClass {

    private String name;

    private int age;

    private WithDefaultConstructorClass() {
    }

    public WithDefaultConstructorClass(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "WithDefaultConstructorAndNoSetterClass{" + "name='" + name + '\'' + ", age=" + age + '}';
    }

}
