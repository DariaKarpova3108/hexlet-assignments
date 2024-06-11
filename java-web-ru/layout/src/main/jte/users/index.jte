@import exercise.dto.users.UsersPage
@param UsersPage page

@template(
content = @"
<main>
    <h1>Пользователи</h1>
    <table>
        @for(var user : page.getUsers())
            <tr>
                <td>${user.getId()}</td>
                <td><a href="/users/${user.getId()}">${user.getFirstName()} ${user.getLastName()}</a></td>
            </tr>
        @endfor
    </table>
</main>
"
)

