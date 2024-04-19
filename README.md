# Разработка автоматизированной системы учета успеваемости студентов вуза (JSP) (2021)

Диаграмма вариантов использования

![image](https://github.com/Evgescha/2101-953.-Development-of-an-automated-system-for-recording-the-academic-performance-of-university-st/assets/38140129/9670a48e-abc0-430b-bb8d-863cc4822b18)

физическая модель системы

![image](https://github.com/Evgescha/2101-953.-Development-of-an-automated-system-for-recording-the-academic-performance-of-university-st/assets/38140129/5f6ac56d-7d39-4323-b9c5-ef40a781d1b4)


## РУКОВОДСТВО ПОЛЬЗОВАТЕЛЯ
Для запуска через IDE (к примеру Eclipse), нужно лишь открыть в нем проект и запустить приложение как серверное приложение (Run as – Server application). 
Затем в самой IDE откроется страница приложения. Или уже после запуска можно перейти в браузер по адресу http://localhost:8080/ PerformanceStudents/ и будет то же запущенное приложение. 
Если запускать приложение без IDE (вне среды разработки), то нужно экспортировать проект в war-файл для запуска через Tomcat. 
Файл экспортировать можно куда угодно, но в конце его нужно положить в папку webapp Tomcat-a. Затем в папке томката в папке bin запустить приложение соответствующим файлом  
И все, снова открывать в браузере уже запущенное приложение 
Приложение имеет простой, интуитивно-понятный интерфейс, доступный обычному пользователю. При запуске данного приложения вы увидите главную страницу (рисунок 8.1).
 
 ![image](https://github.com/Evgescha/2101-953.-Development-of-an-automated-system-for-recording-the-academic-performance-of-university-st/assets/38140129/d7823dc1-9365-4f8a-84ce-4f690f0994d7)

Рисунок 8.1 – Главная страница

Данная страница является главной. Она не наделена какой-либо лишней информации, чтобы можно было спокойно разобраться со всеми пунктами меню. С помощью нее можно перейти к работе с любой сущностью, используя меню.
Для работы с призами достаточно нажать на кнопку меню «Группы». Вид этой странице представлен ниже.

 ![image](https://github.com/Evgescha/2101-953.-Development-of-an-automated-system-for-recording-the-academic-performance-of-university-st/assets/38140129/4f0e8bb9-496d-49e6-b4dc-c4c8f7dd2261)

Рисунок 8.2 – Группы

Для того, чтобы добавить новую группу, необходимо нажать соответствующую кнопку «Добавить запись». Для редактирования – кнопку «Редактировать» напротив нужного элемента.
Вид страницы добавления показан ниже.
 
 ![image](https://github.com/Evgescha/2101-953.-Development-of-an-automated-system-for-recording-the-academic-performance-of-university-st/assets/38140129/d55c013e-f906-48cd-86d5-759ba25377ed)

Рисунок 8.3 – Добавление группы

Для добавления информации нужно заполнить поля и нажать кнопку «Сохранить». Если вдруг вы передумали, достаточно нажать кнопку «Назад», и вы вернетесь на  предыдущую страницу.
Такой же принцип работы и с другими страницами.
К примеру, вид страницы работы с студентами показан ниже.

 ![image](https://github.com/Evgescha/2101-953.-Development-of-an-automated-system-for-recording-the-academic-performance-of-university-st/assets/38140129/d433711c-f213-445a-8fea-01b75eae70fe)

Рисунок 8.4 – Работа с студентами

При добавлении студента нужно из списка всех групп выбрать ту, к которой будет относиться студент.

 ![image](https://github.com/Evgescha/2101-953.-Development-of-an-automated-system-for-recording-the-academic-performance-of-university-st/assets/38140129/7e8c2225-f9c3-4c60-bf93-8e673ddfbb25)

Рисунок 8.5 – Добавление студента

Страница отображений оценок показана ниже.
 
 ![image](https://github.com/Evgescha/2101-953.-Development-of-an-automated-system-for-recording-the-academic-performance-of-university-st/assets/38140129/189a45d4-af82-4546-946e-b91baa0be053)

Рисунок 8.6 – Работа с оценками

Данное приложение является лёгким в использовании, понятным и не требует дополнительных затрат для осваивания .




