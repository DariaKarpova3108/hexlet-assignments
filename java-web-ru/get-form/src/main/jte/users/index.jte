@import exercise.dto.users.UsersPage
@param UsersPage page
@template.layout.page(
content = @`
    <form action="/users" method="get">
        <input type="search" name="term" required value="${page.getTerm()}">
        <input type="submit" value="search">
    </form>
    @if(!page.getUsers().isEmpty())
    @for(var user: page.getUsers())
            <ul>
                <li>${user.getId()}</li>
                <li>${user.getFirstName()}</li>
                <li>${user.getLastName()}</li>
                <li>${user.getEmail()}</li>
            </ul>
        @endfor
    @endif
`
)