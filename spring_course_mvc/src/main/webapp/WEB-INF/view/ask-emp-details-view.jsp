<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<body>

<h2> Введите имя </h2>
<br>
<br>

<form:form action="showDetails" modelAttribute="employee">

    Имя <form:input path="name"/>
    <form:errors path="name"/>
    <br><br>
    Фамилия <form:input path="surname"/>
    <form:errors path="surname"/>
    <br><br>
    Зарплата <form:input path="salary"/>
    <form:errors path="salary"/>
    <br><br>
    Департамент: <form:select path="department">
    <form:options items="${employee.departments}"/>
    </form:select>
    <br><br>
    Какую машину вы хотите?
    <form:radiobuttons path="carBrand" items="${employee.carBrands}"/>
    <br><br>
    Каким языком владеете?
    <form:checkboxes path="languages" items="${employee.languageList}"/>
    <br><br>
    Телефон: <form:input path="phoneNumber"/>
    <form:errors path="phoneNumber"/>
    <br><br>
    Email: <form:input path="email"/>
    <form:errors path="email"/>
    <br><br>
    <input type="submit" value="OK">

</form:form>

</body>

</html>
