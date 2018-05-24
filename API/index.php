<?php
require_once __DIR__ . '/db_connect.php';

function auth() {
    $login = $_GET['login'];
    $password = $_GET['password'];
    $password = md5("dsfds+342d" . $password);
    $sql = "SELECT * FROM users WHERE login = '$login' AND password = '$password'";
    $db = new Db_connect();
    $query = mysqli_query($db->link, $sql);
    /*foreach ($query as $value) {
        $result = $value['login'];
        var_dump($value);
    }*/
    if (mysqli_num_rows($query) > 0) {
        $result = 'success';
    } else {
        $result = 'failed';
    }
     echo $result;
}

function reg() {
    $fio = $_GET['fio'];
    $login = $_GET['login'];
    $password = $_GET['password'];
    $password = md5("dsfds+342d" . $password);
    $db = new Db_connect();
    $sql1 = "INSERT INTO users (fio, login, password, role_id) VALUES ('$fio', '$login', '$password', 2)";
    $query1 = mysqli_query($db->link, $sql1);
    if ($query1) {
        $sql2 = "SELECT * FROM users WHERE login = '$login' AND password = '$password'";
        $query2 = mysqli_query($db->link, $sql2);
        foreach ($query2 as $value) {
            $id = $value['id'];
            $sql3 = "INSERT INTO blogs (title, user_id) VALUES ('блог пользователя : $login', $id)";
            $query3 = mysqli_query($db->link, $sql3);
            if ($query3) {
                $result = 'success';
            } else {
                $result = 'Create blog error'; 
            }
        }
        $result = 'success';
    } else {
        $result = 'Registration error';
    }
     echo $result;
}

function getAllBlogs() {
    $blogs = [];
    $sql = "SELECT * FROM blogs";
    $db = new Db_connect();
    $query = mysqli_query($db->link, $sql);
    if ($query) {
        foreach ($query as $value) {
            array_push($blogs, $value);
        }
        $result = $blogs;
    } else {
        $result = 'failed';
    }
     echo json_encode($result);
}

$_GET['fun']();

?>