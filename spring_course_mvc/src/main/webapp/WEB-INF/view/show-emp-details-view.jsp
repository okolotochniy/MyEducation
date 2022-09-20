<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<body>

<h2> Добро пожаловать! </h2>
<br>
<br>


Ваше имя: ${employee.name}
<br>
Ваша фамилия: ${employee.surname}
<br>
Ваша зарплата: ${employee.salary}
<br>
Ваш департамент: ${employee.department}
<br>
Ваша машина: ${employee.carBrand}
<br>
Язык:
<ul>
    <c:forEach var="lang" items="${employee.languages}">
        <li> ${lang} </li>
    </c:forEach>
</ul>
<br>
Телефон: ${employee.phoneNumber}
<br>
Email: ${employee.email}
<br>
</body>

</html>