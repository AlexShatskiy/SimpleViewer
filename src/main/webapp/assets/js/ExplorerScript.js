$(document).ready(function(){

    //look up folders
    $(document).on("click", ".folder", function(){
        nameFolder = $(this).attr('value');
        loadFileAndFolders(nameFolder, "null");
    });

    //command back
    $(document).on("click", "#back", function(){
        loadFileAndFolders(nameFolder, "back");
    });
});

//It load file and folders from server for table
function loadFileAndFolders(nameFolder, switchPath){
    $("#mainTable").find("tr:gt(0)").remove();
    $.ajax({
        type : "POST",
        url : "controller",
        dataType:'json',
        data:{"command":"SHOW_REPOSITORY_JSON",
            "nameFolder":nameFolder,
            "switchPath":switchPath
        },
        success : function(data) {
            addToMainTable(data);
        },
        error : function(e) {
            console.log("ERROR: ", e);
        }
    });
}

//It add list files and folders in main table
function addToMainTable(received) {
    $("#mainTable").css("display","block");
    for(var i=0;i<received.length;i++)
    {
        if(received[i].isDirectory==true) {
            var tr = "<tr class=\"folder\" value=\"" + received[i].fileName + "\">";
            var td1="<td><span class=\"glyphicon glyphicon-folder-open\">  "+received[i].fileName+"</span></td>";
        } else {
            var tr = "<tr class=\"file\">";
            var td1="<td><a href=\"download?fileName=" + received[i].fileName + "\" download>\<span class=\"glyphicon glyphicon-file\">  "+received[i].fileName+"</a></span></td>";
        }
        var td2="<td>"+received[i].sizeModel.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")+"</td></tr>";

        $("#mainTable").append(tr+td1+td2);
    }
    if (received.length==0) {
        $("#mainTable").css("display","none");
    }
}

