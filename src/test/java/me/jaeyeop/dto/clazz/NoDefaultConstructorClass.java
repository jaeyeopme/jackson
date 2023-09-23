package me.jaeyeop.dto.clazz;

public class NoDefaultConstructorClass {

    private final String name;

    private final int age;

    public NoDefaultConstructorClass(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "NoDefaultConstructorAndGetterClass{" + "name='" + name + '\'' + ", age=" + age + '}';
    }

}
