function postComent() {
    var questionId = $("#question_id").val();
    var content = $("#comment_content").val();

    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: 'application/json',
        data: JSON.stringify({
            "parent_id": questionId,
            "content": content,
            "type": 1
        }),
        success: function (response) {
           if(response.code==200){
               //异步刷新
               window.location.reload();
           }else {
                  var isAccepted=confirm(response.message);
                  if(isAccepted){
                      window.localStorage.setItem("closable",true);
                  }
           }
        },
        dataType: "json"
    });
    console.log(questionId);
    console.log(content);
}
