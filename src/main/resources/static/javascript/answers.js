function saveAnswer()
{
    var answerDTO = {};
    answerDTO.answer = $("#answerform").val();
    answerDTO.email = JSON.parse(sessionStorage.loggedInUser).email;
    answerDTO.questionId = $("#questionID").text();
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/answer/save",
        data: JSON.stringify(answerDTO),
        success: function () {
            window.location.replace("http://localhost:8080/answer/view-answer/" + answerDTO.questionId)
        },
        error: function (e) {
            document.getElementById("answersErrorMessage").innerHTML = e.responseText;
        }
    });
}

function downVote(id) {
    var identifierDTO = {};
    identifierDTO.answerID = id;
    identifierDTO.questionID = $("#questionID").text();
    identifierDTO.userId = JSON.parse(sessionStorage.loggedInUser).id;
    $.ajax({
        type: "PUT",
        contentType: "application/json",
        url: "/answer/down-vote/" + identifierDTO.answerID + "/" + identifierDTO.questionID,
        data: JSON.stringify(identifierDTO),
        success: function () {
            window.location.replace("http://localhost:8080/answer/view-answer/" + identifierDTO.questionID);
            alert("Dislike Pressed!");
        },
        error: function () {

        }
    });
}

function upVote(id) {
    var identifierDTO = {};
    identifierDTO.answerID = id;
    identifierDTO.questionID = $("#questionID").text();
    identifierDTO.userId = JSON.parse(sessionStorage.loggedInUser).id;
    $.ajax({
        type: "PUT",
        contentType: "application/json",
        url: "/answer/up-vote/" + identifierDTO.answerID + "/" + identifierDTO.questionID,
        data: JSON.stringify(identifierDTO),
        success: function () {
            window.location.replace("http://localhost:8080/answer/view-answer/" + identifierDTO.questionID);
            alert("Like Pressed!");
        },
        error: function () {

        }
    });
}