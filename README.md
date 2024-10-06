# testwork-for-custis

## Диаграмма классов

![alt text](https://github.com/whiskolla/testwork-for-custis/blob/main/cd%20custis.jpg)

база данных: jdbc:postgresql://localhost:5432/Custis

username: postgres

password: postgres

## Эндпоинты:

### КУРСЫ:

"/course/{id}" - просмотреть курс по id

"/courses" - прсомотреть все курсы

"/courses/delete{id}" - удалить курс по id

"/courses/update{id}" - изменить курс по id

"/newcourse" - добаввить новый курс

### СТУДЕНТЫ:

"/newstudent" - добаввить нового студента

"/students" - просмотреть всех студентов

"/students/delete{id}" - удалить студента по id

"/students/{studentId}/addcourse{courseId}" - добавить курс по id студенту по id

"/students/{studentId}/removecourse{courseId}" - удалить курс по id у студента по id

"/student{id}" - просмотреть студента по id
