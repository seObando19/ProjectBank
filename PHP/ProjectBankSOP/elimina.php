<?php 

if(isset($_REQUEST['ident'])) {

$ident=$_REQUEST['ident'];

$cnx =  mysqli_connect("localhost","root","","bd_projectbank") or die("Ha sucedido un error inexperado en la conexion de la base de datos");
$result = mysqli_query($cnx,"select ident from cliente where ident = '$ident'");
if (mysqli_num_rows($result)>0)
{
	mysqli_query($cnx,"DELETE from cliente where ident='$ident'");	
}
else
{
	echo "Usuario no existe....";
}
mysqli_close($cnx);

}
else{
	echo "Ingrese los datos del usuario....";
}



 ?>
