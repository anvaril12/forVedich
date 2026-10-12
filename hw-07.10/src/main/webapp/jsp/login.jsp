<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Вход в админку</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<div class="container">

    <h1>Вход для законодателя</h1>

    <form class="login-form" method="post" action="${pageContext.request.contextPath}/login">
        <label for="login">Логин</label>
        <input id="login" type="text" name="login" required>

        <label for="password">Пароль</label>
        <input id="password" type="password" name="password" required>

        <button class="btn" type="submit">Войти</button>
    </form>

    <p class="message error">${error}</p>

</div>

</body>
</html>
