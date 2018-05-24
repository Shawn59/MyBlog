<?php
class Db_connect {
    
    private $user = 'id5861385_root';
    private $dbName = 'id5861385_blog';
    private $password = '123123';
    private $dbServer = 'localhost';
    public $link;
    
    public function __construct() {
        $this->connect();
    }
    
    public function __destruct() {
        
    }
    public function connect() {
        $this->link = mysqli_connect($this->dbServer, $this->user, $this->password) or die(mysqli_error());
        $con_bd = mysqli_select_db($this->link, $this->dbName) or die(mysqli_error());
    }
}
?>