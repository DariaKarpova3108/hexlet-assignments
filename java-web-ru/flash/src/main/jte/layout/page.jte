@import exercise.dto.BasePage
@import gg.jte.Content
@import exercise.util.NamedRoutes

@param Content content
@param BasePage page = null
<!-- BEGIN -->
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Posts from hexlet about succsesses of students</title>
</head>
<body>
<div class="alert alert-success" role="alert">
    @if(page!=null && page.getFlash()!=null)
        ${page.getFlash()}
    @endif
</div>
<div class="lead">
    ${content}
</div>
<footer>
    <nav>
        <div>
            <a href="${NamedRoutes.rootPath()}">main page</a>
        </div>
        <div>
            <a href="${NamedRoutes.postsPath()}">posts</a>
           </div>
        <div>
            <a href="${NamedRoutes.buildPostPath()}">create post</a>
        </div>
    </nav>
</footer>
</body>
</html>
<!-- END -->
