@import exercise.dto.users.UsersPage
@param UsersPage users

<!doctype html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>List of users</title>
</head>

<body>
<div>
    <main>
        <h1></h1>
        @if(users.getUsers().isEmpty())
            <p>пользователи не найдены</p>
        @else
            <ul>
                @for(var user: users.getUsers())
                    <li>
                        <a href="/UsersPage/${user.getId()}">
                            ${"Имя:" + user.getFirstName() + ", фамилия:" + user.getLastName() + ", email:" + user.getEmail() + "\n"}
                        </a>

                    </li>
                @endfor
            </ul>
        @endif
    </main>
</div>
</body>
</html>