package me.jaeyeop.dto.clazz;

public class WithDefaultConstructorAndGetterClass {

    private String name;

    private int age;

    private WithDefaultConstructorAndGetterClass() {
    }

    public WithDefaultConstructorAndGetterClass(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "WithDefaultConstructorAndGetterClass{" + "name='" + name + '\'' + ", age=" + age + '}';
    }

}
