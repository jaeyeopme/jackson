package me.jaeyeop.dto.clazz;

public class NoDefaultConstructorAndWithGetterClass {

    private final String name;

    private final int age;

    public NoDefaultConstructorAndWithGetterClass(String name, int age) {
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
        return "NoDefaultConstructorAndWithGetterClass{" + "name='" + name + '\'' + ", age=" + age + '}';
    }

}
