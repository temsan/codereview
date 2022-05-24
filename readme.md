##Ревью кода
###Нэйминг
1. Названия переменных в виде одного символа не передают суть, я бы использовал
вместо `r` что-то типо `reader`
```java
r = new BufferedReader(new FileReader(path.toFile()));
reader = new BufferedReader(new FileReader(path.toFile()));
```
Также это касается
`String l;`
---
###Deprecated
1. Конструктор `new Character('0')` устарел
---
###Прочие Ошибки
1. Optional сравнивается с нулом, нужно использовать
`if (chr.isPresent())`
Я бы переписал в функциональном стиле
`chr.ifPresent(accum::append);`
2. Опшиналу присваивается нул в определении
`Optional<Character> chr = null;`
3. Используется вложенный try с пустым блоком catch. Нужно использовать try with resources
`try (r = new BufferedReader(new FileReader(path.toFile()))){}` и соответственно блок finally можно выбросить