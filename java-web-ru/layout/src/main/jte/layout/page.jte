@import exercise.dto.users.UsersPage
@import gg.jte.Content
@param UsersPage page
@param Content content

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="/public/css/bootstrap.css" rel="stylesheet">
    <title>Пользователи</title>
</head>
<body>
<script src="/public/js/bootstrap.js"></script>
<header>
    <ul>
        <li>
            <a href="/">Главная страница</a>
        </li>
        @for(var user : page.getUsers())
            <li>
                <a href="/users/${user.getId()}"> ${user.getFirstName()} ${user.getLastName()}</a>
            </li>
        @endfor
    </ul>
</header>

<main>
    ${content}
</main>

<footer>
    <a href="https://github.com/DariaKarpova3108">my GitHub</a>
</footer>
</body>
</html>