### Наследование

Наследование позволяет создавать новый класс на основе уже существующего, так называемого родительского класса или суперкласса. Новый класс, называемый дочерним или подклассом, наследует свойства и методы родительского класса.

Синтаксис наследования:
```java
class SubClass extends SuperClass {
    // Тело подкласса
}
```

Пример наследования:
```java
class Animal {
    public void sound() {
        System.out.println("Animal makes a sound");
    }
}

class Dog extends Animal {
    // Метод sound() унаследован от класса Animal
}
```

### Полиморфизм

Полиморфизм позволяет использовать объекты разных классов с одинаковым интерфейсом без информации о конкретном типе объекта. Это достигается с помощью переопределения методов в дочерних классах.

Переопределение методов:
```java
class SuperClass {
    public void method() {
        System.out.println("SuperClass method");
    }
}

class SubClass extends SuperClass {
    @Override
    public void method() {
        System.out.println("SubClass method");
    }
}
```

### Пример полиморфизма

```java
SuperClass obj1 = new SuperClass();
SubClass obj2 = new SubClass();
SuperClass obj3 = new SubClass();

obj1.method(); // Вывод: SuperClass method
obj2.method(); // Вывод: SubClass method
obj3.method(); // Вывод: SubClass method
```
