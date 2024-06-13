### Ввод-вывод (I/O) в Java

В Java ввод-вывод осуществляется с помощью пакета `java.io`. Этот пакет предоставляет классы и интерфейсы для работы с файлами, потоками ввода-вывода и другими средствами ввода-вывода.

### Чтение данных из файла

Для чтения данных из файла используется класс `FileReader` или `BufferedReader`. Сначала необходимо создать объект класса `File` для представления файла, затем этот объект передается в конструктор класса `FileReader` или `BufferedReader`.

```java
import java.io.*;

public class Main {
    public static void main(String[] args) {
        try {
            File file = new File("example.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

### Запись данных в файл

Для записи данных в файл также можно использовать классы `FileWriter` или `BufferedWriter`. После создания объекта класса `File`, он передается в конструктор класса `FileWriter` или `BufferedWriter`. После записи данных необходимо закрыть поток.

```java
import java.io.*;

public class Main {
    public static void main(String[] args) {
        try {
            File file = new File("example.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write("Hello, world!");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

### Работа с директориями

Класс `File` также позволяет работать с директориями. С его помощью можно создавать новые директории, проверять их существование, получать список файлов и директорий и многое другое.

### Обработка исключений ввода-вывода

При работе с файлами необходимо учитывать возможность возникновения ошибок ввода-вывода. Поэтому весь код, связанный с работой с файлами, обычно помещается в блок `try-catch` для обработки возможных исключений.