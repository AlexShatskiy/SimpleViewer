<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>homeJSON(new)</title>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link href="assets/css/bootstrap.css" rel="stylesheet">

    <script type="text/javascript">

        $(document).ready(function(){


            $.fileDownload('some/file.pdf')
                .done(function () { alert('File download a success!'); })
                .fail(function () { alert('File download failed!'); });


            //look up folders
            $(document).on("click", "#folder", function(){
                nameFolder = $(this).attr('value');
                loadFileAndFolders(nameFolder, "view");
            });

            //command back
            $(document).on("click", "#back", function(){
                nameFolder = $(this).attr('value');
                loadFileAndFolders(nameFolder, "back");
            });

            //command test
            $(document).on("click", "#load", function(){

                window.open("E:\\\\betolit");
            });
        });

        //It load file and folders from server
        function loadFileAndFolders(nameFolder, switchPath){
            $("#back").val(nameFolder);
            $("#mainTable").find("tr:gt(0)").remove();
            $.ajax({
                type : "POST",
                url : "controller?command=SHOW_REPOSITORY_JSON",
                data:{"nameFolder":nameFolder,
                    "switchPath":switchPath
                },
                success : function(data) {
                    var received = JSON.parse(JSON.stringify(data));
                    addToMainTable(received);
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
                    var tr = "<tr id=\"folder\" value=\"" + received[i].fileName + "\">";
                } else {
                    var tr = "<tr id=\"file\">";
                }
                var td1="<td>"+received[i].fileName+"</td>";
                var td2="<td>"+received[i].parentFileName+"</td>";
                var td3="<td>"+received[i].sizeModel+"</td>";
                if(received[i].isDirectory==true) {
                    var td4 = "<td></td></tr>";
                }else{
                    var td41="<td><form method=\"get\" action=\"controller\"><input type=\"hidden\" name=\"command\" value=\"DOWNLOAD_FILE\" /> <input type=\"hidden\" name=\"fullPath\" value=\"";
                    var td42=received[i].fullPath
                    var td43="\" /> <button type=\"submit\" class=\"btn\">download</button> </form></td></tr>";
                    var td4 = td41+td42+td43;
                }
                $("#mainTable").append(tr+td1+td2+td3+td4);
            }
            if (received.length==0) {
                $("#mainTable").css("display","none");
            }
        }


        //It load file and folders from server
        function load(nameFile){
            $.ajax({
                type : "GET",
                url : "controller?command=DOWNLOAD_FILE",
                data:{"nameFolder":nameFile,

                },
                success : function(data) {
                    alert("ok");
                },
                error : function(e) {
                    console.log("ERROR: ", e);
                }
            });
        }


    </script>

</head>

<body onload="loadFileAndFolders()">
<div class="container">
<br>
<br>
    <button type="submit" class="btn" id="back">back</button>
<br>
<br>
    <button type="submit" class="btn" id="load">load</button>
<br>
<br>



</div>

<div class="container">
    <table class="table table-bordered" id="mainTable" style="display: none">
        <tr>
            <th>Name</th>
            <th>Parent folder name</th>
            <th>Size(byte)</th>
            <th></th>
        </tr>
    </table>

</div>


</body>
</html>
