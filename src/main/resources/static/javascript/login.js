function loginUser()
{
    var userDTO = {};
    userDTO.email = $("#username").val();
    userDTO.password = $("#password").val();
    $.ajax({
        type: "POST",
        contentType:"application/json",
        url: "/auth/login",
        //data:{email: email, password: password},
        data: JSON.stringify(userDTO),
        success: function (data) {
            window.location.replace("http://localhost:8080/auth/logged-in");
            window.sessionStorage.setItem("loggedInUser", JSON.stringify(data));
        },
        error: function (e) {
            document.getElementById("errorMessage").innerHTML = e.responseJSON.message;
        }
    });
}