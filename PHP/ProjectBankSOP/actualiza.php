<?php 

if(isset($_REQUEST['ident']) && isset($_REQUEST['nombres']) && isset($_REQUEST['email']) &&
isset($_REQUEST['clave'])) {

$ident=$_REQUEST['ident'];
$nombres=$_REQUEST['nombres'];
$email=$_REQUEST['email'];
$clave=$_REQUEST['clave'];
$cnx =  mysqli_connect("localhost","root","","bd_projectbank") or die("Ha sucedido un error inexperado en la conexion de la base de datos");
$result = mysqli_query($cnx,"select ident from cliente where ident = '$ident'");
if (mysqli_num_rows($result)>0)
{
	mysqli_query($cnx,"UPDATE cliente SET nombres='$nombres', email='$email', clave='$clave' where ident='$ident'");	
}
else
{
	echo "Usuario No existe....";
}
mysqli_close($cnx);

}
else{
	echo "Ingrese todos los datos....";
}



 ?>
