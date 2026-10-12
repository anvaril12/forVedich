<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Налоговая ставка</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<div class="container">

    <h1>Управление налоговой ставкой</h1>

    <p class="current-rate">
        Текущая ставка: <strong>${rate} %</strong>
    </p>

    <label for="rate">Новая ставка, %</label>
    <form class="form" method="post" action="${pageContext.request.contextPath}/admin">
        <input id="rate" type="number" name="rate" min="1" max="100" step="0.01" placeholder="Например, 15" required>
        <button class="btn" type="submit">Изменить</button>
    </form>

    <p class="message">${message}</p>

    <p class="logout"><a href="${pageContext.request.contextPath}/logout">Выйти</a></p>

</div>

</body>
</html>
