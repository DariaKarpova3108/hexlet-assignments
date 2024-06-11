@import exercise.dto.users.UserPage
@param UserPage page

@template(
content=@"
<main>
    <h1>Пользователь ${page.getUser().getId()}</h1>
    <p>${page.getUser().getFirstName()} ${page.getUser().getLastName()}</p>
    <div>${page.getUser().getEmail()}</div>
    <div>
        <a href="/"> main page</a>
        <a href="/users"> users </a>
    </div>
</main>
"
)
