<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>homeJSON</title>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link href="assets/css/bootstrap.css" rel="stylesheet">

    <script type="text/javascript" src="assets/js/ExplorerScript.js?v=2"></script>

</head>

<body>
<div class="container">
<br>
<br>
    <button type="submit" class="back btn">back</button>
<br>
<br>
</div>

<div class="container">
    <table class="table table-bordered" id="folders" style="display: none">
        <tr>
            <th>Folder name</th>
            <th>Parent folder name</th>
            <th>Size(byte)</th>
            <th></th>
        </tr>
    </table>
    <br>
    <br>
    <table class="table table-bordered"  id="files" style="display: none">
        <tr>
            <th>File name</th>
            <th>Parent folder name</th>
            <th>Size(byte)</th>
            <th></th>
        </tr>
    </table>
</div>


</body>
</html>
