/**  回复方法
 *
 * @param commentId
 * @param type
 * @param comment
 */

function comment2(commentId, type, comment) {
    if (!comment) {
        alert("评论不能未空……")
        return;
    }
    $.ajax({
        type: 'POST',
        url: "/comment",
        contentType: "application/json",
        data: JSON.stringify({
            "parentid": commentId,
            "content": comment,
            "type": type
        }),
        success: function (response) {
            if (response.code == 200) {
                // $("#content_section").hide();
                window.location.reload();
            } else if (response.code == 2003) {
                var isAccepted = confirm(response.message)
                if (isAccepted) {
                    window.open("https://github.com/login/oauth/authorize?client_id=Iv1.a5ad1e39f7775a75&redirect_uri=http://localhost:8080/callback&scope=user&state=1")
                    window.localStorage.setItem("closable", true);
                }
            } else {
                alert(response.message)
            }
        },
        dataType: 'json'
    });

}

/** 问题的回复
 *
 */

function post() {
    var parentid = $("#parentId").val()
    var content = $("#content").val()
    comment2(parentid, 1, content);
}
/**  评论的回复
 *
 * @param e
 */
function comment(e) {
    var commentId = e.getAttribute("data-id");
    var content = $("#input-" + commentId).val();
    comment2(commentId, 2, content);

}


/** 展开二级评论
 *
 * @param e
 */

function pinglun(e) {
    var id = e.getAttribute("data-id");
    var comments = $("#comment-" + id);
    //获取二级评论状态
    var collapse = e.getAttribute("data-collapse")
    if (collapse) {
        //折叠二级评论
        comments.removeClass("in");
        //删除二级评论状态
        e.removeAttribute("data-collapse")

    } else {

        var subCommentContainer = $("#comment-" + id);

        if (subCommentContainer.children().length != 1) {
            //展开二级评论
            comments.addClass("in");
            //标记二级评论状态
            e.setAttribute("data-collapse", "in");

        } else {
            $.getJSON("/comment/" + id, function (data) {


              $.each(data.data.reverse(), function (index, comment) {

                    /*图片*/
                    var mediaLeftElement = $("<div/>", {
                        "class": "media-left media-middle"
                    }).append($("<img/>", {
                        "class": "img-rounded",
                        "src": "https://avatars2.githubusercontent.com/u/54128358?s=460&v=4",
                        "style":"width: 50px"
                    }));

                    /*内容*/
                    var mediaBodyElement = $("<div/>", {
                        "class": "media-body"
                    }).append($("<h5/>", {
                        "class": "media-heading",
                        "html": comment.user.name
                    })).append($("<div/>", {
                        "html": comment.content
                    })).append($("<div/>", {
                        "style": "color: #999;margin-top: 5px"
                    }).append($("<span/>", {
                        "class": "pull-right",
                       "html": moment(comment.createdate).format('YYYY-MM-DD')
                    })));

                    var hr=$("<hr>",{
                        "class":"col-lg-12 col-sm-12 col-xs-12 col-md-12"
                    })

                    var mediaElement = $("<div/>", {
                        "class": "media"
                    }).append(mediaLeftElement).append(mediaBodyElement).append(hr);

                    var commentElement = $("<div/>", {
                        "class": "col-lg-12 col-sm-12 col-xs-12 col-md-12",
                        "style":"padding: 10px;"
                    }).append(mediaElement);
                    subCommentContainer.prepend(commentElement);
                });
                //展开二级评论
                comments.addClass("in");
                //标记二级评论状态
                e.setAttribute("data-collapse", "in");
            });
        }
    }
}


/** 点赞按钮
 *
 */

function dianzan(e) {
    var id = e.getAttribute("data-id2");
    var type =2;
    $.ajax({
        type: 'POST',
        url: "/like",
        contentType: "application/json",
        data: JSON.stringify({
            "id": id,
            "type":type,
        }),
        success: function (response) {
            if (response.code == 200) {
                window.location.reload();
            } else if (response.code == 2003) {
                var isAccepted = confirm(response.message)
                if (isAccepted) {
                    window.open("https://github.com/login/oauth/authorize?client_id=Iv1.a5ad1e39f7775a75&redirect_uri=http://localhost:8080/callback&scope=user&state=1")
                    window.localStorage.setItem("closable", true);
                }
            } else {
                alert(response.message)
            }
        },
        dataType: 'json'
    });

}

function selectTag(e) {
    var value = e.getAttribute("data-tag");
    var previous = $("#tag").val();
    if (previous.indexOf(value) == -1) {
        if (previous) {
            $("#tag").val(previous + ',' + value);
        } else {
            $("#tag").val(value);
        }
    }
}

function showSelectTag() {
    $("#select-tag").show();

}


function likes(e) {
    var id = e.getAttribute("data-qusetionId");
    var type=1;
    $.ajax({
        type: 'POST',
        url: "/like",
        contentType: "application/json",
        data: JSON.stringify({
            "id": id,
            "type":type,
        }),
        success: function (response) {
            if (response.code == 200) {
                window.location.reload();
            } else if (response.code == 2003) {
                var isAccepted = confirm(response.message)
                if (isAccepted) {
                    window.open("https://github.com/login/oauth/authorize?client_id=Iv1.a5ad1e39f7775a75&redirect_uri=http://localhost:8080/callback&scope=user&state=1")
                    window.localStorage.setItem("closable", true);
                }
            } else {
                alert(response.message)
            }
        },
        dataType: 'json'
    });

}

/* 删除按钮*/
function deleteQuestion(e) {
    var id = e.getAttribute("data-questionId");
    $.ajax({
        type: 'POST',
        url: "/update",
        contentType: "application/json",
        data: JSON.stringify({
            "id": id,
            "status":0,
        }),
        success: function (response) {
            if (response.code == 200) {
                window.location.reload();
            } else if (response.code == 2003) {
                var isAccepted = confirm(response.message)
                if (isAccepted) {
                    window.open("https://github.com/login/oauth/authorize?client_id=Iv1.a5ad1e39f7775a75&redirect_uri=http://localhost:8080/callback&scope=user&state=1")
                    window.localStorage.setItem("closable", true);
                }
            } else {
                alert(response.message)
            }
        },
        dataType: 'json'
    });
}
/*还原按钮*/
function restoreQuestion(e) {
    var id = e.getAttribute("data-restoreId");
    $.ajax({
        type: 'POST',
        url: "/update",
        contentType: "application/json",
        data: JSON.stringify({
            "id": id,
            "status":1,
        }),
        success: function (response) {
            if (response.code == 200) {
                window.location.reload();
            } else if (response.code == 2003) {
                var isAccepted = confirm(response.message)
                if (isAccepted) {
                    window.open("https://github.com/login/oauth/authorize?client_id=Iv1.a5ad1e39f7775a75&redirect_uri=http://localhost:8080/callback&scope=user&state=1")
                    window.localStorage.setItem("closable", true);
                }
            } else {
                alert(response.message)
            }
        },
        dataType: 'json'
    });
}