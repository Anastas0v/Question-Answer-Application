$(document).ready(function () {
    $("#loggedInUser").html("You are logged in as: " + JSON.parse(sessionStorage.loggedInUser).email);
});

function logOut() {
    sessionStorage.removeItem("loggedInUser");
}

function saveQuestion() {
    var questionDTO = {};
    questionDTO.title = $("#title").val();
    questionDTO.question = $("#questionform").val();
    questionDTO.email = JSON.parse(sessionStorage.loggedInUser).email;
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/question/save",
        data: JSON.stringify(questionDTO),
        success: function () {
            window.location.replace("http://localhost:8080/auth/logged-in");
        },
        error: function (e) {
            document.getElementById("questionsErrorMessage").innerHTML = e.responseText;
        }
    });
}

function deleteQuestion(id) {
    var deleteQuestionDTO = {};
    deleteQuestionDTO.questionId = id;
    deleteQuestionDTO.userEmail = JSON.parse(sessionStorage.loggedInUser).email;
    $.ajax({
        type: "DELETE",
        contentType: "application/json",
        url: "/question/delete-question",
        data: JSON.stringify(deleteQuestionDTO),
        success: function () {
            window.location.replace("http://localhost:8080/auth/logged-in");
        },
        error: function (e) {
            alert(e.responseText);
        }
    });
}