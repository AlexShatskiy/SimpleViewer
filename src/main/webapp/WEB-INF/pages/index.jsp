<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>homeJSON</title>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="resources/assets/js/ExplorerScript.js"></script>
    <link href="resources/assets/css/bootstrap.css" rel="stylesheet">
    <link xmlns="http://www.w3.org/1999/xhtml" href='resources/assets/fonts/glyphicons-halflings-regular.svg' rel='stylesheet' type='image/svg+xml' />

</head>

<body onload="loadFileAndFolders()">
    <div class="container">
        <br>
        <br>
            <button type="submit" class="btn btn-info" id="back"><span class="glyphicon glyphicon-fast-backward">  back</span></button>
            <a class="btn btn-danger" style="float: right;" href="<c:url value="/logout" />"><span class="glyphicon glyphicon-log-out">  Logout</span></a>
        <br>
        <br>

    </div>

    <div class="container">
        <table class="table table-bordered" id="mainTable" style="display: none">
            <tr>
                <th width="350">Name</th>
                <th width="100">Size(byte)</th>
            </tr>
        </table>

    </div>
</body>
</html>