package ru.nippyfox.javanavigator.db

import android.content.Context
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.nippyfox.javanavigator.data.Lecture
import ru.nippyfox.javanavigator.data.Question
import ru.nippyfox.javanavigator.data.Test

fun populateDatabase(database: AppDatabase, context: Context) {
    Log.d("DbInit", "Populating database")

    val lectureDao = database.lectureDao()
    val questionDao = database.questionDao()
    val testDao = database.testDao()

    fun readAssetFile(fileName: String): String {
        return context.assets.open(fileName).bufferedReader().use { it.readText() }
    }

    // Выполнение операций в фоновом потоке
    CoroutineScope(Dispatchers.IO).launch {
        // Добавление лекций в базу данных
        val lecture1 = Lecture(
            title = "Введение в программирование и Java",
            content = readAssetFile("lesson1.md")
        )
        val lecture2 = Lecture(
            title = "Установка и настройка среды разработки (JDK и IDE)",
            content = readAssetFile("lesson2.md")
        )
        val lecture3 = Lecture(
            title = "Основы синтаксиса Java",
            content = readAssetFile("lesson3.md")
        )
        val lecture4 = Lecture(
            title = "Переменные и типы данных",
            content = readAssetFile("lesson4.md")
        )
        val lecture5 = Lecture(
            title = "Операторы и выражения",
            content = readAssetFile("lesson5.md")
        )
        val lecture6 = Lecture(
            title = "Управляющие конструкции (if, switch)",
            content = readAssetFile("lesson6.md")
        )
        val lecture7 = Lecture(
            title = "Циклы (for, while, do-while)",
            content = readAssetFile("lesson7.md")
        )
        val lecture8 = Lecture(
            title = "Массивы и строки",
            content = readAssetFile("lesson8.md")
        )
        val lecture9 = Lecture(
            title = "Методы",
            content = readAssetFile("lesson9.md")
        )
        val lecture10 = Lecture(
            title = "Классы и объекты",
            content = readAssetFile("lesson10.md")
        )
        val lecture11 = Lecture(
            title = "Наследование и полиморфизм",
            content = readAssetFile("lesson11.md")
        )
        val lecture12 = Lecture(
            title = "Интерфейсы и абстрактные классы",
            content = readAssetFile("lesson12.md")
        )
        val lecture13 = Lecture(
            title = "Обработка исключений",
            content = readAssetFile("lesson13.md")
        )
        val lecture14 = Lecture(
            title = "Коллекции",
            content = readAssetFile("lesson14.md")
        )
        val lecture15 = Lecture(
            title = "Работа с файлами",
            content = readAssetFile("lesson15.md")
        )

        lectureDao.insertLecture(lecture1)
        lectureDao.insertLecture(lecture2)
        lectureDao.insertLecture(lecture3)
        lectureDao.insertLecture(lecture4)
        lectureDao.insertLecture(lecture5)
        lectureDao.insertLecture(lecture6)
        lectureDao.insertLecture(lecture7)
        lectureDao.insertLecture(lecture8)
        lectureDao.insertLecture(lecture9)
        lectureDao.insertLecture(lecture10)
        lectureDao.insertLecture(lecture11)
        lectureDao.insertLecture(lecture12)
        lectureDao.insertLecture(lecture13)
        lectureDao.insertLecture(lecture14)
        lectureDao.insertLecture(lecture15)

        // Добавление тестов в базу данных
        val test1 = Test(title = "Введение в программирование и Java")
        val testId1 = testDao.insertTest(test1)

        val test2 = Test(title = "Установка и настройка среды разработки (JDK и IDE)")
        val testId2 = testDao.insertTest(test2)

        val test3 = Test(title = "Основы синтаксиса Java")
        val testId3 = testDao.insertTest(test3)

        val test4 = Test(title = "Переменные и типы данных")
        val testId4 = testDao.insertTest(test4)

        val test5 = Test(title = "Операторы и выражения")
        val testId5 = testDao.insertTest(test5)

        val test6 = Test(title = "Управляющие конструкции (if, switch)")
        val testId6 = testDao.insertTest(test6)

        val test7 = Test(title = "Циклы (for, while, do-while)")
        val testId7 = testDao.insertTest(test7)

        val test8 = Test(title = "Массивы и строки")
        val testId8 = testDao.insertTest(test8)

        val test9 = Test(title = "Методы")
        val testId9 = testDao.insertTest(test9)

        val test10 = Test(title = "Классы и объекты")
        val testId10 = testDao.insertTest(test10)

        val test11 = Test(title = "Наследование и полиморфизм")
        val testId11 = testDao.insertTest(test11)

        val test12 = Test(title = "Интерфейсы и абстрактные классы")
        val testId12 = testDao.insertTest(test12)

        val test13 = Test(title = "Обработка исключений")
        val testId13 = testDao.insertTest(test13)

        val test14 = Test(title = "Коллекции")
        val testId14 = testDao.insertTest(test14)

        val test15 = Test(title = "Работа с файлами")
        val testId15 = testDao.insertTest(test15)

        // Добавление вопросов в тесты
        val question1n1 = Question(
            testId = testId1.toInt(),
            question = "Что из перечисленного НЕ является ключевой особенностью языка программирования Java?",
            options = listOf("Платформонезависимость", "Объектно-ориентированность", "Низкоуровневое управление памятью", "Безопасность"),
            correctAnswerIndex = 2
        )
        val question1n2 = Question(
            testId = testId1.toInt(),
            question = "Как называется принцип, согласно которому код, написанный на Java, может выполняться на любом устройстве с установленной JVM?",
            options = listOf("Write Once, Compile Anywhere", "Write Once, Run Anywhere", "Compile Once, Run Anywhere", "Code Once, Execute Anywhere"),
            correctAnswerIndex = 1
        )
        val question1n3 = Question(
            testId = testId1.toInt(),
            question = "Что выполняет следующая строка кода в Java?\n\n" +
                    "System.out.println(\"Hello, World!\");",
            options = listOf("Объявляет класс с именем \"Hello, World!\"", "Запускает программу \"Hello, World!\"", "Выводит на консоль строку \"Hello, World!\"", "Сохраняет строку \"Hello, World!\" в файл"),
            correctAnswerIndex = 2
        )
        val question2n1 = Question(
            testId = testId2.toInt(),
            question = "Что входит в состав Java Development Kit (JDK)?",
            options = listOf("Только Java Runtime Environment (JRE)", "Компилятор, JRE и инструменты для отладки и профилирования", "Только компилятор", "Только инструменты для отладки и профилирования"),
            correctAnswerIndex = 1
        )
        val question2n2 = Question(
            testId = testId2.toInt(),
            question = "Какая команда используется для проверки успешной установки JDK?",
            options = listOf("java --check", "javac --version", "java -version", "jdk --verify"),
            correctAnswerIndex = 2
        )
        val question3n1 = Question(
            testId = testId3.toInt(),
            question = "Что представляет собой класс в Java?",
            options = listOf("Экземпляр объекта", "Шаблон, описывающий свойства и поведение объекта", "Функция для выполнения определенной задачи", "Переменная для хранения данных"),
            correctAnswerIndex = 1
        )
        val question3n2 = Question(
            testId = testId3.toInt(),
            question = "Как объявить переменную типа int с именем myNumber, содержащую значение 5?",
            options = listOf("int myNumber = 5;", "myNumber int = 5;", "myNumber = 5;", "int 5 = myNumber;"),
            correctAnswerIndex = 0
        )
        val question3n3 = Question(
            testId = testId3.toInt(),
            question = "Какие из перечисленных являются примитивными типами данных в Java?",
            options = listOf("String, Double", "int, double", "Object, Boolean", "Array, Float"),
            correctAnswerIndex = 1
        )
        val question3n4 = Question(
            testId = testId3.toInt(),
            question = "Что выведет следующий код?\n\n" +
                    "int a = 10;\n" +
                    "int b = 5;\n" +
                    "System.out.println(a + b);",
            options = listOf("10", "5", "15", "\"10 + 5\""),
            correctAnswerIndex = 2
        )
        val question3n5 = Question(
            testId = testId3.toInt(),
            question = "Какая управляющая конструкция используется для выполнения блока кода, если условие истинно?",
            options = listOf("for", "if", "while", "switch"),
            correctAnswerIndex = 1
        )
        val question3n6 = Question(
            testId = testId3.toInt(),
            question = "Какой оператор используется для проверки, является ли значение переменной x больше 10?",
            options = listOf("==", "!=", ">", "<"),
            correctAnswerIndex = 2
        )
        val question3n7 = Question(
            testId = testId3.toInt(),
            question = "Какой цикл продолжает выполнение до тех пор, пока условие истинно и проверяется после каждой итерации?",
            options = listOf("for", "while", "do-while", "repeat"),
            correctAnswerIndex = 2
        )
        val question3n8 = Question(
            testId = testId3.toInt(),
            question = "Какой из следующих операторов используется для прерывания выполнения цикла и перехода к следующей итерации?",
            options = listOf("break", "continue", "return", "exit"),
            correctAnswerIndex = 1
        )
        val question3n9 = Question(
            testId = testId3.toInt(),
            question = "Как объявить массив целых чисел с именем myArray в Java?",
            options = listOf("int myArray[];", "myArray int[];", "int[] myArray;", "myArray = new int[];"),
            correctAnswerIndex = 2
        )
        val question3n10 = Question(
            testId = testId3.toInt(),
            question = "Какой из следующих вариантов правильно объявляет метод с именем myMethod, не принимающий аргументов и не возвращающий значение?",
            options = listOf("void myMethod() {}", "void myMethod {}", "myMethod void() {}", "myMethod() {}"),
            correctAnswerIndex = 0
        )
        val question4n1 = Question(
            testId = testId4.toInt(),
            question = "Что представляет собой переменная в Java?",
            options = listOf("Объект", "Массив", "Именованное место в памяти для хранения данных", "Функция"),
            correctAnswerIndex = 2
        )
        val question4n2 = Question(
            testId = testId4.toInt(),
            question = "Какой из следующих типов данных является примитивным в Java?",
            options = listOf("String", "int", "MyClass", "Double"),
            correctAnswerIndex = 1
        )
        val question4n3 = Question(
            testId = testId4.toInt(),
            question = "Как объявить переменную типа double с именем myDouble, содержащую значение 3.14?",
            options = listOf("double myDouble = 3.14;", "myDouble double = 3.14;", "double = myDouble 3.14;", "myDouble = 3.14;"),
            correctAnswerIndex = 0
        )
        val question4n4 = Question(
            testId = testId4.toInt(),
            question = "Какой тип данных используется для хранения символов в Java?",
            options = listOf("int", "char", "String", "boolean"),
            correctAnswerIndex = 1
        )
        val question4n5 = Question(
            testId = testId4.toInt(),
            question = "Как объявить константу PI с значением 3.14159 в Java?",
            options = listOf("const PI = 3.14159;", "PI = 3.14159;", "final double PI = 3.14159;", "constant double PI = 3.14159;"),
            correctAnswerIndex = 2
        )
        val question5n1 = Question(
            testId = testId5.toInt(),
            question = "Что представляют собой операторы в Java?",
            options = listOf("Отдельные переменные", "Символы, используемые для выполнения операций над данными", "Функции для выполнения математических операций", "Типы данных"),
            correctAnswerIndex = 1
        )
        val question5n2 = Question(
            testId = testId5.toInt(),
            question = "Какой оператор используется для выполнения операции деления в Java?",
            options = listOf("/", "%", "//"),
            correctAnswerIndex = 0
        )
        val question5n3 = Question(
            testId = testId5.toInt(),
            question = "Что представляет собой выражение в Java?",
            options = listOf("Набор операторов", "Набор переменных", "Комбинацию операндов и операторов, которая вычисляется в некоторое значение", "Любое значение"),
            correctAnswerIndex = 2
        )
        val question5n4 = Question(
            testId = testId5.toInt(),
            question = "Какой оператор используется для увеличения значения переменной на 1 в Java?",
            options = listOf("+1", "++", "--", "+=1"),
            correctAnswerIndex = 1
        )
        val question5n5 = Question(
            testId = testId5.toInt(),
            question = "Какие операторы имеют более высокий приоритет в Java?",
            options = listOf("Арифметические", "Логические", "Присваивания"),
            correctAnswerIndex = 0
        )
        val question6n1 = Question(
            testId = testId6.toInt(),
            question = "Что делает конструкция if в Java?",
            options = listOf("Выполняет блок кода только если определенное условие истинно", "Выполняет блок кода, если условие ложно", "Выполняет блок кода несмотря на условие", "Определяет значение переменной"),
            correctAnswerIndex = 0
        )
        val question6n2 = Question(
            testId = testId6.toInt(),
            question = "Какое ключевое слово используется для указания альтернативного блока кода в конструкции if, если условие не выполняется??",
            options = listOf("switch", "case", "else", "break"),
            correctAnswerIndex = 2
        )
        val question6n3 = Question(
            testId = testId6.toInt(),
            question = "Какая конструкция используется для проверки нескольких условий последовательно?",
            options = listOf("if", "else if", "switch", "default"),
            correctAnswerIndex = 1
        )
        val question6n4 = Question(
            testId = testId6.toInt(),
            question = "Что делает оператор break в конструкции switch?",
            options = listOf("Продолжает выполнение следующего блока кода", "Прерывает выполнение текущего блока кода и выходит из конструкции", "Переходит к следующему блоку кода без выполнения текущего", "Не имеет отношения к конструкции switch"),
            correctAnswerIndex = 1
        )
        val question6n5 = Question(
            testId = testId6.toInt(),
            question = "Какой оператор используется внутри конструкции switch для указания возможных значений переменной?",
            options = listOf("if", "case", "else", "while"),
            correctAnswerIndex = 1
        )
        val question6n6 = Question(
            testId = testId6.toInt(),
            question = "Что произойдет, если ни один из операторов case не соответствует значению переменной в конструкции switch?",
            options = listOf("Выполнится оператор default, если он присутствует", "Программа завершится с ошибкой", "Выполнится первый оператор case", "Программа пропустит конструкцию switch и перейдет к следующей"),
            correctAnswerIndex = 0
        )
        val question7n1 = Question(
            testId = testId7.toInt(),
            question = "Какая конструкция используется для выполнения блока кода определенное количество раз?",
            options = listOf("if", "for", "switch", "else"),
            correctAnswerIndex = 1
        )
        val question7n2 = Question(
            testId = testId7.toInt(),
            question = "В каком цикле условие проверяется перед выполнением блока кода?",
            options = listOf("for", "while", "do-while", "switch"),
            correctAnswerIndex = 1
        )
        val question7n3 = Question(
            testId = testId7.toInt(),
            question = "Какая конструкция цикла выполняет блок кода хотя бы один раз, даже если условие ложно?",
            options = listOf("for", "while", "do-while", "if"),
            correctAnswerIndex = 2
        )
        val question7n4 = Question(
            testId = testId7.toInt(),
            question = "Какой оператор используется для прерывания выполнения цикла и выхода из него?",
            options = listOf("continue", "exit", "break", "return"),
            correctAnswerIndex = 2
        )
        val question7n5 = Question(
            testId = testId7.toInt(),
            question = "Какой из циклов позволяет определить переменные для использования внутри цикла?",
            options = listOf("while", "do-while", "for", "switch"),
            correctAnswerIndex = 2
        )
        val question7n6 = Question(
            testId = testId7.toInt(),
            question = "Какая конструкция используется для пропуска текущей итерации цикла и перехода к следующей итерации?",
            options = listOf("skip", "next", "jump", "continue"),
            correctAnswerIndex = 3
        )
        val question8n1 = Question(
            testId = testId8.toInt(),
            question = "Что представляет собой массив в Java?",
            options = listOf("Упорядоченный набор различных типов данных", "Упорядоченный набор элементов одного типа данных", "Неупорядоченный набор элементов", "Упорядоченный набор элементов разных типов данных"),
            correctAnswerIndex = 1
        )
        val question8n2 = Question(
            testId = testId8.toInt(),
            question = "Как создаётся массив в Java?",
            options = listOf("С помощью ключевого слова array", "С помощью ключевого слова new и указания типа данных элементов массива", "С помощью ключевого слова int[]", "С помощью ключевого слова list"),
            correctAnswerIndex = 1
        )
        val question8n3 = Question(
            testId = testId8.toInt(),
            question = "Как обратиться к третьему элементу массива в Java?",
            options = listOf("numbers[3]", "numbers[2]", "numbers[4]", "numbers[0]"),
            correctAnswerIndex = 1
        )
        val question8n4 = Question(
            testId = testId8.toInt(),
            question = "Как создать строку в Java?",
            options = listOf("String message = new String(\"Hello\");", "String message = \"Hello\";", "String message = String(\"Hello\");", "string message = \"Hello\";"),
            correctAnswerIndex = 1
        )
        val question8n5 = Question(
            testId = testId8.toInt(),
            question = "Как получить длину строки в Java?",
            options = listOf("message.length()", "message.size()", "message.len()", "message.count()"),
            correctAnswerIndex = 0
        )
        val question9n1 = Question(
            testId = testId9.toInt(),
            question = "Что представляет собой метод в Java?",
            options = listOf("Упорядоченный набор элементов", "Блок кода, который может выполняться при вызове из другого места программы", "Неупорядоченный набор переменных", "Тип данных"),
            correctAnswerIndex = 1
        )
        val question9n2 = Question(
            testId = testId9.toInt(),
            question = "Как объявляется метод в Java?",
            options = listOf("Тип_возвращаемого_значения имя_метода(параметры) { // тело метода }", "void имя_метода(параметры) { // тело метода }", "метод имя_метода(параметры) { // тело метода }", "имя_метода(параметры) { // тело метода }"),
            correctAnswerIndex = 0
        )
        val question9n3 = Question(
            testId = testId9.toInt(),
            question = "Что такое параметры метода?",
            options = listOf("Значения, возвращаемые методом", "Методы, которые могут быть вызваны внутри другого метода", "Локальные переменные метода, передаваемые при вызове метода", "Циклы, выполняемые внутри метода"),
            correctAnswerIndex = 2
        )
        val question9n4 = Question(
            testId = testId9.toInt(),
            question = "Какие ключевые слова используются для возврата значения из метода в Java?",
            options = listOf("return", "break", "exit", "continue"),
            correctAnswerIndex = 0
        )
        val question9n5 = Question(
            testId = testId9.toInt(),
            question = "Что делает оператор return в методе?",
            options = listOf("Прерывает выполнение метода и выходит из него", "Продолжает выполнение следующей итерации цикла в методе", "Возвращает значение из метода и завершает его выполнение", "Ничего из вышеперечисленного"),
            correctAnswerIndex = 2
        )
        val question9n6 = Question(
            testId = testId9.toInt(),
            question = "Что такое перегрузка методов в Java?",
            options = listOf("Возможность объявления нескольких методов с одним и тем же именем, но с разными параметрами", "Возможность вызова метода из другого метода", "Возможность использования метода в разных классах", "Возможность создания метода без параметров"),
            correctAnswerIndex = 0
        )
        val question10n1 = Question(
            testId = testId10.toInt(),
            question = "Что представляет собой класс в Java?",
            options = listOf("Экземпляр объекта", "Шаблон для создания объектов", "Тип данных", "Метод"),
            correctAnswerIndex = 1
        )
        val question10n2 = Question(
            testId = testId10.toInt(),
            question = "Как объявляется класс в Java?",
            options = listOf("сlass MyClass {}", "function MyClass {}", "MyClass {}", "Class MyClass {}"),
            correctAnswerIndex = 0
        )
        val question10n3 = Question(
            testId = testId10.toInt(),
            question = "Какие компоненты включает в себя класс в Java?",
            options = listOf("Поля и методы", "Переменные и операторы", "Функции и аргументы", "Циклы и условия"),
            correctAnswerIndex = 0
        )
        val question10n4 = Question(
            testId = testId10.toInt(),
            question = "Что представляет собой объект в Java?",
            options = listOf("Экземпляр класса", "Шаблон для создания класса", "Метод класса", "Поле класса"),
            correctAnswerIndex = 0
        )
        val question10n5 = Question(
            testId = testId10.toInt(),
            question = "Как создать объект в Java?",
            options = listOf("ClassName objectName = ClassName();", "objectName = new ClassName;", "ClassName objectName = new ClassName();", "new ClassName objectName;"),
            correctAnswerIndex = 2
        )
        val question10n6 = Question(
            testId = testId10.toInt(),
            question = "Какие компоненты класса определяют его состояние?",
            options = listOf("Поля", "Методы", "Конструкторы", "Все вышеперечисленное"),
            correctAnswerIndex = 0
        )
        val question10n7 = Question(
            testId = testId10.toInt(),
            question = "Зачем используются конструкторы класса?",
            options = listOf("Для создания методов класса", "Для инициализации объектов", "Для объявления полей класса", "Для определения поведения объекта"),
            correctAnswerIndex = 1
        )
        val question11n1 = Question(
            testId = testId11.toInt(),
            question = "Что такое наследование в объектно-ориентированном программировании?",
            options = listOf("Возможность использовать объекты разных классов с одинаковым интерфейсом", "Создание нового класса на основе уже существующего, наследуя его свойства и методы", "Возможность объявления нескольких методов с одним и тем же именем, но с разными параметрами"),
            correctAnswerIndex = 1
        )
        val question11n2 = Question(
            testId = testId11.toInt(),
            question = "Какое ключевое слово используется для указания наследования в Java?",
            options = listOf("extends", "implements", "inherits"),
            correctAnswerIndex = 0
        )
        val question11n3 = Question(
            testId = testId11.toInt(),
            question = "Что такое полиморфизм в Java?",
            options = listOf("Возможность использования объектов разных классов с одинаковым интерфейсом", "Возможность создания нового класса на основе уже существующего, наследуя его свойства и методы", "Возможность переопределения методов в дочерних классах"),
            correctAnswerIndex = 0
        )
        val question11n4 = Question(
            testId = testId11.toInt(),
            question = "Какой метод вызывается при использовании объекта дочернего класса через ссылку на родительский класс в Java?",
            options = listOf("Метод родительского класса", "Метод дочернего класса", "Зависит от контекста использования"),
            correctAnswerIndex = 1
        )
        val question12n1 = Question(
            testId = testId12.toInt(),
            question = "Что такое интерфейс в Java?",
            options = listOf("Класс, который содержит как абстрактные, так и обычные методы", "Абстрактный тип данных, который содержит только абстрактные методы", "Класс, который может реализовать несколько интерфейсов"),
            correctAnswerIndex = 1
        )
        val question12n2 = Question(
            testId = testId12.toInt(),
            question = "Может ли интерфейс содержать реализацию методов?",
            options = listOf("Да", "Нет"),
            correctAnswerIndex = 1
        )
        val question12n3 = Question(
            testId = testId12.toInt(),
            question = "Что такое абстрактный класс в Java?",
            options = listOf("Класс, который может реализовать несколько интерфейсов", "Класс, который содержит только абстрактные методы", "Класс, который содержит как абстрактные, так и обычные методы"),
            correctAnswerIndex = 3
        )
        val question12n4 = Question(
            testId = testId12.toInt(),
            question = "Как объявляется абстрактный метод в Java?",
            options = listOf("abstract void methodName();", "void abstract methodName();", "abstract methodName();"),
            correctAnswerIndex = 0
        )
        val question12n5 = Question(
            testId = testId12.toInt(),
            question = "Может ли класс реализовывать несколько абстрактных классов в Java?",
            options = listOf("Да", "Нет"),
            correctAnswerIndex = 1
        )
        val question13n1 = Question(
            testId = testId13.toInt(),
            question = "Что такое исключение в Java?",
            options = listOf("Ошибка, возникающая только на этапе компиляции программы", "Событие, нарушающее нормальное выполнение программы во время ее выполнения", "Ошибка, которая всегда приводит к завершению программы"),
            correctAnswerIndex = 1
        )
        val question13n2 = Question(
            testId = testId13.toInt(),
            question = "Что такое checked исключения в Java?",
            options = listOf("Исключения, которые должны быть обработаны программистом", "Исключения, которые не требуют обязательной обработки программистом", "Исключения, которые возникают только во время выполнения программы"),
            correctAnswerIndex = 0
        )
        val question13n3 = Question(
            testId = testId13.toInt(),
            question = "Какой блок используется для обработки исключений в Java?",
            options = listOf("try-except", "try-catch", "try-finally"),
            correctAnswerIndex = 1
        )
        val question13n4 = Question(
            testId = testId13.toInt(),
            question = "Для чего используется блок finally в обработке исключений?",
            options = listOf("Для генерации исключений", "Для выполнения кода, который должен быть выполнен независимо от того, возникло исключение или нет", "Для обработки исключений определенного типа"),
            correctAnswerIndex = 1
        )
        val question13n5 = Question(
            testId = testId13.toInt(),
            question = "Как можно сгенерировать исключение вручную в Java?",
            options = listOf("С помощью ключевого слова \"catch\"", "С помощью ключевого слова \"throw\"", "С помощью ключевого слова \"finally\""),
            correctAnswerIndex = 1
        )
        val question14n1 = Question(
            testId = testId14.toInt(),
            question = "Что представляют собой коллекции в Java?",
            options = listOf("Структуры данных, позволяющие хранить и управлять наборами объектов", "Отображения ключ-значение", "Специальные типы данных для работы с числами"),
            correctAnswerIndex = 0
        )
        val question14n2 = Question(
            testId = testId14.toInt(),
            question = "Какой из перечисленных интерфейсов представляет упорядоченную коллекцию, которая может содержать дублирующиеся элементы?",
            options = listOf("List", "Set", "Map"),
            correctAnswerIndex = 0
        )
        val question14n3 = Question(
            testId = testId14.toInt(),
            question = "Какой класс представляет реализацию интерфейса Set на основе хэш-таблицы в Java?",
            options = listOf("ArrayList", "HashSet", "LinkedList"),
            correctAnswerIndex = 1
        )
        val question14n4 = Question(
            testId = testId14.toInt(),
            question = "Какой метод используется для добавления элемента в коллекцию в Java?",
            options = listOf("insert()", "put()", "add()"),
            correctAnswerIndex = 2
        )
        val question14n5 = Question(
            testId = testId14.toInt(),
            question = "Какой из следующих кодов создает список (List) и добавляет в него элемент \"Java\"?",
            options = listOf("List<String> list = new ArrayList<>();\n" +
                    "list.add(\"Java\");", "ArrayList<String> list = new ArrayList<>();\n" +
                    "list.put(\"Java\");", "List<String> list = new List<>();\n" +
                    "list.insert(\"Java\");"),
            correctAnswerIndex = 0
        )
        val question15n1 = Question(
            testId = testId15.toInt(),
            question = "Какие классы используются для чтения данных из файла в Java?",
            options = listOf("FileReader и BufferedWriter", "FileWriter и BufferedReader", "FileReader и BufferedReader"),
            correctAnswerIndex = 2
        )
        val question15n2 = Question(
            testId = testId15.toInt(),
            question = "Какой метод класса File используется для проверки существования файла или директории?",
            options = listOf("checkExistence()", "exists()", "isExist()"),
            correctAnswerIndex = 1
        )
        val question15n3 = Question(
            testId = testId15.toInt(),
            question = "Что произойдет, если файл для чтения не существует при попытке открытия с использованием FileReader?",
            options = listOf("Будет создан новый файл с таким именем", "Будет выброшено исключение FileNotFoundException", "Метод вернет null"),
            correctAnswerIndex = 1
        )

        questionDao.insertQuestion(question1n1)
        questionDao.insertQuestion(question1n2)
        questionDao.insertQuestion(question1n3)
        questionDao.insertQuestion(question2n1)
        questionDao.insertQuestion(question2n2)
        questionDao.insertQuestion(question3n1)
        questionDao.insertQuestion(question3n2)
        questionDao.insertQuestion(question3n3)
        questionDao.insertQuestion(question3n4)
        questionDao.insertQuestion(question3n5)
        questionDao.insertQuestion(question3n6)
        questionDao.insertQuestion(question3n7)
        questionDao.insertQuestion(question3n8)
        questionDao.insertQuestion(question3n9)
        questionDao.insertQuestion(question3n10)
        questionDao.insertQuestion(question4n1)
        questionDao.insertQuestion(question4n2)
        questionDao.insertQuestion(question4n3)
        questionDao.insertQuestion(question4n4)
        questionDao.insertQuestion(question4n5)
        questionDao.insertQuestion(question5n1)
        questionDao.insertQuestion(question5n2)
        questionDao.insertQuestion(question5n3)
        questionDao.insertQuestion(question5n4)
        questionDao.insertQuestion(question5n5)
        questionDao.insertQuestion(question6n1)
        questionDao.insertQuestion(question6n2)
        questionDao.insertQuestion(question6n3)
        questionDao.insertQuestion(question6n4)
        questionDao.insertQuestion(question6n5)
        questionDao.insertQuestion(question6n6)
        questionDao.insertQuestion(question7n1)
        questionDao.insertQuestion(question7n2)
        questionDao.insertQuestion(question7n3)
        questionDao.insertQuestion(question7n4)
        questionDao.insertQuestion(question7n5)
        questionDao.insertQuestion(question7n6)
        questionDao.insertQuestion(question8n1)
        questionDao.insertQuestion(question8n2)
        questionDao.insertQuestion(question8n3)
        questionDao.insertQuestion(question8n4)
        questionDao.insertQuestion(question8n5)
        questionDao.insertQuestion(question9n1)
        questionDao.insertQuestion(question9n2)
        questionDao.insertQuestion(question9n3)
        questionDao.insertQuestion(question9n4)
        questionDao.insertQuestion(question9n5)
        questionDao.insertQuestion(question9n6)
        questionDao.insertQuestion(question10n1)
        questionDao.insertQuestion(question10n2)
        questionDao.insertQuestion(question10n3)
        questionDao.insertQuestion(question10n4)
        questionDao.insertQuestion(question10n5)
        questionDao.insertQuestion(question10n6)
        questionDao.insertQuestion(question10n7)
        questionDao.insertQuestion(question11n1)
        questionDao.insertQuestion(question11n2)
        questionDao.insertQuestion(question11n3)
        questionDao.insertQuestion(question11n4)
        questionDao.insertQuestion(question12n1)
        questionDao.insertQuestion(question12n2)
        questionDao.insertQuestion(question12n3)
        questionDao.insertQuestion(question12n4)
        questionDao.insertQuestion(question12n5)
        questionDao.insertQuestion(question13n1)
        questionDao.insertQuestion(question13n2)
        questionDao.insertQuestion(question13n3)
        questionDao.insertQuestion(question13n4)
        questionDao.insertQuestion(question13n5)
        questionDao.insertQuestion(question14n1)
        questionDao.insertQuestion(question14n2)
        questionDao.insertQuestion(question14n3)
        questionDao.insertQuestion(question14n4)
        questionDao.insertQuestion(question14n5)
        questionDao.insertQuestion(question15n1)
        questionDao.insertQuestion(question15n2)
        questionDao.insertQuestion(question15n3)
    }
}