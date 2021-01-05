#Домашнее задание к лекции 16
Cвой json object writer
Цель: Научиться сериализовывать объект в json,
попрактиковаться в разборе структуры объекта.
Напишите свой json object writer (object to JSON string) аналогичный gson на основе javax.json.

Gson это делает так:
Gson gson = new Gson();
AnyObject obj = new AnyObject(22, "test", 10);
String json = gson.toJson(obj);

Сделайте так:
MyGson myGson = new MyGson();
AnyObject obj = new AnyObject(22, "test", 10);
String myJson = myGson.toJson(obj);

Должно получиться:
AnyObject obj2 = gson.fromJson(myJson, AnyObject.class);
System.out.println(obj.equals(obj2));

Поддержите:
- примитивные типы и Wrapper-ы (Integer, Float и т.д.)
- строки
- массивы примитивных типов
- коллекции (interface Collection)

Не забываться, что obj может быть null :)
Критерии оценки: Система оценки максимально соответсвует привычной школьной:
3 и больше - задание принято (удовлетворительно).
ниже - задание возвращается на доработку.