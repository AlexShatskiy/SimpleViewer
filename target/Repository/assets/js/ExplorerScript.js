$(document).ready(function(){

//command back
    $(document).on("click", ".back", function(){
        var passData = "";
        passData = $(this).attr('value');

        $.ajax({
            type : "POST",
            url : "controller?command=BACK_SHOW_REPOSITORY_JSON",
            data:{"pass":passData,
                "isFolder":"true"
            },

            success : function(data) {
                var received = JSON.parse(JSON.stringify(data));
                addToFolderTable(received);
            },
            error : function(e) {
                console.log("ERROR: ", e);
            },
            done : function(e) {
                console.log("DONE");
            }
        });
        $.ajax({
            type : "POST",
            url : "controller?command=BACK_SHOW_REPOSITORY_JSON",
            data:{"pass":passData,
                "isFolder":"false"
            },

            success : function(data) {
                var received = JSON.parse(JSON.stringify(data));
                addToFilesTable(received);
            },
            error : function(e) {
                console.log("ERROR: ", e);
            },
            done : function(e) {
                console.log("DONE");
            }
        });
    });

//command view
    $(document).on("click", ".fold", function(){
        var passData = "";
        passData = $(this).attr('value');

        $.ajax({
            type : "POST",
            url : "controller?command=SHOW_REPOSITORY_JSON",
            data:{"pass":passData,
                "isFolder":"true"
            },

            success : function(data) {
                var received = JSON.parse(JSON.stringify(data));
                addToFolderTable(received);
            },
            error : function(e) {
                console.log("ERROR: ", e);
            },
            done : function(e) {
                console.log("DONE");
            }
        });
        $.ajax({
            type : "POST",
            url : "controller?command=SHOW_REPOSITORY_JSON",
            data:{"pass":passData,
                "isFolder":"false"
            },

            success : function(data) {
                var received = JSON.parse(JSON.stringify(data));
                addToFilesTable(received);
            },
            error : function(e) {
                console.log("ERROR: ", e);
            },
            done : function(e) {
                console.log("DONE");
            }
        });
    });


//start view folder
    $.ajax({
        type : "POST",
        url : "controller?command=SHOW_REPOSITORY_JSON",
        data:{
            "isFolder":"true"
        },

        success : function(data) {
            var received = JSON.parse(JSON.stringify(data));
            addToFolderTable(received);
        },
        error : function(e) {
            console.log("ERROR: ", e);
        },
        done : function(e) {
            console.log("DONE");
        }
    });
    $.ajax({
        type : "POST",
        url : "controller?command=SHOW_REPOSITORY_JSON",
        data:{
            "isFolder":"false"
        },

        success : function(data) {
            var received = JSON.parse(JSON.stringify(data));
            addToFilesTable(received);
        },
        error : function(e) {
            console.log("ERROR: ", e);
        },
        done : function(e) {
            console.log("DONE");
        }
    });



//folder table
    function addToFolderTable(received) {
        $("#folders").css("display","block");

        $("#folders").find("tr:gt(0)").remove();

        for(var i=0;i<received.length;i++)
        {
            var tr="<tr>";
            var td1="<td>"+received[i].fileName+"</td>";
            var td2="<td>"+received[i].parentFileName+"</td>";
            var td3="<td>"+received[i].sizeModel+"</td>";
            var td4="<td>"+"<button type="+"\"button\" class=\"fold btn\" value=\"" + received[i].fullPath +"\">view</button></td></tr>";
            $("#folders").append(tr+td1+td2+td3+td4);
        }
        if (received.length>0) {
            addValButtonBack(received[0].fullPath);
        }else {
            $("#folders").css("display","none");
        }
    }
//file table
    function addToFilesTable(received) {
        $("#files").css("display","block");

        $("#files").find("tr:gt(0)").remove();

        for(var i=0;i<received.length;i++)
        {
            var tr="<tr>";
            var td1="<td>"+received[i].fileName+"</td>";
            var td2="<td>"+received[i].parentFileName+"</td>";
            var td3="<td>"+received[i].sizeModel+"</td>";
            var td4="<td><form method=\"get\" action=\"controller\"><input type=\"hidden\" name=\"command\" value=\"DOWNLOAD_FILE\" /> <input type=\"hidden\" name=\"fullPath\" value=\"";
            var td5=received[i].fullPath
            var td6="\" /> <button type=\"submit\" class=\"btn\">download</button> </form></td></tr>";
            $("#files").append(tr+td1+td2+td3+td4+td5+td6);
        }
        if (received.length>0) {
            addValButtonBack(received[0].fullPath);
        } else {
            $("#files").css("display","none");
        }
    }
//back button
    function addValButtonBack(pass) {
        $(".back").val(pass);
    }
});
